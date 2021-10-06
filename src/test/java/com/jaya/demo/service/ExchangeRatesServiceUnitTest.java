package com.jaya.demo.service;

import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import com.jaya.demo.dto.response.ws.WSExchangeRatesDtoResponse;
import com.jaya.demo.dto.response.ws.WSRatesDtoResponse;
import com.jaya.demo.exception.InvalidCurrency;
import com.jaya.demo.factory.CurrencyFactory;
import com.jaya.demo.model.ExchangeRecord;
import com.jaya.demo.repository.ExchangeRecordRepository;
import com.jaya.demo.service.ws.WSExchangeRates;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ExchangeRatesServiceUnitTest {

    @Mock
    private WSExchangeRates wsExchangeRatesAPI;
    @Mock
    private ExchangeRecordRepository repository;
    private CurrencyFactory currencyFactory;
    private ExchangeRatesService exchangeRatesService;
    private ExchangeRatesDtoRequest exchangeRatesDtoRequest;

    @BeforeEach
    void setup() {
        currencyFactory = new CurrencyFactory();
        exchangeRatesService = new ExchangeRatesService(wsExchangeRatesAPI, currencyFactory, repository);
        exchangeRatesDtoRequest = new EasyRandom().nextObject(ExchangeRatesDtoRequest.class);

    }

    @Test
    @DisplayName("Should exchange currency and save transaction")
    void shouldExchangeAndSaveTransaction() {
        exchangeRatesDtoRequest.setFromCurrency("USD");
        exchangeRatesDtoRequest.setToCurrency("BRL");
        exchangeRatesDtoRequest.setValue(BigDecimal.valueOf(10));
        WSRatesDtoResponse wsRatesDtoResponse = new WSRatesDtoResponse();
        wsRatesDtoResponse.setUSD(0.87);
        wsRatesDtoResponse.setBRL(0.16);
        Long transactionID = new EasyRandom().nextLong();
        when(wsExchangeRatesAPI.findExchangeRates()).thenReturn(WSExchangeRatesDtoResponse.builder().rates(wsRatesDtoResponse).build());
        when(repository.save(any(ExchangeRecord.class))).thenAnswer(invocationOnMock -> {
            ExchangeRecord argument = invocationOnMock.getArgument(0);
            argument.setTransactionID(transactionID);
            return argument;
        });
        ExchangeRecord exchangeRecord = exchangeRatesService.exchangeAndSave(exchangeRatesDtoRequest);
        assertEquals(transactionID, exchangeRecord.getTransactionID());
        assertEquals(exchangeRatesDtoRequest.getUserdID(), exchangeRecord.getUserdID());
        assertEquals(exchangeRatesDtoRequest.getFromCurrency(), exchangeRecord.getFromCurrency());
        assertEquals(exchangeRatesDtoRequest.getToCurrency(), exchangeRecord.getToCurrency());
        assertNotNull(exchangeRecord.getDateTime());
        assertEquals(0.18, exchangeRecord.getExchangeRate().doubleValue());
        assertEquals(1.8, exchangeRecord.getValue().doubleValue());

    }


    @Test
    @DisplayName("Should fail exchange currency when currency is not available")
    void shouldFailExchangeAndSaveTransaction_WhenCurrencyNotAvailable() {
        exchangeRatesDtoRequest.setFromCurrency("USD2");
        assertThrows(InvalidCurrency.class, () -> exchangeRatesService.exchangeAndSave(exchangeRatesDtoRequest));
    }

    @Test
    @DisplayName("Should return list of exchange records")
    void shoulReturnLExchangeRecordList() {
        List<ExchangeRecord> recordList = Collections.singletonList(ExchangeRecord.builder().build());
        when(repository.findByUserdID(anyLong())).thenReturn(recordList);
        assertEquals(recordList, exchangeRatesService.findExchangeRecordsByUser(1L));
    }
}