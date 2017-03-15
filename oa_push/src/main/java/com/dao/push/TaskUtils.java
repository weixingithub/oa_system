package com.dao.push;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import push.PushManage;
import push.SpringUtils;

import com.bean.push.ScheduleJob;

/**
 * 通过scheduleJob的beanClass或springId通过反射或spring来获得需要执行的类,通过methodName来确定执行哪个方法
 * 执行任务中定义的方法
 * @author Administrator
 * @date 2016年10月21日
 * @company
 * TaskUtils.java
 */
public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringUtils.getBean(scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				//不带参数的类
//				clazz = Class.forName(scheduleJob.getBeanClass());
//				object = clazz.newInstance();
				//带参数的类，利用反射机制           
				clazz = Class.forName(scheduleJob.getBeanClass());
				Constructor constructor = clazz.getConstructor();
				object = constructor.newInstance();
//				Constructor constructor = clazz.getConstructor(String.class,String.class);
//				object = (PushManage) constructor.newInstance("58087981f29d9869f00000e2","fqrhykwgmcpa8lni8wrpumk8nvc6sqpa");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		Class[] push = null;
		try {
			method = clazz.getMethod(scheduleJob.getMethodName(),ScheduleJob.class);
			//method = clazz.getMethod(scheduleJob.getMethodName(),PushBean.class );//调用传实体类的方法getDeclaredMethod
			//method = clazz.getMethod(scheduleJob.getMethodName(), new Class[]{String.class,String.class,String.class,String.class});
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object,scheduleJob);
				//method.invoke(object, new PushBean());
				//method.invoke(object, new Object[] {scheduleJob.getToken(),scheduleJob.getTicker(),scheduleJob.getTitle(),scheduleJob.getText()});
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
	}
}
