package com.example.inix.infrastructure.database.adapter;

import com.example.inix.infrastructure.database.entity.PriceEntity;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
import com.example.inix.infrastructure.database.port.PriceCrudRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Objects;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PriceCrudRepositoryAdapter implements PriceCrudRepositoryPort {

    private final PriceRepository priceRepository;

    @Override
    public PriceEntity findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException {

        PriceEntity byDateProductBrand = this.priceRepository.findByDateProductBrand(date, productId, brandId);
        if (Objects.isNull(byDateProductBrand)) {
            log.error("Prices with: brandId: {}, productId: {}, date: {}", brandId, productId, date);
            throw new PriceNotFoundException();
        }
        return byDateProductBrand;
    }
}
