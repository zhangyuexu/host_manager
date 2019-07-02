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
import com.zyx.host.service.BlackWhiteListService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class BlackWhiteListController {
	Logger logger=LoggerFactory.getLogger(BlackWhiteListController.class);
	
	@Autowired
	private BlackWhiteListService blackWhiteListService;
	
	@RequestMapping(value="/listblackwhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listBlackWhite(){
		Map<String,Object> map=new HashMap<>();
		List<BlackWhiteList> list=new ArrayList<>();
		try {
			list=blackWhiteListService.queryBlackWhiteList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listwhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listWhite(HttpServletRequest request){
		int isWhite=1;
		
		Map<String,Object> map=new HashMap<>();
		List<BlackWhiteList> list=new ArrayList<>();
		
		try {
			list=blackWhiteListService.findBlackWhiteListByType(isWhite);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listblack",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listBlack(HttpServletRequest request){
		int isWhite=2;
		
		Map<String,Object> map=new HashMap<>();
		List<BlackWhiteList> list=new ArrayList<>();
		
		try {
			list=blackWhiteListService.findBlackWhiteListByType(isWhite);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	
	@RequestMapping(value="/applyblackwhitelist",method=RequestMethod.POST)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> applyBlackWhiteList(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
		Map<String,Object> modelMap=new HashMap<>();
		String blackWhiteListStr=HttpServletRequestUtil.getString(request,"blackWhiteListStr");
		ObjectMapper mapper = new ObjectMapper();
		BlackWhiteList blackWhiteList = null;
		blackWhiteList=mapper.readValue(blackWhiteListStr, BlackWhiteList.class);

		if (blackWhiteList != null) {
				blackWhiteList.setUpdateTime(new Date());
				int count = blackWhiteListService.insertBlackWhiteList(blackWhiteList);
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

	@RequestMapping(value="/deleteblackwhitelists",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteBlackWhiteLists(@RequestParam List<Long> mIdList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			blackWhiteListService.batchDeleteBlackWhiteList(mIdList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deleteblackwhitelists",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> deleteBlackWhiteList(@RequestParam Long mId){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			blackWhiteListService.deleteBlackWhiteList(mId);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/updateblackwhitelist",method=RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateBlackWhiteList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		BlackWhiteList blackWhiteList = null;
		String blackWhiteListStr=HttpServletRequestUtil.getString(request, "blackWhiteListStr");		
		try {
			blackWhiteList = mapper.readValue(blackWhiteListStr, BlackWhiteList.class);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg1", e.toString());
			return modelMap;
		}
		if (blackWhiteList != null) {
			try {
				int count=blackWhiteListService.updateBlackWhiteList(blackWhiteList);
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
	
	@RequestMapping(value="/updatewhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateWhite(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String mId=request.getParameter("mId");		
		int count=blackWhiteListService.updateWhite(Long.valueOf(mId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updateblack",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateBlack(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String mId=request.getParameter("mId");		
		int count=blackWhiteListService.updateBlack(Long.valueOf(mId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
}
