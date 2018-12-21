package com.wwwxy.employeemanagement.control;

import java.util.List;

import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.entity.SalaryEntity;

public class SalaryControl {
	SalaryDao sd =new SalaryDao();
	
	
	
	//查询所有
	public List<SalaryEntity> GetAllSalary() {
		return sd.GetAllSalary();
	}
	//查询某个员工信息，使用员工ID查询
	public List<SalaryEntity> GetSalaryById(int id) {
		return sd.GetSalaryById(id);
	}
	
	
}
