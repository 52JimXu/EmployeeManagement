package com.wwwxy.employeemanagement.control;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;





import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.dao.SalarySumDao;
import com.wwwxy.employeemanagement.entity.SalaryEntity;

public class SalaryControl {
	SalaryDao sd =new SalaryDao();
	Scanner sc = new Scanner(System.in);
	
	//查询所有
	public void GetAllSalary() {
		List<SalaryEntity> list = sd.GetAllSalary();
		System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
		for(SalaryEntity list1:list){
			System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
		+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
	//查询某个员工信息，使用员工ID查询
	public void GetSalaryById(int id) {
		List<SalaryEntity> list = sd.GetSalaryById(id);
		System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
		for(SalaryEntity list1:list){
			System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
		+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
	
	//增加员工
	public void AddSalary() {
		System.out.println("请输入增加的员工编号:");
		int empid = sc.nextInt();
		int eid=new SalarySumDao().GetEventIdByEmpId(empid);
		System.out.println("请输入增加的发工资时间:");
		String stime = sc.next();
		SalaryEntity se = new SalaryEntity(0, empid, eid, 0, stime);
		int row = sd.add(se);
		if(row>0){
			System.out.println("操作成功");
			GetAllSalary();
		}else{
			System.out.println("操作失败");
		}
	}
	
	//删除
	public void DeleteSalary() {
		System.out.println("请输入你要删除的工资ID:");
		int id = sc.nextInt();
		int row = sd.delete(id);
		if(row>0){
			System.out.println("操作成功");
			GetAllSalary();
		}else{
			System.out.println("操作失败");
		}
	}
//	修改信息
	public void UpdateSalary() {
		SalaryEntity se = new SalaryEntity();
		GetAllSalary();
		System.out.println("请输入你要修改的工资编号:");
		int id = sc.nextInt();
		se.setsId(id);
		List<SalaryEntity> list = sd.GetSalaryById(id);
		for(SalaryEntity list1:list){
			se.setEmpId(list1.getEmpId());
			se.seteId(list1.geteId());
			se.setsSum(list1.getsSum());
			se.setsTime(list1.getsTime());
		}
		System.out.println("请输入你要修改的日期(格式:2000-01-01):");
		String stimeString = sc.next();
		se.setsTime(stimeString);
		
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
				flag = false;
				float salary = new SalarySumDao().SalarySum(empid);
				row = sd.UpdateSalaryByEmpId(empid, salary);
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
	
	
	public void Salary() {
		String chose = "y";
		while("y".equals(chose)){
			System.out.println("请选择你要进行的操作:");
			System.out.println("1、新增工资记录");
			System.out.println("2、删除工资记录");
			System.out.println("3、查询工资记录");
			System.out.println("4、修改发工资时间");
			System.out.println("5、更新工资记录");
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
			default:
				System.out.println("输入有误");
				break;
			}
			System.out.println("是否继续操作？（请输入y/n）");
			chose = sc.next();
			while(!"n".equals(chose)&&!"y".equals(chose)){
				System.out.println("输入有误，请输入y或者n");
				chose = sc.next();
			}
		}
		if("n".equals(chose)){
			System.out.println("已退出工资记录管理");
		}
	}
	
}
