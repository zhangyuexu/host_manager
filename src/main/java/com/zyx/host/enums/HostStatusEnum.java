package com.zyx.host.enums;

public enum HostStatusEnum {
	OFFLINE(-1, "非法机器"), SUCCESS(0, "操作成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(
			-1002, "机器信息为空");

	private int state;

	private String stateInfo;

	private HostStatusEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static HostStatusEnum stateOf(int index) {
		for (HostStatusEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
