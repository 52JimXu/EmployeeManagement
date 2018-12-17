package com.wwwxy.employeemanagement.ui;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.wwwxy.employeemanagement.entity.CheckDetails;
import com.wwwxy.employeemanagement.control.CheckDetailsControl;
import com.wwwxy.employeemanagement.dao.*;
public class CheckDateilsUi {
	Scanner input = new Scanner(System.in);
	CheckDetailsControl cdc = new CheckDetailsControl();
	CheckDetailsDao cdd=new CheckDetailsDao();
	
	public void all(){
		String f = "y";
		do{
			System.out.println("1、显示所有考勤信息");
			System.out.println("2、输入员工ID查询考勤信息");
			System.out.println("3、修改考勤信息");
			System.out.println("4、新增考勤信息");
			System.out.println("5、删除考勤信息");
			System.out.println("6、返回上一级");
			System.out.println("请输入您要进行的管理操作：");
			int action = input.nextInt();
			switch(action){
			case 1:
				getAllCheckDateils();
				break;
			case 2:
				getCheckDateilsByempid(action);
				break;
			case 3:
				delCheckDetailsBycid(action);
				break;
			case 4:
				addCheckDateils();
				break;
			case 5:
				delCheckDetailsBycid(action);
				break;
			case 6:
				f="n";
				break;
			default:
				System.out.println("输入有误。");
			}
			if(action!=6){
				System.out.println("是否继续（y/n）：");
				f = input.next();
			}
		}while("y".equals(f));
	}
	//显示所有考勤信息
		public void getAllCheckDateils(){
			List<CheckDetails> list = cdc.getAllCheckDetails();
			System.out.println("考勤id\t员工id\t签到时间\t签退时间\t考勤状态\t考勤日期\t");
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
				System.out.println("考勤id\t员工id\t签到时间\t签退时间\t考勤状态\t考勤日期\t");
				for(CheckDetails cd:list){
					System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
							+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
				}
			}else{
				System.out.println("未查询到相应信息。");
			}
		}
		//新增考勤信息
		public void addCheckDateils(){
			System.out.println("请输入要添加的考勤ID：");
			int cid = input.nextInt();
			System.out.println("请输入要添加的员工ID：");
			int empid = input.nextInt();
//			System.out.println("请输入要添加的签到时间：");
//			String ccheckin = input.next();
//			System.out.println("请输入要添加的签退时间：");
//			int ccheckinout = input.nextInt();
			System.out.println("请输入要添加的考勤状态：");
			String cstatus = input.next();
			System.out.println("请输入要添加的员工考勤日期：");
			String cdate = input.next();
			CheckDetails cd = new CheckDetails();
			cd.setCid(cid);
			cd.setEmpid(empid);
//			cd.setCcheckin(ccheckin);
//			cd.setCcheckout(ccheckout);
			int row = cdc.addCheckDetails(cd);
			if(row>0){
				System.out.println("添加成功。");
				getAllCheckDateils();
			}else
				System.out.println("添加失败。");
			}
		
//删除考勤信息
	public void delCheckDetailsBycid(int action){
		getAllCheckDateils();
		System.out.println("请选择你要删除的考勤id：");
		int cid = input.nextInt();
		int row = cdc.delCheckDetailsBYCid(cid);
		if(row>0){
			System.out.println("删除成功。");
			getAllCheckDateils();
		}else{
			System.out.println("删除失败。");
		}
	}
	//修改考勤信息
	public void updateCheckDetailsBYCid(){
		CheckDetails cd=new CheckDetails();
		System.out.println("请输入你要修改的员工id:");
		int cid = input.nextInt();
		cd.setCid(cid);
		List<CheckDetails> list = cdc.getCheckDetailsByempid(cid);
		for(CheckDetails list1:list){
			cd.setEmpid(list1.getEmpid());
			cd.setCcheckin(list1.getCcheckin());
			cd.setCcheckout(list1.getCcheckout());
			cd.setCstatus(list1.getCstatus());
			cd.setCdate(list1.getCdate());
		}
		System.out.println("请选择你要修改的选项，用逗号隔开：");
		String msg = input.next();
		String[] x = msg.split(",");
		for(String str:x){
			if("1".equals(str)){
				System.out.println("请输入修改后的员工ID：");
				int empid = input.nextInt();
				cd.setEmpid(empid);
			}
			if("2".equals(str)){
				System.out.println("请输入修改后的签到状态：");
				String cstatus = input.next();
				cd.setCstatus(cstatus);
			}
		}
		int row = cdd.updateCheckDetailsBYCid(cd);
		if(row>0){
			System.out.println("修改成功");
		}else{
			System.out.println("修改失败");
		}
	}
	public static void main(String[] args) {
		new CheckDateilsUi().all();
	}
	
	}
		
	


