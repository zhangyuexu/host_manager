package com.zyx.host.enums;

public enum UseTypeEnum {
	MOCK(1,"mock服务"),DEVELOP(2,"开发"),TEST(3,"测试"),PLATFOM(4,"平台"),
	PRE(5,"预发"),ZHUNCHU(6,"准出"),BAK(7,"备机"),FANGZHEN(8,"仿真");
	
	private int state;
	private String stateInfo;
	
	private UseTypeEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static UseTypeEnum stateOf(int index) {
		for(UseTypeEnum state:values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
