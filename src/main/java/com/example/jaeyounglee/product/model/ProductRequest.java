package com.example.jaeyounglee.product.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProductRequest {
    @NonNull
    private String id;
    @NonNull
    @Size(min = 3, max = 100, message = "상품명은 3자리 이상 100자리 이하여야합니다.")
    private String name;
    @NonNull
    private Integer price;
}
