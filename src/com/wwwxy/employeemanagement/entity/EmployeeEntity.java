package com.wwwxy.employeemanagement.entity;

import java.sql.Date;



public class EmployeeEntity {
	private Integer EmpId;
	private String EmpName;
	private String EmpSex;
	private Integer EmpAge;
	private Date EmpBirthday;
	private Integer EmpBasic;
	private String EmpEmail;
	private String EmpAddress;
	
	
	
	public EmployeeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeEntity(Integer empId, String empName, String empSex,
			Integer empAge, Date empBirthday, Integer empBasic,
			String empEmail, String empAddress) {
		super();
		EmpId = empId;
		EmpName = empName;
		EmpSex = empSex;
		EmpAge = empAge;
		EmpBirthday = empBirthday;
		EmpBasic = empBasic;
		EmpEmail = empEmail;
		EmpAddress = empAddress;
	}
	public Integer getEmpId() {
		return EmpId;
	}
	public void setEmpId(Integer empId) {
		EmpId = empId;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public String getEmpSex() {
		return EmpSex;
	}
	public void setEmpSex(String empSex) {
		EmpSex = empSex;
	}
	public Integer getEmpAge() {
		return EmpAge;
	}
	public void setEmpAge(Integer empAge) {
		EmpAge = empAge;
	}
	public Date getEmpBirthday() {
		return EmpBirthday;
	}
	public void setEmpBirthday(Date empBirthday) {
		EmpBirthday = empBirthday;
	}
	public Integer getEmpBasic() {
		return EmpBasic;
	}
	public void setEmpBasic(Integer empBasic) {
		EmpBasic = empBasic;
	}
	public String getEmpEmail() {
		return EmpEmail;
	}
	public void setEmpEmail(String empEmail) {
		EmpEmail = empEmail;
	}
	public String getEmpAddress() {
		return EmpAddress;
	}
	public void setEmpAddress(String empAddress) {
		EmpAddress = empAddress;
	}
	
}
