package com.wwwxy.employeemanagement.ui;
import java.util.Scanner;
import com.wwwxy.employeemanagement.dao.CheckDetailsDao;
public class SignMethodUi {
	public void checkin(int empid) {
		int row = 0;
		CheckDetailsDao cdd = new CheckDetailsDao();
		int i = cdd.GetCheckin(empid);
		if(i==0){
			row = cdd.checkin(empid);
			if(row>0){
				System.out.println("ǩ���ɹ�");
			}else{
				System.out.println("ǩ��ʧ��");
			}
		}else{
			System.out.println("���Ѿ�ǩ������");
		}
	}
	public void checkout(int empid){
		int row = 0;
		CheckDetailsDao cdd = new CheckDetailsDao();
		int i =cdd.GetCheckin(empid);
		if(i!=0){
			row = cdd.checkout(empid);
		}else{
			System.out.println("�㻹δǩ��");
		}
		if(row>0){
			System.out.println("ǩ�˳ɹ�");
		}else{
			System.out.println("ǩ��ʧ��");
		}
	}
	public void IsCheck(int empid) {
		String chose = "y";
		Scanner sc = new Scanner(System.in);
		int a=0;
		while("y".equals(chose)){
			System.out.println("��ѡ����Ҫ���еĲ���");
			System.out.println("1.ǩ��");
			System.out.println("2.ǩ��");
			try {
				a = sc.nextInt();
			} catch (Exception e) {
				System.out.println("��������,����������");
				sc = new Scanner(System.in);
				continue;
			}
			switch (a) {
			case 1:
				new SignMethodUi().checkin(empid);
				break;
			case 2:
				new SignMethodUi().checkout(empid);
				break;
			default:
				System.out.println("��������");
				break;
			}
			System.out.println("�Ƿ��������y/n");

				chose = sc.next();
				while(!"n".equals(chose)&&!"y".equals(chose)){
					System.out.println("��������������y����n");
					chose = sc.next();
				}
				
		}
		if("n".equals(chose)){
			System.out.println("���˳�ǩ����");
		}
	}
	public static void main(String[] args) {
		new SignMethodUi().IsCheck(0);
	}
}
