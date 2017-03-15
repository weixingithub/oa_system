package com.dao;

import java.util.List;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
public interface PartitionService {
	
    public void addListPartition(String tableName,String modelId);
    
    public void delListPartition(String tableName,String modelId);
    
    public List<Object> findAllPartition(String tableName);
}
