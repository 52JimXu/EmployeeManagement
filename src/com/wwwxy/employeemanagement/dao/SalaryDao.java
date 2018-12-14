package com.wwwxy.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwwxy.employeemanagement.entity.SalaryEntity;
import com.wwwxy.employeemanagement.util.JDBCUtil;

public class SalaryDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public List<SalaryEntity> GetAllSalary() {
		JDBCUtil jdbc = new JDBCUtil();
		con = jdbc.getConnection();
		try {
			ps = con.prepareStatement("select * from salary");
			rs = ps.executeQuery();
			List<SalaryEntity> list = new ArrayList<SalaryEntity>();
			while(rs.next()){
				SalaryEntity se = new SalaryEntity();
				se.seteId(rs.getInt("eid"));
				se.setEmpId(rs.getInt("empid"));
				se.setsId(rs.getInt("sid"));
				se.setsSum(rs.getInt("ssum"));
				se.setsTime(rs.getDate("stime"));
				list.add(se);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("sql´íÎó");
			return null;
		}finally{
			try {
				jdbc.close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
