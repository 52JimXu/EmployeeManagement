package com.wwwxy.employeemanagement.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.EmployeeControl;
import com.wwwxy.employeemanagement.dao.EmployeeDao;
import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.entity.EmployeeEntity;

public class EmployeeUi {
	Scanner sc = new Scanner(System.in);
	EmployeeEntity ee = new EmployeeEntity();
	EmployeeControl ec = new EmployeeControl();
	
	public void EmployeeUiAll() {
		// TODO Auto-generated constructor stub
		String flag = "y";
		do{
			System.out.println("1.�鿴Ա����Ϣ");
			System.out.println("2.����������ѯԱ����Ϣ");
			System.out.println("3.�޸�Ա����Ϣ");
			System.out.println("4.���Ա��");
			System.out.println("5.ɾ��Ա��");
			System.out.println("6.������һ��");
			System.out.println("��ѡ��ִ�еĲ���:");
			int id=0;
			while (true) {
				try {
					String str3 = sc.next();
					id = Integer.valueOf(str3);
					switch (id) {
						case 1:
							getAllEmployee();
							break;
						case 2:
							getEmployeeByName(id);
							break;
						case 3:
							UpdateEmployee(id);
							break;
						case 4:
							AddEmployee();
							break;
						case 5:
							DeleteEmployee(id);
							break;
						case 6:
							flag = "n";
							break;
						default:
							System.out.println("��������!");
							break;
					}
					if(flag!="n"){
						System.out.println("�Ƿ����(y/n):");
						flag = sc.next();
						if("y".equals(flag)){
							break;
						}
						if("n".equals(flag)){
							System.out.println("�˳�����!");		
						}
						while(!"y".equals(flag)&&!"n".equals(flag)){
							System.out.print("��������");
							System.out.println("����������(y/n):");
							flag = sc.next();
							if("y".equals(flag)){
								break;
							}
							if("n".equals(flag)){
								System.out.println("���˳�Ա������!");
							}
						}
						break;
					}else {
						System.out.println("���˳�Ա������!");
						return;
					}
					
				} catch (Exception e) {
					System.out.print("��������");
					System.out.println("����������:");
				}
			}
				
		}while("y".equals(flag));
	}
	
	//1�鿴Ա����Ϣ
	public void getAllEmployee(){
		List<EmployeeEntity> list = ec.getAllEmployee();
		System.out.println("���\t����\t�Ա�\t����\t����ʱ��\t\t����\t�����ַ\t\t\t�־ӵ�ַ");
		for(EmployeeEntity ee:list){
			System.out.println(ee.getEmpId()+"\t"+ee.getEmpName()+"\t"+ee.getEmpSex()+"\t"+ee.getEmpAge()+"\t"+ee.getEmpBirthday()+"\t"+
			ee.getEmpBasic()+"\t"+ee.getEmpEmail()+"\t"+ee.getEmpAddress());
		}
	}
	//2����������ѯԱ����Ϣ��ģ����ѯ
	public boolean getEmployeeByName(int id){
		if(id==2){
			System.out.println("������Ҫ��ѯ��Ա������:");
		}else if(id==3){
			getAllEmployee();
			System.out.println("������Ҫ�޸ĵ�Ա������:");
		}else{
			getAllEmployee();
			System.out.println("������Ҫɾ����Ա������:");
		}
		boolean flag = true;
		String EmpName = sc.next();
		List<EmployeeEntity> list = ec.getEmployeeByName(EmpName);
		if(list.size()!=0){
			System.out.println("���\t����\t�Ա�\t����\t����ʱ��\t\t����\t�����ַ\t\t\t�־ӵ�ַ");
			for(EmployeeEntity ee:list){
				System.out.println(ee.getEmpId()+"\t"+ee.getEmpName()+"\t"+ee.getEmpSex()
						+"\t"+ee.getEmpAge()+"\t"+ee.getEmpBirthday()+"\t"+ee.getEmpBasic()
						+"\t"+ee.getEmpEmail()+"\t"+ee.getEmpAddress());
			}
		}else{
			System.out.println("Ա�������ڡ�");
			flag = false;
		}
		return flag;
	}	
		
