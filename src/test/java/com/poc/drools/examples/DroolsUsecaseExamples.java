package com.poc.drools.examples;

import com.poc.drools.domain.*;
import com.poc.drools.service.DroolsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    // See https://www.baeldung.com/drools-excel
    @Test
    public void shouldRunDecisionTable() {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 5, "John");
        int discount = droolsService.fireCustomerDecisionTable(customer);
        assertEquals(15, discount);
    }

    @Test
    public void shouldBreakRuleOnInvalidName() {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 10, "Jane");
        RuleResult result = droolsService.checkCustomerName(customer);
        assertTrue(result.isRuleBreak());
    }

    @Test
    public void shouldBreakRuleIfContainsValidBusiness() {
        Taxi taxi = new Taxi(List.of(new Customer(Customer.CustomerType.BUSINESS, 1, "Microsoft")));
        RuleResult result = droolsService.checkBusinessIsValidForTaxiRide(taxi);
        assertTrue(result.isRuleBreak());
    }

    @Test
    public void shouldBreakRuleIfBadName() {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 5, "Mike");
        RuleResult result = droolsService.runHaltExampleToExitOnFirstRuleBreak(customer);
        assertTrue(result.isRuleBreak());
    }

    @Test
    public void shouldNotBreakRuleIfGoodName() {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 5, "Karen");
        RuleResult result = droolsService.runHaltExampleToExitOnFirstRuleBreak(customer);
        assertFalse(result.isRuleBreak());
    }

}
