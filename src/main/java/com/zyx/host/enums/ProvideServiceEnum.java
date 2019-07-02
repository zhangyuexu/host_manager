package com.zyx.host.enums;

public enum ProvideServiceEnum {
	JDK(1,"JDK服务"),PYTHON(2,"Python环境"),GO(3,"GO环境"),TOMCAT(4,"tomcat服务"),
	MAVEN(5,"maven服务"),MYSQL(6,"MySQL服务");
	
	private int state;
	private String stateInfo;
	
	private ProvideServiceEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	
	public static ProvideServiceEnum stateOf(int index) {
		for(ProvideServiceEnum state:values()){
			if(state.getState()==index) {
				return state;
			}
		}
		return null;
	}

}
