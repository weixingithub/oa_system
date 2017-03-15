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

/*Table structure for table `w_layout` */

DROP TABLE IF EXISTS `w_layout`;

CREATE TABLE `w_layout` (
  `layout_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(255) DEFAULT NULL,
  `layout_edit_url` varchar(255) DEFAULT NULL,
  `layout_page_url` varchar(255) DEFAULT NULL,
  `layout_thumb_pic` varchar(255) DEFAULT NULL,
  `layout_level` int(4) DEFAULT NULL,
  `layout_name` varchar(255) DEFAULT NULL,
  `layout_intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`layout_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `w_layout` */

insert  into `w_layout`(`layout_id`,`create_time`,`layout_edit_url`,`layout_page_url`,`layout_thumb_pic`,`layout_level`,`layout_name`,`layout_intro`) values (1,'2017-01-12 17:50:39','/account/portals/layout/library/layout1','layout1.jsp','http://192.168.1.129:80/uploadfolder/layout/20170112//1484214552478.jpg',1,'页面一','(6*6)(8*4)(4*4*4)(12)(4*4*4)(8*4)'),(2,'2017-01-12 17:52:52','/account/portals/layout/library/layout2','layout2.jsp','http://192.168.1.129:80/uploadfolder/layout/20170112//1484214745401.jpg',2,'页面二','(4*8)'),(3,'2017-01-12 17:53:18','/account/portals/layout/library/layout3','layout3.jsp','http://192.168.1.129:80/uploadfolder/layout/20170112//1484214789205.jpg',3,'页面三','(12)');

/*Table structure for table `w_plugin` */

DROP TABLE IF EXISTS `w_plugin`;

CREATE TABLE `w_plugin` (
  `plugin_id` int(11) NOT NULL AUTO_INCREMENT,
  `plugin_intro` varchar(255) DEFAULT NULL,
  `plugin_name` varchar(255) DEFAULT NULL,
  `plugin_pic` varchar(255) DEFAULT NULL,
  `plugin_url` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `plugin_type` int(4) DEFAULT NULL,
  PRIMARY KEY (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `w_plugin` */

insert  into `w_plugin`(`plugin_id`,`plugin_intro`,`plugin_name`,`plugin_pic`,`plugin_url`,`author`,`create_time`,`plugin_type`) values (1,'进行网站登录和后台登录的入口，以及前台账号注册的插件。','登录入口','http://192.168.1.129:80/uploadfolder/layout/20170109//1483927901410.jpg','','王青松','2017-01-11 17:39:08',6),(2,'模块的快捷方式入口','快捷方式','http://192.168.1.129:80/uploadfolder/layout/20170109//1483927910192.jpg','ShortcutKey(Data,columnDiv);','王青松','2017-01-11 17:39:01',8),(3,'单模块信息','单模块展示','http://192.168.1.129:80/uploadfolder/layout/20170109//1483927892898.jpg','Survey(Data,columnDiv);','王青松','2017-01-25 08:55:44',1),(4,'多模块显示栏','水平列表','http://192.168.1.129:80/uploadfolder/layout/20170109//1483928058242.jpg','H_listMenu(Data,columnDiv);','江斌','2017-01-11 17:38:51',1),(5,'内容显示','垂直列表','http://192.168.1.129:80/uploadfolder/layout/20170109//1483928125505.jpg','V_listMenu(Data,columnDiv);','江斌','2017-01-11 17:38:47',1),(6,'图片效果显示','图片显示','http://192.168.1.129:80/uploadfolder/layout/20170109//1483928266950.jpg','EventPhotos(Data,columnDiv);','江斌','2017-01-13 16:23:13',4),(7,'图片切换','轮播图','http://192.168.1.129:80/uploadfolder/layout/20170109//1483928606699.jpg','Carousel(Data,columnDiv);','李强','2017-01-11 17:38:27',3),(8,'问题调查统计','问卷调查','http://192.168.1.129:80/uploadfolder/layout/20170109//1483928692397.jpg','','李强','2017-01-11 17:38:41',5),(9,'留言显示展示','留言展示','http://192.168.1.129:80/uploadfolder/layout/20170109//1483928813412.jpg','LeaveMessage(Data,columnDiv);','李强','2017-01-11 17:38:13',1),(10,'头条信息展示','头条展示','http://192.168.1.129:80/uploadfolder/layout/20170109//1483929359844.jpg','','李强','2017-01-11 17:38:05',1),(11,'搜索站内信息','搜索插件','http://192.168.1.129:80/uploadfolder/layout/20170109//1483929481595.jpg','','江斌','2017-01-11 17:37:58',5),(12,'活动接口','活动入口','http://192.168.1.129:80/uploadfolder/layout/20170109//1483929718748.jpg','','李强','2017-01-11 17:37:49',8),(13,'人物风采展示','人物展示','http://192.168.1.129:80/uploadfolder/layout/20170109//1483930271057.jpg','WomenLeader(Data,columnDiv);','王青松','2017-01-11 17:36:52',3),(14,'','横向菜单','http://192.168.1.129:80/uploadfolder/layout/20170119//1484787379176.jpg','H_Nav(Data,columnDiv);','江斌','2017-01-19 08:56:21',1),(15,'','侧边菜单','http://192.168.1.129:80/uploadfolder/layout/20170119//1484787370244.jpg','V_Nav(Data,columnDiv);','李强','2017-01-19 08:56:12',1),(16,'','文本列表','http://192.168.1.129:80/uploadfolder/layout/20170119//1484787356702.jpg','','王青松','2017-01-19 08:55:58',1),(17,'','图文列表','http://192.168.1.129:80/uploadfolder/layout/20170119//1484787328480.jpg','','王青松','2017-01-19 08:55:30',9),(18,'','内容显示','http://192.168.1.129:80/uploadfolder/layout/20170119//1484787312775.jpg','','李强','2017-01-19 08:55:14',9);

/*Table structure for table `w_themes` */

DROP TABLE IF EXISTS `w_themes`;

CREATE TABLE `w_themes` (
  `theme_id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `style_colour` int(4) DEFAULT NULL,
  `theme_css` varchar(255) DEFAULT NULL,
  `theme_intro` varchar(255) DEFAULT NULL,
  `theme_name` varchar(255) DEFAULT NULL,
  `theme_pic` varchar(255) DEFAULT NULL,
  `theme_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`theme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `w_themes` */

insert  into `w_themes`(`theme_id`,`author`,`create_time`,`style_colour`,`theme_css`,`theme_intro`,`theme_name`,`theme_pic`,`theme_url`) values (1,'王青松','2017-02-16 11:42:17',3,'themes/style1/css/style.css','粉红的框架结构','粉红','http://localhost:80/uploadfolder/layout/20170113//1484269662296.jpg','/themes/style1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
