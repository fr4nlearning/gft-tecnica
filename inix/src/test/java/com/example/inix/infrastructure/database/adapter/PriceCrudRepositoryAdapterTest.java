package com.example.inix.infrastructure.database.adapter;

import com.example.inix.infrastructure.database.entity.BrandEntity;
import com.example.inix.infrastructure.database.entity.PriceEntity;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceCrudRepositoryAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceCrudRepositoryAdapter priceCrudRepository;

    @Test
    void findByDateProductBrand() throws PriceNotFoundException {

        final String PATTERN = "yyyy-MM-dd'T'HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceEntity priceEntity = PriceEntity.builder()
                .startDate(LocalDateTime.parse("2020-06-14T15.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-06-14T18.30.00", formatter))
                .priceList(2)
                .productId(35455)
                .brand(BrandEntity.builder().id(1).name("ZARA").build())
                .price(BigDecimal.valueOf(25.45))
                .build();

        final LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14T16.00.00", formatter);

        when(priceRepository.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceEntity);

        final PriceEntity result = priceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1);

        assertNotNull(result);
        assertEquals(2, result.getPriceList());
    }

    @Test
    void findByDateProductBrand_NotFound() {

        final String PATTERN = "yyyy-MM-dd'T'HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceEntity priceEntity = null;

        final LocalDateTime localDateTime = LocalDateTime.parse("2025-06-14T16.00.00", formatter);

        when(priceRepository.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceEntity);

        assertThrows(PriceNotFoundException.class,
                () -> priceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1));
    }
}