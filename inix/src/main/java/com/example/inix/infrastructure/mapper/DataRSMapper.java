package com.example.inix.infrastructure.mapper;

import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.utils.PriceUtils;
import com.example.inix.infrastructure.entity.PriceEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DataRSMapper {

    @Mappings(
            {
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "productId", target = "productId"),
                    @Mapping(source = "price", target = "price"),
            }
    )
    DataRS toDataRS(PriceEntity priceEntity);

    @AfterMapping
    default void setBrandId(@MappingTarget DataRS dataRS, PriceEntity priceEntity) {
        dataRS.setBrandId(priceEntity.getBrand().getId());
        dataRS.setStartDate(PriceUtils.fromDateToString(priceEntity.getStartDate()));
        dataRS.setEndDate(PriceUtils.fromDateToString(priceEntity.getEndDate()));
    }

}
