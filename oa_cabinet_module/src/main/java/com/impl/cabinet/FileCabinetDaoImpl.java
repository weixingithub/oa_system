package com.impl.cabinet;

import org.oa_bean.cabinet.FileCabinet;
import org.springframework.stereotype.Repository;

import com.dao.cabinet.FileCabinetDao;
import com.impl.BaseDaoImpl;

/**
 * @author  江斌
 * @description 
 * @date 创建时间：2016年8月3日 上午10:37:13 
 * @version 1.0 
 */
@Repository(value="fileCabinetDao")
public class FileCabinetDaoImpl extends BaseDaoImpl implements FileCabinetDao {

	/**
	 * 新增附件
	 * @param file
	 */
	public void saveFile(FileCabinet file) {
		save(file);
		
	}
	/**
	 * 修改附件
	 * @param file
	 */
	public void updateFile(FileCabinet file) {
		update(file);
		
	}
	/**
	 * 删除附件
	 * @param ids
	 */
	public void deleteFile(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from t_file where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
		
	}
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	@Override
	public FileCabinet findFileCabinetById(Integer id) {
		return find(FileCabinet.class, id);
	}
}
