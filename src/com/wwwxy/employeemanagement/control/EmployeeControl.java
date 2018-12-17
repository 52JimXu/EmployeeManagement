package com.wwwxy.employeemanagement.control;

import java.util.List;

import com.wwwxy.employeemanagement.dao.EmployeeDao;
import com.wwwxy.employeemanagement.entity.EmployeeEntity;

public class EmployeeControl {
	EmployeeDao ed = new EmployeeDao();
	
	//�鿴Ա����Ϣ
	public List<EmployeeEntity> getAllEmployee(){
		return ed.getAllEmployee();
	}
	//����������ѯԱ����Ϣ��ģ����ѯ
	public List<EmployeeEntity> getEmployeeByName(String EmpName){
		return ed.getEmployeeByName(EmpName);
	}
	//�޸�Ա����Ϣ
	public void updateEmployee(EmployeeEntity ee){
		ed.updateEmployee(ee);
	}
	//���ݹ��Ų�ѯԱ��
	public EmployeeEntity getEmployeeById(int EmpId){
		return ed.getEmployeeById(EmpId);
	}
	//����Ա��
	public void AddEmployee(EmployeeEntity ee){
		ed.AddEmployee(ee);
	}
	//ɾ��Ա��
	public void DeleteEmployee(int EmpId){
		ed.DeleteEmployee(EmpId);
	}
}
