package com.planit.stepDefinitions.carting;

import com.planit.pageObjects.BasePage;
import com.planit.stepDefinitions.StepDefinitionsBase;
import com.planit.utils.CucumberTestContext;
import cucumber.api.java.en.Given;

public class GivenSteps extends StepDefinitionsBase {

    public GivenSteps(CucumberTestContext context) {
        super(context);
    }
    BasePage basePage = new BasePage();

    @Given("I am in the shopping site")
    public void i_am_in_the_shopping_site() {
       basePage.OpenBrowser();
       //basePage.Close_Browser();
    }
}
