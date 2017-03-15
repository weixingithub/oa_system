package com.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.oa_bean.OaModel;
import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.PersonWelfare;
import org.oa_bean.PersonWelfareContent;
import org.oa_bean.SysUser;
import org.oa_bean.comment.CommentDetails;
import org.oa_bean.comment.CommentInfo;
import org.oa_common.date.DateTools;
import org.oa_common.html.HTMLSpirit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OaModelService;
import com.dao.OaOrgService;
import com.dao.OaRoleService;
import com.dao.PersonWelfareService;
import com.dao.SysUserService;
import com.dao.comment.CommentDetailsService;
import com.dao.comment.CommentInfoService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月3日 下午4:37:00 
 * @version 1.0 
 */
@Controller
@RequestMapping("/oa/pwelfare")
public class PersonWelfareController {
	private PersonWelfareService personWelfareService;
	
	private OaModelService oaModelService;
	
	private RuntimeService runtimeService;

	protected TaskService taskService;

	protected HistoryService historyService;

	protected RepositoryService repositoryService;

	private IdentityService identityService;
	
	private OaRoleService oaRoleService;
	
	private SysUserService sysUserService;
	
	private OaOrgService oaOrgService;
	
	private CommentDetailsService commentDetailsService;
	
	private CommentInfoService commentInfoService;
	
	
	public CommentInfoService getCommentInfoService() {
		return commentInfoService;
	}
	@Autowired
	public void setCommentInfoService(CommentInfoService commentInfoService) {
		this.commentInfoService = commentInfoService;
	}
	@Autowired
	public void setCommentDetailsService(CommentDetailsService commentDetailsService) {
		this.commentDetailsService = commentDetailsService;
	}
	public OaOrgService getOaOrgService() {
		return oaOrgService;
	}
	@Autowired
	public void setOaOrgService(OaOrgService oaOrgService) {
		this.oaOrgService = oaOrgService;
	}
	public SysUserService getSysUserService() {
		return sysUserService;
	}
	@Autowired
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	public OaRoleService getOaRoleService() {
		return oaRoleService;
	}
	@Autowired
	public void setOaRoleService(OaRoleService oaRoleService) {
		this.oaRoleService = oaRoleService;
	}
	public OaModelService getOaModelService() {
		return oaModelService;
	}
	@Autowired
	public void setOaModelService(OaModelService oaModelService) {
		this.oaModelService = oaModelService;
	}
	
