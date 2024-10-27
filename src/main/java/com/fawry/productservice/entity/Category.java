package com.fawry.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Category_seq_gen")
    @SequenceGenerator(name = "Category_seq_gen", sequenceName = "Category_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 30)
    private String code;
}