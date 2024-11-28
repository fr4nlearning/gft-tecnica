package com.example.inix.infrastructure.api.rest;

import com.example.inix.application.services.PriceServiceAdapter;
import com.example.inix.domain.model.PriceModel;
import com.example.inix.infrastructure.api.dto.PriceDto;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerImplTest {

    @Mock
    private PriceServiceAdapter priceServiceImpl;

    @InjectMocks
    private PriceControllerImpl priceControllerImpl;

    @Test
    public void testFindProduct() throws PriceNotFoundException {

        final String PATTERN = "yyyy-MM-dd'T'HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceModel priceModel = PriceModel.builder()
                .startDate("2020-06-14T15.00.00")
                .endDate("2020-06-14T18.30.00")
                .priceList(2)
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(25.45))
                .build();

        final LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14T16.00.00", formatter);

        when(priceServiceImpl.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceModel);

        final ResponseEntity<PriceDto> result = priceControllerImpl.findByDateProductBrand(localDateTime, 35455, 1);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}