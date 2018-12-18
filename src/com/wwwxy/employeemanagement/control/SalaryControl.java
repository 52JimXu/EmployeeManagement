package com.wwwxy.employeemanagement.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.entity.SalaryEntity;

public class SalaryControl {
	SalaryDao sd =new SalaryDao();
	Scanner sc = new Scanner(System.in);
	public void GetAllSalary() {
		List<SalaryEntity> list = sd.GetAllSalary();
		System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
		for(SalaryEntity list1:list){
			System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
		+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
	
	public void GetSalaryById(int id) {
		List<SalaryEntity> list = sd.GetSalaryById(id);
		System.out.println("工资编号\t员工ID\t事项ID\t工资总和\t发工资时间\t");
		for(SalaryEntity list1:list){
			System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
		+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
	
	
	public void AddSalary() {
		System.out.println("请输入增加的员工编号:");
		int empid = sc.nextInt();
		System.out.println("请输入增加的事项ID:");
		int eid = sc.nextInt();
		System.out.println("请输入增加的工资总和:");
		float ssum = sc.nextFloat();
		System.out.println("请输入增加的发工资时间:");
		String stime = sc.next();
		SalaryEntity se = new SalaryEntity(0, empid, eid, ssum, stime);
		int row = sd.add(se);
		if(row>0){
			System.out.println("操作成功");
		}else{
			System.out.println("操作失败");
		}
	}
	public void DeleteSalary() {
		System.out.println("请输入你要删除的工资ID:");
		int id = sc.nextInt();
		int row = sd.delete(id);
		if(row>0){
			System.out.println("操作成功");
		}else{
			System.out.println("操作失败");
		}
	}
	public void UpdateSalary() {
		SalaryEntity se = new SalaryEntity();
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
		
		System.out.println("请选择你要修改的选项，用逗号隔开：");
		String msg = sc.next();
		String[] strs = msg.split(",");
		for(String str:strs){
			if("1".equals(str)){
				System.out.println("请输入修改后的员工ID：");
				int empId = sc.nextInt();
				se.setEmpId(empId);
			}
			if("2".equals(str)){
				System.out.println("请输入修改后的事项ID：");
				int eid = sc.nextInt();
				se.seteId(eid);
			}
			if("3".equals(str)){
				System.out.println("请输入修改后的工资总和：");
				float ssum = sc.nextFloat();
				se.setsSum(ssum);
			}
			if("4".equals(str)){
				System.out.println("请输入修改后的发工资时间：");
				String stime = sc.next();
				se.setsTime(stime);
			}
		}
		int row = sd.update(se);
		if(row>0){
			System.out.println("操作成功");
		}else{
			System.out.println("操作失败");
		}
	}
	public static void main(String[] args) {
		new SalaryControl().UpdateSalary();
	}
}
