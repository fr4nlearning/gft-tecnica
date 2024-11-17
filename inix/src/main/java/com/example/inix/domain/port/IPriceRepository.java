package com.example.inix.domain.port;

import com.example.inix.domain.model.DataRS;

import java.time.LocalDateTime;

public interface IPriceRepository {
    DataRS findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId);
}
