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
		public int login(String username,String password){
			JDBCUtil jdbc = new JDBCUtil(); 
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			int row = 0;
			String sql ="select * from login where username =? and password = ?";
			try{
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				if(rs.next()){
					row = rs.getInt("id");
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
			return row;
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
		public int updateLoginById1(int id){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "update login set password = 1234 where id =?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
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
		//根据ID修改账号
		public int updateLoginById2(int id,String username){
			int row =0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "update login set username=? where id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setInt(2, id);
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
		//根据id查找账号
		public String getLoginById5(int id){
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
		//根据id查找empid
		public int getEmpidById(int id){
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			int empid = 0;
			String sql = "select empid from login where id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()){
					empid = rs.getInt("empid");
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
			return empid;
		}
		//根据ID查找该ID信息
		public LoginEntity getLoginById4(int id){
			LoginEntity le = new LoginEntity();
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from login where empid=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
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
			String sql = "insert into login(username,id,admin,empid) values(?,?,?,?)";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, le.getUsername());
				ps.setInt(2, le.getId());
				ps.setInt(3, le.getAdmin());
				ps.setInt(4, le.getEmpid());
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
		
		//新增管理员
		public int addLoginAdmin(LoginEntity le){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "insert into login(username,id,admin) values(?,?,?)";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, le.getUsername());
				ps.setInt(2, le.getId());
				ps.setInt(3, le.getAdmin());
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
		//根据ID删除信息
		public int delLoginById3(int id){
			int row = 0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			String sql = "delete from login where id=?";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
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
		//根据id查询admin的值
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
		//根据id查询密码
		public String getLoginById(int id,String oldpassword){
			String pwd = null;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select password from login where id=?";
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				rs= ps.executeQuery();
				if(rs.next()){
					pwd = rs.getString(1);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return pwd;
		}
		//修改密码
		public int UpdateLoginPassword(String password,int id){
			int row=0;
			JDBCUtil jdbc = new JDBCUtil();
			Connection con = jdbc.getConnection();
			PreparedStatement ps = null;
			try {
				String sql = "update login set password=? where id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1,password);
				ps.setInt(2, id);
				row = ps.executeUpdate();
			} catch (Exception e){
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
