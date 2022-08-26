package com.example.jaeyounglee.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name =  "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDAO {
    @Id
    @NotNull
    private String id;

    @NotNull
    private String name;
    @NotNull
    private Integer price;
}
