package com.dao.cabinet;

import org.oa_bean.cabinet.FileCabinet;


/**
 * @author  江斌
 * @description 
 * @date 创建时间：2016年8月3日 上午10:37:13 
 * @version 1.0 
 */
public interface FileCabinetService {
	/**
	 * 新增附件或编辑
	 * @param file
	 */
	public boolean updateFileService(FileCabinet fileCabinet);
	/**
	 * 删除附件
	 * @param ids
	 */
	public boolean deleteFileService(String ids);
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	public FileCabinet findFileCabinetByIdService(Integer id);
}
