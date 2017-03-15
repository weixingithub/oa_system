package com.impl.population;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.VehicleInformation;
import org.springframework.stereotype.Repository;

import com.dao.population.VehicleDao;
import com.impl.BaseDaoImpl;

/**
 * 人口的车辆信息
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * VehicleDaoImp.java
 */
@Repository(value="vehicleDao")
public class VehicleDaoImp extends BaseDaoImpl implements VehicleDao{
	/**
	 * 验证车辆信息车牌号是否已经存在
	 */
	@Override
	public List<VehicleInformation> findVehicleByLicenseNumber(String licensenumber) {
		String hql="select v from VehicleInformation v  where licenseplatenumber="+licensenumber;
		return findObjectList(hql);
	}
	/**
	 * 车辆的条件查询
	 */
	@Override
	public Page<VehicleInformation> getPersonVehiclePage(
			Page<VehicleInformation> page, String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, VehicleInformation.class, wheresql, params, orderby);
	}
   /**
    * 添加人口车辆信息
    */
	@Override
	public void addVehicle(VehicleInformation vehicle) {
		 save(vehicle);
	}
   /**
    * 条件查询人口车辆信息
    */
	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleInformation> findPersonVehicle(String sql) {
		return createSqlMethod(sql).getResultList();
	}
	/**
	 * 删除人口车辆信息(可批量)
	 */
	@Override
	public void delPersonVehicle(String sql) {
		createSqlMethod(sql).executeUpdate();
	}
	/**
	 * 根据id查询人口车辆信息
	 */
	@Override
	public VehicleInformation findPersonVehicleById(Integer id) {
		return find(VehicleInformation.class, id);
	}
	/**
	 * 修改人口车辆信息
	 */
	@Override
	public void updateVehicle(VehicleInformation vehicle) {
		update(vehicle);
	}
	/**
	 * 更新人口信息同时更新车辆信息
	 */
	@Override
	public int updatePersonVehicle(Integer id,String owners,String contact) {
		String sql="update c_vehicleinformation set owners='"+owners+"',person_contact='"+contact+"' WHERE person_id= "+id;
		return createSqlMethod(sql).executeUpdate();
	}
	/**
	 * 删除人口信息同时删除车辆信息
	 */
	@Override
	public void deleteVehicle(String ids) {
			String []entityids = ids.split(",");
			for(int i=0;i<entityids.length;i++){
				String sql = "DELETE FROM c_vehicleinformation WHERE person_id="+entityids[i];
				createSqlMethod(sql).executeUpdate();
			}
	}
}
