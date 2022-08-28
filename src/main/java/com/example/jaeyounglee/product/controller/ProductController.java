package com.example.jaeyounglee.product.controller;

import com.example.jaeyounglee.product.model.ProductRequest;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody @Valid final ProductRequest request) {
        productService.createProduct(request);
        return new ResponseEntity<>("SUCCES", HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable final String id) {
        return new ResponseEntity<>(
                productService.getProductDetail(id),
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
    public ResponseEntity<String> updateProductDetail(@RequestBody @Valid final ProductRequest request) {
        productService.updateProductDetail(request);
        return new ResponseEntity<>(
                "SUCCESS",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable final String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(
                "SUCCESS",
                HttpStatus.OK
        );
    }
}
