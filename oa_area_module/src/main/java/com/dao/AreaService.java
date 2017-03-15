package com.dao;

import java.util.List;

import org.oa_bean.area.City;
import org.oa_bean.area.District;
import org.oa_bean.area.Province;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月29日 下午3:26:00 
 * @version 1.0 
 */
public interface AreaService {
	/**
	 * 获取所有省份
	 * @return
	 */
    public List<Province> findProvinces();
    /**
     * 根据省份获取城市
     * @param pId
     * @return
     */
    public List<City> findCityByPId(Integer pId);
    /**
     * 根据城市获取区县
     * @param cId
     * @return
     */
    public List<District> findDistrictByCId(Integer cId);
    
    /**
     * 根据Id查询省
     * @param proID
     * @return
     */
    public Province findByProID(Integer proID);
    /**
     * 根据Id查询市
     * @param cityID
     * @return
     */
    public City findBycityID(Integer cityID);
    /**
     * 根据Id查询所在区域
     * @param Id
     * @return
     */
    public District findById(Integer Id);
}
