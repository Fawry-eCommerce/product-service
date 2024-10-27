package com.fawry.productservice.service;

import com.fawry.productservice.entity.Category;
import com.fawry.productservice.model.CategoryModel;

import java.util.List;

public interface CategoryService {

     Category getCategory(Long categoryId);

     CategoryModel getCategoryById(Long categoryId);

     List<CategoryModel> getCategories();

     void addCategory(CategoryModel categoryModel);

     CategoryModel updateCategory(CategoryModel categoryModel);

     void deleteCategory(Long id);

}
