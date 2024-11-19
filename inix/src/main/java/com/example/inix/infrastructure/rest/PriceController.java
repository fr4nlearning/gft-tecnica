package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements IPriceController {

    private final PriceService priceService;

    @Override
    public ResponseEntity<DataRS> findByDateProductBrand(
            LocalDateTime date,
            Integer productId,
            Integer brandId) throws PriceNotFoundException {

        var findByDateProductBrand = priceService.findByDateProductBrand(date, productId, brandId);

        return ResponseEntity.ok(findByDateProductBrand);
    }
}
