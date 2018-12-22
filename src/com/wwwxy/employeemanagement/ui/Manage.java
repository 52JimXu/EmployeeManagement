package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.dao.SalaryDao;
import com.wwwxy.employeemanagement.entity.EventEntity;
import com.wwwxy.employeemanagement.entity.SalaryEntity;


public class Manage {
	public static void main(String[] args) {
		new Manage().all();
	}
	public void all() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		LoginUi lu = new LoginUi();
		System.out.println("��ӭ����������Դ����ϵͳ");
		int arr[] = lu.login();
		int admin = arr[0];
		int id = arr[1];
		if(id==0){
			System.out.println("���˳�������Դϵͳ");
			return;
		}
		if(admin !=0){
			System.out.println("----------��ӭ�������Աϵͳ----------");
			String f = "y";
			do{
				System.out.println("1��Ա����Ϣ����");
				System.out.println("2��������Ϣ����");
				System.out.println("3��������Ϣ����");
				System.out.println("4��������Ϣ����");
				System.out.println("5����¼��Ϣ����");
				System.out.println("6��������Ϣ����");
				System.out.println("7���˳�����ϵͳ");
				System.out.println("��ѡ����Ҫ���еĲ���(���������ż���)��");
				int information = 0;
				boolean flag = true;
				while(flag){
					try {
						information=input.nextInt();
						flag = false;
					} catch (Exception e) {
						System.out.println("��������,����������:");
						input = new Scanner(System.in);
						flag = true;
						continue;
					}
				}
				switch(information){
				case 1:
					new EmployeeUi().EmployeeUiAll();
					break;
				case 2:
					new CheckDateilsUi().all();
					break;
				case 3:
					new EventUi().all();
					break;
				case 4:
					new SalaryUi().Salary();
					break;
				case 5:
					lu.Awesome();
					break;
				case 6:
					lu.Reset(id);
					break;
				case 7:
					f="n";
					break;
				default:
					System.out.println("��������!");
					break;
				}
				if(information !=7){
					System.out.println("�Ƿ����ʹ��Ա������ϵͳ?������������y/�˳�����n����");
					f = input.next();	
				}	
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("��������������y����n");
					f = input.next();
				}
			}while("y".equals(f));{	
				System.out.println("���˳�����Աϵͳ");
			}
		}else if(admin == 0){
			System.out.println("----------��ӭ������˹���ϵͳ----------");
			String f = "y";
			do{
				System.out.println("1�����ڴ�");
				System.out.println("2�����ʲ�ѯ");
				System.out.println("3�������ѯ");
				System.out.println("4���޸�����");
				System.out.println("5���˳�ϵͳ");
				System.out.println("��ѡ����Ҫ���еĲ���(���������ż���)��");
				int information = 0;
				int empid = new LoginDao().getEmpidById(id);
				boolean flag = true;
				while(flag){
					try {
						information = input.nextInt();
						flag = false;
					} catch (Exception e) {
						System.out.println("��������,����������:");
						input = new Scanner(System.in);
						flag = true;
						continue;
					}
				}
				switch(information){
				case 1:
					
					new SignMethodUi().IsCheck(empid);
					
					
					break;
				case 2:
					List<SalaryEntity> list =new SalaryDao().GetSalaryByEmpId(empid);
					System.out.println("���ʱ��\tԱ��ID\t����ID\t�����ܺ�\t������ʱ��\t");
					for(SalaryEntity list1:list){
						System.out.println(list1.getsId()+"\t"+list1.getEmpId()+"\t"
								+list1.geteId()+"\t"+list1.getsSum()+"\t"+list1.getsTime());
					}
					break;
				case 3:
					List<EventEntity> list2 = new EventDao().getEventById(empid);
					System.out.println("��������\tԱ�����\t�ٵ�����\t�Ӱ����\t��������\t�������");
					for(EventEntity e:list2){
						System.out.println(e.geteId()+"\t"+e.geteMpid()+"\t"+e.geteClocking()+"\t"+e.geteOvertime()+"\t"+e.geteBigevent()+"\t"+e.geteAward());
					}
					break;
				case 4:
					lu.UpdateLoginPassword(id);
					break;
				case 5:
					f="n";
					break;
				default:
					System.out.println("��������!");
					break;
				}
				if(information!=5){
					System.out.println("�Ƿ����?������������y/�˳�����n����");
					f = input.next();
				}					
				while(!"n".equals(f)&&!"y".equals(f)){
					System.out.println("��������������y����n");
					f = input.next();
				}
			}while("y".equals(f));{
				System.out.println("���˳����˹���ϵͳ");
			}
		}else{
			System.out.println("�����˳�������Դ����ϵͳ");
		}
	}

}
