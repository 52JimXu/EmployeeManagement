package com.wwwxy.employeemanagement.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class CheckDetails {
private int cid;//考勤ID
private int empid;//员工ID
private Timestamp ccheckin;//签到时间
private Timestamp ccheckout;//签退时间
private String cstatus;//考勤状态
private String cdate;//考勤日期
public CheckDetails(){
	
}
public CheckDetails(int cid, int empid, Timestamp ccheckin,
		Timestamp ccheckout, String cstatus,String cdate) {
	super();
	this.cid = cid;
	this.empid = empid;
	this.ccheckin = ccheckin;
	this.ccheckout = ccheckout;
	this.cstatus = cstatus;
	this.cdate = cdate;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public int getEmpid() {
	return empid;
}
public void setEmpid(int empid) {
	this.empid = empid;
}
public Timestamp getCcheckin() {
	return ccheckin;
}
public void setCcheckin(Timestamp ccheckin) {
	this.ccheckin = ccheckin;
}
public Timestamp getCcheckout() {
	return ccheckout;
}
public void setCcheckout(Timestamp ccheckout) {
	this.ccheckout = ccheckout;
}
public String getCstatus() {
	return cstatus;
}
public void setCstatus(String cstatus) {
	this.cstatus = cstatus;
}
public String getCdate() {
	return cdate;
}
public void setCdate(String cdate) {
	this.cdate = cdate;
}

}
