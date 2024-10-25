package com.fawry.productservice.service.impl;

import com.fawry.productservice.entity.Category;
import com.fawry.productservice.exception.CategoryNotFoundException;
import com.fawry.productservice.mapper.CategoryMapper;
import com.fawry.productservice.model.CategoryModel;
import com.fawry.productservice.repository.CategoryRepository;
import com.fawry.productservice.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

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
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category with ID " + categoryModel.getId() + " not found")
                );
        categoryMapper.updateModelToSavedEntity(categoryModel, savedCategory);
        return categoryMapper.mapEntityToModel(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with ID " + id + " not found")
        );
        categoryRepository.deleteById(id);
    }

}
