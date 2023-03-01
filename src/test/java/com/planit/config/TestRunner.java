package com.planit.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features ="/Users/danushkaperera/Downloads/Planit_Task/src/test/java/com/planit/features",
                glue = "com.planit.stepDefinitions."
        )

public class TestRunner {
}
