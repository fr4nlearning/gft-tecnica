package com.example.inix.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DataRS(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer brandId,
        BigDecimal price
) {
}