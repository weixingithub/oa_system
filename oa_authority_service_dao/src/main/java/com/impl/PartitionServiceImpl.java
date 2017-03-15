package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PartitionDao;
import com.dao.PartitionService;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
@Service(value="partitionService")
public class PartitionServiceImpl implements PartitionService {
	
	private PartitionDao partitionDao;

	public PartitionDao getPartitionDao() {
		return partitionDao;
	}
    @Autowired
	public void setPartitionDao(PartitionDao partitionDao) {
		this.partitionDao = partitionDao;
	}

	@Override
	@Transactional
	public void addListPartition(String tableName, String modelId) {
		// TODO Auto-generated method stub
		String sql="ALTER TABLE "+tableName+" ADD PARTITION (PARTITION p"+modelId+"  VALUES IN('"+modelId+"'))";
		partitionDao.createSqlMethod(sql).executeUpdate();
	}

	@Override
	@Transactional
	public void delListPartition(String tableName, String modelId) {
		// TODO Auto-generated method stub
		String sql="ALTER TABLE "+tableName+" DROP PARTITION  p"+modelId;
		partitionDao.createSqlMethod(sql).executeUpdate();
	}

	@Override
	public List<Object> findAllPartition(String tableName) {
		String sql = "SELECT partition_name part, partition_expression expr, partition_description descr, table_rows FROM INFORMATION_SCHEMA.partitions"
				+" WHERE  TABLE_SCHEMA = SCHEMA()  AND TABLE_NAME='"+tableName+"'";
		return partitionDao.createSqlMethod(sql).getResultList();
	}

}
