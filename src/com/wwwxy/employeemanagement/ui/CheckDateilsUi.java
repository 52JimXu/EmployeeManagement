package com.wwwxy.employeemanagement.ui;
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
			System.out.println("1����ʾ���п�����Ϣ");
			System.out.println("2������Ա��ID��ѯ������Ϣ");
			System.out.println("3���޸Ŀ���״̬��Ϣ");
			System.out.println("4��ɾ��������Ϣ");
			System.out.println("5��������һ��");
			System.out.println("��������Ҫ���еĹ��������");
			int action=0;
			try {
				action=input.nextInt();
			} catch (Exception e) {
				System.out.println("��������,����������");
				input = new Scanner(System.in);
				if(action!=5){
					System.out.println("�Ƿ������y/n����");
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
				System.out.println("��������");
			}
			if(action!=5){
				System.out.println("�Ƿ������y/n����");
				f = input.next();
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("��������������y����n");
					f = input.next();
				}
			}
		}while("y".equals(f));
	}
	//��ʾ���п�����Ϣ
		public void getAllCheckDateils(){
			List<CheckDetails> list = cdc.getAllCheckDetails();
			System.out.println("����id\tԱ��id\t ǩ��ʱ��\t\tǩ��ʱ��\t\t����״̬\t��������\t");
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
				System.out.println("����id\tԱ��idǩ��ʱ��\t\t\tǩ��ʱ��\t\t����״̬\t��������\t");
				for(CheckDetails cd:list){
					System.out.println(cd.getCid()+"\t"+cd.getEmpid()+"\t"+cd.getCcheckin()+"\t"
							+cd.getCcheckout()+"\t"+cd.getCstatus()+"\t"+cd.getCdate()+"\t");
				}
			}else{
				System.out.println("δ��ѯ����Ӧ��Ϣ��");
			}		
		}		
//ɾ��������Ϣ
	public void delCheckDetailsBycid(int action){
		cdd.getCheckDetailsByempid(action);
		System.out.println("��ѡ����Ҫɾ���Ŀ���id��");
		int cid = input.nextInt();
		int row = cdc.delCheckDetailsBYCid(cid);
		if(row>0){
			System.out.println("ɾ���ɹ���");
			cdd.getAllCheckDetails();
		}else{
			System.out.println("ɾ��ʧ�ܡ�");
		}
	}
	//�޸Ŀ�����Ϣ
	public void updateCheckDetailsBYCid(){
		getAllCheckDateils();
		System.out.println("��������Ҫ�޸ĵĿ���id:");
		int cid = input.nextInt();
		
			System.out.println("��ѡ���޸ĺ��ǩ��״̬��");
			int row = to(cid);
		if(row>0){
			System.out.println("�޸ĳɹ�");
		}else{
			System.out.println("�޸�ʧ��");
		}
	}
	public int to(int cid){
		String flag ="";
		System.out.println("1.����");
		System.out.println("2.�ٵ�");
		System.out.println("3.����");
		System.out.println("4.����");
		System.out.println("5.�ٵ����Ӱ�");
		System.out.println("6.�Ӱ�");
		System.out.println("7.�ٵ�������");
		int choose=input.nextInt();
		switch (choose) {
		case 1:
			flag ="����";
			break;
		case 2:
			flag = "�ٵ�";
			break;
		case 3:
			flag = "����";
			break;
		case 4:
			flag = "����";
			break;
		case 5:
			flag = "�ٵ����Ӱ�";
			break;
		case 6:
			flag = "�Ӱ�";
			break;
		case 7:
			flag = "�ٵ�������";
			break;
		default:
			System.out.println("��������");
			break;
		}
		int row = cdd.UpdateStatusById(cid, flag);
		return row;
	}
	public static void main(String[] args) {
		new CheckDateilsUi().getAllCheckDateils();
	}
	}
	
	
	
		
	


