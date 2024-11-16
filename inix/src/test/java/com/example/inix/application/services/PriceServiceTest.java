package com.example.inix.application.services;

import com.example.inix.domain.model.DataRS;
import com.example.inix.domain.port.IPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private IPriceRepository iPriceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void testfindByDateProductBrand() {

        DataRS dataRS = new DataRS().builder()
                .startDate("2020-06-14-15.00.00")
                .endDate("2020-06-14-18.30.00")
                .priceList(2)
                .productId(35455)
                .brandId(1)
                .price(BigDecimal.valueOf(25.45))
                .build();

        when(iPriceRepository.findByDateProductBrand("2020-06-14 16:00:00", 35455, 1))
                .thenReturn(dataRS);

        DataRS result = priceService.findByDateProductBrand("2020-06-14 16:00:00", 35455, 1);

        assertEquals(dataRS, result);
    }
}