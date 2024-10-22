package com.fawry.product_service.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private long id;
    private String name;
    private String code;
}
