package com.example.soundit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Environment;
import android.util.Log;

public class ApplicationProperties {

    private static ApplicationProperties mInstance = new ApplicationProperties();

    private static final String LOG_TAG = "AppProperties";
    private Map<String, Object> properties;
    private String soundFileName;
    private List<String> soundSuggestions;
    private List<String> playedSoundSuggestions;
    private List<SoundResource> soundResources;

    private ApplicationProperties() {
    	Log.d(LOG_TAG, "Instantiating application properties." );
    	// map to store app state
    	properties = new HashMap<String, Object>();
    	
    	// the sound file we will record to
    	soundFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
    	soundFileName += "/soundit-audio.3gp";
    	
    	// list of sounds to mimic
    	soundSuggestions = createSoundSuggestions();
    	
    	// list of played sound suggestions
    	playedSoundSuggestions = new ArrayList<String>();
    	
    	// list of sound resources
    	soundResources = createSoundResources();
    }
    
    public static ApplicationProperties getInstance(){
        return mInstance;
    }
    
    private List<SoundResource> createSoundResources() {
    	List<SoundResource> list = new ArrayList<SoundResource>();
    	list.add(new SoundResource("cat",R.raw.cat));
    	list.add(new SoundResource("clap",R.raw.clap));
    	list.add(new SoundResource("dog",R.raw.dog));
    	list.add(new SoundResource("pig",R.raw.pig));
    	list.add(new SoundResource("snake",R.raw.snake));
    	// list.add(new SoundResource("",R.raw));
    	return list;
    }
    
    public SoundResource getSoundResource() {
    	if(soundResources.isEmpty()) {
    		soundResources = createSoundResources();
    	}
    	
    	return soundResources.remove(0);
    }
    
    public List<String> getSoundSuggestions(int size) {
    	List<String> list = new ArrayList<String>();
    	// shuffle so we get random order
    	Collections.shuffle(soundSuggestions);
    	
		for(int i = 0; i < soundSuggestions.size(); i++) {
			String suggestion = soundSuggestions.get(i);
			// check if this has already been played
			if(playedSoundSuggestions.contains(suggestion)) {
				list.add(suggestion);
				if(list.size() == size) {
					break;
				}
			}
		}
    	
    	// if everything has been played just add whatever
    	if(list.size() < size) {
    		list = new ArrayList<String>(soundSuggestions.subList(0, size));
    	}
    	
    	return list;
    }
    
    public void addPlayedSoundSuggestion(String sound) {
    	if(sound != null && !playedSoundSuggestions.contains(sound)) {
    		playedSoundSuggestions.add(sound);
    	}
    }
    
    public void clearPlayedSoundSuggestion() {
    	playedSoundSuggestions.clear();
    }
    
    private List<String> createSoundSuggestions() {
    	List<String> list = new ArrayList<String>();
    	list.add("dog");
    	list.add("cat");
    	list.add("pig");
    	list.add("chicken");
    	list.add("horse");
    	list.add("snake");
		list.add("frog");
		list.add("monkey");
		list.add("mouse");
		list.add("clap");
		list.add("drumroll");
		list.add("drums");
		list.add("trumpet");
		list.add("piano");
		list.add("guitar");
		list.add("nightmare");
		list.add("siren");
		list.add("phone");
		list.add("alarm");
		list.add("blowfish");
		list.add("cowbell");
		list.add("motorbike");
		list.add("train");
		list.add("car");
		list.add("catfish");
		list.add("leapfrog");
		list.add("wheelbarrow");
		list.add("footsteps");

    	return list;
    }
    
    public void clearProperties() {
    	Log.d(LOG_TAG, "Clear properties." );
    	properties.clear();
    }
    
    public Map<String,Object> getProperties() {
    	return properties;
    }
    
    public String getSoundFileName() {
    	return soundFileName;
    }
}
