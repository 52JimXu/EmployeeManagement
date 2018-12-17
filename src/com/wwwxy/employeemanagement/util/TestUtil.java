package com.wwwxy.employeemanagement.util;

import java.sql.Date;

public class TestUtil {
	public java.sql.Timestamp getTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
		}
}
