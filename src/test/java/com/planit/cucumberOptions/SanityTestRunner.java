package com.planit.cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/planit/features/",
        glue = "com/planit/stepDefinitions",
        tags = "@sanity " +
                "and not @broken " +
                "and not @notImplemented",
        plugin = {"pretty","json:target/cucumber_report.json"})
public class SanityTestRunner extends AbstractTestNGCucumberTests {
}
