package com.fawry.product_service.service;

import com.fawry.product_service.entity.Category;
import com.fawry.product_service.model.CategoryModel;

import java.util.List;

public interface CategoryService {
     List<CategoryModel> getCategories();

     void addCategory(CategoryModel categoryModel);

     CategoryModel updateCategory(CategoryModel categoryModel);

     void deleteCategory(long id);

}
