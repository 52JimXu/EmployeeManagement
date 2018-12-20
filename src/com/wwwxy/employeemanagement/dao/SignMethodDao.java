package com.wwwxy.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.wwwxy.employeemanagement.entity.EventEntity;
import com.wwwxy.employeemanagement.util.JDBCUtil;


public class SignMethodDao {
	//签退后通过empid修改事项记录
	public void UpdateEventByEmpid(int empid,int cid) {
		int a=0; //迟到次数
		int b=0; //早退次数
		int c=0; //加班次数
		int d=0; //旷工次数
		String status = "";
		EventDao ed = new EventDao();
		CheckDetailsDao cdd = new CheckDetailsDao();
		if(cid == 0){
			status = cdd.GetCheckStatus();
		}else{
			status = cdd.GetCheckStatusByCid(cid);
		}
		System.out.println(status);
		String[] strs = status.split(",");
		for(String str:strs){
			if("迟到".equals(str)){
				a++;
			}
			if("早退".equals(str)){
				b++;
			}
			if("加班".equals(str)){
				c++;
			}
			if("旷工".equals(str)){
				d++;
			}
			if(cid != 0 ){
				if("正常".equals(str)){
					a--;
				}
			}
		}
		updateEventByEmpId(empid, a, b, c, d);
		System.out.println("更新事项记录成功");
		float salary = new SalarySumDao().SalarySum(empid);
		new SalaryDao().UpdateSalaryByEmpId(empid, salary);
		System.out.println("更新工资记录成功");
	}
	public int updateEventByEmpId(int empid,int a,int b,int c,int d){
		int eaward =0;
		int row = 0;
		PreparedStatement ps = null;
		Connection con = new JDBCUtil().getConnection();
		EventDao eDao = new EventDao();
		String sql = "Update event set eclocking = ?,eovertime = ?,ebigevent = ?,eaward = ? where empid = ?";
		try {
			ps = con.prepareStatement(sql);
			List<EventEntity> list = eDao.getEventById(empid);
			for(EventEntity lists:list){
				eaward = lists.geteAward();
				a = a+b+ lists.geteClocking();
				c = lists.geteOvertime();
				d = lists.geteBigevent();
			}
			ps.setInt(1, a);
			ps.setInt(2, c);
			ps.setInt(3, d);
			eaward = (c*50)-(a*50)-(d*100);  //更新工资奖惩   工资 = 加班*50 - 迟到早退*50-旷工 *100

			ps.setInt(4, eaward);
			ps.setInt(5,empid);
			row = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return row;
	}
}
