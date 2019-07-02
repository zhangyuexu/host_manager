package com.zyx.host.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.dao.MachineApplyDao;
import com.zyx.host.entity.MachineApply;
import com.zyx.host.service.MachineApplyService;


@Service
public class MachineApplyServiceImpl implements MachineApplyService {
	@Autowired
	private MachineApplyDao machineApplyDao;

	@Override
	public MachineApply findMachineApplyById(Long machineApplyId) {
		// TODO Auto-generated method stub
		return machineApplyDao.findMachineApplyById(machineApplyId);
	}

	@Override
	public List<MachineApply> getMachineApplyList() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return machineApplyDao.queryMachineApply();
	}

	@Override
	public int addMachineApply(MachineApply machineApply) {
		// TODO Auto-generated method stub
		return machineApplyDao.insertMachineApply(machineApply);
	}

	@Override
	public int modifyMachineApply(MachineApply machineApply) {
		// TODO Auto-generated method stub
		return machineApplyDao.updateMachineApply(machineApply);
	}

	@Override
	public int removeMachineApply(long machineApplyId) {
		// TODO Auto-generated method stub
		return machineApplyDao.deleteMachineApply(machineApplyId);
	}

	@Override
	public int removeMachineApplyList(List<Long> machineApplyIdList) {
		// TODO Auto-generated method stub
		return machineApplyDao.batchDeleteMachineApply(machineApplyIdList);
	}

	@Override
	public List<MachineApply> findMachineApplyByStatus(int status) {
		// TODO Auto-generated method stub
		return machineApplyDao.findMachineApplyByStatus(status);
	}

	@Override
	public int updateMachineApplypass(Long machineApplyId) {
		// TODO Auto-generated method stub
		return machineApplyDao.updateMachineApplypass(machineApplyId);
	}

	@Override
	public int updateMachineApplyfail(Long machineApplyId) {
		// TODO Auto-generated method stub
		return machineApplyDao.updateMachineApplyfail(machineApplyId);
	}

	
}
