package com.example.inix.infrastructure.rest;

import com.example.inix.application.services.PriceService;
import com.example.inix.domain.model.DataRS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Tag(name = "Prices CRUD", description = "API")
public class PriceController {

    private final PriceService priceService;

    @Operation(
            summary = "Find price by date, product ID, and brand ID",
            description = "Get data from database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DataRS.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content),
    })

    @GetMapping("/findBy")
    public ResponseEntity<DataRS> findByDateProductBrand(
            @Parameter(description = "Date", required = true) @RequestParam String date,
            @Parameter(description = "The Id of product", required = true) @RequestParam Integer productId,
            @Parameter(description = "The Id of brand", required = true) @RequestParam Integer brandId
    ) {
        return ResponseEntity.ok(priceService.findByDateProductBrand(date, productId, brandId));
    }
}
