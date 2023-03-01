package com.planit.stepDefinitions.carting;

import com.planit.stepDefinitions.StepDefinitionsBase;
import com.planit.testData.ResourceList;
import com.planit.utils.CucumberTestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

public class WhenSteps extends StepDefinitionsBase {
    private final ResourceList resourceList;
    public WhenSteps(CucumberTestContext context, ResourceList resourceList) {
        super(context);
        this.resourceList = resourceList;
    }

    @When("I am in shop page")
    public void iAmInShopPage() {
        Pages().cartPage().selectShopTab();
    }

    @And("I buy following items")
    public void iBuyFollowingItems(List<Map<String, String>> productList) {
        for (Map<String, String> item : productList) {
            Pages().productsListPage().addGivenNoOfSameProduct(item.get("Product"),item.get("Count"),resourceList);
            resourceList.productNames.add(item.get("Product"));
            resourceList.productCount.add(item.get("Count"));
        }
    }

    @And("I navigate to cart page")
    public void iNavigateToCartPage() {
        Pages().cartPage().navigateToCart();
    }
}
