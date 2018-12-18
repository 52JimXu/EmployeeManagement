package com.wwwxy.employeemanagement.test;

import java.util.List;

import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.entity.LoginEntity;




public class LoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginDao ld = new LoginDao();
		List<LoginEntity> list1 =ld.getAllLogin();
		for(LoginEntity le:list1){
			System.out.println(le.getUsername());
		}
	}

}