	public PersonWelfareService getPersonWelfareService() {
		return personWelfareService;
	}
	@Autowired
	public void setPersonWelfareService(PersonWelfareService personWelfareService) {
		this.personWelfareService = personWelfareService;
	}
	public RuntimeService getRuntimeService() {
		return runtimeService;
	}
	@Autowired
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public HistoryService getHistoryService() {
		return historyService;
	}
	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	public IdentityService getIdentityService() {
		return identityService;
	}
	@Autowired
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	/**
	 * 分页流程列表
	 * 进入流程列表页面
	 * @param kind
	 * @param model
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param personWelfare
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value="/modelvariable{kind}")
	public ModelAndView intoPersonWelfareList(@PathVariable String kind,HttpServletRequest request,Integer numPerPage ,Integer pageNum,
			   Page<PersonWelfare> page,PersonWelfare personWelfare,String orderField,String orderDirection,String content){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		ModelAndView mav = new ModelAndView("conservice/conservice_list");
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String,String>();
		String orgIds = "";
		if(numPerPage == null || numPerPage ==-1){
    		page = new Page(10);
    		personWelfare.setModelId(kind);
    	}else{
    		page.setPageSize(numPerPage); 
 		    page.setPageNo(pageNum);
    	}
		OaModel oaModel = oaModelService.findModelById(personWelfare.getModelId());
		if(OACommon.SYSCREATOR.equals(sysUser.getNodeId())){
			personWelfare.setUserId(0);
			personWelfare.setOrgId(0);
			
		}else{
			List<Integer> orgidList = (List<Integer>)request.getSession().getAttribute(OACommon.GROUPID);
			if(orgidList.size()>1){
				StringBuffer ids = new StringBuffer();
				for(int i=0;i<orgidList.size();i++){
					ids.append(orgidList.get(i).toString());
					ids.append(",");
				}
				ids = ids.deleteCharAt(ids.length()-1);
				orgIds = oaOrgService.findMoreOrgNodeChild(ids.toString(), orgidList.size());
			}else{
				if(!orgidList.isEmpty()){
					orgIds = oaOrgService.findOrgNodeChild(orgidList.get(0).toString());
				}else{
					orgIds="$,$";
				}
			}
			orgIds = orgIds.substring(2, orgIds.length());
		}
		if(orderField != null && !"".equals(orderField) && orderDirection != null && !"".equals(orderDirection)){
	    	orderby = new LinkedHashMap<String,String>();
 		    orderby.put(orderField, orderDirection);
	    }else{
	    	orderby = new LinkedHashMap<String,String>();
	    	orderField = "creattime";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
	    }
		page = personWelfareService.findPersonWelfarePage(page, personWelfare, orderby,content,orgIds);
		if(page.getResult().size()>0){
			page = convertPersonWelfarePage(page);
		}
		mav.addObject("sysUser", sysUser);
		mav.addObject("page", page);
		mav.addObject("personWelfare", personWelfare);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		mav.addObject("oaModel",oaModel);
		mav.addObject("publishStatusMap",OACommon.publishStatusMap);
		mav.addObject("content", content);
	    return mav;
	     
	}
	/**
	 * 进行信息用户与机构的匹配转换
	 * @param page
	 * @return
	 */
	public Page<PersonWelfare> convertPersonWelfarePage(Page<PersonWelfare> page){
			List<PersonWelfare> pwfList =  page.getResult();
			StringBuffer userIds = new StringBuffer();
			StringBuffer orgIds = new StringBuffer();
			HashSet<String> pids = new HashSet<String>();
			for(int i=0;i<pwfList.size();i++){
				PersonWelfare pw = pwfList.get(i);
				userIds.append(pw.getUserId());
				userIds.append(",");
				orgIds.append(pw.getOrgId());
				orgIds.append(",");
				if(pw.getFlowstatus()==1){
					pids.add(pw.getProcessInstanceId());
				}
			}
			userIds = userIds.deleteCharAt(userIds.length()-1);
			orgIds = orgIds.deleteCharAt(orgIds.length()-1);
			List<SysUser> sysUserList = sysUserService.findSysUserAllById(userIds.toString());
			Map<Integer,SysUser> umap = new HashMap<Integer,SysUser>();
			for(SysUser su:sysUserList){
				umap.put(su.getId(), su);
			}
			List<OaOrg> oaOrgList = oaOrgService.findOrgAllById(orgIds.toString());
			Map<Integer,OaOrg> omap = new HashMap<Integer,OaOrg>();
			for(OaOrg og:oaOrgList){
				omap.put(og.getOrgId(), og);
			}
			/*获取流程实例*/
			Map<String,ProcessInstance> pmap = new HashMap<String,ProcessInstance>();
			if(pids.size()>0){
				List<ProcessInstance> pis = runtimeService.createProcessInstanceQuery().processInstanceIds(pids).list();
				for(int i=0;i<pis.size();i++){
					ProcessInstance pi = pis.get(i);
					pmap.put(pi.getId(), pi);
				}
			}
			for(int i=0;i<pwfList.size();i++){
				PersonWelfare pw = pwfList.get(i);
				/*获取用户*/
				pw.setSysUser(umap.get(pw.getUserId()));
				/*获取机构*/
				pw.setOrg(omap.get(pw.getOrgId()));
				
				pw.setProcessInstance(pmap.get(pw.getProcessInstanceId()));
			}
		return page;
	}
	/**
	 * 进入添加页面
	 * @param request
	 * @param modelId
	 * @return
	 */
	@RequestMapping("/intoAddPwelfare")
	public ModelAndView intoAddPwelfare(HttpServletRequest request,String modelId,Integer autoCheck){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<OaOrg> orglist = sysUser.getOaOrg();
		ModelAndView mav = new ModelAndView("conservice/conservice_add");
		PersonWelfare personWelfare = new PersonWelfare();
		personWelfare.setModelId(modelId);
		personWelfare.setSysUser(sysUser);
		personWelfare.setCommentSwitch(0);
		personWelfare.setCommentInfo(new CommentInfo());
		mav.addObject("orglist", orglist);
		mav.addObject("autoCheck", autoCheck);
		mav.addObject("personWelfare", personWelfare);
		return mav;
	}
	/**
	 * 新增和编辑信息
	 * @param personWelfare
	 * @return
	 */
	 @RequestMapping("/addAndUpdatePwelfare")
	 @ResponseBody
	public Object addAndUpdatePwelfare(PersonWelfare personWelfare,PersonWelfareContent personWelfareContent,Integer commentInfoId,Integer autoCheck){
		  JsonBean jsonBean = null;
		  personWelfare.setPersonWelfareContent(personWelfareContent);
		  List<String> srcList = HTMLSpirit.getImgSrc(personWelfareContent.getContent());
		  if(commentInfoId != null){
			  CommentInfo commentInfo = new CommentInfo();
			  commentInfo.setCommentInfoId(commentInfoId);
			  commentInfo.setTitle(personWelfare.getTitle());
			  commentInfo.setOrgId(personWelfare.getOrgId());
			  commentInfo.setModelId(personWelfare.getModelId());
			  commentInfo.setPraiseNumber(0L);
			  commentInfo.setNegativeNumber(0L);
			  commentInfo.setBrowseNumber(0L);
			  personWelfare.setCommentInfo(commentInfo);
		  }
		  if(srcList.size()>0){
			  personWelfare.setImgSrc(srcList.get(0));
		  }else{
			  personWelfare.setImgSrc(null);
		  }
		  if(autoCheck != 1){
			  personWelfare.setFlag(true);
		  }
		  if(personWelfareService.addAndUpdatePersonWelfare(personWelfare)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.PERSONWELFARENODEID,"","closeCurrent","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		  return jsonBean;
	}
	 /**
	 * 进入编辑页面
	 * @param request
	 * @param modelId
	 * @return
	 */
	@RequestMapping("/intoUpdatePwelfare")
	public ModelAndView intoUpdatePwelfare(HttpServletRequest request,String id,Integer autoCheck){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<OaOrg> orglist = sysUser.getOaOrg();
		ModelAndView mav = new ModelAndView("conservice/conservice_add");
		PersonWelfare personWelfare = personWelfareService.findPwById(Integer.parseInt(id));
		personWelfare.setSysUser(sysUser);
		 CommentInfo commentInfo = personWelfare.getCommentInfo();
	    if(commentInfo == null ){
		  personWelfare.setCommentInfo(new CommentInfo());
	    }
		mav.addObject("orglist", orglist);
		mav.addObject("autoCheck", autoCheck);
		mav.addObject("personWelfare", personWelfare);
		return mav;
	}
	/**
	 * 详情预览
	 * @param id
	 * @return
	 */
	@RequestMapping("/showPwelfare")
	public ModelAndView showPwelfare(HttpServletRequest request,String id){
		String detailUrl = request.getRequestURL().append("?id=" + id).toString();
		ModelAndView mav = new ModelAndView("conservice/conservice_show");
		PersonWelfare personWelfare = personWelfareService.findPwById(Integer.parseInt(id));
		
		//处理文章阅读量，即每访问一次详情，访问量就+1
		if(personWelfare.getCommentInfo() !=null){
			commentInfoService.updateCommentInfoByIdAndCondition(personWelfare.getCommentInfo().getCommentInfoId(),"browseNum");
		}
		
		OaOrg oaOrg= oaOrgService.getFindOaOrg(personWelfare.getOrgId());
		SysUser sysUser=sysUserService.getFindSysUser(personWelfare.getUserId());
		personWelfare.setOrg(oaOrg);
		personWelfare.setSysUser(sysUser);
	    CommentInfo commentInfo = personWelfare.getCommentInfo();
	    if(commentInfo == null ){
		  personWelfare.setCommentInfo(new CommentInfo());
	    }else{
	    	List<CommentDetails> list = personWelfare.getCommentInfo().getCommentDetails();
	    	Collections.sort(list,new Comparator<CommentDetails>() {
				@Override
				public int compare(CommentDetails o1, CommentDetails o2) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date dt1 = format.parse(o1.getCreateTime());
						Date dt2 = format.parse(o2.getCreateTime());
						if (dt1.getTime() < dt2.getTime()) {
							return 1;
						} else if (dt1.getTime() > dt2.getTime()) {
						     return -1;
						} else {
							return 0;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return 0;
				}
			});
	    	
	    	//将评论和对应的回复做匹配
			List<CommentDetails> comments = new ArrayList<CommentDetails>();
			comments = commentDetailsService.findCommentsByIsrec();		//查询所有的回复
			for (CommentDetails comment : list) {
				for (CommentDetails commentDetails2 : comments) {
					if (commentDetails2.getIsRec() == comment.getContentId()) {
						comment.setCommentDetails(commentDetails2);
					}
				}
			}
	    	personWelfare.getCommentInfo().setCommentDetails(list);
	    }
	    //如果commentInfo中没有文章详情的地址，就新增
	    if(personWelfare.getCommentInfo().getUrl() == null){
	    	commentInfoService.updateCommentInfoByIdAndUrl(personWelfare.getCommentInfo().getCommentInfoId(),detailUrl);
	    	personWelfare.getCommentInfo().setUrl(detailUrl);
	    }
		mav.addObject("personWelfare", personWelfare);
		return mav;
	}
	/**
	 * 删除信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delPwelfare")
	@ResponseBody
	public Object delPwelfare(HttpServletRequest request,String ids,String modelId,String state){
		  JsonBean jsonBean = null;
		  if("all".equals(state)){
			  SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
			  if(personWelfareService.deletePersonWelfareByUser(sysUser.getId().toString(),modelId)){
				  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.PERSONWELFARENODEID,"","","","");
			  }else{
				  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			  }
		  }else{
			  if(personWelfareService.delPersonWelfare(ids)){
				  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.PERSONWELFARENODEID,"","","","");
			  }else{
				  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			  }
		  }
		
		return jsonBean;
	}
	/**
	 * 创建申请表单
	 * @param model
	 * @return
	 */
	@RequestMapping("/createForm")
	public String createForm(HttpServletRequest request,Model model,Integer id,String title,String creattime,String modelId){
			OaModel om = oaModelService.findModelById(modelId);
			OaRole oaRole = oaRoleService.findRoleById(Integer.parseInt(om.getCheckRoleId()));
			model.addAttribute("id", id);
			model.addAttribute("title", title);
			model.addAttribute("creattime",creattime);
			model.addAttribute("oaRole", oaRole);
	     return "/conservice/activitipage";
	}	
	 /**
	  * 启动申请流程
	  * @param request
	  * @param personWelfare
	  * @param assigneperson
	  * @return
	  */
    @RequestMapping(value = "start", method = RequestMethod.POST)
    @ResponseBody
    public Object startWorkflow(HttpServletRequest request,String assigneperson,Integer id,String finishtime) {
    	 SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
         String userName = sysUser.getUserName();
         JsonBean jsonBean = null;
    	 if(personWelfareService.savePersonWelfareService(id,userName,assigneperson,finishtime)){
    		 jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
    	 }else{
    		 jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	 }
        return jsonBean;
    }
    
    /**
     * 获取代办任务列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/todotask")
    public ModelAndView taskList(HttpServletRequest request,Page<PersonWelfare> page,
    		Integer pageNum,Integer numPerPage,PersonWelfare personWelfare) {
        ModelAndView mav = new ModelAndView("oatask/tasklist");
        if(numPerPage == null || numPerPage ==-1){
    		page = new Page(10);
    	}else{
    		page.setPageSize(numPerPage); 
 		    page.setPageNo(pageNum);
    	}
        SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
        String userName = sysUser.getUserName();
        //处理分页
    	int paramone = page.getFirst();
    	int paramtwo = page.getPageSize();
    	int[] pageParams = new int[2];
    	pageParams[0] = paramone;
    	pageParams[1] = paramtwo;
        page = personWelfareService.findTodoTasks(userName, page, pageParams,personWelfare);
        mav.addObject("page", page);
        return mav;
    }
    /**
     * 已办任务列表
     * @param request
     * @param page
     * @param pageNum
     * @param numPerPage
     * @return
     */
    @RequestMapping(value="donetask")
    public ModelAndView taskDoneList(HttpServletRequest request,Page<PersonWelfare> page,Integer pageNum,Integer numPerPage,PersonWelfare personWelfare){
    	ModelAndView mav = new ModelAndView("oatask/taskedlist");
    	if(numPerPage == null){
    		page = new Page(10);
    	}else{
    		page.setPageSize(numPerPage); 
 		    page.setPageNo(pageNum);
    	}
    	SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
    	//处理分页
    	int paramone = page.getFirst();
    	int paramtwo = page.getPageSize();
    	int[] pageParams = new int[2];
    	pageParams[0] = paramone;
    	pageParams[1] = paramtwo;
    	
    	page = personWelfareService.findFinishedProcessInstaces(page, "ConvenienceService", sysUser.getUserName(), pageParams,personWelfare);
    	mav.addObject("page", page);
    	return mav;
    }
    /**
     * 获取超时任务列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/overtimetask")
    public ModelAndView overtimetask(HttpServletRequest request,Page<PersonWelfare> page,
    		Integer pageNum,Integer numPerPage,PersonWelfare personWelfare) {
        ModelAndView mav = new ModelAndView("oatask/overtimetask");
        if(numPerPage == null || numPerPage ==-1){
    		page = new Page(10);
    	}else{
    		page.setPageSize(numPerPage); 
 		    page.setPageNo(pageNum);
    	}
        SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
        String userName = sysUser.getUserName();
        //处理分页
    	int paramone = page.getFirst();
    	int paramtwo = page.getPageSize();
    	int[] pageParams = new int[2];
    	pageParams[0] = paramone;
    	pageParams[1] = paramtwo;
        page = personWelfareService.findOvertimeTasks(userName, page, pageParams,personWelfare);
        mav.addObject("page", page);
        return mav;
    }
    /**
     * 签收任务
     */
    @RequestMapping(value = "/claim/{id}")
    @ResponseBody
    public Object claim(@PathVariable("id") String taskId, HttpServletRequest request) {
    	 SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
         String userName = sysUser.getUserName();
         JsonBean jsonBean = null;
         try{
        	 taskService.claim(taskId, userName);
             jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/pwelfare/todotask"),"","","","");
         }catch(ActivitiTaskAlreadyClaimedException e){
        	 jsonBean = new JsonBean("300",OACommon.ASSIGNTASK,OACommon.modelMap.get("/oa/pwelfare/todotask"),"","","","");
         }
        return jsonBean;
    }
    /**
     * 获取审核表单
     * @param id
     * @param taskId
     * @return
     */
    @RequestMapping(value="/checkInfo")
    public ModelAndView checkInfo(Integer id,String taskId){
    	ModelAndView mav = new ModelAndView("conservice/checkpage");
    	boolean sign = true;
    	String rebackReason = null;
    	if(taskService.getVariable(taskId, "leaderpass") != null){
    		sign = (boolean) taskService.getVariable(taskId, "leaderpass");
    		rebackReason = (String)taskService.getVariable(taskId, "rebackReason");
    		mav.addObject("rebackReason", rebackReason);
    	}
    	PersonWelfare pw = personWelfareService.findPwById(id);
    	mav.addObject("pw", pw);
    	mav.addObject("taskId",taskId);
    	mav.addObject("sign", sign);
    	return mav;
    }
    /**
     * 处理审核流程
     * @param pw
     * @param taskId
     * @param reApply
     * @param rebackReason
     * @return
     */
    @RequestMapping(value="/handleWorkFlow")
    @ResponseBody
    public Object handleWorkFlow(PersonWelfare pw,PersonWelfareContent personWelfareContent,
    		String taskId,String reApply,String rebackReason,CommentInfo commentInfo){
    	pw.setCommentInfo(commentInfo);
    	JsonBean jsonBean = null;
    	personWelfareContent.setId(pw.getId());
    	pw.setPersonWelfareContent(personWelfareContent);
    	if("true".equals(reApply)){//重新调整申请
    		try {
    			List<IdentityLink> ils = runtimeService.getIdentityLinksForProcessInstance(pw.getProcessInstanceId());
    			pw.setSignUserName(ils.get(ils.size()-1).getUserId());
                 if(personWelfareService.updatePersonWelfareService(pw)){
                     Map<String, Object> variables = new HashMap<String,Object>();
                     variables.put("assignp",ils.get(ils.size()-1).getUserId());
                     variables.put("reApply", true);
                     variables.put("leaderpass", true);
                     taskService.complete(taskId, variables);
                     jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/pwelfare/todotask"),"","closeCurrent","","");
    			 }
            } catch (Exception e) {
            	jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
            }
    	}else if("false".equals(reApply)){//结束流程
    		try {
		         String endTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
    			 pw.setEndtime(endTime);
    			 pw.setFlowstatus(3);
    			 List<IdentityLink> ils = runtimeService.getIdentityLinksForProcessInstance(pw.getProcessInstanceId());
     			 pw.setSignUserName(ils.get(ils.size()-1).getUserId());
    			 if(personWelfareService.updatePersonWelfareService(pw)){
    				  Map<String, Object> variables = new HashMap<String,Object>();
    	                 variables.put("reApply", false);
    	                 taskService.complete(taskId, variables);
    	                 jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/pwelfare/todotask"),"","closeCurrent","","");
    			 }
            } catch (Exception e) {
            	jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
            }
    	}else{//处理流程
    		if(pw.getFlag()){
    		   String endTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
   			   pw.setEndtime(endTime);
   			   pw.setFlowstatus(2);
    		}else{
    			pw.setSignUserName(pw.getActivityuserId());
    		}
    		if(personWelfareService.updatePersonWelfareService(pw)){
        		try {
                    Map<String, Object> variables = new HashMap<String,Object>();
                    variables.put("leaderpass", pw.getFlag());
                    if(!pw.getFlag()){
                    	variables.put("rebackReason", rebackReason);
                    }
                    taskService.complete(taskId, variables);
                    jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/pwelfare/todotask"),"","closeCurrent","","");
                } catch (Exception e) {
                	jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
                }
        	}else{
        		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
        	}
    	}
    	return jsonBean;
    }
    /**
     * 超时任务退回
     */
    @RequestMapping("/doTaskBack")
    @ResponseBody
    public Object doTaskBack(PersonWelfare personWelfare,String taskId){
    	JsonBean jsonBean = null;
    	try{
    		 Map<String, Object> variables = new HashMap<String,Object>();
             variables.put("leaderpass", true);
    		taskService.complete(taskId,variables);
    		personWelfareService.updatePwBySqlService(personWelfare);
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/pwelfare/overtimetask"),"","","","");
    	}catch(Exception e){
    		 jsonBean = new JsonBean("200",OACommon.OPERATION_FAIL,"","","","","");
    	}
    	return jsonBean;
    }
    
}
