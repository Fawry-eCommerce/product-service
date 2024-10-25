package com.fawry.productservice.mapper;

import com.fawry.productservice.entity.Category;
import com.fawry.productservice.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryModel mapEntityToModel(Category category);

    List<CategoryModel> mapEntitiesToModels(List<Category> categories);

    Category mapModelToEntity(CategoryModel categoryModel);

    void updateModelToSavedEntity(CategoryModel categoryModel, @MappingTarget Category category);
}
