package com.example.inix.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DataRS(
        Integer productId,
        Integer brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price
) {
}
