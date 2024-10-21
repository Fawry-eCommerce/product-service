package com.fawry.product_service.service.impl;

import com.fawry.product_service.entity.Category;
import com.fawry.product_service.model.CategoryModel;
import com.fawry.product_service.repository.CategoryRepository;
import com.fawry.product_service.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> getCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public void addCategory(CategoryModel categoryModel) {

        Category category = mapModelToEntity(categoryModel);
        categoryRepository.save(category);
    }


    @Override
    @Transactional
    public CategoryModel updateCategory(CategoryModel categoryModel) {
        Category savedCategory = categoryRepository.findById(categoryModel.getId())
                .orElseThrow(() -> new RuntimeException("Category Not Exist"));

        savedCategory.setCode(categoryModel.getCode());
        savedCategory.setName(categoryModel.getName());

        return mapEntityToModel(savedCategory);
    }


    @Override
    public void deleteCategory(long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Exist"));
        categoryRepository.deleteById(id);
    }

    private CategoryModel mapEntityToModel(Category category) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        categoryModel.setCode(category.getCode());

        return categoryModel;
    }

    private Category mapModelToEntity(CategoryModel categoryModel) {
        Category category = new Category();
        category.setName(categoryModel.getName());
        category.setCode(categoryModel.getCode());
        return category;
    }
}
