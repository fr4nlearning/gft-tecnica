package com.example.inix.infrastructure.api.rest;

import com.example.inix.domain.port.PriceServicePort;
import com.example.inix.infrastructure.api.dto.PriceDto;
import com.example.inix.infrastructure.api.mapper.PriceDtoMapper;
import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceControllerImpl implements PriceController {

    private final PriceServicePort priceService;

    @Override
    public ResponseEntity<PriceDto> findByDateProductBrand(
            LocalDateTime date,
            Integer productId,
            Integer brandId) throws PriceNotFoundException {

        var findByDateProductBrand = priceService.findByDateProductBrand(date, productId, brandId);

        PriceDtoMapper mapper = Mappers.getMapper(PriceDtoMapper.class);

        PriceDto priceDto = mapper.toPriceDto(findByDateProductBrand);

        return ResponseEntity.ok(priceDto);
    }
}
