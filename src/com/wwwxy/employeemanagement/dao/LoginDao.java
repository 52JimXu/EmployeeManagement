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
					// TODO Auto-generated catch block
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
					le.setId(rs.getInt("Lid"));
					le.setUsername(rs.getString("Lusername"));
					le.setPassword(rs.getString("Lpassword"));
					le.setAdmin(rs.getInt("Ladmin"));
					le.setEmpid(rs.getInt("Lempid"));
					list.add(le);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					jdbc.close(con, ps, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		}
		//员工，管理员输入姓名
}
