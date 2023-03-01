package com.planit.stepDefinitions.carting;

import com.planit.stepDefinitions.StepDefinitionsBase;
import com.planit.testData.ResourceList;
import com.planit.utils.CucumberTestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ThenSteps extends StepDefinitionsBase {
    private final ResourceList resourceList;

    public ThenSteps(CucumberTestContext context, ResourceList resourceList) {
        super(context);
        this.resourceList = resourceList;
    }

    @Then("I can see correct quantity, product and subtotal are correct")
    public void iCanSeeCorrectQuantityAndProductPriceAreCorrect() {
        Pages().cartPage().verifyPriceAndQuantityAgainstProduct(resourceList);
    }

    @And("I can see Total cost is correct")
    public void iCanSeeTotalCostIsCorrect() {
        Pages().cartPage().verifyTotalCost(resourceList);
    }
}
