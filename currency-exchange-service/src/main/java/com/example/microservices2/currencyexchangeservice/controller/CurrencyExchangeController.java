package com.example.microservices2.currencyexchangeservice.controller;

import com.example.microservices2.currencyexchangeservice.entity.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        int port = Integer.parseInt(environment.getProperty("local.server.port"));
        return new ExchangeValue(1L, from, to, BigDecimal.valueOf(5), port);
    }

}
