package com.wwwxy.employeemanagement.ui;

//import java.security.spec.ECField;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.EventControl;
import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.entity.EventEntity;

public class EventUi {
	EventControl ec = new EventControl();
	static EventDao eve = new EventDao();
	static EventEntity ee = new EventEntity();
	static Scanner input = new Scanner(System.in);
		// TODO Auto-generated method stub
		public void all(){
			Scanner input = new Scanner(System.in);
		String x = "y";
		do{
		boolean flag = true;
		System.out.print("----------事项管理中心----------\n");
		System.out.print("请输入您要进行的操作(输入编号即可：)\n");
		System.out.print("1.查询所有员工事项\n");
		System.out.print("2.增加一条员工事项\n");
		System.out.print("3.删除一条员工事项\n");
		//System.out.print("4.修改一条员工事项\n");
		System.out.print("4.输入员工工号查询员工事项\n");
		System.out.print("5.退出系统\n");
		System.out.print("请输入编号：\n");
		//输入事项编
		int a1 = input.nextInt();
		switch (a1){
		case 1:
			//getAllEvent2();
			getAllEvent();
			break;
		case 2:
			addEvent();
			break;
		case 3:
			getAllEvent();
			DropEvent();
			break;
		/*case 4:
			UpdateEvent();
			break;*/
		case 4:
			getEventbyeMpid();
			break;
		case 5:
			x = "n";
			flag = false;
			break;
		case 6:
			getAllEvent3();
			getAllEvent2();
			break;
		default:
			System.out.println("您输入方法不正确，请重新输入:");
			flag = false;
		}
		if(flag){
		System.out.println("是否继续请输入(y/n):");
		x = input.next();
		while(!x.equals("y")&&(!x.equals("n"))){
			System.out.println("输入有误请重新输入(y/n):");
			x = input.next();
		}
		}
		}while(x.equals("y"));
		System.out.println("----------系统已退出----------");
	}
	//查看所有员工事项
	public static void getAllEvent(){
		List<EventEntity> list = eve.getAllEvent();
			System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t加班次数\t\t旷工次数\t\t工资情况");
			for(EventEntity e:list){
				System.out.println(e.geteId()+"\t\t"+e.geteMpid()+"\t\t"+e.geteClocking()+"\t\t"+e.geteOvertime()+"\t\t"+e.geteBigevent()+"\t\t"+e.geteAward());
			}
			
	}
	//更新
	public static void getAllEvent3(){
		eve.UpdateEvent1();
	}
	public static void getAllEvent2(){
		System.out.println("更新中请稍后...");
		//eve.UpdateEvent1();
		int Clocking = 0;
		int Overtime = 0;
		int Bigevent = 0;
		int Award;
		String a = null;//设置一个判断标准
		int c ;
		//获取考勤表所有数据判断非正常员工事项及员工id
		List<CheckDetails> list2 = eve.updateEventCheckdetails();
		for(CheckDetails b:list2){
			//将考勤事件内容赋值给a
			 a=b.getCstatus();
			 //将考勤事件员工编号赋值给c
			 c=b.getEmpid();
			 //获取事项表所有数据
					if(a !=null){
						//输入id查询所有数据
						List<EventEntity> list1 = eve.getEventById(c);
						for(EventEntity list11:list1){
							ee.seteMpid(list11.geteMpid());
							ee.seteClocking(list11.geteClocking());
							ee.seteOvertime(list11.geteOvertime());
							ee.seteBigevent(list11.geteBigevent());
							ee.seteAward(list11.geteAward());
						}
						//输入员工id获取该员工迟到早退，加班次数，旷工次数
						List<EventEntity> list = eve.getAllEventEntity(c);
						for(EventEntity e:list){
						Clocking = e.geteClocking();//迟到早退
						Bigevent = e.geteBigevent();//旷工
						Overtime = e.geteOvertime();//加班
						if(("早退".equals(a))||("迟到".equals(a)))
						{	 
							Clocking++;
							ee.seteClocking(Clocking);
							break;
						}
						if("旷工".equals(a)){
							Bigevent++;
							ee.seteBigevent(Bigevent);
							break;
						}
						if("加班".equals(a)){
							Overtime++;
							ee.seteOvertime(Overtime);
							break;
						}
						if("迟到,加班".equals(a)){
							Clocking++;
							ee.seteClocking(Clocking);
							Overtime++;
							ee.seteOvertime(Overtime);
							break;	
						}
						int row = eve.updateEventEntityById(ee);
						}
							}
			Award = (Clocking*-50+Overtime*50+Bigevent*-100);
			ee.seteAward(Award);
			 int row = eve.updateEventEntityById(ee);
		}
		System.out.println("更新成功");
	}

