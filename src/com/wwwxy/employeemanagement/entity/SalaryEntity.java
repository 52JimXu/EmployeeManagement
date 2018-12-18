package com.wwwxy.employeemanagement.entity;


public class SalaryEntity {
	private int sId;
	private int empId;
	private int eId;
	private float sSum;
	private String sTime;
	public SalaryEntity(){
		
	}
	public SalaryEntity(int sid, int empid, int eid, float ssum,String stime) {
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
	public float getsSum() {
		return sSum;
	}
	public void setsSum(float sSum) {
		this.sSum = sSum;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	
}
