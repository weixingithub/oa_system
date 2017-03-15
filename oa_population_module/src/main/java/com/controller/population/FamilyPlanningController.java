package com.controller.population;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.oa_bean.Page;
import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;
import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.Person;
import org.oa_bean.population.VehicleInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AreaService;
import com.dao.population.FamilyPlanningService;
import com.dao.population.PersonService;
import com.tool.ExcelUtil;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

/**
 * 计划生育管理信息
 * @author Administrator
 * @date 2016年7月16日
 *
 */
@Controller
@RequestMapping("/oa/planning")
public class FamilyPlanningController {
	private PersonService personService;
	private FamilyPlanningService familyPlanningService;
	private AreaService areaService;

	
	 
	public FamilyPlanningService getFamilyPlanningService() {
		return familyPlanningService;
	}
	@Autowired
	public void setFamilyPlanningService(FamilyPlanningService familyPlanningService) {
		this.familyPlanningService = familyPlanningService;
	}

	public PersonService getPersonService() {
		return personService;
	}
	
	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	public AreaService getAreaService() {
		return areaService;
	}
	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
	/**
	 * 分页显示人口信息
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page 分页实体
	 * @param person 人口实体
	 * @param startTime
	 * @param endTime
	 * @param orderField
	 * @param orderDirection
	 * @param plan 计生实体
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getPersonPage")
	public ModelAndView getPersonPage(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Person> page,Person person,String startTime,String endTime,
    		String orderField,String orderDirection,FamilyPlanning plan){
		ModelAndView mav=new ModelAndView("/person/planning_list"); 
		LinkedHashMap<String,String> orderby  = null;
		person.setFamilyPlanning(plan);
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
		 personService.getConditionPersonPage(page, person, orderby, startTime, endTime,null,plan,null,null,null);
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
        * 根据id查询出人口信息
        * @param request
        * @param id
        * @return
        */
       @RequestMapping("/findPersonById")
       @ResponseBody
       public ModelAndView findPersonById(HttpServletRequest request,Integer id){
       	ModelAndView mv=new ModelAndView("/person/familyplanning");
           List<VehicleInformation> listv=personService.findPersonVehicle(id);//人口车辆信息
       	List<Province> plist = areaService.findProvinces();
       	if(id != null){
           	Person persons = personService.findPersonById(id);
           	String proid = "";
           	String cityid = "";
           	String disid = "";
           	List<City> clist = null;
           	List<District> dlist = null;
           	if(persons.getPerPCD() != null && !"".equals(persons.getPerPCD())){
           		 proid = persons.getPerPCD().split(",")[0];
               	 cityid =persons.getPerPCD().split(",")[1];
               	 disid =persons.getPerPCD().split(",")[2];
               	 clist = areaService.findCityByPId(Integer.parseInt(proid));
               	 dlist = areaService.findDistrictByCId(Integer.parseInt(cityid));
           	}
           	mv.addObject("persons", persons);
           	
           	mv.addObject("proid", proid);
           	mv.addObject("cityid", cityid);
           	mv.addObject("disid", disid);
           	
           	
           	mv.addObject("plist", plist);
           	mv.addObject("clist", clist);
           	mv.addObject("dlist", dlist);
       	}else{
       		mv = new ModelAndView("person/planning_list");
       		mv.addObject("plist", plist);
       	}
       	Person person = personService.findPersonById(id);
       	mv.addObject("person", person);
       	mv.addObject("listv", listv);
   		return mv;
       }
       /**
        * 编辑人口计生信息
        * @param person
        * @return
        */
       @RequestMapping("/updateFamilyPlanning")
       @ResponseBody
       public Object updateFamilyPlanning(FamilyPlanning familyPlanning){
       	JsonBean jsonBean=null;
       	if(familyPlanningService.updateFamilyPlanningService(familyPlanning)){
       		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/planning/getPersonPage"),"","","","");
       	}else{
       		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
       	}
   		return jsonBean;
       }
       /**
   	 * 高级检索跳转页面
   	 */
   	@RequestMapping("/findByIdSearch")
   	public String findByIdSearch(){
   		return "person/planning_search";
   	}
   	
   	/**
     * 解析计生excel数据并且保存至数据库
     */
	@RequestMapping("/familyPlanningExcelImport")
	@ResponseBody
	public Object familyPlanningExcelImport(String filePath,HttpSession session){
		filePath=session.getServletContext().getRealPath("/")+filePath;
		filePath=filePath.replace("\\", "/");
    	JsonBean jsonBean=null;
    	List<Person> list = new ExcelUtil().excelByFamilyPlanning(filePath);
    	if(list==null){
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}else{
    		if( familyPlanningService.updateFamilyPlanning(list)){
        		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/planning/getPersonPage"),"","closeCurrent","","");
        	}else{
        		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
        	}
    	}
       return jsonBean;
}
}
