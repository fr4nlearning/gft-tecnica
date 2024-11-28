package com.example.inix.integration.database;

import com.example.inix.infrastructure.database.adapter.BrandRepository;
import com.example.inix.infrastructure.database.adapter.PriceCrudRepositoryAdapter;
import com.example.inix.infrastructure.database.adapter.PriceRepository;
import com.example.inix.infrastructure.database.config.InitDatabase;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PriceCrudRepositoryAdapterITTest {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private BrandRepository brandRepository;

    private PriceCrudRepositoryAdapter priceCrudRepositoryImpl;

    @BeforeEach
    public void init() throws Exception {

        priceRepository.deleteAll();
        brandRepository.deleteAll();
        InitDatabase database = new InitDatabase(brandRepository, priceRepository);
        priceCrudRepositoryImpl = new PriceCrudRepositoryAdapter(priceRepository);
        database.run();
    }

    @Test
    public void findByDateProductBrandIntegration() throws PriceNotFoundException {

        final LocalDateTime localDateTime = getLocalDateTime("2020-06-14T10.00.00");

        final var result = priceCrudRepositoryImpl.findByDateProductBrand(localDateTime, 35455, 1);
        assertNotNull(result);
        assertEquals(1, result.getPriceList());

    }

    @Test
    public void findByDateProductBrandIntegration_NotFound() {

        final LocalDateTime localDateTime = getLocalDateTime("2025-06-14T16.00.00");

        assertThrows(PriceNotFoundException.class,
                () -> priceCrudRepositoryImpl.findByDateProductBrand(localDateTime, 35455, 1));
    }

    private static LocalDateTime getLocalDateTime(String text) {
        final String PATTERN = "yyyy-MM-dd'T'HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(text, formatter);
    }
}
