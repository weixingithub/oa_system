package com.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.oa_bean.Page;

import com.dao.BaseDao;



public class BaseDaoImpl implements BaseDao {
    @PersistenceContext(unitName="datasourceone")
	protected EntityManager entityManager;
	public void save(Object entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}
	
	public void update(Object entity){
		entityManager.merge(entity);
	}

	public <T> void delete(Class<T> entityClass, Object entityId) {
		// TODO Auto-generated method stub
		delete(entityClass,new Object[]{entityId});
	}

	public <T> void delete(Class<T> entityClass, Object[] entityids){
		// TODO Auto-generated method stub
		for(Object id : entityids){
			entityManager.remove(entityManager.getReference(entityClass, id));
		}
	}

	public <T> T find(Class<T> entityClass, Object entityId) {
		// TODO Auto-generated method stub
		return entityManager.find(entityClass, entityId);
	}

	public <T> Page<T> getScrollData(Page<T> page,Class<T> entityClass) {
		// TODO Auto-generated method stub
		String entityname = getEntityName(entityClass);
		String hql="select o from "+entityname+" o";
		String counthql = "select count(o) from "+entityname+" o";
		Query query = entityManager.createQuery(hql);
		query.setFirstResult(page.getFirst()).setMaxResults(page.getPageSize());
		page.setResult(query.getResultList());
		query = entityManager.createQuery(counthql);
		page.setTotalCount(Integer.parseInt(query.getSingleResult().toString()));
		return page;
	}
	
	public <T> Page<T> getScrollData(Page<T> page, Class<T> entityClass, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, entityClass, null, null, orderby);
	}
	
	public <T> Page<T> getScrollData(Page<T> page, Class<T> entityClass,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby) {
		String entityname = getEntityName(entityClass);
		String hql = "select o from "+entityname+" o "+" where " + wheresql + buildOrderby(orderby);   
		String hqlcount = "select count(o) from "+entityname+" o "+ " where " + wheresql;
		Query query = entityManager.createQuery(hql);
		setQueryParams(query, queryParams);
		query.setFirstResult(page.getFirst()).setMaxResults(page.getPageSize());
		page.setResult(query.getResultList());
		query = entityManager.createQuery(hqlcount);
		setQueryParams(query, queryParams);
		page.setTotalCount(Integer.parseInt(query.getSingleResult().toString()));
		return page;
	}
	
	
	public <T> Page<T> getSqlScrollData(Page<T> page,Class<T> entityClass,String wheresql,String tableName,Map<String,String> orderby){
		String entitysql ="select * from "+tableName+" where "+wheresql+buildSqlOrderby(orderby)+" limit "+page.getFirst()+","+page.getPageSize();
		String countsql = "select count(*) from "+tableName+" where "+wheresql;
		Query query = entityManager.createNativeQuery(entitysql, entityClass);
		page.setResult(query.getResultList());
		query = entityManager.createNativeQuery(countsql);
		page.setTotalCount(Integer.parseInt(query.getSingleResult().toString()));
		return page;
	}
	
	public <T> List<T> findObjectList(String hql) {
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}
	
	public Object findObject(String hql) {
		Query query = entityManager.createQuery(hql);
		return query.getSingleResult();
	}

	public <T> Page<T> findObjectPage(Page<T> page,String hql,String counthql) {
		Query query = entityManager.createQuery(hql);
		query.setFirstResult(page.getFirst()).setMaxResults(page.getPageSize());
		page.setResult(query.getResultList());
		query = entityManager.createQuery(counthql);
		page.setTotalCount(Integer.parseInt(query.getSingleResult().toString()));
		return page;
	}  
	
	public Query createSqlMethod(String sql) {
		return entityManager.createNativeQuery(sql);
	}
	
	public Query createSqlMethod(String sql,Class z) {
		return entityManager.createNativeQuery(sql,z);
	}
	
    /**
     * 获取实体类的名字
     * @param entityClass
     * @return
     */
	protected <T>String getEntityName(Class<T> entityClass){
		String entityname = entityClass.getSimpleName();  
        Entity entity = entityClass.getAnnotation(Entity.class);
        if(entity.name() != null && !"".equals(entity.name())) {  
            entityname = entity.name();  
        }  
        return entityname;  
	}
	/**
	 * 拼接排序字段
	 * @param orderby
	 * @return
	 */
	public String buildOrderby(LinkedHashMap<String,String> orderby){
		 StringBuffer sb = new StringBuffer();
		 if(null != orderby && 0 != orderby.size()) {  
	            sb.append(" order by ");  
	            for (String key : orderby.keySet()) {  
	                sb.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");  
	            }  
	            sb.deleteCharAt(sb.length() - 1);  
	        }  
	        return sb.toString();
		
	}
	
	private String buildSqlOrderby(Map<String,String> orderby){
		StringBuffer sb = new StringBuffer();
		 if(null != orderby && 0 != orderby.size()) {  
	            sb.append(" order by ");  
	            for (String key : orderby.keySet()) {  
	                sb.append(key).append(" ").append(orderby.get(key)).append(",");  
	            }  
	            sb.deleteCharAt(sb.length() - 1);  
	     }  
	    return sb.toString();
	}
	/**
	 * 拼接查询条件
	 * @param query
	 * @param queryParams
	 */
	 protected void setQueryParams(Query query,List<Object> queryParams) {  
	        if(null != queryParams && 0 < queryParams.size()) {  
	            for (int i = 0; i < queryParams.size(); i++) {  
	                query.setParameter(i+1, queryParams.get(i));  
	            }  
	        }  
	    }

	@Override
	public int createHqlMethod(String hql) {
		// TODO Auto-generated method stub
		return entityManager.createQuery(hql).executeUpdate();
	}
	
	
	
	/**
	 * 全文检索部分
	 * @return
	 */
	@Override
	public FullTextEntityManager getFullTextEntityManager() {
		return Search.getFullTextEntityManager(entityManager);
	}
	
	protected SearchFactory getSearchFactory() {
	   return getFullTextEntityManager().getSearchFactory();
	}

	@Override
	public QueryBuilder getQueryBuilder(Class z) {
	    return getFullTextEntityManager().getSearchFactory().buildQueryBuilder()
	    		.forEntity(z).get();
	}
	@Override
	public <E>Page<E> searchPage(Page<E> page, org.apache.lucene.search.Query query,Class z) {
		FullTextQuery fullTextQuery = getFullTextEntityManager().createFullTextQuery(query, z);
		if (page.isAutoCount()) {
			page.setTotalCount(fullTextQuery.getResultSize());
		    page.setAutoCount(false);
		}
		fullTextQuery.setFirstResult(page.getFirst());
		fullTextQuery.setMaxResults(page.getPageSize());
		page.setResult(fullTextQuery.getResultList());
		return page;
	}

}