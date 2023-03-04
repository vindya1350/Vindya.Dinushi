package com.planit.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String,Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setTestNGParameters(TestNGParameters parameters){
        scenarioContext.put(TestNGParameter.EnvironmentName.toString(),parameters.EnvironmentName);
        scenarioContext.put(TestNGParameter.BaseURL.toString(),parameters.BaseURL);
        scenarioContext.put(TestNGParameter.Headless.toString(),parameters.Headless);
        scenarioContext.put(TestNGParameter.SiteName.toString(),parameters.SiteName);

    }

    public Object getParameter(TestNGParameter param){
        return scenarioContext.get(param.toString());
    }

    public void clear(){
        scenarioContext.clear();
    }

    public void setValue(String key, Object value) {
        scenarioContext.put(key,value);
    }
}
