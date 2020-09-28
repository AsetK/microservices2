package com.example.microservices2.currencyconversionservice.controller;

import com.example.microservices2.currencyconversionservice.entity.CurrencyConversionBean;
import com.example.microservices2.currencyconversionservice.restclient.CurrencyExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeClient currencyExchangeClient;

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Integer port = Integer.valueOf(environment.getProperty("server.port"));

        CurrencyConversionBean body = currencyExchangeClient.retrieveExchangeValue(from, to);

        BigDecimal conversionMultiple = body.getConversionMultiple();

        return new CurrencyConversionBean(1L, from, to,
                conversionMultiple, quantity, quantity.multiply(conversionMultiple), port);

    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Integer port = Integer.valueOf(environment.getProperty("server.port"));

        ResponseEntity<CurrencyConversionBean> response = callCurrencyExchangeService(from, to);

        CurrencyConversionBean body = response.getBody();
        BigDecimal conversionMultiple = body.getConversionMultiple();

        return new CurrencyConversionBean(1L, from, to,
                conversionMultiple, quantity, quantity.multiply(conversionMultiple), port);

    }

    private ResponseEntity<CurrencyConversionBean> callCurrencyExchangeService(String from, String to) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> response =
                new RestTemplate().getForEntity("http://localhost:8000//currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionBean.class, uriVariables);
        return response;
    }
}
