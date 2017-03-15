package com.controller.population;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.oa_bean.Page;
import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;
import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AreaService;
import com.dao.population.LaborInsuranceService;
import com.dao.population.PersonService;
import com.tool.ExcelUtil;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/oa/labour")
public class LaborController {

	private PersonService personService;
	private AreaService areaService;
	private LaborInsuranceService laborService;
	
	
	
	public LaborInsuranceService getLaborService() {
		return laborService;
	}
	@Autowired
	public void setLaborService(LaborInsuranceService laborService) {
		this.laborService = laborService;
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
	 * @param labour 劳保实体
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getPersonPage")
	public ModelAndView getPersonPage(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Person> page,Person person,String startTime,String endTime,
    		String orderField,String orderDirection,LaborInsurance labour){
		ModelAndView mav=new ModelAndView("/person/labour_list"); 
		LinkedHashMap<String,String> orderby  = null;
		person.setLaborInsurance(labour);
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
		 personService.getConditionPersonPage(page, person, orderby, startTime, endTime,null,null,labour,null,null);
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
    	ModelAndView mv=new ModelAndView("/person/labour_update");
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
    		mv = new ModelAndView("person/labour_list");
    		mv.addObject("plist", plist);
    	}
    	Person person = personService.findPersonById(id);
    	mv.addObject("person", person);
		return mv;
    }
   	/**
     * 编辑人口劳保信息
     * @param person
     * @return
     */
    @RequestMapping("/updateLaborInsurance")
    @ResponseBody
    public Object updateLaborInsurance(LaborInsurance labor){
    	JsonBean jsonBean=null;
    	if(laborService.updateLaborInsuranceService(labor)){
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/labour/getPersonPage"),"","","","");
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
		return "person/labor_search";
	}
	
 	/**
     * 解析劳保excel数据并且保存至数据库
     */
	@RequestMapping("/labourExcelImport")
	@ResponseBody
	public Object labourExcelImport(String filePath,HttpSession session){
		filePath=session.getServletContext().getRealPath("/")+filePath;
		filePath=filePath.replace("\\", "/");
    	JsonBean jsonBean=null;
    	List<Person> list = new ExcelUtil().excelByLabour(filePath);
    	if(list==null){
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}else{
    		if( laborService.updateLaborInsurance(list)){
        		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/planning/getPersonPage"),"","closeCurrent","","");
        	}else{
        		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
        	}
    	}
       return jsonBean;
}
	
}
