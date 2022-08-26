package com.example.jaeyounglee.layout.model;

import com.example.jaeyounglee.product.model.ProductResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LayoutResponse {
    private String id;
    private String name;
    private List<ProductResponse> products;
}
