package com.jaya.demo.factory;

import com.jaya.demo.exception.InvalidCurrency;
import com.jaya.demo.model.currency.BRL;
import com.jaya.demo.model.currency.Currency;
import com.jaya.demo.model.currency.USD;
import org.springframework.stereotype.Component;

@Component
public class CurrencyFactory {

    public Currency buildCurrency(String description){
        if (description.equalsIgnoreCase(BRL.class.getSimpleName())){
            return new BRL();
        }

        if (description.equalsIgnoreCase(USD.class.getSimpleName())){
            return new USD();
        }


        throw new InvalidCurrency("Currency '" + description+ "' is not available!");
    }
}
