package com.wwwxy.employeemanagement.test;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.entity.EventEntity;


//import com.wwwxy.employeemanagement.entity.EventEntity;

public class EventTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventDao eve = new EventDao();
		/*List<EventEntity> list = eve.getAllEvent();
		System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t加班次数\t\t工资情况");
		for(EventEntity e:list){
			System.out.println(e.geteId()+"\t\t"+e.geteMpid()+"\t\t"+e.geteClocking()+"\t\t"+e.geteBigevent()+"\t\t"+e.geteAward());
		}*/
		
		
		
			EventEntity ee = new EventEntity();
			/*int a=103;
			int b=4;
			int c=4;
			int d=4;
			int e=4;
			ee.seteClocking(b);
			ee.seteOvertime(c);
			ee.seteBigevent(d);
			ee.seteAward(e);
			ee.geteId();
			int row = eve.addEventEntity(ee);
			System.out.print(row);*/
		
		
		/*t.seteMpid(102);
		t.seteClocking(1);
		t.seteOvertime(1);
		t.seteBigevent("文文");
		t.seteAward(2);
		t.seteId(1);
		int row = eve.updateEventEntityById(t);
		System.out.print(row);*/
		/*EventEntity t1 = new EventEntity();
		t1.seteId(16);*/
		
		
		/*int row1 = eve.DropEventEntity(16);
		System.out.print(row1);*/
		
		/*List<EventEntity> list1 = eve.getAllEventEntity(103);
		System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t加班次数\t\t工资情况");
		for(EventEntity t1:list1){
			System.out.println(t1.geteId()+"\t\t"+t1.geteMpid()+"\t\t"+t1.geteClocking()+"\t\t"+t1.geteBigevent()+"\t\t"+t1.geteAward());
		}*/
			System.out.println("请稍后---");
		List<CheckDetails> list2 = eve.updateEventCheckdetails();
		String a = null;
		boolean flag = false;
		int c ;
		for(CheckDetails b:list2){
			int Clocking = 0;
			int Overtime = 0;
			int Bigevent = 0;
			int Award = 0;
			 a=b.getCstatus();
			 c=b.getEmpid();
			 for(CheckDetails f:list2){
					if(a !=null){
						//输入员工id修改事项信息
						List<EventEntity> list = eve.getEventById(c);
						for(EventEntity list1:list){
							//ee.seteMpid(list1.geteMpid());
							ee.seteClocking(list1.geteClocking());
							ee.seteOvertime(list1.geteOvertime());
							ee.seteBigevent(list1.geteBigevent());
							ee.seteAward(list1.geteAward());
						}
						//输入员工id获取员工各种事项次数
						List<EventEntity> list1 = eve.getAllEventEntity(c);
						for(EventEntity e:list1){
						Clocking = e.geteClocking();//迟到早退
						Bigevent = e.geteBigevent();//旷工
						Overtime = e.geteOvertime();//加班
						if(("早退".equals(a))||("迟到".equals(a)))
						{	 
							System.out.println("事项员工id"+c);
							Clocking++;
							//System.out.println(Clocking);
							ee.seteClocking(Clocking);
							break;
						}
						if("旷工".equals(a)){
							System.out.println("事项员工id"+c);
							Bigevent++;
							//System.out.println(Bigevent);
							ee.seteBigevent(Bigevent);
							break;
						}
						if("加班".equals(a)){
							System.out.println("事项员工id"+c);
							Overtime++;
							//System.out.println(Overtime);
							ee.seteOvertime(Overtime);
							break;
						}
						if("迟到,加班".equals(a)){
							System.out.println("事项员工id"+c);
							Clocking++;
							ee.seteClocking(Clocking);
							Overtime++;
							ee.seteOvertime(Overtime);
							break;	
						}
						}
							}
					}
			 Award = (Clocking*-50+Overtime*50+Bigevent*-100);
			 ee.seteAward(Award);
			 int row = eve.updateEventEntityById(ee);
					}
		List<EventEntity> list = eve.getAllEvent();
			System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t旷工\t\t加班次数\t\t工资情况");
			for(EventEntity e:list){
				System.out.println(e.geteId()+"\t\t"+e.geteMpid()+"\t\t"+e.geteClocking()+"\t\t"+e.geteBigevent()+"\t\t"+e.geteOvertime()+"\t\t"+e.geteAward());
			}
		}	
	}
