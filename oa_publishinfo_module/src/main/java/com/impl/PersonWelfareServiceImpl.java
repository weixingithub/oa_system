package com.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.oa_bean.Page;
import org.oa_bean.PersonWelfare;
import org.oa_bean.PersonWelfareContent;
import org.oa_bean.comment.CommentInfo;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PersonWelfareDao;
import com.dao.PersonWelfareService;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
@Service(value="personWelfareService")
public class PersonWelfareServiceImpl implements PersonWelfareService {
	private PersonWelfareDao personWelfareDao;
    
	protected TaskService taskService;
	
	private RuntimeService runtimeService;
	
	protected RepositoryService repositoryService;
	
	private IdentityService identityService;
	
	protected HistoryService historyService;
	
	private ManagementService managementService;
	
	public ManagementService getManagementService() {
		return managementService;
	}
	@Autowired
	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
	public HistoryService getHistoryService() {
		return historyService;
	}
	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	public IdentityService getIdentityService() {
		return identityService;
	}
	 @Autowired
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	public RuntimeService getRuntimeService() {
		return runtimeService;
	}
	 @Autowired
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	 @Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	 @Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public PersonWelfareDao getPersonWelfareDao() {
		return personWelfareDao;
	}
    @Autowired
	public void setPersonWelfareDao(PersonWelfareDao personWelfareDao) {
		this.personWelfareDao = personWelfareDao;
	}
	@Override
	@Transactional
	public boolean savePersonWelfareService(Integer id,String userName,String assigneperson,String finishtime) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			 PersonWelfare personWelfare = personWelfareDao.find(PersonWelfare.class, id);
			 personWelfare.setActivityuserId(userName);
			 personWelfare.setFlowstatus(1);
			 personWelfare.setFinishtime(finishtime);
			 personWelfare.setSignUserName(assigneperson);
		     String startTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		     personWelfare.setStarttime(startTime);
			 Integer pwId = personWelfare.getId();
		     ProcessInstance processInstance = null;
	         // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
	         identityService.setAuthenticatedUserId(personWelfare.getActivityuserId());
	         Map<String, Object> variables = new HashMap<String, Object>();
	         variables.put("assignp", assigneperson);
	         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	         variables.put("dueDate", dateFormat.parse(finishtime+":00"));
	         
	         processInstance = runtimeService.startProcessInstanceByKey("ConvenienceService", pwId.toString(), variables);
	         
