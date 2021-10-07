package com.jaya.demo.service;

import com.jaya.demo.dto.request.ExchangeCurrencyDtoRequest;
import com.jaya.demo.dto.response.ExchangeRatesDtoResponse;
import com.jaya.demo.model.ExchangeRecord;
import com.jaya.demo.dto.response.ws.WSExchangeRatesDtoResponse;
import com.jaya.demo.factory.CurrencyFactory;
import com.jaya.demo.model.currency.Currency;
import com.jaya.demo.repository.ExchangeRecordRepository;
import com.jaya.demo.service.ws.WSExchangeRates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ExchangeRatesService {

    private final WSExchangeRates wsExchangeRatesAPI;
    private final CurrencyFactory currencyFactory;
    private final ExchangeRecordRepository repository;

    @Autowired
    public ExchangeRatesService(WSExchangeRates wsExchangeRatesAPI, CurrencyFactory currencyFactory, ExchangeRecordRepository repository) {
        this.wsExchangeRatesAPI = wsExchangeRatesAPI;
        this.currencyFactory = currencyFactory;
        this.repository = repository;
    }

    public ExchangeRatesDtoResponse exchangeAndSave(ExchangeCurrencyDtoRequest exchangeRatesDtoRequest){
        ExchangeRecord exchangeRecord = repository.save(this.exchange(exchangeRatesDtoRequest));
        log.info("Currency exchanged!");
        return ExchangeRatesDtoResponse.builder()
                .transactionID(exchangeRecord.getTransactionID())
                .userdID(exchangeRecord.getUserdID())
                .fromCurrency(exchangeRecord.getFromCurrency())
                .toCurrency(exchangeRecord.getToCurrency())
                .exchangeRate(exchangeRecord.getExchangeRate())
                .fromValue(exchangeRecord.getValue())
                .toValue(exchangeRecord.getValue().multiply(exchangeRecord.getExchangeRate()).round(new MathContext(2, RoundingMode.CEILING)))
                .dateTime(LocalDateTime.now())
                .build();
    }

    public ExchangeRecord exchange(ExchangeCurrencyDtoRequest exchangeRatesDtoRequest) {
        Currency fromCurrency = currencyFactory.buildCurrency(exchangeRatesDtoRequest.getFromCurrency());
        Currency toCurrency = currencyFactory.buildCurrency(exchangeRatesDtoRequest.getToCurrency());
        WSExchangeRatesDtoResponse exchangeRates = wsExchangeRatesAPI.findExchangeRates();
        BigDecimal exchangeRate = toCurrency.getEuroExchangeRate(exchangeRates.getRates()).divide(fromCurrency.getEuroExchangeRate(exchangeRates.getRates()), 2, RoundingMode.HALF_EVEN);
        return ExchangeRecord.builder()
                .userdID(exchangeRatesDtoRequest.getUserdID())
                .fromCurrency(exchangeRatesDtoRequest.getFromCurrency())
                .toCurrency(exchangeRatesDtoRequest.getToCurrency())
                .exchangeRate(exchangeRate.round(new MathContext(2, RoundingMode.CEILING)))
                .value(exchangeRatesDtoRequest.getValue())
                .dateTime(LocalDateTime.now())
                .build();
    }

    public List<ExchangeRecord> findExchangeRecordsByUser(Long userID){
        return repository.findByUserdID(userID);
    }

}
