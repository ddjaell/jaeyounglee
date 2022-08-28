package com.example.jaeyounglee.layout.model;

import com.example.jaeyounglee.product.model.ProductResponse;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LayoutRequest {
    @NonNull
    private String id;
    @NonNull
    @Size(min = 3, max = 100, message = "레이아웃 명은 3자리 이상 100자리 이하여야합니다.")
    private String name;
    @NonNull
    private List<ProductResponse> products;
}
