package com.example.microservices2.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    Configuration configuration;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsFromConfig() {
        return new LimitsConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}
