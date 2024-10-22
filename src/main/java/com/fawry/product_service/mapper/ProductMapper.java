package com.fawry.product_service.mapper;

import com.fawry.product_service.entity.Product;
import com.fawry.product_service.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper (componentModel = "spring")
public interface ProductMapper {


    ProductModel mapEntityToModel(Product product);

    List<ProductModel> mapEntitiesToModels(List<Product> products);

    Product mapModelToEntity(ProductModel productModel);

    @Mapping(target = "id", source = "id", ignore = true)
    void updateModelToEntity(ProductModel productModel, @MappingTarget Product product);

}
