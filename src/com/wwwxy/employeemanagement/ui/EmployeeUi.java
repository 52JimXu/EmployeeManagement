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
			System.out.println("1.�鿴Ա����Ϣ");
			System.out.println("2.����������ѯԱ����Ϣ");
			System.out.println("3.�޸�Ա����Ϣ");
			System.out.println("4.���Ա��");
			System.out.println("5.ɾ��Ա��");
			System.out.println("6.������һ��");
			System.out.println("��ѡ��ִ�еĲ���:");
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
					System.out.println("��������");
					break;
			}
		}while(flag);
		
	}
	
	//�鿴Ա����Ϣ
	public void getAllEmployee(){
		List<EmployeeEntity> list = ec.getAllEmployee();
		System.out.println("���\t����\t�Ա�\t����\t����ʱ��\t\t����\t�����ַ\t\t\t��ס��ַ");
		for(EmployeeEntity ee:list){
			System.out.println(ee.getEmpId()+"\t"+ee.getEmpName()+"\t"+ee.getEmpSex()+"\t"+ee.getEmpAge()+"\t"+ee.getEmpBirthday()+"\t"+
			ee.getEmpBasic()+"\t"+ee.getEmpEmail()+"\t"+ee.getEmpAddress());
		}
	}
}
