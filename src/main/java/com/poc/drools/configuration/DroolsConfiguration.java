package com.poc.drools.configuration;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("TAXI_FARE_RULE.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("BOOLEAN_RULE.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("CUSTOMER_RULES.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("LIST_EXAMPLE_RULES.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("Discount.drl.xlsx"));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();

        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

}
