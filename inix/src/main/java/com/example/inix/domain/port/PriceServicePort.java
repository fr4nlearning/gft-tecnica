package com.example.inix.domain.port;

import com.example.inix.domain.model.PriceModel;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;

import java.time.LocalDateTime;

public interface PriceServicePort {
    PriceModel findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException;
}