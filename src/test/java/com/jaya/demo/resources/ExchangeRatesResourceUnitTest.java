package com.jaya.demo.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import com.jaya.demo.service.ExchangeRatesService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
class ExchangeRatesResourceUnitTest {

    @Mock
    private ExchangeRatesService exchangeRatesService;
    private ExchangeRatesResource exchangeRatesResource;
    private ExchangeRatesDtoRequest dto;

    @BeforeEach
    void setup() {
        exchangeRatesResource = new ExchangeRatesResource(exchangeRatesService);
        dto = new EasyRandom().nextObject(ExchangeRatesDtoRequest.class);
    }

    @Test
    @DisplayName("Should return valid response")
    void shouldReturnValidResponse() {
        Assertions.assertNotNull(exchangeRatesResource.exchangeCurrency(dto));

    }
}