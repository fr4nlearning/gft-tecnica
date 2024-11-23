package com.example.inix.application.services;

import com.example.inix.domain.model.PriceRS;
import com.example.inix.domain.port.IPriceRepository;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PriceService {

    private final IPriceRepository iPriceRepository;

    public PriceRS findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException {
        return iPriceRepository.findByDateProductBrand(date, productId, brandId);
    }
}
