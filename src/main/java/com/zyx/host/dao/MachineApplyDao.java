package com.zyx.host.dao;
import java.util.List;
import com.zyx.host.entity.MachineApply;
public interface MachineApplyDao {
	
	MachineApply findMachineApplyById(Long machineApplyId);

	List<MachineApply> queryMachineApply();
	
	List<MachineApply> findMachineApplyByStatus(int status);
	
	int insertMachineApply(MachineApply machineApply);

	
	int updateMachineApply(MachineApply machineApply);
	
	int updateMachineApplypass(Long machineApplyId);
	
	int updateMachineApplyfail(Long machineApplyId);

	int deleteMachineApply(long machineApplyId);

	
	int batchDeleteMachineApply(List<Long> machineApplyIdList);
}
