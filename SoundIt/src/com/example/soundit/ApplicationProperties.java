package com.example.soundit;

import java.util.HashMap;
import java.util.Map;

public class ApplicationProperties {

    private static ApplicationProperties mInstance = new ApplicationProperties();

    private Map<String, Object> properties;

    private ApplicationProperties() {
    	properties = new HashMap<String, Object>();
    }

    public static ApplicationProperties getInstance(){
        return mInstance;
    }
    
    public Map<String,Object> getProperties() {
    	return properties;
    }
}
