package com.fawry.product_service.service.impl;

import com.fawry.product_service.entity.Category;
import com.fawry.product_service.exception.CategoryNotFoundException;
import com.fawry.product_service.mapper.CategoryMapper;
import com.fawry.product_service.model.CategoryModel;
import com.fawry.product_service.repository.CategoryRepository;
import com.fawry.product_service.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryModel> getCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.mapEntitiesToModels(categories);
    }

    @Override
    public void addCategory(CategoryModel categoryModel) {
        Category category = categoryMapper.mapModelToEntity(categoryModel);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public CategoryModel updateCategory(CategoryModel categoryModel) {
        Category savedCategory = categoryRepository.findById(categoryModel.getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + categoryModel.getId() + " not found"));

        categoryMapper.updateModelToSavedEntity(categoryModel, savedCategory);
        return categoryMapper.mapEntityToModel(savedCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + id + " not found"));
        categoryRepository.deleteById(id);
    }

}
