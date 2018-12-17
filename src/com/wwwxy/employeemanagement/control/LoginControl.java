package com.wwwxy.employeemanagement.control;

import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.LoginDao;

public class LoginControl {
	public void login(){
		boolean flag = false;
		@SuppressWarnings("resource")
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
	}

}
