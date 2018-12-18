package com.wwwxy.employeemanagement.dao;
import java.sql.*;
import java.util.*;

import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.util.JDBCUtil;
public class CheckDetailsDao extends JDBCUtil{
	//查询所有考勤信息
	public List<CheckDetails> getAllCheckDetails(){
		List<CheckDetails> list=new ArrayList<CheckDetails>();
		Connection con = this.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from checkdetails";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				CheckDetails cd=new CheckDetails();
				cd.setCid(rs.getInt("cid"));
				cd.setEmpid(rs.getInt("empid"));
				cd.setCcheckin(rs.getTimestamp("ccheckin"));
				cd.setCcheckout(rs.getTimestamp("ccheckout"));
				cd.setCstatus(rs.getString("cstatus"));
				cd.setCdate(rs.getString("cdate"));
				list.add(cd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, rs);
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
}
	//新增考勤信息
	public int addCheckDetails(CheckDetails cd){
		int row=0;
		Connection con=this.getConnection();
		PreparedStatement ps=null;
		String sql="insert into checkdetails"
				+ "(cid,empid,ccheckin,ccheckout,cstatus,cdate) values(?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1,cd.getCid());
			ps.setInt(2,cd.getEmpid());
			ps.setTimestamp(3,cd.getCcheckin());
			ps.setTimestamp(4,cd.getCcheckout());
			ps.setString(5,cd.getCstatus());
			ps.setString(6,cd.getCdate());
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, null);
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return row;
	}
	//删除考勤信息
	public int delCheckDetailsBYCid(int cid){
		int row=0;
		Connection con=this.getConnection();
		PreparedStatement ps=null;
		String sql="delete from checkdetails where cid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1,cid);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, null);
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return row;
	}
	//输入员工ID查询信息
		public List<CheckDetails> getCheckDetailsByempid(int empid){
			List<CheckDetails> list = new ArrayList<CheckDetails>();
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from checkdetails where empid = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, empid);
				rs = ps.executeQuery();
				while(rs.next()){
					CheckDetails cd = new CheckDetails();
					cd.setCid(rs.getInt("cid"));
					cd.setEmpid(rs.getInt("empid"));
					cd.setCcheckin(rs.getTimestamp("ccheckin"));
					cd.setCcheckin(rs.getTimestamp("ccheckout"));
					cd.setCstatus(rs.getString("cstatus"));
					cd.setCdate(rs.getString("cdate"));
					list.add(cd);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					this.close(con, ps, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		}
	//修改考勤信息
	public int updateCheckDetailsBYCid(CheckDetails cd){
		int row = 0;
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		String sql = "update checkdetails set empid=?,ccheckin=?,"
				+ ",cstatus=?,cdate=? where cid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, cd.getCid());
			ps.setInt(2, cd.getEmpid());
			ps.setTimestamp(3, cd.getCcheckin());
			ps.setTimestamp(4, cd.getCcheckout());
			ps.setString(5,cd.getCstatus());
			ps.setString(6, cd.getCdate());
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.close(con, ps, null);
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return row;
	}
}