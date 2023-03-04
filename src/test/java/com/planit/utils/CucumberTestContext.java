package com.planit.utils;

public class CucumberTestContext {
    private static PageObjectManager pageObjectManager;
    private static ScenarioContext scenarioContext;

    public void initialize() {
        scenarioContext = new ScenarioContext();

        TestNGParameters parameters = new TestNGParameters(true);
        scenarioContext.setTestNGParameters(parameters);

        pageObjectManager = new PageObjectManager();
        setDriver(parameters);
        System.setProperty("selenide.headless", parameters.Headless ? "true" : "false");
        System.setProperty("startMaximised", "true");
        System.setProperty("chromeoptions.args", "--disable-gpu");
    }

    private void setDriver(TestNGParameters parameters) {
        switch (parameters.Browser.toLowerCase()) {
            case "chrome-mac":
                System.out.println("I'm using MAC");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/mac/chromedriver");
                break;
            case "chrome-windows":
                System.out.println("I'm using Windows");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/windows/chromedriver.exe");
                break;
        }
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public void tearDown() {
        if (pageObjectManager != null) {
            pageObjectManager = null;
        }
        if (scenarioContext != null) {
            scenarioContext.clear();
            scenarioContext = null;
        }
    }
}
