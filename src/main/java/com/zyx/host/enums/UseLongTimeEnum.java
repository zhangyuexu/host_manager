package com.zyx.host.enums;

public enum UseLongTimeEnum {
	ONEDAY(1,"一天"),TWODAY(2,"两天"),THREEDAY(3,"三天"),ONEWEEK(4,"一周"),
	TWOWEEK(5,"两周"),ONEMONTH(6,"一个月"),LongTime(7,"长期");
	
	private Integer code;
	private String desc;
	
	private UseLongTimeEnum(Integer code,String desc){
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
	
	public static UseLongTimeEnum getTypeByCode(Integer code) {
		UseLongTimeEnum defaultType=UseLongTimeEnum.ONEDAY;
		for(UseLongTimeEnum useLongTimeEnum:UseLongTimeEnum.values()) {
			if(useLongTimeEnum.getCode()==code) {
				return useLongTimeEnum;
			}
		}
		return defaultType;
	}
	
	public static String getDescByCode(Integer code) {
		return getTypeByCode(code).getDesc();
	}
}
