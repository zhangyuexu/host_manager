package com.zyx.host.web.superadmin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyx.host.entity.BlackWhiteList;
import com.zyx.host.entity.PublicWhiteList;
import com.zyx.host.service.BlackWhiteListService;
import com.zyx.host.service.PublicBlackWhiteListService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class PublicBlackWhiteListController {
	Logger logger=LoggerFactory.getLogger(PublicBlackWhiteListController.class);
	
	@Autowired
	private PublicBlackWhiteListService publicBlackWhiteListService;
	
	@RequestMapping(value="/listpublicblackwhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listPublicBlackWhite(){
		Map<String,Object> map=new HashMap<>();
		List<PublicWhiteList> list=new ArrayList<>();
		try {
			list=publicBlackWhiteListService.queryPublicBlackWhiteList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listwhiteqa",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listWhiteQA(HttpServletRequest request){
		int publicListType=1;
		
		Map<String,Object> map=new HashMap<>();
		List<PublicWhiteList> list=new ArrayList<>();
		
		try {
			list=publicBlackWhiteListService.findPublicBlackWhiteListByType(publicListType);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listwhiterd",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listWhiteRD(HttpServletRequest request){
		int publicListType=2;
		
		Map<String,Object> map=new HashMap<>();
		List<PublicWhiteList> list=new ArrayList<>();
		
		try {
			list=publicBlackWhiteListService.findPublicBlackWhiteListByType(publicListType);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	
	@RequestMapping(value="/applypublicblackwhitelist",method=RequestMethod.POST)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> applyPublicBlackWhiteList(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		Map<String,Object> modelMap=new HashMap<>();
		String publicBlackWhiteListStr=HttpServletRequestUtil.getString(request,"publicBlackWhiteListStr");
		ObjectMapper mapper = new ObjectMapper();
		PublicWhiteList publicWhiteList = null;
		publicWhiteList=mapper.readValue(publicBlackWhiteListStr, PublicWhiteList.class);

		if (publicWhiteList != null) {
			publicWhiteList.setUpdateTime(new Date());
				int count = publicBlackWhiteListService.insertPublicBlackWhiteList(publicWhiteList);
				if (count>0) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "插入失败");
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入正确的申请信息或用户权限未审批");
			return modelMap;
		}
	}

	@RequestMapping(value="/deletepublicblackwhitelists",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deletePublicBlackWhiteLists(@RequestParam List<String> lIpList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			publicBlackWhiteListService.batchDeletePublicBlackWhiteList(lIpList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deletepublicblackwhitelists",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> deletePublicBlackWhiteList(@RequestParam String lIp){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			publicBlackWhiteListService.deletePublicBlackWhiteList(lIp);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/updatepublicblackwhitelist",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updatePublicBlackWhiteList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		PublicWhiteList publicWhiteList = null;
		String publicBlackWhiteListStr=HttpServletRequestUtil.getString(request, "publicBlackWhiteListStr");		
		try {
			publicWhiteList = mapper.readValue(publicBlackWhiteListStr, PublicWhiteList.class);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg1", e.toString());
			return modelMap;
		}
		if (publicWhiteList != null) {
			try {
				int count=publicBlackWhiteListService.updatePublicBlackWhiteList(publicWhiteList);
				if (count>0) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg2", "更新失败");
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg3", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg4", "请输入正确信息");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updatewhiteqa",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateWhiteQA(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String lIp=request.getParameter("lIp");		
		int count=publicBlackWhiteListService.updateWhiteQA(lIp);
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updatewhiterd",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateWhiteRD(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String lIp=request.getParameter("lIp");		
		int count=publicBlackWhiteListService.updateWhiteRD(lIp);
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
}