	//3�޸�Ա����Ϣ
	public void UpdateEmployee(int id){
			boolean isflag = getEmployeeByName(id);
			
			while(isflag){	
				System.out.println("��ѡ�����ϲ�ѯ����Ա��������Ҫ�޸ĵ�Ա�����:");
				int EmpId=0;
				try {
					EmpId = sc.nextInt();
					EmployeeEntity ee1 = new EmployeeDao().getEmployeeById(EmpId);
					EmployeeEntity ee=ec.getEmployeeById(EmpId);
					if(ee1.getEmpId()!=null){
						System.out.println("��ѡ����Ҫ�޸ĵ���Ϣ���ö��Ÿ�����");
						System.out.println("1������");
						System.out.println("2���Ա�");
						System.out.println("3������");
						System.out.println("4������ʱ��");
						System.out.println("5������");
						System.out.println("6������");
						System.out.println("7���־ӵ�ַ");
						String msg = sc.next();
						String[] strs = msg.split("[,�� ]");
						
						
						boolean a =false;
							
						do{
							for(String str:strs){
								if("1".equals(str)||"2".equals(str)||"3".equals(str)||"4".equals(str)
								||"5".equals(str)||"6".equals(str)||"7".equals(str)){
									a =false;
									if("1".equals(str)){
										System.out.println("�������޸Ĺ��������:");
										String EmpName = sc.next();
										ee.setEmpName(EmpName);
										ec.UpdateEmployee(ee);
									}
									if("2".equals(str)){
										System.out.println("��ѡ���޸Ĺ�����Ա�:");
										System.out.println("1.��\t2.Ů");
										
										String EmpSex =null;
										boolean psex =false;
										do{
											String sexid = sc.next();
											if("1".equals(sexid)){
												EmpSex = "��";
												break;
											}else if("2".equals(sexid)){
												EmpSex = "Ů";
												break;
											}else{
												System.out.println("��������ȷѡ��:");
												psex = true;
											}
										}while(psex);
										ee.setEmpSex(EmpSex);
										ec.UpdateEmployee(ee);
									}
									if("3".equals(str)){
										System.out.println("�������޸Ĺ��������:");
										int EmpAge=0;
										while(true){
											try {
												String str1 = sc.next();
												EmpAge=Integer.valueOf(str1);
											}catch (Exception e) {
												System.out.println("����ȷ��������:");
												continue;
											}
										ee.setEmpAge(EmpAge);
										ec.UpdateEmployee(ee);
										break;
										}
									}
									if("4".equals(str)){
										System.out.println("�������޸Ĺ���ĳ���ʱ��(��ʽ:2000-01-01):");
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										String EmpBirthday = null;
										while(true){
											EmpBirthday= sc.next();
											try {
												sdf.parse(EmpBirthday);
											} catch (ParseException e) {
												System.out.println("��ʽ����");
												continue;
											}
										ee.setEmpBirthday(EmpBirthday);
										ec.UpdateEmployee(ee);
										break;
										}
									}
									if("5".equals(str)){
										System.out.println("�������޸Ĺ���Ĺ���:");
										float EmpBasic=0;
										while(true){
										try {
												String str2 = sc.next();
												EmpBasic=Integer.valueOf(str2);
											}catch (Exception e) {
												System.out.println("����ȷ���빤��:");
												continue;
											}
										ee.setEmpBasic(EmpBasic);
										ec.UpdateEmployee(ee);
										break;
										}
									}
									if("6".equals(str)){
										String reg ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{3,}$";
										System.out.println("�������޸Ĺ��������:");
										String EmpEmail= null;
										boolean flag ;
										do{
											EmpEmail =sc.next();
											if(EmpEmail.matches(reg)){
												flag = false;
												break;
											}else{
												System.out.println("����ȷ��������:");
												flag = true;
											}
										}while(flag);	
										ee.setEmpEmail(EmpEmail);
										ec.UpdateEmployee(ee);
										break;
										
									}
									if("7".equals(str)){
										System.out.println("�������޸Ĺ�����־ӵ�ַ:");
										String EmpAddress = sc.next();
										ee.setEmpAddress(EmpAddress);
										ec.UpdateEmployee(ee);
									}
							}else{
								System.out.println("����ȷ������Ӧ���:");
								a = true;
								msg = sc.next();
								strs = msg.split("[,�� ]");
							}
						}					
					}while(a);
							
						
						
						boolean flag = ec.UpdateEmployee(ee);
						if(flag){
							System.out.println("�޸ĳɹ�!");
						}else{
							System.out.println("�޸�ʧ��!");
						}
						getAllEmployee();
						return;
					}else{
						System.out.println("�����б��в����ڸ�IDԱ����");
						System.out.println("����������Ա�����:");
					}
				} catch (Exception e) {
					System.out.println("������ƥ��!");
					sc = new Scanner(System.in);
					continue;
				}
				
			}
		}

