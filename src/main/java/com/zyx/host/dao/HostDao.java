package com.zyx.host.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zyx.host.entity.Machine;
public interface HostDao {
	/**
	 * 列出机器列表
	 * 
	 * @param hostCondition
	 * @return
	 */
	
	List<Machine> findHostByCondition(@Param("healthyStatusCode") int healthyStatusCode,@Param("valid") int valid);
	
	Machine findHostById(int mId);
	List<Machine> findHostByuId(int uId);
	
	List<Machine> queryHost();

	/**
	 * 
	 * @param machine
	 * @return
	 */
	int insertMachine(Machine machine);

	/**
	 * 
	 * @param machine
	 * @return
	 */
	int updateMachine(Machine machine);

	int updateMachineByIp(Machine machine);
	/**
	 * 
	 * @param mId
	 * @return
	 */
	int deleteMachine(long mId);
	
	int deleteMachineByIp(String mIp);

	/**
	 * 
	 * @param mIdList
	 * @return
	 */
	int batchDeleteMachine(List<Long> mIdList);
}
