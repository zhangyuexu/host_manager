package com.zyx.host.service;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.dto.HostExecution;
import com.zyx.host.entity.Machine;
public interface HostService {
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	List<Machine> findHostByCondition(int healthyStatusCode,int valid);
	
	Machine findHostById(int mId);
	
	List<Machine> findHostByuId(int uId);
	
	List<Machine> getMachineList() throws JsonParseException, JsonMappingException,
			IOException;

	/**
	 * 
	 * @param machine
	 * @return
	 */
	HostExecution addMachine(Machine machine);
	

	/**
	 * 
	 * @param machine
	 * @return
	 */
	HostExecution modifyMachine(Machine machine);

	HostExecution modifyMachineByIp(Machine machine);
	/**
	 * 
	 * @param mId
	 * @return
	 */
	HostExecution removeMachine(long mId);
	
	HostExecution removeMachineByIp(String mIp);

	/**
	 * 
	 * @param mIdList
	 * @return
	 */
	HostExecution removeMachineList(List<Long> mIdList);

}
