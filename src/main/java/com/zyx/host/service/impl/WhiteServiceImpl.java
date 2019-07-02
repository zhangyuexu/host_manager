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
import com.zyx.host.dao.WhiteDao;
import com.zyx.host.entity.White;
import com.zyx.host.service.WhiteService;


@Service
public class WhiteServiceImpl implements WhiteService {
	@Autowired
	private WhiteDao whiteDao;

	@Override
	public White findWhiteById(Long id) {
		// TODO Auto-generated method stub
		return whiteDao.findWhiteById(id);
	}

	@Override
	public List<White> getWhiteList() throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		return whiteDao.queryWhite();
	}

	@Override
	public int addWhite(White white) {
		// TODO Auto-generated method stub
		return whiteDao.insertWhite(white);
	}

	@Override
	public int modifyWhite(White white) {
		// TODO Auto-generated method stub
		return whiteDao.updateWhite(white);
	}

	@Override
	public int removeWhite(long whiteId) {
		// TODO Auto-generated method stub
		return whiteDao.deleteWhite(whiteId);
	}

	@Override
	public int removeWhiteList(List<Long> whiteIdList) {
		// TODO Auto-generated method stub
		return whiteDao.batchDeleteWhite(whiteIdList);
	}

	@Override
	public List<White> findWhiteApplyByStatus(int status) {
		// TODO Auto-generated method stub
		return whiteDao.findWhiteApplyByStatus(status);
	}

	@Override
	public int updateWhiteApplypass(Long whiteId) {
		// TODO Auto-generated method stub
		return whiteDao.updateWhiteApplypass(whiteId);
	}

	@Override
	public int updateWhiteApplyfail(Long whiteId) {
		// TODO Auto-generated method stub
		return whiteDao.updateWhiteApplyfail(whiteId);
	}


}
