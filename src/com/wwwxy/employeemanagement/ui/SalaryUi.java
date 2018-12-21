package com.wwwxy.employeemanagement.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.SalaryControl;
import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.dao.SalarySumDao;
import com.wwwxy.employeemanagement.entity.SalaryEntity;

public class SalaryUi {
	SalaryControl sac = new SalaryControl();
	SalaryDao sd = new SalaryDao();
	Scanner sc = new Scanner(System.in);
	public void Salary() {
		all();
	}
	//查询所有
	public void GetAllSalary() {
		List<SalaryEntity> list = sac.GetAllSalary();
		System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
		for(SalaryEntity list1:list){
			System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
		+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
	//查询某个员工信息，使用员工ID查询
		public void GetSalaryById(int id) {
			List<SalaryEntity> list = sac.GetSalaryById(id);
			System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
			for(SalaryEntity list1:list){
				System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
			+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
			}
		}
		//增加工资记录
		public void AddSalary() {
			System.out.println("请输入增加的员工编号:");
			int empid=0;
			int eid = 0;
			int row = 0;
			boolean flag =true;
			while(flag){
				try {
					empid = sc.nextInt();
					eid=new SalarySumDao().GetEventIdByEmpId(empid);//获取事项id
					List<SalaryEntity> list = sd.GetSalaryByEmpId(empid);
					if(list.size()!=0){
						System.out.println("已存在该员工的工资记录");
						return;
					}
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 30);
					Date date = cal.getTime();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String time= sdf.format( date);
					SalaryEntity se = new SalaryEntity(0, empid, eid, 0, time);
					
					
					row= sd.add(se);
					flag = false;
				} catch (InputMismatchException e) {
					System.out.println("输入有误,请重新输入:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}catch (NullPointerException e) {
					System.out.println("员工不存在,请重新输入:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			if(row>0){
				System.out.println("操作成功");
				GetAllSalary();
			}else{
				System.out.println("操作失败");
			}
		}
		//删除
		public void DeleteSalary() {
			GetAllSalary();
			System.out.println("请输入你要删除的工资ID:");
			boolean flag = true;
			int id=0;
			int row=0;
			while(flag){
				try {
					id = sc.nextInt();
					List<SalaryEntity> list = sd.GetSalaryById(id);
					if(list.size()==0){
						System.out.println("不存在该员工的工资记录");
						return;
					}
					row = sd.delete(id);
				} catch (InputMismatchException e) {
					System.out.println("输入有误,请重新输入:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			if(row>0){
				System.out.println("操作成功");
				GetAllSalary();
			}else{
				System.out.println("操作失败");
			}
		}
//		修改信息
		public void UpdateSalary() {
			SalaryEntity se = new SalaryEntity();
			GetAllSalary();
			System.out.println("请输入你要修改的工资编号:");
			
			int id=0;
			boolean flag = true;
			while(flag){
				try {
					 id= sc.nextInt();
					 flag = false;
					 
				} catch (Exception e) {
					System.out.println("输入有误,请输入整数:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			flag = true;
			while(flag){
				
				List<SalaryEntity> list = sd.GetSalaryById(id);
				if(list.size()==0){
					System.out.println("输入的工资ID不存在,请重新输入:");
					boolean flag1 =true;
					while(flag1){
						try {
							id= sc.nextInt();
							list = sd.GetSalaryById(id);
							flag1 = false;
							 
						} catch (Exception e) {
							System.out.println("输入有误,请输入整数:");
							sc = new Scanner(System.in);
							flag1 = true;
							continue;
						}
					}
					flag =true;
				}else{
					flag =false;
				}
			}
			
			se.setsId(id);
			List<SalaryEntity> list = sd.GetSalaryById(id);

			for(SalaryEntity list1:list){
				if(list1.getsTime() ==null){
					System.out.println("输入有误");
					return;
				}
				se.setEmpId(list1.getEmpId());
				se.seteId(list1.geteId());
				se.setsSum(list1.getsSum());
				se.setsTime(list1.getsTime());
			}
			System.out.println("请输入你要修改的日期(格式:2000-01-01):");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			flag = true;
			String stimeString;
			
			while(flag){
				stimeString = sc.next();
				try {
					sdf.parse(stimeString);
					flag = false;
				} catch (ParseException e) {
					System.out.println("格式有误");
					flag = true;
				}
				se.setsTime(stimeString);
			}
			int row = sd.update(se);
			if(row>0){
				System.out.println("操作成功");
				GetAllSalary();
			}else{
				System.out.println("操作失败");
			}
		}
		//更新工资记录
		public void UpdateSalaryByEmpId() {
			
			new SalaryDao().GetAllSalary();
			GetAllSalary();
			int empid=0 ;
			System.out.println("请输入你要更新的员工ID：");
			boolean flag = true;
			int row =0;
			while(flag){
				try {
					empid = sc.nextInt();
					
					float salary = new SalarySumDao().SalarySum(empid);
					row = sd.UpdateSalaryByEmpId(empid, salary);
					flag = false;
				} catch (InputMismatchException e) {
					System.out.println("输入有误,请重新输入");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}catch (NullPointerException e) {
					System.out.println("不存在该员工，请重新输入");
					sc = new Scanner(System.in);
					flag = true;
					continue;
					}
			}
			if(row>0){
				System.out.println("操作成功");
				GetAllSalary();
			}else{
				System.out.println("操作失败");
			}
		}
		
		
		public void all() {
			String chose = "y";
			while("y".equals(chose)){
				System.out.println("请选择你要进行的操作:");
				System.out.println("1、新增工资记录");
				System.out.println("2、删除工资记录");
				System.out.println("3、查询工资记录");
				System.out.println("4、修改发工资时间");
				System.out.println("5、更新工资记录");
				System.out.println("6、退出工资管理");
				int num =0;
				try {
					num=sc.nextInt();
				} catch (Exception e) {
					System.out.println("输入有误,请输入整数");
					sc = new Scanner(System.in);
					continue;
				}
				
				switch (num) {
				case 1:
					AddSalary();
					break;
				case 2:
					DeleteSalary();
					break;
				case 3:
					GetAllSalary();
					break;
				case 4:
					UpdateSalary();
					break;
				case 5:
					UpdateSalaryByEmpId();
					break;
				case 6:
					chose = "n";
					break;
				default:
					System.out.println("输入有误");
					break;
				}
				if(!"n".equals(chose)){
					System.out.println("是否继续操作？（请输入y/n）");
					chose = sc.next();
					while(!"n".equals(chose)&&!"y".equals(chose)){
						System.out.println("输入有误，请输入y或者n");
						chose = sc.next();
					}
				}else{
					System.out.println("退出工资管理!");
					return;
				}
			}
			if("n".equals(chose)){
				System.out.println("已退出工资记录管理");
			}
		}
}
