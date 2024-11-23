package com.example.inix.domain.port;

import com.example.inix.domain.model.PriceRS;
import com.example.inix.infrastructure.exception.PriceNotFoundException;

import java.time.LocalDateTime;

public interface IPriceRepository {
    PriceRS findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException;
}
