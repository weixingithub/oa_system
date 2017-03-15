package com.oa.controller;

import java.util.HashMap;
import java.util.Map;

import org.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年10月20日 上午10:16:45 
 * @version 1.0 
 */
@Controller
@RequestMapping(value = "/redis")
public class RedisController {
	   @Autowired
	   private RedisService redisService;
		 @RequestMapping(value = "/test")
		 @ResponseBody
	    public Map testRedis() {
			 redisService.put("userToken","52301", "GBLfuLaQB");  
			  if(redisService.keyExist("userToken", "52301")){
				System.out.println("52301的用户已登录,token为:"+redisService.get("userToken", "52301"));
				redisService.deleteHashByKey("userToken", "52301");
				System.out.println("52301的用户已注销");
				if(!redisService.keyExist("userToken", "52301")){
					System.out.println("52301的用户注销成功");
				}
			}
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("redis", "success");
		 return map;
	   }
}
