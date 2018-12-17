package com.wwwxy.employeemanagement.control;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.entity.LoginEntity;

public class LoginControl {
	public boolean login(){
		boolean flag = false;
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
			flag = ld.login(username, password);
			if(flag){
				System.out.println("恭喜你，登录成功");
				break;
			}else{
				count++;
				System.out.println("登录失败，您还有"+(3-count)+"次机会。");
			}
		}while(count<3);
		return flag;
	}
	LoginDao ld = new LoginDao();
	//显示所有登录信息
	public List<LoginEntity> getAllLogin(){
		return ld.getAllLogin();
	}
	//输入账号查询员工信息,模糊查询
	public List<LoginEntity> getLoginByUsername(String username){
		return ld.getLoginByUsername(username);
	}
	//修改员工信息
	public int updateLoginByEmpid(LoginEntity le){
		return ld.updateLoginByEmpid(le);
	}
	//根据编号查找员工信息
	public LoginEntity getLoginByEmpid(int empid){
		return ld.getLoginByEmpid(empid);
	}
}
