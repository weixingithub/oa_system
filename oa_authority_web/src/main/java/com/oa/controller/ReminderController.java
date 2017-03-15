package com.oa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.oa_bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tool.util.OACommon;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年7月16日 上午11:13:16 
 * @version 1.0 
 */
@Controller
@RequestMapping("/oa/reminder")
public class ReminderController {
	@Autowired
	protected TaskService taskService;
	
	@RequestMapping("/task")
	@ResponseBody
	public Map<String,String> reminderTask(HttpServletRequest request){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
        String userName = sysUser.getUserName();
        Map<String,String> map = new HashMap<String,String>();
        Date today = new Date();
        int todotask=0;
        int donetask=0;
		List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(userName).list();
		for(Task task:tasks){
			if(task.getDueDate().getTime()>=today.getTime()){
				todotask++;
			}else{
				donetask++;
			}
		}
		
		if(tasks.size()>0){
			StringBuffer msg=new StringBuffer();
			String  overhref="<p><a href=\""+request.getContextPath()+"/oa/pwelfare/overtimetask\" rel=\""+OACommon.modelMap.get("/oa/pwelfare/overtimetask")+"\" target=\"navTab\" onclick=\"ajaxTodoTask(this,event)\">";
			String todohref ="<p><a href=\""+request.getContextPath()+"/oa/pwelfare/todotask\" rel=\""+OACommon.modelMap.get("/oa/pwelfare/todotask")+"\" target=\"navTab\" onclick=\"ajaxTodoTask(this,event)\">";
			if(todotask>0){
				msg.append(todohref);
				msg.append("<font color=\"FF8000\">您有"+todotask+"条待办任务,请尽快处理</font>");
				msg.append("</a></p>");
			}
			if(donetask>0){
				msg.append(overhref);
				msg.append("<font color=\"FF0000\">您有"+donetask+"条超时任务,请尽快处理</font>");
				msg.append("</a></p>");
			}
			map.put("warn", msg.toString());
		}else{
			map.put("warn", "nothing");
		}
		return map;
	}
   
}
