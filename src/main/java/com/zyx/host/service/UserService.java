package com.zyx.host.service;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.entity.User;
public interface UserService {
	
	User findUserById(Long id);
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	List<User> getUserList() throws JsonParseException, JsonMappingException,
			IOException;

	List<User> findUserApplyByStatus(int status);
	
	List<User> findUserByCondition(int status,int uGroup);
	
	int addUser(User user);

	int modifyUser(User user);
	
	int updateUserApplypass(Long uId);
	
	int updateUserApplyfail(Long uId);
	
	int removeUser(long uId);

	int removeUserList(List<Long> uIdList);

}
