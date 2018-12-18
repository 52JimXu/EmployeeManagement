package com.wwwxy.employeemanagement.ui;

import java.util.Scanner;


public class Manage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		LoginUi lu = new LoginUi();
		System.out.println("欢迎来到人力资源管理系统");
		int id = lu.login();
		if(id !=0){
			System.out.println("----------欢迎进入人力资源管理系统----------");
			String f = "y";
			do{
				System.out.println("1、员工信息管理");
				System.out.println("2、考勤信息管理");
				System.out.println("3、事项信息管理");
				System.out.println("4、工资信息管理");
				System.out.println("5、登录信息管理");
				System.out.println("6、退出管理系统");
				System.out.println("请选择您要进行的操作(输入操作编号即可)：");
				int information = input.nextInt();
				switch(information){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					lu.Awesome();
					break;
				case 6:
					f="n";
					break;
				default:
					System.out.println("输入有误!");
					break;
				}
				if(information!=6){
					System.out.println("是否继续（y/n）：");
					f = input.next();
				}					
				if(!"y".equals(f)){
					System.out.println("退出系统");
				}
			}while("y".equals(f));
		}else if(id == 0){
			System.out.println("----------欢迎进入个人管理系统----------");
			String f = "y";
			do{
				System.out.println("1、考勤打卡");
				System.out.println("2、工资查询");
				System.out.println("3、事项查询");
				System.out.println("4、修改密码");
				System.out.println("5、退出系统");
				System.out.println("请选择您要进行的操作(输入操作编号即可)：");
				int information = input.nextInt();
				switch(information){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					f="n";
					break;
				default:
					System.out.println("输入有误!");
					break;
				}
				if(information!=5){
					System.out.println("是否继续（y/n）：");
					f = input.next();
				}					
				if(!"y".equals(f)){
					System.out.println("退出系统");
				}
			}while("y".equals(f));
		}else{
			System.out.println("对不起，现已退出系统");
		}
	}

}
