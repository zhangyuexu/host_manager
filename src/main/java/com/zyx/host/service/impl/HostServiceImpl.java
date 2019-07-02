package com.zyx.host.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyx.host.cache.JedisUtil;
import com.zyx.host.dao.HostDao;
import com.zyx.host.dto.HostExecution;

import com.zyx.host.entity.Machine;
import com.zyx.host.enums.HostStatusEnum;
import com.zyx.host.service.HostService;

@Service
public class HostServiceImpl implements HostService {
	@Autowired
	private HostDao hostDao;
	
	public Machine findHostById(int mId) {
		return hostDao.findHostById(mId);
	}
	public List<Machine> findHostByCondition(int healthyStatusCode,int valid){
		List<Machine> list=hostDao.findHostByCondition(healthyStatusCode, valid);
		return list;
	}
	@Override
	public List<Machine> getMachineList() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		List<Machine>list=hostDao.queryHost();
		return list;
	}

	@Override
	public HostExecution addMachine(Machine machine) {
		if (machine.getmName() != null && !"".equals(machine.getmName())) {
			machine.setUpdateTime(new Date());
			try {
				int effectedNum = hostDao.insertMachine(machine);
				if (effectedNum > 0) {
					return new HostExecution(HostStatusEnum.SUCCESS,machine);
				} else {
					return new HostExecution(HostStatusEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("添加机器信息失败:" + e.toString());
			}
		} else {
			return new HostExecution(HostStatusEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public HostExecution modifyMachine(Machine machine) {
		if (machine.getmId() != null && machine.getmId() > 0) {
			machine.setUpdateTime(new Date());
			try {
				int effectedNum = hostDao.updateMachine(machine);
				if (effectedNum > 0) {
					return new HostExecution(HostStatusEnum.SUCCESS, machine);
				} else {
					return new HostExecution(HostStatusEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("更新机器信息失败:" + e.toString());
			}
		} else {
			return new HostExecution(HostStatusEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public HostExecution removeMachine(long mId) {
		if (mId > 0) {
			try {
				int effectedNum = hostDao.deleteMachine(mId);
				if (effectedNum > 0) {
					return new HostExecution(HostStatusEnum.SUCCESS);
				} else {
					return new HostExecution(HostStatusEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除机器信息失败:" + e.toString());
			}
		} else {
			return new HostExecution(HostStatusEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public HostExecution removeMachineList(List<Long> mIdList) {
		if (mIdList != null && mIdList.size() > 0) {
			try {
				int effectedNum = hostDao.batchDeleteMachine(mIdList);
				if (effectedNum > 0) {
					return new HostExecution(HostStatusEnum.SUCCESS);
				} else {
					return new HostExecution(HostStatusEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除机器信息失败:" + e.toString());
			}
		} else {
			return new HostExecution(HostStatusEnum.EMPTY);
		}
	}
	@Override
	public HostExecution removeMachineByIp(String mIp) {
		if (mIp != null) {
			try {
				int effectedNum = hostDao.deleteMachineByIp(mIp);
				if (effectedNum > 0) {
					return new HostExecution(HostStatusEnum.SUCCESS);
				} else {
					return new HostExecution(HostStatusEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除机器信息失败:" + e.toString());
			}
		} else {
			return new HostExecution(HostStatusEnum.EMPTY);
		}
	}
	@Override
	public List<Machine> findHostByuId(int uId) {
		// TODO Auto-generated method stub
		return hostDao.findHostByuId(uId);
	}
	@Override
	@Transactional
	public HostExecution modifyMachineByIp(Machine machine) {
		if (machine.getmId() != null && machine.getmId() > 0) {
			machine.setUpdateTime(new Date());
			try {
				int effectedNum = hostDao.updateMachineByIp(machine);
				if (effectedNum > 0) {
					return new HostExecution(HostStatusEnum.SUCCESS, machine);
				} else {
					return new HostExecution(HostStatusEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("更新机器信息失败:" + e.toString());
			}
		} else {
			return new HostExecution(HostStatusEnum.EMPTY);
		}
	}
	
}
