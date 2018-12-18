package com.wwwxy.employeemanagement.test;
import java.sql.Timestamp;
import java.util.List;



import com.wwwxy.employeemanagement.dao.*;
import com.wwwxy.employeemanagement.entity.CheckDetails;
public class CheckDateilsTest {
public static void main(String[] args) {
	CheckDetailsDao cdd=new CheckDetailsDao(); 
	List<CheckDetails> list = cdd.getAllCheckDetails();
	for(CheckDetails cd1:list){
		System.out.println(cd1.getCdate());//查询
//	CheckDetails cd=new CheckDetails(); 
//	cd.setCid(0);
//	cd.setEmpid(103);
//	cd.setCcheckin(Timestamp.valueOf("2018-12-17 12:10:10"));
//	cd.setCcheckout(Timestamp.valueOf("2018-12-17 17:10:10"));
//	cd.setCstatus("正常");
//	cd.setCdate("2018-12-17");
//	cdd.addCheckDetails(cd);
	//新增
	}
}}

