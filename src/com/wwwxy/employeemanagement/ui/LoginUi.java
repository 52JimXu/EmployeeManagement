package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.LeafElement;

import com.wwwxy.employeemanagement.control.LoginControl;
import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.entity.LoginEntity;

public class LoginUi {
	public int login() {
		LoginControl lc = new LoginControl();

		LoginDao ld = new LoginDao();
		int admin=ld.getAdminById(lc.login());
		if(admin == 1){
			return 1;
		}else{
			return 0;
		}
		
		
	}
	Scanner input = new Scanner(System.in);
	LoginControl lc = new LoginControl();
	public void Awesome(){
		String f = "y";
		do{
			System.out.println("1、显示所有员工信息");
			System.out.println("2、输入员工账号查询员工信息");
			System.out.println("3、修改员工信息");
			System.out.println("4、新增员工信息");
			System.out.println("5、删除员工信息");
			System.out.println("6、返回上一级");
			System.out.println("请输入您要对员工进行的管理操作：");
			int information = input.nextInt();
			switch(information){
			case 1:
				getAllLogin();
				break;
			case 2:
				getLoginByUsername(information);
				break;
			case 3:
				updateLoginByEmpid1(information);
				break;
			case 4:
				addLogin();
				break;
			case 5:
				delLoginByEmpid(information);
				break;
			case 6:
				f ="n";
				break;
			default:
				System.out.println("输入有误!");
			}
			if(information!=6){
				System.out.println("是否继续y/n");
				f= input.next();
			}
		}while("y".equals(f));
	}
	//显示所有登录信息
	public void getAllLogin(){
		List<LoginEntity> list = lc.getAllLogin();
		System.out.println("工号\t账号\t密码\t身份编号\t编号");
		for(LoginEntity le:list){
			if(le.getAdmin()==1){
			System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
					le.getPassword()+"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}else{
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
						"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}
		}
	}
	//根据账号查询员工信息
	public void getLoginByUsername(int information){
		if(information ==2){
			System.out.println("请输入你要查询的员工账号：");
		}else if(information ==3){
			System.out.println("请输入你要修改的员工账号：");
		}else{
			System.out.println("请输入你要删除的员工账号：");
		}
		String username = input.next();
		List<LoginEntity> list = lc.getLoginByUsername(username);
		if(list.size()!=0){
			System.out.println("工号\t账号\t编号");
			for(LoginEntity le:list){
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+le.getEmpid());
			}
		}else{
			System.out.println("未查询到信息");
		}
	}
	//根据编号修改账号
	public void updateLoginByEmpid1(int information){
		getLoginByUsername(information);
		System.out.println("请选择以上查询出的员工当中您要修改的员工编号：");
		int empid = input.nextInt();
		LoginEntity le =lc.getLoginByEmpid(empid);	
		System.out.println("请选择您要修改的信息：");
		System.out.println("1、账号");
		System.out.println("请输入修改后的账号：");
		String username = input.next();
		int row = lc.updateLoginByEmpid1(empid, username);
		if(row>0){
			System.out.println("修改成功。");
			getAllLogin();
		}else{
			System.out.println("修改失败。");
		}
	}
	//新增登录信息
	public void addLogin(){
		System.out.println("请输入要添加的账号：");
		String username = input.next();
		System.out.println("请输入要添加的编号：");
		int empid = input.nextInt();
		LoginEntity le = new LoginEntity(0, username, null, 0, empid);
		le.setUsername(username);
		le.setEmpid(empid);
		int row = lc.addLogin(le);
		if(row>0){
			System.out.println("添加成功。");
			getAllLogin();
		}else{
			System.out.println("添加失败。");
		}
	}
	//删除信息
	public void delLoginByEmpid(int information){
		getLoginByUsername(information);
		System.out.println("请选择以上查询出的员工当中您要删除的员工编号：");
		int empid = input.nextInt();
		int row = lc.delLoginByEmpid(empid);
		if(row>0){
			System.out.println("删除成功。");
			getAllLogin();
		}else{
			System.out.println("删除失败。");
		}
	}
}
