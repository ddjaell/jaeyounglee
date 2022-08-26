package com.example.jaeyounglee.product.controller;

import com.example.jaeyounglee.product.model.ProductRequest;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody final ProductRequest request) {
        productService.createProduct(request);
        return new ResponseEntity<>("SUCCES", HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductResponse> getProduct(@RequestBody final ProductRequest request) {
        return new ResponseEntity<>(
                productService.getProductDetail(request),
                HttpStatus.OK
        );
    }

    @GetMapping("/productList")
    public ResponseEntity<List<ProductResponse>> getProductList() {
        return new ResponseEntity<>(
                productService.getProductList(),
                HttpStatus.OK
        );
    }

    @PatchMapping("/product")
    public ResponseEntity<String> updateProductDetail(@RequestBody final ProductRequest request) {
        productService.updateProductDetail(request);
        return new ResponseEntity<>(
                "SUCCESS",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/product")
    public ResponseEntity<String> deleteProduct(@RequestBody final ProductRequest request) {
        productService.deleteProduct(request);
        return new ResponseEntity<>(
                "SUCCESS",
                HttpStatus.OK
        );
    }
}
