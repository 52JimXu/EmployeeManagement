package com.wwwxy.employeemanagement.ui;

import java.security.spec.ECField;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.EventControl;
import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.entity.EventEntity;

public class EventUi {
	EventControl ec = new EventControl();
	EventDao eve = new EventDao();
	EventEntity t = new EventEntity();
	Scanner input = new Scanner(System.in);
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		public void all(){
			Scanner input = new Scanner(System.in);
		String x="y";
		do{
		boolean flag = true;
		System.out.print("----------事项管理中心----------\n");
		System.out.print("请输入您要进行的操作(输入编号即可：)\n");
		System.out.print("1.查询所有员工事项\n");
		System.out.print("2.增加一条员工事项\n");
		System.out.print("3.删除一条员工事项\n");
		System.out.print("4.修改一条员工事项\n");
		System.out.print("5.输入员工工号查询员工事项\n");
		System.out.print("请输入编号：\n");
		//输入事项编号
		int a = input.nextInt();
		switch (a){
		case 1:
			getAllEvent();
			break;
		case 2:
			addEvent();
			break;
		case 3:
			getAllEvent();
			DropEvent();
			break;
		case 4:
			UpdateEvent();
			break;
		case 5:
			getEventbyeMpid();
			break;
		default:
			System.out.println("您输入方法不正确，请重新输入:");
			flag = false;
		}
		if(flag){
		System.out.println("是否继续请输入(y/n):");
		x = input.next();
		}
		}while(x.equals("y"));
		System.out.println("----------系统已退出----------");
	}
	//}
	//查看所有员工事项
	public void getAllEvent(){

		List<EventEntity> list = eve.getAllEvent();
		System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t加班次数\t\t矿工次数\t\t工资情况");
		for(EventEntity e:list){
			System.out.println(e.geteId()+"\t"
					+ "\t"+e.geteMpid()+"\t\t"+e.geteClocking()+"\t"
							+ "\t"+e.geteBigevent()+"\t"
									+ "\t"+e.geteOvertime()+"\t\t"+e.geteAward());
		}
	}
	//新增一条员工事项
	public void addEvent(){
		System.out.println("请输入员工编号：");
		int a= input.nextInt();
		
		System.out.println("请输入迟到或早退：");
		int b=input.nextInt();
		
		System.out.println("请输入员工加班次数：");
		int c=input.nextInt();
		
		System.out.println("请输入员工旷工次数：");
		String d=input.next();
		
		System.out.print("请输入员工工资情况：");
		int e=input.nextInt();
		
		t.seteMpid(a);
		t.seteClocking(b);
		t.seteOvertime(c);
		t.seteBigevent(d);
		t.seteAward(e);
		int row = eve.addEventEntity(t);
		if(row !=0){
		System.out.print("新增成功");
		}else{
			System.out.print("新增失败");
		}
	}
	//删除一条员工事项
	public void DropEvent(){
		System.out.println("请输入需要删除的事项编号：");
		int a = input.nextInt();
		int row = eve.DropEventEntity(a);
		if(row != 0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
	public void UpdateEvent(){
		//EventEntity t = eve.
		System.out.println("请输入你要修改的事项ID:");
		int id = input.nextInt();
		List<EventEntity> list = eve.getEventById(id);
		for(EventEntity list1:list){
			t.seteMpid(list1.geteMpid());
			t.seteClocking(list1.geteClocking());
			t.seteOvertime(list1.geteOvertime());
			t.seteBigevent(list1.geteBigevent());
			t.seteAward(list1.geteAward());
		}
		System.out.println("请选择您要修改的信息，用逗号隔开：");
		System.out.println("1、编号");
		System.out.println("2、迟到早退");
		System.out.println("3、加班");
		System.out.println("4、旷工");
		System.out.println("5、工资情况");
		String msg = input.next();
		String[] strs =msg.split(",");
		for(String str:strs){
			if("1".equals(str)){
				System.out.println("请输入修改后的员工编号：");
				int empid = input.nextInt();
				t.seteMpid(empid);
			}
			if("2".equals(str)){
				System.out.println("请输入修改后的员工迟到早退：");
				int b = input.nextInt();
				t.seteClocking(b);
			}
			if("3".equals(str)){
				System.out.println("请输入修改后的员工加班次数：");
				int c = input.nextInt();
				t.seteOvertime(c);
			}
			if("4".equals(str)){
				System.out.println("请输入修改后的员工旷工次数：");
				String d = input.next();
				t.seteBigevent(d);
			}
			if("5".equals(str)){
				System.out.println("请输入修改后的员工工资情况：");
				int e = input.nextInt();
				t.seteAward(e);
			}
		}
			t.seteId(id);
			int row = eve.updateEventEntityById(t);
			System.out.println(row);
			if(row>0){
				System.out.println("修改成功。");
				getAllEvent();
			}else{
				System.out.println("修改失败。");
			}
	}
	public void getEventbyeMpid(){
		System.out.println("请输入要查询员工工号：");
		int eMpid = input.nextInt();
		List<EventEntity> list = eve.getAllEventEntity(eMpid);
		System.out.println("事项排序\t\t员工编号\t\t迟到早退\t\t加班次数\t\t矿工次数\t\t工资情况");
		for(EventEntity e:list){
			System.out.println(e.geteId()+"\t\t"+e.geteMpid()+"\t\t"+e.geteClocking()+"\t\t"+e.geteBigevent()+"\t\t"+e.geteOvertime()+"\t\t"+e.geteAward());
		}
	}
}
