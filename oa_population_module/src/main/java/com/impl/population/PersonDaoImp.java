package com.impl.population;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.Person;
import org.oa_bean.population.VehicleInformation;
import org.springframework.stereotype.Repository;

import com.dao.population.PersonDao;
import com.impl.BaseDaoImpl;

/**
 * 人口信息
 * @author pl
 * @date 2016年6月28日
 * @company
 * PersonDaoImp.java
 */
@Repository(value="personDao")
public class PersonDaoImp extends BaseDaoImpl implements PersonDao{
    /**
     * 分页显示人口信息
     */
	@Override
	public Page<Person> findPersonPage(Page<Person> page,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Person.class, orderby);
	}
    /**
     * 添加人口信息
     */
	@Override
	public void addPerson(Person person) {
		save(person);
	}
	/**
	 * 验证人口身份账号是否已经存在
	 */
	@Override
	public List<Person> findPersonByIdnumber(String idnumber, String orgId,String orgPid) {
		StringBuilder hql = new StringBuilder();
		hql.append("select p from Person p where  p.idnumber='"+idnumber+"'");
		if(!"".equals(orgId) && orgId!=null){
			hql.append(" and p.orgId ="+orgId);
		}
		if(!"".equals(orgId) && orgId!=null){
			hql.append(" and p.orgPid ="+orgPid);
		}
		return findObjectList(hql.toString());
	}
    /**
     * 修改人口信息
     */
	@Override
	public void updatePerson(Person person) {
		update(person);
	}
    /**
     * 根据id查找一条记录
     */
	@Override
	public Person findPersonById(Integer id) {
		return find(Person.class, id);
	}
     /**
      * 条件分页查询
      */
	@Override
	public Page<Person> getConditionFamilyPage(Page<Person> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Person.class, wheresql, params, orderby);
	}
    /**
     * 删除人口信息
     */
	@Override
	public void deletePerson(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_person where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}
	/**
	 * 分页查询人口车辆信息
	 */
	@Override
	public Page<VehicleInformation> findVehiclePage(
			Page<VehicleInformation> page) {
		return getScrollData(page, VehicleInformation.class);
	}
	/**
	 * 更新与户主的关系
	 */
	@Override
	public void updatePersonByFamily(String familyId, Person person) {
		String sql ="";
		if(person.getId()==null || person.getId()==0 ){
			sql= "update c_person set ralation ='',family_id="+null+" where family_id="+Integer.parseInt(familyId); //解除户籍关系
		}else{
			sql= "update c_person set ralation ='"+person.getRalation()+"', family_id="+familyId+"  where id="+person.getId();//更新户主和户籍关系
		}
		createSqlMethod(sql).executeUpdate();
	}
	/**
	 * 查询所有人的手机号码
	 */
	@Override
	public List findPersonContact() {
		String sql="select contact from c_person";
		return createSqlMethod(sql).getResultList();
	}
	/**
	 * 根据户籍Id查询人口基本信息
	 */
	@Override
	public List<Person> findByFamilyPlan(Integer familyPlanId) {
		String hql="select p from Person p where p.familyPlanning.id="+familyPlanId;
		return findObjectList(hql);
	}
	@Override
	public List<Person> findPersonByIds(String ids) {
		String hql="select p from Person p where p.id in("+ids+")";
		return findObjectList(hql);
	}
	@Override
	public List statisticsData(String sql) {
		return createSqlMethod(sql).getResultList();
	}

}
