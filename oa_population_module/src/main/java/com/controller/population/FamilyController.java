package com.controller.population;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import org.oa_bean.Page;
import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;
import org.oa_bean.cabinet.FileCabinet;
import org.oa_bean.cabinet.FileMessage;
import org.oa_bean.population.Family;
import org.oa_bean.population.Ground;
import org.oa_bean.population.House;
import org.oa_bean.population.Person;
import org.oa_common.date.DateTools;
import org.oa_common.xml.XmlSwitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AreaService;
import com.dao.population.FamilyService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/oa/population")
public class FamilyController {
	private AreaService areaService;
	private FamilyService familyService;
	@Autowired
	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
	/**
	 * 条件分页
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param family
	 * @param startTime
	 * @param endTime
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping("/findFamilyPage")
	@ResponseBody
	public ModelAndView findFamilyPage(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Family> page,Family family,String startTime,String endTime,
    		String orderField,String orderDirection){
		ModelAndView mav = new ModelAndView("population/family_list");
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
	    	orderField = "createTime";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
		 }
		 page = familyService.getConditionFamilyPage(page, family, orderby, startTime, endTime);
		 mav.addObject("page", page);
		 mav.addObject("orderField",orderField);
		 mav.addObject("orderDirection",orderDirection);
		 mav.addObject("startTime",startTime);
		 mav.addObject("endTime",endTime);
		 mav.addObject("family",family);
		return mav;
	}
	
	/**
	 * 添加和更新户籍基本信息
	 * @param family
	 * @return
	 */
	@RequestMapping("/addAndUpdateFamily")
	@ResponseBody
	public Object addAndUpdateFamily(HttpServletRequest request,Family family,String personId,
			String personOldId,String personName,
			String personContact, String houseId,String groundId,String fileCabinetId,
			String province, String city,String region,String provinces,String citys,String regions ){
		 JsonBean  jsonbean = null;
		 String operationType = "";
		 family.setHouseholderName(personName);
		 family.setHouseholderContact(personContact);
		 if( family.getId()==null || "".equals(family.getId())){
			 operationType = "add";
			 String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			 family.setCreateTime(createTime);
			 family.setHouse(new House());
			 family.setGround(new Ground());
			 family.setFileCabinet(new FileCabinet());
			 
		 }else{
			 FileCabinet fileCabinet= new FileCabinet(); 
			 fileCabinet.setId(Integer.parseInt(fileCabinetId));
			 Ground ground =  new Ground();
			 ground.setId(Integer.parseInt(groundId));
			 House house =new House();
			 house.setId(Integer.parseInt(houseId));
			 family.setHouse(house);
			 family.setGround(ground);
			 family.setFileCabinet(fileCabinet);
		 }
	    	 if(!"0".equals(province)){
	    		family.setFamPCD(province+","+city+","+region);
	    	 }else{
	    		 family.setFamPCD("");
	    	 }
	    	 if(!"0".equals(provinces)){
	 			family.setPenPCD(provinces+","+citys+","+regions);
	 		}else{
	 			family.setPenPCD("");
	 		}
		 if(familyService.addAndUpdateFamilyService(family, operationType, personId, personOldId)){
			   jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/population/findFamilyPage"),"","closeCurrent","","");
		    }else{
		       jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		 return jsonbean;
	}
	/**
	 * 更新户籍的房屋信息
	 * @param house
	 * @return
	 */
	@RequestMapping("/updateFamilyByHouse")
	@ResponseBody
	public Object updateFamilyByHouse(House house){
		 JsonBean  jsonbean = null;
		 if(familyService.updateFamilyByHouseService(house)){
			   jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/population/findFamilyPage"),"","","","");
		    }else{
		       jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		 return jsonbean;
	}
	
	/**
	 * 更新户籍的土地信息
	 * @param house
	 * @return
	 */
	@RequestMapping("/updateFamilyByGround")
	@ResponseBody
	public Object updateFamilyByGround(Ground ground){
		 JsonBean  jsonbean = null;
		 if(familyService.updateFamilyByGroundService(ground)){
			   jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/population/findFamilyPage"),"","","","");
		    }else{
		       jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		 return jsonbean;
	}
	/**
	 * 更新家庭成员
	 * @param request
	 * @param familyId
	 * @param familymemberjson
	 * @return
	 */
	@RequestMapping("/updateFamilyByPerson")
	@ResponseBody
	public Object updateFamilyByPerson(HttpServletRequest request,String familyId,String familymemberjson){
		JsonBean  jsonbean = null;
		try {
			 Set<Person> set = new HashSet<Person>();
			 if(!"".equals(familymemberjson)&&familymemberjson!=null){
				 JSONArray filejsonarr = JSONArray.fromObject(familymemberjson);
				 for(int i =0;i<filejsonarr.size();i++){
					 Person  person =new Person();
					 JSONObject jsonObject = (JSONObject)filejsonarr.get(i);
					 person.setId(jsonObject.getInt("personId"));
					 person.setName(jsonObject.getString("personName"));
					 person.setRalation(jsonObject.getString("ralation"));
					 person.setIdnumber(jsonObject.getString("personIdnumber"));
					 person.setContact(jsonObject.getString("personContact"));
					 set.add(person);
				 }
			 }
			 if(familyService.updateFamilyByPerson(familyId, set)){
				 jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/population/findFamilyPage"),"","","","");
			 }else{
				 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			 }
		} catch (Exception e) {
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		return jsonbean;
	}
	/**
	 * 进入添加户籍页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/intoAddFamily")
	public ModelAndView intoAddFamily(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("population/family_add");
		//获取集合
		List<Province> plist = areaService.findProvinces();
		mav.addObject("plist", plist);
		Family family = new Family();
		mav.addObject("family",family);
		return mav;
	}
	/**
	 * 进入编辑户籍页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/intoUpdateFamily")
	public ModelAndView intoUdateFamily(HttpServletRequest request,String id){
		ModelAndView mav = new ModelAndView("population/family_add");
		List<Province> plist = areaService.findProvinces();
		Family fam = familyService.findFamilyById(id);
    	if(id != null){
        	String proid = "";
        	String cityid = "";
        	String disid = "";
        	String proids = "";
        	String cityids = "";
        	String disids = "";
        	List<City> clist = null;
        	List<District> dlist = null;
        	List<City> clists = null;
        	List<District> dlists = null;
        	if(fam.getFamPCD() != null && !"".equals(fam.getFamPCD())){
        		 proid = fam.getFamPCD().split(",")[0];
            	 cityid = fam.getFamPCD().split(",")[1];
            	 disid =  fam.getFamPCD().split(",")[2];
            	 clist = areaService.findCityByPId(Integer.parseInt(proid));
            	 dlist = areaService.findDistrictByCId(Integer.parseInt(cityid));
        	}
        	if(fam.getPenPCD() != null && !"".equals(fam.getPenPCD())){
           		 proids = fam.getPenPCD().split(",")[0];
               	 cityids = fam.getPenPCD().split(",")[1];
               	 disids =  fam.getPenPCD().split(",")[2];
               	 clists = areaService.findCityByPId(Integer.parseInt(proids));
               	 dlists = areaService.findDistrictByCId(Integer.parseInt(cityids));
        	 }
        	mav.addObject("proid", proid);
        	mav.addObject("cityid", cityid);
        	mav.addObject("disid", disid);
        	
        	mav.addObject("proids", proids);
        	mav.addObject("cityids", cityids);
        	mav.addObject("disids", disids);
        	
        	mav.addObject("plist", plist);
        	mav.addObject("clist", clist);
        	mav.addObject("dlist", dlist);
        	
        	mav.addObject("clists", clists);
        	mav.addObject("dlists", dlists);
    	}else{
    		mav = new ModelAndView("population/family_list");
    		mav.addObject("plist", plist);
    	}
		List<Person> list = fam.getPerson();
		Collections.sort(list, new Comparator<Person>(){
            public int compare(Person person,Person person1){
                double d1=Double.parseDouble(person.getRalation());
                double d2=Double.parseDouble(person1.getRalation());
                if(d2<d1){
                    return 1;
                }else if (d2>d1){
                    return -1;
                }
                return 0;
            }
        });
		FileCabinet fileCabinet = fam.getFileCabinet();
		String xml = fileCabinet.getFileXml();
		if(xml!=null && !"".equals(xml)){
			FileCabinet xmlfileCabinet = new FileCabinet();
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("fileCabinet", FileCabinet.class);
			classMap.put("fileMessage", FileMessage.class);
			xmlfileCabinet = (FileCabinet) new XmlSwitch().XmlToObject(xml, classMap);
			fileCabinet.setListFile(xmlfileCabinet.getListFile());
		}
		fam.setFileCabinet(fileCabinet);
		fam.setPerson(list);
		mav.addObject("family",fam);
		mav.addObject("hPerson",list.get(0));
		return mav;
	}
	/**
	 * 删除操作
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteFamily")
	@ResponseBody
	public Object deleteFamily(String ids){
		 JsonBean  jsonbean = null;
		 if(familyService.deleteFamilyService(ids)){
			   jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/population/findFamilyPage"),"","","","");
		    }else{
		       jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		 return jsonbean;
	}
	/**
	 * 验证户籍是否存在
	 * @param householderID
	 * @return
	 */
	@RequestMapping("/verifyHouseholderID")
	@ResponseBody
	public Object verifyHouseholderID(String householderID,String orgId,String id,String oldhouseholderID){
		boolean falg = false;
		if(id==null || "".equals(id)){ //添加判断
			List<Family> familyList = familyService.findFamilyByHouseholderID(householderID,orgId);
			if(familyList.size()>0){
				falg =false;
			}else{
				falg =true;
			}
		}else{
			if(householderID.equals(oldhouseholderID)){
				falg =true;
			}else{
				List<Family> familyList = familyService.findFamilyByHouseholderID(householderID,orgId);
				if(familyList.size()>0){
					falg =false;
				}else{
					falg =true;
				}
			}
		}
		return falg;
	}
	
	/**
	 * 高级检索跳转页面
	 */
	@RequestMapping("/findByIdSearch")
	public String findByIdSearch(){
		return "population/family_search";
	}
	/**
	 * 户籍详情跳转页面 
	 * @return
	 */
	@RequestMapping("/familyDetails")
	public ModelAndView familyDetails(HttpServletRequest request,String id){
		ModelAndView mav = new ModelAndView("population/family_details");
		Family family = familyService.findFamilyById(id);
		Province provice=new Province();
		City city=new City();
		District dis=new District();
		Province provices=new Province();
		City citys=new City();
		District diss=new District();
		String proid = "";
    	String cityid = "";
    	String disid = "";
    	String proids = "";
    	String cityids = "";
    	String disids = "";
		if(family.getFamPCD()!= null && !"".equals(family.getFamPCD())){
   		 proid = family.getFamPCD().split(",")[0];
       	 cityid = family.getFamPCD().split(",")[1];
       	 disid =  family.getFamPCD().split(",")[2];
       	 provice=areaService.findByProID(Integer.parseInt(proid));//根据Id查询省
       	 city=areaService.findBycityID(Integer.parseInt(cityid));//根据Id查询市
       	 dis=areaService.findById(Integer.parseInt(disid));//根据Id查询区域
		}
		if(family.getPenPCD()!= null && !"".equals(family.getPenPCD())){
   		 proids = family.getPenPCD().split(",")[0];
       	 cityids = family.getPenPCD().split(",")[1];
       	 disids =  family.getPenPCD().split(",")[2];
       	 provices=areaService.findByProID(Integer.parseInt(proids));//根据Id查询省
       	 citys=areaService.findBycityID(Integer.parseInt(cityids));//根据Id查询市
       	 diss=areaService.findById(Integer.parseInt(disids));//根据Id查询区域
		}
		List<Person> list = family.getPerson();
		Collections.sort(list, new Comparator<Person>(){
            public int compare(Person person,Person person1){
                double d1=Double.parseDouble(person.getRalation());
                double d2=Double.parseDouble(person1.getRalation());
                if(d2<d1){
                    return 1;
                }else if (d2>d1){
                    return -1;
                }
                return 0;
            }
        });
		FileCabinet fileCabinet = family.getFileCabinet();
		String xml = fileCabinet.getFileXml();
		if(xml!=null && !"".equals(xml)){
			FileCabinet xmlfileCabinet = new FileCabinet();
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("fileCabinet", FileCabinet.class);
			classMap.put("fileMessage", FileMessage.class);
			xmlfileCabinet = (FileCabinet) new XmlSwitch().XmlToObject(xml, classMap);
			fileCabinet.setListFile(xmlfileCabinet.getListFile());
		}
		family.setFileCabinet(fileCabinet);
		family.setPerson(list);
		mav.addObject("family",family);
		mav.addObject("provice", provice);
		mav.addObject("city", city);
		mav.addObject("dis", dis);
		mav.addObject("provices", provices);
		mav.addObject("citys", citys);
		mav.addObject("diss", diss);
		return mav;
	}
}
