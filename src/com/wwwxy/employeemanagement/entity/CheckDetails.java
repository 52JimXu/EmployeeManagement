package com.wwwxy.employeemanagement.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class CheckDetails {
private int cid;//����ID
private int empid;//Ա��ID
private String ccheckin;//ǩ��ʱ��
private String ccheckout;//ǩ��ʱ��
private String cstatus;//����״̬
private String cdate;//��������
public CheckDetails(){
	
}
public CheckDetails(int cid, int empid, String ccheckin,
		String ccheckout, String cstatus,String cdate) {
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
