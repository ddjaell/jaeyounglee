package com.example.jaeyounglee.product.service;

import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductRequest;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(ProductRequest request) {
        productRepository.save(ProductDAO.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .build());
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductDetail(ProductRequest request) {

        ProductDAO product = this.findProductById(request.getId());
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductList() {
        return productRepository.findAll().stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateProductDetail(ProductRequest request) {
        ProductDAO product = this.findProductById(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        productRepository.save(product);
    }

    private ProductDAO findProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 상품이 없습니다."));
    }

    @Transactional
    public void deleteProduct(ProductRequest request) {
        productRepository.deleteById(request.getId());
    }
}
