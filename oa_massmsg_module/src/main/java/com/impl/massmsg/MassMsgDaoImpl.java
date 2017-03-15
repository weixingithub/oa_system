package com.impl.massmsg;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.massmsg.MassMsg;
import org.springframework.stereotype.Repository;

import com.dao.massmsg.MassMsgDao;
import com.impl.BaseDaoImpl;
/**
 * 消息群发结果保存
 * @author 江斌
 *
 */
@Repository(value="massMsgDao")
public class MassMsgDaoImpl extends BaseDaoImpl	implements MassMsgDao{

	@Override
	public void addMassMsg(MassMsg massMsg) {
		save(massMsg);
	}

	@Override
	public void updateMassMsg(MassMsg massMsg) {
		update(massMsg);
	}

	@Override
	public void deleteMassMsg(Integer id) {
		delete(MassMsg.class, id);
	}

	@Override
	public MassMsg getMassMsgById(Integer id) {
		return find(MassMsg.class, id);
	}

	@Override
	public Page<MassMsg> getPageMassMsg(Page<MassMsg> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, MassMsg.class, wheresql, queryParams, orderby);
	}

	@Override
	public List<MassMsg> getMassMsgByArticleIds(String aIds) {
		String hql= "select m from MassMsg m where m.articleIds = '"+aIds+"' order by m.sendTime desc";
		return findObjectList(hql);
	}

}
