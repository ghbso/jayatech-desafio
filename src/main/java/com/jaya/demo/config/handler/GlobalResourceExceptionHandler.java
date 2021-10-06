package com.jaya.demo.config.handler;

import com.jaya.demo.dto.response.ErrorDtoResponse;
import com.jaya.demo.exception.InvalidCurrency;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalResourceExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<List<ErrorDtoResponse>> handleConstraintViolation(MethodArgumentNotValidException ex) {
        List<ErrorDtoResponse> messages = new ArrayList<>();
        for (ObjectError violation : ex.getBindingResult().getFieldErrors()) {
            messages.add(ErrorDtoResponse.builder().message(violation.getDefaultMessage()).build());
        }

        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidCurrency.class })
    public ResponseEntity<ErrorDtoResponse> handleInvalidCurrency(InvalidCurrency ex) {
        return new ResponseEntity<>(ErrorDtoResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorDtoResponse> handleException(Exception ex) {
        return new ResponseEntity<>(ErrorDtoResponse.builder().message("Internal error").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
