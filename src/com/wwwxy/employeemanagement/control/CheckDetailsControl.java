package com.wwwxy.employeemanagement.control;
import java.util.*;
import com.wwwxy.employeemanagement.dao.CheckDetailsDao;
import com.wwwxy.employeemanagement.entity.CheckDetails;
public class CheckDetailsControl {
	CheckDetailsDao cdd=new CheckDetailsDao();
	//��ʾ���п�����Ϣ
	public List<CheckDetails> getAllCheckDetails(){
		return cdd.getAllCheckDetails();
	}
	//����Ա��ID��ѯ������Ϣ
		public List<CheckDetails> getCheckDetailsByempid(int empid){
			return cdd.getCheckDetailsByempid(empid);
		}
		//����������Ϣ
	public int addCheckDetails(CheckDetails cd){
		return cdd.addCheckDetails(cd);
	}
	//ɾ��������Ϣ
	public int delCheckDetailsBYCid(int cid){
		return cdd.delCheckDetailsBYCid(cid);
	}
	//�޸Ŀ�����Ϣ
	public int CheckDetailsByecid(CheckDetails cd){
		return cdd.updateCheckDetailsBYCid(cd);
	}
}
