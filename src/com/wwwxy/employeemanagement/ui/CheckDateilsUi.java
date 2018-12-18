package com.wwwxy.employeemanagement.ui;
import java.sql.Timestamp;
import java.util.*;

import com.wwwxy.employeemanagement.control.CheckDetailsControl;
import com.wwwxy.employeemanagement.dao.*;
import com.wwwxy.employeemanagement.entity.CheckDetails;
public class CheckDateilsUi {
	SignMethodUi smu=new SignMethodUi();
	Scanner input=new Scanner(System.in);
		public void All(){
			smu.addCheckDetails(1, 101, Timestamp.valueOf("2018-12-13 14:15:41"), 
					Timestamp.valueOf("2018-12-13 14:15:58"),"正常", "2018-12-13");
			smu.addCheckDetails(2, 102, Timestamp.valueOf("2018-12-13 12:17:25"), 
					Timestamp.valueOf("2018-12-13 14:17:44"),"正常", "2018-12-13");
			smu.addCheckDetails(3, 102, Timestamp.valueOf("2018-12-18 11:09:17"), 
					Timestamp.valueOf("2018-12-18 11:09:17"),"旷工", "2018-12-17");
			smu.addCheckDetails(5, 103, Timestamp.valueOf("2018-12-17 12:10:10"), 
					Timestamp.valueOf("2018-12-17 17:10:10"),"正常", "2018-12-17");
//			smu.showcd();
		String f="y";
		do {
			 System.out.println("员工考勤系统");
	         System.out.println("1-------------签到");
	         System.out.println("2-------------签退");
	         System.out.println("3-------------显示考勤信息");
	         System.out.println("0-------------exit");
	         int loop=input.nextInt();
	         switch (loop) {
	         	case 1:
	         		smu.Signin();
	         		
	         		break;
	         	case 2:
	         		smu.Signout();
	         		
         	    case 3:
	         		smu.Look();
	         		case 0:
	         		f="n";
    				break;
               default:
                	System.out.println("输入有误。");
                    break;
		}
	         if(loop!=0){
					System.out.println("是否继续（y/n）：");
					f = input.next();
				}
		} while ("y".equals(f));		
	}
//	public void all(){
//		String f = "y";
//		do{
//			System.out.println("1、显示所有考勤信息");
//			System.out.println("2、输入员工ID查询考勤信息");
//			System.out.println("3、修改考勤信息");
//			System.out.println("4、删除考勤信息");
//			System.out.println("5、返回上一级");
//			System.out.println("请输入您要进行的管理操作：");
//			int action = input.nextInt();
//			switch(action){
//			case 1:
//				getAllCheckDateils();
//				break;
//			case 2:
//				getCheckDateilsByempid(action);
//				break;
//			case 3:
//				updateCheckDetailsBYCid();
//				break;
//			case 4:
//				delCheckDetailsBycid(action);
//				break;
//			case 5:
//				f="n";
//				break;
//			default:
//				System.out.println("输入有误。");
//			}
//			if(action!=5){
//				System.out.println("是否继续（y/n）：");
//				f = input.next();
//			}
//		}while("y".equals(f));
//	}
//	//显示所有考勤信息
//		public void getAllCheckDateils(){
//			List<CheckDetails> list = cdc.getAllCheckDetails();
//			System.out.println("考勤id\t员工id\t\t\t签到时间\t\t签退时间\t\t考勤状态\t考勤日期\t");
//			for(CheckDetails cd:list){
//				System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
//					+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
//			}
//		}
//		//输入员工id查询考勤信息
//		public void getCheckDateilsByempid(int action){
//			if(action==2){
//				System.out.println("请输入您要查询的考勤员工ID：");
//			}else if(action==3){
//				System.out.println("请输入您要修改的考勤员工ID：");
//			}else{
//				System.out.println("请输入您要删除的考勤员工ID：");
//			}
//			int empid = input.nextInt();
//			List<CheckDetails> list = cdc.getCheckDetailsByempid(empid);
//			if(list.size()!=0){
//				System.out.println("考勤id\t员工id\t\t\t签到时间\t\t\t\t签退时间\t\t考勤状态\t考勤日期\t\t");
//				for(CheckDetails cd:list){
//					System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
//							+cd.getCcheckout()+"\t"+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
//				}
//			}else{
//				System.out.println("未查询到相应信息。");
//			}		
//		}		
////删除考勤信息
//	public void delCheckDetailsBycid(int action){
//		getAllCheckDateils();
//		System.out.println("请选择你要删除的考勤id：");
//		int cid = input.nextInt();
//		int row = cdc.delCheckDetailsBYCid(cid);
//		if(row>0){
//			System.out.println("删除成功。");
//			getAllCheckDateils();
//		}else{
//			System.out.println("删除失败。");
//		}
//	}
//	//修改考勤信息
//	public void updateCheckDetailsBYCid(){
//		CheckDetails cd=new CheckDetails();
//		System.out.println("请输入你要修改的员工id:");
//		int cid = input.nextInt();
//		cd.setCid(cid);
//		List<CheckDetails> list = cdc.getCheckDetailsByempid(cid);
//		for(CheckDetails list1:list){
//			cd.setEmpid(list1.getEmpid());
//			cd.setCcheckin(list1.getCcheckin());
//			cd.setCcheckout(list1.getCcheckout());
//			cd.setCstatus(list1.getCstatus());
//			cd.setCdate(list1.getCdate());
//		}
//		System.out.println("请选择你要修改的选项，用逗号隔开：");
//		String msg = input.next();
//		String[] x = msg.split(",");
//		for(String str:x){
//			if("1".equals(str)){
//				System.out.println("请输入修改后的员工ID：");
//				int empid = input.nextInt();
//				cd.setEmpid(empid);
//			}
//			if("2".equals(str)){
//				System.out.println("请输入修改后的签到状态：");
//				String cstatus = input.next();
//				cd.setCstatus(cstatus);
//			}
//		}
//		int row = cdd.updateCheckDetailsBYCid(cd);
//		if(row>0){
//			System.out.println("修改成功");
//		}else{
//			System.out.println("修改失败");
//		}
//	}
	public static void main(String[] args) {
		new CheckDateilsUi().All();
	}
	}
	
	
	
		
	


