package com.wwwxy.employeemanagement.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wwwxy.employeemanagement.entity.LoginEntity;
import com.wwwxy.employeemanagement.util.JDBCUtil;
public class LoginDao {
		//登录操作
		public boolean login(String Lusername,String Lpassword){
			LoginEntity le = new LoginEntity();
			JDBCUtil jdbc = new JDBCUtil(); 
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql ="select * from login where username =? and password = ?";
			try{
				ps = con.prepareStatement(sql);
				ps.setString(1, Lusername);
				ps.setString(2, Lpassword);
				rs = ps.executeQuery();
				if(rs.next()){
					return true;
				}else{
					return false;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		//查询Login表中所有信息
		public List<LoginEntity> getAllLogin(){
			List<LoginEntity> list = new ArrayList<LoginEntity>();
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql ="select * from login"; 
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					LoginEntity le = new LoginEntity();
					le.setId(rs.getInt("id"));
					le.setUsername(rs.getString("username"));
					le.setPassword(rs.getString("password"));
					le.setAdmin(rs.getInt("admin"));
					le.setEmpid(rs.getInt("empid"));
					list.add(le);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		//输入账号查询管理员和员工信息,模糊查询
		public List<LoginEntity> getLoginByUsername(String username){
			List<LoginEntity> list = new ArrayList<LoginEntity>();
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps =null;
			ResultSet rs = null;
			String sql = "select * from login where username like ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+username+"%");
				rs = ps.executeQuery();
				while(rs.next()){
					LoginEntity le = new LoginEntity();
					le.setId(rs.getInt("id"));
					le.setUsername(rs.getString("username"));
					le.setPassword(rs.getString("password"));
					le.setAdmin(rs.getInt("admin"));
					le.setEmpid(rs.getInt("empid"));
					list.add(le);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		//修改登录信息
		public int updateLoginByEmpid(LoginEntity le){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "update login set password = 1234 where empid =?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, le.getPassword());
				ps.setInt(2, le.getEmpid());
				row = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps,null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		//根据编号查找员工
		public LoginEntity getLoginByEmpid(int empid){
			LoginEntity le = new LoginEntity();
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from login where empid=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, empid);
				rs = ps.executeQuery();
				while(rs.next()){
					le.setId(rs.getInt("id"));
					le.setUsername(rs.getString("username"));
					le.setAdmin(rs.getInt("admin"));
					le.setEmpid(rs.getInt("empid"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return le;
		}
		//新增登录信息
		public int addLogin(LoginEntity le){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "insert into Login(username,empid) values(?,?)";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, le.getUsername());
				ps.setInt(2, le.getEmpid());
				row = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		//删除登录信息
		public int delLoginByempId(int Lempid){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "delete from Login where empid=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, Lempid);
				row = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
			return row;
		}
}
