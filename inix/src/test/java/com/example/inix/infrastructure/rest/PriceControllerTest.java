package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.PriceRS;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void testFindProduct() throws PriceNotFoundException {

        final String PATTERN = "yyyy-MM-dd'T'HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceRS priceRS = new PriceRS().builder()
                .startDate("2020-06-14T15.00.00")
                .endDate("2020-06-14T18.30.00")
                .priceList(2)
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(25.45))
                .build();

        final LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14T16.00.00", formatter);

        when(priceService.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceRS);

        final ResponseEntity<PriceRS> result = priceController.findByDateProductBrand(localDateTime, 35455, 1);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(priceRS, result.getBody());

    }
}
