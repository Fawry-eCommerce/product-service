package com.fawry.product_service.resource;


import com.fawry.product_service.entity.Category;
import com.fawry.product_service.model.CategoryModel;
import com.fawry.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryModel> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public void addCategory(@RequestBody CategoryModel categoryModel) {
        categoryService.addCategory(categoryModel);
    }

    @PutMapping
    public CategoryModel updateCategory(@RequestBody CategoryModel categoryModel) {
        return categoryService.updateCategory(categoryModel);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
    }
}
