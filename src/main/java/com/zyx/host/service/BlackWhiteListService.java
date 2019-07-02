package com.zyx.host.service;
import java.util.List;
import com.zyx.host.entity.BlackWhiteList;

public interface BlackWhiteListService {
	
	BlackWhiteList findBlackWhiteListById(Long mId);

	List<BlackWhiteList> queryBlackWhiteList();
	
	List<BlackWhiteList> findBlackWhiteListByType(int isWhite);
	
	int insertBlackWhiteList(BlackWhiteList blackWhiteList);

	int updateBlackWhiteList(BlackWhiteList blackWhiteList);

	int updateWhite(Long mId);
	
	int updateBlack(Long mId);

	int deleteBlackWhiteList(Long mId);

	int batchDeleteBlackWhiteList(List<Long> mIdList);

}
