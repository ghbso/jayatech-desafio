package com.jaya.demo.config.handler;

import com.jaya.demo.dto.response.ErrorDtoResponse;
import com.jaya.demo.exception.InvalidCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalResourceExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<List<ErrorDtoResponse>> handleConstraintViolation(MethodArgumentNotValidException ex) {
        List<ErrorDtoResponse> messages = new ArrayList<>();
        for (ObjectError violation : ex.getBindingResult().getFieldErrors()) {
            messages.add(ErrorDtoResponse.builder().message(violation.getDefaultMessage()).build());
        }
        log.error(messages.toString());
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidCurrency.class })
    public ResponseEntity<ErrorDtoResponse> handleInvalidCurrency(InvalidCurrency ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ErrorDtoResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<ErrorDtoResponse> handleMissingServletRequestParameterException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ErrorDtoResponse.builder().message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorDtoResponse> handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ErrorDtoResponse.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
