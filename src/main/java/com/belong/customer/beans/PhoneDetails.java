package com.belong.customer.beans;

public class PhoneDetails {

	private String phoneNumber;
	private boolean isActive;

	public PhoneDetails(String phoneNumber, boolean isActive) {
		super();
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
