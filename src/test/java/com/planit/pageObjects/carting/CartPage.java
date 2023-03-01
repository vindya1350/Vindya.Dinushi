package com.planit.pageObjects.carting;

import com.codeborne.selenide.SelenideElement;
import com.planit.pageObjects.BasePage;
import com.planit.testData.ResourceList;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CartPage extends BasePage {
    private final static String priceLocator = "//*[text()=' %s']/following-sibling::td[1]";
    private final static String quantityLocator = "//*[text()=' %s']/following-sibling::td[2]/input";
    private final static String subtotalLocator = "//*[text()=' %s']/following-sibling::td[3]";

    SelenideElement shopTab = $(By.id("nav-shop"));
    SelenideElement cartIcon = $(By.id("nav-cart"));
    SelenideElement totalCost = $(By.xpath("//tfoot//*[contains(text(),'Total')]"));

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
            SelenideElement priceOnCartPage = $(By.xpath(String.format(priceLocator, resourceList.productPrices.get(productName))));
            SelenideElement quantityOnCartPage = $(By.xpath(String.format(quantityLocator, resourceList.productPrices.get(productName))));

            Assert.assertEquals(priceOnCartPage.getText(), resourceList.productPrices.get(i));
            Assert.assertEquals(quantityOnCartPage.getAttribute("value"), resourceList.productCount.get(i));

            String actualSubTotal = getSubTotal(priceOnCartPage.getText(), quantityOnCartPage.getAttribute("value"));
            SelenideElement subTotalCartPage = $(By.xpath(String.format(subtotalLocator, resourceList.productPrices.get(productName))));
            Assert.assertEquals(subTotalCartPage.getText(), "$" + actualSubTotal);
        }
    }

    private String getSubTotal(String price, String quantity) {
        int tempPrice = Integer.parseInt(Arrays.toString(price.split("$")));
        int tempQuantity = Integer.parseInt(quantity);
        return String.valueOf(tempPrice * tempQuantity);
    }

    public void verifyTotalCost(ResourceList resourceList) {
        int total = 0;
        for (int i = 0; i < resourceList.productPrices.size(); i++) {
            String productName = resourceList.productNames.get(i);
            SelenideElement subTotalCartPage = $(By.xpath(String.format(subtotalLocator, resourceList.productPrices.get(productName))));
            int tempSubTotal = Integer.parseInt(Arrays.toString(subTotalCartPage.getText().split("$")));
            total = total + tempSubTotal;
        }
        String finalTotal = String.valueOf(total);
        Assert.assertEquals(totalCost.getText(), "$" + finalTotal);
    }
}
