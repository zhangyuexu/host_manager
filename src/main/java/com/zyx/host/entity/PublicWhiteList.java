package com.zyx.host.entity;

import java.util.Date;

public class PublicWhiteList {
	private String lIp;
	private String lName;
	private Integer isWhite;
	private String personInCharge;
	private String description;
	private Date updateTime;
	private Integer publicListType;
	public String getlIp() {
		return lIp;
	}
	public void setlIp(String lIp) {
		this.lIp = lIp;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public Integer getIsWhite() {
		return isWhite;
	}
	public void setIsWhite(Integer isWhite) {
		this.isWhite = isWhite;
	}
	public String getPersonInCharge() {
		return personInCharge;
	}
	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getPublicListType() {
		return publicListType;
	}
	public void setPublicListType(Integer publicListType) {
		this.publicListType = publicListType;
	}
	
}
