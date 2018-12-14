package com.wwwxy.employeemanagement.entity;

import java.sql.Date;

public class SalaryEntity {
	private int sId;
	private int empId;
	private int eId;
	private int sSum;
	private Date sTime;
	public SalaryEntity(){
		
	}
	public SalaryEntity(int sid, int empid, int eid, int ssum, Date stime) {
		super();
		this.sId = sid;
		this.empId = empid;
		this.eId = eid;
		this.sSum = ssum;
		this.sTime = stime;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public int getsSum() {
		return sSum;
	}
	public void setsSum(int sSum) {
		this.sSum = sSum;
	}
	public Date getsTime() {
		return sTime;
	}
	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}
	
}
