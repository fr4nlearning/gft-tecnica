package com.example.inix.infrastructure.rest;

import com.example.inix.domain.model.PriceRS;
import com.example.inix.infrastructure.dto.Error;
import com.example.inix.infrastructure.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Tag(name = "Prices CRUD", description = "API")
@RequestMapping("/api/v1/prices")
public interface IPriceController {

    @Operation(
            operationId = "findByDateProductBrand",
            summary = "Find price by date, product ID, and brand ID",
            description = "Get data from database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PriceRS.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "startDate": "2020-06-14-00.00.00",
                                                    "endDate": "2020-12-31-23.59.59",
                                                    "priceList": 1,
                                                    "productId": 35455,
                                                    "brandId": 1,
                                                    "price": 35.50
                                                }
                                            """
                            )
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "httpStatus": "BAD_REQUEST",
                                                    "localDateTime": "18/11/2024 01:09:47",
                                                    "message": "Invalid format: "
                                                }
                                            """
                            )
                    )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "httpStatus": "NOT_FOUND",
                                                    "localDateTime": "18/11/2024 01:09:47",
                                                    "message": "Prices Not Found"
                                                }
                                            """
                            )
                    )),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "httpStatus": "INTERNAL_SERVER_ERROR",
                                                    "localDateTime": "18/11/2024 01:09:47",
                                                    "message": ""
                                                }
                                            """
                            )
                    ))
    })
    @GetMapping("/search")
    public ResponseEntity<PriceRS> findByDateProductBrand(
            @Parameter(
                    description = "Date",
                    example = "2020-06-14T16.00.00",
                    schema = @Schema(
                            type = "string",
                            pattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])T([01]\\d|2[0-3])\\.([0-5]\\d)\\.([0-5]\\d)$"
                    ),
                    required = true)
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH.mm.ss") LocalDateTime date,
            @Parameter(
                    description = "The Id of product",
                    example = "35455",
                    required = true)
            @RequestParam Integer productId,
            @Parameter(
                    description = "The Id of brand",
                    example = "1",
                    required = true)
            @RequestParam Integer brandId
    ) throws PriceNotFoundException;
}
