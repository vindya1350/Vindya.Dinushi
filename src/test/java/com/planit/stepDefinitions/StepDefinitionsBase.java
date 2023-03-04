package com.planit.stepDefinitions;

import com.planit.utils.CucumberTestContext;
import com.planit.utils.PageObjectManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StepDefinitionsBase {

    protected final CucumberTestContext cucumberTestContext;
    protected static Logger logger;

    public StepDefinitionsBase(CucumberTestContext context) {
        cucumberTestContext = context;

        logger = LogManager.getLogger(this);
        logger.info("Initialised");
    }

    protected PageObjectManager Pages() {
        return cucumberTestContext.getPageObjectManager();
    }

    public CucumberTestContext getCucumberTestContext() {
        return cucumberTestContext;
    }
}
