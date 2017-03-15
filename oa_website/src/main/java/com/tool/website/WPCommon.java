package com.tool.website;

import java.util.HashMap;
import java.util.Map;


public class WPCommon {
	/*首页页面地址*/
	public static final String pageIndex ="/layout/index";
	/*列表页面地址*/
	public static final String pageList ="/layout/infoList";
	/*详情页面地址*/
	public static final String pageDetails ="/layout/details";
	/*错误页面地址*/
	public static final String pageError ="/layout/error";
	/*首页url*/
	public static final String findWebsiteIndex ="/portal/findWebsiteIndex";
	/*二级url*/
	public static final String findWebsiteNext ="/portal/findWebsiteNext";
 
    public static Map<Long,String> pluginTypeMap = new HashMap<Long,String>(){{
	    put(1l,"文章");
	    put(3l,"视频");
	    put(4l,"图片");
	    put(5l,"搜索");
	    put(6l,"登录");
	    put(7l,"翻页");
	    put(8l,"链接");
	    put(9l,"图文");
	    put(10l,"导航");
	    put(11l,"活动");
	    put(12l,"职务");
	    put(13l,"快捷方式");
    }};
    public static Map<Long,String> layoutlevelMap = new HashMap<Long,String>(){{
	    put(1l,"首页页面");
	    put(2l,"列表页面");
	    put(3l,"详情页面");
	    put(4l,"自定义页面");
    }};
    public static Map<Long,String> themeColourMap = new HashMap<Long,String>(){{
	    put(1l,"紫红");
	    put(2l,"鲜红");
	    put(3l,"粉红");
	    put(4l,"天蓝");
	    put(5l,"橘黄");
    }};
    public static Map<Long,String> websiteTypeMap = new HashMap<Long,String>(){{
	    put(1l,"政务");
	    put(2l,"电商");
	    put(3l,"公益");
	    put(4l,"学校");
    }};
    public static String getSource(int pubPlatform){
    	String str ="";
    	switch (pubPlatform) {
		case 3:
			str = 3+","+3*5+","+3*7+","+3*5*7;
			break;
		case 5:
			str = 5+","+5*3+","+5*7+","+3*5*7;	
			break;
		case 7:
			str = 7+","+7*3+","+7*5+","+3*5*7;
			break;
		}
    	return str;
    }

}
