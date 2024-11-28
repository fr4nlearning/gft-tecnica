package com.example.inix.infrastructure.api.exception;

import com.example.inix.infrastructure.database.exception.PriceNotFoundException;
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
    public ResponseEntity<ErrorDto> handlRuntimeException(PriceNotFoundException priceNotFoundException) {
        log.error("PriceNotFoundException ocurred", priceNotFoundException);
        ErrorDto errorDto = ErrorDto.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .message(priceNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handlRuntimeException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        log.error("MethodArgumentTypeMismatchException ocurred", methodArgumentTypeMismatchException);
        ErrorDto errorDto = ErrorDto.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .localDateTime(LocalDateTime.now())
                .message(methodArgumentTypeMismatchException.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handlException(Exception exception) {
        log.error("Unhandler Exception ocurred", exception);
        ErrorDto errorDto = ErrorDto.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .localDateTime(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
