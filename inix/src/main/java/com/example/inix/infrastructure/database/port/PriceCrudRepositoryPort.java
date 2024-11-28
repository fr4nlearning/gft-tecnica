package com.example.inix.infrastructure.database.port;

import com.example.inix.infrastructure.database.entity.PriceEntity;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;

import java.time.LocalDateTime;

public interface PriceCrudRepositoryPort {
    PriceEntity findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException;
}
