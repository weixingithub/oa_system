package com.controller.population;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.oa_bean.Page;
import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;
import org.oa_bean.cabinet.FileCabinet;
import org.oa_bean.cabinet.FileMessage;
import org.oa_bean.population.Family;
import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
import org.oa_bean.population.Stability;
import org.oa_bean.population.VehicleInformation;
import org.oa_common.date.DateTools;
import org.oa_common.xml.XmlSwitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AreaService;
import com.dao.population.FamilyService;
import com.dao.population.PersonService;
import com.tool.ExcelUtil;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

/**
 * 人口信息
 * @author Administrator
 * @date 2016年6月28日
 * @company
 * PersonController.java
 */
@Controller
@RequestMapping("/oa/person")
public class PersonController {
	 private PersonService personService;
	 private AreaService areaService;
	 private FamilyService familyService;
    @Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
	@Autowired
	public void setFamilyService(FamilyService familyService) {
		this.familyService = familyService;
	}
	/**
	 * 人口信息分页列表显示
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param person
	 * @param startTime
	 * @param endTime
	 * @param orderField
	 * @param orderDirection
	 * @param idnumbers
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getPersonPage")
	public ModelAndView getPersonPage(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Person> page,Person person,String startTime,String endTime,String orderField,String orderDirection,String idnumbers){
    	
    	ModelAndView mav=new ModelAndView("/person/getpersonpage"); 
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
	    	orderField = "creatime";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
		}
		
		 page = personService.getConditionPersonPage(page, person, orderby, startTime, endTime,null,null,null,null,null);
		 mav.addObject("page", page);
		 mav.addObject("orderField",orderField);
		 mav.addObject("orderDirection",orderDirection);
		 mav.addObject("startTime",startTime);
		 mav.addObject("endTime",endTime);
		 mav.addObject("person",person);
		 mav.addObject("page", page);
		 return mav;
    }
    /**
     * 进入人口信息添加页面
     * @return
     */
    @RequestMapping("/personAdd")
    public ModelAndView personAdd(HttpServletRequest request){
    	HttpSession session=request.getSession();
		ModelAndView mv=new ModelAndView("/person/person_add");
		//获取省市区三级联动集合
		List<Province> plist = areaService.findProvinces();
		mv.addObject("plist", plist);
		Person person=new Person();
		mv.addObject("person", person);
		return mv;
    }
    /**
     * 添加或更新人口信息
     * @return
     */
    @RequestMapping("/personAddOrUpdate")
    @ResponseBody
    public Object personAddOrUpdate(HttpServletRequest request,Person person,Integer familyId,Integer populationCiviId,Integer stabilityId,Integer laborInsuranceId,Integer familyPlanningId,Integer fileCabinetId, String province,String city,String region){
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
    	JsonBean jsonBean=null;
    	String opertionType ="";
    	//添加
    	if("".equals(person.getId()) || null==person.getId()){
    		opertionType ="add";
    		person.setFamilyPlanning(new FamilyPlanning());//计生信息
			person.setPopulationCivil(new PopulationCivil());//民政信息
			person.setLaborInsurance(new LaborInsurance());//劳保信息
			person.setStability(new Stability());//综治信息
			person.setFileCabinet(new FileCabinet());
        	person.setCreatime(createTime);
    	}else{//更新
    		if(familyId!=null){
    			Family family =new Family();
        		family.setId(familyId);
    			person.setFamily(family);//户籍信息
    		}
    		Stability stability=new Stability();
    		stability.setId(stabilityId);
    		PopulationCivil pc=new PopulationCivil();
    		pc.setId(populationCiviId);
    		FamilyPlanning plan=new FamilyPlanning();
    		plan.setId(familyPlanningId);
    		LaborInsurance labor=new LaborInsurance();
    		labor.setId(laborInsuranceId);
    		FileCabinet fileCabinet = new FileCabinet();
    		fileCabinet.setId(fileCabinetId);
    		person.setPopulationCivil(pc);//民政信息
    		person.setFamilyPlanning(plan);//计生信息
    		person.setLaborInsurance(labor);//劳保信息
        	person.setStability(stability);//综治信息
        	person.setFileCabinet(fileCabinet);//人口文件信息
    	  }
    	 if(!"0".equals(province) ){
    		person.setPerPCD(province+","+city+","+region);
    	 }else{
    		person.setPerPCD("");
    	 }
    	if(personService.addAndUpdatePersonService(person, opertionType,person.getId())){
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/person/getPersonPage"),"","closeCurrent","","");
    	}else{
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}
    	return jsonBean;
    }
    /**
	 * 验证人口信息的身份证号是否存在
	 * @param householderID
	 * @return
	 */
	@RequestMapping("/verifyIdnumber")
	@ResponseBody
	public Object verifyIdnumber(String idnumber,String orgId,String id,String oldidnumber){
		boolean falg = false;
		if(id==null || "".equals(id)){ //添加判断
			List<Person> personList = personService.findPersonByIdnumber(idnumber,null,null);
			if(personList.size()>0){
				falg =false;
			}else{
				falg =true;
			}
		}else{
			if(idnumber.equals(oldidnumber)){
				falg =true;
			}else{
				List<Person> personList = personService.findPersonByIdnumber(idnumber,null,null);
				if(personList.size()>0){
					falg =false;
				}else{
					falg =true;
				}
			}
		}
		return falg;
	}
	
