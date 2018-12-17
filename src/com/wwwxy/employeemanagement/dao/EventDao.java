package com.wwwxy.employeemanagement.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
				e.seteBigevent(rs.getString("eBigevent"));
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
	public List<EventEntity> getEventById(int id){
		List<EventEntity> list = new ArrayList<EventEntity>();
		Connection con = this.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from event where eid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				EventEntity e = new EventEntity();
				e.seteId(rs.getInt("eId"));
				e.seteMpid(rs.getInt("eMpid"));
				e.seteClocking(rs.getInt("eClocking"));
				e.seteOvertime(rs.getInt("eOvertime"));
				e.seteBigevent(rs.getString("eBigevent"));
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
	public int addEventEntity(EventEntity t){
		int row = 0;
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "insert into event(empid,eclocking,eovertime,ebigevent,eaward) values(?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, t.geteMpid());
			ps.setInt(2,t.geteClocking());
			ps.setInt(3, t.geteOvertime());
			ps.setString(4, t.geteBigevent());
			ps.setInt(5, t.geteAward());
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
	public int updateEventEntityById(EventEntity t){
		int row = 0;
		PreparedStatement ps = null;
		Connection con = this.getConnection();
		String sql = "Update event set empid = ?,eclocking = ?,eovertime = ?,ebigevent = ?,eaward = ? where eid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, t.geteMpid());
			ps.setInt(2, t.geteClocking());
			ps.setInt(3, t.geteOvertime());
			ps.setString(4, t.geteBigevent());
			ps.setInt(5, t.geteAward());
			ps.setInt(6,t.geteId());
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
				e.seteBigevent(rs.getString("eBigevent"));
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
	
}
