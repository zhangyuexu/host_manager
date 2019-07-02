package com.zyx.host.service;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.entity.White;
public interface WhiteService {
	
	White findWhiteById(Long id);
	
	List<White> getWhiteList() throws JsonParseException, JsonMappingException,
			IOException;

	List<White> findWhiteApplyByStatus(int status);
	
	int addWhite(White white);

	int modifyWhite(White white);
	
	int updateWhiteApplypass(Long whiteId);
	
	int updateWhiteApplyfail(Long whiteId);
	
	int removeWhite(long whiteId);

	int removeWhiteList(List<Long> whiteIdList);

}
