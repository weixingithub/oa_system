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

/*Table structure for table `pw_table` */

DROP TABLE IF EXISTS `pw_table`;

CREATE TABLE `pw_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activiti_user` varchar(255) DEFAULT NULL,
  `article_tag` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  `flow_status` varchar(255) DEFAULT NULL,
  `model_id` varchar(255) NOT NULL DEFAULT '',
  `org_id` varchar(255) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `pub_platform` int(11) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `finish_time` varchar(255) DEFAULT NULL,
  `img_src` varchar(255) DEFAULT NULL,
  `original_links` varchar(255) DEFAULT NULL,
  `sign_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`model_id`),
  KEY `statusIndex` (`flow_status`),
  KEY `orgIndex` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50500 PARTITION BY LIST  COLUMNS(model_id)
(PARTITION p0 VALUES IN ('all') ENGINE = InnoDB,
 PARTITION p75 VALUES IN ('75') ENGINE = InnoDB) */;

/*Data for the table `pw_table` */

/*Table structure for table `t_pw_content` */

DROP TABLE IF EXISTS `t_pw_content`;

CREATE TABLE `t_pw_content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `model_id` varchar(255) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_pw_content` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
