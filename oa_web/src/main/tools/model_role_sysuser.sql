/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.10 : Database - oaplatform
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oaplatform` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oaplatform`;

/*Table structure for table `oa_model` */

DROP TABLE IF EXISTS `oa_model`;

CREATE TABLE `oa_model` (
  `model_id` int(11) NOT NULL AUTO_INCREMENT,
  `model_name` varchar(255) DEFAULT NULL,
  `node_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `is_parent` varchar(255) DEFAULT NULL,
  `model_frame` varchar(255) DEFAULT NULL,
  `model_url` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_auto` int(11) DEFAULT '0',
  `check_role_id` varchar(255) DEFAULT NULL,
  `p_auto` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `stage_url` varchar(255) DEFAULT NULL,
  `is_check_model` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

/*Data for the table `oa_model` */

insert  into `oa_model`(`model_id`,`model_name`,`node_id`,`parent_id`,`is_parent`,`model_frame`,`model_url`,`remark`,`is_auto`,`check_role_id`,`p_auto`,`user_id`,`stage_url`,`is_check_model`) values (1,'系统管理','1','0','true','false','','',0,NULL,NULL,NULL,NULL,0),(2,'用户管理','2','1','false','false','/oa/sysuser/findSysUserPage','对于管理员用户的增删改查操作，并进行权限与机构的配置',0,NULL,NULL,NULL,NULL,0),(3,'机构管理','3','1','false','false','/oa/org/getOrgDetail','对机构或群组进行操作',0,NULL,NULL,NULL,NULL,0),(4,'角色管理','4','1','false','false','/oa/role/findRolePage','对系统角色做增删改查的操作，并进行功能模块的配置',0,NULL,NULL,NULL,NULL,0),(5,'模块管理','5','1','false','false','/oa/model/getModelDetail','对功能模块进行管理',0,NULL,NULL,NULL,NULL,0),(10,'个人办公','10','0','true','false','','',0,NULL,NULL,NULL,NULL,0),(11,'待办任务','11','10','false','false','/oa/pwelfare/todotask','还未办理的任务',0,NULL,NULL,NULL,NULL,0),(12,'已办任务','12','10','false','false','/oa/pwelfare/donetask','已经办理的任务',0,NULL,NULL,NULL,NULL,0),(13,'超时任务','13','10','false','false','/oa/pwelfare/overtimetask','过期的任务展示',0,NULL,NULL,NULL,NULL,0),(14,'社区管理','14','0','true','false','','进行社区的人口户籍管理',0,NULL,NULL,NULL,NULL,0),(15,'户籍管理','15','14','false','false','/oa/population/findFamilyPage','管理户籍',0,NULL,NULL,NULL,NULL,0),(16,'人口管理','16','14','false','false','/oa/person/getPersonPage','人口信息',0,NULL,NULL,NULL,NULL,0),(17,'车辆管理','17','14','false','false','/oa/vehicle/getPersonVehiclePage','车辆信息管理',0,NULL,NULL,NULL,NULL,0),(18,'计生管理','18','14',NULL,'false','/oa/planning/getPersonPage','计划生育管理信息 ',0,NULL,NULL,NULL,NULL,0),(19,'劳保管理','19','14',NULL,'false','/oa/labour/getPersonPage','劳动保障管理信息',0,NULL,NULL,NULL,NULL,0),(20,'民政管理','20','14',NULL,'false','/oa/civil/getPersonPage','民政管理信息',0,NULL,NULL,NULL,NULL,0),(21,'综治管理','21','14',NULL,'false','/oa/stability/getPersonPage','综治管理信息',0,NULL,NULL,NULL,NULL,0),(22,'日志管理','22','0','true','false','','日志信息的管理',0,NULL,NULL,NULL,NULL,0),(23,'日志信息','23','22',NULL,'false','/oa/logger/findloggerPage','日志信息的查询展示',0,NULL,NULL,NULL,NULL,0),(24,'统计管理','24','0','true','false','','进行各个模块的数据统计',0,NULL,NULL,NULL,NULL,0),(26,'信息统计','26','24','','false','','',0,'','false',1,'',0),(27,'发布统计','27','24',NULL,'false','','',0,'','false',1,'',0),(46,'发布系统','46','0','true','false','','',0,'','false',1,'',0),(47,'微信服务','47','46','true','false','','',0,'','false',1,'',0),(48,'微信群发','48','47','','false','','群发消息保存的素材和文章',0,'','false',1,'',0),(49,'微博服务','49','46','true','false','','',0,'','false',1,'',0),(50,'微博群发','50','49','','false','','',0,'','false',1,'',0),(55,'网站服务','55','46','true','false','','',0,'','false',1,'',0),(56,'网站管理','56','55','','false','/oa/website/getPageWebsite','',0,'','false',1,'',0),(58,'消息管理','58','46','true','false','','',0,'','false',1,'',0),(59,'消息结果','59','58','','false','','',0,'','false',1,'',0),(63,'主题管理','63','55',NULL,'false','/oa/themes/findThemesPage','',0,'','false',1,'',0),(64,'插件管理','64','55',NULL,'false','/oa/plugin/findPluginPage','',0,'','false',1,'',0),(66,'布局管理','66','55',NULL,'false','/oa/layout/findLayoutPage','',0,'','false',1,'',0),(68,'菜单管理','68','55',NULL,'false','/oa/menu/findMenuPage','',0,'','false',1,'',0),(70,'微信账号管理','70','47',NULL,'false','/oa/wechat/getPageWechat','',0,'','false',1,'',0),(71,'微博账号管理','71','49',NULL,'false','/oa/weibo/getPageWeibo','',0,'','false',1,'',0),(74,'信息管理','74','0','true','false','','',1,'','false',1,'',0),(75,'资料中心','75','74','','false','/oa/pwelfare/modelvariable75','',1,'2','true',1,'/message/getMessagePage',0);

/*Table structure for table `oa_role` */

DROP TABLE IF EXISTS `oa_role`;

CREATE TABLE `oa_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `is_parent` varchar(255) DEFAULT NULL COMMENT '判断是否是父节点 true  false',
  `remark` varchar(255) DEFAULT NULL COMMENT '角色备注',
  `create_time` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `oa_role` */

insert  into `oa_role`(`role_id`,`node_id`,`parent_id`,`role_name`,`is_parent`,`remark`,`create_time`,`user_id`,`org_id`) values (2,'2','0','超级管理员','true','用户最大权限使用者','2016-12-02 17:06:49','1',''),(3,'3','2','普通管理员','false','普通管理员','2016-12-08 08:49:33','2',''),(6,'6','0','系统管理员','false','系统管理员','2017-02-17 11:38:08','1',''),(7,'7','0','社区管理员','false','社区管理员','2017-02-17 11:38:59','1','');

/*Table structure for table `oa_sysuser` */

DROP TABLE IF EXISTS `oa_sysuser`;

CREATE TABLE `oa_sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` varchar(255) DEFAULT NULL COMMENT 'creator 创建者 none未配置节点id的管理员',
  `parent_id` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  `user_sex` int(11) DEFAULT NULL COMMENT '1 女 2 男',
  `user_email` varchar(255) DEFAULT NULL,
  `user_createtime` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `user_phone` varchar(255) DEFAULT NULL,
  `user_org_id` varchar(255) DEFAULT NULL,
  `is_parent` varchar(255) DEFAULT NULL,
  `device_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `oa_sysuser` */

insert  into `oa_sysuser`(`id`,`node_id`,`parent_id`,`real_name`,`user_age`,`user_name`,`user_pwd`,`user_sex`,`user_email`,`user_createtime`,`user_phone`,`user_org_id`,`is_parent`,`device_token`) values (1,'creator','0','创建者',100,'creator','1000:e4a3a1ce39c7d15d83219ad730946f9b4b55fac4c57b18a0:103df2c1a94f39759016698c6a4882d0ce613d9e847b891c',0,'creator@creator.com','1997-1-1','12345678901',NULL,NULL,NULL),(2,'2','creator','魏歆',27,'admin','1000:e4a3a1ce39c7d15d83219ad730946f9b4b55fac4c57b18a0:103df2c1a94f39759016698c6a4882d0ce613d9e847b891c',2,'763328075@qq.com','2016-12-02 17:07:24','13572434567','1',NULL,'');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
