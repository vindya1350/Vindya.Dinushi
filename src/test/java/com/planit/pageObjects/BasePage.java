package com.planit.pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class BasePage {

    protected static Logger logger;

    public BasePage() {
        logger = LogManager.getLogger(this);
    }

    protected void waitAndClick(SelenideElement element, long timeout) {
        element.shouldBe(exist, Duration.ofSeconds(timeout)).click();
    }

    protected void waitAndAssertVisibility(long timeout, SelenideElement element, String errorText) {
        element.shouldBe(visible.because(errorText), Duration.ofSeconds(timeout));
    }

    protected void waitAndAssertInvisibility(long timeout, SelenideElement element, String errorMessage) {
        element.shouldNotBe(visible.because(errorMessage), Duration.ofSeconds(timeout));
    }

    protected void waitAndSendKeys(SelenideElement element, String keysToSend, long timeout) {
        element.shouldBe(visible, Duration.ofSeconds(timeout)).setValue(keysToSend);
    }

    public void closeDriver() {
        Selenide.closeWebDriver();
    }

}