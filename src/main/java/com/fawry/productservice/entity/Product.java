package com.fawry.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String sku;
    private String code;
    private double price;
    private String imageURL;
    private String description;
    @ManyToOne
    private Category category;
}
