package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/category")
    public String inputCategory(@ModelAttribute("category") Category category) {
        return "CategoryForm";
    }

    @GetMapping(value = "/category/category_list")
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAll();

        model.addAttribute("categories", categories);

        return "ListCategories";
    }

    @RequestMapping(value = "/category/category_save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);

        return "CategoryDetails";
    }

}
