package com.wwwxy.employeemanagement.entity;

public class CheckTimeEntity {
	private String cCheckin;
	private String cCheckout;
	public  CheckTimeEntity() {
		
	}
	public CheckTimeEntity(String cCheckin, String cCheckout) {
		super();
		this.cCheckin = cCheckin;
		this.cCheckout = cCheckout;
	}
	public String getcCheckin() {
		return cCheckin;
	}
	public void setcCheckin(String cCheckin) {
		this.cCheckin = cCheckin;
	}
	public String getcCheckout() {
		return cCheckout;
	}
	public void setcCheckout(String cCheckout) {
		this.cCheckout = cCheckout;
	}
	
}
