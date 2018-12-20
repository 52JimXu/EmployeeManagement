package com.wwwxy.employeemanagement.ui;

import java.util.Scanner;

import com.wwwxy.employeemanagement.control.LoginControl;


public class Manage {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		LoginUi lu = new LoginUi();
		System.out.println("欢迎来到人力资源管理系统");
		int arr[] = lu.login();
		int admin = arr[0];
		int id = arr[1];
		if(admin !=0){
			System.out.println("----------欢迎进入管理员系统----------");
			String f = "y";
			do{
				System.out.println("1、员工信息管理");
				System.out.println("2、考勤信息管理");
				System.out.println("3、事项信息管理");
				System.out.println("4、工资信息管理");
				System.out.println("5、登录信息管理");
				System.out.println("6、密码信息管理");
				System.out.println("7、退出管理系统");
				System.out.println("请选择您要进行的操作(输入操作编号即可)：");
				int information = 0;
				try {
					information=input.nextInt();
				} catch (Exception e) {
					System.out.println("输入有误,请输入整数");
					input = new Scanner(System.in);
					continue;
				}
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
					lu.Reset(id);
					break;
				case 7:
					f="n";
					break;
				default:
					System.out.println("输入有误!");
					break;
				}
				if(information !=7){
					System.out.println("是否继续?（继续请输入y/退出输入n）：");
					f = input.next();	
				}	
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("输入有误，请输入y或者n");
					f = input.next();
				}
			}while("y".equals(f));{	
				System.out.println("对不起，已退出管理员系统");
			}
		}else if(admin == 0){
			System.out.println("----------欢迎进入个人管理系统----------");
			String f = "y";
			do{
				System.out.println("1、考勤打卡");
				System.out.println("2、工资查询");
				System.out.println("3、事项查询");
				System.out.println("4、修改密码");
				System.out.println("5、退出系统");
				System.out.println("请选择您要进行的操作(输入操作编号即可)：");
				int information = 0;
				try {
					information = input.nextInt();
				} catch (Exception e) {
					System.out.println("输入有误,请输入整数");
					input = new Scanner(System.in);
					continue;
				}
				switch(information){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					lu.UpdateLoginPassword(id);
					break;
				case 5:
					f="n";
					break;
				default:
					System.out.println("输入有误!");
					break;
				}
				if(information!=5){
					System.out.println("是否继续? （继续请输入y/退出输入n）：");
					f = input.next();
				}					
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("输入有误，请输入y或者n");
					f = input.next();
				}
			}while("y".equals(f));{
				System.out.println("对不起，已退出个人管理系统");
			}
		}else{
			System.out.println("对不起，现已退出人力资源系统");
		}
	}

}
