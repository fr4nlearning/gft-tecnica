package com.example.inix.infrastructure.mapper;

import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.entity.PriceEntity;
import com.example.inix.infrastructure.utils.PriceUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = {PriceUtils.class})
public interface DataRSMapper {

    @Mappings(
            {
                    @Mapping(target = "startDate", expression = "java(PriceUtils.fromDateToString(priceEntity.getStartDate()))"),
                    @Mapping(target = "endDate", expression = "java(PriceUtils.fromDateToString(priceEntity.getEndDate()))"),
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "productId", target = "productId"),
                    @Mapping(source = "price", target = "price"),
                    @Mapping(target = "brandId", expression = "java(priceEntity.getBrand().getId())")
            }
    )
    DataRS toDataRS(PriceEntity priceEntity);
}
