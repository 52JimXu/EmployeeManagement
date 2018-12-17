package com.wwwxy.employeemanagement.entity;

public class LoginEntity {
	//管理员，员工ID
	private int Lid;
	//管理员，员工姓名
	private String Lusername;
	//管理员，员工密码
	private String Lpassword;
	//管理员，员工账号
	private int Ladmin;
	//属性的访问器
	public int getId() {
		return Lid;
	}
	public void setId(int id) {
		this.Lid = id;
	}
	public String getUsername() {
		return Lusername;
	}
	public void setUsername(String username) {
		this.Lusername = username;
	}
	public String getPassword() {
		return Lpassword;
	}
	public void setPassword(String password) {
		this.Lpassword = password;
	}
	public int getAdmin() {
		return Ladmin;
	}
	public void setAdmin(int admin) {
		this.Ladmin = admin;
	}
	public int getEmpid() {
		return Lempid;
	}
	public void setEmpid(int empid) {
		this.Lempid = empid;
	}
	//管理员，员工编号
	private int Lempid;
	public LoginEntity(int id, String username, String password, int admin, int empid) {
		super();
		this.Lid = id;
		this.Lusername = username;
		this.Lpassword = password;
		this.Ladmin = admin;
		this.Lempid = empid;
	}
	public LoginEntity() {
		
	}
}
