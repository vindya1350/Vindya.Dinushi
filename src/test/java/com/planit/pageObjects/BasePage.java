package com.planit.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;

    public void OpenBrowser() {
        System.setProperty("webdriver.chrome.driver", "/Users/danushkaperera/Downloads/Planit_Task/src/test/resources/drivers/mac/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://jupiter.cloud.planittesting.com");
    }

    public void Close_Browser() {
        driver.close();
    }

    protected void waitAndClick(SelenideElement element, long timeOut) {
        element.shouldBe(Condition.exist, Duration.ofSeconds(timeOut)).click();
    }

    protected void waitAndSendKeys(SelenideElement element, String keysToSend, long timeOut) {
        element.shouldBe(Condition.visible, Duration.ofSeconds(timeOut)).setValue(keysToSend);
    }

    protected void waitAndAssertInvisibility(long timeOut, SelenideElement element, String errorMessage){
        element.shouldNotBe(Condition.visible.because(errorMessage),Duration.ofSeconds(timeOut));
    }

    protected void waitAndAssertVisibility(long timeOut, SelenideElement element, String errorMessage){
        element.shouldBe(Condition.visible.because(errorMessage),Duration.ofSeconds(timeOut));
    }
}
