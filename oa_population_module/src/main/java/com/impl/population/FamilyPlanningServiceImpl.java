package com.impl.population;

import java.util.List;

import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.population.FamilyPlanningDao;
import com.dao.population.FamilyPlanningService;
import com.dao.population.PersonDao;
/**
 * 计生信息
 * @author Administrator
 *
 */
@Service(value="familyPlanningService")
public class FamilyPlanningServiceImpl implements FamilyPlanningService{
	
	private PersonDao personDao;//人口基本信息
	private FamilyPlanningDao familyplanDao;//人口计生信息
	
	public PersonDao getPersonDao() {
		return personDao;
	}
	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public FamilyPlanningDao getFamilyplanDao() {
		return familyplanDao;
	}
	@Autowired
	public void setFamilyplanDao(FamilyPlanningDao familyplanDao) {
		this.familyplanDao = familyplanDao;
	}

	/**
	 * 批量修改计生信息
	 */
	@Override
	@Transactional
	public boolean updateFamilyPlanning(List<Person> listPerson){
		boolean flag=false;
		try{
			for(int i=0;i<listPerson.size();i++){
				List<Person> list=personDao.findPersonByIdnumber(listPerson.get(i).getIdnumber(),null,null);
				if(list.size()>0){
					Person person=list.get(0);
					Integer familyPlanId=person.getFamilyPlanning().getId();
					FamilyPlanning familyPlan=listPerson.get(i).getFamilyPlanning();
					familyPlan.setId(familyPlanId);
					familyplanDao.updateFamilyPlanning(familyPlan);
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
	 * 编辑人口计生信息
	 */
	@Override
	@Transactional
	public boolean updateFamilyPlanningService(FamilyPlanning familyPlanning) {
		Boolean result=false;
		try{
			familyplanDao.updateFamilyPlanning(familyPlanning);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
}
