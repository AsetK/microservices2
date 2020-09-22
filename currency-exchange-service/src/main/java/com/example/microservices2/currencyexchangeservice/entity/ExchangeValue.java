package com.example.microservices2.currencyexchangeservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ExchangeValue {
    private final Long id;
    private final String from;
    private final String to;
    private final BigDecimal conversionMultiple;
    private final int port;
}
