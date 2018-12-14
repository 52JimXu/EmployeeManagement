package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.EmployeeControl;
import com.wwwxy.employeemanagement.entity.EmployeeEntity;

public class EmployeeUi {
	Scanner sc = new Scanner(System.in);
	EmployeeControl ec = new EmployeeControl();
	public void EmployeeUiAll() {
		// TODO Auto-generated constructor stub
		boolean flag = false;
		do{
			System.out.println("1.查看员工信息");
			System.out.println("2.输入姓名查询员工信息");
			System.out.println("3.修改员工信息");
			System.out.println("4.添加员工");
			System.out.println("5.删除员工");
			System.out.println("6.返回上一级");
			System.out.println("请选择执行的操作:");
			int id = sc.nextInt();
			switch (id) {
				case 1:
					getAllEmployee();
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
					flag = false;
					break;
	
				default:
					System.out.println("输入有误");
					break;
			}
		}while(flag);
		
	}
	
	//查看员工信息
	public void getAllEmployee(){
		List<EmployeeEntity> list = ec.getAllEmployee();
		System.out.println("编号\t姓名\t性别\t年龄\t出生时间\t\t工资\t邮箱地址\t\t\t现住地址");
		for(EmployeeEntity ee:list){
			System.out.println(ee.getEmpId()+"\t"+ee.getEmpName()+"\t"+ee.getEmpSex()+"\t"+ee.getEmpAge()+"\t"+ee.getEmpBirthday()+"\t"+
			ee.getEmpBasic()+"\t"+ee.getEmpEmail()+"\t"+ee.getEmpAddress());
		}
	}
}
