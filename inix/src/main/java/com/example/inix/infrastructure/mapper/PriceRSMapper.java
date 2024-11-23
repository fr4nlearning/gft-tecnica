package com.example.inix.infrastructure.mapper;

import com.example.inix.domain.model.PriceRS;
import com.example.inix.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PriceRSMapper {

    @Mappings(
            {
                    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd-HH.mm.ss"),
                    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd-HH.mm.ss"),
                    @Mapping(source = "priceList", target = "priceList"),
                    @Mapping(source = "productId", target = "productId"),
                    @Mapping(source = "price", target = "price"),
                    @Mapping(source = "brand.id", target = "brandId")
            }
    )
    PriceRS toPriceRS(PriceEntity priceEntity);
}
