package com.planit.stepDefinitions.contact;

import com.planit.stepDefinitions.StepDefinitionsBase;
import com.planit.testData.ContactTab;
import com.planit.utils.CucumberTestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThenSteps extends StepDefinitionsBase {
    private final ContactTab contactTab;

    public ThenSteps(CucumberTestContext context, ContactTab contactTab) {
        super(context);
        this.contactTab = contactTab;
    }

    @Then("^I can see following error messages are populated for Mandatory fields")
    public void iCanSeeFollowingErrorMessagesArePopulatedForMandatoryFields(List<Map<String, String>> errorMsgList) {
        contactTab.mandoryErrList = new ArrayList<>();
        contactTab.fieldNameList = new ArrayList<>();
        for (Map<String, String> msg : errorMsgList) {
            Pages().contactSubmissionPage().verifyMandatoryFieldErrorMsg(msg.get("FieldName"), msg.get("ErrorMsg"));
            contactTab.mandoryErrList.add(msg.get("ErrorMsg"));
            contactTab.fieldNameList.add(msg.get("FieldName"));
        }
    }

    @Then("I can not see error messages")
    public void iCanNotSeeErrorMessages() {
        for (int i = 0; i < contactTab.fieldNameList.size(); i++) {
            Pages().contactSubmissionPage().errorMsgNotVisible(contactTab.fieldNameList.get(i));
        }
    }

    @Then("^I can see success messages as '(.*)'$")
    public void iCanSeeSuccessMessages(String successMsg) {
        String[] msgSplits = successMsg.split(",", 2);
        Pages().contactSubmissionPage().successMsgIsVisible(msgSplits[0], msgSplits[1]);
    }

    @When("I am in contact page")
    public void iAmInContactPage() {
        Pages().contactSubmissionPage().selectContactTab();
    }
}
