package com.planit.cucumberOptions;

import org.apache.commons.compress.utils.Lists;
import org.testng.TestNG;

import java.util.List;

public class App {
    public static void main(String[] args){
        TestNG testRunner = new TestNG();
        List<String> suits = Lists.newArrayList();
        suits.add(args[0]);
        testRunner.setTestSuites(suits);

        testRunner.run();
    }
}
