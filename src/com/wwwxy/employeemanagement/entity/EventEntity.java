package com.wwwxy.employeemanagement.entity;

public class EventEntity {
	//事项id
	private int eId;
	//事项员工id
	private int eMpid;
	//事项类型(迟到早退)
	private int eClocking;
	//事项加班时间
	private int eOvertime;
	//事项矿工次数
	private int eBigevent;
	//工资评定
	private int eAward;
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public int geteMpid() {
		return eMpid;
	}
	public void seteMpid(int eMpid) {
		this.eMpid = eMpid;
	}
	public int geteClocking() {
		return eClocking;
	}
	public void seteClocking(int eClocking) {
		this.eClocking = eClocking;
	}
	public int geteOvertime() {
		return eOvertime;
	}
	public void seteOvertime(int eOvertime) {
		this.eOvertime = eOvertime;
	}
	public int geteBigevent() {
		return eBigevent;
	}
	public void seteBigevent(int bigevent) {
		this.eBigevent = bigevent;
	}
	public int geteAward() {
		return eAward;
	}
	public void seteAward(int eAward) {
		this.eAward = eAward;
	}
}
