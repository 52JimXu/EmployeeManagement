package com.wwwxy.employeemanagement.entity;

public class CheckTime {
private int empid;
private String ccheckin;
private String ccheckout;
public CheckTime(){
	
}
public CheckTime(int empid, String ccheckin, String ccheckout) {
	super();
	this.empid = empid;
	this.ccheckin = ccheckin;
	this.ccheckout = ccheckout;
}
public int getEmpid() {
	return empid;
}
public void setEmpid(int empid) {
	this.empid = empid;
}
public String getCcheckin() {
	return ccheckin;
}
public void setCcheckin(String ccheckin) {
	this.ccheckin = ccheckin;
}
public String getCcheckout() {
	return ccheckout;
}
public void setCcheckout(String ccheckout) {
	this.ccheckout = ccheckout;
}

}
