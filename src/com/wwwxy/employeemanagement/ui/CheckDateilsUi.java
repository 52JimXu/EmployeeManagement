package com.wwwxy.employeemanagement.ui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

import com.wwwxy.employeemanagement.control.CheckDetailsControl;
import com.wwwxy.employeemanagement.dao.*;
import com.wwwxy.employeemanagement.entity.CheckDetails;
public class CheckDateilsUi {
	SignMethodUi smu=new SignMethodUi();
	Scanner input=new Scanner(System.in);
	CheckDetailsControl cdc=new CheckDetailsControl();
	CheckDetailsDao cdd=new CheckDetailsDao();

	public void all(){
		String f = "y";
		do{
			System.out.println("1、显示所有考勤信息");
			System.out.println("2、输入员工ID查询考勤信息");
			System.out.println("3、修改考勤状态信息");
			System.out.println("4、删除考勤信息");
			System.out.println("5、返回上一级");
			System.out.println("请输入您要进行的管理操作：");
			int action=0;
			try {
				action=input.nextInt();
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入");
				input = new Scanner(System.in);
				if(action!=5){
					System.out.println("是否继续（y/n）：");
					f = input.next();}
				continue;
			}
			switch(action){
			case 1:
				getAllCheckDateils();
				break;
			case 2:
 			    getCheckDateilsByempid(action);
				break;
			case 3:
				updateCheckDetailsBYCid();
				break;
			case 4:
				delCheckDetailsBycid(action);
				break;
			case 5:
				f="n";
				break;
			default:
				System.out.println("输入有误。");
			}
			if(action!=5){
				System.out.println("是否继续（y/n）：");
				f = input.next();
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("输入有误，请输入y或者n");
					f = input.next();
				}
			}
		}while("y".equals(f));
	}
	//显示所有考勤信息
		public void getAllCheckDateils(){
			List<CheckDetails> list = cdc.getAllCheckDetails();
			System.out.println("考勤id\t员工id\t 签到时间\t\t签退时间\t\t考勤状态\t考勤日期\t");
			for(CheckDetails cd:list){
				System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
					+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
			}
		}
		//输入员工id查询考勤信息
		public void getCheckDateilsByempid(int action){
			if(action==2){
				System.out.println("请输入您要查询的考勤员工ID：");
			}else if(action==3){
				System.out.println("请输入您要修改的考勤员工ID：");
			}else{
				System.out.println("请输入您要删除的考勤员工ID：");
			}
			int empid = input.nextInt();
			List<CheckDetails> list = cdc.getCheckDetailsByempid(empid);
			if(list.size()!=0){
				System.out.println("考勤id\t员工id签到时间\t\t\t签退时间\t\t考勤状态\t考勤日期\t");
				for(CheckDetails cd:list){
					System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
							+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
				}
			}else{
				System.out.println("未查询到相应信息。");
			}		
		}		
//删除考勤信息
	public void delCheckDetailsBycid(int action){
		cdd.getCheckDetailsByempid(action);
		System.out.println("请选择你要删除的考勤id：");
		int cid = input.nextInt();
		int row = cdc.delCheckDetailsBYCid(cid);
		if(row>0){
			System.out.println("删除成功。");
			cdd.getAllCheckDetails();
		}else{
			System.out.println("删除失败。");
		}
	}
	//修改考勤信息
	public void updateCheckDetailsBYCid(){
		getAllCheckDateils();
		int empid;
		System.out.println("请输入你要修改的考勤id:");
		int cid = input.nextInt();
		
			System.out.println("请选择修改后的签到状态：");
			int row = to(cid);
		if(row>0){
			System.out.println("修改成功");
			//更新事项信息
			empid = cdd.GetEmpidByCid(cid);
			SignMethodDao smd = new SignMethodDao();
			smd.UpdateEventByEmpid(empid, cid);
			
		}else{
			System.out.println("修改失败");
		}
	}
	public int to(int cid){
		String flag ="";
		System.out.println("1.正常");
		System.out.println("2.迟到");
		System.out.println("3.早退");
		System.out.println("4.旷工");
		System.out.println("5.迟到，加班");
		System.out.println("6.加班");
		System.out.println("7.迟到，早退");
		int choose=input.nextInt();
		switch (choose) {
		case 1:
			flag ="正常";
			break;
		case 2:
			flag = "迟到";
			break;
		case 3:
			flag = "早退";
			break;
		case 4:
			flag = "旷工";
			break;
		case 5:
			flag = "迟到，加班";
			break;
		case 6:
			flag = "加班";
			break;
		case 7:
			flag = "迟到，早退";
			break;
		default:
			System.out.println("输入有误。");
			break;
		}
		int row = cdd.UpdateStatusById(cid, flag);
		return row;
	}
	public static void main(String[] args) {
		new CheckDateilsUi().all();
	}
	}
	
	
	
		
	


