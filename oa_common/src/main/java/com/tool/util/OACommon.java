package com.tool.util;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class OACommon {
   public static final String LOGIN_USER="loginuser";//session用户信息
   
   public static final String GROUPID="usergroup";//session用户群组信息
   
   public static final String ROLEID="userrole";//session用户角色信息
   
   public static final String ROLEMODEL="rolemodel";//session角色权限信息
   
   public static final String MODELFLAG="modelflag";//具体模块的权限标识
   
   /*临时节点*/
   public final static String ALLADMIN="全体人员";//所有用户
   
   public final static String ICONPATH="themes/css/img/diy/1_open.png";//全体人员节点图标路径
   
   public static final String TEMPNODE="tempnode";//临时节点ID
   
   public static final String TEMPPARENT="tempparent";//临时父节点ID
   
   public static final String MODELAUTO = "auto";//自动模块标识
    
   public static final String ORGTOPNODE="top";//机构最高节点
   
   public static final String ORGTOP_PARENT="0";//最高机构父节点值
   
   public static final String MODEL_PARENT="0";//最高模块父节点值
   
   public static final String ROLE_PARENT="0";//最高角色父节点值
   
   public static final String SYSCREATOR="creator";//创建者
   
   public static final Integer CREATORID=1;//创建者ID
   
   /*访问权限标识*/
   public static final String VISITE="2";//访问权限
   
   public static final String ADD="3";//新增权限
   
   public static final String EDITOR="5";//编辑权限
   
   public static final String DELETE="7";//删除权限
   
   public static final String  DETAIL="11";// 查看详情权限
   
   public static final String CONFIGRE="41";//配置权限
   
   public static final String VISITE_GROUP="g";//群组访问类型
   
   public static final String VISITE_USER="u";//用户访问类型
   
   public static final String VISITE_SYS="o";//系统访问类型
   
   /*树形菜单url*/
   public static final String FINDUSERPAGE ="/oa/sysuser/findSysUserPage?orgId=";//根据机构获取管理员
   
   public static final String GETORGDETAIL ="/oa/org/getOrgDetail?orgId=";//根据机构id获取机构详情
   
   public static final String FINDMODELDETAIL="/oa/model/getModelDetail?modelId=";//根据模块id获取模块详情
   
   public static final String PWELFAREBASEURL="/oa/pwelfare/modelvariable";//自动便民服务模块
   
   public static final String PWELFARESTAGEURL="/message/getMessagePage";//前台自动便民服务模块
   /*审核状态*/
   public static Map<Long,String> publishStatusMap = new HashMap<Long,String>(){{
  	 put(1l,"正在审核中");
  	 put(2l,"已发布");
  	 put(3l,"放弃申请");
  	 put(4l,"超时退回");
   }};
   
   /*动态生成模块ID*/
   public static final String PERSONWELFARENODEID = "automodel";
   
   public static final String PERSONWELFARETABLE = "pw_table";
   /*模块属性标识*/
   public static final String MODE_TYPE_AUTO = "auto"; //自动化表单
   public static final String MODE_TYPE_CUSTOM = "custom";//自定义
   
   /*单选与多选*/	
   public static final String SELECTIVE_CHECKBOX ="checkbox";
   public static final String SELECTIVE_RADIO ="radio";
   
   /*操作提示*/
   public static final String NOBODY="*用户不存在";
   
   public static final String LOGIN_ERROR="*用户名或密码错误";
   
   public static final String SYS_ERROR="*系统故障，请稍后再试";
   
   public static final String CODE_ERROR="*验证码错误";
   
   public static final String OPERATION_FAIL="操作失败";
   
   public static final String OPERATION_RELEVANCE="信息被引用,无法进行此操作!";
   
   public static final String OPERATION_SUCCESS="操作成功";
   
   public static final String ASSIGNTASK="此任务已被其他管理人员签收";
   
   public static final String ALREADYSELECT="已选定";
   
   public static final String MODELADDSECCUSS="配置模块到属于你的任一角色才可看到";
   
   public static final int width = 90;//定义图片的width
   
   public static final int height = 20;//定义图片的height
	
   public static final int codeCount = 4;//定义图片上显示验证码的个数
   
   public static final int xx = 15;
	
   public static final int fontHeight = 18;
	
   public static final int codeY = 16;
   
   public static final char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
   
   public String rename(String fileName) {  
	      String body="";  
	      String ext="";  
	      Date date = new Date();  
	      int pot=fileName.lastIndexOf(".");  
	      if(pot!=-1){  
	          body= date.getTime() +"";  
	          ext=fileName.substring(pot);  
	      }else{  
	          body=(new Date()).getTime()+"";  
	          ext="";  
	      }  
	      String newName=body+ext;  
	      return newName;  
	}  
   /**
    * 读取properties文件方法
    * @param key
    * @return
    */
   public  String getString(String key,String path){
	   Properties p  =new Properties();
	   InputStream inputStream =getClass().getResourceAsStream(path);
	   try{
		   p.load(inputStream);
		   inputStream.close();
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return p.getProperty(key);
   }
   public static Map<String,String> modelMap = new HashMap<String,String>();
   
}
