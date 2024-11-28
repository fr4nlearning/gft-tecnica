package com.example.inix.infrastructure.database.exception;

public class PriceNotFoundException extends Exception {
    public PriceNotFoundException() {
        super("Prices Not Found");
    }
}
