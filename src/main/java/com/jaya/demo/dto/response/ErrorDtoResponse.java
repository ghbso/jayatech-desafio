package com.jaya.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDtoResponse {

    private String message;
}
