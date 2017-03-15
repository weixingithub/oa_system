package com.controller.population;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;



import org.oa_bean.Page;
import org.oa_bean.population.VehicleInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.population.VehicleService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

/**
 * 车辆信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oa/vehicle")
public class VehicleInformationController {
	 private VehicleService vehicleService;
	 @Autowired
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	/**
     * 分页显示人口车辆信息
     * @param request
     * @param numPerPage
     * @param pageNum
     * @param page
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getPersonVehiclePage")
	public ModelAndView getPersonVehiclePage(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<VehicleInformation> page,VehicleInformation vehicle,String orderField,String orderDirection){
		ModelAndView mav=new ModelAndView("/person/getpersonvehiclepage"); 
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
	    	orderField = "id";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
		}
		vehicleService.getPersonVehiclePage(page, vehicle, orderby);
		 mav.addObject("page", page);
		 mav.addObject("orderField",orderField);
		 mav.addObject("orderDirection",orderDirection);
		 mav.addObject("vehicle",vehicle);
		 mav.addObject("page", page);
		 return mav;
    }
    /**
     * 添加人口车辆信息
     * @return
     */
    @RequestMapping("/addVehicle")
    @ResponseBody
    public Object addVehicle(VehicleInformation vehicle,HttpServletRequest request){
    	String owners=request.getParameter("personName");
    	String contact=request.getParameter("personContact");
    	Integer person_id=Integer.parseInt(request.getParameter("personId"));
    	vehicle.setOwners(owners);
    	vehicle.setPerson_contact(contact);
    	vehicle.setPerson_id(person_id);
    	JsonBean jsonBean=null;
    	//添加
    	if(vehicleService.addVehicleService(vehicle)){
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/vehicle/getPersonVehiclePage"),"","closeCurrent","","");
    	}else{
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}
		return jsonBean;
    }
    /**
     * 删除人口车辆信息（可批量）
     * @param ids
     * @return
     */
    @RequestMapping("/delPersonVehicle")
    @ResponseBody
    public Object delPersonVehicle(String ids,String number){
    	JsonBean jsonBean=null;
		if(vehicleService.delPersonVehicleService(ids,number)){
			jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/vehicle/getPersonVehiclePage"),"","","","");
		}else{
			jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
	return jsonBean;
    }
    /**
     * 根据id查询出人口车辆信息
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/findPersonVehicleById")
    @ResponseBody
    public ModelAndView findPersonVehicleById(HttpServletRequest request,Integer id){
    	ModelAndView mv=new ModelAndView("/person/vehicle_update");
    	VehicleInformation vehicle=new VehicleInformation();
    	vehicle=vehicleService.findPersonVehicleById(id);
    	mv.addObject("vehicle", vehicle);
		return mv;
    }
    /**
     * 编辑人口车辆信息
     * @param person
     * @return
     */
    @RequestMapping("/updateVehicle")
    @ResponseBody
    public Object updateVehicle(HttpServletRequest request,VehicleInformation vehicle){
    	String owners=request.getParameter("personName");
    	String contact=request.getParameter("personContact");
    	Integer person_id=Integer.parseInt(request.getParameter("personId"));
    	vehicle.setOwners(owners);
    	vehicle.setPerson_contact(contact);
    	vehicle.setPerson_id(person_id);
    	JsonBean jsonBean=null;
    	if(vehicleService.updateVehicleService(vehicle)){
    		jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/vehicle/getPersonVehiclePage"),"","closeCurrent","","");
    	}else{
    		jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
    	}
		return jsonBean;
    }
    
    
}
