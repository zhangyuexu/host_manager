package com.zyx.host.enums;

public enum UserRightEnum {
	ADDHSOT(1,"增加机器"),DELHOST(2,"删除机器"),MODIFYHOST(3,"修改机器"),FINDHOST(4,"查询机器"),
	APPROVE(5,"审批权限"),APPLY(6,"申请权限");
	
	private Integer code;
	private String desc;
	
	private UserRightEnum(Integer code,String desc) {
		this.code=code;
		this.desc=desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static UserRightEnum getTypeByCode(Integer code) {
		UserRightEnum defaultType=UserRightEnum.FINDHOST;
		for(UserRightEnum userRightEnum:UserRightEnum.values()) {
			if(userRightEnum.getCode()==code) {
				return userRightEnum;
			}
		}
		return defaultType;
	}
	
	public static String getDescByCode(Integer code) {
		return getTypeByCode(code).getDesc();
	}
}
