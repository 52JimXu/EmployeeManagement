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
		public int login(String Lusername,String Lpassword){
			LoginEntity le = new LoginEntity();
			JDBCUtil jdbc = new JDBCUtil(); 
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			int id = 0;
			String sql ="select * from login where username =? and password = ?";
			try{
				ps = con.prepareStatement(sql);
				ps.setString(1, Lusername);
				ps.setString(2, Lpassword);
				rs = ps.executeQuery();
				if(rs.next()){
					id = rs.getInt("id");
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
			return id;
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
		//重置密码
		public int updateLoginByEmpid(int empid){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "update login set password = 1234 where empid =?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, empid);
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
		//根据编号修改账号
		public int updateLoginByEmpid1(int empid,String username){
			int row =0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "update login set username=? where empid=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setInt(2, empid);
				
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
		//根据id查找用户名
		public String getLoginById(int id){
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String username = null;
			String sql = "select username from login where id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()){
					username = rs.getString("username");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return username;
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
		//新增信息
		public int addLogin(LoginEntity le){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "insert into login(username,empid) values(?,?)";
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
		//根据编号删除信息
		public int delLoginByempId(int empid){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "delete from login where empid=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, empid);
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
		//根据id获取admin的值
		public int getAdminById(int id){
			int admin =0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select admin from login where id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()){
					admin = rs.getInt("admin");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return admin;
		}
}
