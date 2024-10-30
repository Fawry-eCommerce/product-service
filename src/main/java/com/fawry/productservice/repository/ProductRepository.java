package com.fawry.productservice.repository;

import com.fawry.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsById(Long id);

    @Query("SELECT p FROM Product p WHERE " +
            "(:name is null or :name='' or p.name like %:name%) and " +
            "(:category is null or :category='' or p.category.name like %:category%) and " +
            "(:code is null or p.code like %:code%)")
    List<Product> searchProducts(String name, String category, String code);

}
