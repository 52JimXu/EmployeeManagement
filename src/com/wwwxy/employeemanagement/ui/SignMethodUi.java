package com.wwwxy.employeemanagement.ui;

import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.CheckDetailsDao;
import com.wwwxy.employeemanagement.dao.SignMethodDao;

public class SignMethodUi {
	public void checkin(int empid) {
		int row = 0;

		CheckDetailsDao cdd = new CheckDetailsDao();
		int i = cdd.GetCheckin(empid);
		if(i==0){
			row = cdd.checkin(empid);
			if(row>0){
				System.out.println("签到成功");
			}else{
				System.out.println("签到失败");
			}
		}else{
			System.out.println("你已经签过到了");
		}
	}
	public void checkout(int empid){
		int row = 0;
		CheckDetailsDao cdd = new CheckDetailsDao();
		int i = cdd.GetCheckin(empid);
		if(i !=0){
			row = cdd.checkout(empid);
		}else{
			System.out.println("你还未签到");
		}
		if(row>0){
			System.out.println("签退成功");
			//更新事项信息
			new SignMethodDao().UpdateEventByEmpid(empid,0);
		}else{
			System.out.println("签退失败");
		}
	}
	public void IsCheck(int empid) {
		String chose = "y";
		Scanner sc = new Scanner(System.in);
		int a=0;
		while("y".equals(chose)){
			System.out.println("请选择你要进行的操作");
			System.out.println("1.签到");
			System.out.println("2.签退");
			try {
				a = sc.nextInt();
			} catch (Exception e) {
				System.out.println("输入有误,请输入整数");
				sc = new Scanner(System.in);
				continue;
			}
			
			switch (a) {
			case 1:
				new SignMethodUi().checkin(empid);
				break;
			case 2:
				new SignMethodUi().checkout(empid);
				break;
			default:
				System.out.println("输入有误,请重新输入");
				break;
			}
			System.out.println("是否继续操作y/n");

				chose = sc.next();
				while(!"n".equals(chose)&&!"y".equals(chose)){
					System.out.println("输入有误，请输入y或者n");
					chose = sc.next();
				}
				
		}
		if("n".equals(chose)){
			System.out.println("已退出签到打卡");
		}
	}
	public static void main(String[] args) {
		new SignMethodUi().IsCheck(103);
	}
}
