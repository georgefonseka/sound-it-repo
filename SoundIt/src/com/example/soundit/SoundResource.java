package com.example.soundit;

public class SoundResource {
	
	private String name;
	private int resourceId;
	
	public SoundResource(String n, int rid) {
		this.name = n;
		this.resourceId = rid;
	}
	
	public String getName() {
		return name;
	}
	public int getResourceId() {
		return resourceId;
	}
	
}
