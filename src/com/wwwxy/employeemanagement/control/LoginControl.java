package com.wwwxy.employeemanagement.control;

import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.LoginDao;

public class LoginControl {
	public void login(){
		boolean flag = false;
		@SuppressWarnings("resource")
		Scanner input =new Scanner(System.in);
		int count =0;
		String username;
		String password;
		do{
			System.out.println("�������˺ţ�");
			username = input.next();
			System.out.println("���������룺");
			password = input.next();
			LoginDao ld = new LoginDao();
			flag = ld.login(username, password);
			if(flag){
				System.out.println("��ϲ�㣬��¼�ɹ�");
				break;
			}else{
				count++;
				System.out.println("��¼ʧ�ܣ�������"+(3-count)+"�λ��ᡣ");
			}
		}while(count<3);
	}

}
