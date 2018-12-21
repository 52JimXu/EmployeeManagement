package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.LoginControl;
import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.entity.EventEntity;
import com.wwwxy.employeemanagement.entity.SalaryEntity;


public class Manage {
	public static void main(String[] args) {
		new Manage().all();
	}
	public void all() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		LoginUi lu = new LoginUi();
		System.out.println("欢迎来到人力资源管理系统");
		int arr[] = lu.login();
		int admin = arr[0];
		int id = arr[1];
		if(id==0){
			System.out.println("对不起，已退出人力资源系统");
			return;
		}
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
				boolean flag = true;
				while(flag){
					try {
						information=input.nextInt();
						flag = false;
					} catch (Exception e) {
						System.out.println("输入有误,请输入整数:");
						input = new Scanner(System.in);
						flag = true;
						continue;
					}
				}
				switch(information){
				case 1:
					new EmployeeUi().EmployeeUiAll();
					break;
				case 2:
					new CheckDateilsUi().all();
					break;
				case 3:
					new EventUi().all();
					break;
				case 4:
					new SalaryUi().Salary();
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
					System.out.println("是否继续使用员工管理系统?（继续请输入y/退出输入n）：");
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
				int empid = new LoginDao().getEmpidById(id);
				boolean flag = true;
				while(flag){
					try {
						information = input.nextInt();
						flag = false;
					} catch (Exception e) {
						System.out.println("输入有误,请输入整数:");
						input = new Scanner(System.in);
						flag = true;
						continue;
					}
				}
				switch(information){
				case 1:
					
					new SignMethodUi().IsCheck(empid);
					
					
					break;
				case 2:
					List<SalaryEntity> list =new SalaryDao().GetSalaryByEmpId(empid);
					System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
					for(SalaryEntity list1:list){
						System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
								+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
					}
					break;
				case 3:
					List<EventEntity> list2 = new EventDao().getEventById(empid);
					System.out.println("事项排序\t员工编号\t迟到早退\t加班次数\t旷工次数\t工资情况");
					for(EventEntity e:list2){
						System.out.println(e.geteId()+"\t"+e.geteMpid()+"\t"+e.geteClocking()+"\t"+e.geteOvertime()+"\t"+e.geteBigevent()+"\t"+e.geteAward());
					}
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
			System.out.println("对不起，现已退出人力资源管理系统");
		}
	}

}
