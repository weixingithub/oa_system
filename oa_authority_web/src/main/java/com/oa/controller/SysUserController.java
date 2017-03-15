package com.oa.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.Ztree;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OaOrgService;
import com.dao.OaRoleService;
import com.dao.SysUserService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
import com.tool.util.PasswordHash;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月7日 上午10:33:25 
 * @version 1.0 
 */
@Controller
@RequestMapping("/oa/sysuser")
public class SysUserController {
    private SysUserService sysUserService;
    private OaRoleService oaRoleService;
    private OaOrgService oaOrgService;

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
    public OaRoleService getOaRoleService() {
		return oaRoleService;
	 }
	 @Autowired
	 public void setOaRoleService(OaRoleService oaRoleService) {
			this.oaRoleService = oaRoleService;
	 }
    /**
	 * 登录成功跳转主页
	 * @return
	 */
	@RequestMapping("/main")
	public String loginSuccess(){
		return "index";
	}
	/**
     * 新增或更新管理员
     * @param sysuser
     * @return
     */
    @RequestMapping(value="/addSysUser",method=RequestMethod.POST)
    @ResponseBody
    public Object addSysUser(SysUser sysuser,String newUserPwd){
    	 JsonBean jsonBean = null;
    	 String opertionType=null;
    	 sysuser.setRealName(sysuser.getRealName().trim());
    	 sysuser.setUserName(sysuser.getUserName().trim());
    	 try {
    		 if(newUserPwd!=null && !"".equals(newUserPwd)){
    			 String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
    			 String screpassword = PasswordHash.createHash(newUserPwd);
    			 sysuser.setUserPwd(screpassword);
    			 sysuser.setUserCreateTime(createTime);
    			 OaOrg oaOrg = new OaOrg();
    			 if(!"top".equals(sysuser.getOrgIds())){//全体成员中创建管理员 
    				 oaOrg.setOrgId(Integer.parseInt(sysuser.getOrgIds()));
    				 List<OaOrg> list = new ArrayList<OaOrg>();
        			 list.add(oaOrg);
        			 sysuser.setOaOrg(list);
    			 }else{
    				 sysuser.setOrgIds("");
    			 }
    		 }
    		 if(sysuser.getId() == null || sysuser.getId() == 0){
    			 opertionType = "add";
    			 sysuser.setParetId(OACommon.SYSCREATOR);
    		 }else{
    			 opertionType = "update";
    		 }
			if(sysUserService.saveOrUpdateSysUserService(sysuser,opertionType)){
				jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","sysUserBox","closeCurrent","","");
			}else{
				jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","sysUserBox","","","");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
    	return jsonBean;
    }
    /**
     * 进入添加页面
     * @param orgIds
     * @return
     */
    @RequestMapping(value="/addIntoSysUser")
    public ModelAndView addIntoSysUser(String orgIds){
    	ModelAndView mav = new ModelAndView("sysuserpage/sysuseradd");
    	mav.addObject("orgIds", orgIds);
    	mav.addObject("edit",false); //用户名可编辑
    	return mav;
    }
    /**
     * 进入编辑页面
     * @param id
     * @return
     */
    @RequestMapping(value="/updateIntoSysUser")
    public ModelAndView updateIntoSysUser(Integer id){
    	ModelAndView mav = new ModelAndView("sysuserpage/sysuseradd");
    	SysUser sysuser = sysUserService.getFindSysUser(id);
    	mav.addObject("userinfo", sysuser);
    	mav.addObject("orgIds", sysuser.getOrgIds());
    	mav.addObject("userpassword", sysuser.getUserPwd());
    	mav.addObject("edit",true); //用户名不可编辑
    	return mav;
    }
    /**
     * 创建者同步获取角色和机构信息
     * 同时将该用户所拥有的权限信息选中并显示在页面
     * @param request
     * @param id
     * @param parentId
     * @return
     */
    @RequestMapping(value="/configureCreator")
    @ResponseBody
    public Map<String,Object> getCreatorRole(HttpServletRequest request,Integer id) {
    	Ztree rtree=null;//角色树对象
    	Ztree otree=null;//结构树对象
    	List<Ztree> roletree=new ArrayList<Ztree>();//角色树
    	List<Ztree> orgtree=new ArrayList<Ztree>();//机构树
    	Map<String,Object> map=new HashMap<String, Object>();//用来存放登录者的角色树的集合以及该配置用户的角色信息
    	/**
    	 * 创建者的配置功能start
    	 */
    	 List<OaRole> creatorrolelist=new ArrayList<OaRole>();//角色信息
    	 List<OaOrg> creatororglist=new ArrayList<OaOrg>();//机构信息
		//查询出该配置用户所拥有的角色信息
       	 SysUser user=sysUserService.getFindSysUser(id);
       	 List<OaRole> listconRole=new ArrayList<OaRole>();
       	 listconRole= user.getOaRole();//用户角色信息
       	 List<OaOrg> listconOrg=new ArrayList<OaOrg>();
       	 listconOrg=user.getOaOrg();//用户机构信息
       	 map.put("rolelist", listconRole);
       	 map.put("orglist", listconOrg);
       	 List<String> conRoleIds = new ArrayList<String>();//获取配置用户的所有角色id
       	 if(listconRole.size()>0){
       		 for(int y=0;y<listconRole.size();y++){
		   		 String nodeId = listconRole.get(y).getNodeId();
		   		 conRoleIds.add(nodeId);
		   	 }
       	 }
       	 map.put("roleid", conRoleIds);
    	 List<String> conOrgIds = new ArrayList<String>();//获取配置用户的所有管辖机构id(包含子节点)
  	   	 StringBuffer orgIds = new StringBuffer();//获取配置用户的管辖机构id(不包含子节点)
  	   	 if(listconOrg.size()>0){
	  	   	for(int y=0;y<listconOrg.size();y++){
	  	   		Integer orgId = listconOrg.get(y).getOrgId();
	  	   	    orgIds.append(orgId);
	  	   	    orgIds.append(",");
	  	   	 }
	  	   	 orgIds = orgIds.deleteCharAt(orgIds.length()-1);
	  	     String children =  oaOrgService.findMoreOrgNodeChild(orgIds.toString(), listconOrg.size());
	  	     String array[] = children.split(","); 
	   		 for(int a=1;a<array.length;a++){
	   			 conOrgIds.add(array[a]);
	   		 }
  	   	 }
  	     map.put("orgid", orgIds.toString());
    	 creatorrolelist=oaRoleService.getAllRole();
				if(creatorrolelist!=null && creatorrolelist.size()>0){
					for(OaRole role:creatorrolelist){
	 					rtree=new Ztree();
	 					if(listconRole!=null && listconRole.size()>0){//该用户已经拥有角色
	 						if(conRoleIds.size()>0 && conRoleIds.contains(role.getNodeId())){//将该用户拥有的角色选中并显示到页面上
	 	 					rtree.setId(role.getNodeId());
							rtree.setpId(role.getParentId());
							rtree.setName(role.getRoleName());
							rtree.setIsParent(role.getIsParent());
							rtree.setOpen("true");
							rtree.setChecked("true");
							roletree.add(rtree);
	 						}else{//不包含的部分不选中，直接加载树
	 	 					rtree.setId(role.getNodeId());
							rtree.setpId(role.getParentId());
							rtree.setName(role.getRoleName());
							rtree.setIsParent(role.getIsParent());
							rtree.setOpen("true");
							roletree.add(rtree);
	 						}
	 						}else{//该用户不拥有任何角色则正常加载树
	 	 					rtree.setId(role.getNodeId());
							rtree.setpId(role.getParentId());
							rtree.setName(role.getRoleName());
							rtree.setIsParent(role.getIsParent());
							rtree.setOpen("true");
							roletree.add(rtree);
	 						}
					}
					
				}
				map.put("roletree", roletree);
				creatororglist=oaOrgService.getAllOrg();
	   			for(OaOrg creaorg:creatororglist){
	   				otree=new Ztree();
	   				if(listconOrg!=null && listconOrg.size()>0){//该用户是否已经拥有管辖机构
	   					if(conOrgIds.size()>0 && conOrgIds.contains(creaorg.getNodeId())){//将该用户拥有的机构默认选中显示
	   						otree.setId(creaorg.getNodeId());
							otree.setpId(creaorg.getParentId());
							otree.setName(creaorg.getOrgName());
							otree.setChecked("true");
							otree.setIsParent(creaorg.getIsParent());
							otree.setOpen("true");
							orgtree.add(otree);
	   					}else{//其它则正常显示
	   						otree.setId(creaorg.getNodeId());
							otree.setpId(creaorg.getParentId());
							otree.setName(creaorg.getOrgName());
							otree.setIsParent(creaorg.getIsParent());
							otree.setOpen("true");
							orgtree.add(otree);
	   					}
	   				}else{//该用户还未拥有任何管辖机构
	   					otree.setId(creaorg.getNodeId());
						otree.setpId(creaorg.getParentId());
						otree.setName(creaorg.getOrgName());
						otree.setIsParent(creaorg.getIsParent());
						otree.setOpen("true");
						orgtree.add(otree);
	   				}
	   			}
	   		 map.put("orgtree", orgtree);
		return map;
    }
    /**
     * 用户配置权限功能
     * 生成树同时将该配置用户的权限信息选中显示
     * @param request
     * @param id
     * @param parentId
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/configureSysUser")
    @ResponseBody
    public Map<String,Object> getSysUserRole(HttpServletRequest request,Integer id,String parentId){
    	Ztree rtree=null;//角色树对象
    	Ztree otree=null;//机构树对象
    	List<Ztree> roletree=new ArrayList<Ztree>();//角色树
    	List<Ztree> orgtree=new ArrayList<Ztree>();//机构树
    	Map<String,Object> map=new HashMap<String, Object>();//用来存放登录者的角色树的集合以及该配置用户的角色信息
    	HttpSession session = request.getSession();
    	/**
    	 * 创建者的配置功能start
    	 */
		List<Integer> roleIds=(List<Integer>) session.getAttribute(OACommon.ROLEID);//获取该用户的角色id
		List<Integer> orgIds= (List<Integer>) session.getAttribute(OACommon.GROUPID);//该用户的机构id
		SysUser sysUser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
    	//查询出该配置用户所拥有的角色信息
    	 SysUser user=sysUserService.getFindSysUser(id);
    	 List<OaRole> listconRole=new ArrayList<OaRole>();
    	 listconRole= user.getOaRole();
    	//查询出该配置用户已经拥有的机构信息
    	 List<OaOrg> listconOrg=user.getOaOrg();
	   	 map.put("orglist", listconOrg);
    	 map.put("rolelist", listconRole);
    	 List<String> conRoleIds = new ArrayList<String>();//获取配置用户的所有角色id
    	 StringBuffer roleids = new StringBuffer();
    	 if(listconRole.size()>0){
	   	 for(int y=0;y<listconRole.size();y++){
	   		 String nodeId = listconRole.get(y).getNodeId();
	   		conRoleIds.add(nodeId);
	   		roleids.append(nodeId);
	   		roleids.append(",");
	   	 }
	   		 roleids = roleids.deleteCharAt(roleids.length()-1);
	   	 }
	   	 map.put("roleid", roleids);
		 List<String> conOrgIds = new ArrayList<String>();//获取配置用户的所有管辖机构id
	   	 StringBuffer sborgids = new StringBuffer();
	   	 if(listconOrg.size()>0){
	   		for(int y=0;y<listconOrg.size();y++){
		   		Integer orgId = listconOrg.get(y).getOrgId();
		   		sborgids.append(orgId);
		   		sborgids.append(",");
		   	 }
	   		sborgids = sborgids.deleteCharAt(sborgids.length()-1);
	   		String children =  oaOrgService.findMoreOrgNodeChild(sborgids.toString(), listconOrg.size());
	   		String array[] = children.split(","); 
	   		for(int a=1;a<array.length;a++){
	   			conOrgIds.add(array[a]);
	   		}
	   	 }
	   	 map.put("orgid", sborgids.toString());
	   	 /**获取登录者所拥有所有角色 start**/
	   	StringBuffer luroleids = new StringBuffer();
	    if(roleIds!= null && roleIds.size()>0){
	        for(int i=0;i<roleIds.size();i++){
	        	luroleids.append(roleIds.get(i));
	        	luroleids.append(",");
	        }
	        luroleids=luroleids.deleteCharAt(luroleids.length()-1);
	    }
    	List<OaRole> listrole=oaRoleService.findRoleAllById(luroleids.toString(),sysUser.getId());//该登录者所拥有的所有角色
   	    /**获取登录者所拥有所有角色 end**/
        /**获取登录者所拥有所有机构start **/
    	 String oids = null;
	   	 if(orgIds != null && orgIds.size()>0){
	   		 StringBuffer luorgids = new StringBuffer();
		   	 for(int i=0;i<orgIds.size();i++){
		   		luorgids.append(orgIds.get(i));
		   		luorgids.append(",");
		   	 }
		   	luorgids = luorgids.deleteCharAt(luorgids.length()-1);
		   	String orgids= oaOrgService.findMoreOrgNodeChild(luorgids.toString(), orgIds.size());//该机构以及它下面的子节点id
			oids = orgids.substring(2, orgids.length());
	   	 }
    	List<OaOrg> listOrg= oaOrgService.findOrgAllById(oids);  //该结构下的所有机构信息
    	/**获取登录者所拥有所有机构end **/
    	//循环判断登录者所拥有的角色信息和该配置用户的角色信息
    		if(listrole!=null && listrole.size()>0){
    			for(OaRole role:listrole){
    				rtree= new Ztree();
    				//判断该配置用户是否已经拥有角色
    				    //是，则将该配置用户所拥有的角色信息选中显示
    				if(listconRole!=null && listconRole.size()>0){
        					if(conRoleIds.contains(role.getNodeId())){
        						rtree.setId(role.getNodeId());
                				rtree.setpId(role.getParentId());
                				rtree.setName(role.getRoleName());
                				rtree.setIsParent(role.getIsParent());
                				rtree.setOpen("true");
                				rtree.setChecked("true");
        					}else{
        						rtree.setId(role.getNodeId());
                				rtree.setpId(role.getParentId());
                				rtree.setName(role.getRoleName());
                				rtree.setIsParent(role.getIsParent());
                				rtree.setOpen("true");
        					}
        				
    				}else{
    					rtree.setId(role.getNodeId());
        				rtree.setpId(role.getParentId());
        				rtree.setName(role.getRoleName());
        				rtree.setIsParent(role.getIsParent());
        				rtree.setOpen("true");
    				}
    				roletree.add(rtree);
    				}
    			}
    		map.put("roletree", roletree);
    		if(listOrg!=null && listOrg.size()>0){
    			//循环判断登录者的管辖机构和配置者的管辖机构信息
    			for(OaOrg org:listOrg){
    				otree= new Ztree();
    				//判断该配置用户是否已经拥有管辖机构
    				     //有，则对所拥有的机构选中显示
    				if(listconOrg!=null && listconOrg.size()>0){
        					if(conOrgIds.size()>0 && conOrgIds.contains(org.getNodeId())){
        						otree.setId(org.getNodeId());
                				otree.setpId(org.getParentId());
                				otree.setName(org.getOrgName());
                				otree.setChecked("true");
                				otree.setOpen("true");
                				otree.setIsParent(org.getIsParent());
        					}else{
        						otree.setId(org.getNodeId());
        	    				otree.setpId(org.getParentId());
        	    				otree.setName(org.getOrgName());
        	    				otree.setOpen("true");
        	    				otree.setIsParent(org.getIsParent());
        					}
    				}else{
    					otree.setId(org.getNodeId());
        				otree.setpId(org.getParentId());
        				otree.setName(org.getOrgName());
        				otree.setOpen("true");
        				otree.setIsParent(org.getIsParent());
    				}
    				orgtree.add(otree);
    			}
    		}
        	 map.put("orgtree", orgtree);
	     return map;
   }
 
    /**
     * 配置用户角色和机构信息
     */
    @RequestMapping(value="/configureUser")
    @ResponseBody
    public Object configureUserRole(Integer id,String orgid,String roleid){
    	JsonBean jsonBean=null;
    	List<OaRole> listrole=null;//更新角色信息
    	List<OaOrg> listorg=null;//更新机构信息
    	SysUser user = null;
    	try{
        	user=sysUserService.getFindSysUser(id);//根据id查询出该用户信息
    		if("".equals(roleid)){
    			listrole=null;
    		}else{
    			listrole=sysUserService.findSysUserRole(roleid);//根据用户的角色id查询出角色信息
    		}
        	if("".equals(orgid)){
        		user.setOrgIds(orgid);
        		listorg=null;
    		}else{
    			user.setOrgIds(orgid);
    			listorg=sysUserService.findSysUserOrg(orgid);//根据用户的机构id查询出机构信息
    		}
	  	    sysUserService.configureUserService(user,listrole,listorg);//配置用户的角色和机构信息
	  	    jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","sysUserBox","closeCurrent","","");
  	    }catch (Exception e){
  	    	    jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","sysUserBox","","","");
  	    }
		return jsonBean;
    }
    /**
     * 查询该用户的配置信息
     * @param request
     * @param id
     * @param parentId
     * @return
     */
     @RequestMapping(value="/findSysUserConfigure")
     @ResponseBody
     public ModelAndView findSysUserConfigure(Integer id,HttpServletRequest request){
    	 ModelAndView mv=new ModelAndView();
    	 HttpSession session = request.getSession();
    	 SysUser sysuser=  (SysUser) session.getAttribute(OACommon.LOGIN_USER);//拿到该登录者的信息
    	 if(sysuser.getNodeId().equals(OACommon.SYSCREATOR)){
    		  mv=new ModelAndView("/sysuserpage/creatorconfigure");
    	 }else{
    	     mv=new ModelAndView("/sysuserpage/sysuserconfigure");
    	 }
    	 mv.addObject("id", id);
		 return mv;
     }
    /**
     * 删除管理员
     * @param ids
     * @return
     */
    @RequestMapping(value="/delSysUser")
    @ResponseBody
    public Object delSysUser(String ids,String userName){
    	JsonBean jsonBean = null;
    	if(sysUserService.delSysUserService(ids,userName)){
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","sysUserBox","","","");
    	}else{
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","sysUserBox","","","");
    	}
    	return jsonBean;
    }
    /**
     * 进入详情页面
     * @param id
     * @return
     */
    @RequestMapping(value="/showIntoSysUser")
    public ModelAndView showIntoSysUser(Integer id){
    	ModelAndView mav = new ModelAndView("sysuserpage/sysusershow");
    	SysUser sysuser = sysUserService.getFindSysUser(id);
    	mav.addObject("userinfo", sysuser);
    	return mav;
    }
    
    /**
     * 分页获取管理员
     * @param numPerPage
     * @param pageNum
     * @param page
     * @param sysUser
     * @return
     */
    @RequestMapping("/findSysUserPage")
    public ModelAndView findSysUserPage(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<SysUser> page,SysUser sysUser,String startTime,String endTime,String orderField,String orderDirection){
     	String orgId = request.getParameter("orgId");
     	ModelAndView mod = null;
     	if(orgId != null){
     		SysUser suser = (SysUser) request.getSession().getAttribute(OACommon.LOGIN_USER);
        	mod = new ModelAndView("sysuserpage/sysuserlist");
        	LinkedHashMap<String,String> orderby  = null;
        	if(numPerPage == null || numPerPage ==-1){
        		page = new Page(10);
        	}else{
        		page.setPageSize(numPerPage); 
     		    page.setPageNo(pageNum);
        	}
        	if(orderField != null && !"".equals(orderField) && orderDirection != null && !"".equals(orderDirection)){
 		    	orderby = new LinkedHashMap<String,String>();
 	 		    orderby.put(orderField, orderDirection);
 		    }else{
 		    	orderby = new LinkedHashMap<String,String>();
 		    	orderField = "user_createTime";
 		    	orderDirection="desc";
 		    	orderby.put(orderField, orderDirection);
 		    }
        	page = sysUserService.findSysUserPage(page, sysUser, orderby, orgId,startTime,endTime);
        	mod.addObject("page", page);
        	mod.addObject("orgId", orgId);
        	mod.addObject("orderField", orderField);
        	mod.addObject("orderDirection", orderDirection);
        	mod.addObject("startTime",startTime);
        	mod.addObject("endTime",endTime);
        	mod.addObject("sysUser", sysUser);
     	}else{
     		mod = new ModelAndView("sysuserpage/sysusermanage");
     	}
    	return mod;
    }
    /**
     * 验证登录账号唯一
     * @param userName
     * @return
     */
    @RequestMapping(value="/verifyUserName",method=RequestMethod.POST)
    @ResponseBody
    public Object verifyUserName(String userName){
    	Map<String,String> mapInfo = new HashMap<String,String>();
    	SysUser sysuser = new SysUser();
    	sysuser.setUserName(userName);
    	List<SysUser> list= sysUserService.valSysUserCondition(sysuser);
    	if(list.size()>0){
    		mapInfo.put("result", "false");
    		return mapInfo;
    	}else{
    		mapInfo.put("result", "true");
    		return mapInfo;
    	}
    }
}
