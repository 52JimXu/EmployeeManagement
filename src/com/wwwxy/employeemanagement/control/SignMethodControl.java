package com.wwwxy.employeemanagement.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wwwxy.employeemanagement.dao.CheckDetailsDao;
import com.wwwxy.employeemanagement.entity.CheckTimeEntity;

public class SignMethodControl {
	public String CheckInThan(String checkin,String checkout) {
		String flag="";
		CheckDetailsDao cdd = new CheckDetailsDao();
		CheckTimeEntity cte = cdd.GetCheckTime();
		String ccheckin = cte.getcCheckin();
		String ccheckout = cte.getcCheckout();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			Date d1 = df.parse(ccheckin); //默认签到时间
			Date d2 = df.parse(ccheckout); //默认签退时间
			Date d3 = df.parse(checkin); //用户签到时间
			Date d4 = df.parse(checkout); //用户签退时间
			int t1 = (int) d1.getTime();//默认签到时间int类型
			int t2 = (int) d2.getTime();//默认签退时间int类型
			int t3 = (int) d3.getTime();//用户签到时间int类型
			int t4 = (int) d4.getTime();//用户签退时间int类型
			if(t3>t1){
				//迟到
				flag = "迟到";
			}
			if(t3>t1 && t4<t2){
				flag = "迟到,早退";
			}
			if(t4<t2){
				//早退
				flag = "早退";
			}
			if(t3>t1 && t4>(t2+360000)){
				flag = "迟到,加班";
			}
			if(t4>(t2+360000)){
				//加班
				flag += ",加班";
			}
			if(t1>=t3 && t4>=t2 && t4<(t2+360000)){
				flag = "正常";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
