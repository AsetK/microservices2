package com.example.microservices2.currencyconversionservice.restclient;

import com.example.microservices2.currencyconversionservice.entity.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeClient {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    //Also we can get ResponseEntity<CurrencyConversionBean>
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}

