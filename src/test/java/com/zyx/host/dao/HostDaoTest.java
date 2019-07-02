package com.zyx.host.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zyx.host.BaseTest;
import com.zyx.host.entity.Machine;
public class HostDaoTest extends BaseTest {
	@Autowired
	private HostDao hostDao;
	@Test
	public void testQueryMachine() {
		List<Machine> hostList=hostDao.queryHost();
		assertEquals(1, hostList.size());
	}
}
