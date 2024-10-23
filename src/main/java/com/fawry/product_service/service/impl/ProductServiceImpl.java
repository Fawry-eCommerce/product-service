package com.fawry.product_service.service.impl;


import com.fawry.product_service.entity.Product;
import com.fawry.product_service.exception.ProductNotFoundException;
import com.fawry.product_service.mapper.ProductMapper;
import com.fawry.product_service.model.ProductModel;
import com.fawry.product_service.repository.CategoryRepository;
import com.fawry.product_service.repository.ProductRepository;
import com.fawry.product_service.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductModel> getProductsByIds(List<Long> ids, Pageable pageable) {
        List<Product> savedProducts = productRepository.findAllById(ids);
        if (savedProducts.isEmpty()) {
            throw new ProductNotFoundException("No products found for the provided IDs: " + ids);
        }
        return new PageImpl<>(productMapper.mapEntitiesToModels(savedProducts));
    }

    @Override
    public ProductModel getProductById(long id) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(("Product with ID " + id + " not found")));
       return  productMapper.mapEntityToModel(savedProduct);
    }


    @Override
    public void addProduct(ProductModel productModel) {
        categoryRepository.findById(productModel.getCategoryModel().getId())
                .orElseThrow(() -> new ProductNotFoundException("Category with ID " + productModel.getCategoryModel().getId() + " not exist"));

        Product product =productMapper.mapModelToEntity(productModel);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductModel updateProduct(ProductModel productModel) {
        Product savedProduct = productRepository.findById(productModel.getId())
                .orElseThrow(() -> new ProductNotFoundException ("Product with ID " + productModel.getId() + " not exist"));

        categoryRepository.findById(productModel.getCategoryModel().getId())
                .orElseThrow(() -> new ProductNotFoundException("Category with ID " + productModel.getCategoryModel().getId() + " not found"));

        productMapper.updateModelToEntity(productModel, savedProduct);
        return productMapper.mapEntityToModel(savedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not exist"));
        productRepository.deleteById(id);
    }

}
