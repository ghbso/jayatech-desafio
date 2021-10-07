package com.jaya.demo.resources;

import com.jaya.demo.dto.request.ExchangeCurrencyDtoRequest;
import com.jaya.demo.dto.response.ExchangeRatesDtoResponse;
import com.jaya.demo.model.ExchangeRecord;
import com.jaya.demo.service.ExchangeRatesService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class ExchangeRatesResourceUnitTest {

    @Mock
    private ExchangeRatesService exchangeRatesService;
    private ExchangeCurrencyResource exchangeRatesResource;

    @BeforeEach
    void setup() {
        exchangeRatesResource = new ExchangeCurrencyResource(exchangeRatesService);
    }

    @Test
    @DisplayName("Should return exchange record saved")
    void shouldReturnExchangeRecordSaved() {
        ExchangeRatesDtoResponse build = ExchangeRatesDtoResponse.builder().build();
        ExchangeCurrencyDtoRequest exchangeRatesDtoRequest = new EasyRandom().nextObject(ExchangeCurrencyDtoRequest.class);
        when(exchangeRatesService.exchangeAndSave(any(ExchangeCurrencyDtoRequest.class))).thenReturn(build);
        assertEquals(build, exchangeRatesResource.exchangeCurrency(exchangeRatesDtoRequest).getBody());
    }

    @Test
    @DisplayName("Should return list of exchange records")
    void shoulReturnLExchangeRecordList() {
        List<ExchangeRecord> recordList = Collections.singletonList(ExchangeRecord.builder().build());
        when(exchangeRatesService.findExchangeRecordsByUser(anyLong())).thenReturn(recordList);
        assertEquals(recordList, exchangeRatesResource.findExchangeRecordsByUser(1L).getBody());
    }
}