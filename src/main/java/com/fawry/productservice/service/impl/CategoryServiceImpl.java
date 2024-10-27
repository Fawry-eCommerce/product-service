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
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category with " + categoryId + " not found")
                );
    }

    @Override
    public CategoryModel getCategoryById(Long categoryId) {
        return categoryMapper.mapEntityToModel(getCategory(categoryId));
    }

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
        Category savedCategory = getCategory(categoryModel.getId());
        categoryMapper.updateModelToSavedEntity(categoryModel, savedCategory);
        return categoryMapper.mapEntityToModel(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
    }

}
