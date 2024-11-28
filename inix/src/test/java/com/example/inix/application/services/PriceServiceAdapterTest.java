package com.example.inix.application.services;

import com.example.inix.domain.model.PriceModel;
import com.example.inix.infrastructure.database.entity.BrandEntity;
import com.example.inix.infrastructure.database.entity.PriceEntity;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
import com.example.inix.infrastructure.database.port.PriceCrudRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceAdapterTest {

    @Mock
    private PriceCrudRepositoryPort priceCrudRepositoryPort;

    @InjectMocks
    private PriceServiceAdapter priceServiceImpl;

    @Test
    void testfindByDateProductBrand() throws PriceNotFoundException {

        final String PATTERN = "yyyy-MM-dd'T'HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceEntity priceEntity = PriceEntity.builder()
                .brand(BrandEntity.builder().id(1).name("ZARA").build())
                .startDate(LocalDateTime.parse("2020-06-14T15.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-06-14T18.30.00", formatter))
                .priceList(2)
                .productId(35455)
                .price(BigDecimal.valueOf(25.45))
                .build();

        final LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14T16.00.00", formatter);

        when(priceCrudRepositoryPort.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceEntity);

        final PriceModel result = priceServiceImpl.findByDateProductBrand(localDateTime, 35455, 1);

        assertNotNull(result);
        assertEquals(35455, result.getProductId());
    }
}
