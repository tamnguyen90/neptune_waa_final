package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Category;
import edu.miu.cs.neptune.exception.CategoryException;
import edu.miu.cs.neptune.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        //handle validation errors
        if (bindingResult.hasErrors()) {
            return "category/CategoryForm";
        }

        String[] suppressedFields = bindingResult.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempt to bind fields that haven't been allowed in initBinder(): "
                    + StringUtils.addStringToArray(suppressedFields, ", "));
        }

        //Call service to save category.
        try {
            categoryService.save(category);
        } catch (CategoryException e) {
            model.addAttribute("error",e.getMessage());
            return "category/CategoryForm";
        }

        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId, RedirectAttributes redirectAttributes) {
        //Call service to save category.
        try {
            categoryService.delete(categoryId);
        } catch (CategoryException e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
        }

        return "redirect:/categories";
    }

    @RequestMapping(value = "/edit/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId, Model model) {
        Optional<Category> category = categoryService.findById(categoryId);

        model.addAttribute("category", category.orElse(null));
        return "/category/CategoryEditForm";
    }
}
