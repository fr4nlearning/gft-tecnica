package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void testFindProduct() {

        DataRS dataRS = new DataRS().builder()
                .startDate("2020-06-14-15.00.00")
                .endDate("2020-06-14-18.30.00")
                .priceList(2)
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(25.45))
                .build();

        when(priceService.findByDateProductBrand("2020-06-14 16:00:00", 35455, 1))
                .thenReturn(dataRS);

        ResponseEntity<DataRS> result = priceController.findByDateProductBrand("2020-06-14 16:00:00", 35455, 1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(dataRS, result.getBody());

    }

}