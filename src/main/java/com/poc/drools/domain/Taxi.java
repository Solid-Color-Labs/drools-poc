package com.poc.drools.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Taxi {

    private List<Customer> customers;

}
