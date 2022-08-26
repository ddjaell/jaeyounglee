package com.example.jaeyounglee.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private Integer price;

    public static ProductResponse from(ProductDAO product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
