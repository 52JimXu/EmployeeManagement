package com.wwwxy.employeemanagement.dao;

import java.util.List;

import com.wwwxy.employeemanagement.entity.EmployeeEntity;
import com.wwwxy.employeemanagement.entity.EventEntity;


public class SalarySumDao {
	//更新工资
	public float SalarySum(int empid) {
		EventDao ed = new EventDao();
		List<EventEntity> list=ed.getAllEventEntity(empid);
		int award = 0;
		for(EventEntity list1:list){
			award=list1.geteAward();
		}
		EmployeeDao empdao = new EmployeeDao();
		EmployeeEntity empe= empdao.getEmployeeById(empid);
		float basic = empe.getEmpBasic();
		float salary =(float)award+basic;
		return salary;
	}
	public int GetEventIdByEmpId(int empid) {
		EventDao ed = new EventDao();
		List<EventEntity> list=ed.getAllEventEntity(empid);
		int eid = 0;
		for(EventEntity list1:list){
			eid=list1.geteId();
		}
		return eid;
	}
}
