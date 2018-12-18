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
	Map<String,CheckDetails> mapEmp = new HashMap<>();//Map中的KEY为签到的日期
	private Map<String,List<CheckDetails>> map = new HashMap<>();
    //定义一个添加员工的方法
	public Map<String, CheckDetails> addCheckDetails(int cid,int empid,Timestamp ccheckin,
			Timestamp ccheckout,String cstatus,Date cdate){
        mapEmp.put(null, new CheckDetails());
        return mapEmp;
    }
	public void sign(){
		//签到
		Map<String,CheckDetails> mapEmp = new HashMap<>();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-xx-ll");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		String signDate = sdf.format(date);
        String checkTime = sdf1.format(date);
        System.out.println("请输入签到员工id");
        Scanner sc=new Scanner(System.in);
        int inputcid = sc.nextInt();
        if(!mapEmp.containsKey(inputcid)){
            System.out.println("不存在该员工");
            return;
        }
        List<CheckDetails> listCheck = null;
        if(!mapEmp.containsKey(signDate)){
        	//第一个签到的员工
            listCheck = new ArrayList<>();
            CheckDetails cd = new CheckDetails();
            cd.setEmpid(mapEmp.get(inputcid).getEmpid());
            cd.setCcheckin(Timestamp.valueOf("signTime"));
            cd.setCcheckout(Timestamp.valueOf(""));
            listCheck.add(cd);
            System.out.println("签到成功!");
        }else {
        	listCheck = map.get(signDate);
            //判断是否已经签到过
            boolean bIsFind = false;
            for (CheckDetails cd : listCheck) {
                if (cd.getEmpid() == mapEmp.get(inputcid).getEmpid()) {
                    System.out.println("已经签到过了");
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
                System.out.println("签到成功!");
            }
        }
		}
	public void signback(){
		//签退
		Scanner sc = new Scanner(System.in);
        int inputcid = sc.nextInt();
        String s = sc.nextLine();
        if(!mapEmp.containsKey(inputcid)){
            System.out.println("员工不存在");
            return;
        }

	}
	}

