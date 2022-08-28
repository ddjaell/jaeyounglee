package com.example.jaeyounglee.product.service;

import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductRequest;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    private final ProductDAO defaultProduct = ProductDAO.builder()
            .id("001")
            .name("Apple 1 box")
            .price(25000)
            .build();

    private final ProductRequest defaultProductRequest = ProductRequest.builder()
            .id("001")
            .name("Apple 1 box")
            .price(25000)
            .build();

    @Test
    public void getProductDetailTest() {
        given(productRepository.findById(anyString())).willReturn(
                Optional.of(defaultProduct)
        );

        ProductResponse productResponse = productService.getProductDetail("productId");

        assertEquals("001", productResponse.getId());
        assertEquals("Apple 1 box", productResponse.getName());
        assertEquals(25000, productResponse.getPrice());
    }

    @Test
    public void createProductTest() {
        //given
        given(productRepository.findById(anyString())).willReturn(
                Optional.empty()
        );
        ArgumentCaptor<ProductDAO> captor = ArgumentCaptor.forClass(ProductDAO.class);

        //when
        productService.createProduct(defaultProductRequest);

        //test
        verify(productRepository, times(1))
                .save(captor.capture());
        ProductDAO savingProduct = captor.getValue();

        assertEquals("001", savingProduct.getId());
        assertEquals("Apple 1 box", savingProduct.getName());
        assertEquals(25000, savingProduct.getPrice());
    }

    @Test
    public void updateProductTest() {
        //given
        given(productRepository.findById(anyString())).willReturn(
                Optional.of(defaultProduct)
        );
        ArgumentCaptor<ProductDAO> captor = ArgumentCaptor.forClass(ProductDAO.class);

        //when
        productService.updateProductDetail(defaultProductRequest);

        //test
        verify(productRepository, times(1))
                .save(captor.capture());
        ProductDAO updatingProduct = captor.getValue();

        assertEquals("001", updatingProduct.getId());
        assertEquals("Apple 1 box", updatingProduct.getName());
        assertEquals(25000, updatingProduct.getPrice());
    }

    @Test
    public void deleteProductTest() {
        //given
        given(productRepository.findById(anyString())).willReturn(
                Optional.of(defaultProduct)
        );
        ArgumentCaptor<ProductDAO> captor = ArgumentCaptor.forClass(ProductDAO.class);
        //when
        productService.deleteProduct(defaultProductRequest.getId());

        //test
        verify(productRepository, times(1))
                .delete(captor.capture());
        ProductDAO deletingProduct = captor.getValue();

        assertEquals("001", deletingProduct.getId());
        assertEquals("Apple 1 box", deletingProduct.getName());
        assertEquals(25000, deletingProduct.getPrice());
    }

    @Test
    public void createProductFailTest() {
        //given
        given(productRepository.findById(anyString())).willReturn(
                Optional.of(defaultProduct)
        );

        //when
        //test
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> productService.createProduct(defaultProductRequest));

        assertEquals("이미 존재하는 상품 ID입니다.", exception.getMessage());
    }
}
