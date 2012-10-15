package com.example.soundit;

import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

public class ApplicationProperties {

    private static ApplicationProperties mInstance = new ApplicationProperties();

    private Map<String, Object> properties;
    private String soundFileName;

    private ApplicationProperties() {
    	properties = new HashMap<String, Object>();
    	soundFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
    	soundFileName += "/soundit-audio.3gp";
    }

    public static ApplicationProperties getInstance(){
        return mInstance;
    }
    
    public Map<String,Object> getProperties() {
    	return properties;
    }
    
    public String getSoundFileName() {
    	return soundFileName;
    }
}
