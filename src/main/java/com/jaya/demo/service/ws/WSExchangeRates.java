package com.jaya.demo.service.ws;

import com.jaya.demo.dto.response.ws.WSExchangeRatesDtoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WSExchangeRates {
    private final String baseUrl;
    private final String accessKey;


    public WSExchangeRates(@Value("${ws.url}") String baseUrl, @Value("${ws.access-token}") String accessKey) {
        this.baseUrl = baseUrl;
        this.accessKey = accessKey;
    }

    public WSExchangeRatesDtoResponse findExchangeRates(){
        return WebClient.create(baseUrl + "&access_key=" + accessKey)
                .get().retrieve().bodyToMono(WSExchangeRatesDtoResponse.class).block();
    }
}
