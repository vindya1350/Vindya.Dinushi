package com.planit.utils;

import org.testng.Reporter;
import org.testng.xml.XmlTest;


public class TestNGParameters {
    public String Browser ;
    public boolean Headless ;
    public String EnvironmentName ;
    public String BaseURL ;
    public String SiteName ;

    public TestNGParameters(Boolean init){
        if(init) loadFromTestNGXML();
    }

    public void loadFromTestNGXML(){
        XmlTest testSuite = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

        Browser = testSuite.getParameter(TestNGParameter.Browser.toString());
        String headlessSetting = testSuite.getParameter(TestNGParameter.Headless.toString());
        Headless = headlessSetting.equalsIgnoreCase("true");
        EnvironmentName =testSuite.getParameter(TestNGParameter.EnvironmentName.toString());
        BaseURL =testSuite.getParameter(TestNGParameter.BaseURL.toString());
        SiteName =testSuite.getParameter(TestNGParameter.SiteName.toString());

    }
}
