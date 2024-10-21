package com.fawry.product_service.service.impl;


import com.fawry.product_service.entity.Category;
import com.fawry.product_service.entity.Product;
import com.fawry.product_service.model.CategoryModel;
import com.fawry.product_service.model.ProductModel;
import com.fawry.product_service.repository.ProductRepository;
import com.fawry.product_service.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<ProductModel> getProducts() {
        List<Product> products =productRepository.findAll();

        return products
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());

    }

    @Override
    public void addProduct(ProductModel productModel) {
        Product product = mapModelToEntity(productModel);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductModel updateProduct(ProductModel productModel) {
        Product savedProduct = productRepository.findById(productModel.getId())
                .orElseThrow(() -> new RuntimeException("Product Not Exist"));

        savedProduct.setName(productModel.getName());
        savedProduct.setSku(productModel.getSku());
        savedProduct.setCode(productModel.getCode());
        savedProduct.setPrice(productModel.getPrice());
        savedProduct.setImageURL(productModel.getImageURL());
        savedProduct.setDescription(productModel.getDescription());

        return mapEntityToModel(savedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Exist"));
        productRepository.deleteById(id);
    }
    private ProductModel mapEntityToModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setSku(product.getSku());
        productModel.setCode(product.getCode());
        productModel.setPrice(product.getPrice());
        productModel.setImageURL(product.getImageURL());
        productModel.setDescription(product.getDescription());

        CategoryModel categoryModel =new CategoryModel();
        categoryModel.setId(product.getCategory().getId());
        productModel.setCategoryModel(categoryModel);
        return productModel;
    }

       private Product mapModelToEntity(ProductModel productModel) {
        Product product = new Product();
        product.setName(productModel.getName());
        product.setSku(productModel.getSku());
        product.setCode(productModel.getCode());
        product.setPrice(productModel.getPrice());
        product.setImageURL(productModel.getImageURL());
        product.setDescription(productModel.getDescription());

        Category category = new Category();
        category.setId(productModel.getCategoryModel().getId());

        product.setCategory(category);
        return product;
    }
}
