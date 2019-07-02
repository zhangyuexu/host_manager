package com.zyx.host.dao;
import java.util.List;

import com.zyx.host.entity.MachineAudit;
public interface MachineAuditDao {
	
	MachineAudit findMachineAuditById(Long id);

	List<MachineAudit> queryMachineAudit();
	
	List<MachineAudit> findMachineAuditByCondition(int status);
	
	int insertMachineAudit(MachineAudit machineAudit);

	
	int updateMachineAudit(MachineAudit machineAudit);


	int deleteMachineAudit(long auditId);

	
	int batchDeleteMachineAudit(List<Long> auditIdList);
}
