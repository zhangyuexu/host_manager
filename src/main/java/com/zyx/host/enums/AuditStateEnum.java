package com.zyx.host.enums;

public enum AuditStateEnum {
	UNPROCESS(0, "待处理"), AUDIT_SUCCESS(1, "审核通过"), REFUSE(-1, "拒绝");

	private int state;

	private String stateInfo;

	private AuditStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AuditStateEnum stateOf(int index) {
		for (AuditStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
