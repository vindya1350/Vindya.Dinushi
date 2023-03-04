package com.planit.stepDefinitions.contact;

import com.planit.stepDefinitions.StepDefinitionsBase;
import com.planit.utils.CucumberTestContext;
import io.cucumber.java.en.Given;

public class GivenSteps extends StepDefinitionsBase{
    public GivenSteps(CucumberTestContext context) throws Throwable {
        super(context);
    }

    @Given ( "I login to site" )
    public void iLoginToSite() {
        Pages().loginHomePage().openPages(cucumberTestContext,"Login");
    }
}
