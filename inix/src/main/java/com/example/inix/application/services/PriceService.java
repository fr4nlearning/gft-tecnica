package com.example.inix.application.services;

import com.example.inix.domain.model.DataRS;
import com.example.inix.domain.port.IPriceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceService {

    private final IPriceRepository iPriceRepository;

    public DataRS findByDateProductBrand(String date, Integer productId, Integer brandId) {
        return null;
    }
}
