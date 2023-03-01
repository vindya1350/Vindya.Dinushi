package com.planit.pageObjects.contact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.planit.pageObjects.BasePage;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ContactSubmissionPage extends BasePage {

    private final static String errMsgLocator = "//*[@id='%s-err]";

    SelenideElement contactTab = $(By.id("nav-contact"));
    SelenideElement submitButton = $(By.xpath(" //*[text()='Submit']"));
    SelenideElement foreName = $(By.id("forename"));
    SelenideElement email = $(By.id("email"));
    SelenideElement message = $(By.id("nav-contact"));
    SelenideElement successMsgP1 = $(By.xpath("//*[@class='ng-binding']"));
    SelenideElement successMsgP2 = $(By.xpath("//*[@class='alert alert-success']"));



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
        waitAndAssertVisibility(3, successMsgP1, "Element is Not visible");
        successMsgP1.shouldHave(Condition.text(part1));
        waitAndAssertVisibility(3, successMsgP2, "Element is Not visible");
        successMsgP2.shouldHave(Condition.text(part1));
    }
}
