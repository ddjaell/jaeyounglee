package com.example.jaeyounglee.product.repository;

import com.example.jaeyounglee.product.model.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, String> {
    public Optional<ProductDAO> findById(String id);
    public List<ProductDAO> findAllById(String id);
}
