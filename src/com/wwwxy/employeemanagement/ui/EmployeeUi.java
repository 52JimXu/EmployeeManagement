package com.wwwxy.employeemanagement.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.EmployeeControl;
import com.wwwxy.employeemanagement.dao.EmployeeDao;
import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.entity.EmployeeEntity;

public class EmployeeUi {
	Scanner sc = new Scanner(System.in);
	EmployeeEntity ee = new EmployeeEntity();
	EmployeeControl ec = new EmployeeControl();
	
	public void EmployeeUiAll() {
		// TODO Auto-generated constructor stub
		String flag = "y";
		do{
			System.out.println("1.查看员工信息");
			System.out.println("2.输入姓名查询员工信息");
			System.out.println("3.修改员工信息");
			System.out.println("4.添加员工");
			System.out.println("5.删除员工");
			System.out.println("6.返回上一级");
			System.out.println("请选择执行的操作:");
			int id=0;
			while (true) {
				try {
					String str3 = sc.next();
					id = Integer.valueOf(str3);
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
							System.out.println("ERROR!qqq");
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
						return;
					}
					
				} catch (Exception e) {
					System.out.println("ERROR!ssss");
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
			
			while(isflag){	
				System.out.println("请选择以上查询出的员工当中您要修改的员工编号:");
				int EmpId=0;
				try {
					EmpId = sc.nextInt();
					EmployeeEntity ee1 = new EmployeeDao().getEmployeeById(EmpId);
					EmployeeEntity ee=ec.getEmployeeById(EmpId);
					if(ee1.getEmpId()!=null){
						System.out.println("请选择您要修改的信息，用逗号隔开：");
						System.out.println("1、姓名");
						System.out.println("2、性别");
						System.out.println("3、年龄");
						System.out.println("4、出生时间");
						System.out.println("5、工资");
						System.out.println("6、邮箱");
						System.out.println("7、现居地址");
						String msg = sc.next();
						String[] strs = msg.split("[,， ]");
						
						
						boolean a =false;
							
						do{
							for(String str:strs){
								if("1".equals(str)||"2".equals(str)||"3".equals(str)||"4".equals(str)
								||"5".equals(str)||"6".equals(str)||"7".equals(str)){
									a =false;
									if("1".equals(str)){
										System.out.println("请输入修改过后的姓名:");
										String EmpName = sc.next();
										ee.setEmpName(EmpName);
										ec.UpdateEmployee(ee);
									}
									if("2".equals(str)){
										System.out.println("请选择修改过后的性别:");
										System.out.println("1.男\t2.女");
										
										String EmpSex =null;
										boolean psex =false;
										do{
											String sexid = sc.next();
											if("1".equals(sexid)){
												EmpSex = "男";
												break;
											}else if("2".equals(sexid)){
												EmpSex = "女";
												break;
											}else{
												System.out.println("请重新正确选择:");
												psex = true;
											}
										}while(psex);
										ee.setEmpSex(EmpSex);
										ec.UpdateEmployee(ee);
									}
									if("3".equals(str)){
										System.out.println("请输入修改过后的年龄:");
										int EmpAge=0;
										while(true){
											try {
												String str1 = sc.next();
												EmpAge=Integer.valueOf(str1);
											}catch (Exception e) {
												System.out.println("请正确输入年龄:");
												continue;
											}
										ee.setEmpAge(EmpAge);
										ec.UpdateEmployee(ee);
										break;
										}
									}
									if("4".equals(str)){
										System.out.println("请输入修改过后的出生时间(格式:2000-01-01):");
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										String EmpBirthday = null;
										while(true){
											EmpBirthday= sc.next();
											try {
												sdf.parse(EmpBirthday);
											} catch (ParseException e) {
												System.out.println("格式有误");
												continue;
											}
										ee.setEmpBirthday(EmpBirthday);
										ec.UpdateEmployee(ee);
										break;
										}
									}
									if("5".equals(str)){
										System.out.println("请输入修改过后的工资:");
										float EmpBasic=0;
										while(true){
										try {
												String str2 = sc.next();
												EmpBasic=Integer.valueOf(str2);
											}catch (Exception e) {
												System.out.println("请正确输入工资:");
												continue;
											}
										ee.setEmpBasic(EmpBasic);
										ec.UpdateEmployee(ee);
										break;
										}
									}
									if("6".equals(str)){
										String reg ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{3,}$";
										System.out.println("请输入修改过后的邮箱:");
										String EmpEmail= null;
										boolean flag ;
										do{
											EmpEmail =sc.next();
											if(EmpEmail.matches(reg)){
												flag = false;
												break;
											}else{
												System.out.println("请正确输入邮箱:");
												flag = true;
											}
										}while(flag);	
										ee.setEmpEmail(EmpEmail);
										ec.UpdateEmployee(ee);
										break;
										
									}
									if("7".equals(str)){
										System.out.println("请输入修改过后的现居地址:");
										String EmpAddress = sc.next();
										ee.setEmpAddress(EmpAddress);
										ec.UpdateEmployee(ee);
									}
							}else{
								System.out.println("请正确输入相应编号:");
								a = true;
								msg = sc.next();
								strs = msg.split("[,， ]");
							}
						}					
					}while(a);
							
						
						
						boolean flag = ec.UpdateEmployee(ee);
						if(flag){
							System.out.println("修改成功!");
						}else{
							System.out.println("修改失败!");
						}
						getAllEmployee();
						return;
					}else{
						System.out.println("上述列表中不存在该ID员工！");
						System.out.println("请重新输入员工编号:");
					}
				} catch (Exception e) {
					System.out.println("输入无匹配!");
					sc = new Scanner(System.in);
					continue;
				}
				
			}
		}

	//4新增员工信息
	public void AddEmployee(){
		System.out.println("请输入要新增的员工姓名:");
		String EmpName = sc.next();
		
		System.out.println("性别:");
		System.out.println("1.男\t2.女");
		String EmpSex =null;
		boolean psex =false;
		do{
			String sexid = sc.next();
			if("1".equals(sexid)){
				EmpSex = "男";
				break;
			}else if("2".equals(sexid)){
				EmpSex = "女";
				break;
			}else{
				System.out.println("请重新正确选择:");
				psex = true;
			}
		}while(psex);
		
		
		System.out.println("年龄:");
		int EmpAge=0;
		while(true){
		try {
				String str1 = sc.next();
				EmpAge=Integer.valueOf(str1);
			}catch (Exception e) {
				System.out.println("请正确输入年龄:");
				continue;
			}
		break;
		}
		
		System.out.println("出生年月:");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String EmpBirthday = null;
		while(true){
			EmpBirthday= sc.next();
			try {
				sdf.parse(EmpBirthday);
			} catch (ParseException e) {
				System.out.println("格式有误");
				continue;
			}
		break;
		}
		
		System.out.println("当月工资:");
		float EmpBasic=0;
		while(true){
			try {
				String str2 = sc.next();
				EmpBasic=Integer.valueOf(str2);
			}catch (Exception e) {
				System.out.println("请正确输入工资:");
				continue;
			}
		break;
		}
		
		
		System.out.println("邮箱地址:");
		String reg ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{3,}$";
		String EmpEmail= null;
		boolean flagem ;
		do{
			EmpEmail =sc.next();
			if(EmpEmail.matches(reg)){
				flagem = false;
				break;
			}else{
				System.out.println("请正确输入邮箱:");
				flagem = true;
			}
		}while(flagem);
		
		
		System.out.println("现居地址:");
		String EmpAddress = sc.next();
		EmployeeEntity ee = new EmployeeEntity(EmpName,EmpSex, EmpAge, EmpBirthday, EmpBasic, EmpEmail, EmpAddress);
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
