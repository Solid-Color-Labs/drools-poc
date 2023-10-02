package com.poc.drools.domain;

import lombok.Data;

import java.util.List;

@Data
public class RuleResult {

    private boolean isRuleBreak;
    private List<String> namesOfRulesBroken;

}
