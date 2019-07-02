package com.zyx.host.enums;

public enum WhiteBlongTreeEnum {
	PUBLICSERVICE(1,"公共服务"),ENGINE(2,"engine仿真环境"),QA(3,"qa仿真环境");
	
	private int state;
	private String stateInfo;
	
	private WhiteBlongTreeEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static WhiteBlongTreeEnum stateOf(int index) {
		for(WhiteBlongTreeEnum state:values()) {
			if(state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
