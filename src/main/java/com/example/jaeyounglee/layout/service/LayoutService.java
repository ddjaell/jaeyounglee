package com.example.jaeyounglee.layout.service;

import com.example.jaeyounglee.layout.model.LayoutDAO;
import com.example.jaeyounglee.layout.model.LayoutRequest;
import com.example.jaeyounglee.layout.model.LayoutResponse;
import com.example.jaeyounglee.layout.repository.LayoutRepository;
import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LayoutService {
    private final LayoutRepository layoutRepository;
    
    public LayoutResponse getLayoutDetail(String id) {
        LayoutDAO layout = throwExceptionIfLayoutNotExist(id);
        return LayoutResponse.builder()
                .id(layout.getId())
                .name(layout.getName())
                .products(layout.getProducts().stream().map(product -> {
                    return ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public void createLayout(LayoutRequest request) {
        findLayoutById(request.getId()).ifPresent(layout -> {
            throw new RuntimeException("이미 존재하는 레이아웃입니다.");
        });
        layoutRepository.save(LayoutDAO.builder()
                .id(request.getId())
                .name(request.getName())
                .products(request.getProducts().stream().map(product -> {
                    return ProductDAO.builder()
                            .id(product.getId())
                            .build();
                }).collect(Collectors.toList()))
                .build());
    }

    private Optional<LayoutDAO> findLayoutById(String id) {
        return layoutRepository.findById(id);
    }

    private LayoutDAO throwExceptionIfLayoutNotExist(String id) {
        return findLayoutById(id)
                .orElseThrow(() ->
                        new RuntimeException("해당 ID의 레이아웃이 존재하지 않습니다.")
                );
    }
    @Transactional
    public void updateLayout(LayoutRequest request) {
        LayoutDAO layout = throwExceptionIfLayoutNotExist(request.getId());
        layout.setName(request.getName());
        layout.setProducts(request.getProducts().stream().map(product -> {
            return ProductDAO.builder()
                    .id(product.getId())
                    .build();
        }).collect(Collectors.toList()));
        layoutRepository.save(layout);
    }
    @Transactional
    public void deleteLayout(String id) {
        LayoutDAO layout = throwExceptionIfLayoutNotExist(id);
        layoutRepository.delete(layout);
    }
}
