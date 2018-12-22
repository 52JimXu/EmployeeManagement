package com.wwwxy.employeemanagement.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.SalaryControl;
import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.dao.SalarySumDao;
import com.wwwxy.employeemanagement.entity.SalaryEntity;

public class SalaryUi {
	SalaryControl sac = new SalaryControl();
	SalaryDao sd = new SalaryDao();
	Scanner sc = new Scanner(System.in);
	public void Salary() {
		all();
	}
	//��ѯ����
	public void GetAllSalary() {
		List<SalaryEntity> list = sac.GetAllSalary();
		System.out.println("���ʱ��\tԱ��ID\t����ID\t�����ܺ�\t������ʱ��\t");
		for(SalaryEntity list1:list){
			System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
		+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
		}
	}
	//��ѯĳ��Ա����Ϣ��ʹ��Ա��ID��ѯ
		public void GetSalaryById(int id) {
			List<SalaryEntity> list = sac.GetSalaryById(id);
			System.out.println("���ʱ��\tԱ��ID\t����ID\t�����ܺ�\t������ʱ��\t");
			for(SalaryEntity list1:list){
				System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
			+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
			}
		}
		//���ӹ��ʼ�¼
		public void AddSalary() {
			System.out.println("���������ӵ�Ա�����:");
			int empid=0;
			int eid = 0;
			int row = 0;
			boolean flag =true;
			while(flag){
				try {
					empid = sc.nextInt();
					eid=new SalarySumDao().GetEventIdByEmpId(empid);//��ȡ����id
					List<SalaryEntity> list = sd.GetSalaryByEmpId(empid);
					if(list.size()!=0){
						System.out.println("�Ѵ��ڸ�Ա���Ĺ��ʼ�¼");
						return;
					}
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 30);
					Date date = cal.getTime();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String time= sdf.format( date);
					SalaryEntity se = new SalaryEntity(0, empid, eid, 0, time);
					
					
					row= sd.add(se);
					flag = false;
				} catch (InputMismatchException e) {
					System.out.println("��������,����������:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}catch (NullPointerException e) {
					System.out.println("Ա��������,����������:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			if(row>0){
				System.out.println("�����ɹ�");
				GetAllSalary();
			}else{
				System.out.println("����ʧ��");
			}
		}
		//ɾ��
		public void DeleteSalary() {
			GetAllSalary();
			System.out.println("��������Ҫɾ���Ĺ���ID:");
			boolean flag = true;
			int id=0;
			int row=0;
			while(flag){
				try {
					id = sc.nextInt();
					List<SalaryEntity> list = sd.GetSalaryById(id);
					if(list.size()==0){
						System.out.println("�����ڸ�Ա���Ĺ��ʼ�¼");
						return;
					}
					row = sd.delete(id);
				} catch (InputMismatchException e) {
					System.out.println("��������,����������:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			if(row>0){
				System.out.println("�����ɹ�");
				GetAllSalary();
			}else{
				System.out.println("����ʧ��");
			}
		}
//		�޸���Ϣ
		public void UpdateSalary() {
			SalaryEntity se = new SalaryEntity();
			GetAllSalary();
			System.out.println("��������Ҫ�޸ĵĹ��ʱ��:");
			
			int id=0;
			boolean flag = true;
			while(flag){
				try {
					 id= sc.nextInt();
					 flag = false;
					 
				} catch (Exception e) {
					System.out.println("��������,����������:");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}
			}
			flag = true;
			while(flag){
				
				List<SalaryEntity> list = sd.GetSalaryById(id);
				if(list.size()==0){
					System.out.println("����Ĺ���ID������,����������:");
					boolean flag1 =true;
					while(flag1){
						try {
							id= sc.nextInt();
							list = sd.GetSalaryById(id);
							flag1 = false;
							 
						} catch (Exception e) {
							System.out.println("��������,����������:");
							sc = new Scanner(System.in);
							flag1 = true;
							continue;
						}
					}
					flag =true;
				}else{
					flag =false;
				}
			}
			
			se.setsId(id);
			List<SalaryEntity> list = sd.GetSalaryById(id);

			for(SalaryEntity list1:list){
				if(list1.getsTime() ==null){
					System.out.println("��������");
					return;
				}
				se.setEmpId(list1.getEmpId());
				se.seteId(list1.geteId());
				se.setsSum(list1.getsSum());
				se.setsTime(list1.getsTime());
			}
			System.out.println("��������Ҫ�޸ĵ�����(��ʽ:2000-01-01):");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			flag = true;
			String stimeString;
			
			while(flag){
				stimeString = sc.next();
				try {
					sdf.parse(stimeString);
					flag = false;
				} catch (ParseException e) {
					System.out.println("��ʽ����");
					flag = true;
				}
				se.setsTime(stimeString);
			}
			int row = sd.update(se);
			if(row>0){
				System.out.println("�����ɹ�");
				GetAllSalary();
			}else{
				System.out.println("����ʧ��");
			}
		}
		//���¹��ʼ�¼
		public void UpdateSalaryByEmpId() {
			
			new SalaryDao().GetAllSalary();
			GetAllSalary();
			int empid=0 ;
			System.out.println("��������Ҫ���µ�Ա��ID��");
			boolean flag = true;
			int row =0;
			while(flag){
				try {
					empid = sc.nextInt();
					
					float salary = new SalarySumDao().SalarySum(empid);
					row = sd.UpdateSalaryByEmpId(empid, salary);
					flag = false;
				} catch (InputMismatchException e) {
					System.out.println("��������,����������");
					sc = new Scanner(System.in);
					flag = true;
					continue;
				}catch (NullPointerException e) {
					System.out.println("�����ڸ�Ա��������������");
					sc = new Scanner(System.in);
					flag = true;
					continue;
					}
			}
			if(row>0){
				System.out.println("�����ɹ�");
				GetAllSalary();
			}else{
				System.out.println("����ʧ��");
			}
		}
		
		
		public void all() {
			String chose = "y";
			while("y".equals(chose)){
				System.out.println("��ѡ����Ҫ���еĲ���:");
				System.out.println("1���������ʼ�¼");
				System.out.println("2��ɾ�����ʼ�¼");
				System.out.println("3����ѯ���ʼ�¼");
				System.out.println("4���޸ķ�����ʱ��");
				System.out.println("5�����¹��ʼ�¼");
				System.out.println("6���˳����ʹ���");
				int num =0;
				try {
					num=sc.nextInt();
				} catch (Exception e) {
					System.out.println("��������,����������");
					sc = new Scanner(System.in);
					continue;
				}
				
				switch (num) {
				case 1:
					AddSalary();
					break;
				case 2:
					DeleteSalary();
					break;
				case 3:
					GetAllSalary();
					break;
				case 4:
					UpdateSalary();
					break;
				case 5:
					UpdateSalaryByEmpId();
					break;
				case 6:
					chose = "n";
					break;
				default:
					System.out.println("��������");
					break;
				}
				if(!"n".equals(chose)){
					System.out.println("�Ƿ������������������y/n��");
					chose = sc.next();
					while(!"n".equals(chose)&&!"y".equals(chose)){
						System.out.println("��������������y����n");
						chose = sc.next();
					}
				}else{
					System.out.println("���˳����ʼ�¼����!");
					return;
				}
			}
			if("n".equals(chose)){
				System.out.println("���˳����ʼ�¼����");
			}
		}
}
