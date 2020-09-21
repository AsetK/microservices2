package com.example.microservices2.limitsservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LimitsConfiguration {
    private final int minimum;
    private final int maximum;
}
