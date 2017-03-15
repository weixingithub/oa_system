package com.impl.population;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.oa_bean.Page;
import org.oa_bean.population.Family;
import org.oa_bean.population.Ground;
import org.oa_bean.population.House;
import org.oa_bean.population.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.cabinet.FileCabinetDao;
import com.dao.population.FamilyDao;
import com.dao.population.FamilyService;
import com.dao.population.GroundDao;
import com.dao.population.HouseDao;
import com.dao.population.PersonDao;

@Service(value="familyService")
public class FamilyServiceImpl implements FamilyService {
	private  FamilyDao familyDao;
	private  GroundDao groundDao;
	private  HouseDao houseDao;
	private PersonDao personDao;//人口基本信息
	private FileCabinetDao fileCabinetDao;//文件柜信息
	
	public FileCabinetDao getFileCabinetDao() {
		return fileCabinetDao;
	}
	@Autowired
	public void setFileCabinetDao(FileCabinetDao fileCabinetDao) {
		this.fileCabinetDao = fileCabinetDao;
	}
	public PersonDao getPersonDao() {
		return personDao;
	}
	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public GroundDao getGroundDao() {
		return groundDao;
	}
	@Autowired
	public void setGroundDao(GroundDao groundDao) {
		this.groundDao = groundDao;
	}
	public HouseDao getHouseDao() {
		return houseDao;
	}
	@Autowired
	public void setHouseDao(HouseDao houseDao) {
		this.houseDao = houseDao;
	}
	public FamilyDao getFamilyDao() {
		return familyDao;
	}
	@Autowired
	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}

	@Override
	public Family findFamilyById(String id) {
		// TODO Auto-generated method stub
		return familyDao.findFamilyById(id);
	}

	@Override
	@Transactional
	public boolean addAndUpdateFamilyService(Family family,String operationType,String personId,String personOldId) {
		boolean flag = false;
		try{
			if("add".equals(operationType)){  //添加操作
				familyDao.save(family);
				
				Person person = new Person();
				person.setId(Integer.parseInt(personId));
				person.setRalation("1");
				personDao.updatePersonByFamily(family.getId().toString(),person);
				flag = true;
			}else{
				familyDao.update(family);
				if(!personOldId.equals(personId)){
					if(personId!=null && !"".equals(personId)){
						Person person = new Person();
						person.setId(Integer.parseInt(personId));
						person.setRalation("1");
						personDao.updatePersonByFamily(family.getId().toString(),person);
						
					}
					if(personOldId!=null && !"".equals(personOldId)){
						Person operson = new Person();
						operson.setId(Integer.parseInt(personOldId));
						operson.setRalation("");
						personDao.updatePersonByFamily(null,operson);
					}
				}
				flag = true;
			}
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean deleteFamilyService(String ids) {
		boolean flag = false;
		try{
			List<Family> listfamily = familyDao.findFamilyByIds(ids);
			StringBuffer houseIds = new StringBuffer();
			StringBuffer groundIds = new StringBuffer();
			StringBuffer fileCabinetids = new StringBuffer();
			
			
			for (Family family : listfamily) {
				if(family.getHouse().getId()!=null &&!"".equals(family.getHouse().getId())){
					houseIds.append(family.getHouse().getId());
					houseIds.append(",");
				}
				if(family.getGround().getId()!=null &&!"".equals(family.getGround().getId())){
					groundIds.append(family.getGround().getId());
					groundIds.append(",");
				}
				if(family.getFileCabinet().getId()!=null &&!"".equals(family.getFileCabinet().getId())){
					fileCabinetids.append(family.getFileCabinet().getId());
					fileCabinetids.append(",");
				}
				
			}
			houseIds = houseIds.deleteCharAt(houseIds.length()-1);
			groundIds = groundIds.deleteCharAt(groundIds.length()-1);
			fileCabinetids = fileCabinetids.deleteCharAt(fileCabinetids.length()-1);
			
			String []entityids = ids.split(",");
			
			for(int i=0;i<entityids.length;i++){
				personDao.updatePersonByFamily(entityids[i],new Person());
			}
			familyDao.deleteFamily(ids);
			houseDao.deleteHouse(houseIds.toString());
			groundDao.deleteGround(groundIds.toString());
			fileCabinetDao.deleteFile(fileCabinetids.toString());
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public Page<Family> getConditionFamilyPage(Page<Family> page,
			Family family, LinkedHashMap<String, String> orderby,
			String starttime, String endtime) {
		// TODO Auto-generated method stub
		 StringBuffer wheresql = new StringBuffer("1=1");
		 List<Object> params = new ArrayList<Object>();
		 if(family.getOrgId()!=null &&!"".equals(family.getOrgId())){
			 String[] arr = family.getOrgId().split(",");
				wheresql.append(" and o.orgId in (");
				for(int i=0;i<arr.length;i++){
					wheresql.append(" ? ");
					if(i<arr.length-1){
						wheresql.append(",");
					}
					params.add(arr[i].trim());
				}
				wheresql.append(")");
		 }
		 if(family.getOrgPid()!=null && !"".equals(family.getOrgPid())){
			 wheresql.append(" and o.orgPid = ?  ");
			 params.add(family.getOrgPid());
		 }
		 if(family.getHouseholderID()!=null && !"".equals(family.getHouseholderID())){
			 wheresql.append(" and o.householderID like ?  ");
			 params.add("%"+family.getHouseholderID().trim()+"%");
		 }
		 if(family.getHouseholderName()!=null && !"".equals(family.getHouseholderName())){
			 wheresql.append(" and o.householderName like ?  ");
			 params.add("%"+family.getHouseholderName().trim()+"%");
		 }
		 if(family.getDoublefemaleHouseholds()!=null && !"".equals(family.getDoublefemaleHouseholds())){
			 wheresql.append(" and o.doublefemaleHouseholds like ?  ");
			 params.add("%"+family.getDoublefemaleHouseholds()+"%");
		 }
		 if(family.getOneChildFamily()!=null && !"".equals(family.getOneChildFamily())){
			 wheresql.append(" and o.oneChildFamily like ?  ");
			 params.add("%"+family.getOneChildFamily()+"%");
		 }
		 if(family.getPlacestate()!=null && !"".equals(family.getPlacestate())){
			 wheresql.append(" and o.placestate like ?  ");
			 params.add("%"+family.getPlacestate()+"%");
		 }
		 if(family.getPlacetype()!=null && !"".equals(family.getPlacetype())){
			 wheresql.append(" and o.placetype like ?  ");
			 params.add("%"+family.getPlacetype()+"%");
		 }
		 if(family.getPoorfamilystate()!=null && !"".equals(family.getPoorfamilystate())){
			 wheresql.append(" and o.poorfamilystate like ?  ");
			 params.add("%"+family.getPoorfamilystate()+"%");
		 }
		 if(family.getLowincomestate()!=null && !"".equals(family.getLowincomestate())){
			 wheresql.append(" and o.lowincomestate like ?  ");
			 params.add("%"+family.getLowincomestate()+"%");
		 }
		 if(family.getLowincometype()!=null && !"".equals(family.getLowincometype())){
			 wheresql.append(" and o.lowincometype like ?  ");
			 params.add("%"+family.getLowincometype()+"%");
		 }
		 if(family.getSingleparent()!=null && !"".equals(family.getSingleparent())){
			 wheresql.append(" and o.singleparent like ?  ");
			 params.add("%"+family.getSingleparent()+"%");
		 }
		 if((starttime != null && !"".equals(starttime)) && (endtime != null && !"".equals(endtime))){
		    	wheresql.append(" and o.createTime between ?  and ? ");
		    	params.add(starttime+" 00:00:00");
		    	params.add(endtime+" 00:00:00");
		  }
		return familyDao.getConditionFamilyPage(page, wheresql.toString(), params, orderby);
	}
	@Override
	public List<Family> findFamilyByHouseholderID(String householderID,String orgId) {
		// TODO Auto-generated method stub
		return familyDao.findFamilyByHouseholderID(householderID,orgId);
	}
	@Override
	@Transactional
	public boolean updateFamilyByHouseService(House house) {
		boolean flag = false;
		try{
			 houseDao.updateHouse(house);
			 flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean updateFamilyByGroundService(Ground ground) {
		boolean flag = false;
		try{
			groundDao.updateGround(ground);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean updateFamilyByPerson(String familyId, Set<Person> set) {
		boolean flag = false;
		try{
			Person personNew = new Person(); 
			personDao.updatePersonByFamily(familyId,personNew);//解除户籍关系
			for(Person person:set){
				personDao.updatePersonByFamily(familyId,person);//更新户籍关系
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public List<Family> findFamilyByIds(String ids) {
		return familyDao.findFamilyByIds(ids);
	}
}
