package com.wwwxy.employeemanagement.control;
import java.util.*;
import com.wwwxy.employeemanagement.dao.CheckDetailsDao;
import com.wwwxy.employeemanagement.entity.CheckDetails;
public class CheckDetailsControl {
	CheckDetailsDao cdd=new CheckDetailsDao();
	//显示所有考勤信息
	public List<CheckDetails> getAllCheckDetails(){
		return cdd.getAllCheckDetails();
	}
	//输入员工ID查询考勤信息
		public List<CheckDetails> getCheckDetailsByempid(int empid){
			return cdd.getCheckDetailsByempid(empid);
		}
		//新增考勤信息
	public int addCheckDetails(CheckDetails cd){
		return cdd.addCheckDetails(cd);
	}
	//删除考勤信息
	public int delCheckDetailsBYCid(int cid){
		return cdd.delCheckDetailsBYCid(cid);
	}
	//修改考勤信息
	public int CheckDetailsByecid(CheckDetails cd){
		return cdd.updateCheckDetailsBYCid(cd);
	}
}
