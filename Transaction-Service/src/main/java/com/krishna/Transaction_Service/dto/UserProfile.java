package com.krishna.Transaction_Service.dto;

import java.util.List;

public class UserProfile {

    private String userId;

    private String country;

    private List<String> devices;

    private Double averageAmount;

	public UserProfile(String userId, String country, List<String> devices, Double averageAmount) {
		super();
		this.userId = userId;
		this.country = country;
		this.devices = devices;
		this.averageAmount = averageAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getDevices() {
		return devices;
	}

	public void setDevices(List<String> devices) {
		this.devices = devices;
	}

	public Double getAverageAmount() {
		return averageAmount;
	}

	public void setAverageAmount(Double averageAmount) {
		this.averageAmount = averageAmount;
	}
    
    
}
