package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.EmployeeControl;
import com.wwwxy.employeemanagement.entity.EmployeeEntity;

public class EmployeeUi {
	Scanner sc = new Scanner(System.in);
	EmployeeEntity ee = new EmployeeEntity();
	EmployeeControl ec = new EmployeeControl();
	
	public void EmployeeUiAll() {
		// TODO Auto-generated constructor stub
		String flag = "y";
		do{
			System.out.println("1.查看员工信息\t\t4.添加员工");
			System.out.println("2.输入姓名查询员工信息\t5.删除员工");
			System.out.println("3.修改员工信息\t\t6.返回上一级");
			System.out.println("请选择执行的操作:");
			int id=0;
			while (true) {
				try {
					String str = sc.next();
					id = Integer.valueOf(str);
					switch (id) {
						case 1:
							getAllEmployee();
							break;
						case 2:
							getEmployeeByName(id);
							break;
						case 3:
							UpdateEmployee(id);
							break;
						case 4:
							AddEmployee();
							break;
						case 5:
							DeleteEmployee(id);
							break;
						case 6:
							flag = "n";
							break;
						default:
							System.out.println("输入有误!");
							break;
					}
					if(flag!="n"){
						System.out.println("是否继续(y/n):");
						flag = sc.next();
						if("y".equals(flag)){
							break;
						}
						if("n".equals(flag)){
							System.out.println("退出管理!");		
						}
						while(!"y".equals(flag)&&!"n".equals(flag)){
							System.out.println("请重新输入(y/n):");
							flag = sc.next();
							if("y".equals(flag)){
								break;
							}
							if("n".equals(flag)){
								System.out.println("退出管理!");
							}
						}
						break;
					}else {
						System.out.println("退出管理!");
					}
					
				} catch (Exception e) {
					System.out.println("ERROR!");
					System.out.println("请输入整数:");
				}
			}
				
		}while("y".equals(flag));
	}
	
	//1查看员工信息
	public void getAllEmployee(){
		List<EmployeeEntity> list = ec.getAllEmployee();
		System.out.println("编号\t姓名\t性别\t年龄\t出生时间\t\t工资\t邮箱地址\t\t\t现居地址");
		for(EmployeeEntity ee:list){
			System.out.println(ee.getEmpId()+"\t"+ee.getEmpName()+"\t"+ee.getEmpSex()+"\t"+ee.getEmpAge()+"\t"+ee.getEmpBirthday()+"\t"+
			ee.getEmpBasic()+"\t"+ee.getEmpEmail()+"\t"+ee.getEmpAddress());
		}
	}
	//2输入姓名查询员工信息即模糊查询
	public boolean getEmployeeByName(int id){
		if(id==2){
			System.out.println("请输入要查询的员工姓名:");
		}else if(id==3){
			getAllEmployee();
			System.out.println("请输入要修改的员工姓名:");
		}else{
			getAllEmployee();
			System.out.println("请输入要删除的员工姓名:");
		}
		boolean flag = true;
		String EmpName = sc.next();
		List<EmployeeEntity> list = ec.getEmployeeByName(EmpName);
		if(list.size()!=0){
			System.out.println("编号\t姓名\t性别\t年龄\t出生时间\t\t工资\t邮箱地址\t\t\t现居地址");
			for(EmployeeEntity ee:list){
				System.out.println(ee.getEmpId()+"\t"+ee.getEmpName()+"\t"+ee.getEmpSex()
						+"\t"+ee.getEmpAge()+"\t"+ee.getEmpBirthday()+"\t"+ee.getEmpBasic()
						+"\t"+ee.getEmpEmail()+"\t"+ee.getEmpAddress());
			}
		}else{
			System.out.println("员工不存在。");
			flag = false;
		}
		return flag;
	}
	//3修改员工信息
	public void UpdateEmployee(int id){
			boolean isflag = getEmployeeByName(id);
			if(isflag){	
				System.out.println("请选择以上查询出的员工当中您要修改的员工编号:");
				int EmpId = sc.nextInt();
				EmployeeEntity ee = ec.getEmployeeById(EmpId);
				
				System.out.println("请选择您要修改的信息，用逗号隔开：");
				System.out.println("1、姓名\t5、工资");
				System.out.println("2、性别\t6、邮箱");
				System.out.println("3、年龄\t7、现居地址");
				System.out.println("4、出生时间\t");
				
				String msg = sc.next();
				String[] strs = msg.split(",");
				for(String str:strs){
					if("1".equals(str)){
						System.out.println("请输入修改过后的姓名:");
						String EmpName = sc.next();
						ee.setEmpName(EmpName);
						ec.UpdateEmployee(ee);
					}
					if("2".equals(str)){
						System.out.println("请输入修改过后的性别:");
						String EmpSex = sc.next();
						ee.setEmpSex(EmpSex);
						ec.UpdateEmployee(ee);
					}
					if("3".equals(str)){
						System.out.println("请输入修改过后的年龄:");
						int EmpAge = sc.nextInt();
						ee.setEmpAge(EmpAge);
						ec.UpdateEmployee(ee);
					}
					if("4".equals(str)){
						System.out.println("请输入修改过后的出生时间:");
						String EmpBirthday = sc.next();
						ee.setEmpBirthday(EmpBirthday);
						ec.UpdateEmployee(ee);
					}
					if("5".equals(str)){
						System.out.println("请输入修改过后的工资:");
						float EmpBasic = sc.nextFloat();
						ee.setEmpBasic(EmpBasic);
						ec.UpdateEmployee(ee);
					}
					if("6".equals(str)){
						System.out.println("请输入修改过后的邮箱:");
						String EmpEmail = sc.next();
						ee.setEmpEmail(EmpEmail);
						ec.UpdateEmployee(ee);
					}
					if("7".equals(str)){
						System.out.println("请输入修改过后的现居地址:");
						String EmpAddress = sc.next();
						ee.setEmpAddress(EmpAddress);
						ec.UpdateEmployee(ee);
					}
				}
				boolean flag = ec.UpdateEmployee(ee);
				if(flag){
					System.out.println("修改成功!");
				}else{
					System.out.println("修改失败!");
				}
				getAllEmployee();
			}
		}

	//4新增员工信息
	public void AddEmployee(){
		System.out.println("请输入要新增的员工姓名:");
		String EmpName = sc.next();
		System.out.println("性别:");
		String EmpSex = sc.next();
		System.out.println("年龄:");
		int EmpAge = sc.nextInt();
		System.out.println("出生年月:");
		String EmpBirthday = sc.next();
		System.out.println("当月工资:");
		float EmpBasic = sc.nextFloat();
		System.out.println("邮箱地址:");
		String EmpEmail = sc.next();
		System.out.println("现居地址:");
		String EmpAddress = sc.next();
		EmployeeEntity ee = new EmployeeEntity(null, EmpName,EmpSex, EmpAge, EmpBirthday, EmpBasic, EmpEmail, EmpAddress);
		boolean flag = ec.AddEmployee(ee);
		if(flag){
			System.out.println("添加成功");
		}else{
			System.out.println("添加失败");
		}
		getAllEmployee();
	}
	//5删除员工
	public void DeleteEmployee(int id){
		getEmployeeByName(id);
		System.out.println("请选择您要删除的员工编号:");
		int EmpId = sc.nextInt();
		ee.setEmpId(EmpId);
		boolean flag = ec.DeleteEmployee(EmpId);
		if(flag){
			System.out.println("删除成功!");
		}else{
			System.out.println("删除失败!");
		}
		getAllEmployee();
	}
}
