package com.dao.population;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.VehicleInformation;

import com.dao.BaseDao;

/**
 * 人口的车辆信息
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * VehicleDao.java
 */
public interface VehicleDao extends BaseDao{
	/**
	 * 验证车牌号是否已经存在
	 * @param licensenumber
	 * @param orgId
	 * @return
	 */
	public List<VehicleInformation> findVehicleByLicenseNumber(
			String licensenumber);
	/**
	 * 车辆信息的条件查询
	 * @param page
	 * @param string
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<VehicleInformation> getPersonVehiclePage(
			Page<VehicleInformation> page, String string, List<Object> params,
			LinkedHashMap<String, String> orderby);
    /**
     * 添加人口车辆信息
     * @param vehicle
     */
	public void addVehicle(VehicleInformation vehicle);
    /**
     * 条件查询出人口的车辆信息
     * @param id
     * @return
     */
	public List<VehicleInformation> findPersonVehicle(String sql);
	/**
	 * 删除人口车辆信息(可批量)
	 * @param ids
	 */
	public void delPersonVehicle(String sql);
	/**
	 * 根据id查询人口车辆信息
	 * @param id
	 * @return
	 */
	public VehicleInformation findPersonVehicleById(Integer id);
	/**
	 * 修改人口车辆信息
	 * @param vehicle
	 */
	public void updateVehicle(VehicleInformation vehicle);
	/**
	 * 更新人口信息同时更新车辆信息
	 * @param sql
	 * @return
	 */
	public int updatePersonVehicle(Integer id,String owners,String contact);
	/**
	 * 删除人口同时删除车辆
	 * @param ids
	 */
	public void deleteVehicle(String ids);


	

}
