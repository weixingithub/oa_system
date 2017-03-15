package com.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.AreaService;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月29日 上午10:09:48 
 * @version 1.0 
 */
@Controller
@RequestMapping(value = "/oa/area")
public class AreaController {
	 private AreaService areaService;
	 public AreaService getAreaService() {
		return areaService;
	}
    @Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	@RequestMapping("/city")
	 @ResponseBody
     public Object getCurrentCity(Integer pvalue){
		 List<List> alist = new ArrayList<List>();
		 if(pvalue != 0){
			 List<City> clist = areaService.findCityByPId(pvalue);
			 List<Object> list = null;
			 for(City city:clist){
				 list = new ArrayList<Object>();
				 list.add(city.getCityID());
				 list.add(city.getCityName());
				 alist.add(list);
			 }
		 }else{
			 List<Object> list = new ArrayList<Object>();
			 list.add(0);
			 list.add("所有城市");
			 alist.add(list);
		 }
    	 return alist;
     }
	 
	 @RequestMapping("/region")
	 @ResponseBody
     public Object getCurrentRegion(Integer cvalue){
		 List<List> alist = new ArrayList<List>();
		 if(cvalue != 0){
			 List<District> dlist = areaService.findDistrictByCId(cvalue);
			 List<Object> list = null;
			 for(District district:dlist){
				 list = new ArrayList<Object>();
				 list.add(district.getId());
				 list.add(district.getDisName());
				 alist.add(list);
			 }
		 }else{
			 List<Object> list = new ArrayList<Object>();
			 list.add(0);
			 list.add("所有区县");
			 alist.add(list);
		 }
    	 return alist;
     }
}
