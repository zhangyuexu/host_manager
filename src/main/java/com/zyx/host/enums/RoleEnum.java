package com.zyx.host.enums;

public enum RoleEnum {
	SUPER(1,"超级管理员"),QAADMIN(2,"QA管理员"),NORMAL(3,"普通用户");
	
	private int state;
	private String stateInfo;
	
	private RoleEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static RoleEnum stateOf(int index) {
		for(RoleEnum state:values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
