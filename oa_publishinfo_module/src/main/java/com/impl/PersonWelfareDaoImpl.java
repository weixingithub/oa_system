package com.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.search.jpa.FullTextQuery;
import org.oa_bean.Page;
import org.oa_bean.PersonWelfare;
import org.springframework.stereotype.Repository;

import com.dao.PersonWelfareDao;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
@Repository(value="personWelfareDao")
public class PersonWelfareDaoImpl extends BaseDaoImpl implements PersonWelfareDao  {
	
	@Override
	public Page<PersonWelfare> findPersonWelfarePage(Page<PersonWelfare> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, PersonWelfare.class, wheresql, params, orderby);
	}

	@Override
	public void deletePersonWelfareByUser(String userId,String modleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete p.*,c.* from pw_table p,t_pw_content c where p.flow_status !=1 and p.user_id ='" +userId+"' and p.id=c.content_id");
		if(!"".equals(modleId)&& modleId!=null){
			sql.append(" and p.model_id = '"+modleId+"'");
		}
		createSqlMethod(sql.toString()).executeUpdate();
	}

	@Override
	public List<PersonWelfare> findByPersonWelfareIds(Set<String> businessKeys) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select p from PersonWelfare p where p.id in (");
		Iterator its = businessKeys.iterator();
		while(its.hasNext()){
			hql.append(its.next());
			hql.append(",");
		}
		hql = hql.deleteCharAt(hql.length()-1);
		hql.append(")");
		return findObjectList(hql.toString());
	}

	@Override
	public String findPwIdByCIndex(org.apache.lucene.search.Query query, Class z) {
		StringBuffer ids=new StringBuffer("");
		FullTextQuery fullTextQuery = getFullTextEntityManager().createFullTextQuery(query, z);
		fullTextQuery.setProjection("id");
		List<Object[]> list = fullTextQuery.getResultList();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object obj = list.get(i)[0];
				ids.append(obj+",");
			}
			ids.deleteCharAt(ids.length()-1);
		}else{
			ids.append("0");
		}
		return ids.toString();
	}
}
