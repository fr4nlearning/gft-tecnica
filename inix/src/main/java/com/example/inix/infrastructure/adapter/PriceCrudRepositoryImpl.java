package com.example.inix.infrastructure.adapter;

import com.example.inix.domain.model.PriceRS;
import com.example.inix.domain.port.IPriceRepository;
import com.example.inix.infrastructure.entity.PriceEntity;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import com.example.inix.infrastructure.mapper.PriceRSMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Objects;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PriceCrudRepositoryImpl implements IPriceRepository {

    private final IPriceCrudRepository iPriceCrudRepository;

    @Override
    public PriceRS findByDateProductBrand(LocalDateTime date, Integer productId, Integer brandId)
            throws PriceNotFoundException {

        PriceRSMapper mapper = Mappers.getMapper(PriceRSMapper.class);

        PriceEntity byDateProductBrand = this.iPriceCrudRepository.findByDateProductBrand(date, productId, brandId);
        if (Objects.isNull(byDateProductBrand)) {
            log.error("Prices with: brandId: {}, productId: {}, date: {}", brandId, productId, date);
            throw new PriceNotFoundException();
        }
        return mapper.toPriceRS(byDateProductBrand);
    }
}
