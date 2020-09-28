package com.example.microservices2.currencyconversionservice.controller;

import com.example.microservices2.currencyconversionservice.entity.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Integer port = Integer.valueOf(environment.getProperty("server.port"));

        return new CurrencyConversionBean(1L, from, to,
                BigDecimal.valueOf(75L), quantity, BigDecimal.valueOf(750), port);

    }
}
