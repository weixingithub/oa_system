package com.impl;

import java.util.List;

import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AreaDao;
import com.dao.AreaService;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月29日 下午3:30:43 
 * @version 1.0 
 */
@Service(value="areaService")
public class AreaServiceImpl implements AreaService {
    private AreaDao areaDao;
	public AreaDao getAreaDao() {
		return areaDao;
	}
    @Autowired
	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public List<Province> findProvinces() {
		// TODO Auto-generated method stub
		String hql = "select p from Province p";
		return areaDao.findObjectList(hql);
	}

	@Override
	public List<City> findCityByPId(Integer pId) {
		// TODO Auto-generated method stub
		String hql="select c from City c where c.proID="+pId;
		return areaDao.findObjectList(hql);
	}

	@Override
	public List<District> findDistrictByCId(Integer cId) {
		// TODO Auto-generated method stub
		String hql="select d from District d where d.cityID="+cId;
		return areaDao.findObjectList(hql);
	}
	@Override
	public Province findByProID(Integer proID) {
		return areaDao.find(Province.class, proID);
	}
	@Override
	public City findBycityID(Integer cityID) {
		// TODO Auto-generated method stub
		return areaDao.find(City.class, cityID);
	}
	@Override
	public District findById(Integer Id) {
		// TODO Auto-generated method stub
		return areaDao.find(District.class, Id);
	}

}
