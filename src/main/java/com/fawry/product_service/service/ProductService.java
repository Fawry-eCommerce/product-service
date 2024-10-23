package com.fawry.product_service.service;

import com.fawry.product_service.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductModel> getProducts();

    Page<ProductModel> getProductsByIds(List<Long> ids, Pageable pageable);

    ProductModel getProductById(long id);

    void addProduct(ProductModel productModel);

    ProductModel updateProduct(ProductModel productModel);

    void deleteProduct(long id);
}
