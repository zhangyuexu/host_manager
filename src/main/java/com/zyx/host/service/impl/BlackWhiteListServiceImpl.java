package com.zyx.host.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyx.host.cache.JedisUtil;
import com.zyx.host.dao.BlackWhiteListDao;
import com.zyx.host.dao.WhiteDao;
import com.zyx.host.entity.BlackWhiteList;
import com.zyx.host.entity.White;
import com.zyx.host.service.BlackWhiteListService;
import com.zyx.host.service.WhiteService;


@Service
public class BlackWhiteListServiceImpl implements BlackWhiteListService {
	@Autowired
	BlackWhiteListDao blackWhiteListDao;
	@Override
	public BlackWhiteList findBlackWhiteListById(Long mId) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.findBlackWhiteListById(mId);
	}

	@Override
	public List<BlackWhiteList> queryBlackWhiteList() {
		// TODO Auto-generated method stub
		return blackWhiteListDao.queryBlackWhiteList();
	}

	@Override
	public List<BlackWhiteList> findBlackWhiteListByType(int isWhite) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.findBlackWhiteListByType(isWhite);
	}

	@Override
	public int insertBlackWhiteList(BlackWhiteList blackWhiteList) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.insertBlackWhiteList(blackWhiteList);
	}

	@Override
	public int updateBlackWhiteList(BlackWhiteList blackWhiteList) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.updateBlackWhiteList(blackWhiteList);
	}

	@Override
	public int updateWhite(Long mId) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.updateWhite(mId);
	}

	@Override
	public int updateBlack(Long mId) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.updateBlack(mId);
	}

	@Override
	public int deleteBlackWhiteList(Long mId) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.deleteBlackWhiteList(mId);
	}

	@Override
	public int batchDeleteBlackWhiteList(List<Long> mIdList) {
		// TODO Auto-generated method stub
		return blackWhiteListDao.batchDeleteBlackWhiteList(mIdList);
	}
	
}
