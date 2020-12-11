package edu.miu.cs.neptune.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    int count = 1;
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
        count = 1;
        return listProduct(model, 1, "productName", "asc");
    }


    @GetMapping("products/{pageNum}")
    public String listProduct(Model model, @PathVariable(name = "pageNum") int pageNum,
                              @Param("sortField") String sortField,
                              @Param("sortDir") String sortDir){
        count = 1;
        Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
        general(model, page, pageNum, sortField, sortDir);
        return "customer/productList";
    }
    @GetMapping("category/products")
    public String listProductByCategory(@RequestParam("id") Long id, Model model){
        count = 1;
        Page<Product> page = productService.getProductsByCategoryId(id, 1, "productName", "asc");
        general(model, page, 1, "productName", "asc");

        List<Product> list = page.getContent();
        System.out.println("----------category list"+list.toString());
        model.addAttribute("products", list);
        model.addAttribute("categoryId", id);
        return "customer/productsByCategory";
    }
    @GetMapping("category/productsSort/{pageNum}")
    public String listProductByCategorySort(Model model, @PathVariable(name = "pageNum") int pageNum,
                                            @Param("sortField") String sortField,
                                            @Param("sortDir") String sortDir,
                                            @Param("categoryId") Long categoryId){
        count = 1;
        Page<Product> page = productService.getProductsByCategoryId(categoryId, 1, sortField, sortDir);
        general(model, page, 1, sortField, sortDir);

        List<Product> list = page.getContent();
        System.out.println("----------category list"+list.toString());
        model.addAttribute("products", list);
        model.addAttribute("categoryId", categoryId);
        return "customer/productsByCategory";
    }
    @PostMapping("category/productsNext")
    @RequestMapping(value = "category/productsNext",
    method = RequestMethod.POST
    )
    @ResponseBody
    public String listProductByCategoryOrder(@RequestBody String request, Model model) throws JsonProcessingException {
        count++;
        System.out.println("test ......" + request);
        JsonObject jsonObject = new JsonParser().parse(request).getAsJsonObject();
        System.out.println(jsonObject.get("currentPage"));
        int pageNum = jsonObject.get("currentPage").getAsInt();
        System.out.println(pageNum);
        Long categoryId = jsonObject.get("categoryId").getAsLong();
        System.out.println(categoryId);
        String sortDir = jsonObject.get("sortDir").getAsString();
        System.out.println(sortDir);
        String sortField = jsonObject.get("sortField").getAsString();
        System.out.println(sortField);

        Page<Product> page = productService.getProductsByCategoryId(categoryId, count, sortField, sortDir);
        general(model, page, pageNum, sortField, sortDir);
//        System.out.println(id + "category id");
        List<Product> list = page.getContent();
        if(page.getTotalPages()>=count){
            return list.toString() + page.getTotalPages();
        }
        else
            return "done";
    }

    @GetMapping("categories")
    public String listCategory(Model model){
        count = 1;
        model.addAttribute("categories", categoryService.getAll());
        return "customer/categoryList";
    }

    @GetMapping("product")
    public String getProductById(@RequestParam("id") Long productId, Model model){
        count = 1;
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
//        model.addAttribute("searchURL", "/customer/products");
        System.out.println(product.getDbImages());
        return "customer/product";
//        return "fragments/sidenav_cus";
    }

    @RequestMapping("product_search")
    public String  searchProducts( @RequestParam("keyword") String keyword, Model model){
        count = 1;
        //System.out.println(pageNum);
        String key=keyword.toLowerCase();
        System.out.println(key);
        Page<Product> page = productService.findProductsByProductNameContains(keyword, 1, "productName", "asc");
        List<Product> list = page.getContent();
        System.out.println(list);
        general(model, page, 1, "productName", "asc");
        model.addAttribute("products", list);
        model.addAttribute("keyword", keyword);
       // model.addAttribute("searchURL", "/customer/products");
        return "customer/productList";
    }

}
