package com.poc.drools.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

    @NonNull
    private CustomerType type;

    @NonNull
    private int years;

    @NonNull
    private String name;

    private int discount;

    // Standard getters and setters

    public enum CustomerType {
        INDIVIDUAL,
        BUSINESS;
    }
}
