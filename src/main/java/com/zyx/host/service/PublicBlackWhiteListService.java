package com.zyx.host.service;
import java.util.List;
import com.zyx.host.entity.PublicWhiteList;

public interface PublicBlackWhiteListService {
	PublicWhiteList findPublicBlackWhiteListByIp(String lIp);

	List<PublicWhiteList> queryPublicBlackWhiteList();
	
	List<PublicWhiteList> findPublicBlackWhiteListByType(int publicListType);
	
	int insertPublicBlackWhiteList(PublicWhiteList PublicBlackWhiteList);

	int updatePublicBlackWhiteList(PublicWhiteList PublicBlackWhiteList);

	int updateWhiteQA(String lIp);
	
	int updateWhiteRD(String lIp);

	int deletePublicBlackWhiteList(String lIp);

	int batchDeletePublicBlackWhiteList(List<String> lIpList);

}
