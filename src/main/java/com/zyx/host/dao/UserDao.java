package com.zyx.host.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.zyx.host.entity.User;
public interface UserDao {
	
	User findUserById(Long id);

	List<User> queryUser();
	List<User> findUserApplyByStatus(int status);
	
	List<User> findUserByCondition(@Param("status") int status,@Param("uGroup") int uGroup);
	
	int insertUser(User user);

	int updateUser(User user);
	
	int updateUserApplypass(Long uId);
	
	int updateUserApplyfail(Long uId);

	int deleteUser(long uId);

	int batchDeleteUser(List<Long> uIdList);
}
