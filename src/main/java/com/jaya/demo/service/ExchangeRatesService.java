package com.jaya.demo.service;

import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import com.jaya.demo.dto.response.ExchangeRatesDtoResponse;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesService {

    public ExchangeRatesDtoResponse exchange(ExchangeRatesDtoRequest exchangeRatesDtoRequest) {
        return ExchangeRatesDtoResponse.builder().build();
    }
}