     /**
     * 根据进入编辑页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/findPersonById")
    @ResponseBody
    public ModelAndView findPersonById(HttpServletRequest request,Integer id){
    	ModelAndView mv=new ModelAndView("/person/person_add");
        List<VehicleInformation> listv=personService.findPersonVehicle(id);//人口车辆信息
    	List<Province> plist = areaService.findProvinces();
    	Person person = personService.findPersonById(id);
    	if(id != null){
        	String proid = "";
        	String cityid = "";
        	String disid = "";
        	List<City> clist = null;
        	List<District> dlist = null;
        	if(person.getPerPCD() != null && !"".equals(person.getPerPCD())){
        		 proid = person.getPerPCD().split(",")[0];
            	 cityid =person.getPerPCD().split(",")[1];
            	 disid =person.getPerPCD().split(",")[2];
            	 clist = areaService.findCityByPId(Integer.parseInt(proid));
            	 dlist = areaService.findDistrictByCId(Integer.parseInt(cityid));
        	}
        	mv.addObject("persons", person);
        	
        	mv.addObject("proid", proid);
        	mv.addObject("cityid", cityid);
        	mv.addObject("disid", disid);
        	
        	
        	mv.addObject("plist", plist);
        	mv.addObject("clist", clist);
        	mv.addObject("dlist", dlist);
    	}else{
    		mv = new ModelAndView("person/getpersonpage");
    		mv.addObject("plist", plist);
    	}
    	
    	FileCabinet fileCabinet = person.getFileCabinet();
    	String xml = fileCabinet.getFileXml();
    	if(xml!=null && !"".equals(xml)){
    		FileCabinet xmlfileCabinet = new FileCabinet();
    		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
    		classMap.put("fileCabinet", FileCabinet.class);
			classMap.put("fileMessage", FileMessage.class);
			xmlfileCabinet = (FileCabinet) new XmlSwitch().XmlToObject(xml, classMap);
			fileCabinet.setListFile(xmlfileCabinet.getListFile());
    	}
    	person.setFileCabinet(fileCabinet);
    	mv.addObject("person", person);
    	mv.addObject("listv", listv);
		return mv;
    }
    /**
     * 删除人口信息以及关联的所有信息
     * @param id
     * @return
     */
    @RequestMapping("/delPerson")
    @ResponseBody
    public Object delPerson(String ids,String idnumber){
    	JsonBean jsonBean=null;
    		if(personService.delPersonService(ids,idnumber)){
    			jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/person/getPersonPage"),"","","","");
    		}else{
    			jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    		}
		return jsonBean;
    }
    /**
	 * 验证人口车辆信息的车牌号是否存在
	 * @param householderID
	 * @return
	 */
	@RequestMapping("/verifyLicenseNumber")
	@ResponseBody
	public Object verifyLicenseNumber(String licenseplatenumber,String id,String oldlicensenumber){
		boolean falg = false;
		if(id==null || "".equals(id)){ //添加判断
			List<VehicleInformation> vehicleList = personService.findVehicleByLicenseNumber(licenseplatenumber);
			if(vehicleList.size()>0){
				falg =false;
			}else{
				falg =true;
			}
		}else{
			if(licenseplatenumber.equals(oldlicensenumber)){
				falg =true;
			}else{
				List<VehicleInformation> vehicleList = personService.findVehicleByLicenseNumber(licenseplatenumber);
				if(vehicleList.size()>0){
					falg =false;
				}else{
					falg =true;
				}
			}
		}
		return falg;
	}
    /**
     * 进入人口车辆信息添加页面
     * @return
     */
    @RequestMapping("/vehicleAdd")
    public ModelAndView vehicleAdd(HttpServletRequest request,Integer id){
		ModelAndView mv=new ModelAndView("/person/vehicle_add");
		VehicleInformation vehicle=new VehicleInformation();
		vehicle.setPerson_id(id);
		mv.addObject("vehicle", vehicle);
		return mv;
    }
    /**
   	 * 模式窗口人口查询
     * @param request
     * @param numPerPage
     * @param pageNum
     * @param page
     * @param person
     * @param startTime
     * @param endTime
     * @param orderField
     * @param orderDirection
     * @param conditioned 特殊条件
     * @return
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getFamilyPersonPage")
	public ModelAndView getNotFamilyPersonPage(HttpServletRequest request,
			Integer numPerPage, Integer pageNum, Page<Person> page,
			Person person, String startTime, String endTime, String orderField, String orderDirection,String conditioned) {
		
		ModelAndView mav = new ModelAndView("/person/person_notfamily");
		LinkedHashMap<String, String> orderby = null;
		if (numPerPage == null || numPerPage == -1) {
			page = new Page(10);
		} else {
			page.setPageSize(numPerPage);
			page.setPageNo(pageNum);
		}
		if (orderField != null && !"".equals(orderField)
				&& orderDirection != null && !"".equals(orderDirection)) {
			orderby = new LinkedHashMap<String, String>();
			orderby.put(orderField, orderDirection);
		} else {
			orderby = new LinkedHashMap<String, String>();
			orderField = "creatime";
			orderDirection = "desc";
			orderby.put(orderField, orderDirection);
		}
		 personService.getConditionPersonPage(page, person, orderby, startTime, endTime,null,null,null,null,conditioned);
		mav.addObject("page", page);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		mav.addObject("person", person);
		mav.addObject("page", page);
		mav.addObject("conditioned", conditioned);
		return mav;
	}
	/**
	 * 发送短信给用户
	 * 接收要发送人的手机号
	 * @return
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("/personExcel")
	@ResponseBody
	public Object personExcel(String contact){
		System.out.println("要发送的人的手机号"+contact);
		if("".equals(contact) || contact==null){
			List contactlist=personService.findPersonContact();//查询所有人的手机号码
		}
		JsonBean jsonbean=null;
		return jsonbean;
	}
	
	
	 /**
     * 根据id查询人口详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/personDetails")
    @ResponseBody
    public ModelAndView personDetails(HttpServletRequest request,Integer id){
    	ModelAndView mv=new ModelAndView("/person/person_details");
    	Person person = personService.findPersonById(id);
    	if(id != null){
        	Province provice=new Province();
    		City city=new City();
    		District dis=new District();
        	String proid = "";
        	String cityid = "";
        	String disid = "";
        	if(person.getPerPCD() != null && !"".equals(person.getPerPCD())){
        		 proid = person.getPerPCD().split(",")[0];
            	 cityid =person.getPerPCD().split(",")[1];
            	 disid =person.getPerPCD().split(",")[2];
            	 provice=areaService.findByProID(Integer.parseInt(proid));//根据Id查询省
               	 city=areaService.findBycityID(Integer.parseInt(cityid));//根据Id查询市
               	 dis=areaService.findById(Integer.parseInt(disid));//根据Id查询区域
            	 
        	}
        	
        	mv.addObject("proid", proid);
        	mv.addObject("cityid", cityid);
        	mv.addObject("disid", disid);
        	
        	mv.addObject("provice", provice);
        	mv.addObject("city", city);
        	mv.addObject("dis", dis);
    	}
    	
    	mv.addObject("person", person);
		return mv;
    }
    
    
    /**
     * 人口信息详情页面跳转
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/findPersonEditor")
    @ResponseBody
    public ModelAndView findPersonEditor(HttpServletRequest request,Integer id){
    	ModelAndView mv=new ModelAndView("/person/person_editor");
        List<VehicleInformation> listv=personService.findPersonVehicle(id);//人口车辆信息
        Person person = personService.findPersonById(id);
    	
    	Family family = null;
    	if(person.getFamily()!=null){
    		family = familyService.findFamilyById(person.getFamily().getId().toString());//根据户籍Id查询户籍详情
    	}
    	String proid = "";
    	String cityid = "";
    	String disid = "";
    	Province provice=new Province();
		City city=new City();
		District dis=new District();
		//人口详情现居地
    	if(person.getPerPCD() != null && !"".equals(person.getPerPCD())){
    		 proid = person.getPerPCD().split(",")[0];
        	 cityid =person.getPerPCD().split(",")[1];
        	 disid =person.getPerPCD().split(",")[2];
        	 provice=areaService.findByProID(Integer.parseInt(proid));//根据Id查询省
           	 city=areaService.findBycityID(Integer.parseInt(cityid));//根据Id查询市
           	 dis=areaService.findById(Integer.parseInt(disid));//根据Id查询区域
    	}
    	
    	Province provicess=new Province();
		City cityss=new City();
		District disss=new District();
		Province provices=new Province();
		City citys=new City();
		District diss=new District();
		String proidss = "";
    	String cityidss = "";
    	String disidss = "";
    	String proids = "";
    	String cityids = "";
    	String disids = "";
    	if(person.getFamily()!=null){
	    	//户籍详情户籍地
			if(family.getFamPCD()!= null && !"".equals(family.getFamPCD())){
	   		 proidss = family.getFamPCD().split(",")[0];
	       	 cityidss = family.getFamPCD().split(",")[1];
	       	 disidss =  family.getFamPCD().split(",")[2];
	       	 provicess=areaService.findByProID(Integer.parseInt(proidss));//根据Id查询省
	       	 cityss=areaService.findBycityID(Integer.parseInt(cityidss));//根据Id查询市
	       	 disss=areaService.findById(Integer.parseInt(disidss));//根据Id查询区域
			}
			//户籍详情现居地
			if(family.getPenPCD()!= null && !"".equals(family.getPenPCD())){
	   		 proids = family.getPenPCD().split(",")[0];
	       	 cityids = family.getPenPCD().split(",")[1];
	       	 disids =  family.getPenPCD().split(",")[2];
	       	 provices=areaService.findByProID(Integer.parseInt(proids));//根据Id查询省
	       	 citys=areaService.findBycityID(Integer.parseInt(cityids));//根据Id查询市
	       	 diss=areaService.findById(Integer.parseInt(disids));//根据Id查询区域
			}
    	}
    	
    	
    	mv.addObject("proid", proid);
    	mv.addObject("cityid", cityid);
    	mv.addObject("disid", disid);
   
    	mv.addObject("provice", provice);
    	mv.addObject("city", city);
    	mv.addObject("dis", dis);
    	
    	mv.addObject("proids", proids);
    	mv.addObject("cityids", cityids);
    	mv.addObject("disids", disids);
   
    	mv.addObject("provices", provices);
    	mv.addObject("citys", citys);
    	mv.addObject("diss", diss);
    	
    	mv.addObject("proidss", proidss);
    	mv.addObject("cityidss", cityidss);
    	mv.addObject("disidss", disidss);
   
    	mv.addObject("provicess", provicess);
    	mv.addObject("cityss", cityss);
    	mv.addObject("disss", disss);
    	
    	
       	FileCabinet fileCabinet = person.getFileCabinet();
    	String xml = fileCabinet.getFileXml();
    	if(xml!=null && !"".equals(xml)){
    		FileCabinet xmlfileCabinet = new FileCabinet();
    		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("fileCabinet", FileCabinet.class);
			classMap.put("fileMessage", FileMessage.class);
			xmlfileCabinet = (FileCabinet) new XmlSwitch().XmlToObject(xml, classMap);
			fileCabinet.setListFile(xmlfileCabinet.getListFile());
    	}
    	person.setFileCabinet(fileCabinet);
    	mv.addObject("person", person);
    	mv.addObject("family",person.getFamily());
    	mv.addObject("listv", listv);
		return mv;
    }
    /**
     * 解析excel数据并且保存至数据库
     */
	@RequestMapping("/personExcelImport")
	@ResponseBody
	public Object personExcelImport(String filePath,HttpSession session){
		filePath=session.getServletContext().getRealPath("/")+filePath;
		filePath=filePath.replace("\\", "/");
    	JsonBean jsonBean=null;
    	List<Person> list = new ExcelUtil().excelByPerson(filePath);
    	if(list==null){
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}else{
    		if(personService.addAndUpadetPersons(list)){
        		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/person/getPersonPage"),"","closeCurrent","","");
        	}else{
        		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
        	}
    	}
       return jsonBean;
	}
}
