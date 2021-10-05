package com.jaya.demo.config.handler;

import com.jaya.demo.dto.response.ErrorDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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


}
