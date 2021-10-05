package com.jaya.demo.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeRatesResource {

    @GetMapping
    public String exchangeCurrency(){
        return "oi";
    }
}
