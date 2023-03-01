package com.planit.stepDefinitions;

import com.planit.utils.CucumberTestContext;
import com.planit.utils.PageObjectManager;

public class StepDefinitionsBase {

    protected final CucumberTestContext cucumberTestContext;

    public StepDefinitionsBase(CucumberTestContext context) {
        cucumberTestContext = context;
    }

    protected PageObjectManager Pages() {
        return cucumberTestContext.getPageObjectManager();
    }

//    public CucumberTestContext getCucumberTestContext() {
//        return cucumberTestContext;
//    }
}
