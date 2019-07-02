package com.zyx.host.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zyx.host.dao.PublicBlackWhiteListDao;
import com.zyx.host.entity.PublicWhiteList;
import com.zyx.host.service.PublicBlackWhiteListService;


@Service
public class PublicBlackWhiteListServiceImpl implements PublicBlackWhiteListService {
	@Autowired
	PublicBlackWhiteListDao publicBlackWhiteListDao;

	@Override
	public PublicWhiteList findPublicBlackWhiteListByIp(String lIp) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.findPublicBlackWhiteListByIp(lIp);
	}

	@Override
	public List<PublicWhiteList> queryPublicBlackWhiteList() {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.queryPublicBlackWhiteList();
	}

	@Override
	public List<PublicWhiteList> findPublicBlackWhiteListByType(int publicListType) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.findPublicBlackWhiteListByType(publicListType);
	}

	@Override
	public int insertPublicBlackWhiteList(PublicWhiteList PublicBlackWhiteList) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.insertPublicBlackWhiteList(PublicBlackWhiteList);
	}

	@Override
	public int updatePublicBlackWhiteList(PublicWhiteList PublicBlackWhiteList) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.updatePublicBlackWhiteList(PublicBlackWhiteList);
	}

	@Override
	public int updateWhiteQA(String lIp) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.updateWhiteQA(lIp);
	}

	@Override
	public int updateWhiteRD(String lIp) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.updateWhiteRD(lIp);
	}

	@Override
	public int deletePublicBlackWhiteList(String lIp) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.deletePublicBlackWhiteList(lIp);
	}

	@Override
	public int batchDeletePublicBlackWhiteList(List<String> lIpList) {
		// TODO Auto-generated method stub
		return publicBlackWhiteListDao.batchDeletePublicBlackWhiteList(lIpList);
	}
	
}
