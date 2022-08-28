package com.example.jaeyounglee.layout.service;

import ch.qos.logback.core.Layout;
import com.example.jaeyounglee.layout.model.LayoutDAO;
import com.example.jaeyounglee.layout.model.LayoutRequest;
import com.example.jaeyounglee.layout.model.LayoutResponse;
import com.example.jaeyounglee.layout.repository.LayoutRepository;
import com.example.jaeyounglee.product.model.ProductDAO;
import com.example.jaeyounglee.product.model.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LayoutServiceTest {

    @InjectMocks
    private LayoutService layoutService;

    @Mock
    private LayoutRepository layoutRepository;
    private final List<ProductDAO> productList = Arrays.asList(
            ProductDAO.builder()
                    .id("001")
                    .name("Apple 1 box")
                    .price(25000)
                    .build(),
            ProductDAO.builder()
                    .id("002")
                    .name("banana")
                    .price(5000)
                    .build()
    );

    private final List<ProductResponse> productResponseList = Arrays.asList(
            ProductResponse.builder()
                    .id("001")
                    .name("Apple 1 box")
                    .price(25000)
                    .build(),
            ProductResponse.builder()
                    .id("002")
                    .name("banana")
                    .price(5000)
                    .build()
    );

    private final LayoutRequest defaultLayoutRequest = LayoutRequest.builder()
            .id("001")
            .name("product layout page 001")
            .products(productResponseList)
            .build();

    private final LayoutDAO defaultLayout = LayoutDAO.builder()
            .id("001")
            .name("product layout page 001")
            .products(productList)
            .build();



    @Test
    public void getLayoutTest() {
        given(layoutRepository.findById(anyString())).willReturn(
                Optional.of(defaultLayout)
        );
        LayoutResponse layoutResponse = layoutService.getLayoutDetail("layoutId");

        assertEquals("001", layoutResponse.getId());
        assertEquals("product layout page 001", layoutResponse.getName());
        assertEquals(productResponseList, layoutResponse.getProducts());
    }
    @Test
    public void createLayoutTest() {
        //given
        given(layoutRepository.findById(anyString())).willReturn(
                Optional.empty()
        );
        ArgumentCaptor<LayoutDAO> captor = ArgumentCaptor.forClass(LayoutDAO.class);
        //when
        layoutService.createLayout(defaultLayoutRequest);
        //then
        verify(layoutRepository, times(1))
                .save(captor.capture());
        LayoutDAO savingLayout = captor.getValue();
        assertEquals("001", savingLayout.getId());
        assertEquals("product layout page 001", savingLayout.getName());

    }

    @Test
    public void updateLayoutTest() throws JsonProcessingException {
        //given
        given(layoutRepository.findById(anyString())).willReturn(
                Optional.of(defaultLayout)
        );
        ArgumentCaptor<LayoutDAO> captor = ArgumentCaptor.forClass(LayoutDAO.class);

        ObjectMapper mapper = new ObjectMapper();
        LayoutRequest request = mapper.readValue(mapper.writeValueAsString(defaultLayoutRequest), LayoutRequest.class);
        //when
        request.setName("page001");
        layoutService.updateLayout(request);

        //test
        verify(layoutRepository, times(1))
                .save(captor.capture());
        LayoutDAO updatingLayout = captor.getValue();

        assertEquals("001", updatingLayout.getId());
        assertEquals("page001", updatingLayout.getName());


    }
    @Test
    public void deleteLayoutTest() {
        //given
        given(layoutRepository.findById(anyString())).willReturn(
                Optional.of(defaultLayout)
        );
        ArgumentCaptor<LayoutDAO> captor = ArgumentCaptor.forClass(LayoutDAO.class);
        //when
        layoutService.deleteLayout(defaultLayoutRequest.getId());

        //test
        verify(layoutRepository, times(1))
                .delete(captor.capture());
        LayoutDAO deletingLayout = captor.getValue();

        assertEquals("001", deletingLayout.getId());
        assertEquals("product layout page 001", deletingLayout.getName());

    }


}
