package com.example.inix.infrastructure.config;

import com.example.inix.infrastructure.adapter.IBrandCrudRepository;
import com.example.inix.infrastructure.adapter.IPriceCrudRepository;
import com.example.inix.infrastructure.entity.BrandEntity;
import com.example.inix.infrastructure.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {

    private final IBrandCrudRepository iBrandCrudRepository;
    private final IPriceCrudRepository iPriceCrudRepository;

    @Override
    public void run(String... args) throws Exception {

        BrandEntity brand = BrandEntity.builder().name("ZARA").build();
        iBrandCrudRepository.save(brand);

        iPriceCrudRepository.saveAll(
                List.of(
                        newProduct(brand, "2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, BigDecimal.valueOf(35.50)),
                        newProduct(brand, "2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, BigDecimal.valueOf(25.45)),
                        newProduct(brand, "2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, BigDecimal.valueOf(30.50)),
                        newProduct(brand, "2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, BigDecimal.valueOf(38.95))
                )
        );
    }

    private PriceEntity newProduct(BrandEntity brandEntity, String startDate, String endDate,
                                   Integer priceList, Integer priority, BigDecimal price) {

        String PATTERN = "yyyy-MM-dd-HH.mm.ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

        return PriceEntity.builder()
                .brand(brandEntity)
                .startDate(LocalDateTime.parse(endDate, formatter))
                .endDate(LocalDateTime.parse(endDate, formatter))
                .priceList(priceList)
                .productId(35455)
                .priority(priority)
                .price(price)
                .curr("EUR")
                .build();
    }
}
