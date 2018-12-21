package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.LoginControl;
import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.entity.LoginEntity;

public class LoginUi {
	LoginEntity le = new LoginEntity();
	public int[] login() {
		LoginControl lc = new LoginControl();
		int id = lc.login();
		int[] arr=new int[2] ;
		LoginDao ld = new LoginDao();
		int admin=ld.getAdminById(id);
		if(admin == 1){
			arr[0] =1; 
		}else{
			arr[0] =0;
		}
		arr[1] = id;
		return arr;
		
	}
	Scanner input = new Scanner(System.in);
	LoginControl lc = new LoginControl();
	public void Awesome(){
		String f = "y";
		do{
			System.out.println("1、显示所有登录信息");
			System.out.println("2、输入账号查询信息");
			System.out.println("3、修改登录信息");
			System.out.println("4、新增登录信息");
			System.out.println("5、删除登录信息");
			System.out.println("6、返回上一级");
			System.out.println("请输入您需要操作：");
			int information = 0;
			try {
				information = input.nextInt();
			} catch (Exception e) {
				System.out.println("输入有误,请输入整数");
				input = new Scanner(System.in);
				continue;
			}
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
				delLoginById3(information);
				break;
			case 6:
				f ="n";
				break;
			default:
				System.out.println("输入有误!");
				break;
			}
			if(information!=6){
				System.out.println("是否继续? (继续请输入y/退出输入n)");
				f= input.next();
			}
			if("n".equals(f)){
				System.out.println("已退出当前这一级(如再次输入“n”，系统退出)！");
			}
		}while("y".equals(f));
	}
	// 密码信息管理
	public void Reset( int id){
		String f = "y";
		do{
			System.out.println("1、重置密码");
			System.out.println("2、修改密码");
			System.out.println("3、返回上一级");
			System.out.println("请输入您需要操作：");
			int information = 0;
			try {
				information = input.nextInt();
			} catch (Exception e) {
				System.out.println("输入有误,请输入整数");
				input = new Scanner(System.in);
				continue;
			}
			switch(information){
			case 1:
				updateLoginById1();
				break;
			case 2:
				UpdateLoginPassword(id);
				break;
			case 3:
				f="n";
				break;
			default:
				System.out.println("输入有误!");
				break;
			}
			if(information!=3){
				System.out.println("是否继续?（继续请输入y/退出输入n）：");
				f = input.next();
			}			
			if("n".equals(f)){
				System.out.println("已退出当前这一级(如再次输入“ n ”，系统退出)！");
			}
		}while("y".equals(f));
	}
	//显示所有登录信息
	public void getAllLogin(){
		List<LoginEntity> list = lc.getAllLogin();
		System.out.println("ID\t账号\t密码\t身份编号\t编号");
		for(LoginEntity le:list){
			if(le.getAdmin()==1){
			System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
					"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}else{
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
						"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}
		}
	}
	//根据账号查询员工信息
	public int getLoginByUsername(int information){
		if(information ==2){
			System.out.println("请输入你要查询的账号(用户名)：");
		}else if(information ==3){
			System.out.println("请输入你要修改的账号(用户名)：");
		}else{
			System.out.println("请输入你要删除的账号(用户名)：");
		}
		String username = input.next();
		List<LoginEntity> list = lc.getLoginByUsername(username);
		if(list.size()!=0){
			System.out.println("ID\t账号\t编号");
			for(LoginEntity le:list){
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+le.getEmpid());
			}
			return 1;
		}else{
			System.out.println("未查询到信息");
			return 0;
		}
	}
	//根据ID修改信息
	public void updateLoginByEmpid1(int information){
		int flag = getLoginByUsername(information);
		if(flag == 0){
			return;
		}
		System.out.println("请选择以上查询出的信息当中您要修改的ID：");
		int id = input.nextInt();
		le = lc.getLoginById4(id);
		System.out.println("请输入修改后的账号：");
		String username = input.next();
		le.getUsername();
		int row = lc.updateLoginById2(id, username);
		if(row>0){
			System.out.println("修改成功。");
			getAllLogin();
		}else{
			System.out.println("修改失败。");
		}
	}
	//新增登录信息
	public void addLogin(){
		System.out.println("请选择你要添加的信息：");
		System.out.println("1、管理员");
		System.out.println("2、员工");
		String key = input.next();
		int row=0;
		String[] strs = key.split("//.");
		for(String str:strs){			
			if("1".equals(str)){
				System.out.println("请输入要添加的账号：");
				String username = input.next();
				le.setUsername(username);
				le.setAdmin(1);
				row = new LoginDao().addLoginAdmin(le);
			}
			if("2".equals(str)){
				System.out.println("请输入要添加的账号：");
				String username = input.next();
				System.out.println("请输入要添加的编号：");
				int empid = input.nextInt();
				le.setUsername(username);
				le.setAdmin(0);
				le.setEmpid(empid);
				row = new LoginDao().addLogin(le);
			}
		}
		if(row>0){
			System.out.println("添加成功。(默认密码是1234，请尽快修改你的密码!)");
		}else{
			System.out.println("添加失败。");
		}
	}
	//删除信息
	public void delLoginById3(int information){
		int flag = getLoginByUsername(information);
		if(flag == 0){
			return;
		}
		System.out.println("以上查询出的员工当中您要删除的员工ID：");
		boolean a = true;
		
		int id=0;
		while(a){
			try {
				id = input.nextInt();
				a = false;
			} catch (Exception e) {
				System.out.println("输入有误,请输入整数");
				input = new Scanner(System.in);
				a=true;
				continue;
			}
		}
		
		int row = lc.delLoginByEmpid3(id);
		if(row>0){
			System.out.println("删除成功。");
			getAllLogin();
		}else{
			System.out.println("删除失败。");
		}
	}
	
	
	//修改密码
	public void UpdateLoginPassword(int id){
		System.out.println("输入旧密码：");
		String oldpassword = input.next();
		if(oldpassword.equals(lc.getLoginById(id, oldpassword))){
			System.out.println("输入修改密码：");
			String password = input.next();
			le.setPassword(password);
			int row = lc.UpdateLoginPassword(password, id);
			if(oldpassword.equals(password)){
				System.out.println("新密码不能与旧密码一致");
			}else{
				if(row==1){
					System.out.println("执行完毕");
				}else{
					System.out.println("执行失败");
				}
			}
		}else{
			System.out.println("旧密码错误");
		}
	}
	
	//重置密码
	public void updateLoginById1(){
		getAllLogin();
		System.out.println("您要重置密码的ID是：");
		int id = input.nextInt();
		int row = lc.updateLoginById1(id);
		if(row>0){
			System.out.println("重置成功。");
			getAllLogin();
		}else{
			System.out.println("重置失败。");
		}
	}
}
