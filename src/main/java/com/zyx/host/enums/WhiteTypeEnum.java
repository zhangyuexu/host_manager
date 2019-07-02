package com.zyx.host.enums;

public enum WhiteTypeEnum {
	PUBLICWHITE(1,"公共白名单"),PRIVATEWHITE(2,"私有白名单"),LOCALWHITE(3,"本机白名单");
	
	private int state;
	private String stateInfo;
	
	private WhiteTypeEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static WhiteTypeEnum stateOf(int index) {
		for(WhiteTypeEnum state:values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
