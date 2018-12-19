package com.wwwxy.employeemanagement.ui;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.entity.CheckTime;
public class SignMethodUi {
	 private  Map<Integer,CheckDetails> mapcd = new HashMap<>();
	    //Map中的KEY为签到的日期
	    private Map<String,List<CheckTime>> map = new HashMap<>();
	    //定义一个添加员工的方法
	    public Map<Integer,CheckDetails> addCheckDetails(int cid,int empid,Timestamp ccheckin,
	    		Timestamp ccheckout,String cstatus,String cdate){
	        mapcd.put(empid,new CheckDetails(cid,empid,ccheckin,ccheckout,cstatus,cdate));
	        return mapcd;
	    }
	  //显示现在公司员工的信息
	    public void showcd(){
	        for(Map.Entry<Integer,CheckDetails> m :mapcd.entrySet()){
	        System.out.println("考勤id:"+m.getValue().getCid()+"员工id:"+m.getValue().getEmpid()
	        		+"签到时间"+m.getValue().getCcheckin()+"签退时间"+m.getValue().getCcheckout()
	        		+"考勤状态"+m.getValue().getCstatus()+"考勤日期"+m.getValue().getCdate());
	        }
	    }
	    public void Signin(){
	    	//签到方法
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取年月日
	        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");//获取时分秒
	        String SignDate = sdf.format(date);
	        String CheckTime = sdf1.format(date);
	        System.out.println("请输入要签到的员工Id");
	        Scanner sc = new Scanner(System.in);
	        int inputempid = sc.nextInt();
	        if(!mapcd.containsKey(inputempid)){
	            System.out.println("不存在该员工");
	            return;
	        }
	        List<CheckTime> listCheck = null;
	        if(!map.containsKey(SignDate)){
	            //第一个签到的员工
	            listCheck = new ArrayList<>();
	            CheckTime ct = new CheckTime();
	           ct.setEmpid(mapcd.get(inputempid).getEmpid());
	           ct.setCcheckin(CheckTime);
	            ct.setCcheckout("");
	            listCheck.add(ct);
	            map.put(SignDate,listCheck);
	            System.out.println("签到成功!");
	        }
	        else {//说明之前,今天已经有人签到过了
	            listCheck = map.get(SignDate);
	            //判断是否已经签到过
	            boolean Find = false;
	            for (CheckTime ct : listCheck) {
	                if (ct.getEmpid() == mapcd.get(inputempid).getEmpid()) {
	                    System.out.println("已经签到过了");
	                    Find = true;
	                    break;
	                }
	            }
	            if (!Find) {
	                CheckTime ct = new CheckTime();
	                ct.setEmpid(mapcd.get(inputempid).getEmpid());
	                ct.setCcheckin(CheckTime);
	                ct.setCcheckout("");
	                listCheck.add(ct);
	                System.out.println("签到成功!");
	            }
	        }
	    }
	    public void Signout(){
	    	//签退方法
	    	 System.out.println("请输入签退员工ID");
	         Scanner sc = new Scanner(System.in);
	         int inputId = sc.nextInt();
	         String s = sc.nextLine();
	         if(!mapcd.containsKey(inputId)){
	             System.out.println("员工不存在");
	             return;
	         }
	         Date date = new Date();
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取年月日
	         SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");//获取时分秒
	         String SignDate = sdf.format(date);
	         String CheckTime = sdf1.format(date);
	         if(!map.containsKey(SignDate)){
	             System.out.println("您没签到,不能签退!");
	    }
	         else{
	             List<CheckTime> listCheck = map.get(SignDate);
	             boolean Find = false;
	             for(CheckTime ct : listCheck){
	                 if(ct.getEmpid()==mapcd.get(inputId).getEmpid()){
	                     ct.setCcheckout(CheckTime);
	                     Find = true;
	                     break;
	                 }
	             }
	             if(!Find){
	                 System.out.println("您没签到,不能签退");
	             }else{
	                 System.out.println("签退成功!");
	             }
	      }
	    }
	    public String Time(){
	    	//判断员工是否早退，迟到，正常，加班，旷工
	    	String state="";
	    	String state1="正常";
	    	String state2="正常";
	    	String state3="正常";
	    	DateFormat df=new SimpleDateFormat("HH:mm:ss");//获取时分秒
	    	Date d=new Date();
	    	try {
				Date d1=df.parse("14:00:00");//规定签到时间
				Date d2=df.parse(df.format(d));//员工签到，签退时间
				Date d3=df.parse("20:00:00");//规定签退时间
				int t1=(int) d1.getTime();
				int t2=(int) d2.getTime();
				int t3=(int) d3.getTime();
				if (t2>t1) {
					//迟到
					long between=(t2-t1)/1000;//转化成秒
					long hour=between%(24*3600)/3600;//转化为小时
					long minute=between%3600/60;//转化为分
					state1="迟到："+hour+"时"+minute+"分"+between+"秒"+"\t";
				}
				if (t2<t3) {
					//早退
					long between=(t2-t1)/1000;//转化成秒
					long hour=between%(24*3600)/3600;//转化为小时
					long minute=between%3600/60;//转化为分
					state2="早退："+hour+"时"+minute+"分"+between+"秒"+"\t";
				}
				if (t2>t3) {
					//加班
					long between=(t2-t1)/1000;//转化成秒
					long hour=between%(24*3600)/3600;//转化为小时
					long minute=between%3600/60;//转化为分
					state2="加班："+hour+"时"+minute+"分"+between+"秒"+"\t";
				}
			   
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	return state;
	    }
	    public void Look(){
	    	 System.out.println("请输入要查询的日期（yyyy-MM-dd）");
	         Scanner sc = new Scanner(System.in);
	         String SignDate = sc.nextLine();
	         List<CheckTime> listCheck = map.get(SignDate);
	         for(CheckTime ct : listCheck){
	             System.out.println(ct.getEmpid()+" 签到时间为:"+ct.getCcheckin()+
	            		 " 签退时间为:"+ct.getCcheckout());
	                String s = Time();
					System.out.println(s);
	    }
	         
	    }}

