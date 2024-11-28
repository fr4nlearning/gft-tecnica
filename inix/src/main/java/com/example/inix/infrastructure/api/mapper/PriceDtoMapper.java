package com.example.inix.infrastructure.api.mapper;

import com.example.inix.domain.model.PriceModel;
import com.example.inix.infrastructure.api.dto.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PriceDtoMapper {

    PriceDto toPriceDto(PriceModel priceModel);
}
