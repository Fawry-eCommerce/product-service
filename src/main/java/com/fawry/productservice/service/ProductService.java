package com.fawry.productservice.service;

import com.fawry.productservice.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    List<ProductModel> getProducts();

    Page<ProductModel> getProductsByIds(List<Long> ids, Pageable pageable);

    ProductModel getProductById(Long id);

    void addProduct(ProductModel productModel);

    ProductModel updateProduct(ProductModel productModel);

    void deleteProduct(Long id);

    boolean checkProductExists(@RequestParam("productId") Long productId);


}
