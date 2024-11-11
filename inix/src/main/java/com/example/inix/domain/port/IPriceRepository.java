package com.example.inix.domain.port;

import com.example.inix.domain.model.DataRS;

public interface IPriceRepository {
    DataRS findByDateProductBrand(String date, Integer productId, Integer brandId);
}
