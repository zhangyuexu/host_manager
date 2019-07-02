package com.zyx.host.entity;

public class User {
	private Long uId;
	private String uName;
	private Integer uRole;
	private Integer uRight;
	private Integer uGroup;
	private Integer status;
	
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Integer getuRole() {
		return uRole;
	}
	public void setuRole(Integer uRole) {
		this.uRole = uRole;
	}
	public Integer getuRight() {
		return uRight;
	}
	public void setuRight(Integer uRight) {
		this.uRight = uRight;
	}
	public Integer getuGroup() {
		return uGroup;
	}
	public void setuGroup(Integer uGroup) {
		this.uGroup = uGroup;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
