package com.jaya.demo.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@ApiModel("ExchangeCurrencyRequestModel")
public class ExchangeCurrencyDtoRequest {

    @NotNull(message = "Enter user ID!")
    private Long userdID;
    @NotNull(message = "Enter origin currency!")
    private String fromCurrency;
    @NotNull(message = "Enter destiny currency!")
    private String toCurrency;
    @NotNull(message = "Enter value to be exchanged!")
    private BigDecimal value;

}
