package com.example.inix.infrastructure.mapper;

import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DataRSMapper {

    @Mappings(
            {
                    @Mapping(source = "startDate", target = "startDate"),
                    @Mapping(source = "endDate", target = "endDate"),
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "productId", target = "productId"),
                    //@Mapping(source = "brand.id", target = "brandId"),
                    @Mapping(source = "price", target = "price"),
            }
    )
    DataRS toDataRS(PriceEntity priceEntity);

}
