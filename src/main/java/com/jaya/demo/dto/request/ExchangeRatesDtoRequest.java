package com.jaya.demo.dto.request;

import com.jaya.demo.enums.Currency;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ExchangeRatesDtoRequest {

    @NotNull(message = "Enter user ID!")
    private String userdID;
    @NotNull(message = "Enter origin currency!")
    private Currency fromCurrency;
    @NotNull(message = "Enter destiny currency!")
    private Currency toCurrency;
    @NotNull(message = "Enter value to be exchanged!")
    private BigDecimal value;

}
