package com.fawry.product_service.model;

import lombok.Data;

@Data
public class ProductModel {
    private long id;
    private String name;
    private String sku;
    private String code;
    private double price;
    private String imageURL;
    private String description;

    private CategoryModel categoryModel;
}
