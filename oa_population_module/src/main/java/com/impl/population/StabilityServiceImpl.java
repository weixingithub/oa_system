package com.impl.population;

import java.util.List;

import org.oa_bean.population.Person;
import org.oa_bean.population.Stability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.population.PersonDao;
import com.dao.population.StabilityDao;
import com.dao.population.StabilityService;
/**
 * 综治信息
 * @author Administrator
 *
 */
@Service(value="stabilityService")
public class StabilityServiceImpl implements StabilityService{
	private PersonDao personDao;//人口基本信息
	private StabilityDao stabilityDao;//人口综治信息
	
	public PersonDao getPersonDao() {
		return personDao;
	}
	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public StabilityDao getStabilityDao() {
		return stabilityDao;
	}
	@Autowired
	public void setStabilityDao(StabilityDao stabilityDao) {
		this.stabilityDao = stabilityDao;
	}
	/**
	 * 批量修改综治信息
	 */
	@Override
	@Transactional
	public boolean updateStability(List<Person> listPerson) {
		boolean flag=false;
		try{
			for(int i=0;i<listPerson.size();i++){
				List<Person> list=personDao.findPersonByIdnumber(listPerson.get(i).getIdnumber(),null,null);
				if(list.size()>0){
					Person person=list.get(0);
					Integer stabilityId=person.getStability().getId();
					Stability stab=listPerson.get(i).getStability();
					stab.setId(stabilityId);
					stabilityDao.updateStability(stab);
				}
			}
			flag= true;
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 编辑综治维稳信息
	 */
	@Override
	@Transactional
	public boolean updateStabilityService(Stability stability) {
		Boolean result=false;
		try{
			stabilityDao.updateStability(stability);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}

}
