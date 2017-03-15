package com.oa.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.oa_bean.OaOrg;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.Ztree;
import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AreaService;
import com.dao.OaOrgService;
import com.dao.SysUserService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月11日 上午9:56:17 
 * @version 1.0 
 */
@Controller
@RequestMapping("/oa/org")
public class OaOrgController {
	private OaOrgService oaOrgService;
  
	private SysUserService sysUserService;
  
	private AreaService areaService;
	
    @Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}
    @Autowired
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	
    
    public OaOrgService getOaOrgService() {
		return oaOrgService;
	}
    @Autowired
	public void setOaOrgService(OaOrgService oaOrgService) {
		this.oaOrgService = oaOrgService;
	}
	/**
     * 新增或更新组织机构
     * @param sysuser
     * @param freshValue 刷新类型 fresh:关闭当前窗口刷新  freshnoc:不关闭当前窗口刷新
     * @param nodeIdType 判断同级还是子级
     * @param dataId当前操作的nodeId值
     * @param optype 操作类型 update or add
     * @return
     */
    @RequestMapping(value="/addOaOrg",method=RequestMethod.POST)
    @ResponseBody
    public Object addOaOrg(HttpServletRequest  request,OaOrg oaOrg,String nodeIdType,
    		String dataId,String freshValue,String optype,String province,String city,String region){
    	 JsonBean jsonBean = null;
    	 SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
    	 List<Integer> oaOrglist = (List<Integer>) request.getSession().getAttribute(OACommon.GROUPID);
    	 if(!"0".equals(province) ){
    		 oaOrg.setOrgPCD(province+","+city+","+region);
    	 }else{
    		 oaOrg.setOrgPCD("");
    	 }
    	if(oaOrgService.saveOrUpdateOaOrgService(oaOrg,nodeIdType,dataId,optype,sysUser,oaOrglist)){
    		if("fresh".equals(freshValue)){
    			 jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/org/getOrgDetail"),"","closeCurrent","","");
    		}else if("freshnoc".equals(freshValue)){
    			 jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/org/getOrgDetail"),"","","","");
    		}else if("add".equals(optype)){
    			jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
    		}else if("update".equals(optype)){
    			jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","","","");
    		}
    	}else{
    		 jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}
    	return jsonBean;
    }
    /**
     * 删除组织机构
     * @param ids
     * @return
     */
    @RequestMapping(value="/delOaOrg")
    @ResponseBody
    public Object delOaOrg(String ids,String parentId,String orgName){
    	JsonBean jsonBean = null;
    	if(oaOrgService.delOaOrgService(ids, parentId,orgName)){
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/org/getOrgDetail"),"","","","");
    	}else{
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}
    	return jsonBean;
    }
    
    /**
     * 获取组织机构详情
     * @param orgId
     * @return
     */
    @RequestMapping("/getOrgDetail")
    public ModelAndView getOrgDetail(String orgId){
    	ModelAndView mav = null;
    	List<Province> plist = areaService.findProvinces();
    	if(orgId != null){
    		mav = new ModelAndView("oaorgpage/orgdetailpage");
        	OaOrg org = oaOrgService.getFindOaOrg(Integer.parseInt(orgId));
        	String proid = "";
        	String cityid = "";
        	String disid = "";
        	List<City> clist = null;
        	List<District> dlist = null;
        	if(org.getOrgPCD() != null && !"".equals(org.getOrgPCD())){
        		 proid = org.getOrgPCD().split(",")[0];
            	 cityid = org.getOrgPCD().split(",")[1];
            	 disid = org.getOrgPCD().split(",")[2];
            	 clist = areaService.findCityByPId(Integer.parseInt(proid));
            	 dlist = areaService.findDistrictByCId(Integer.parseInt(cityid));
        	}
        	mav.addObject("org", org);
        	
        	mav.addObject("proid", proid);
        	mav.addObject("cityid", cityid);
        	mav.addObject("disid", disid);
        	
        	mav.addObject("plist", plist);
        	mav.addObject("clist", clist);
        	mav.addObject("dlist", dlist);
    	}else{
    		mav = new ModelAndView("oaorgpage/orgmanagepage");
    		mav.addObject("plist", plist);
    	}
    	return mav;
    } 
    /**
     * 分页获取组织机构
     * @param numPerPage
     * @param pageNum
     * @param page
     * @param sysUser
     * @param sequence
     * @return
     */
    @RequestMapping("/findOaOrgPage")
    public ModelAndView findOaOrgPage(Integer numPerPage,Integer pageNum,Page<OaOrg> page,OaOrg oaOrg,String sequence){
    	ModelAndView mod = new ModelAndView();
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
    	page = oaOrgService.findOaOrgPage(page, oaOrg, orderby);
    	return mod;
    }
    /**
     * 获取机构用户树
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping("/getOrgUserMeun")
    @ResponseBody
    public Object getOrgUserMeun(HttpServletRequest request,String parentId,String modeltype,
    		String reqUrl,String reqRel ){
    	HttpSession session = request.getSession();
    	
    	//获取ip及端口号以及项目目录
    	StringBuffer url = request.getRequestURL();
    	String ipporturl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString();
    	
    	String basepath = request.getContextPath();
    	List<Integer> ids = (List<Integer>) session.getAttribute(OACommon.GROUPID);
    	SysUser sysuser = (SysUser) session.getAttribute(OACommon.LOGIN_USER);
    	List<Ztree> ztrees = null;
    	if(sysuser.getNodeId().equals(OACommon.SYSCREATOR)){
     	   if(parentId == null){
     		  parentId = OACommon.ORGTOP_PARENT;
     		  
     	   }
     	  ztrees = getOrgZtrees(ztrees,parentId,basepath,modeltype,sysuser.getNodeId(),reqUrl,reqRel,ipporturl);
     	}else{
     		Ztree ztree = null;
     		List<OaOrg> orglist = new ArrayList<OaOrg>();
 			 if(parentId == null){
 				 ztrees = new ArrayList<Ztree>();
 				 /*ztree= new Ztree();//生成临时父节点
 	     		 ztree.setTarget("none");
 	     		 if(ids.size()>0){
 	     			 ztree.setIsParent("true");
 	     		 }else{
 	     			ztree.setIsParent("false");
 	     		 }
 	 			 ztree.setName(OACommon.TEMPNAME);
 	 			 ztree.setId(OACommon.TEMPNODE);
 	 			 ztree.setpId(OACommon.TEMPPARENT);
 	 			 ztree.setUrl("");
 	 			 ztrees.add(ztree);*/
 	 			 if(ids.size()>0){
 	 				StringBuffer orgids = new StringBuffer();
 	 	 			 for(int i=0;i<ids.size();i++){
 	 	 				orgids.append(ids.get(i));
 	 	 				orgids.append(",");
 	 	 			 }
 	 	 			 orgids = orgids.deleteCharAt(orgids.length()-1);
 	 	 			 orglist = oaOrgService.findOrgAllById(orgids.toString()); 
 	 			 }
 	 			 for(int i = 0;i<ids.size();i++){
 	 				 if(i>=orglist.size()){
 	 					break;
 	 				 }else{
 	 					OaOrg oaOrg = orglist.get(i);
	 	 				 if(oaOrg != null){
	 	 					oaOrg.setParentId(OACommon.TEMPNODE);
	 	 	 				 ztree = new Ztree();
	 	 	 				 ztree.setTarget("ajax");
	 	 	     			 ztree.setIsParent(oaOrg.getIsParent());
	 	 	 				 ztree.setId(oaOrg.getNodeId());
	 	 	 				 ztree.setpId(oaOrg.getParentId());
	 	 	 				 ztree.setName(oaOrg.getOrgName());
	 	 	 				 ztree.setUrl(basepath+reqUrl+"?orgId="+oaOrg.getOrgId());
	 	 	 				 ztree.setRel(reqRel);
	 	 	 				 ztrees.add(ztree);
	 	 				 }
 	 				 }
 	 			 }
 			 }else{
 				     ztrees = getOrgZtrees(ztrees,parentId,basepath,modeltype,sysuser.getNodeId(),reqUrl,reqRel,ipporturl);
 			 }
     	}
    	return ztrees;
    } 
    
    /**
     * 创建关联人员的组织机构树
     * @param ztrees
     * @param parentId
     * @param basepath
     * @return
     */
    public List<Ztree> getOrgZtrees(List<Ztree> ztrees,String parentId,String basepath,
    		String modeltype,String sysnodeId,String reqUrl,String reqRel,String ipporturl){
    	    List<OaOrg> oaOrgs = oaOrgService.getNodeOaOrg(parentId);
    	    if(ztrees == null)ztrees = new ArrayList<Ztree>();
			Ztree ztree =null;
			OaOrg  oaOrg = null;
			if(OACommon.ORGTOP_PARENT.equals(parentId)){//生成临时父节点
 	 			/* ztree= new Ztree();
 	     		 ztree.setTarget("none");
 	     		 if(oaOrgs.size()>0){
 	     			ztree.setIsParent("true");
 	     		 }else{
 	     			ztree.setIsParent("false"); 
 	     		 }
 	 			 ztree.setName(OACommon.TEMPNAME);
 	 			 ztree.setId(OACommon.ORGTOP_PARENT);
 	 			 ztree.setpId(OACommon.TEMPPARENT);
 	 			 ztree.setUrl("");
 	 			 ztrees.add(ztree);*/
 	 			 if(sysnodeId.equals(OACommon.SYSCREATOR) && "sysuser".equals(modeltype)){//最高权限查看所有成员
 	 				 ztree= new Ztree();
 	 	     		 ztree.setTarget("ajax");
 	 	 			 ztree.setName(OACommon.ALLADMIN);
 	 	 			 ztree.setId(OACommon.ORGTOPNODE);
 	 	 			 ztree.setpId(OACommon.ORGTOP_PARENT);
 	 	 			 ztree.setUrl(basepath+reqUrl+"?orgId="+OACommon.ORGTOPNODE);
 	 				 ztree.setRel(reqRel);
 	 				 ztree.setIcon(ipporturl+OACommon.ICONPATH);
 	 	 			 ztrees.add(ztree);
 	 			 }
			}
			for(int j=0;j<oaOrgs.size();j++){
				     oaOrg = oaOrgs.get(j);
	 				 ztree = new Ztree();
	 				 ztree.setTarget("ajax");
	     			 ztree.setIsParent(oaOrg.getIsParent());
	 				 ztree.setId(oaOrg.getNodeId());
	 				 ztree.setpId(oaOrg.getParentId());
	 				 ztree.setName(oaOrg.getOrgName());
	 				 ztree.setUrl(basepath+reqUrl+"?orgId="+oaOrg.getOrgId());
	 	 			 ztree.setRel(reqRel);
	 				 ztrees.add(ztree);
			 }
			return ztrees;
    }
    @RequestMapping("/gotoAddOrg")
	public ModelAndView gotoAddOrg(String parentId,String nodeId){
		ModelAndView mav = new ModelAndView("oaorgpage/addoaorg");
		List<Province> plist = areaService.findProvinces();
		mav.addObject("plist", plist);
		String dataId = null;
		String datatype =null;
		if(parentId == null){
	    	dataId = nodeId;
	    	datatype = "child";
	    }else{
	    	dataId =parentId+";"+nodeId;
	    	datatype = "brother";
	    }
		mav.addObject("dataId", dataId);
		mav.addObject("datatype", datatype);
		return mav;
	}
    /**
     * 获取下一级org信息
     * @param ztrees
     * @param parentId
     * @param basepath
     * @param sysnodeId
     * @return
     */
    
    public List<Ztree> getOnelevelOrg(List<Ztree> ztrees,String parentId,String basepath,String sysnodeId,String reqUrl,String reqRel){
    	 List<OaOrg> oaOrgs = oaOrgService.getNodeOaOrg(parentId);
 	    if(ztrees == null)ztrees = new ArrayList<Ztree>();
			Ztree ztree =null;
			OaOrg  oaOrg = null;
			/*if(OACommon.ORGTOP_PARENT.equals(parentId)){//生成临时父节点
	 			 ztree= new Ztree();
	     		 ztree.setTarget("none");
	     		 if(oaOrgs.size()>0){
	     			ztree.setIsParent("true");
	     		 }else{
	     			ztree.setIsParent("false"); 
	     		 }
	 			 ztree.setName(OACommon.TEMPNAME);
	 			 ztree.setId(OACommon.ORGTOP_PARENT);
	 			 ztree.setpId(OACommon.TEMPPARENT);
	 			 ztree.setUrl("");
	 			 ztrees.add(ztree);
			}*/
			for(int j=0;j<oaOrgs.size();j++){
				     oaOrg = oaOrgs.get(j);
	 				 ztree = new Ztree();
	 				 ztree.setTarget("ajax");
	     			 ztree.setIsParent(oaOrg.getIsParent());
	 				 ztree.setId(oaOrg.getNodeId());
	 				 ztree.setpId(oaOrg.getParentId());
	 				 ztree.setName(oaOrg.getOrgName());
	 				 ztree.setUrl(basepath+reqUrl+"?orgId="+oaOrg.getOrgId());
	 	 			 ztree.setRel(reqRel);
	 				 ztrees.add(ztree);
			 }
			return ztrees;
    }
    /**
     * 同步加载机构树
     * @param request
     * @param parentId
     * @return
     */
    @RequestMapping("/getOrgTree")
    @ResponseBody
    public Object getOrgTree(HttpServletRequest request,String type){
    	HttpSession session = request.getSession();
    	SysUser sysuser = (SysUser) session.getAttribute(OACommon.LOGIN_USER);
    	List<OaOrg> orgList = new ArrayList<OaOrg>();
    	if("all".equals(type)){
    		orgList = oaOrgService.getAllOrg();
    	}else{
    		if(OACommon.SYSCREATOR.equals(sysuser.getNodeId())){
    			orgList = oaOrgService.getAllOrg();
    		}else{
    			String orgIds = sysuser.getOrgIds();
    			String children =  oaOrgService.findMoreOrgNodeChild(orgIds.toString(),sysuser.getOaOrg().size() ); 	
    			String childrenids = children.substring(2, children.length());
    			orgList = oaOrgService.findOrgAllById(childrenids);
    		}
    	}
    	List<Ztree> ztreelist = new ArrayList<Ztree>();
    	Ztree ztree =null;
    	for (int i=0 ;i<orgList.size();i++){
    		 ztree = new Ztree();
    		 ztree.setIsParent(orgList.get(i).getIsParent());
			 ztree.setId(orgList.get(i).getNodeId());
			 ztree.setpId(orgList.get(i).getParentId());
			 ztree.setName(orgList.get(i).getOrgName());
			 ztreelist.add(ztree);
		}
    	return ztreelist;
    }
}