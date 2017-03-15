package org.impl.redis;  
  
import org.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
  
@Component("redisService")  
public class RedisServiceImpl implements RedisService {  
      
    @Autowired  
    private StringRedisTemplate redisTemplate;//redis操作模板  
      
          
    public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}


	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

    /**
     * 设置字符串hash存储
     */
	public void put(String hashName, String key, String value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(hashName, key, value);  
          
    }  
  
    /**
     * 设置对象map存储  
     */
    public void put(String hashName, String key, Object value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(hashName, key, new Gson().toJson(value));  
          
    }  
  
    /**
     *  设置 对象hash存储
     */
    public <T> T get(String hashName,String key, Class<T> className) {  
        Object obj = redisTemplate.opsForHash().get(hashName, key);  
        if(obj == null){  
            return null;  
        }  
        return new Gson().fromJson(""+obj, className);  
    }  
  
    /**
     * hash根据key获取值  
     */
    public String get(String hashName,String key) {  
        Object obj = redisTemplate.opsForHash().get(hashName, key);  
        if(obj == null){  
            return null;  
        }else{  
            return String.valueOf(obj);  
        }  
    }  
    /**
     * hash判断key是否存在
     */
    @Override
	public boolean keyExist(String key,String field) {
		// TODO Auto-generated method stub
		return redisTemplate.opsForHash().hasKey(key, field);
	}
    /**
     * hash根据key删除field
     */
    @Override
	public void deleteHashByKey(String key,String field) {
		// TODO Auto-generated method stub
    	redisTemplate.opsForHash().delete(key, field);
		
	}
    
    /**
     * list根据key设置值
     */
    public void setRedisList(String key,String value){
    	redisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * list根据key获取值
     */
    public String getRedisList(String key,long index){
    	return redisTemplate.opsForList().index(key, index);
    }

    
}  