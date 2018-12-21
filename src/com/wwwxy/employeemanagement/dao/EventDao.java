package com.wwwxy.employeemanagement.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwwxy.employeemanagement.control.CheckDetailsControl;
import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.entity.EventEntity;
import com.wwwxy.employeemanagement.util.JDBCUtil;

public class EventDao extends JDBCUtil {
	//查看所有事项
	public List<EventEntity> getAllEvent(){
		List<EventEntity> list = new ArrayList<EventEntity>();
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from event";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				EventEntity e = new EventEntity();
				e.seteId(rs.getInt("eId"));
				e.seteMpid(rs.getInt("eMpid"));
				e.seteClocking(rs.getInt("eClocking"));
				e.seteOvertime(rs.getInt("eOvertime"));
				e.seteBigevent(rs.getInt("eBigevent"));
				e.seteAward(rs.getInt("eAward"));
				list.add(e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	//输入员工id查询该员工的事项
	public List<EventEntity> getEventById(int eMpid){
		List<EventEntity> list = new ArrayList<EventEntity>();
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from event where empid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, eMpid);
			rs = ps.executeQuery();
			while(rs.next()){
				EventEntity e = new EventEntity();
				e.seteId(rs.getInt("eId"));
				e.seteMpid(rs.getInt("eMpid"));
				e.seteClocking(rs.getInt("eClocking"));
				e.seteOvertime(rs.getInt("eOvertime"));
				e.seteBigevent(rs.getInt("eBigevent"));
				e.seteAward(rs.getInt("eAward"));
				list.add(e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	//新增事项
	public int addEventEntity(EventEntity e){
		int row = 0;
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "insert into event(empid,eclocking,eovertime,ebigevent,eaward) values(?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, e.geteMpid());
			ps.setInt(2,e.geteClocking());
			ps.setInt(3, e.geteOvertime());
			ps.setInt(4, e.geteBigevent());
			ps.setInt(5, e.geteAward());
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
	
	
	
	
	
	
	
	
	//修改事项
	public int updateEventEntityById(EventEntity e){
		int row = 0;
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "Update event set eclocking = ?,eovertime = ?,ebigevent = ?,eaward = ? where empid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, e.geteClocking());
			ps.setInt(2, e.geteOvertime());
			ps.setInt(3, e.geteBigevent());
			ps.setInt(4, e.geteAward());
			ps.setInt(5,e.geteMpid());
			row = ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return row;
	}
	
	
	
	
	
	
	
	
	
	
	//删除事项
	public int DropEventEntity(int eId){
		int row = 0;
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "delete from event where eid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, eId);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	
	
	
	//通过empid删除事项
	public void DropEventEntityByempid(int empid){
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "delete from event where empid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, empid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new SalaryDao().deleteByempid(empid);
		
	}
	
	
	//输入员工id查询事项
	public List<EventEntity> getAllEventEntity(int eMpid){
		List<EventEntity> list1 = new ArrayList<EventEntity>();
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from event where empid = ?";
		EventEntity t= new EventEntity();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, eMpid);
			rs = ps.executeQuery();
			while(rs.next()){
				EventEntity e = new EventEntity();
				e.seteId(rs.getInt("eId"));
				e.seteMpid(rs.getInt("eMpid"));
				e.seteClocking(rs.getInt("eClocking"));
				e.seteOvertime(rs.getInt("eOvertime"));
				e.seteBigevent(rs.getInt("eBigevent"));
				e.seteAward(rs.getInt("eAward"));
				list1.add(e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list1;
		
	}
	//查询考勤表中非正常员工id及非正常事项
	public List<CheckDetails> updateEventCheckdetails(){
		List<CheckDetails> list = new ArrayList<CheckDetails>();
		//int row = 0;
		PreparedStatement ps = null;
		Connection conn = this.getConnection();
		ResultSet rs = null;
		//String sql = "update event a,checkdetails b set a.ebigevent = b.cstatus where a.empid = b.empid";
		String sql = "select empid,cstatus from checkdetails where cstatus !='正常' ";
		try {
			ps= conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				CheckDetails cdd = new CheckDetails();
				cdd.setEmpid(rs.getInt("empid"));
				cdd.setCstatus(rs.getString("cstatus"));
				list.add(cdd);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	//修改表指定字段数据
//	public List<EventEntity> UpdateEvent1(){
	public int UpdateEvent1(){
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		
		String sql = "update event set eclocking = 0,eovertime = 0,ebigevent = 0,eaward = 0";
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	//新增员工时增加一条事项记录
	public void AddEventAfterAddEmp(int empid) {
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "insert into event(empid,eclocking,eovertime,ebigevent,eaward) values(?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, empid);
			ps.setInt(2,0);
			ps.setInt(3,0);
			ps.setInt(4,0);
			ps.setInt(5,0);
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		new SalaryDao().AddSalaryAfterAddEvent(empid);
	}
	
	
	
}
