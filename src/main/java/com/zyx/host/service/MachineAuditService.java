package com.zyx.host.service;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.entity.MachineAudit;
public interface MachineAuditService {
	
	MachineAudit findMachineAuditById(Long auditId);
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	List<MachineAudit> getMachineAuditList() throws JsonParseException, JsonMappingException,
			IOException;

	List<MachineAudit> findMachineAuditByCondition(int status);
	
	int addMachineAudit(MachineAudit machineAudit);

	
	int modifyMachineAudit(MachineAudit machineAudit);

	
	int removeMachineAudit(long auditId);

	
	int removeMachineAuditList(List<Long> auditIdList);

}
