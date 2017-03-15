package com.impl.massmsg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.massmsg.MassMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.massmsg.MassMsgDao;
import com.dao.massmsg.MassMsgService;

@Service(value="massMsgService")
public class MassMsgServiceImpl implements MassMsgService{
	private MassMsgDao massMsgDao;
	
	
	public MassMsgDao getMassMsgDao() {
		return massMsgDao;
	}
	@Autowired
	public void setMassMsgDao(MassMsgDao massMsgDao) {
		this.massMsgDao = massMsgDao;
	}

	@Override
	@Transactional
	public boolean addMassMsgService(MassMsg massMsg) {
		boolean flag = false;
		try{
			massMsgDao.addMassMsg(massMsg);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateMassMsgService(MassMsg massMsg) {
		boolean flag = false;
		try{
			massMsgDao.update(massMsg);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteMassMsgService(Integer id) {
		boolean flag = false;
		try{
			massMsgDao.deleteMassMsg(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public MassMsg getMassMsgById(Integer id) {
		return massMsgDao.getMassMsgById(id);
	}

	@Override
	public Page<MassMsg> getPageMassMsg(Page<MassMsg> page, MassMsg massMsg,
			LinkedHashMap<String, String> orderby,String startTime,String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if(!"".equals(massMsg.getSendType())&& massMsg.getSendType()!=null ){
			wheresql.append(" and sendType = ? ");
			params.add(massMsg.getSendType());
		}
		if(!"".equals(massMsg.getFilter())&& massMsg.getFilter()!=null ){
			wheresql.append(" and filter = ? ");
			params.add(massMsg.getFilter());
		}
		if(!"".equals(massMsg.getMsgtype())&& massMsg.getMsgtype()!=null ){
			wheresql.append(" and msgtype = ? ");
			params.add(massMsg.getMsgtype());
		}
		if(!"".equals(startTime)&& startTime!=null ){
			wheresql.append(" and sendTime > ? ");
			params.add(startTime);
		}
		if(!"".equals(endTime)&& endTime!=null ){
			wheresql.append(" and sendTime < ? ");
			params.add(endTime);
		}
		return massMsgDao.getPageMassMsg(page, wheresql.toString(), params, orderby);
	}
	@Override
	public List<MassMsg> getMassMsgByArticleIds(String aIds) {
		return massMsgDao.getMassMsgByArticleIds(aIds);
	}

}
