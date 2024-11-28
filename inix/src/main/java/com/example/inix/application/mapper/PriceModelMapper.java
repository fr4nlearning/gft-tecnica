package com.example.inix.application.mapper;

import com.example.inix.domain.model.PriceModel;
import com.example.inix.infrastructure.database.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PriceModelMapper {

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd'T'HH.mm.ss")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd'T'HH.mm.ss")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "brand.id", target = "brandId")
    PriceModel toPriceModel(PriceEntity priceEntity);
}
