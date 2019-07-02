package com.zyx.host.enums;

public enum HealthyStatusEnum {
	HEALTHY(1,"健康"),UNHEALTHY(0,"故障");
	private int state;
	private String stateInfo;
	
	private HealthyStatusEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static HealthyStatusEnum stateOf(int index) {
		for(HealthyStatusEnum state:values()) {
			if(state.getState()==index) {
				return state;
			}
		}
		return null;
	}
}
