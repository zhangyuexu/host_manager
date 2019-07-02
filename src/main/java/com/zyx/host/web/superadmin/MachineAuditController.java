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
import com.zyx.host.entity.MachineAudit;
import com.zyx.host.service.MachineAuditService;
import com.zyx.host.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/superadmin")
public class MachineAuditController {
	Logger logger=LoggerFactory.getLogger(MachineAuditController.class);
	
	@Autowired
	private MachineAuditService machineAuditService;
	
	@RequestMapping(value="/listmachineaudit",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> listMachineAudit(){
		Map<String,Object> map=new HashMap<>();
		List<MachineAudit> list=new ArrayList<>();
		
		try {
			list=machineAuditService.getMachineAuditList();
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/listauditbycondition",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listAuditByCondition(HttpServletRequest request){
		int status=1;
		
		Map<String,Object> map=new HashMap<>();
		List<MachineAudit> list=new ArrayList<>();
		
		try {
			list=machineAuditService.findMachineAuditByCondition(status);
			map.put("rows", list);
			map.put("total", list.size());
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/addmachineaudit", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addMachineAudit(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		System.out.println(request.getParameter("machineAuditStr"));
		//1、接收并转换相应的参数
		String machineAuditStr=HttpServletRequestUtil.getString(request,"machineAuditStr");
		ObjectMapper mapper = new ObjectMapper();
		MachineAudit machineAudit = null;
		try {
			machineAudit=mapper.readValue(machineAuditStr,MachineAudit.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		//2、注册
		if (machineAudit != null) {
				int count = machineAuditService.addMachineAudit(machineAudit);
				if (count>0) {
					modelMap.put("success", true);
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
	

	@RequestMapping(value="/deletemachineaudit",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteMachineAudit(@RequestParam Long auditId){
		
		Map<String,Object> map=new HashMap<>();

		
		try {
			machineAuditService.removeMachineAudit(auditId);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value="/deletemachineaudits",method=RequestMethod.GET)
	//自动让返回给前端的内容转换为json格式
	@ResponseBody
	private Map<String,Object> deleteMachineAudits(@RequestParam List<Long> auditIdList){
		
		Map<String,Object> map=new HashMap<>();
		
		try {
			machineAuditService.removeMachineAuditList(auditIdList);
			map.put("success", "删除成功");
		}catch(Exception e) {
			map.put("success", false);
			map.put("errMsg", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/updatemachineaudit", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateMachineAudit(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		MachineAudit machineAudit = null;
		String machineAuditStr=HttpServletRequestUtil.getString(request, "machineAuditStr");
		System.out.println(machineAuditStr);
		
		try {
			machineAudit = mapper.readValue(machineAuditStr, MachineAudit.class);
			
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg1", e.toString());
			return modelMap;
		}
		if (machineAudit != null && machineAudit.getAuditName() != null) {
			try {
				int count=machineAuditService.modifyMachineAudit(machineAudit);
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
			modelMap.put("errMsg4", "请输入正确的审核信息");
		}
		return modelMap;
	}
	
}
