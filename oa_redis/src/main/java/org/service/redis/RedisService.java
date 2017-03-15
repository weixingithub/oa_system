package org.service.redis;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年10月19日 下午2:10:45 
 * @version 1.0 
 */
public interface RedisService {
	
	public void put(String hashName, String key, String value);
	
	public void put(String hashName, String key, Object value);
	
	public <T> T get(String hashName,String key, Class<T> className);
	 
	public String get(String hashName,String key);
	
	public void setRedisList(String key,String value);
	
	public String getRedisList(String key,long index);
	
	public boolean keyExist(String key,String field);
	
	public void deleteHashByKey(String key,String field);
}
