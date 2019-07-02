package com.zyx.host.entity;

public class MachineApply {
	private Long machineApplyId;
	private Integer applyRoleType;
	private String applyPerson;
	private Integer useLongTime;
	private Integer status;
	private Long mId;
	private Long uId;
	public Long getMachineApplyId() {
		return machineApplyId;
	}
	public void setMachineApplyId(Long machineApplyId) {
		this.machineApplyId = machineApplyId;
	}
	public Integer getApplyRoleType() {
		return applyRoleType;
	}
	public void setApplyRoleType(Integer applyRoleType) {
		this.applyRoleType = applyRoleType;
	}
	public String getApplyPerson() {
		return applyPerson;
	}
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	public Integer getUseLongTime() {
		return useLongTime;
	}
	public void setUseLongTime(Integer useLongTime) {
		this.useLongTime = useLongTime;
	}
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
