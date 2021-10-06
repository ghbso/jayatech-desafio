package com.jaya.demo.resources;

import com.jaya.demo.config.SpringFoxConfig;
import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import com.jaya.demo.model.ExchangeRecord;
import com.jaya.demo.service.ExchangeRatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exchange")
@Api(tags = {SpringFoxConfig.CURRENCY_EXCHANGE_ENDPOINTS})
public class ExchangeRatesResource {


    private final ExchangeRatesService exchangeRatesService;

    @Autowired
    public ExchangeRatesResource(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @PostMapping
    @ApiOperation(value = "Perform the currency exchange and save the transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the currency exchanged record."),
            @ApiResponse(code = 400, message = "Returns invalid request error."),
            @ApiResponse(code = 500, message = "Returns internal error.")
    })
    public ResponseEntity<ExchangeRecord> exchangeCurrency(@Valid @RequestBody ExchangeRatesDtoRequest exchangeRatesDtoRequest){
        return ResponseEntity.ok(exchangeRatesService.exchangeAndSave(exchangeRatesDtoRequest));
    }

    @ApiOperation(value = "List the currency exchange records by user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns the list of currency exchanged records by user."),
            @ApiResponse(code = 400, message = "Returns invalid request error."),
            @ApiResponse(code = 500, message = "Returns internal error.")
    })
    @GetMapping("/list")
    public ResponseEntity<List<ExchangeRecord>> findExchangeRecordsByUser(@RequestParam Long userID){
        return ResponseEntity.ok(exchangeRatesService.findExchangeRecordsByUser(userID));
    }
}
