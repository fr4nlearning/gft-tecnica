package com.example.inix.infrastructure.adapter;

import com.example.inix.infrastructure.config.InitDatabase;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PriceCrudRepositoryImplTestIntegration {

    @Autowired
    private IPriceCrudRepository iPriceCrudRepository;

    @Autowired
    private IBrandCrudRepository iBrandCrudRepository;

    @BeforeEach
    public void init() throws Exception {

        iPriceCrudRepository.deleteAll();
        iBrandCrudRepository.deleteAll();
        InitDatabase database = new InitDatabase(iBrandCrudRepository, iPriceCrudRepository);
        database.run();
    }

    @Test
    public void findByDateProductBrandIntegration() {

        final LocalDateTime localDateTime = getLocalDateTime("2020-06-14-10.00.00");

        final var result = iPriceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1);
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(35.50), result.getPrice());
    }

    @Test
    public void findByDateProductBrandIntegration_NotFound() {

        final LocalDateTime localDateTime = getLocalDateTime("2025-06-14-16.00.00");

        assertThrows(PriceNotFoundException.class,
                () -> {
                    final var result = iPriceCrudRepository.findByDateProductBrand(localDateTime, 35455, 1);
                    if (Objects.isNull(result)) {
                        throw new PriceNotFoundException();
                    }
                });
    }

    private static LocalDateTime getLocalDateTime(String text) {
        final String PATTERN = "yyyy-MM-dd-HH.mm.ss";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(text, formatter);
    }
}