	//4����Ա����Ϣ
	public void AddEmployee(){
		System.out.println("������Ҫ������Ա������:");
		String EmpName = sc.next();
		
		System.out.println("�Ա�:");
		System.out.println("1.��\t2.Ů");
		String EmpSex =null;
		boolean psex =false;
		do{
			String sexid = sc.next();
			if("1".equals(sexid)){
				EmpSex = "��";
				break;
			}else if("2".equals(sexid)){
				EmpSex = "Ů";
				break;
			}else{
				System.out.println("��������ȷѡ��:");
				psex = true;
			}
		}while(psex);
		
		
		System.out.println("����:");
		int EmpAge=0;
		while(true){
		try {
				String str1 = sc.next();
				EmpAge=Integer.valueOf(str1);
			}catch (Exception e) {
				System.out.println("����ȷ��������:");
				continue;
			}
		break;
		}
		
		System.out.println("��������:");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String EmpBirthday = null;
		while(true){
			EmpBirthday= sc.next();
			try {
				sdf.parse(EmpBirthday);
			} catch (ParseException e) {
				System.out.println("��ʽ����");
				continue;
			}
		break;
		}
		
		System.out.println("���¹���:");
		float EmpBasic=0;
		while(true){
			try {
				String str2 = sc.next();
				EmpBasic=Integer.valueOf(str2);
			}catch (Exception e) {
				System.out.println("����ȷ���빤��:");
				continue;
			}
		break;
		}
		
		
		System.out.println("�����ַ:");
		String reg ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{3,}$";
		String EmpEmail= null;
		boolean flagem ;
		do{
			EmpEmail =sc.next();
			if(EmpEmail.matches(reg)){
				flagem = false;
				break;
			}else{
				System.out.println("����ȷ��������:");
				flagem = true;
			}
		}while(flagem);
		
		
		System.out.println("�־ӵ�ַ:");
		String EmpAddress = sc.next();
		EmployeeEntity ee = new EmployeeEntity(EmpName,EmpSex, EmpAge, EmpBirthday, EmpBasic, EmpEmail, EmpAddress);
		boolean flag = ec.AddEmployee(ee);
		if(flag){
			System.out.println("��ӳɹ�");
		}else{
			System.out.println("���ʧ��");
		}
		getAllEmployee();
	}
	//5ɾ��Ա��
	public void DeleteEmployee(int id){
		getEmployeeByName(id);
		System.out.println("��ѡ����Ҫɾ����Ա�����:");
		int EmpId = sc.nextInt();
		ee.setEmpId(EmpId);
		boolean flag = ec.DeleteEmployee(EmpId);
		if(flag){
			System.out.println("ɾ���ɹ�!");
		}else{
			System.out.println("ɾ��ʧ��!");
		}
		getAllEmployee();
	}
	
}
