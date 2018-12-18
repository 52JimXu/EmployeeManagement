package com.wwwxy.employeemanagement.entity;

public class LoginEntity {
	//管理员，员工工号
	private int id;
	//管理员，员工账号
	private String username;
	//管理员，员工密码
	private String password;
	//管理员，员工
	private int admin;
	//属性的访问器
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	//管理员，员工编号
	private int empid;
	public LoginEntity(int id, String username, String password, int admin, int empid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.empid = empid;
	}
	public LoginEntity() {
		
	}
}
