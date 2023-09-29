package com.poc.drools.controller;

import com.poc.drools.service.DroolsService;
import com.poc.drools.domain.Fare;
import com.poc.drools.domain.TaxiRide;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class DroolsController {

    private DroolsService droolsService;

    @GetMapping("/calculate")
    public long calculateRideFare() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(9L);
        Fare rideFare = new Fare();
        return droolsService.calculateFare(taxiRide, rideFare);
    }

}
