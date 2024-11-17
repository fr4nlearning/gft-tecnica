package com.example.inix.infrastructure.adapter;

import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.entity.BrandEntity;
import com.example.inix.infrastructure.entity.PriceEntity;
import com.example.inix.infrastructure.mapper.DataRSMapper;
import com.example.inix.infrastructure.utils.PriceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceCrudRepositoryImplTest {

    @Mock
    private IPriceCrudRepository iPriceCrudRepository;

    @Mock
    private DataRSMapper dataRSMapper;

    @InjectMocks
    private PriceCrudRepositoryImpl priceCrudRepository;

    @Test
    void findByDateProductBrand() {

        DataRS dataRS = new DataRS().builder()
                .startDate("2020-06-14-15.00.00")
                .endDate("2020-06-14-18.30.00")
                .priceList(2)
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(25.45))
                .build();

        PriceEntity priceEntity = PriceEntity.builder()
                .startDate(PriceUtils.fromStringToDate("2020-06-14-15.00.00"))
                .endDate(PriceUtils.fromStringToDate("2020-06-14-18.30.00"))
                .priceList(2)
                .productId(35455)
                .brand(BrandEntity.builder().id(1).name("ZARA").build())
                .price(BigDecimal.valueOf(25.45))
                .build();

        LocalDateTime localDateTime= PriceUtils.fromStringToDate("2020-06-14-16.00.00");


        when(iPriceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1))
                .thenReturn(priceEntity);
        when(dataRSMapper.toDataRS(priceEntity)).thenReturn(dataRS);

        DataRS result = priceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1);

        assertEquals(dataRS, result);
    }
}