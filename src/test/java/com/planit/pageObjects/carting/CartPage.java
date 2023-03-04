package com.planit.pageObjects.carting;

import com.codeborne.selenide.SelenideElement;
import com.planit.pageObjects.BasePage;
import com.planit.testData.ResourceList;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.DecimalFormat;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CartPage extends BasePage {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final static String priceLocator = "//*[text()=' %s']/following-sibling::td[1]";
    private final static String quantityLocator = "//*[text()=' %s']/following-sibling::td[2]/input";
    private final static String subtotalLocator = "//*[text()=' %s']/following-sibling::td[3]";

    SelenideElement shopTab = $(byId("nav-shop"));
    SelenideElement cartIcon = $(byId("nav-cart"));
    SelenideElement totalCost = $(byXpath("//tfoot//*[contains(text(),'Total')]"));

    public CartPage() {

    }

    public void selectShopTab() {
        sleep(5000);
        waitAndClick(shopTab, 3);
    }

    public void navigateToCart() {
        waitAndClick(cartIcon, 3);
        sleep(3000);
    }

    public void verifyPriceAndQuantityAgainstProduct(ResourceList resourceList) {
        for (int i = 0; i < resourceList.productPrices.size(); i++) {
            String productName = resourceList.productNames.get(i);
            SelenideElement priceOnCartPage = $(By.xpath(String.format(priceLocator, productName)));
            SelenideElement quantityOnCartPage = $(By.xpath(String.format(quantityLocator, productName)));
            SelenideElement subTotalOnCartPage = $(By.xpath(String.format(subtotalLocator, productName)));

            Assert.assertEquals(resourceList.productCount.get(i), quantityOnCartPage.getAttribute("value"));
            Assert.assertEquals(resourceList.productPrices.get(productName), priceOnCartPage.getText());

            String actualSubTotal = getSubTotal(priceOnCartPage.getText(), quantityOnCartPage.getAttribute("value"));
            subTotalOnCartPage.shouldHave(text("$" + actualSubTotal));
        }
    }

    private String getSubTotal(String price, String quantity) {
        String temp = price.replace("$", "");
        float tempPrice = Float.parseFloat(temp);
        int tempQuantity = Integer.parseInt(quantity);
        return String.valueOf(df.format(tempPrice * tempQuantity));
    }

    public void verifyTotalCost(ResourceList resourceList) {
        float total = 0;
        for (int i = 0; i < resourceList.productPrices.size(); i++) {
            String productName = resourceList.productNames.get(i);
            SelenideElement subTotalCartPage = $(By.xpath(String.format(subtotalLocator, productName)));
            String temp = subTotalCartPage.getText().replace("$", "");
            float tempSubTotal = Float.parseFloat(temp);
            total = total + tempSubTotal;
        }
        String finalTotal = String.valueOf(total);
        Assert.assertEquals(totalCost.getText(), "Total: " + finalTotal);
    }
}
