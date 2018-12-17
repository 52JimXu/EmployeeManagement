package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.LoginControl;
import com.wwwxy.employeemanagement.entity.LoginEntity;

public class LoginUi {
	public boolean login() {
		LoginControl lc = new LoginControl();
		return lc.login();
	}
	Scanner input = new Scanner(System.in);
	LoginControl lC = new LoginControl();
	public void Awesome(){
		String f = "y";
		do{
			System.out.println("1、显示所有登录信息");
			System.out.println("2、输入员工账号查询信息");
			System.out.println("3、修改员工登录信息");
			System.out.println("4、新增员工登录信息");
			System.out.println("5、删除员工登录信息");
			System.out.println("6、返回上一级");
			System.out.println("请输入您要对员工进行的管理操作：");
			int information = input.nextInt();
			switch(information){
			case 1:
				getAllLogin();
				break;
			case 2:
				getLoginByUsername(information);
				break;
			case 3:
				updateLoginByEmpid(information);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				f ="n";
				break;
			default:
				System.out.println("输入有误");
			}
			if(information!=6){
				System.out.println("是否继续y/n");
				f= input.next();
			}
		}while("y".equals(f));
	}
	//显示所有登录信息
	public void getAllLogin(){
		List<LoginEntity> list = lC.getAllLogin();
		System.out.println("工号\t账号\t密码\t身份编号\t编号");
		for(LoginEntity le:list){
			if(le.getAdmin()==1){
			System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
					le.getPassword()+"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}else{
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
						"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}
		}
	}
	//根据账号查询员工信息
	public void getLoginByUsername(int information){
		if(information ==2){
			System.out.println("请输入你要查询的员工账号:");
		}else if(information ==3){
			System.out.println("请输入你要修改的员工账号:");
		}else{
			System.out.println("请输入你要删除的员工账号:");
		}
		String username = input.next();
		List<LoginEntity> list = lC.getLoginByUsername(username);
		if(list.size()!=0){
			System.out.println("工号\t账号\t编号");
			for(LoginEntity le:list){
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+le.getEmpid());
			}
		}else{
			System.out.println("未查询到信息");
		}
	}
	//修改员工信息
	public void updateLoginByEmpid(int information){
		getLoginByUsername(information);
		System.out.println("请选择以上查询出的员工当中你要修改的员工编号:");
		int empid = input.nextInt();
		LoginEntity le = lC.getLoginByEmpid(empid);
		System.out.println("1、密码");
//		System.out.println("输入");

	}

}
