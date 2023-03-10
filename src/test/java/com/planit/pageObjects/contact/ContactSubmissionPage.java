package com.planit.pageObjects.contact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.planit.pageObjects.BasePage;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selectors.*;

public class ContactSubmissionPage extends BasePage {

    private final static String errMsgLocator = "//*[@id='%s-err']";

    SelenideElement contactTab = $(byId("nav-contact"));
    SelenideElement submitButton = $(byXpath(" //*[text()='Submit']"));
    SelenideElement foreName = $(byId("forename"));
    SelenideElement email = $(byId("email"));
    SelenideElement message = $(byId("message"));
    SelenideElement successMsg = $(byXpath("//*[@class='ng-binding']"));
    public ContactSubmissionPage() {
    }

    public void clickOnSubmitButton() {
        waitAndClick(submitButton, 3);
    }

    public void selectContactTab() {
        sleep(5000);
        waitAndClick(contactTab, 3);
    }

    public void verifyMandatoryFieldErrorMsg(String fieldName, String errorMsg) {
        SelenideElement actualMsg = $(By.xpath(String.format(errMsgLocator, fieldName.toLowerCase(Locale.ROOT))));
        actualMsg.shouldHave(Condition.text(errorMsg));
    }

    public void fillForename(String name) {
        waitAndSendKeys(foreName, name, 4);
    }

    public void fillEmail(String mail) {
        waitAndSendKeys(email, mail, 4);
    }

    public void fillMsg(String msg) {
        waitAndSendKeys(message, msg, 4);
    }

    public void errorMsgNotVisible(String fieldName) {
        waitAndAssertInvisibility(3, $(By.xpath(String.format(errMsgLocator, fieldName.toLowerCase(Locale.ROOT)))), "Element is visible");
    }

    public void successMsgIsVisible(String part1, String part2) {
        sleep(7000);
        waitAndAssertVisibility(3, successMsg, "Element is Not visible");
        successMsg.shouldHave(Condition.text(part1));
    }
}