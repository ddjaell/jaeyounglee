package com.example.jaeyounglee.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String id;
    private String name;
    private Integer price;
}
