package com.impl.population;

import java.util.List;

import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.population.LaborInsuranceDao;
import com.dao.population.LaborInsuranceService;
import com.dao.population.PersonDao;

/**
 * 劳保信息
 * @author Administrator
 *
 */
@Service(value="laborService")
public class LaborInsuranceServiceImpl implements LaborInsuranceService{
	private PersonDao personDao;//人口基本信息
	private LaborInsuranceDao  laborinsuranceDao;//人口劳保信息
	
	public PersonDao getPersonDao() {
		return personDao;
	}
	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public LaborInsuranceDao getLaborinsuranceDao() {
		return laborinsuranceDao;
	}
	@Autowired
	public void setLaborinsuranceDao(LaborInsuranceDao laborinsuranceDao) {
		this.laborinsuranceDao = laborinsuranceDao;
	}
	/**
	 * 批量修改劳保信息
	 */
	@Override
	@Transactional
	public boolean updateLaborInsurance(List<Person> listPerson) {
		boolean flag=false;
		try{
			for(int i=0;i<listPerson.size();i++){
				List<Person> list=personDao.findPersonByIdnumber(listPerson.get(i).getIdnumber(),null,null);//根据身份证号查询劳保信息
				if(list.size()>0){
					Person person=list.get(0);
					Integer labourId=person.getLaborInsurance().getId();
					System.out.println(labourId);
					LaborInsurance labour=listPerson.get(i).getLaborInsurance();
					labour.setId(labourId);
					laborinsuranceDao.updateLaborInsurance(labour);
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
	 * 编辑人口劳保信息
	 */
	@Override
	@Transactional
	public boolean updateLaborInsuranceService(LaborInsurance labor) {
		Boolean result=false;
		try{
			laborinsuranceDao.updateLaborInsurance(labor);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}

}
