package com.jaya.demo.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ExchangeCurrencyResponseModel")
public class ExchangeRatesDtoResponse {

    private Long transactionID;
    private Long userdID;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal fromValue;
    private BigDecimal toValue;
    private BigDecimal exchangeRate;
    private LocalDateTime dateTime;
}
