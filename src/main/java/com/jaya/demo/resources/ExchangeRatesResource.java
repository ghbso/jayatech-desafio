package com.jaya.demo.resources;

import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import com.jaya.demo.model.ExchangeRecord;
import com.jaya.demo.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeRatesResource {


    private final ExchangeRatesService exchangeRatesService;

    @Autowired
    public ExchangeRatesResource(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @PostMapping
    public ResponseEntity<ExchangeRecord> exchangeCurrency(@Valid @RequestBody ExchangeRatesDtoRequest exchangeRatesDtoRequest){
        return ResponseEntity.ok(exchangeRatesService.exchangeAndSave(exchangeRatesDtoRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ExchangeRecord>> findExchangeRecordsByUser(@RequestParam Long userID){
        return ResponseEntity.ok(exchangeRatesService.findExchangeRecordsByUser(userID));
    }
}
