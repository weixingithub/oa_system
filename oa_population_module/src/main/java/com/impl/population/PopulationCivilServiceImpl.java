package com.impl.population;

import java.util.List;

import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.population.PersonDao;
import com.dao.population.PopulationCivilDao;
import com.dao.population.PopulationCivilService;
/**
 * 民政信息
 * @author Administrator
 *
 */
@Service(value="populationCivilService")
public class PopulationCivilServiceImpl implements PopulationCivilService{
	private PersonDao personDao;//人口基本信息
	private PopulationCivilDao populationcivilDao;//人口民政信息
	
	public PersonDao getPersonDao() {
		return personDao;
	}
	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public PopulationCivilDao getPopulationcivilDao() {
		return populationcivilDao;
	}
	@Autowired
	public void setPopulationcivilDao(PopulationCivilDao populationcivilDao) {
		this.populationcivilDao = populationcivilDao;
	}

	@Override
	@Transactional
	public boolean updatePopulationCivil(List<Person> listPerson) {
		boolean flag=false;
		try{
			for(int i=0;i<listPerson.size();i++){
				List<Person> list=personDao.findPersonByIdnumber(listPerson.get(i).getIdnumber(),null,null);
				if(list.size()>0){
					Person person=list.get(0);
					Integer civilId=person.getPopulationCivil().getId();
					PopulationCivil civil=listPerson.get(i).getPopulationCivil();
					civil.setId(civilId);
					populationcivilDao.updatePopulationCivil(civil);
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
	 * 编辑人口民政信息
	 */
	@Override
	@Transactional
	public boolean updatePopulationCivilService(PopulationCivil pc) {
		Boolean result=false;
		try{
			populationcivilDao.updatePopulationCivil(pc);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}

}
