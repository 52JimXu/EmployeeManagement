package com.wwwxy.employeemanagement.control;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.entity.LoginEntity;

public class LoginControl {
	public int login(){
		int id = 0;
		Scanner input =new Scanner(System.in);
		int count =0;
		String username;
		String password;
		do{
			System.out.println("请输入账号：");
			username = input.next();
			System.out.println("请输入密码：");
			password = input.next();
			LoginDao ld = new LoginDao();
			id = ld.login(username, password);
			if(id !=0){
				String username1 = ld.getLoginById(id);
				System.out.println("恭喜你，登录成功"+"\t"+username1);
				break;
			}else{
				count++;
				System.out.println("登录失败，您还有"+(3-count)+"次机会。");
			}
		}while(count<3);
		return id;
	}
	LoginDao ld = new LoginDao();
	//显示所有登录信息
	public List<LoginEntity> getAllLogin(){
		return ld.getAllLogin();
	}
	//输入账号查询信息,模糊查询
	public List<LoginEntity> getLoginByUsername(String username){
		return ld.getLoginByUsername(username);
	}
	//重置密码
	public int updateLoginByEmpid(int empid){
		return ld.updateLoginByEmpid(empid);
	}
	//根据编号修改账号
	public int updateLoginByEmpid1(int empid,String username){
		return ld.updateLoginByEmpid1(empid, username);
	}
	//根据编号查找信息
	public LoginEntity getLoginByEmpid(int empid){
		return ld.getLoginByEmpid(empid);
	}
	//新增信息
	public int addLogin(LoginEntity le){
		return ld.addLogin(le);
	}
	//删除信息
	public int delLoginByEmpid(int empid){
		return ld.delLoginByempId(empid);
	}
}
