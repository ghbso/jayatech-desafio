package com.jaya.demo.model.currency;

import com.jaya.demo.dto.response.ws.WSRatesDtoResponse;

import java.math.BigDecimal;

public class BRL implements Currency{

    @Override
    public BigDecimal getEuroExchangeRate(WSRatesDtoResponse wsRatesDtoResponse) {
        return BigDecimal.valueOf(wsRatesDtoResponse.getBRL());
    }
}
