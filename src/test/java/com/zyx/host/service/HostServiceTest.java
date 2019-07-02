package com.zyx.host.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zyx.host.BaseTest;

public class HostServiceTest extends BaseTest{
	@Autowired
	private HostService hostService;
	@Test
	public void testGetHostList() throws JsonParseException, JsonMappingException, IOException {
		//System.out.println(areaService.getAreaList().get(0).getAreaName());
		assertEquals("zyx", hostService.getMachineList().get(0).getmName());
	}

	@Test
	@Ignore
	public void testRegisterhost() throws JsonParseException, JsonMappingException, IOException {
		
	}
}
