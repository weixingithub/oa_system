package com.oa.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.oa_bean.OaModel;
import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.RoleModel;
import org.oa_bean.SysUser;
import org.oa_bean.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OaModelService;
import com.dao.OaOrgService;
import com.dao.OaRoleService;
import com.dao.RoleModelService;
import com.dao.SysUserService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月18日 上午9:34:46 
 * @version 1.0 
 */
@Controller
@RequestMapping("/oa/role")
public class OaRoleController {
	  private OaRoleService oaRoleService;
	  private RoleModelService roleModelService;
	  private OaModelService oaModelService;
	  private SysUserService sysUserService;
	  private OaOrgService oaOrgService;

		public SysUserService getSysUserService() {
			return sysUserService;
		}
	    @Autowired
		public void setSysUserService(SysUserService sysUserService) {
			this.sysUserService = sysUserService;
		}
		public OaModelService getOaModelService() {
			return oaModelService;
		}
		@Autowired
		public void setOaModelService(OaModelService oaModelService) {
			this.oaModelService = oaModelService;
		}
	 
	  public RoleModelService getRoleModelService() {
			return roleModelService;
		}
	  @Autowired
	 public void setRoleModelService(RoleModelService roleModelService) {
			this.roleModelService = roleModelService;
	 }
	 public OaRoleService getOaRoleService() {
			return oaRoleService;
	 }
	 @Autowired
	 public void setOaRoleService(OaRoleService oaRoleService) {
			this.oaRoleService = oaRoleService;
	 }
	 
