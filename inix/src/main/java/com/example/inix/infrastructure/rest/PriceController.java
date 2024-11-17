package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.utils.PriceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class PriceController implements IPriceController {

    private final PriceService priceService;

    @Override
    public ResponseEntity<DataRS> findByDateProductBrand(String date, Integer productId, Integer brandId) {

        var findByDateProductBrand = priceService.findByDateProductBrand(getDate(date), productId, brandId);

        if (Objects.isNull(findByDateProductBrand)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(findByDateProductBrand);
        }
    }

    private static LocalDateTime getDate(String date) {
        try {
            return PriceUtils.fromStringToDate(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format: " + date, e);
        }
    }
}
