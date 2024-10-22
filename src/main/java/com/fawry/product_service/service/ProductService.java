package com.fawry.product_service.service;

import com.fawry.product_service.entity.Category;
import com.fawry.product_service.entity.Product;
import com.fawry.product_service.model.ProductModel;

import java.util.List;

public interface ProductService {

    List<ProductModel> getProducts();

    ProductModel getProductById(long id);

    void addProduct(ProductModel productModel);

    ProductModel updateProduct(ProductModel productModel);

    void deleteProduct(long id);
}
