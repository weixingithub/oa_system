package com.impl.population;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2017年2月16日 下午3:28:04 
 * @version 1.0 
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.oa_bean.OALog;
import org.oa_bean.SysUser;
import org.oa_bean.population.Family;
import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
import org.oa_bean.population.Stability;
import org.oa_bean.population.VehicleInformation;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.LogService;
import com.dao.PartitionService;
import com.dao.SysUserDao;
import com.tool.util.OACommon;
@Component
@Aspect
public class PopulationAnotitionAopLog {
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
	public PopulationAnotitionAopLog(){
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
    private final static Logger logger = Logger.getLogger(PopulationAnotitionAopLog.class);
	@Pointcut("execution(* com.impl.population.*.*Service(..))")
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
				    	  /**
				    	   * 人口信息模块日志
				    	   */
				    	  //人口基本信息添加和更新日志
				      case "addAndUpdatePersonService":
				    	    Person  person = (Person) joinPoint.getArgs()[0];
							String operation = (String) joinPoint.getArgs()[1];
							Integer person_id=(Integer) joinPoint.getArgs()[2];
							oalog=new OALog();
							if("add".equals(operation)){//添加人口信息的日志
								oalog.setActiontime(person.getCreatime());
								oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")添加了身份证号为'"+person.getIdnumber()+"'的人口信息");
							}else{//更新人口信息的日志
								oalog.setActiontime(createTime);
								oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+person.getId()+"'的人口信息");
							}
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //人口基本信息删除日志
				      case "delPersonService":
				    	    String  Ids = (String) joinPoint.getArgs()[0];
				    	    String idnumber = (String) joinPoint.getArgs()[1];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")删除了身份证号为'"+idnumber+"'的人口信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //民政更新日志
				      case "updatePopulationCivilService":
				    	    PopulationCivil  civil = (PopulationCivil) joinPoint.getArgs()[0];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+civil.getId()+"'的民政信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //计生更新日志
				      case "updateFamilyPlanningService":
				    	    FamilyPlanning  plan = (FamilyPlanning) joinPoint.getArgs()[0];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+plan.getId()+"'的计生信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //劳保更新日志
				      case "updateLaborInsuranceService":
				    	    LaborInsurance  labor = (LaborInsurance) joinPoint.getArgs()[0];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+labor.getId()+"'的劳保信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //综治更新日志
				      case "updateStabilityService":
				    	    Stability  stability = (Stability) joinPoint.getArgs()[0];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+stability.getId()+"'的综治信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //车辆添加日志
				      case "addVehicleService":
				    	    VehicleInformation  vehicle = (VehicleInformation) joinPoint.getArgs()[0];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")添加了车主为'"+vehicle.getOwners()+"'(id为"+vehicle.getId()+")");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //车辆信息删除日志
				      case "delPersonVehicleService":
				    	    String  idS = (String) joinPoint.getArgs()[0];
				    	    String number =(String) joinPoint.getArgs()[1];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")删除了车牌号为'"+number+"'的车辆信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  //车辆更新日志
				      case "updateVehicleService":
				    	    VehicleInformation  Vehicle = (VehicleInformation) joinPoint.getArgs()[0];
				    	    oalog=new OALog();
				    	    oalog.setActiontime(createTime);
				    	    oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")更新了id为'"+Vehicle.getId()+"'的车辆信息");
							logService.baseSaveLog(oalog);
				    	  break;
				    	  /**
				    	   * 用户管理模块日志
				    	   */
				     //户籍新增和修改记录日志
				      case "addAndUpdateFamilyService":
				    	  Family family=(Family) joinPoint.getArgs()[0];
				    	  String optionType=(String) joinPoint.getArgs()[1];
				    	  oalog=new OALog(); 
				    	  if("add".equals(optionType)){
				    		  oalog.setActiontime(family.getCreateTime());
				    		  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")对户籍管理进行了添加操作。");
				    		  logService.addOALogService(oalog);
				    	  }else{
				    		  oalog.setActiontime(family.getCreateTime());
				    		  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")对户籍管理进行了更新操作。");
				    		  logService.addOALogService(oalog);
				    	  }
				    	  break;
				      //户籍删除记录日志
				      case "deleteFamilyService":
				    	  String famId=(String) joinPoint.getArgs()[0];//获取删除Id
				    	  oalog=new OALog();
		    	  		  oalog.setActiondescribe(logingUser.getUserName()+"("+logingUser.getRealName()+")对户籍管理编号为"+famId+"的,进行了删除操作。");
		    		      oalog.setActiontime(sdf.format(new Date()));
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
