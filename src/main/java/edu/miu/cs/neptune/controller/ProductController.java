package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.exception.NoProductsFoundUnderCategoryException;
import edu.miu.cs.neptune.exception.ProductDeleteException;
import edu.miu.cs.neptune.service.AuctionService;
import edu.miu.cs.neptune.service.BiddingService;
import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ServletContext servletContext;
    private final BiddingService biddingService;

    public ProductController(ProductService productService, CategoryService categoryService, ServletContext servletContext,
                             BiddingService biddingService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.servletContext = servletContext;
        this.biddingService = biddingService;
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('SELLER')")
    private String getAllProducts(Model model) {
        List<Product> products = productService.getAll();
        Integer numberOfBid = 0;

        Map<Long,Integer> productMaps = new HashMap<>();
        for (Product p: products
             ) {
            numberOfBid = biddingService.getNumberOfBidByProductId(p.getProductId());

            productMaps.put(p.getProductId(), numberOfBid);
        }
        model.addAttribute("products", productService.getAll());
        model.addAttribute("productMaps", productMaps);

        return "seller/AllProducts";
    }

    @GetMapping("/products/{category}")
    public String getProductsByCategory(@PathVariable("category") Long categoryId, Model model) {
        List<Product> productList = productService.getProductsByCategoryId(categoryId);

        if (productList == null || productList.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }

        model.addAttribute("products", productList);

        return "product/AllProducts";
    }

    @GetMapping("/{id}")
    public String productDetail(@PathVariable("id") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);

        return "seller/ProductDetail";
    }

    @GetMapping("/createProduct")
    //@PreAuthorize("hasRole('SELLER')")
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "seller/ProductForm";
    }

    @PostMapping("/saveProduct")
    //@PreAuthorize("hasRole('SELLER')")
    public String saveProduct(Product product,@RequestParam(value="action", required=true) String action,
                              BindingResult result) {
        Auction auction = product.getAuction();
        if(action.equals("Save And Release")) {
            product.setProductState(ProductState.SAVE_AND_RELEASE);
            auction.setAuctionStatus(AuctionStatus.ACTIVE);
        } else {
            product.setProductState(ProductState.SAVE_WITHOUT_RELEASE);
            auction.setAuctionStatus(AuctionStatus.INACTIVE);
        }

        if (result.hasErrors()) {
            return "seller/ProductForm";
        }

        List<MultipartFile> images = product.getImages();
        Long now = System.currentTimeMillis();
        if (images != null && !images.isEmpty()) {
            try {

                int count = 0;
                for (MultipartFile image : images) {
                    String uploadDir = "ProductImages/";
                    String fileName = now + "_" + ++count + ".png";
                    Util.saveFile(uploadDir, fileName, image);
                    product.addDbImage(new Image(fileName));
                }
            } catch (Exception ex) {
                throw new RuntimeException("Product image was saving failed", ex);
            }
        }

        productService.save(product);

        return "redirect:/product/all";
    }

    @RequestMapping(value = "/edit/{productId}")
    public String updateCategory(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);

        Integer numberOfBid = biddingService.getNumberOfBidByProductId(product.getProductId());
        Boolean disabled = false;

        if(numberOfBid>0) {
            disabled = true;
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("disabled", disabled);
        model.addAttribute("numberOfBid", numberOfBid);

        return "seller/ProductEditForm";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId, RedirectAttributes redirectAttributes) {
        try {
            productService.delete(productId);
        } catch (ProductDeleteException productDeleteException) {
            redirectAttributes.addFlashAttribute("error", productDeleteException.getMessage());
        }
        return "redirect:/product/all";
    }
}
