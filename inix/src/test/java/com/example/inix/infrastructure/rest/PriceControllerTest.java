package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.utils.PriceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        LocalDateTime localDateTime= PriceUtils.fromStringToDate("2020-06-14-16.00.00");

        when(priceService.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(dataRS);

        ResponseEntity<DataRS> result = priceController.findByDateProductBrand("2020-06-14-16.00.00", 35455, 1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(dataRS, result.getBody());

    }

    @Test
    public void testFindProduct_IllegalArgumentException() {

        assertThrows(IllegalArgumentException.class,
                () -> priceController.findByDateProductBrand("2020-06-14T16.00.00", 35455, 1));
    }

    @Test
    public void testFindProduct_NotFound() {

        DataRS dataRS = null;

        LocalDateTime localDateTime= PriceUtils.fromStringToDate("2020-06-14-16.00.00");

        when(priceService.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(dataRS);

        ResponseEntity<DataRS> result = priceController.findByDateProductBrand("2020-06-14-16.00.00", 35455, 1);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}