package com.zyx.host.web.superadmin;
import java.util.ArrayList;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyx.host.entity.Machine;
import com.zyx.host.entity.User;
import com.zyx.host.service.UserService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class UserController {
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listuser",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> listUser(){
		Map<String,Object> map=new HashMap<>();
		List<User> list=new ArrayList<>();
		
		try {
			list=userService.getUserList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listuserbycondition",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> findUserByCondition(HttpServletRequest request){
		int status=2;
		int uGroup=Integer.valueOf(request.getParameter("uGroup"));
		
		Map<String,Object> map=new HashMap<>();
		List<User> list=new ArrayList<>();
		
		try {
			list=userService.findUserByCondition(status, uGroup);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listunaudituser",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listUnAuditUser(HttpServletRequest request){
		int status=1;
		
		Map<String,Object> map=new HashMap<>();
		List<User> list=new ArrayList<>();
		
		try {
			list=userService.findUserApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditpassuser",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditPassUser(HttpServletRequest request){
		int status=2;
		
		Map<String,Object> map=new HashMap<>();
		List<User> list=new ArrayList<>();
		
		try {
			list=userService.findUserApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditfailuser",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditFailUser(HttpServletRequest request){
		int status=3;
		
		Map<String,Object> map=new HashMap<>();
		List<User> list=new ArrayList<>();
		
		try {
			list=userService.findUserApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addUser(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(request.getParameter("userStr"));
		//1、接收并转换相应的参数
		String userStr=HttpServletRequestUtil.getString(request,"userStr");
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user=mapper.readValue(userStr,User.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (user != null) {
				user.setStatus(1);
				int count = userService.addUser(user);
				if (count>0) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "插入失败");
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入用户信息");
			return modelMap;
		}
	}
	

	@RequestMapping(value="/deleteuser",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteUser(@RequestParam Long uId){
		
		Map<String,Object> map=new HashMap<>();

		
		try {
			userService.removeUser(uId);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deleteusers",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteUsers(@RequestParam List<Long> uIdList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			userService.removeUserList(uIdList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateUser(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		String userStr=HttpServletRequestUtil.getString(request, "userStr");
		System.out.println(userStr);
		
		try {
			user = mapper.readValue(userStr, User.class);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg1", e.toString());
			return modelMap;
		}
		if (user != null && user.getuName() != null) {
			try {
				int count=userService.modifyUser(user);
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
			modelMap.put("errMsg4", "请输入正确的用户信息");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerUser(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(request.getParameter("userStr"));
		//1、接收并转换相应的参数
		String userStr=HttpServletRequestUtil.getString(request,"userStr");
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user=mapper.readValue(userStr,User.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (user != null) {
				user.setStatus(1);
				int count=userService.addUser(user);
				if (count>0) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "注册用户失败");
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入用户信息");
			return modelMap;
		}
	}
	
	@RequestMapping(value="/updateuserapplypass",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateUserApplyPass(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String uId=request.getParameter("uId");		
		int count=userService.updateUserApplypass(Long.valueOf(uId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updateuserapplyfail",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateUserApplyFail(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String uId=request.getParameter("uId");		
		int count=userService.updateUserApplyfail(Long.valueOf(uId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
	
}
