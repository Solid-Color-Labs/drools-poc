package com.poc.drools.listener;

import lombok.Getter;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener which keeps track of rules broken.
 *
 * @author Oliver Klesing
 */
@Getter
public class RuleBreakEventListener extends DefaultAgendaEventListener  {

    private final List<String> namesOfRulesBroken = new ArrayList<>();

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        Rule rule = event.getMatch().getRule();
        String ruleName = rule.getName();
        namesOfRulesBroken.add(ruleName);
    }

}
