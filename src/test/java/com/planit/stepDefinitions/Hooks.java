package com.planit.stepDefinitions;

import com.planit.utils.CucumberTestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Collection;


public class Hooks extends StepDefinitionsBase{


    public Hooks(CucumberTestContext context) throws Throwable {
        super(context);
    }

    @Before ( order = 1 )
    public void setUp(Scenario scenario) {
        Collection<String> tagNames = scenario.getSourceTagNames();
        cucumberTestContext.initialize();
        cucumberTestContext.getScenarioContext().setValue("ScenarioName", scenario.getName());
    }

    @After ( order = 10 )
    public void tearDown() {
        if (cucumberTestContext == null)
            return;
        cucumberTestContext.tearDown();
    }

    @After ( value = "@CloseDriver", order = 9730 )
    public void closeDriver() {
        Pages().basePage().closeDriver();
    }

}