package com.fawry.product_service.mapper;

import com.fawry.product_service.entity.Category;
import com.fawry.product_service.model.CategoryModel;
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
