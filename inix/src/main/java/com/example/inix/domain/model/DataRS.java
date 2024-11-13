package com.example.inix.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DataRS {
    private String startDate;
    private String endDate;
    private Integer priceList;
    private Integer productId;
    private Integer brandId;
    private BigDecimal price;
}