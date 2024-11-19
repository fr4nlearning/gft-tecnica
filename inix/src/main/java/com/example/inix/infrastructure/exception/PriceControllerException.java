package com.example.inix.infrastructure.exception;

import com.example.inix.infrastructure.dto.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class PriceControllerException {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Error> handlRuntimeException(PriceNotFoundException priceNotFoundException) {
        log.error("PriceNotFoundException ocurred", priceNotFoundException);
        Error error = Error.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .message(priceNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handlRuntimeException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        log.error("MethodArgumentTypeMismatchException ocurred", methodArgumentTypeMismatchException);
        Error error = Error.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .localDateTime(LocalDateTime.now())
                .message(methodArgumentTypeMismatchException.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handlException(Exception exception) {
        log.error("Unhandler Exception ocurred", exception);
        Error error = Error.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .localDateTime(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
