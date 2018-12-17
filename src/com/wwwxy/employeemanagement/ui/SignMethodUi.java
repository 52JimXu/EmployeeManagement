package com.wwwxy.employeemanagement.ui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wwwxy.employeemanagement.entity.CheckDetails;

public class SignMethodUi {
	Map<String,CheckDetails> mapEmp = new HashMap<>();//Map�е�KEYΪǩ��������
	private Map<String,List<CheckDetails>> map = new HashMap<>();
    //����һ�����Ա���ķ���
	public Map<String, CheckDetails> addCheckDetails(int cid,int empid,Timestamp ccheckin,
			Timestamp ccheckout,String cstatus,Date cdate){
        mapEmp.put(null, new CheckDetails());
        return mapEmp;
    }
	public void sign(){
		//ǩ��
		Map<String,CheckDetails> mapEmp = new HashMap<>();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-xx-ll");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		String signDate = sdf.format(date);
        String checkTime = sdf1.format(date);
        System.out.println("������ǩ��Ա��id");
        Scanner sc=new Scanner(System.in);
        int inputcid = sc.nextInt();
        if(!mapEmp.containsKey(inputcid)){
            System.out.println("�����ڸ�Ա��");
            return;
        }
        List<CheckDetails> listCheck = null;
        if(!mapEmp.containsKey(signDate)){
        	//��һ��ǩ����Ա��
            listCheck = new ArrayList<>();
            CheckDetails cd = new CheckDetails();
            cd.setEmpid(mapEmp.get(inputcid).getEmpid());
            cd.setCcheckin(Timestamp.valueOf("signTime"));
            cd.setCcheckout(Timestamp.valueOf(""));
            listCheck.add(cd);
            System.out.println("ǩ���ɹ�!");
        }else {
        	listCheck = map.get(signDate);
            //�ж��Ƿ��Ѿ�ǩ����
            boolean bIsFind = false;
            for (CheckDetails cd : listCheck) {
                if (cd.getEmpid() == mapEmp.get(inputcid).getEmpid()) {
                    System.out.println("�Ѿ�ǩ������");
                    bIsFind = true;
                    break;
                }
            }
            if (!bIsFind) {
            	 CheckDetails cd = new  CheckDetails();
                cd.setEmpid(mapEmp.get(inputcid).getEmpid());
                cd.setCcheckin(Timestamp.valueOf("signTime"));
                cd.setCcheckout(Timestamp.valueOf(""));
                listCheck.add(cd);
                System.out.println("ǩ���ɹ�!");
            }
        }
		}
	public void signback(){
		//ǩ��
		Scanner sc = new Scanner(System.in);
        int inputcid = sc.nextInt();
        String s = sc.nextLine();
        if(!mapEmp.containsKey(inputcid)){
            System.out.println("Ա��������");
            return;
        }

	}
	}

