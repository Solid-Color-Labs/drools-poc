package com.poc.drools.domain;

import lombok.Data;

@Data
public class TaxiRide {

    private Boolean isNightSurcharge;
    private Long distanceInMile;

}