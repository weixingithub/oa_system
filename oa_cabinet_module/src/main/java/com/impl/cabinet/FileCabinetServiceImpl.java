package com.impl.cabinet;

import org.oa_bean.cabinet.FileCabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.cabinet.FileCabinetDao;
import com.dao.cabinet.FileCabinetService;

/**
 * @author  江斌
 * @description 
 * @date 创建时间：2016年8月3日 上午10:37:13 
 * @version 1.0 
 */
@Service(value="fileCabinetService")
public class FileCabinetServiceImpl implements FileCabinetService{
	private FileCabinetDao fileCabinetDao;

	public FileCabinetDao getFileCabinetDao() {
		return fileCabinetDao;
	}
	@Autowired
	public void setFileCabinetDao(FileCabinetDao fileCabinetDao) {
		this.fileCabinetDao = fileCabinetDao;
	}
	@Override
	@Transactional
	public boolean deleteFileService(String ids) {
		boolean falg = false;
		try{
			fileCabinetDao.deleteFile(ids);
			falg = true;
		}catch(Exception e){
			falg = false;
		}
		return falg;
	}
	
	@Override
	@Transactional
	public boolean updateFileService(FileCabinet fileCabinet) {
		boolean falg = false;
		try{
			fileCabinetDao.updateFile(fileCabinet);
			falg = true;
		}catch(Exception e){
			falg = false;
		}
		return falg;
	}
	@Override
	public FileCabinet findFileCabinetByIdService(Integer id) {
		return fileCabinetDao.findFileCabinetById(id);
	}

}
