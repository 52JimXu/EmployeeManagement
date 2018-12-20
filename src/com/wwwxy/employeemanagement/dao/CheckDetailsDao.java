package com.wwwxy.employeemanagement.dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.wwwxy.employeemanagement.control.SignMethodControl;
import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.entity.CheckTimeEntity;
import com.wwwxy.employeemanagement.ui.SignMethodUi;
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
				cd.setCcheckin(rs.getString("ccheckin"));
				cd.setCcheckout(rs.getString("ccheckout"));
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
			ps.setString(3,cd.getCcheckin());
			ps.setString(4,cd.getCcheckout());
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
			SignMethodUi smu=new SignMethodUi();
			
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, empid);
				rs = ps.executeQuery();
				while(rs.next()){
					CheckDetails cd = new CheckDetails();
					cd.setCid(rs.getInt("cid"));
					cd.setEmpid(rs.getInt("empid"));
					cd.setCcheckin(rs.getString("ccheckin"));
					cd.setCcheckout(rs.getString("ccheckout"));
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
			ps.setString(3, cd.getCcheckin());
			ps.setString(4, cd.getCcheckout());
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
	//签到
	public int checkin(int empid) {
		int row = 0;
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into checkdetails (empid,ccheckin,cstatus,cdate) values(?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowtime = df.format(new Date());
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, empid);
			ps.setString(2, nowtime);
			ps.setString(3, "未签退");
			ps.setString(4, df1.format(new Date()));
			row = ps.executeUpdate();
		} catch (SQLException e) {
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
	
	//查看是否签过到
	public int GetCheckin(int empid) {
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select empid from checkdetails where cstatus=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "未签退");
			rs = ps.executeQuery();
			while(rs.next()){
				if(empid == rs.getInt("empid")){
					return 1;  //已经签过到了
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;//未签到
		
	}
	//通过empid查看未签退的签到时间
	public String GetCheckinTimeByEmpId(int empid) {
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String ccheckin = null;
		String sql = "select ccheckin from checkdetails where cstatus=? and empid=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "未签退");
			ps.setInt(2, empid);
			rs = ps.executeQuery();
			if(rs.next()){
				ccheckin = rs.getString("ccheckin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return ccheckin;
	
	}
	
	
	//签退
	public int checkout(int empid) {
		int row = 0;
		
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		String sql = "update checkdetails set ccheckout =?,cstatus=? where empid = ? and cstatus=?";
		try {
			ps=con.prepareStatement(sql);
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			String nowtime =df.format(new Date());
			String ccheckin = GetCheckinTimeByEmpId(empid);
			String flag = new SignMethodControl().CheckInThan(ccheckin, nowtime);
			
			ps.setString(1, nowtime);
			ps.setString(2, flag);

			ps.setInt(3, empid);
			ps.setString(4, "未签退");
			row = ps.executeUpdate();
		} catch (SQLException e) {
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
	//通过empid查询上一次签到的考勤状态
	public String GetCheckStatus() {
		String status="";
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select cstatus from checkdetails where cid = (select max(cid) from checkdetails)";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				status = rs.getString("cstatus");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
		
	}
	
	// 修改时通过cid获取考勤信息
	public String GetCheckStatusByCid(int cid) {
		String status="";
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select cstatus from checkdetails where cid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			if(rs.next()){
				status = rs.getString("cstatus");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
		
	}
	
	//获取默认签到时间
	public CheckTimeEntity GetCheckTime() {
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		CheckTimeEntity cte = new CheckTimeEntity();
		String sql = "select ccheckin,ccheckout from checktime where id=1";
		try {
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				cte.setcCheckin(rs.getString("ccheckin"));
				cte.setcCheckout(rs.getString("ccheckout"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				new JDBCUtil().close(con, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cte;
	}
	
	//通过cid修改考勤状态
	public int UpdateStatusById(int cid,String flag) {
		int row = 0;
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		String sql = "update checkdetails set cstatus=? where cid = ?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, flag);
			ps.setInt(2, cid);
			row = ps.executeUpdate();
		} catch (SQLException e) {
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
	//通过cid获取empid
		public int GetEmpidByCid(int cid) {
			int empid = 0;
			Connection con = this.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select empid from checkdetails where cid = ?";
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, cid);
				rs = ps.executeQuery();
				if(rs.next()){
					empid = rs.getInt("empid");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					new JDBCUtil().close(con, ps, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return empid;
		}
	
	
	
}