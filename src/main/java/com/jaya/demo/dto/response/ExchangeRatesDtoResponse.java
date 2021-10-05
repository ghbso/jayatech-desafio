package com.jaya.demo.dto.response;

import com.jaya.demo.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeRatesDtoResponse {

    private String transactionID;
    private String userdID;
    private Currency fromCurrency;
    private Currency toCurrency;
    private BigDecimal value;
    private BigDecimal exchangeRate;
    private LocalDateTime dateTime;
}
