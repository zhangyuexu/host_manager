package com.zyx.host.service;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.entity.MachineApply;
public interface MachineApplyService {
	
	MachineApply findMachineApplyById(Long machineApplyId);
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	List<MachineApply> getMachineApplyList() throws JsonParseException, JsonMappingException,
			IOException;
	
	List<MachineApply> findMachineApplyByStatus(int status);

	int addMachineApply(MachineApply machineApply);

	
	int modifyMachineApply(MachineApply machineApply);
	
	int updateMachineApplypass(Long machineApplyId);
	
	int updateMachineApplyfail(Long machineApplyId);
	
	int removeMachineApply(long machineApplyId);

	
	int removeMachineApplyList(List<Long> machineApplyIdList);

}
