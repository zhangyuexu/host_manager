package com.zyx.host.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyx.host.cache.JedisUtil;
import com.zyx.host.dao.MachineAuditDao;
import com.zyx.host.entity.MachineAudit;
import com.zyx.host.entity.User;
import com.zyx.host.service.MachineAuditService;

@Service
public class MachineAuditServiceImpl implements MachineAuditService {
	@Autowired
	private MachineAuditDao machineAuditDao;

	@Override
	public MachineAudit findMachineAuditById(Long auditId) {
		// TODO Auto-generated method stub
		return machineAuditDao.findMachineAuditById(auditId);
	}

	@Override
	public List<MachineAudit> getMachineAuditList() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return machineAuditDao.queryMachineAudit();
	}

	@Override
	public int addMachineAudit(MachineAudit machineAudit) {
		// TODO Auto-generated method stub
		return machineAuditDao.insertMachineAudit(machineAudit);
	}

	@Override
	public int modifyMachineAudit(MachineAudit machineAudit) {
		// TODO Auto-generated method stub
		return machineAuditDao.updateMachineAudit(machineAudit);
	}

	@Override
	public int removeMachineAudit(long auditId) {
		// TODO Auto-generated method stub
		return machineAuditDao.deleteMachineAudit(auditId);
	}

	@Override
	public int removeMachineAuditList(List<Long> auditIdList) {
		// TODO Auto-generated method stub
		return machineAuditDao.batchDeleteMachineAudit(auditIdList);
	}

	@Override
	public List<MachineAudit> findMachineAuditByCondition(int status) {
		// TODO Auto-generated method stub
		return machineAuditDao.findMachineAuditByCondition(status);
	}
	
}
