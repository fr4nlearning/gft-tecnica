package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/findBy")
    public ResponseEntity<DataRS> findByDateProductBrand(
            @RequestParam String date,
            @RequestParam Integer productId,
            @RequestParam Integer brandId
    ) {
        return ResponseEntity.ok(priceService.findByDateProductBrand(date, productId, brandId));
    }
}
