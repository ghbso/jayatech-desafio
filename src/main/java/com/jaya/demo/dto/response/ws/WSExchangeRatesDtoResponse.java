package com.jaya.demo.dto.response.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WSExchangeRatesDtoResponse {
    private String date;
    private boolean success;
    private WSRatesDtoResponse rates;
    private int timestamp;
    private String base;
}