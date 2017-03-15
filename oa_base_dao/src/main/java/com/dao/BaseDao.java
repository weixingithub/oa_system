package com.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.oa_bean.Page;

public interface BaseDao {
	/**
	 * 新增操作
	 * @param entity
	 */
    public void save(Object entity);
    /**
     * 更新和新增操作
     * @param entity
     */
    public void update(Object entity);
    /**
     * 根据Id进行删除
     * @param entityClass
     * @param entityId
     */
    public <T> void delete(Class<T> entityClass,Object entityId) ;
    /**
     * 根据ID数据进行批量删除
     * @param entityClass
     * @param entityids
     */
    public <T> void delete(Class<T> entityClass,Object[] entityids) ;
    /**
     * 根据ID进行查找
     * @param entityClass
     * @param entityId
     * @return
     */
    public <T> T find(Class<T> entityClass,Object entityId);
    /**
     * 普通分页
     * @param page
     * @param entityClass
     * @return
     */
    public <T> Page<T> getScrollData(Page<T> page,Class<T> entityClass);
    /**
     * 排序分页
     * @param page
     * @param entityClass
     * @param orderby
     * @return
     */
    public <T> Page<T> getScrollData(Page<T> page,Class<T> entityClass,LinkedHashMap<String,String> orderby);
    /**
     * 带限制条件的排序
     * @param page
     * @param entityClass
     * @param wheresql
     * @param queryParams
     * @param orderby
     * @return
     */
    public <T> Page<T> getScrollData(Page<T> page,Class<T> entityClass,String wheresql,List<Object> queryParams,LinkedHashMap<String,String> orderby);
    /**
     * 执行hql语句查询列表
     * @param hql
     * @return
     */
    public <T> List<T> findObjectList(String hql);
    /**
     *  执行hql查询单个实体
     * @param hql
     * @return
     */
    public Object findObject(String hql);
    /**
     * 执行hql语句查询分页
     * @param hql
     * @return
     */
    public <T> Page<T> findObjectPage(Page<T> page,String hql,String counthql);
    
    /**
     * 执行sql操作
     * @param sql
     * @return
     */
    public Query createSqlMethod(String sql);
    /**
     * hql增删改操作
     * @param hql
     * @return
     */
    public int createHqlMethod(String hql);
    /**
     * sql转换实体查询
     * @param sql
     * @param z
     * @return
     */
    public Query createSqlMethod(String sql,Class z);
    /**
     * sql转化实体分页
     * @param page
     * @param entityClass
     * @param sql
     * @param countsql
     * @param orderby
     * @return
     */
    public <T> Page<T> getSqlScrollData(Page<T> page,Class<T> entityClass,String wheresql,String tableName,Map<String,String> orderby);
    /**
     * 获取全文检索查询工具
     * @param z
     * @return
     */
    public QueryBuilder getQueryBuilder(Class z);
    /**
     * 获取全文检索分页
     * @param page
     * @param query
     * @return
     */
    public <E>Page<E> searchPage(Page<E> page, org.apache.lucene.search.Query query,Class z);
    /**
     * 获取FullTextEntityManager
     * @return
     */
    public FullTextEntityManager getFullTextEntityManager();
}
