package com.wwwxy.employeemanagement.entity;

public class LoginEntity {
	//����Ա��Ա��ID
	private int Lid;
	//����Ա��Ա������
	private String Lusername;
	//����Ա��Ա������
	private String Lpassword;
	//����Ա��Ա���˺�
	private int Ladmin;
	//���Եķ�����
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
	//����Ա��Ա�����
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