	 public OaOrgService getOaOrgService() {
		return oaOrgService;
	 }
	 @Autowired
	  public void setOaOrgService(OaOrgService oaOrgService) {
		 this.oaOrgService = oaOrgService;
	 }
	 @RequestMapping("/findRolePage")
     public ModelAndView findRolePage(HttpServletRequest request,Integer numPerPage
     		,Integer pageNum,Page<OaRole> page,
     		OaRole oaRole,String sequence,String starttime,String endtime){
		 ModelAndView mav = new ModelAndView("oarolepage/oarolelist");
		 SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
		 LinkedHashMap<String,String> orderby  = null;
    	 if(numPerPage == null || numPerPage ==-1){
     		page = new Page(10);
     	 }else{
     		page.setPageSize(numPerPage); 
  		    page.setPageNo(pageNum);
  		    if(sequence != null && !"".equals(sequence)){
  		    	String param = sequence.split(",")[0];
  		    	String order = sequence.split(",")[1];
  		    	orderby = new LinkedHashMap<String,String>();
  	 		    orderby.put(param, order);
  		    }
     	}
		 if(OACommon.SYSCREATOR.equals(sysUser.getNodeId())){
	    	page = oaRoleService.findOaRolePage(page, oaRole, orderby,starttime,endtime,null,0);
		 }else{
			 List<Integer> roleIds = (List<Integer>) request.getSession().getAttribute(OACommon.ROLEID);
			 page = oaRoleService.findOaRolePage(page, oaRole, orderby,starttime,endtime,roleIds,sysUser.getId());
		 }
		 mav.addObject("page", page);
	     mav.addObject("oaRole", oaRole);
	     mav.addObject("endtime", endtime);
	     mav.addObject("starttime", starttime);
    	 return mav;
     }
	 @RequestMapping("/findOneRoleModel")
	 public ModelAndView findOneRoleModel(String roleId,String modelId,String mparentId,int isCheckModel){
		 ModelAndView mav = new ModelAndView("oarolepage/roleandmodel");
		 RoleModel  rm =  oaRoleService.findOneRoleModel(roleId,modelId);
		 mav.addObject("rm", rm);
		 mav.addObject("roleId",roleId);
		 mav.addObject("modelId",modelId);
		 mav.addObject("mparentId",mparentId);
		 mav.addObject("isCheckModel",isCheckModel);
		 return mav;
	 }
	 @RequestMapping(value="/setRoleModelInfo",method=RequestMethod.POST)
	 @ResponseBody
	 public Object setRoleModelInfo(RoleModel rm,OaModel model,OaRole role,String parentflag,String settype,String mparentId){
		    JsonBean  jsonbean  = null;
		    rm.setModel(model);
		    rm.setRole(role);
		    if(parentflag != null && !"".equals(parentflag)){
		    	 rm.setIsParent(parentflag);
		    }else{
		    	 rm.setIsParent("false");
		    }
		    switch(settype){
			    case "clean":
			    	if(roleModelService.cancelModelService(rm)){
			    		if(!OACommon.MODEL_PARENT.equals(mparentId)){//根据条件删除上级节点权限
			    			 List<Integer> modelIds =  oaModelService.findChildOaModel(mparentId);
			    			 List<RoleModel> rms = roleModelService.findRoleModelId(role.getRoleId(), modelIds);
			    			 RoleModel prm = oaRoleService.findOneRoleModel(role.getRoleId().toString(), mparentId);
			    			 RoleModel lprm = prm;
	    				     String rmdataflag = lprm.getDataflag();
			    			 if(rms.size()<=0){
			    				 if(rmdataflag == null || "".equals(rmdataflag)){
										roleModelService.conditionDelService(role.getRoleId(),Integer.parseInt(mparentId));
						    		}else{
						    			lprm.setIsParent("false");
						    			roleModelService.updateRoleModelService(lprm,null);
						    		} 
			    			 }
			    		}
				    	jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
				    }else{
				    	jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
				    }
			    	break;
			   default:
				   if(roleModelService.updateRoleModelService(rm,mparentId)){
					   jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
				    }else{
				    	jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
				    }
			    	break;
		    }
		     return jsonbean;
	 }
	 @RequestMapping("/selNoUrlMeun")
	 @ResponseBody
	 public Object selNoUrlMeun(OaRole role,OaModel model,String mparentId,Integer type){
		 JsonBean jsonbean = null;
		 RoleModel rm = new RoleModel();
		 rm.setRole(role);
		 rm.setModel(model);
		 rm.setIsParent("true");
		 if(type ==1){
				 if(roleModelService.updateRoleModelService(rm,mparentId)){
					 jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","","","");
				 }else{
					 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
				 }
		 }else{
			 if(oaRoleService.cancelModelByIds(role.getRoleId(),model.getModelId())){
				 jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","","","");
			 }else{
				 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			 }
		 }
		 return jsonbean;
	 }
	 /**
	  * 角色添加编辑
	  * @param oaRole
	  * @return
	  */
	 @RequestMapping(value="/addRole",method=RequestMethod.POST)
	 @ResponseBody
	 public Object addRole(HttpServletRequest request,OaRole oaRole,String oldParentId){
		 JsonBean  jsonbean  = null;
		 String opertionType = null;
		 SysUser logingUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
		 if(oaRole.getRoleId() == null || oaRole.getRoleId() == 0){
			 opertionType = "add";
			 oaRole.setUserId(logingUser.getId().toString());
		 }else{
			 opertionType = "update";
		 }
		 if(oaRoleService.saveOrUpdateRoleService(oaRole,oldParentId,opertionType)){
			 jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/role/findRolePage"),"","closeCurrent","","");
		 }else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		 return jsonbean;
	 }
	 /**
	  * 进入编辑页面
	  * @param id
	  * @return
	  */
	 @RequestMapping("/IntoRoleUpdate")
	 public ModelAndView IntoRoleUpdate(String id){
		 ModelAndView mav = new ModelAndView("oarolepage/oaroleadd");
		 OaRole oaRole = oaRoleService.findRoleById(Integer.parseInt(id));
		 if(oaRole.getOrgId() != null && !"".equals(oaRole.getOrgId())){
			 OaOrg org = oaOrgService.getFindOaOrg(Integer.parseInt(oaRole.getOrgId()));
			 mav.addObject("orgName",org.getOrgName());
		 }
		 OaRole poaRole = new OaRole();
		 if(!oaRole.getParentId().equals(OACommon.ROLE_PARENT)){
			 poaRole =  oaRoleService.findRoleByParent(oaRole.getParentId());
		 }
		 mav.addObject("oaRole", oaRole);
		 mav.addObject("poaRole", poaRole);
		 mav.addObject("oldParentId",oaRole.getParentId());
		 return mav;
	 }
	 /**
	  * 角色删除
	  * @param ids
	  * @return
	  */
	 @RequestMapping(value="/delRole",method=RequestMethod.POST)
	 @ResponseBody
	 public Object delRole(String id,String nodeId,String parentId,String name){
		 JsonBean  jsonbean  = null;
		 if(oaRoleService.delRoleService(id,nodeId,parentId,name)){
			 jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/role/findRolePage"),"","","","");
		 }else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,OACommon.modelMap.get("/oa/role/findRolePage"),"","","","");
		 }
		 return jsonbean;
	 }
	 
	 /**
	  * 返回角色树
	  * @param request
	  * @return
	  */
	 @RequestMapping(value="/getRoleTree")
	 @ResponseBody
	 public Object getRoleTree(HttpServletRequest request,String roleId){
		 SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		 List<OaRole> roleList = oaRoleService.findOaRoleList();
		 if(roleId!=null && !"".equals(roleId)){
			 String roleids =  oaRoleService.findRoleNodeChild(roleId);
			 String ids = roleids.substring(2, roleids.length());
			 List<OaRole> nodelist = oaRoleService.findRoleAllById(ids,0); //该角色和所有子节点的集合
			 roleList.removeAll(nodelist);
		 }
		 
		 List<Ztree> ztrees = null;
		 if(null!=roleList && roleList.size()>0){
			 ztrees = new ArrayList<Ztree>();
			 Ztree ztree;
			 for(OaRole oaRole :roleList){
				 ztree = new Ztree();
				 ztree.setId(oaRole.getNodeId());
				 ztree.setpId(oaRole.getParentId());
				 ztree.setName(oaRole.getRoleName());
				 ztree.setIsParent(oaRole.getIsParent());
				 ztree.setOpen("true");
				 ztrees.add(ztree);
			 }
		 }
		 return ztrees;
	 }
	 /**
	  * 进入权限树界面
	  * @return
	  */
	 @RequestMapping("/getIntoRole")
	 public ModelAndView getIntoRole(String id,String keyName){
		 ModelAndView mav = new ModelAndView("/oarolepage/oaroletree");
		 mav.addObject("roleId", id);
		 mav.addObject("keyName",keyName);
		 return mav;
	 }
}