	         String processInstanceId = processInstance.getId();
	         personWelfare.setProcessInstanceId(processInstanceId);
			 flag = true;
		}catch(Exception e){
			flag = false;
		}finally {
            identityService.setAuthenticatedUserId(null);
        }
		return flag;
	}
	@Override
	public Page<PersonWelfare> findOvertimeTasks(String userName,
			Page<PersonWelfare> page, int[] pageParams,
			PersonWelfare personWelfare){
	        return getTaskPersonPage(userName,  page, pageParams, personWelfare,3);
	}
	@Override
	public Page<PersonWelfare> findTodoTasks(String userName, Page<PersonWelfare> page,int pageParams[],PersonWelfare personWelfare) {
	      return getTaskPersonPage(userName,  page, pageParams, personWelfare,1);
		
	}
	  /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }
	@Override
	public PersonWelfare findPwById(Integer id) {
		PersonWelfare personWelfare = personWelfareDao.find(PersonWelfare.class, id);
		PersonWelfareContent personWelfareContent = personWelfareDao.find(PersonWelfareContent.class, id);
		personWelfare.setPersonWelfareContent(personWelfareContent);
		return personWelfare;
	}
	@Override
	@Transactional
	public boolean updatePersonWelfareService(PersonWelfare personWelfare) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			personWelfareDao.update(personWelfare);
			PersonWelfareContent personWelfareContent = personWelfare.getPersonWelfareContent();
			personWelfareContent.setId(personWelfare.getId());
			personWelfareDao.update(personWelfareContent);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public Page<PersonWelfare> findPersonWelfarePage(Page<PersonWelfare> page,
			PersonWelfare personWelfare, LinkedHashMap<String, String> orderby,String content,String orgids) {
		String pwids = null;
		//全文检索
		if(content != null && !"".equals(content)){
			QueryBuilder qbuery = personWelfareDao.getQueryBuilder(PersonWelfareContent.class);
			BooleanJunction<BooleanJunction> bjunction = qbuery.bool();
			bjunction.must(
					qbuery.phrase()
					.onField("content")
					.sentence(content)
					.createQuery()
			     );
			if(personWelfare.getModelId()!=null &&!"".equals(personWelfare.getModelId())){
				   bjunction.must(
						   qbuery.keyword()
		    				 .onField("modelId")
		    				 .matching(personWelfare.getModelId())
		    				 .createQuery()
						   );
			   }
			     if(orgids !=null && !"".equals(orgids)){
			    	 BooleanJunction<BooleanJunction> orgbjunction = qbuery.bool();
						String[] arr = orgids.split(",");
						 for(int j=0;j<arr.length;j++){
							 orgbjunction.should(
				    				 qbuery.keyword()
				    				 .onField("orgId")
				    				 .matching(arr[j])
				    				 .createQuery()
				    			);
						}
						
						 bjunction.must(orgbjunction.createQuery());	    
				}
			     
			     pwids = personWelfareDao.findPwIdByCIndex(bjunction.createQuery(), PersonWelfareContent.class);
		}
		
		StringBuffer wheresql = new StringBuffer(" 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(pwids != null){
			wheresql.append(" and id in ("+pwids+")");
		}else if(orgids !=null && !"".equals(orgids)){
			String[] arr = orgids.split(",");
			wheresql.append(" and orgId in (");
			for(int i=0;i<arr.length;i++){
				wheresql.append(arr[i]);
				if(i<arr.length-1){
					wheresql.append(",");
				}
			}
			wheresql.append(")");
		}
        if(pwids == null && personWelfare.getModelId()!=null &&!"".equals(personWelfare.getModelId())){
			wheresql.append(" and modelId in("+personWelfare.getModelId()+") ");
		}
		if(personWelfare.getUserId()!=null && personWelfare.getUserId() != 0){
			wheresql.append(" and userId = ? ");
	    	params.add(personWelfare.getUserId());
		}
		if(personWelfare.getTitle()!=null && !"".equals(personWelfare.getTitle())){
			wheresql.append(" and title like ? ");
	    	params.add("%"+personWelfare.getTitle().trim()+"%");
		}
		if(personWelfare.getFlowstatus()!=null && !"".equals(personWelfare.getFlowstatus())){
			wheresql.append(" and flowstatus = ?");
	    	params.add(personWelfare.getFlowstatus());
		}
		if(personWelfare.getFlag() != null){
			wheresql.append(" and flag = ? ");
			params.add(personWelfare.getFlag());
		}
		return personWelfareDao.findPersonWelfarePage(page, wheresql.toString(), params, orderby);
	}
	@Override
	@Transactional
	public boolean addAndUpdatePersonWelfare(PersonWelfare personWelfare) {
		boolean flag = false;
		try{
			if(personWelfare.getId()==null || personWelfare.getId()== 0 ){
				if(personWelfare.getCommentSwitch() != 0){
					CommentInfo commentInfo = new CommentInfo();
					commentInfo.setTitle(personWelfare.getTitle());
					commentInfo.setOrgId(personWelfare.getOrgId());
					commentInfo.setModelId(personWelfare.getModelId());
					commentInfo.setPraiseNumber(0L);
					commentInfo.setNegativeNumber(0L);
					commentInfo.setBrowseNumber(0L);
					commentInfo.setType(1); 	//评论对象类型：1文章，2活动
					personWelfare.setCommentInfo(commentInfo);
				}
				String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
				personWelfare.setCreattime(createTime);
				personWelfare.setFlowstatus(0);//初始化审核状态
				personWelfareDao.save(personWelfare);
				personWelfareDao.save(personWelfare.getPersonWelfareContent());
			}else{
				if(personWelfare.getCommentSwitch() != 0){
					  CommentInfo commentInfo = personWelfare.getCommentInfo();
					  if(commentInfo == null ){
						  personWelfare.setCommentInfo(new CommentInfo());
					  }else{
						  commentInfo.setOrgId(personWelfare.getOrgId());
						  commentInfo.setCreateUserId(personWelfare.getUserId());
						  commentInfo.setModelId(personWelfare.getModelId());
						  personWelfare.setCommentInfo(commentInfo);
					  }
				}
				
				personWelfareDao.update(personWelfare);
				PersonWelfareContent personWelfareContent = personWelfare.getPersonWelfareContent();
				personWelfareContent.setId(personWelfare.getId());
				personWelfareDao.update(personWelfareContent);
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean delPersonWelfare(String ids) {
				boolean flag = false;
				try{
					String [] entityids = ids.split(",");
					Integer[] arr = new Integer[entityids.length];
					for(int i = 0;i<entityids.length;i++){
						arr[i] = Integer.parseInt(entityids[i]);
					}	
					personWelfareDao.delete(PersonWelfare.class, arr);
					personWelfareDao.delete(PersonWelfareContent.class, arr);
					flag = true;
				}catch(Exception e){
					flag = false;
				}
				return flag;
	}
	
	/**
     * 读取已结束中的流程
     *
     * @return
     */
    @Transactional(readOnly = true)
    public Page<PersonWelfare> findFinishedProcessInstaces(Page<PersonWelfare> page,String key, String userName, int[] pageParams,PersonWelfare personWelfare) {
        return getTaskPersonPage(userName,  page, pageParams, personWelfare,2);
    }
	@Override
	@Transactional
	public boolean deletePersonWelfareByUser(String userId,String modleId) {
		boolean flag = false;
		try{
			personWelfareDao.deletePersonWelfareByUser(userId,modleId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	public Page<PersonWelfare> getTaskPersonPage(String userName, Page<PersonWelfare> page,
			int pageParams[],PersonWelfare personWelfare,int type){//1 代办任务  2 办结任务  3 超时任务
		StringBuffer sql =null;
		StringBuffer countsql=null;
		String currenttime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm","time-nw.nist.gov",2500);
		switch (type) {
		case 1:
			sql  =new StringBuffer("select * from pw_table where flow_status=1 and sign_user_name='"+userName+"' and finish_time>= '"+currenttime+"'");
			countsql = new StringBuffer("select count(*) from pw_table where flow_status=1 and sign_user_name='"+userName+"' and finish_time>= '"+currenttime+"'");
			break;
		case 2:
			sql  =new StringBuffer("select * from pw_table where flow_status in (2,3,4) and sign_user_name='"+userName+"'");
			countsql = new StringBuffer("select count(*) from pw_table where flow_status in (2,3,4) and sign_user_name='"+userName+"'");
			break;
		case 3:
			sql  =new StringBuffer("select * from pw_table where flow_status=1 and sign_user_name='"+userName+"' and finish_time<'"+currenttime+"'");
			countsql = new StringBuffer("select count(*) from pw_table where flow_status=1 and sign_user_name='"+userName+"' and finish_time<'"+currenttime+"'");
			break;
		default:
			break;
		}
		String sdate = null;    
    	String edate = null;
    	List<PersonWelfare>  pws = null;
    	List<Task> tasks = null;
    	List<HistoricProcessInstance> hps = null;
    	Map<String,Task> tmap = new HashMap<String,Task>();//process  task        //根据流程的业务ID查询实体并关联
    	Map<String,HistoricProcessInstance> hpmap = new HashMap<String,HistoricProcessInstance>();//process  task        //根据流程的业务ID查询实体并关联
	    if(personWelfare.getStarttime() != null && !"".equals(personWelfare.getStarttime())){
				sdate = personWelfare.getStarttime()+" 00:00:00";
				if(personWelfare.getEndtime() != null && !"".equals(personWelfare.getEndtime())){
					edate = personWelfare.getEndtime()+" 23:59:59";
					sql.append(" and start_time>='"+sdate+"'");
					sql.append(" and start_time<='"+edate+"'");
					
					countsql.append(" and start_time>='"+sdate+"'");
					countsql.append(" and start_time<='"+edate+"'");
				}else{
					sql.append(" and start_time>='"+sdate+"'");
					countsql.append(" and start_time>='"+sdate+"'");
				}
	    }else if(personWelfare.getEndtime() != null && !"".equals(personWelfare.getEndtime())){
	    	edate = personWelfare.getEndtime()+" 23:59:59";
	    	sql.append(" and start_time<='"+edate+"'");
	    	
	    	countsql.append(" and start_time<='"+edate+"'");
	    }
	    sql.append(" order by id desc"); 
	    sql.append(" limit "+pageParams[0]+",");
	    sql.append(pageParams[1]);
	    pws = personWelfareDao.createSqlMethod(sql.toString(), PersonWelfare.class).getResultList();
	    Integer count =Integer.parseInt(personWelfareDao.createSqlMethod(countsql.toString()).getSingleResult().toString());
	    if(pws != null && !pws.isEmpty()){
	    	StringBuffer pids = new StringBuffer("");
	    	for(PersonWelfare pw:pws){
	    		pids.append(pw.getProcessInstanceId()+",");
		    }
	    	pids = pids.deleteCharAt(pids.length()-1);
			if(!"".equals(pids.toString())){
				if(type == 2){
					hps = historyService.createNativeHistoricProcessInstanceQuery().sql("select * from "+managementService.getTableName(HistoricProcessInstance.class)+" t where t.PROC_INST_ID_ in ("+pids.toString()+")").list();
					for(HistoricProcessInstance hp:hps){
						hpmap.put(hp.getId(), hp);
					}
					for(PersonWelfare pw:pws){
						pw.setHistoricProcessInstance(hpmap.get(pw.getProcessInstanceId()));
					}
				}else{
					tasks = taskService.createNativeTaskQuery().sql("select * from "+managementService.getTableName(Task.class)+" t where t.PROC_INST_ID_ in ("+pids.toString()+")").list();
					for(Task task : tasks){
						tmap.put(task.getProcessInstanceId(), task);
					}
					for(PersonWelfare pw:pws){
						pw.setTask(tmap.get(pw.getProcessInstanceId()));
					}
				}
			}
	    }
        page.setTotalCount(count);
        page.setResult(pws);
        return page;
	}
	@Override
	@Transactional
	public int updatePwBySqlService(PersonWelfare pw) {
		// TODO Auto-generated method stub
		StringBuffer sbf = new StringBuffer("update pw_table set ");
		sbf.append(" flow_status=4 where id ="+pw.getId());
		return personWelfareDao.createSqlMethod(sbf.toString()).executeUpdate();
	}
	@Override
	public Page<PersonWelfare> findPersonWelfarePageByPub(
			Page<PersonWelfare> page, PersonWelfare personWelfare,
			LinkedHashMap<String, String> orderby, String pubPlatform) {
		StringBuffer wheresql = new StringBuffer(" 1=1 ");
		List<Object> params = new ArrayList<Object>();
			if( personWelfare.getModelId()!=null &&!"".equals(personWelfare.getModelId())){
				wheresql.append(" and modelId in("+personWelfare.getModelId()+") ");
			}
			if(personWelfare.getOrgId()!=null ){
				wheresql.append(" and orgId = ? ");
				params.add(personWelfare.getOrgId());
			}
			if(personWelfare.getFlowstatus()!=null && !"".equals(personWelfare.getFlowstatus())){
				wheresql.append(" and flowstatus = ?");
		    	params.add(personWelfare.getFlowstatus());
			}
			if(personWelfare.getFlag() != null){
				wheresql.append(" and flag = ? ");
				params.add(personWelfare.getFlag());
			}
			if(pubPlatform!=null && !"".equals(pubPlatform)){
				wheresql.append(" and pubPlatform in ("+pubPlatform+")");
			}
			if(personWelfare.getImgSrc()!=null &&!"".equals(personWelfare.getImgSrc())){
				wheresql.append(" and imgSrc is not null ");
			}
			return personWelfareDao.findPersonWelfarePage(page, wheresql.toString(), params, orderby);
	}
}
