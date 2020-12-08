package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.service.CategoryService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("customer/")
public class CustomerController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("products")
    public String viewHomePage(Model model) {
        return listProduct(model, 1, "productName", "asc");
    }


    @GetMapping("products/{pageNum}")
    public String listProduct(Model model, @PathVariable(name = "pageNum") int pageNum,
                              @Param("sortField") String sortField,
                              @Param("sortDir") String sortDir){
        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
        List<Product> list = page.getContent();
        System.out.println(list);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", list);
        //System.out.println(productService.getAll());
        return "customer/productList";
    }
    @GetMapping("category/products")

    public String listProductByCategory(@RequestParam("id") Long id, Model model){
        System.out.println("category ID"+id);
        List<Product> list = productService.findByCategoryId(id);
        System.out.println("----------category list"+list.toString());
        model.addAttribute("products", list);
//        return list;
        return "customer/productList";
    }

    @GetMapping("categories")
    public String listCategory(Model model){
        model.addAttribute("categories", categoryService.getAll());
//        return "customer/categoryList";
        return "customer/categoryList";
    }
    @GetMapping("product")
    public String getProductById(@RequestParam("id") Long productId, Model model){
        model.addAttribute("product", productService.getProductById(productId));
        return "customer/product";
//        return "fragments/sidenav_cus";
    }
}
