package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.exception.NoProductsFoundUnderCategoryException;
import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.swing.text.html.Option;
import java.io.File;
import java.util.List;
import java.util.Optional;

@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ServletContext servletContext;

    public ProductController(ProductService productService, CategoryService categoryService, ServletContext servletContext) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.servletContext = servletContext;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('Admin')")
    private String getALlProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product/AllProducts";
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

        return "product/ProductDetail";
    }

    @GetMapping("/inputProduct")
    @PreAuthorize("hasRole('Seller')")
    public String addProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "product/ProductForm";
    }

    @PostMapping("/saveProduct")
    @PreAuthorize("hasRole('Seller')")
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product/ProductForm";
        }

        List<MultipartFile> images = product.getImageList();
        String rootDirectory = servletContext.getRealPath("/");
        if (images != null && !images.isEmpty()) {
            try {
                int count = 0;
                for (MultipartFile image : images) {
                    image.transferTo(new File(rootDirectory+"resources\\images\\"+ product.getProductId() + "_" + (++count) + ".png"));
                }
            } catch (Exception ex) {
                throw new RuntimeException("Product image was saving failed", ex);
            }
        }

        productService.save(product);

        return "user/SellerDetails";
    }
}
