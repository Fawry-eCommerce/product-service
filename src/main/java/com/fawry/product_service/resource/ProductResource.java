package com.fawry.product_service.resource;


import com.fawry.product_service.model.ProductModel;
import com.fawry.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductResource {


    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductModel> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductModel productModel) {
        productService.addProduct(productModel);
    }

    @PutMapping
    public ProductModel updateProduct(@RequestBody ProductModel productModel) {
        return productService.updateProduct(productModel);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
