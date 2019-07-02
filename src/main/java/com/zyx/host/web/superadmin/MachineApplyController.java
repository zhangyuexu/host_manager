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
import com.zyx.host.entity.MachineApply;
import com.zyx.host.entity.User;
import com.zyx.host.service.MachineApplyService;
import com.zyx.host.service.UserService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class MachineApplyController {
	Logger logger=LoggerFactory.getLogger(MachineApplyController.class);
	
	@Autowired
	private MachineApplyService machineApplyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listhostapply",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listHostApply(){
		Map<String,Object> map=new HashMap<>();
		List<MachineApply> list=new ArrayList<>();
		try {
			list=machineApplyService.getMachineApplyList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listunaudit",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listUnAudit(HttpServletRequest request){
		int status=1;
		
		Map<String,Object> map=new HashMap<>();
		List<MachineApply> list=new ArrayList<>();
		
		try {
			list=machineApplyService.findMachineApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditpass",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditPass(HttpServletRequest request){
		int status=2;
		
		Map<String,Object> map=new HashMap<>();
		List<MachineApply> list=new ArrayList<>();
		
		try {
			list=machineApplyService.findMachineApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditfail",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditFail(HttpServletRequest request){
		int status=3;
		
		Map<String,Object> map=new HashMap<>();
		List<MachineApply> list=new ArrayList<>();
		
		try {
			list=machineApplyService.findMachineApplyByStatus(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
//	@RequestMapping(value="/applyhost",method=RequestMethod.POST)
//	//自动让返回给前端的内容转换为json格式
//	@ResponseBody
//	private Map<String,Object> applyHost(HttpServletRequest request){
////		String uId=request.getParameter("uId");
////		User user=userService.findUserById(Long.valueOf(uId));
////		String mId=request.getParameter("mId");
////		Machine machine=hostService.findHostById(Integer.valueOf(mId));
//		MachineAudit machineAudit=new MachineAudit();
//		
//		Map<String,Object> modelMap=new HashMap<>();
//		//1、接收并转换相应的参数
//		String machineApplyStr=HttpServletRequestUtil.getString(request,"hostStr");
//		ObjectMapper mapper = new ObjectMapper();
//		MachineApply machineApply = null;
////		machineApply.setmId(machine.getmId());
//		
//		try {
//			machineApply=mapper.readValue(machineApplyStr,MachineApply.class);
//		}catch(Exception e) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", e.getMessage());
//			return modelMap;
//		}
//		
//		//2、注册
//		if (machineApply != null) {
//				int count = machineApplyService.addMachineApply(machineApply);
//				if (count>0) {
//					modelMap.put("success", true);
////					machineAudit.setAuditName(mId+"审核");
////					machineAudit.setmId(Long.valueOf(mId));
////					machineAudit.setStatus(1);
////					machineAudit.setuId(Long.valueOf(uId));
//					machineAudit.setAuditName("审核"+machineApply.getmId());
//					machineAudit.setmId(Long.valueOf(machineApply.getmId()));
//					machineAudit.setStatus(1);
//					machineAudit.setuId(Long.valueOf(machineApply.getuId()));
//					machineAuditService.addMachineAudit(machineAudit);
//				}else {
//					modelMap.put("success", false);
//					modelMap.put("errMsg", "插入失败");
//				}
//				return modelMap;
//		}else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "请输入审核信息");
//			return modelMap;
//		}
//	}
	
	
	@RequestMapping(value="/applyhost",method=RequestMethod.POST)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> applyHost(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<>();
		//1、接收并转换相应的参数
		String machineApplyStr=HttpServletRequestUtil.getString(request,"hostStr");
		ObjectMapper mapper = new ObjectMapper();
		MachineApply machineApply = null;
		User user=userService.findUserById(Long.valueOf(request.getParameter("uId")));
		try {
			machineApply=mapper.readValue(machineApplyStr,MachineApply.class);
			System.out.println(request.getParameter("mId"));
			System.out.println(request.getParameter("uId"));
			machineApply.setmId(Long.valueOf(request.getParameter("mId")));
			machineApply.setuId(Long.valueOf(request.getParameter("uId")));
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (machineApply != null && user.getuName().equals(machineApply.getApplyPerson()) &&user.getStatus()==2) {
				machineApply.setStatus(1);
				int count = machineApplyService.addMachineApply(machineApply);
				if (count>0) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "插入失败");
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入正确的申请信息或者该用户权限未审批");
			return modelMap;
		}
	}

	@RequestMapping(value="/deletehostapplys",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteHostApplys(@RequestParam List<Long> machineApplyIdList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			machineApplyService.removeMachineApplyList(machineApplyIdList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deletehostapply",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> deletehostApply(@RequestParam Long machineApplyId){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			machineApplyService.removeMachineApply(machineApplyId);
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
	
	@RequestMapping(value="/updateapplypass",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateApplyPass(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String machineApplyId=request.getParameter("machineApplyId");		
		int count=machineApplyService.updateMachineApplypass(Long.valueOf(machineApplyId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/updateapplyfail",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> updateApplyFail(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String machineApplyId=request.getParameter("machineApplyId");		
		int count=machineApplyService.updateMachineApplyfail(Long.valueOf(machineApplyId));
		if (count>0) {
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg2", "更新失败");
		}
		return modelMap;
	}
	
}
