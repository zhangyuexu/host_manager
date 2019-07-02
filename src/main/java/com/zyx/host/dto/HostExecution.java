package com.zyx.host.dto;

import java.util.List;
import com.zyx.host.entity.Machine;
import com.zyx.host.enums.HostStatusEnum;

public class HostExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	private int count;

	private Machine machine;

	private List<Machine> machineList;

	public HostExecution() {
	}

	// 失败的构造器
	public HostExecution(HostStatusEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 成功的构造器
	public HostExecution(HostStatusEnum stateEnum, Machine machine) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.machine = machine;
	}

	// 成功的构造器
	public HostExecution(HostStatusEnum stateEnum, List<Machine> machineList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.machineList = machineList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}



}
