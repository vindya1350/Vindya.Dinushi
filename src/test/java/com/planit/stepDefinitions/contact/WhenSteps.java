package com.planit.stepDefinitions.contact;

import com.planit.stepDefinitions.StepDefinitionsBase;
import com.planit.testData.ContactTab;
import com.planit.utils.CucumberTestContext;
import cucumber.api.java.en.When;

public class WhenSteps extends StepDefinitionsBase {
    private final ContactTab contactTab;
    public WhenSteps(CucumberTestContext context, ContactTab contactTab) {
        super(context);
        this.contactTab = contactTab;
    }

    @When("I submit contact form without filling any mandatory fields")
    public void iSubmitContactFormWithoutFillingAnyMandatoryFields() {
        Pages().contactSubmissionPage().selectContactTab();
        Pages().contactSubmissionPage().clickOnSubmitButton();
    }

    @When("^I submit after filling all mandatory fields with '(.*)','(.*)' and '(.*)'$")
    public void iSubmitAfterFillingAllMandatoryFiled(String name, String email, String msg) {
        Pages().contactSubmissionPage().fillForename(name);
        Pages().contactSubmissionPage().fillEmail(email);
        Pages().contactSubmissionPage().fillMsg(msg);
        Pages().contactSubmissionPage().clickOnSubmitButton();
    }
}
