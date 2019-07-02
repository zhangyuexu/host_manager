package com.zyx.host.entity;

import java.util.Date;
public class Machine {
	private Long mId;
	private String mName;
	private String mIp;
	private String mRoom;
	private Integer healthyStatusCode;
	private Integer blongTree;
	private Integer mType;
	private Integer businessType;
	private String owner;
	private String useType;
	private Date updateTime;
	private String cpuRate;
	private String diskRate;
	private String memRate;
	private String description;
	private String productLine;
	private Integer serviceCode;

	private Integer valid;
	private String uId;
	private String whiteId;
	
	
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmIp() {
		return mIp;
	}
	public void setmIp(String mIp) {
		this.mIp = mIp;
	}
	public String getmRoom() {
		return mRoom;
	}
	public void setmRoom(String mRoom) {
		this.mRoom = mRoom;
	}
	public Integer getHealthyStatusCode() {
		return healthyStatusCode;
	}
	public void setHealthyStatusCode(Integer healthyStatusCode) {
		this.healthyStatusCode = healthyStatusCode;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCpuRate() {
		return cpuRate;
	}
	public void setCpuRate(String cpuRate) {
		this.cpuRate = cpuRate;
	}
	public String getDiskRate() {
		return diskRate;
	}
	public void setDiskRate(String diskRate) {
		this.diskRate = diskRate;
	}
	public String getMemRate() {
		return memRate;
	}
	public void setMemRate(String memRate) {
		this.memRate = memRate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public Integer getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(Integer serviceCode) {
		this.serviceCode = serviceCode;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getWhiteId() {
		return whiteId;
	}
	public void setWhiteId(String whiteId) {
		this.whiteId = whiteId;
	}
	public Integer getBlongTree() {
		return blongTree;
	}
	public void setBlongTree(Integer blongTree) {
		this.blongTree = blongTree;
	}
	public Integer getmType() {
		return mType;
	}
	public void setmType(Integer mType) {
		this.mType = mType;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

}
