package com.jaya.demo.service.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaya.demo.dto.response.ws.WSExchangeRatesDtoResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class WSExchangeRatesUnitTest {

    private MockWebServer mockWebServer;
    private WSExchangeRates wsExchangeRates;

    @BeforeEach
    void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        wsExchangeRates = new WSExchangeRates(String.format("http://localhost:%s?base=EUR",  mockWebServer.getPort()), "85928cc847c46e21e95c2a2cea3e9725");
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Should receive WS response")
    void shouldReceiveWSResponse() throws JsonProcessingException {
        WSExchangeRatesDtoResponse wsExchangeRatesDtoResponse = new EasyRandom().nextObject(WSExchangeRatesDtoResponse.class);
        mockWebServer.enqueue(new MockResponse().setBody(new ObjectMapper().writeValueAsString(wsExchangeRatesDtoResponse)).addHeader("Content-Type", "application/json"));
        assertEquals(wsExchangeRatesDtoResponse,wsExchangeRates.findExchangeRates());

    }
}