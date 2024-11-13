package com.example.inix.infrastructure.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceUtils {

    private static final String PATTERN = "yyyy-MM-dd-HH.mm.ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDateTime fromStringToDate(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }

    public static String fromDateToString(LocalDateTime dateLocalDate) {
        return dateLocalDate.format(formatter);
    }
}
