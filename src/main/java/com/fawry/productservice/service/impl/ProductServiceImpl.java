package com.fawry.productservice.service.impl;


import com.fawry.productservice.entity.Product;
import com.fawry.productservice.exception.ProductNotFoundException;
import com.fawry.productservice.mapper.ProductMapper;
import com.fawry.productservice.model.ProductModel;
import com.fawry.productservice.repository.CategoryRepository;
import com.fawry.productservice.repository.ProductRepository;
import com.fawry.productservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // TODO: use category service instead of repository
    private final ProductMapper productMapper;

    @Override
    public Page<ProductModel> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::mapEntityToModel);
    }

    @Override
    public Page<ProductModel> getProductsByIds(List<Long> ids, Pageable pageable) { // This logic is not correct, it should be implemented in the repository layer
        List<Product> savedProducts = productRepository.findAllById(ids);
        return new PageImpl<>(productMapper.mapEntitiesToModels(savedProducts));
    }

    @Override
    public ProductModel getProductById(Long id) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(("Product with ID " + id + " not found"))
                );
       return productMapper.mapEntityToModel(savedProduct);
    }


    @Override
    public void addProduct(ProductModel productModel) {
        categoryRepository.findById(productModel.getCategoryModel().getId())
                .orElseThrow(
                        () -> new ProductNotFoundException("Category with ID " + productModel.getCategoryModel().getId() + " not exist")
                );
        Product product = productMapper.mapModelToEntity(productModel);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductModel updateProduct(ProductModel productModel) {
        Product savedProduct = productRepository.findById(productModel.getId())
                .orElseThrow(
                        () -> new ProductNotFoundException ("Product with ID " + productModel.getId() + " not exist")
                );

        categoryRepository.findById(productModel.getCategoryModel().getId())
                .orElseThrow(
                        () -> new ProductNotFoundException("Category with ID " + productModel.getCategoryModel().getId() + " not found")
                ); // TODO: use category service instead of repository

        productMapper.updateModelToEntity(productModel, savedProduct);
        return productMapper.mapEntityToModel(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with ID " + id + " not exist")
        );
        productRepository.deleteById(id);
    }

    @Override
    public boolean checkProductExists(Long productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public List<ProductModel> searchProducts(String name, String category, String code) {
        return productRepository.searchProducts(name, category, code)
                .stream()
                .map(productMapper::mapEntityToModel)
                .toList();
    }
}
