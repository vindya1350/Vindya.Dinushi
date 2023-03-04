package com.planit.pageObjects;

import com.planit.utils.CucumberTestContext;
import com.planit.utils.TestNGParameter;

import static com.codeborne.selenide.Selenide.open;

public class LoginHomePage extends BasePage {

    public LoginHomePage() {

    }


    public void openPages(CucumberTestContext cucumberTestContext, String page) {
        String absoluteURL = cucumberTestContext.getScenarioContext().getParameter(TestNGParameter.BaseURL).toString();

        String predefinedURL = getPageURL(page);
        absoluteURL += "/" + predefinedURL;

        open(absoluteURL);
    }

    private String getPageURL(String page) {
        switch (page) {
            case "Home":
            case "Login":
                return "";
            case "Admin":
                return "core/admin/index";
            case "CustomShareManage":
                return "customshare/manage";
        }
        return null;
    }

}