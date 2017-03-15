package com.dao.cabinet;

import org.oa_bean.cabinet.FileCabinet;
/**
 * @author  江斌
 * @description 
 * @date 创建时间：2016年8月3日 上午10:37:13 
 * @version 1.0 
 */
public interface FileCabinetDao {
	/**
	 * 编辑附件
	 * @param file
	 */
	public void updateFile(FileCabinet file);
	/**
	 * 删除附件
	 * @param ids
	 */
	public void deleteFile(String ids);
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	public FileCabinet findFileCabinetById(Integer id);
	
}
