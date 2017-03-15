package com.impl.population;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.VehicleInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.population.VehicleDao;
import com.dao.population.VehicleService;

/**
 * 车辆信息
 * @author Administrator
 *
 */
@Service(value="vehicleService")
public class VehicleServiceImpl implements VehicleService {
	private VehicleDao vehicleDao;

	public VehicleDao getVehicleDao() {
		return vehicleDao;
	}
	@Autowired
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	/**
	 * 人口车辆信息条件分页查询
	 */
	@Override
	public Page<VehicleInformation> getPersonVehiclePage(
			Page<VehicleInformation> page, VehicleInformation vehicle,
			LinkedHashMap<String, String> orderby) {
		 StringBuffer wheresql = new StringBuffer("1=1");
		 List<Object> params = new ArrayList<Object>();
		 if(vehicle.getOwners()!=null && !"".equals(vehicle.getOwners())){
			 	wheresql.append(" and o.owners like ? ");
			 	params.add("%"+vehicle.getOwners().trim()+"%");
		 }
		return vehicleDao.getPersonVehiclePage(page,wheresql.toString(),params,orderby);
	}
	/**
	 * 添加人口车辆信息
	 */
	@Override
	@Transactional
	public boolean addVehicleService(VehicleInformation vehicle) {
		Boolean result=false;
		try{
			vehicleDao.addVehicle(vehicle);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	/**
	 * 删除人口车辆信息（可批量）
	 */
	@Override
	@Transactional
	public boolean delPersonVehicleService(String ids,String number) {
		Boolean result=false;
		try{
			String sql="DELETE FROM c_vehicleinformation WHERE id in("+ids+")";
			vehicleDao.delPersonVehicle(sql);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	/**
	 * 根据id查询出车辆信息
	 */
	@Override
	public VehicleInformation findPersonVehicleById(Integer id) {
		return vehicleDao.findPersonVehicleById(id);
	}
	/**
	 * 编辑人口的车辆信息
	 */
	@Override
	@Transactional
	public boolean updateVehicleService(VehicleInformation vehicle) {
		Boolean result=false;
		try{
			vehicleDao.updateVehicle(vehicle);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
}
