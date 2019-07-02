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
import com.zyx.host.dao.UserDao;
import com.zyx.host.entity.User;
import com.zyx.host.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}

	@Override
	public List<User> getUserList() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return userDao.queryUser();
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public int removeUser(long uId) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(uId);
	}

	@Override
	public int removeUserList(List<Long> uIdList) {
		// TODO Auto-generated method stub
		return userDao.batchDeleteUser(uIdList);
	}

	@Override
	public List<User> findUserApplyByStatus(int status) {
		// TODO Auto-generated method stub
		return userDao.findUserApplyByStatus(status);
	}

	@Override
	public int updateUserApplypass(Long uId) {
		// TODO Auto-generated method stub
		return userDao.updateUserApplypass(uId);
	}

	@Override
	public int updateUserApplyfail(Long uId) {
		// TODO Auto-generated method stub
		return userDao.updateUserApplyfail(uId);
	}

	@Override
	public List<User> findUserByCondition(int status, int uGroup) {
		// TODO Auto-generated method stub
		return userDao.findUserByCondition(status, uGroup);
	}
	
	
	
}
