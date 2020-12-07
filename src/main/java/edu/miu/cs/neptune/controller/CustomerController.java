package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller


public class CustomerController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("customer/products")
    public String listProduct(Model model){
        model.addAttribute("products", productService.getAll());
        System.out.println(productService.getAll());
        return "customer/productList";
    }

    @GetMapping("customer/categories")
    public String listCategory(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "customer/categoryList";
    }
    @GetMapping("customer/product")
    public String getProductById(@RequestParam("id") String productId, Model model){
        model.addAttribute("product", productService.getProductById(productId));
        return "customer/product";
    }
}
