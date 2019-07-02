package com.zyx.host.enums;

public enum UserGroupEnum {
	DATAGROUP(1,"数据组"),STABLE(2,"稳定性组"),TOOL(3,"工具组"),PINCHE(4,"拼车组"),
	FENDAN(5,"分单组"),OTHERS(6,"其他");
	
	private Integer code;
	private String desc;
	
	private UserGroupEnum(Integer code,String desc) {
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
	
	public static UserGroupEnum getTypeByCode(Integer code) {
		UserGroupEnum defaultType=UserGroupEnum.DATAGROUP;
		for(UserGroupEnum userGroupEnum:UserGroupEnum.values()) {
			if(userGroupEnum.getCode()==code) {
				return userGroupEnum;
			}
		}
		return defaultType;
	}
	
	public static String getDescByCode(Integer code) {
		return getTypeByCode(code).getDesc();
	}
	
}
