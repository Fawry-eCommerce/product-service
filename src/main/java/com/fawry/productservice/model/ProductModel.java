package com.fawry.productservice.model;

import lombok.Data;

@Data
public class ProductModel { // TODO: handle validation
    private Long id;
    private String name;
    private String sku;
    private String code;
    private double price;
    private String imageUrl;
    private String description;

    private CategoryModel categoryModel;
}
