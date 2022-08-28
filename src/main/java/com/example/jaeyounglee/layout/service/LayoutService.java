package com.example.jaeyounglee.layout.service;

import com.example.jaeyounglee.common.enums.ErrorCode;
import com.example.jaeyounglee.common.exception.CommonException;
import com.example.jaeyounglee.layout.model.LayoutDAO;
import com.example.jaeyounglee.layout.model.LayoutRequest;
import com.example.jaeyounglee.layout.model.LayoutResponse;
import com.example.jaeyounglee.layout.repository.LayoutRepository;
import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.example.jaeyounglee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.jaeyounglee.common.enums.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
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
            throw new CommonException(ALREADY_EXIST_LAYOUT);
        });
        try{
            layoutRepository.save(LayoutDAO.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .products(request.getProducts().stream().map(product -> {
                        return ProductDAO.builder()
                                .id(product.getId())
                                .build();
                    }).collect(Collectors.toList()))
                    .build());
        }catch(Exception e) {
            log.error(e.getMessage());
            throw new CommonException(UNKNOWN_ERROR);
        }
    }

    private Optional<LayoutDAO> findLayoutById(String id) {
        return layoutRepository.findById(id);
    }

    private LayoutDAO throwExceptionIfLayoutNotExist(String id) {
        return findLayoutById(id)
                .orElseThrow(() ->
                        new CommonException(NOT_FOUND_LAYOUT)
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
        try{
            layoutRepository.save(layout);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CommonException(UNKNOWN_ERROR);
        }
    }
    @Transactional
    public void deleteLayout(String id) {
        layoutRepository.delete(throwExceptionIfLayoutNotExist(id));
    }
}
