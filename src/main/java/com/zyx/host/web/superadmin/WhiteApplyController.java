package com.zyx.host.web.superadmin;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyx.host.entity.User;
import com.zyx.host.entity.White;
import com.zyx.host.service.UserService;
import com.zyx.host.service.WhiteService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class WhiteApplyController {
	Logger logger=LoggerFactory.getLogger(WhiteApplyController.class);
	
	@Autowired
	private WhiteService whiteService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listwhiteapply",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listHostApply(){
		Map<String,Object> map=new HashMap<>();
		List<White> list=new ArrayList<>();
		try {
			list=whiteService.getWhiteList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listunauditwhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listUnAuditWhite(HttpServletRequest request){
		int status=1;
		
		Map<String,Object> map=new HashMap<>();
		List<White> list=new ArrayList<>();
		
		try {
			list=whiteService.findWhiteApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditpasswhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditPassWhite(HttpServletRequest request){
		int status=2;
		
		Map<String,Object> map=new HashMap<>();
		List<White> list=new ArrayList<>();
		
		try {
			list=whiteService.findWhiteApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditfailwhite",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditFailWhite(HttpServletRequest request){
		int status=3;
		
		Map<String,Object> map=new HashMap<>();
		List<White> list=new ArrayList<>();
		
		try {
			list=whiteService.findWhiteApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	
	@RequestMapping(value="/applywhite",method=RequestMethod.POST)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> applyWhite(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<>();
		//1、接收并转换相应的参数
		String whiteApplyStr=HttpServletRequestUtil.getString(request,"whiteStr");
		ObjectMapper mapper = new ObjectMapper();
		White white = null;
		User user=userService.findUserById(Long.valueOf(request.getParameter("uId")));
		try {
			white=mapper.readValue(whiteApplyStr,White.class);
			System.out.println(request.getParameter("mId"));
			System.out.println(request.getParameter("uId"));
			white.setmId(Long.valueOf(request.getParameter("mId")));
			white.setuId(Long.valueOf(request.getParameter("uId")));
			white.setUpdateTime(new Date());
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (white != null && user.getuName().equals(white.getApplyPerson()) && user.getStatus()==2) {
				white.setStatus(1);
				white.setUpdateTime(new Date());
				int count = whiteService.addWhite(white);
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

	@RequestMapping(value="/deletewhiteapplys",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteWhiteApplys(@RequestParam List<Long> whiteIdList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			whiteService.removeWhiteList(whiteIdList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deletewhiteapply",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> deleteWhiteApply(@RequestParam Long whiteId){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			whiteService.removeWhite(whiteId);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
//	@RequestMapping(value="/updatehostapply",method=RequestMethod.POST)
//	@ResponseBody
//	private Map<String, Object> updateHostApply(HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		ObjectMapper mapper = new ObjectMapper();
//		MachineApply machineApply = null;
//		String hostStr=HttpServletRequestUtil.getString(request, "hostStr");		
//		try {
//			machineApply = mapper.readValue(hostStr, MachineApply.class);
//			
//		} catch (Exception e) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg1", e.toString());
//			return modelMap;
//		}
//		System.out.println(machineApply.getApplyRoleType());
//		if (machineApply != null && machineApply.getMachineApplyId() != null) {
//			try {
//				int count=machineApplyService.modifyMachineApply(machineApply);
//				if (count>0) {
//					modelMap.put("success", true);
//				} else {
//					modelMap.put("success", false);
//					modelMap.put("errMsg2", "更新失败");
//				}
//			} catch (RuntimeException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg3", e.toString());
//				return modelMap;
//			}
//
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg4", "请输入正确信息");
//		}
//		return modelMap;
//	}
	
	@RequestMapping(value="/updatewhiteapplypass",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateWhiteApplyPass(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String whiteId=request.getParameter("whiteId");		
		int count=whiteService.updateWhiteApplypass(Long.valueOf(whiteId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updatewhiteapplyfail",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateWhiteApplyFail(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String whiteId=request.getParameter("whiteId");		
		int count=whiteService.updateWhiteApplyfail(Long.valueOf(whiteId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
}
