package com.example.jaeyounglee.product.service;

import com.example.jaeyounglee.common.enums.ErrorCode;
import com.example.jaeyounglee.common.exception.CommonException;
import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductRequest;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.jaeyounglee.common.enums.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(ProductRequest request) {
        findProductById(request.getId()).ifPresent(product -> {
            throw new CommonException(ALREADY_EXIST_PRODUCT);
        });
        productRepository.save(ProductDAO.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .build());
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductDetail(String id) {

        ProductDAO product = throwExceptionIfProductNotExist(id);
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
        ProductDAO product = throwExceptionIfProductNotExist(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        productRepository.save(product);
    }

    private Optional<ProductDAO> findProductById(String id) {
        return productRepository.findById(id);
    }
    private ProductDAO throwExceptionIfProductNotExist(String id) {
        return findProductById(id).orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));
    }

    @Transactional
    public void deleteProduct(String id) {
        productRepository.delete(throwExceptionIfProductNotExist(id));
    }
}
