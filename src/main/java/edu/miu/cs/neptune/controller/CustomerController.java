package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("customer/category/products")

    public String listProductByCategory(@RequestParam("id") Long id, Model model){
        System.out.println("category ID"+id);
        List<Product> list = productService.findByCategoryId(id);
        System.out.println("----------category list"+list.toString());
        model.addAttribute("products", list);
//        return list;
        return "customer/productList";
    }

    @GetMapping("customer/categories")
    public String listCategory(Model model){
        model.addAttribute("categories", categoryService.getAll());
//        return "customer/categoryList";
        return "customer/categoryList";
    }
    @GetMapping("customer/product")
    public String getProductById(@RequestParam("id") Long productId, Model model){
        model.addAttribute("product", productService.getProductById(productId));
        return "customer/product";
//        return "fragments/sidenav_cus";
    }
}
