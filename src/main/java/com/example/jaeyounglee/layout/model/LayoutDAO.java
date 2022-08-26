package com.example.jaeyounglee.layout.model;

import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "layout")
public class LayoutDAO {
    @Id
    private String id;
    private String name;
    @ManyToMany
    private List<ProductDAO> products = new ArrayList<>();
}
