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
import com.zyx.host.dto.HostExecution;

import com.zyx.host.entity.Machine;
import com.zyx.host.entity.MachineApply;
import com.zyx.host.entity.MachineAudit;
import com.zyx.host.entity.User;
import com.zyx.host.enums.HostStatusEnum;
import com.zyx.host.service.HostService;
import com.zyx.host.service.MachineApplyService;
import com.zyx.host.service.MachineAuditService;
import com.zyx.host.service.UserService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class HostController {
	Logger logger=LoggerFactory.getLogger(HostController.class);
	
	@Autowired
	private HostService hostService;
	@Autowired
	private MachineApplyService machineApplyService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listhost",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> listHost(HttpServletRequest request){		
		Map<String,Object> map=new HashMap<>();
		List<Machine> list=new ArrayList<>();
		
		try {
			list=hostService.getMachineList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listhostbyugroup",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> listHostByuGroup(HttpServletRequest request){		
		Map<String,Object> map=new HashMap<>();
		//List<List<Machine>> list=new ArrayList<>();
		List<Machine> list=new ArrayList<>();
		List<User> userList=new ArrayList<>();
		int group=Integer.valueOf(request.getParameter("uGroup"));
		int status=2;
		try {
			userList=userService.findUserByCondition(status, group);
			for(User user:userList) {
				List<Machine> machineList=hostService.findHostByuId(user.getuId().intValue());
				for(Machine machine:machineList) {
					list.add(machine);
				}
				//list.add(machineList);
			}
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/addhost", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addHost(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(request.getParameter("hostStr"));
		//1、接收并转换相应的参数
		String hostStr=HttpServletRequestUtil.getString(request,"hostStr");
		ObjectMapper mapper = new ObjectMapper();
		Machine host = null;
		User user=userService.findUserById(Long.valueOf(request.getParameter("uId")));
		try {
			host=mapper.readValue(hostStr,Machine.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (host != null && user.getuRole()==1 && user.getStatus()==2) {
				host.setUpdateTime(new Date());
				host.setuId(request.getParameter("uId"));
				HostExecution se = hostService.addMachine(host);
				if (se.getState() == HostStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入正确的机器信息或您不是管理员");
			return modelMap;
		}
	}
	

	@RequestMapping(value="/deletehostbyid",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteHostById(@RequestParam Long mId){
		
		Map<String,Object> map=new HashMap<>();

		
		try {
			hostService.removeMachine(mId);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deletehostbyip",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteHostByIp(@RequestParam String mIp){
		
		Map<String,Object> map=new HashMap<>();

		
		try {
			hostService.removeMachineByIp(mIp);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deletehosts",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteHost(@RequestParam List<Long> mIdList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			hostService.removeMachineList(mIdList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/updatehost", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateHost(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Machine host = null;
		User user=userService.findUserById(Long.valueOf(request.getParameter("uId")));
		String hostStr=HttpServletRequestUtil.getString(request, "hostStr");		
		try {
			host = mapper.readValue(hostStr, Machine.class);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg1", e.toString());
			return modelMap;
		}
		if (host != null && user.getuRole()==1 && user.getStatus()==2) {
			try {
				host.setUpdateTime(new Date());
				HostExecution ae = hostService.modifyMachine(host);
				if (ae.getState() == HostStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg2", ae.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg3", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg4", "请输入正确的机器信息");
		}
		return modelMap;
	}
	
	
	@RequestMapping(value = "/updatehostbyip", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateHostByIp(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Machine host = null;
		User user=userService.findUserById(Long.valueOf(request.getParameter("uId")));
		String hostStr=HttpServletRequestUtil.getString(request, "hostStr");		
		try {
			host = mapper.readValue(hostStr, Machine.class);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg1", e.toString());
			return modelMap;
		}
		if (host != null && user.getuRole()==1 && user.getStatus()==2) {
			try {
				host.setUpdateTime(new Date());
				HostExecution ae = hostService.modifyMachineByIp(host);
				if (ae.getState() == HostStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg2", ae.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg3", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg4", "请输入正确的机器信息");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/registerhost", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerHost(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(request.getParameter("hostStr"));
		//1、接收并转换相应的参数
		String hostStr=HttpServletRequestUtil.getString(request,"hostStr");
		ObjectMapper mapper = new ObjectMapper();
		Machine host = null;
		try {
			host=mapper.readValue(hostStr,Machine.class);
			// decode可能有中文的地方
//			host.setmName((host.getmName() == null) ? null : URLDecoder
//							.decode(host.getmName(), "UTF-8"));
//			host.setDescription((host.getDescription() == null) ? null : (URLDecoder
//							.decode(host.getDescription(), "UTF-8")));
//			host.setOwner((host.getOwner()==null)?null:(URLDecoder.decode(host.getOwner(),"UTF-8")));
//			host.setmRoom((host.getmRoom()==null)? null:(URLDecoder.decode(host.getmRoom(),"UTF-8")));
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (host != null) {
				HostExecution se = hostService.addMachine(host);
				if (se.getState() == HostStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入机器信息");
			return modelMap;
		}
	}
	
	//把用户取到
	@RequestMapping(value="/listhostbycondition",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> findHostByCondition(HttpServletRequest request){
		int healthyStatusCode=1;
		int valid=1;
		
		Map<String,Object> map=new HashMap<>();
		List<Machine> list=new ArrayList<>();
		
		try {
			list=hostService.findHostByCondition(healthyStatusCode,valid);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}

/*
	@RequestMapping(value="/applyhost",method=RequestMethod.POST)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> applyHost(HttpServletRequest request){
//		String uId=request.getParameter("uId");
//		User user=userService.findUserById(Long.valueOf(uId));
//		String mId=request.getParameter("mId");
//		Machine machine=hostService.findHostById(Integer.valueOf(mId));
		MachineAudit machineAudit=new MachineAudit();
		
		Map<String,Object> modelMap=new HashMap<>();
		//1、接收并转换相应的参数
		String machineApplyStr=HttpServletRequestUtil.getString(request,"hostStr");
		ObjectMapper mapper = new ObjectMapper();
		MachineApply machineApply = null;
//		machineApply.setmId(machine.getmId());
		
		try {
			machineApply=mapper.readValue(machineApplyStr,MachineApply.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (machineApply != null) {
				int count = machineApplyService.addMachineApply(machineApply);
				if (count>0) {
					modelMap.put("success", true);
//					machineAudit.setAuditName(mId+"审核");
//					machineAudit.setmId(Long.valueOf(mId));
//					machineAudit.setStatus(1);
//					machineAudit.setuId(Long.valueOf(uId));
					machineAudit.setAuditName("审核"+machineApply.getmId());
					machineAudit.setmId(Long.valueOf(machineApply.getmId()));
					machineAudit.setStatus(1);
					machineAudit.setuId(Long.valueOf(machineApply.getuId()));
					machineAuditService.addMachineAudit(machineAudit);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "插入失败");
				}
				return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入审核信息");
			return modelMap;
		}
	}
*/	
	
	
	
	//1.查询机器方法，把机器取到 where status==0,valid==1,healthy_status_code==1
	 
	 //2.申请，把界面传的机器ids和用户信息传过来，添加机器申请表和机器审核表tb_machine_audit的status==1,userId
	 
	 //3.查询审批tb_machine_audit列表， where status==1,healthy_status_code==1
	 
	 //4.审批，审核通过：入参是审批表(tb_machine_audit)的ids，操作是把机器表的status改成2，修改机器表的u_id；审核失败：操作是把tb_machine_audit的status改成0；
	 
	 //5.查询审批通过列表(tb_machine_audit)， where status==2,valid==1,healthy_status_code==1,uId
	 
	 //6.新增白名单，入参是页面填写的信息+机器id,保存白名单写入数据库(status==1,valid==1)
	 
	 //7.查询白名单列表()where white_type
	 
	 //8.审核白名单，查询白名单审核列表where status==1,valid==1
	 
	 //9.审批，审核通过：入参是whiteIds，操作是把白名单表的status改成，把白名单id写到机器表 2；审核失败：入参是机器ids，操作是把status改成0；
}
