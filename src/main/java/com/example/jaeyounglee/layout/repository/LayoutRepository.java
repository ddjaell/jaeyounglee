package com.example.jaeyounglee.layout.repository;

import com.example.jaeyounglee.layout.model.LayoutDAO;
import com.example.jaeyounglee.product.model.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LayoutRepository extends JpaRepository<LayoutDAO, String> {
    public Optional<LayoutDAO> findById(String id);
    public List<LayoutDAO> findAllById(String id);
}
