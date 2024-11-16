package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements IPriceController {

    private final PriceService priceService;

    @Override
    public ResponseEntity<DataRS> findByDateProductBrand(String date, Integer productId, Integer brandId) {
        return ResponseEntity.ok(priceService.findByDateProductBrand(date, productId, brandId));
    }
}
