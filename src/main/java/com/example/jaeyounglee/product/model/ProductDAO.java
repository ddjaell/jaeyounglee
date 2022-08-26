package com.example.jaeyounglee.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name =  "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDAO {
    @Id
    private String id;
    private String name;
    private Integer price;
}
