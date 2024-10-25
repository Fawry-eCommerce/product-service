package com.fawry.productservice.resource;


import com.fawry.productservice.model.CategoryModel;
import com.fawry.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

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
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