	//新增一条员工事项
	public static void addEvent(){
		System.out.println("请输入员工编号：");
		int a= input.nextInt();
		
		System.out.println("请输入迟到或早退：");
		int b=input.nextInt();
		
		System.out.println("请输入员工加班次数：");
		int c=input.nextInt();
		
		System.out.println("请输入员工旷工次数：");
		int d=input.nextInt();
		
		System.out.print("请输入员工工资情况：");
		int e=input.nextInt();
		ee.seteMpid(a);
		ee.seteClocking(b);
		ee.seteOvertime(c);
		ee.seteBigevent(d);
		ee.seteAward(e);
		int row = eve.addEventEntity(ee);
		if(row !=0){
		System.out.print("新增成功");
		}else{
			System.out.print("新增失败");
		}
	}
	//删除一条员工事项
	public static void DropEvent(){
		System.out.println("请输入需要删除的事项编号：");
		int a = input.nextInt();
		int row = eve.DropEventEntity(a);
		if(row != 0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	//修改事项
	public static void UpdateEvent(){
		System.out.println("请输入你要修改的员工ID:");
		int empid = input.nextInt();
		List<EventEntity> list = eve.getEventById(empid);
		for(EventEntity list1:list){
			ee.seteMpid(list1.geteMpid());
			ee.seteClocking(list1.geteClocking());
			ee.seteOvertime(list1.geteOvertime());
			ee.seteBigevent(list1.geteBigevent());
			ee.seteAward(list1.geteAward());
		}
		System.out.println("请选择您要修改的信息，用逗号隔开：");
		System.out.println("1、迟到早退");
		System.out.println("2、旷工");
		System.out.println("3、加班");
		System.out.println("4、工资情况");
		String msg = input.next();
		String[] strs =msg.split(",");
		for(String str:strs){
			if("1".equals(str)){
				System.out.println("请输入修改后的员工迟到早退：");
				int b = input.nextInt();
				ee.seteClocking(b);
			}
			if("2".equals(str)){
				System.out.println("请输入修改后的员工加班次数：");
				int c = input.nextInt();
				ee.seteOvertime(c);
			}
			if("3".equals(str)){
				System.out.println("请输入修改后的员工旷工次数：");
				int d = input.nextInt();
				ee.seteBigevent(d);
			}
			if("4".equals(str)){
				System.out.println("请输入修改后的员工工资情况：");
				int e = input.nextInt();
				ee.seteAward(e);
			}
		}
			ee.seteId(empid);
			int row = eve.updateEventEntityById(ee);
			System.out.println(row);
			if(row>0){
				System.out.println("修改成功。");
				getAllEvent();
			}else{
				System.out.println("修改失败。");
			}
	}
	public static void getEventbyeMpid(){
		System.out.println("请输入要查询员工工号：");
		int eMpid = input.nextInt();
		List<EventEntity> list = eve.getAllEventEntity(eMpid);
		System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t加班次数\t\t矿工次数\t\t工资情况");
		for(EventEntity e:list){
			System.out.println(e.geteId()+"\t\t"+e.geteMpid()+"\t\t"+e.geteClocking()+"\t\t"+e.geteBigevent()+"\t\t"+e.geteOvertime()+"\t\t"+e.geteAward());
		}
	}
}
