package com.wwwxy.employeemanagement.ui;

import java.util.Scanner;

public class Manage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		LoginUi lu = new LoginUi();
		System.out.println("欢迎来到人力资源管理系统");
		boolean flag = lu.login();
		if(flag){
			System.out.println("----------欢迎进入人力资源管理系统----------");
			String f = "y";
			do{
				System.out.println("1、管理登录");
				System.out.println("2、管理员工");
				System.out.println("3、管理考勤");
				System.out.println("4、管理事项");
				System.out.println("5、管理工资");
				System.out.println("6、退出管理系统");
				System.out.println("请选择您要进行的操作(输入操作编号即可)：");
				int information = input.nextInt();
				switch(information){
				case 1:
					lu.Awesome();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					
					break;
				case 6:
					f="n";
				default:
					System.out.println("输入有误。");
				}
				if(information!=6){
					System.out.println("是否继续（y/n）：");
					f = input.next();
				}
				if(!"y".equals(f)){
					System.out.println("系统已退出！");
				}
			}while("y".equals(f));
		}else{
			System.out.println("对不起，现已退出系统");
		}
	}

}
