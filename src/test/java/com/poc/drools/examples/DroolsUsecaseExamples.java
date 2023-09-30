package com.poc.drools.examples;

import com.poc.drools.domain.Fare;
import com.poc.drools.domain.RuleResult;
import com.poc.drools.domain.TaxiRide;
import com.poc.drools.service.DroolsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// See more info here: https://www.baeldung.com/drools
@SpringBootTest
public class DroolsUsecaseExamples {

    @Autowired
    private DroolsService droolsService;

    @Test
    public void shouldSetRideFare() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(9L);
        Fare rideFare = new Fare();
        Long totalFare = droolsService.calculateFare(taxiRide, rideFare);
        assertEquals(70L, totalFare);
    }

    @Test
    public void shouldBreakRule() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceInMile(9L);
        RuleResult result = droolsService.fireDroolsRule(taxiRide);
        assertTrue(result.isRuleBreak());
    }

}
