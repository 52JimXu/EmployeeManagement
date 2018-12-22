package com.wwwxy.employeemanagement.ui;

import java.util.List;
import java.util.Scanner;

import com.wwwxy.employeemanagement.control.LoginControl;
import com.wwwxy.employeemanagement.dao.LoginDao;
import com.wwwxy.employeemanagement.entity.LoginEntity;

public class LoginUi {
	LoginEntity le = new LoginEntity();
	public int[] login() {
		LoginControl lc = new LoginControl();
		int id = lc.login();
		int[] arr=new int[2] ;
		LoginDao ld = new LoginDao();
		int admin=ld.getAdminById(id);
		if(admin == 1){
			arr[0] =1; 
		}else{
			arr[0] =0;
		}
		arr[1] = id;
		return arr;
		
	}
	Scanner input = new Scanner(System.in);
	LoginControl lc = new LoginControl();
	public void Awesome(){
		String f = "y";
		do{
			System.out.println("1����ʾ���е�¼��Ϣ");
			System.out.println("2�������˺Ų�ѯ��Ϣ");
			System.out.println("3���޸ĵ�¼��Ϣ");
			System.out.println("4��������¼��Ϣ");
			System.out.println("5��ɾ����¼��Ϣ");
			System.out.println("6��������һ��");
			System.out.println("����������Ҫ������");
			int information = 0;
			try {
				information = input.nextInt();
			} catch (Exception e) {
				System.out.println("��������,����������");
				input = new Scanner(System.in);
				continue;
			}
			switch(information){
			case 1:
				getAllLogin();
				break;
			case 2:
				getLoginByUsername(information);
				break;
			case 3:
				updateLoginByEmpid1(information);
				break;
			case 4:
				addLogin();
				break;
			case 5:
				delLoginById3(information);
				break;
			case 6:
				f ="n";
				break;
			default:
				System.out.println("��������!");
				break;
			}
			if(information!=6){
				System.out.println("�Ƿ����? (����������y/�˳�����n)");
				f= input.next();
			}
			while(!"n".equals(f)&&!"y".equals(f)){
				System.out.println("��������������y����n");
				f = input.next();
			}
			if("n".equals(f)){
				System.out.println("���˳���ǰ��һ����");
			}
		}while("y".equals(f));
	}
	// ������Ϣ����
	public void Reset( int id){
		String f = "y";
		int flag = 1;
		do{
			System.out.println("1����������");
			System.out.println("2���޸�����");
			System.out.println("3��������һ��");
			System.out.println("����������Ҫ������");
			int information = 0;
			try {
				information = input.nextInt();
			} catch (Exception e) {
				System.out.println("��������,����������");
				input = new Scanner(System.in);
				continue;
			}
			switch(information){
			case 1:
				updateLoginById1();
				break;
			case 2:
				flag =UpdateLoginPassword(id);
				break;
			case 3:
				f="n";
				break;
			default:
				System.out.println("��������!");
				break;
			}
			if(information!=3){
				if(flag ==0){
					System.out.println("�޸�����ʧ�ܣ������˳�ϵͳ");
					System.exit(-1);
				}else{
					System.out.println("�Ƿ����?������������y/�˳�����n����");
					f = input.next();
				}
			}			
			if("n".equals(f)){
				if(flag ==0){
					return;
				}
				System.out.println("���˳���ǰ��һ����");
			}
		}while("y".equals(f));
	}
	//��ʾ���е�¼��Ϣ
	public void getAllLogin(){
		List<LoginEntity> list = lc.getAllLogin();
		System.out.println("ID\t�˺�\t����\t��ݱ��\t���");
		for(LoginEntity le:list){
			if(le.getAdmin()==1){
			System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
					"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}else{
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+
						"\t"+le.getAdmin()+"\t"+le.getEmpid());
			}
		}
	}
	//�����˺Ų�ѯԱ����Ϣ
	public int getLoginByUsername(int information){
		if(information ==2){
			System.out.println("��������Ҫ��ѯ���˺�(�û���)��");
		}else if(information ==3){
			System.out.println("��������Ҫ�޸ĵ��˺�(�û���)��");
		}else{
			System.out.println("��������Ҫɾ�����˺�(�û���)��");
		}
		String username = input.next();
		List<LoginEntity> list = lc.getLoginByUsername(username);
		if(list.size()!=0){
			System.out.println("ID\t�˺�\t���");
			for(LoginEntity le:list){
				System.out.println(le.getId()+"\t"+le.getUsername()+"\t"+le.getEmpid());
			}
			return 1;
		}else{
			System.out.println("δ��ѯ����Ϣ");
			return 0;
		}
	}
	//����ID�޸���Ϣ
	public void updateLoginByEmpid1(int information){
		int flag = getLoginByUsername(information);
		if(flag == 0){
			return;
		}
		System.out.println("��ѡ�����ϲ�ѯ������Ϣ������Ҫ�޸ĵ�ID��");
		int id = input.nextInt();
		le = lc.getLoginById4(id);
		System.out.println("�������޸ĺ���˺ţ�");
		String username = input.next();
		le.getUsername();
		int row = lc.updateLoginById2(id, username);
		if(row>0){
			System.out.println("�޸ĳɹ���");
			getAllLogin();
		}else{
			System.out.println("�޸�ʧ�ܡ�");
		}
	}
	//������¼��Ϣ
	public void addLogin(){
		System.out.println("��ѡ����Ҫ��ӵ���Ϣ��");
		System.out.println("1������Ա");
		System.out.println("2��Ա��");
		String key = input.next();
		int row=0;
		String[] strs = key.split("//.");
		for(String str:strs){			
			if("1".equals(str)){
				System.out.println("������Ҫ��ӵ��˺ţ�");
				String username = input.next();
				le.setUsername(username);
				le.setAdmin(1);
				row = new LoginDao().addLoginAdmin(le);
			}
			if("2".equals(str)){
				System.out.println("������Ҫ��ӵ��˺ţ�");
				String username = input.next();
				System.out.println("������Ҫ��ӵı�ţ�");
				int empid = input.nextInt();
				le.setUsername(username);
				le.setAdmin(0);
				le.setEmpid(empid);
				row = new LoginDao().addLogin(le);
			}
		}
		if(row>0){
			System.out.println("��ӳɹ���(Ĭ��������1234���뾡���޸��������!)");
		}else{
			System.out.println("���ʧ�ܡ�");
		}
	}
	//ɾ����Ϣ
	public void delLoginById3(int information){
		int flag = getLoginByUsername(information);
		if(flag == 0){
			return;
		}
		System.out.println("���ϲ�ѯ����Ա��������Ҫɾ����Ա��ID��");
		boolean a = true;
		
		int id=0;
		while(a){
			try {
				id = input.nextInt();
				a = false;
			} catch (Exception e) {
				System.out.println("��������,����������");
				input = new Scanner(System.in);
				a=true;
				continue;
			}
		}
		
		int row = lc.delLoginByEmpid3(id);
		if(row>0){
			System.out.println("ɾ���ɹ���");
			getAllLogin();
		}else{
			System.out.println("ɾ��ʧ�ܡ�");
		}
	}
	
	
	//�޸�����
	public int UpdateLoginPassword(int id){
		int f=0;
		int count =0;
		do{
		System.out.println("��������룺");
		String oldpassword = input.next();
		if(oldpassword.equals(lc.getLoginById(id, oldpassword))){
			System.out.println("�����޸����룺");
			String password = input.next();
			le.setPassword(password);
			int row = lc.UpdateLoginPassword(password, id);
			if(oldpassword.equals(password)){
				System.out.println("�����벻���������һ��");
			}else{
				if(row==1){
					System.out.println("ִ�����");
					break;
				}else{
					System.out.println("ִ��ʧ��");
				}
			}
		}else{
			count++;
			System.out.println("���������,�㻹��"+(3-count)+"�λ���");
			
		}}while(count<3);
		if(count==3){
			return 0;
		}
		return 1;
	}
	
	//��������
	public void updateLoginById1(){
		getAllLogin();
		System.out.println("��Ҫ���������ID�ǣ�");
		int id = input.nextInt();
		int row = lc.updateLoginById1(id);
		if(row>0){
			System.out.println("���óɹ���");
			getAllLogin();
		}else{
			System.out.println("����ʧ�ܡ�");
		}
	}
}
