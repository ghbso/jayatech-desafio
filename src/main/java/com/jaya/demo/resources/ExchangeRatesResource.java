package com.jaya.demo.resources;

import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import com.jaya.demo.model.ExchangeRecord;
import com.jaya.demo.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeRatesResource {

    private final ExchangeRatesService exchangeRatesService;

    @Autowired
    public ExchangeRatesResource(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @PostMapping
    public ResponseEntity<ExchangeRecord> exchangeCurrency(@Validated @RequestBody ExchangeRatesDtoRequest exchangeRatesDtoRequest){
        return ResponseEntity.ok(exchangeRatesService.exchangeAndSave(exchangeRatesDtoRequest));
    }
}
