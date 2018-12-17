package com.wwwxy.employeemanagement.control;

import java.util.List;

import com.wwwxy.employeemanagement.dao.EventDao;
import com.wwwxy.employeemanagement.entity.EventEntity;


public class EventControl {
	EventDao ed = new EventDao();
	//查询所有事项
	public List<EventEntity> getAllEvent(){
		return ed.getAllEvent();
	}
	//新增事项
	public int addEventEntity(EventEntity t){
		return ed.addEventEntity(t);
	}
	//修改事项
	public int getEventEntityByid(EventEntity t){
		return ed.addEventEntity(t);
	}
	//删除事项
	public int DropEventEntity(int eId){
		return ed.DropEventEntity(eId);
	}
	
}
