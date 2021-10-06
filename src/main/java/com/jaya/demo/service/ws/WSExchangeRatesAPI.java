package com.jaya.demo.service.ws;

import com.jaya.demo.dto.response.ws.WSExchangeRatesDtoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WSExchangeRatesAPI {
    private String baseUrl = "http://api.exchangeratesapi.io/latest?base=EUR";
    private String accessKey = "85928cc847c46e21e95c2a2cea3e9725";

    public WSExchangeRatesDtoResponse findExchangeRates(){
       return WebClient.create(baseUrl + "&access_key=" + accessKey)
                .get().retrieve().bodyToMono(WSExchangeRatesDtoResponse.class).block();

    }
}
