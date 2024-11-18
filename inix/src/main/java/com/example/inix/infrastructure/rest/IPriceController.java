package com.example.inix.infrastructure.rest;

import com.example.inix.domain.model.DataRS;
import com.example.inix.infrastructure.dto.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Prices CRUD", description = "API")
@RequestMapping("/prices")
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
                            schema = @Schema(implementation = DataRS.class),
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
                    content = @Content),
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


    @GetMapping("/findBy")
    public ResponseEntity<DataRS> findByDateProductBrand(
            @Parameter(description = "Date", example = "2020-06-14-16.00.00", required = true) @RequestParam String date,
            @Parameter(description = "The Id of product", example = "35455", required = true) @RequestParam Integer productId,
            @Parameter(description = "The Id of brand", example = "1", required = true) @RequestParam Integer brandId
    );
}
