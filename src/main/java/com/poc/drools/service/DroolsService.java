package com.poc.drools.service;

import com.poc.drools.domain.Customer;
import com.poc.drools.domain.Fare;
import com.poc.drools.domain.RuleResult;
import com.poc.drools.domain.TaxiRide;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

    private final KieContainer kieContainer;

    public DroolsService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    /*
     * Since we load all rules (drl files) in the KieFileSystem,
     * in the drl file, we need to set agenda-group above all rules of the same grouping.
     *
     * Otherwise fireAllRules() would execute all drl rules, when we only want to execute a specific
     * set of rules.
     */
    public Long calculateFare(TaxiRide taxiRide, Fare rideFare) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("Taxi Rules").setFocus();
        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rideFare.getRideFare();
    }

    public RuleResult fireDroolsRule(TaxiRide taxiRide) {
        RuleResult result = new RuleResult();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("Pass Fail Rules").setFocus();
        kieSession.setGlobal("ruleResult", result);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();
        return result;
    }

    public int fireCustomerDecisionTable(Customer customer) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup("Discount Rates").setFocus();
        kieSession.insert(customer);
        kieSession.fireAllRules();
        kieSession.dispose();
        return customer.getDiscount();
    }

}
