package com.wwwxy.employeemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.wwwxy.employeemanagement.entity.SalaryEntity;
import com.wwwxy.employeemanagement.util.JDBCUtil;

public class SalaryDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	//查询所有
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
				se.setsTime(rs.getString("stime"));
				list.add(se);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("sql错误");
			return null;
		}finally{
			try {
				jdbc.close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//增加
	public int add(SalaryEntity se){
		String sql = "insert into salary(eid,empid,ssum,stime)"
				+ " values(?,?,?,?)";
		int row = 0;
		SalarySumDao ssd = new SalarySumDao();
		float salary = ssd.SalarySum(se.getEmpId());
		try{
			con = new JDBCUtil().getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1,se.geteId());
			ps.setInt(2,se.getEmpId());
			ps.setFloat(3,salary);
			ps.setString(4,se.getsTime().toString());
			
			row = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				new JDBCUtil().close(con, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//删除
	public int delete(int id){
		String sql = "delete from salary where sid = "+id;
		Connection con = null;
		PreparedStatement ps = null;
		int row = 0;
		try{
			con = new JDBCUtil().getConnection();
			ps = con.prepareStatement(sql);
			
			row = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return row;
		
	}
	//更新by eID
	public int update (SalaryEntity se){
		String sql = "update salary set eid=?,"
				+ "empid=?,ssum=?,stime=? where sid =?";
		Connection con = null;
		PreparedStatement ps = null;
		int row=0;
		try{
			con = new JDBCUtil().getConnection();
			ps = con.prepareStatement(sql);
			
			
			ps.setInt(1,se.geteId());
			ps.setInt(2,se.getEmpId());
			ps.setFloat(3,se.getsSum());
			ps.setString(4,se.getsTime());
			ps.setInt(5, se.getsId());
			
			row = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	//通过员工id更新工资总和
	public int UpdateSalaryByEmpId(int empid,float ssum) {
		String sql = "update salary set ssum=? where empid =?";
		Connection con = null;
		PreparedStatement ps = null;
		int row=0;
		try{
			con = new JDBCUtil().getConnection();
			ps = con.prepareStatement(sql);
			
			
			ps.setFloat(1,ssum);
			ps.setInt(2,empid);
			
			row = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	
	}
	
	//查询by eid
	public List<SalaryEntity> GetSalaryById(int id) {
		JDBCUtil jdbc = new JDBCUtil();
		con = jdbc.getConnection();
		try {
			ps = con.prepareStatement("select * from salary where sid=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List<SalaryEntity> list = new ArrayList<SalaryEntity>();
			while(rs.next()){
				SalaryEntity se = new SalaryEntity();
				se.seteId(rs.getInt("eid"));
				se.setEmpId(rs.getInt("empid"));
				se.setsId(rs.getInt("sid"));
				se.setsSum(rs.getInt("ssum"));
				se.setsTime(rs.getString("stime"));
				list.add(se);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("sql错误");
			return null;
		}finally{
			try {
				jdbc.close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//查询by empid
		public List<SalaryEntity> GetSalaryByEmpId(int id) {
			JDBCUtil jdbc = new JDBCUtil();
			con = jdbc.getConnection();
			try {
				ps = con.prepareStatement("select * from salary where empid=?");
				ps.setInt(1, id);
				rs = ps.executeQuery();
				List<SalaryEntity> list = new ArrayList<SalaryEntity>();
				while(rs.next()){
					SalaryEntity se = new SalaryEntity();
					se.seteId(rs.getInt("eid"));
					se.setEmpId(rs.getInt("empid"));
					se.setsId(rs.getInt("sid"));
					se.setsSum(rs.getInt("ssum"));
					se.setsTime(rs.getString("stime"));
					list.add(se);
				}
				return list;
			} catch (SQLException e) {
				System.out.println("sql错误");
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
