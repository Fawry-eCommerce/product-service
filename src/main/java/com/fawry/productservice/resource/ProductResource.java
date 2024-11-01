package com.fawry.productservice.resource;


import com.fawry.productservice.model.ProductModel;
import com.fawry.productservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductResource {


    private final ProductService productService;

    @GetMapping
    public Page<ProductModel> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping("/details")
    public Page<ProductModel> getProductsDetailsByIds(@RequestParam List<Long> ids, Pageable pageable) {
        return productService.getProductsByIds(ids, pageable);
    }

    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable Long id) {
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
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/check-product/{productId}")
    public boolean checkProduct(@PathVariable Long productId) {
        return productService.checkProductExists(productId);
    }

    @GetMapping("/{productId}/validate")
    public void validateProductExists(@PathVariable Long productId) {
        if (!productService.checkProductExists(productId)) {
            throw new EntityNotFoundException("Product not found");
        }
    }

    @GetMapping("/search")
    public List<ProductModel> searchProducts(@RequestParam(defaultValue = "") String name,
                                             @RequestParam(defaultValue = "") String category,
                                             @RequestParam(defaultValue = "") String code) {
        return productService.searchProducts(name, category, code);
    }
}
