package org.impl.redis;

import java.util.HashMap;
import java.util.Map;

import org.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年10月19日 下午2:17:10 
 * @version 1.0 
 */
public class RedisTest {
	@Autowired
	private RedisService redisService;
	

	public void testRedis(){  
		/*redisService.setRedisList("listKey", "listValue");
		System.out.println(redisService.getRedisList("listKey", 0));
		Map map2 = new Gson().fromJson(redisService.get("hashName","map"), new TypeToken<Map<String,Object>>() {}.getType());  
	    if(map2 != null){  
	        System.out.println("second map---"+map2.get("key1"));  
	    } */ 
	    //简单字符串处理  
	    //map  
	    /*Map<String,Object> map = new HashMap<String,Object>();  
	    map.put("manager", "TbhfuL130");  
	    map.put("dy", "dyufuL453");  
	    redisService.put("userToken","map", map);  */
		/*redisService.put("userToken","52301", "GBLfuLaQB");  
		  if(redisService.keyExist("userToken", "52301")){
			System.out.println("52301的用户已登录,token为:"+redisService.get("userToken", "52301"));
			redisService.deleteHashByKey("userToken", "52301");
			System.out.println("52301的用户已注销");
			if(!redisService.keyExist("userToken", "52301")){
				System.out.println("52301的用户注销成功");
			}
		}*/
	    /*//第一种取值方式  
	    Map map1 = redisService.get("userToken","map",Map.class);  
	    if(map1 != null){  
	        System.out.println("first map---"+map1.get("manager"));  
	    }  
	    //第二种取值方式  
	    Map map2 = new Gson().fromJson(redisService.get("userToken","map"), new TypeToken<Map<String,Object>>() {}.getType());  
	    if(map2 != null){  
	        System.out.println("second map---"+map2.get("dy"));  
	    }  */
	      
	      
	    /*//JavaBean处理  
	    TUser user = new TUser();  
	    user.setUserName("test");  
	    cacheUtil.put("hashName","user",user);  
	    TUser user1 = cacheUtil.get("hashName","user",TUser.class);  
	    System.out.println("javaBean--name--"+user1.getUserName());  
	      
	    //List<JavaBean>处理  
	    List<TUser> list = new ArrayList<TUser>();  
	    list.add(user);  
	    cacheUtil.put("hashName","list", list);  
	    List<TUser> list1 = new Gson().fromJson(cacheUtil.get("hashName","list"), new TypeToken<List<TUser>>() {}.getType());  
	    if(list1 != null){  
	        System.out.println("List<JavaBean>--"+list1.get(0).getUserName());  
	    }  
	      
	      
	    //list<String>  
	    List<String> newlist = new ArrayList<String>();  
	    newlist.add("str1");  
	    newlist.add("sr2");  
	    cacheUtil.put("hashName","newlist", newlist);  
	    List<String> newlist1 =  new Gson().fromJson(cacheUtil.get("hashName","newlist"), new TypeToken<List<String>>(){}.getType());  
	    System.out.println("list<String>--"+newlist1);  
	      
	    //List<Map<String,Object>>  
	    List<Map<String,Object>> nowlist = new ArrayList<Map<String,Object>>();  
	    Map<String,Object> newmap = new HashMap<String,Object>();  
	    newmap.put("key1", "value1");  
	    newmap.put("key2", "value2");  
	    nowlist.add(newmap);  
	    cacheUtil.put("hashName","nowlist", nowlist);  
	    List<Map<String,Object>> nowlist1 =  new Gson().fromJson(cacheUtil.get("hashName","nowlist"), new TypeToken<List<Map<String,Object>>>(){}.getType());  
	    if(nowlist1 !=null ){  
	        System.out.println(nowlist1.get(0).get("key1"));  
	    }  
	    System.out.println("List<Map<String,Object>>--"+nowlist1);  
	      
	    //List<Map<String,TUser>>  
	    List<Map<String,TUser>> lastList = new ArrayList<Map<String,TUser>>();  
	    Map<String,TUser> lastMap = new HashMap<String, TUser>();  
	    lastMap.put("user", user);  
	    lastList.add(lastMap);  
	    cacheUtil.put("hashName","lastList", lastList);  
	    List<Map<String,TUser>> lastList1 =  new Gson().fromJson(cacheUtil.get("hashName","lastList"), new TypeToken<List<Map<String,TUser>>>(){}.getType());  
	    if(lastList1 != null){  
	        System.out.println("List<Map<String,TUser>>---"+lastList1.get(0).get("user").getUserName());  
	    }  
	    System.out.println(lastList1);*/
	}  
}
