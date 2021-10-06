package com.jaya.demo.model.currency;

import com.jaya.demo.dto.response.ws.WSRatesDtoResponse;

import java.math.BigDecimal;

public interface Currency {

    BigDecimal getEuroExchangeRate(WSRatesDtoResponse wsRatesDtoResponse);

}
