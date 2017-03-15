package com.impl;


import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.oa_bean.OALog;
import org.oa_bean.OaModel;
import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.SysUser;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.LogService;
import com.dao.PartitionService;
import com.dao.SysUserDao;
import com.tool.util.OACommon;
@Component
@Aspect
public class AnotitionAopLog {
	@Autowired  
	private HttpSession session;  
	@Autowired
	private LogService logService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private PartitionService partitionService;
	private SimpleDateFormat sdf;
	public AnotitionAopLog(){
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
    private final static Logger logger = Logger.getLogger(AnotitionAopLog.class);
	@Pointcut("execution(* com.impl.*.*Service(..))")
	public void aspect(){	}
	/**
	 * 前置通知
	 * @param joinPoint
	 */
	/*@Before("aspect()")
	public void beforeMethod(JoinPoint joinPoint) {
		if(log.isInfoEnabled()){
			log.info("before " + joinPoint);
		}
	}*/
	/**
	 * 后置通知
	 * @param joinPoint
	 */
	/*@After("aspect()")
	public void afterMethod(JoinPoint joinPoint) {
		// TODO Auto-generated method stub
		if(log.isInfoEnabled()){
			log.info("after " + joinPoint);
		}
	}*/
	/**
	 * 环绕通知
	 * @param joinPoint
	 * @throws Throwable
	 */
	/*@Around("aspect()")
	public void aroundMethod(JoinPoint joinPoint)throws Throwable {
		long start = System.currentTimeMillis();
		try {
			((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			if(log.isInfoEnabled()){
				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
			}
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			if(log.isInfoEnabled()){
				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
			}
		}
	}*/
	/**
	 * 后置返回通知
	 * @param joinPoint
	 */
	@SuppressWarnings({ "unused", "null" })
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint){
		try {
			    SysUser logingUser = (SysUser) session.getAttribute(OACommon.LOGIN_USER);
				String methodName = joinPoint.getSignature().getName();
				OALog oalog = null;
				Integer id = null;
				Integer [] ids=null;
	  		    String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
				List<Integer> oaOrglist = (List<Integer>) session.getAttribute(OACommon.GROUPID);
				switch(methodName){
					  case "saveOrUpdateOaOrgService":
						  if(!OACommon.SYSCREATOR.equals(logingUser.getNodeId())){
							    String optype = (String) joinPoint.getArgs()[3];
								String nodeIdType = (String) joinPoint.getArgs()[1];
								String dataId = (String) joinPoint.getArgs()[2];
								OaOrg oaOrg = (OaOrg) joinPoint.getArgs()[0];
								if("add".equals(optype) && "brother".equals(nodeIdType) && 
								    oaOrglist.contains(Integer.parseInt(dataId.split(";")[1]))
								    || ("add".equals(optype)&&nodeIdType == null)){
									oaOrglist.add(oaOrg.getOrgId());
									/*更新groupId*/
									session.setAttribute(OACommon.GROUPID, oaOrglist);
									
									/*更新user*/
									StringBuffer orgids = new StringBuffer("");
									for(int i=0;i<oaOrglist.size();i++){
										orgids.append(oaOrglist.get(i)+",");
									}
									orgids = orgids.deleteCharAt(orgids.length()-1);
									logingUser.setOrgIds(orgids.toString());
									sysUserDao.updateUserOrgids(logingUser);
								}else{
									
								}
						  //添加和更新机构日志
						  oalog=new OALog();
						  if("add".equals(optype)){
							  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")添加了'"+oaOrg.getOrgName()+"'机构(机构编号为"+oaOrg.getOrgNum()+")");
							  oalog.setActiontime(createTime);
						  }else if("update".equals(optype)){
							  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+oaOrg.getOrgId()+"'的机构信息");
							  oalog.setActiontime(createTime);
						  }
						  logService.addOALogService(oalog);
						  }
						break;
						//机构删除日志
					 case "delOaOrgService":
						  String orgids = (String)joinPoint.getArgs()[0];
						  String arrayorgids[] = orgids.split(",");
				    	  String parentid = (String)joinPoint.getArgs()[1];
				    	  String orgName = (String)joinPoint.getArgs()[2];
				    	  oalog=new OALog();
				    	  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")删除了'"+orgName+"'机构(父节点为"+parentid+")");
				    	  oalog.setActiontime(createTime);
				    	  for(int i=0;i<arrayorgids.length;i++){
				    		  int signindex = oaOrglist.indexOf(Integer.parseInt(arrayorgids[i]));
				    		  if(signindex>=0){
				    			  oaOrglist.remove(signindex);
				    		  }
				    	  }
				    	  session.setAttribute(OACommon.GROUPID, oaOrglist);
				    	  logService.addOALogService(oalog);
				    	  break;
				      case "saveOrUpdateSysUserService":
				    	    SysUser sysUser = (SysUser) joinPoint.getArgs()[0];
				    	    String opertionType = (String) joinPoint.getArgs()[1];
				    	    if("add".equals(opertionType)){
				    	    	User user = identityService.newUser(sysUser.getUserName());
				    	    	if(sysUser.getRealName() != null){
				    	    		user.setFirstName(sysUser.getRealName().substring(0, 1));
					    	    	user.setLastName(sysUser.getRealName().substring(1, sysUser.getRealName().length()));
				    	    	}
				    	    	identityService.saveUser(user);
				    	    }else if("update".equals(opertionType)){
				    	    	User user = identityService.createUserQuery().userId(sysUser.getUserName()).singleResult();
				    	    	if(user != null){
				    	    		if(sysUser.getRealName() != null){
					    	    		user.setFirstName(sysUser.getRealName().substring(0, 1));
						    	    	user.setLastName(sysUser.getRealName().substring(1, sysUser.getRealName().length()));
					    	    	}
						  			identityService.saveUser(user);
				    	    	}
				    	    }
				    	    //用户添加和更新日志
				    	    oalog=new OALog();
				    	    if("add".equals(opertionType)){
				    	    	  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")添加了账号为'"+sysUser.getUserName()+"'("+sysUser.getRealName()+")的用户");
						    	  oalog.setActiontime(createTime);
				    	    }else{
				    	    	oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了账号为'"+sysUser.getUserName()+"'("+sysUser.getRealName()+")的用户信息");
				    	    	oalog.setActiontime(createTime);
				    	    }
				    	    logService.addOALogService(oalog);
				    	  break;
				      case "saveOrUpdateRoleService":
				    	   OaRole oaRole = (OaRole)joinPoint.getArgs()[0];
				    	   String operationType = (String)joinPoint.getArgs()[2];
				    	   if("add".equals(operationType)){
				    		   Group group = identityService.newGroup(oaRole.getRoleId().toString());
				    		   group.setName(oaRole.getRoleName());
				    	    	identityService.saveGroup(group);
				    	    	//角色添加日志
				    	    	oalog=new OALog();
				    	    	oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")添加了'"+oaRole.getRoleName()+"'角色");
				    	    	oalog.setActiontime(createTime);
				    	    	
				    	   }else if("update".equals(operationType)){
				    		    Group group = identityService.createGroupQuery().groupId(oaRole.getRoleId().toString()).singleResult();
				    		    group.setName(oaRole.getRoleName());
					  			identityService.saveGroup(group);
					  			//角色更新日志
					  			oalog=new OALog();
					  			oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+oaRole.getRoleId()+"'的角色");
					  			oalog.setActiontime(createTime);
				    	   }
				    	   logService.addOALogService(oalog);
				    	  break;
				      case "delSysUserService":
				    	  String userName = (String)joinPoint.getArgs()[1];
				    	  identityService.deleteUser(userName);
				    	  //用户删除日志
				    	  oalog=new OALog();
				    	  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")删除了账号为'"+userName+"'的用户");
				    	  oalog.setActiontime(createTime);
				    	  logService.addOALogService(oalog);
				    	  break;
				      case "delRoleService":
				    	  String roleId = (String)joinPoint.getArgs()[0];
				    	  String rolename= (String) joinPoint.getArgs()[3];
				    	  identityService.deleteGroup(roleId);
				    	  //角色删除日志
				    	  oalog=new OALog();
				    	  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")删除了'"+rolename+"'角色");
				    	  oalog.setActiontime(createTime);
				    	  logService.addOALogService(oalog);
				    	  break;
				      case "configureUserService":
				    	  SysUser csysUser = (SysUser)joinPoint.getArgs()[0];
				    	  String auserId = csysUser.getUserName();
				    	  List<OaRole> oaRoles = (List<OaRole>)joinPoint.getArgs()[1];
				    	  List<Group> groups  =identityService.createGroupQuery().groupMember(auserId).list();
				    	  if(oaRoles != null){
				    		  for(Group group:groups){
				    			  identityService.deleteMembership(auserId, group.getId());
				    		  }
				    		  for(OaRole role:oaRoles){
				    			  identityService.createMembership(auserId, role.getRoleId().toString());
				    		  }
				    	  }else{
				    		  for(Group group:groups){
				    			  identityService.deleteMembership(auserId, group.getId());
				    		  }
				    	  }
				    	  //用户配置模块日志
				    	  oalog=new OALog();
				    	  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")给用户'"+csysUser.getUserName()+"'("+csysUser.getRealName()+")进行了角色和机构配置");
				    	  oalog.setActiontime(createTime);
				    	  logService.addOALogService(oalog);
				    	  break;
				      case "addOrUpdateOaModelService":
				    	    OaModel model=(OaModel) joinPoint.getArgs()[0];
							String optype = (String) joinPoint.getArgs()[1];
							if(model.getIsAuto()==1 && "true".equals(model.getpAuto()) && "add".equals(optype)){
								OaModel oaModel = (OaModel) joinPoint.getArgs()[0];
					    	    partitionService.addListPartition(OACommon.PERSONWELFARETABLE, oaModel.getModelId().toString());
							}
							//模块的添加日志
							oalog=new OALog();
							if("add".equals(optype)){
								oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")添加了'"+model.getModelName()+"'模块");
								oalog.setActiontime(createTime);
							}else if("update".equals(optype)){
								oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+model.getModelId()+"'的模块");
								oalog.setActiontime(createTime);
							}
							logService.addOALogService(oalog);
				    	  break;
				      case "delModelService":
				    	    String omid = (String) joinPoint.getArgs()[0];
							String pAuto = (String) joinPoint.getArgs()[6];
							String modelname=(String) joinPoint.getArgs()[3];
							if("true".equals(pAuto)){
								partitionService.delListPartition(OACommon.PERSONWELFARETABLE, omid);
							}
							//模块的删除日志
							oalog=new OALog();
							oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")删除了'"+modelname+"'模块");
							oalog.setActiontime(createTime);
							logService.addOALogService(oalog);
				    	  break;
				}
		} catch (Exception e) {
			logger.error("error",e);
		}
	}
	
	private String NOW() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 异常抛出后通知	
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		if(logger.isInfoEnabled()){
			logger.error("error",ex);
		}
	}

}
