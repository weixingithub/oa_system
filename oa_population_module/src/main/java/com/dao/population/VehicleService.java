package com.dao.population;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.population.VehicleInformation;

public interface VehicleService {
	/**
	 * 人口车辆信息条件查询
	 * @param page
	 * @param vehicle
	 * @param orderby
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Page<VehicleInformation> getPersonVehiclePage(Page<VehicleInformation> page,
			VehicleInformation vehicle, LinkedHashMap<String, String> orderby);
	
	 /**
     * 添加人口车辆信息
     * @param vehicle
     * @return
     */
	public boolean addVehicleService(VehicleInformation vehicle);
	
	/**
	 * 删除人口车辆信息（可批量）
	 * @param ids
	 * @param number 
	 * @return
	 */
	public boolean delPersonVehicleService(String ids, String number);
	
	/**
	 * 根据id查询出车辆信息
	 * @param id
	 * @return
	 */
	public VehicleInformation findPersonVehicleById(Integer id);
	/**
	 * 编辑人口的车辆信息
	 * @param person
	 * @return
	 */
	public boolean updateVehicleService(VehicleInformation vehicle);
	
}
