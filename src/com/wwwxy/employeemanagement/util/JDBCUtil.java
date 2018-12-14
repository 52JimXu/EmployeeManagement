package com.wwwxy.employeemanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	private Connection con;
	public Connection getConnection() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = 
            DriverManager.getConnection("jdbc:mysql://jimxu.top:3306/wwwxy", "wwwxy", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;

    }
	public void close(Connection con,PreparedStatement ps ps,ResultSet rs) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(ps != null){
			ps.close();
		}
		if(con != null){
			con.close();
		}
	}
}
