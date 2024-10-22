package com.fawry.product_service.service.impl;


import com.fawry.product_service.entity.Product;
import com.fawry.product_service.mapper.ProductMapper;
import com.fawry.product_service.model.ProductModel;
import com.fawry.product_service.repository.CategoryRepository;
import com.fawry.product_service.repository.ProductRepository;
import com.fawry.product_service.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductModel> getProducts() {
        List<Product> products =productRepository.findAll();

        return productMapper.mapEntitiesToModels(products);
    }

    @Override
    public ProductModel getProductById(long id) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Fount"));
       return  productMapper.mapEntityToModel(savedProduct);
    }


    @Override
    public void addProduct(ProductModel productModel) {
        categoryRepository.findById(productModel.getCategoryModel().getId())
                .orElseThrow(() -> new RuntimeException("category Not exist"));

        Product product =productMapper.mapModelToEntity(productModel);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductModel updateProduct(ProductModel productModel) {
        Product savedProduct = productRepository.findById(productModel.getId())
                .orElseThrow(() -> new RuntimeException("Product Not Exist"));

        categoryRepository.findById(productModel.getCategoryModel().getId())
                .orElseThrow(() -> new RuntimeException("wrong category"));

        productMapper.updateModelToEntity(productModel, savedProduct);
        return productMapper.mapEntityToModel(savedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Exist"));
        productRepository.deleteById(id);
    }

}
