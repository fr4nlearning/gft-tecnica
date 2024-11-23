package com.example.inix.infrastructure.adapter;

import com.example.inix.domain.model.PriceRS;
import com.example.inix.infrastructure.entity.BrandEntity;
import com.example.inix.infrastructure.entity.PriceEntity;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import com.example.inix.infrastructure.mapper.DataRSMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceCrudRepositoryImplTest {

    @Mock
    private IPriceCrudRepository iPriceCrudRepository;

    @Mock
    private DataRSMapper dataRSMapper= Mappers.getMapper(DataRSMapper.class);

    @InjectMocks
    private PriceCrudRepositoryImpl priceCrudRepository;

    @Test
    void findByDateProductBrand() throws PriceNotFoundException {

        final String PATTERN = "yyyy-MM-dd-HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceRS priceRS = new PriceRS().builder()
                .startDate("2020-06-14-15.00.00")
                .endDate("2020-06-14-18.30.00")
                .priceList(2)
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(25.45))
                .build();

        final PriceEntity priceEntity = PriceEntity.builder()
                .startDate(LocalDateTime.parse("2020-06-14-15.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-06-14-18.30.00", formatter))
                .priceList(2)
                .productId(35455)
                .brand(BrandEntity.builder().id(1).name("ZARA").build())
                .price(BigDecimal.valueOf(25.45))
                .build();

        final LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14-16.00.00", formatter);


        when(iPriceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceEntity);

        final PriceRS result = priceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1);

        assertEquals(priceRS, result);
    }

    @Test
    void findByDateProductBrand_NotFound() {

        final String PATTERN = "yyyy-MM-dd-HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        final PriceEntity priceEntity = null;

        final LocalDateTime localDateTime = LocalDateTime.parse("2020-06-14-16.00.00", formatter);


        when(iPriceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceEntity);

        assertThrows(PriceNotFoundException.class,
                () -> priceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1));
    }
}
