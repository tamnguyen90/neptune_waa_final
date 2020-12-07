package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String getCategories(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);

        return "category/ListCategories";
    }

    @GetMapping(value = "/create")
    public String inputCategory(@ModelAttribute("category") Category category) {
        return "category/CategoryForm";
    }

    @RequestMapping(value = "/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);

        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);

        return "redirect:/categories";
    }

    @RequestMapping(value = "/edit/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId, Model model) {
        Optional<Category> category = categoryService.findById(categoryId);

        model.addAttribute("category", category.orElse(null));
        return "/category/CategoryEditForm";
    }
}
