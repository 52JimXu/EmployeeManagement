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
			System.out.println("1����ʾ���п�����Ϣ");
			System.out.println("2������Ա��ID��ѯ������Ϣ");
			System.out.println("3���޸Ŀ�����Ϣ");
			System.out.println("4������������Ϣ");
			System.out.println("5��ɾ��������Ϣ");
			System.out.println("6��������һ��");
			System.out.println("��������Ҫ���еĹ��������");
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
				System.out.println("��������");
			}
			if(action!=6){
				System.out.println("�Ƿ������y/n����");
				f = input.next();
			}
		}while("y".equals(f));
	}
	//��ʾ���п�����Ϣ
		public void getAllCheckDateils(){
			List<CheckDetails> list = cdc.getAllCheckDetails();
			System.out.println("����id\tԱ��id\tǩ��ʱ��\tǩ��ʱ��\t����״̬\t��������\t");
			for(CheckDetails cd:list){
				System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
					+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
			}
		}
		//����Ա��id��ѯ������Ϣ
		public void getCheckDateilsByempid(int action){
			if(action==2){
				System.out.println("��������Ҫ��ѯ�Ŀ���Ա��ID��");
			}else if(action==3){
				System.out.println("��������Ҫ�޸ĵĿ���Ա��ID��");
			}else{
				System.out.println("��������Ҫɾ���Ŀ���Ա��ID��");
			}
			int empid = input.nextInt();
			List<CheckDetails> list = cdc.getCheckDetailsByempid(empid);
			if(list.size()!=0){
				System.out.println("����id\tԱ��id\tǩ��ʱ��\tǩ��ʱ��\t����״̬\t��������\t");
				for(CheckDetails cd:list){
					System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
							+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
				}
			}else{
				System.out.println("δ��ѯ����Ӧ��Ϣ��");
			}
		}
		//����������Ϣ
		public void addCheckDateils(){
			System.out.println("������Ҫ��ӵĿ���ID��");
			int cid = input.nextInt();
			System.out.println("������Ҫ��ӵ�Ա��ID��");
			int empid = input.nextInt();
//			System.out.println("������Ҫ��ӵ�ǩ��ʱ�䣺");
//			String ccheckin = input.next();
//			System.out.println("������Ҫ��ӵ�ǩ��ʱ�䣺");
//			int ccheckinout = input.nextInt();
			System.out.println("������Ҫ��ӵĿ���״̬��");
			String cstatus = input.next();
			System.out.println("������Ҫ��ӵ�Ա���������ڣ�");
			String cdate = input.next();
			CheckDetails cd = new CheckDetails();
			cd.setCid(cid);
			cd.setEmpid(empid);
//			cd.setCcheckin(ccheckin);
//			cd.setCcheckout(ccheckout);
			int row = cdc.addCheckDetails(cd);
			if(row>0){
				System.out.println("��ӳɹ���");
				getAllCheckDateils();
			}else
				System.out.println("���ʧ�ܡ�");
			}
		
//ɾ��������Ϣ
	public void delCheckDetailsBycid(int action){
		getAllCheckDateils();
		System.out.println("��ѡ����Ҫɾ���Ŀ���id��");
		int cid = input.nextInt();
		int row = cdc.delCheckDetailsBYCid(cid);
		if(row>0){
			System.out.println("ɾ���ɹ���");
			getAllCheckDateils();
		}else{
			System.out.println("ɾ��ʧ�ܡ�");
		}
	}
	//�޸Ŀ�����Ϣ
	public void updateCheckDetailsBYCid(){
		CheckDetails cd=new CheckDetails();
		System.out.println("��������Ҫ�޸ĵ�Ա��id:");
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
		System.out.println("��ѡ����Ҫ�޸ĵ�ѡ��ö��Ÿ�����");
		String msg = input.next();
		String[] x = msg.split(",");
		for(String str:x){
			if("1".equals(str)){
				System.out.println("�������޸ĺ��Ա��ID��");
				int empid = input.nextInt();
				cd.setEmpid(empid);
			}
			if("2".equals(str)){
				System.out.println("�������޸ĺ��ǩ��״̬��");
				String cstatus = input.next();
				cd.setCstatus(cstatus);
			}
		}
		int row = cdd.updateCheckDetailsBYCid(cd);
		if(row>0){
			System.out.println("�޸ĳɹ�");
		}else{
			System.out.println("�޸�ʧ��");
		}
	}
	public static void main(String[] args) {
		new CheckDateilsUi().all();
	}
	
	}
		
	


