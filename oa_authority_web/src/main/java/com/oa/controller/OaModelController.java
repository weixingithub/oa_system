package com.oa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.oa_bean.OaModel;
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
import com.dao.OaRoleService;
import com.dao.RoleModelService;
import com.tool.util.AuthorityCommonUtil;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
@Controller
@RequestMapping("/oa/model")
public class OaModelController {
	private OaRoleService oaRoleService;
	
	private RoleModelService roleModelService;
	
	private OaModelService oaModelService;
		
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
	/**
	 * 生成功能树形菜单
	 * @param request
	 * @param parentId
	 * @return
	 * author weixin
	 */
	@RequestMapping("/meun")
	@ResponseBody
	public List<Ztree> getModelMeun(HttpServletRequest request,String parentId){
		
		SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<OaModel> models = null;
		HashSet<String> modelIdSet = new HashSet<String>();//获取用户可操作的模块id
		Map<String,String> map = new HashMap<String,String>();
		List<Integer> rolelist = (List<Integer>) request.getSession().getAttribute(OACommon.ROLEID);
		if(!OACommon.SYSCREATOR.equals(sysUser.getNodeId())){//区分创建者与非创建者
			//获取角色权限信息保存到session中
			Map<String,HashSet<String>> mapModel = new HashMap<String,HashSet<String>>();
			String basepath = request.getContextPath();
			List<RoleModel> rms =  roleModelService.findRModelList(rolelist);
			Map<Integer,OaModel> mapRm = roleModelService.findRmMap(rolelist);
			HashSet<String> sm;
			for(RoleModel rm :rms){
				String modelUrl = mapRm.get(rm.getId()).getModelUrl();
				String modelId = mapRm.get(rm.getId()).getModelId().toString();
				String isParent = rm.getIsParent();
				if(!"true".equals(isParent)){
					map.put(modelId, isParent);
				}else{
					map.put(modelId, "true");
				}
				modelIdSet.add(modelId);
				sm = mapModel.get(basepath+modelUrl);
				if(sm == null){
					sm =new HashSet<String>();
				}
				String dataflag = rm.getDataflag();
				if(dataflag != null && !"".equals(dataflag)){
					sm.addAll(Arrays.asList(dataflag.split(",")));
				}
				String datatype = rm.getDatatype();
				if(datatype != null && !"".equals(datatype)){
					sm.add(datatype);
				}
				mapModel.put(basepath+modelUrl,sm);
			}
			request.getSession().setAttribute(OACommon.ROLEMODEL, mapModel);
			//生成用户所拥有的模块树
			models = analysisModels(parentId,modelIdSet);
		}else{
			if(parentId == null){
				parentId = OACommon.MODEL_PARENT;
			}
			models = oaModelService.findOaModleByParentId(parentId);
		}
		List<Ztree> ztrees = null;
		if(null != models && models.size()>0){
			ztrees = new ArrayList<Ztree>();
			Ztree ztree;
			/*if(parentId == null || OACommon.MODEL_PARENT.equals(parentId)){
				 ztree= new Ztree();//生成临时父节点
	    		 ztree.setTarget("none");
				 ztree.setIsParent("true");
				 ztree.setName(OACommon.MEUNNAME);
				 ztree.setId(OACommon.TEMPNODE);
				 ztree.setpId(OACommon.TEMPPARENT);
				 ztree.setUrl("");
				 ztrees.add(ztree);
			 }*/
            String basepath = request.getContextPath();
            
            AuthorityCommonUtil.initModelNodeId(models);
			for(OaModel model : models){
				ztree = new Ztree();
				ztree.setId(model.getNodeId());
				if(parentId == null || OACommon.MODEL_PARENT.equals(parentId)){
					ztree.setpId(OACommon.TEMPNODE);
				}else{
					ztree.setpId(model.getParentId());
				}
				ztree.setName(model.getModelName());
				
				if(!OACommon.SYSCREATOR.equals(sysUser.getNodeId())){//区分创建者与非创建者
					if(!modelIdSet.contains(model.getModelId().toString()) && "true".equals(map.get(model.getModelId().toString()))){
						ztree.setIsParent("false");
						roleModelService.conditionUpdateService(rolelist,model.getModelId(),"false");
					}else{
						ztree.setIsParent(map.get(model.getModelId().toString()));
					}
					
				}else{
					ztree.setIsParent(model.getIsParent());
				}
				String url = model.getModelUrl();
				if("".equals(url) || url == null){
					ztree.setUrl("");
					ztree.setTarget("none");
				}else{
					ztree.setUrl(basepath+model.getModelUrl());
					ztree.setTarget("navTab");
					if("true".equals(model.getpAuto())){
						ztree.setRel(OACommon.PERSONWELFARENODEID);
					}else{
						ztree.setRel(model.getNodeId());
					}
					ztree.setExternal(model.getModelIframe());
				}
				
				ztrees.add(ztree);
			}
		}
		return ztrees;
	}
	
	
	@RequestMapping("/makeMore")
	public ModelAndView findOaModelPage(HttpServletRequest request,String roleId,Integer numPerPage
     		,Integer pageNum,Page<OaModel> page,OaModel oaModel,String sequence){
		   SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
		   ModelAndView mav = new ModelAndView("oarolepage/roleconfigure");
		   LinkedHashMap<String,String> orderby  = null;
		   List<Integer> modelIds = null;
		   if(numPerPage == null){
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
		   modelIds = roleModelService.findModelsIdByRole(Integer.parseInt(roleId));
		   if(OACommon.SYSCREATOR.equals(sysUser.getNodeId())){
			   page = oaModelService.findOaModelPage(page, oaModel, orderby,null,0);
			  
		   }else{
			   List<Integer> roleIds = (List<Integer>) request.getSession().getAttribute(OACommon.ROLEID);
			   List<Integer> ownmodelIds = roleModelService.findModelsIdByRoles(roleIds);
			   page = oaModelService.findOaModelPage(page, oaModel, orderby,ownmodelIds,sysUser.getId());
		   }
	    	mav.addObject("page", page);
	    	mav.addObject("oaModel", oaModel);
	    	mav.addObject("roleId",roleId);
	    	mav.addObject("modelIds", modelIds);
		   return mav;
	}
	@RequestMapping("/getModelDetail")
	public ModelAndView getModelDetail(HttpServletRequest request,String modelId){
		ModelAndView mav = null;
		String userflag="0";//非创建者
		SysUser sysuser=(SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);//获取当前登录者信息
		if(sysuser.getNodeId().equals(OACommon.SYSCREATOR)){
			userflag="1";//创建者
		}
		if(modelId != null){
			OaModel oaModel = oaModelService.findModelById(modelId);
			mav = new ModelAndView("oamodelpage/modeldetailpage");
			OaRole poaRole = new OaRole();
			if(oaModel.getCheckRoleId() != null && !"".equals(oaModel.getCheckRoleId())){
				 poaRole =  oaRoleService.findRoleByParent(oaModel.getCheckRoleId());
			}
			mav.addObject("oaModel", oaModel);
			mav.addObject("poaRole",poaRole);
			mav.addObject("userflag", userflag);
		}else{
			mav = new ModelAndView("oamodelpage/modelmanagepage");
			mav.addObject("userflag", userflag);
		}
		return mav;
	}
	
	@RequestMapping("/getModelUserMeun")
	@ResponseBody
	public List<Ztree> getModelUserMeun(HttpServletRequest request,String parentId){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<OaModel> models = null;
		HashSet<String> modelIdSet = new HashSet<String>();//获取用户可操作的模块id
		Map<String,String> map = new HashMap<String,String>();
		List<Integer> rolelist = (List<Integer>) request.getSession().getAttribute(OACommon.ROLEID);
		if(!OACommon.SYSCREATOR.equals(sysUser.getNodeId())){//区分创建者与非创建者
			List<RoleModel> rms =  roleModelService.findRModelList(rolelist);
			Map<Integer,OaModel> mapRm = roleModelService.findRmMap(rolelist);
			for(RoleModel rm :rms){
				String modelId = mapRm.get(rm.getId()).getModelId().toString();
				String isParent = rm.getIsParent();
				modelIdSet.add(modelId);
				if(!"true".equals(isParent)){
					map.put(modelId, isParent);
				}else{
					map.put(modelId, "true");
				}
			}
			//生成用户所拥有的模块树
			models = analysisModels(parentId,modelIdSet);
		}else{
			if(parentId == null){
				parentId = OACommon.MODEL_PARENT;
			}
			models = oaModelService.findOaModleByParentId(parentId);
		}
		List<Ztree> ztrees = null;
		if(null != models && models.size()>0){
			 ztrees = new ArrayList<Ztree>();
			 Ztree ztree;
			 /*if(parentId == null || OACommon.MODEL_PARENT.equals(parentId)){
				 ztree= new Ztree();//生成临时父节点
	     		 ztree.setTarget("none");
	 			 ztree.setIsParent("true");
	 			 ztree.setName(OACommon.MEUNNAME);
	 			 ztree.setId(OACommon.TEMPNODE);
	 			 ztree.setpId(OACommon.TEMPPARENT);
	 			 ztree.setUrl("");
	 			 ztrees.add(ztree);
			 }*/
            String basepath = request.getContextPath();
			for(OaModel model : models){
				ztree = new Ztree();
				ztree.setId(model.getNodeId());
				if(parentId == null || OACommon.MODEL_PARENT.equals(parentId)){
					ztree.setpId(OACommon.TEMPNODE);
				}else{
					ztree.setpId(model.getParentId());
				}
				ztree.setName(model.getModelName());
				
				if(!OACommon.SYSCREATOR.equals(sysUser.getNodeId())){//区分创建者与非创建者
					if(!modelIdSet.contains(model.getModelId().toString()) && "true".equals(map.get(model.getModelId().toString()))){
						ztree.setIsParent("false");
						roleModelService.conditionUpdateService(rolelist,model.getModelId(),"false");
					}else{
						ztree.setIsParent(map.get(model.getModelId().toString()));
					}
				}else{
					ztree.setIsParent(model.getIsParent());
				}
				ztree.setUrl(basepath+OACommon.FINDMODELDETAIL+model.getModelId());
				ztree.setTarget("ajax");
				ztree.setRel("modelBox");
				ztree.setExternal(model.getModelIframe());
				ztrees.add(ztree);
			}
		}
		return ztrees;
	}
	/**
	 * 获得所属模块	
	 * @param parentId
	 * @param modelIdSet
	 * @return
	 */
	public List<OaModel> analysisModels(String parentId,HashSet<String> modelIdSet){
		List<OaModel> oamodels =  oaModelService.findModelById(modelIdSet);
		List<OaModel> models = new ArrayList<OaModel>();
		Set<String> modelPId = new HashSet<String>();
		if(oamodels != null){
			OaModel m;
			for(int i=0;i<oamodels.size();i++){
				m = oamodels.get(i);
				modelPId.add(m.getParentId());
			}
			modelIdSet.retainAll(modelPId);
			modelPId.removeAll(modelIdSet);
			if(parentId == null){
				for(int j=0;j<oamodels.size();j++){
					m = oamodels.get(j);
					Iterator iterator = modelPId.iterator();
					while(iterator.hasNext()){
						if(m.getParentId().equals(iterator.next())){
							models.add(m);
						}
					}
				}
			}else{
				for(int i=0;i<oamodels.size();i++){
					m = oamodels.get(i);
					if(m.getParentId().equals(parentId)){
						models.add(m);
					}
				}
			}
		}
		return models;
	}
	@RequestMapping(value="/addOaModel",method = RequestMethod.POST)
	@ResponseBody
	public Object addOaModel(HttpServletRequest request,OaModel oaModel,
			String freshValue,String optype,
			String nodeIdType,String dataId,boolean autoIsCheckModel){
		JsonBean jsonBean = null;
		if(oaModel.getModelUrl() != null && !"".equals(oaModel.getModelUrl())){
			String modelUrl =  oaModel.getModelUrl().replaceAll(" ", "");
			oaModel.setModelUrl(modelUrl);
		}
		if("add".equals(optype)){
			SysUser user = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
			oaModel.setUserId(user.getId());
		}
		if(oaModelService.addOrUpdateOaModelService(oaModel,optype,nodeIdType,dataId)){
			if("fresh".equals(freshValue)){
				 jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/model/getModelDetail"),"","","","");
			}else if("noclose".equals(freshValue)){
				jsonBean = new JsonBean("200",OACommon.MODELADDSECCUSS,"","","","","");
			}else if("add".equals(optype)){
    			jsonBean = new JsonBean("200",OACommon.MODELADDSECCUSS,"","","closeCurrent","","");
    		}else if("update".equals(optype)){
    			jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","","","");
    		}
		}else{
			 jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		return jsonBean;
	}
	@RequestMapping("/delOaModel")
	@ResponseBody
	public Object delOaModel(String id,String parentId,
			String nodeId,String modelname,String misparent,String pAuto){
		JsonBean jsonBean = null;
		String modleIds = oaModelService.findModelChlidren(parentId);
		String[] arrayId = modleIds.split(",");
		if(oaModelService.delModelService(id,nodeId,parentId,modelname,misparent,arrayId.length,pAuto)){
			jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/model/getModelDetail"),"","","","");
		}else{
			jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		return jsonBean;
	}
	@RequestMapping("/gotoAddModel")
	public ModelAndView gotoAddModel(String parentId,String nodeId,String userflag){
		ModelAndView mav = new ModelAndView("oamodelpage/addoamodel");
		String dataId = null;
		String datatype =null;
		if(parentId == null){
	    	dataId = nodeId;
	    	datatype = "child";
	    }else{
	    	dataId =parentId;
	    	datatype = "brother";
	    }
		mav.addObject("dataId", dataId);
		mav.addObject("datatype", datatype);
		mav.addObject("userflag", userflag);
		return mav;
	}
	 /**
     * 同步加载模块树
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping("/getModelTree")
    @ResponseBody
	public Object getModelTree(HttpServletRequest request,String modelType ){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<OaModel> models = null;
		if(!OACommon.SYSCREATOR.equals(sysUser.getNodeId())){//区分创建者与非创建者
			List<Integer> roleIds = (List<Integer>) request.getSession().getAttribute(OACommon.ROLEID);
			List<Integer> ownmodelIds = roleModelService.findModelsIdByRoles(roleIds);
			HashSet<String> modelIdSet = new HashSet<String>();//获取用户可操作的模块id
			for (Integer integer : ownmodelIds) {
				modelIdSet.add(integer.toString());
			}
			models = oaModelService.findModelById(modelIdSet);
		//	oaModelService.findModelById(modelIds)
			//生成用户所拥有的模块树
		}else{
			models = oaModelService.findAllModel();
		}
		List<OaModel> modellist = new ArrayList<OaModel>();
		if(OACommon.MODELAUTO.equals(modelType)){
			for (int i=0;i<models.size();i++) {
				if("true".equals(models.get(i).getpAuto())&& !"".equals(models.get(i).getCheckRoleId())){
					modellist.add(models.get(i));
				}
			}
		}else{
			modellist = models;
		}
		List<Ztree> ztrees = null;
		if(null != models && modellist.size()>0){
			 ztrees = new ArrayList<Ztree>();
			 Ztree ztree;
			for(OaModel model : modellist){
				ztree = new Ztree();
				ztree.setIsParent(model.getIsParent());
				ztree.setId(model.getNodeId());
				ztree.setpId(model.getParentId());
				ztree.setName(model.getModelName());
				ztree.setExternal(model.getModelIframe());
				ztrees.add(ztree);
			}
		}
		return ztrees;
	}
    /**
     * 分页选择回调
     * （SELECTIVE_CHECKBOX ：多选 ）
     * （SELECTIVE_RADIO ：单选 ）
     * （MODE_TYPE_AUTO ：自动 ）
     * （MODE_TYPE_CUSTOM ：自定义 ）
     * @param request
     * @param numPerPage
     * @param pageNum
     * @param page
     * @param oaModel
     * @param select
     * @param modelType
     * @return
     */
    @RequestMapping("/findOaModelPageSelect")
	public ModelAndView findOaModelPageSelect(HttpServletRequest request,Integer numPerPage
     		,Integer pageNum,Page<OaModel> page,OaModel oaModel ,String select,String modelType){
		  SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);	
		  ModelAndView mav = null;
		  if(OACommon.SELECTIVE_CHECKBOX.equals(select)){
			  mav =  new ModelAndView("oamodelpage/model_checkbox");
		  }else if(OACommon.SELECTIVE_RADIO.equals(select)){
			  mav =  new ModelAndView("oamodelpage/model_radio");
		  }
		  if(OACommon.MODE_TYPE_AUTO.equals(modelType)){ 
			  
		  }else if(OACommon.MODE_TYPE_CUSTOM.equals(modelType)){
			  
		  }
		   LinkedHashMap<String,String> orderby  = null;
		   if(numPerPage == null){
	     		page = new Page<OaModel>(100);
	     	}else{
	     		page.setPageSize(numPerPage); 
	  		    page.setPageNo(pageNum);
	     	}
		   if(OACommon.SYSCREATOR.equals(sysUser.getNodeId())){
			   page = oaModelService.findOaModelPage(page, oaModel, orderby,null,0);
		   }else{
			   List<Integer> roleIds = (List<Integer>) request.getSession().getAttribute(OACommon.ROLEID);
			   List<Integer> ownmodelIds = roleModelService.findModelsIdByRoles(roleIds);
			   page = oaModelService.findOaModelPage(page, oaModel, orderby,ownmodelIds,sysUser.getId());
		   }
	    	mav.addObject("page", page);
	    	mav.addObject("oaModel", oaModel);
	    	mav.addObject("select", select);
	    	mav.addObject("modelType", modelType);
		   return mav;
	}
}