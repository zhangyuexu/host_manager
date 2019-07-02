package com.zyx.host.enums;

public enum ValidStateEnum {
	UNPROCESS(0, "待处理"), VALID_SUCCESS(1, "生效上线"), VALID_FAIL(-1, "失效下线");

	private int state;

	private String stateInfo;

	private ValidStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ValidStateEnum stateOf(int index) {
		for (ValidStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
