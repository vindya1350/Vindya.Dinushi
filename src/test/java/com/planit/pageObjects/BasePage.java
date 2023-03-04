package com.planit.pageObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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

    protected void waitAndAssertVisibility(long timeout, By by, String errorText) {
        $(by).shouldBe(visible.because(errorText), Duration.ofSeconds(timeout));
    }

    protected void waitAndAssertInvisibility(long timeout, SelenideElement element, String errorMessage) {
        element.shouldNotBe(visible.because(errorMessage), Duration.ofSeconds(timeout));
    }

    protected void waitAndAssertInvisibility(long timeout, By by, String errorMessage) {
        $(by).shouldNotBe(visible.because(errorMessage), Duration.ofSeconds(timeout));
    }

    protected void waitAndAssertVisibilityOfAllElements(long timeout, List<SelenideElement> allElements, String errorText) {
        for (SelenideElement element : allElements) {
            element.shouldBe(visible.because(errorText), Duration.ofSeconds(timeout));
        }
    }

    protected void waitAndSendKeys(SelenideElement element, String keysToSend, long timeout) {
        element.shouldBe(visible, Duration.ofSeconds(timeout)).setValue(keysToSend);
    }

    protected void waitAndSendKeys(By by, String keysToSend, long timeout) {
        $(by).shouldBe(visible, Duration.ofSeconds(timeout)).setValue(keysToSend);
    }

    protected void waitAndClear(SelenideElement element, int timeout) {
        waitAndAssertVisibility(timeout, element, "");
        element.clear();
    }

    protected boolean waitAndIsElementPresent(SelenideElement element, long timeout) {
        long start_time = System.currentTimeMillis();
        long wait_time = timeout * 1000;
        long end_time = start_time + wait_time;
        while (System.currentTimeMillis() < end_time) {
            if (element.exists()) {
                return true;
            }
        }
        return false;
    }

    protected boolean waitAndIsElementPresent(By by, long timeout) {
        return waitAndIsElementPresent($(by), timeout);
    }

    protected boolean waitAndIsElementNotPresent(SelenideElement element, long timeout) {
        long start_time = System.currentTimeMillis();
        long wait_time = timeout * 1000;
        long end_time = start_time + wait_time;
        while (System.currentTimeMillis() < end_time) {
            if (!element.exists()) {
                return true;
            }
        }
        return false;
    }

    protected boolean waitAndIsElementNotPresent(By by, long timeout) {
        return waitAndIsElementNotPresent($(by), timeout);
    }

    //Click on checkbox if it's not already checked
    public void clickOnCheckboxIfNotChecked(SelenideElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void goBack() {
        getWebDriver().navigate().back();
    }

    private void scrollUpOrDown(int interval, int times) {
        JavascriptExecutor js = ((JavascriptExecutor) getWebDriver());
        js.executeScript("arrayElement = document.getElementsByClassName(\"ReactVirtualized__List\");");
        js.executeScript("scrollElement = arrayElement[arrayElement.length -1];");
        js.executeScript("scrollElement.scrollTo(0 ," + interval * times + ");");
        System.out.println("scrolled: " + times);
    }

    public void scrollUpOrDownToElement(SelenideElement element, String upOrDown) {
        int interval = upOrDown.equalsIgnoreCase("up") ? -400 : 400;
        for (int i = 1; i < 6; i++) {
            if (waitAndIsElementPresent(element, 3)) {
                element.scrollIntoView("{block: \"center\"}");
                break;
            } else {
                scrollUpOrDown(interval, i);
            }
        }
    }

    public void scrollDownToElement(SelenideElement element) {
        scrollUpOrDownToElement(element, "down");
    }

    public void scrollUpToElement(SelenideElement element) {
        scrollUpOrDownToElement(element, "up");
    }

    public void selectFromDropdownList(String itemToSelect, int sleepTime) {
        sleep(sleepTime);
        Actions builder = new Actions(getWebDriver());
        builder.sendKeys(itemToSelect);
        builder.perform();
        sleep(sleepTime);
        builder.sendKeys(Keys.ENTER);
        builder.perform();
        sleep(sleepTime);
    }

    public void selectFromDropdownList(String itemToSelect) {
        selectFromDropdownList(itemToSelect, 1000);
    }

    public void waitAndClickWithActionDriver(SelenideElement element, long timeout) {
        waitAndIsElementPresent(element, timeout);
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(element).click().build().perform();
    }

    //Click on checkbox if its already checked
    public void unClickOnCheckboxIfChecked(SelenideElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void clickAndRetry(SelenideElement elementToWait, SelenideElement elementToClick, long interval) {
        for (int i = 0; i < 5; i++) {
            elementToClick.shouldBe(visible).click();
            if (waitAndIsElementPresent(elementToWait, interval)) {
                break;
            }
        }
    }

    /**
     * Clear the text box when clear is not working (e.g. Markup comments)
     */
    protected void waitAndDeleteByKeyboard(SelenideElement element, int timeout) {
        waitAndAssertVisibility(timeout, element, "");
        while (!element.getText().isEmpty()) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    /**
     * This method checks if a file is downloaded.
     */
    public void checkFileDownload(String downloadedFileName) {
        sleep(3000);
        List<File> fileList = WebDriverRunner.getBrowserDownloadsFolder().files();
        boolean check = false;
        for (File file : fileList) {
            if (file.getName().contains(downloadedFileName)) {
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
    }

    public void closeDriver() {
        Selenide.closeWebDriver();
    }

    public void handleNewWindowAndSwitchBackToOriginal(String newWindowTitle, String originalWindowHandle) {
        switchTo().window(newWindowTitle, Duration.ofSeconds(10));
        Selenide.closeWindow();
        switchTo().window(originalWindowHandle);
    }

    public void enableOrDisableOnElement(boolean enable, SelenideElement element) {
        if (enable) {
            clickOnCheckboxIfNotChecked(element);
        } else {
            unClickOnCheckboxIfChecked(element);
        }
    }

    public void replaceUrlAndOpen(String url) {
        getWebDriver().navigate().to(url);
    }

    public void switchToWindow(int index) {
        switchTo().window(index);
        getWebDriver().manage().window().setSize(new Dimension(1600, 900));
    }


}