package com.example.inix.application.services;

import com.example.inix.application.mapper.PriceModelMapper;
import com.example.inix.domain.model.PriceModel;
import com.example.inix.domain.port.PriceServicePort;
import com.example.inix.infrastructure.database.entity.PriceEntity;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
import com.example.inix.infrastructure.database.port.PriceCrudRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PriceServiceAdapter implements PriceServicePort {

    private final PriceCrudRepositoryPort priceCrudRepositoryPort;

    public PriceModel findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException {

        PriceModelMapper mapper = Mappers.getMapper(PriceModelMapper.class);

        PriceEntity byDateProductBrand = priceCrudRepositoryPort.findByDateProductBrand(date, productId, brandId);

        return mapper.toPriceModel(byDateProductBrand);
    }
}
