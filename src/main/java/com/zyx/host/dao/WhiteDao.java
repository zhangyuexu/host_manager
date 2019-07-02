package com.zyx.host.dao;
import java.util.List;
import com.zyx.host.entity.White;
public interface WhiteDao {
	
	White findWhiteById(Long whiteId);

	List<White> queryWhite();
	
	List<White> findWhiteApplyByStatus(int status);
	
	int insertWhite(White white);

	int updateWhite(White white);

	int updateWhiteApplypass(Long whiteId);
	
	int updateWhiteApplyfail(Long whiteId);

	int deleteWhite(long whiteId);

	int batchDeleteWhite(List<Long> whiteIdList);
}
