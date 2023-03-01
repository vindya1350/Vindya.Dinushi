package com.planit.pageObjects.products;

import com.codeborne.selenide.SelenideElement;
import com.planit.pageObjects.BasePage;
import com.planit.testData.ResourceList;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductsListPage extends BasePage {
    private final static String buyLocator = "//h4[text()='%s']/ancestor::div/p/a[text()='Buy']";
    private final static String priceLocator = "//h4[text()='%s']/ancestor::div/p/span";

    public void addGivenNoOfSameProduct(String product, String num, ResourceList resourceList) {
        int count = Integer.parseInt(num);
        for (int i = 0; i < count; i++) {
            waitAndClick($(By.xpath(String.format(buyLocator, product))), 4);
        }
        SelenideElement productPrice = $(By.xpath(String.format(priceLocator, product)));
        resourceList.productPrices.put(product, productPrice.getText());
    }
}
