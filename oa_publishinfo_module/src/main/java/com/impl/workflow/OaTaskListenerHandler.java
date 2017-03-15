package com.impl.workflow;

import java.util.Date;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月6日 下午5:01:17 
 * @version 1.0 
 */
@Component
@Transactional
public class OaTaskListenerHandler implements TaskListener {
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		String dealp = (String) delegateTask.getVariable("assignp");
		Date dueDate = (Date) delegateTask.getVariable("dueDate");
		if(dealp != null && !"".equals(dealp)){
			delegateTask.setAssignee(dealp);
		}
		delegateTask.setDueDate(dueDate);
		
	}

}
