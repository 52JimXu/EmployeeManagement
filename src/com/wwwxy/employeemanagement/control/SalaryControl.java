package com.wwwxy.employeemanagement.control;

import java.util.ArrayList;
import java.util.List;

import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.entity.SalaryEntity;

public class SalaryControl {
	public void GetAllSalary() {
		SalaryDao sd =new SalaryDao();
		List<SalaryEntity> list = sd.GetAllSalary();
		for(SalaryEntity list1:list){
			System.out.println(list1.geteId()+"\t"+list1.getEmpId()+"\t"
		+list1.getsId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
}
