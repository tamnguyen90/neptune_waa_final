package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller

@RequestMapping("customer/")
public class CustomerController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    public void general(Model model, Page<Product> page, int pageNum, String sortField, String sortDir){

        List<Product> list = page.getContent();
        System.out.println(list);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", list);
    }

    @GetMapping("products")
    public String viewHomePage(Model model) {
        return listProduct(model, 1, "productName", "asc");
    }


    @GetMapping("products/{pageNum}")
    public String listProduct(Model model, @PathVariable(name = "pageNum") int pageNum,
                              @Param("sortField") String sortField,
                              @Param("sortDir") String sortDir){
        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
        general(model, page, pageNum, sortField, sortDir);

//        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
//        List<Product> list = page.getContent();
//        System.out.println(list);
//        model.addAttribute("currentPage", pageNum);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("products", list);
        //System.out.println(productService.getAll());
        return "customer/productList";
    }
    @GetMapping("category/products")
    public String listProductByCategory(@RequestParam("id") Long id, Model model){
//        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
//        general(model, page, pageNum, sortField, sortDir);

//    public String listProductByCategory(@RequestParam("id") Long id, Model model, @PathVariable(name = "startNum") int startNum,
//                                        @Param("sortField") String sortField,
//                                        @Param("sortDir") String sortDir){
//        System.out.println("category ID"+id);
//        Slice<Product> page = productService.getProductsByCategoryId(id,1,5, "productName", "asc");
//        List<Product> list = page.getContent();
//        model.addAttribute("startNum", startNum);
//        model.addAttribute("next", page.hasNext());
//        model.addAttribute("totalItems", page.getSize());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("products", list);
//        Page<Product> page = productService.listAll(1, "firstName", "asc");
//        general(model, page, 1, "firstName", "asc");
        List<Product> list = productService.getProductsByCategoryId(id);
        System.out.println("----------category list"+list.toString());
        model.addAttribute("products", list);
//        return list;
        return "customer/productsByCategory";
    }

    @GetMapping("categories")
    public String listCategory(Model model){
        model.addAttribute("categories", categoryService.getAll());
//        return "customer/categoryList";
        model.addAttribute("searchURL", "/customer/products");
        return "customer/categoryList";
    }
    @GetMapping("product")
    public String getProductById(@RequestParam("id") Long productId, Model model){
        System.out.println("product ID: " + productId);
        List<Category> categories = categoryService.getAll();
        Product product = productService.getProductById(productId);
        System.out.println(categories + "cate list");
        StringBuilder categoryName = new StringBuilder();
        for (Category cat:categories){
            if(cat.getCategoryId()==product.getCategoryId()){
                categoryName.append(cat.getCategoryName()+ " ");
            }
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryName);
        model.addAttribute("images", product.getDbImages());
        model.addAttribute("searchURL", "/customer/products");
        System.out.println(product.getDbImages());
        return "customer/product";
//        return "fragments/sidenav_cus";
    }

    @RequestMapping("product_search")
    public String  searchProducts( @RequestParam("keyword") String keyword, @RequestParam("param") Integer pageNum, Model model){
        System.out.println(pageNum);
        String key=keyword.toLowerCase();
        System.out.println(key);
        Page<Product> page = productService.findProductsByProductNameContains(keyword, pageNum, "productName", "asc");

        List<Product> list = page.getContent();
        System.out.println(list);
        general(model, page, pageNum, "productName", "asc");
        model.addAttribute("products", list);
        model.addAttribute("keyword", keyword);
        return "customer/productList";
    }
}
