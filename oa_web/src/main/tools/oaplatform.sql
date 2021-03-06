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

/*Table structure for table `act_evt_log` */

DROP TABLE IF EXISTS `act_evt_log`;

CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_evt_log` */

/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_bytearray` */

/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_property` */

insert  into `act_ge_property`(`NAME_`,`VALUE_`,`REV_`) values ('next.dbid','402501',162),('schema.history','create(5.16)',1),('schema.version','5.16',1);

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_actinst` */

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_attachment` */

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_comment` */

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_detail` */

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_identitylink` */

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_procinst` */

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `CLAIM_TIME_` datetime DEFAULT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_taskinst` */

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_varinst` */

/*Table structure for table `act_id_group` */

DROP TABLE IF EXISTS `act_id_group`;

CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_group` */

insert  into `act_id_group`(`ID_`,`REV_`,`NAME_`,`TYPE_`) values ('2',1,'超级管理员',NULL),('3',1,'普通管理员',NULL),('6',1,'系统管理员',NULL),('7',1,'社区管理员',NULL);

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_info` */

/*Table structure for table `act_id_membership` */

DROP TABLE IF EXISTS `act_id_membership`;

CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_membership` */

insert  into `act_id_membership`(`USER_ID_`,`GROUP_ID_`) values ('admin','2'),('admin','3'),('admin','6'),('admin','7');

/*Table structure for table `act_id_user` */

DROP TABLE IF EXISTS `act_id_user`;

CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_user` */

insert  into `act_id_user`(`ID_`,`REV_`,`FIRST_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values ('admin',1,'魏','歆',NULL,NULL,NULL);

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` datetime NOT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_deployment` */

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_model` */

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_procdef` */

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_event_subscr` */

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_execution` */

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_identitylink` */

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_job` */

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` datetime NOT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_task` */

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_variable` */

/*Table structure for table `c_family` */

DROP TABLE IF EXISTS `c_family`;

CREATE TABLE `c_family` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_amount` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `current_address` varchar(255) DEFAULT NULL,
  `current_place` varchar(255) DEFAULT NULL,
  `double_female_house_holds` varchar(255) DEFAULT NULL,
  `enjoy_amount` varchar(255) DEFAULT NULL,
  `fam_p_c_d` varchar(255) DEFAULT NULL,
  `family_demand` varchar(255) DEFAULT NULL,
  `have_two_children` varchar(255) DEFAULT NULL,
  `household_type` varchar(255) DEFAULT NULL,
  `house_holder_contact` varchar(255) DEFAULT NULL,
  `householder_id` varchar(255) DEFAULT NULL,
  `house_holder_name` varchar(255) DEFAULT NULL,
  `low_income` varchar(255) DEFAULT NULL,
  `low_income_audit` varchar(255) DEFAULT NULL,
  `low_income_numbe` varchar(255) DEFAULT NULL,
  `low_income_state` varchar(255) DEFAULT NULL,
  `low_income_type` varchar(255) DEFAULT NULL,
  `low_number` varchar(255) DEFAULT NULL,
  `no_poor_family_state` varchar(255) DEFAULT NULL,
  `one_child_family` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `org_pid` varchar(255) DEFAULT NULL,
  `other_place` varchar(255) DEFAULT NULL,
  `pen_p_c_d` varchar(255) DEFAULT NULL,
  `place_state` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) DEFAULT NULL,
  `poor_family_state` varchar(255) DEFAULT NULL,
  `poor_family_type` varchar(255) DEFAULT NULL,
  `pregnant_check` varchar(255) DEFAULT NULL,
  `provision_service` varchar(255) DEFAULT NULL,
  `registry_address` varchar(255) DEFAULT NULL,
  `registry_place` varchar(255) DEFAULT NULL,
  `single_parent` varchar(255) DEFAULT NULL,
  `spouse_name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `vaccine_check` varchar(255) DEFAULT NULL,
  `file_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9uhdmnsyvm00m3numyeq0ei1v` (`file_id`),
  KEY `FK_oj6vphbnl0102vujmuavyt3o0` (`ground_id`),
  KEY `FK_5phhc0hh72rrraa0iic01hslg` (`house_id`),
  CONSTRAINT `FK_5phhc0hh72rrraa0iic01hslg` FOREIGN KEY (`house_id`) REFERENCES `c_house` (`id`),
  CONSTRAINT `FK_9uhdmnsyvm00m3numyeq0ei1v` FOREIGN KEY (`file_id`) REFERENCES `t_file` (`id`),
  CONSTRAINT `FK_oj6vphbnl0102vujmuavyt3o0` FOREIGN KEY (`ground_id`) REFERENCES `c_ground` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_family` */

/*Table structure for table `c_familyplanning` */

DROP TABLE IF EXISTS `c_familyplanning`;

CREATE TABLE `c_familyplanning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annualnumber` varchar(255) DEFAULT NULL,
  `birthregistration` varchar(255) DEFAULT NULL,
  `childgrants` varchar(255) DEFAULT NULL,
  `childgrantslqd` varchar(255) DEFAULT NULL,
  `childsubsidyamount` varchar(255) DEFAULT NULL,
  `contraceptiontime` varchar(255) DEFAULT NULL,
  `contraceptive` varchar(255) DEFAULT NULL,
  `getsubsidiestime` varchar(255) DEFAULT NULL,
  `marriagechangetime` varchar(255) DEFAULT NULL,
  `marriagestatus` varchar(255) DEFAULT NULL,
  `marriagetime` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `obstetricalcard` varchar(255) DEFAULT NULL,
  `onlyamounttime` varchar(255) DEFAULT NULL,
  `onlychildstatus` varchar(255) DEFAULT NULL,
  `onlychildstatuslqd` varchar(255) DEFAULT NULL,
  `pregnantnumber` varchar(255) DEFAULT NULL,
  `richardiii` varchar(255) DEFAULT NULL,
  `socialcompensationfee` varchar(255) DEFAULT NULL,
  `subsidiesstate` varchar(255) DEFAULT NULL,
  `twotires` varchar(255) DEFAULT NULL,
  `vaccinenumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_familyplanning` */

/*Table structure for table `c_ground` */

DROP TABLE IF EXISTS `c_ground`;

CREATE TABLE `c_ground` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `arable_area` varchar(255) DEFAULT NULL,
  `compensation` varchar(255) DEFAULT NULL,
  `farmlandto_forest_area` varchar(255) DEFAULT NULL,
  `subsidy_amount` varchar(255) DEFAULT NULL,
  `woodland_area` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_ground` */

/*Table structure for table `c_house` */

DROP TABLE IF EXISTS `c_house`;

CREATE TABLE `c_house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `construction_area` varchar(255) DEFAULT NULL,
  `hose_type` varchar(255) DEFAULT NULL,
  `house_datatime` varchar(255) DEFAULT NULL,
  `house_disaster_nature` varchar(255) DEFAULT NULL,
  `house_facility` varchar(255) DEFAULT NULL,
  `house_hall` varchar(255) DEFAULT NULL,
  `house_master` varchar(255) DEFAULT NULL,
  `house_master_phone` varchar(255) DEFAULT NULL,
  `house_nature` varchar(255) DEFAULT NULL,
  `house_premise` varchar(255) DEFAULT NULL,
  `house_room` varchar(255) DEFAULT NULL,
  `house_toilet` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_house` */

/*Table structure for table `c_laborinsurance` */

DROP TABLE IF EXISTS `c_laborinsurance`;

CREATE TABLE `c_laborinsurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employmentnature` varchar(255) DEFAULT NULL,
  `employmentstatus` varchar(255) DEFAULT NULL,
  `endowmentinsurance` varchar(255) DEFAULT NULL,
  `enjoymenttime` varchar(255) DEFAULT NULL,
  `enjoypettyloan` varchar(255) DEFAULT NULL,
  `jobintension` varchar(255) DEFAULT NULL,
  `medicalinsurance` varchar(255) DEFAULT NULL,
  `medicaltype` varchar(255) DEFAULT NULL,
  `nationallowances` varchar(255) DEFAULT NULL,
  `officename` varchar(255) DEFAULT NULL,
  `pensionlqd` varchar(255) DEFAULT NULL,
  `pensionreturn` varchar(255) DEFAULT NULL,
  `pensiontype` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `retirepension` varchar(255) DEFAULT NULL,
  `socialsubsidies` varchar(255) DEFAULT NULL,
  `unemployregistration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_laborinsurance` */

/*Table structure for table `c_person` */

DROP TABLE IF EXISTS `c_person`;

CREATE TABLE `c_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthdate` varchar(255) DEFAULT NULL,
  `bloodtype` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `citywoman` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `creatime` varchar(255) DEFAULT NULL,
  `current_address` varchar(255) DEFAULT NULL,
  `economicdistribution` varchar(255) DEFAULT NULL,
  `grid` varchar(255) DEFAULT NULL,
  `householdid` varchar(255) DEFAULT NULL,
  `idnumber` varchar(255) DEFAULT NULL,
  `jurisdiction` varchar(255) DEFAULT NULL,
  `marital_status` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `oldname` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `org_pid` varchar(255) DEFAULT NULL,
  `per_p_c_d` varchar(255) DEFAULT NULL,
  `person_url` varchar(255) DEFAULT NULL,
  `political` varchar(255) DEFAULT NULL,
  `poverty` varchar(255) DEFAULT NULL,
  `ralation` varchar(255) DEFAULT NULL,
  `registry_place` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `residenttype` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `tall` varchar(255) DEFAULT NULL,
  `uniform_identification` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `village_household` varchar(255) DEFAULT NULL,
  `family_id` int(11) DEFAULT NULL,
  `fplanning_id` int(11) DEFAULT NULL,
  `file_id` int(11) DEFAULT NULL,
  `labor_id` int(11) DEFAULT NULL,
  `civil_id` int(11) DEFAULT NULL,
  `stab_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2q4s60nlpo291wxbe17bauji2` (`family_id`),
  KEY `FK_qqpays4jwikede222pebi7v6t` (`fplanning_id`),
  KEY `FK_m97q39n6lg3aop5uhfbjlde6q` (`file_id`),
  KEY `FK_60oh90plt0jepxua1gg4m6xx4` (`labor_id`),
  KEY `FK_p8b0v527ibwt8x7jscba6cfkc` (`civil_id`),
  KEY `FK_orf1sy9obg20mumyn5capcu16` (`stab_id`),
  CONSTRAINT `FK_orf1sy9obg20mumyn5capcu16` FOREIGN KEY (`stab_id`) REFERENCES `c_stability` (`id`),
  CONSTRAINT `FK_2q4s60nlpo291wxbe17bauji2` FOREIGN KEY (`family_id`) REFERENCES `c_family` (`id`),
  CONSTRAINT `FK_60oh90plt0jepxua1gg4m6xx4` FOREIGN KEY (`labor_id`) REFERENCES `c_laborinsurance` (`id`),
  CONSTRAINT `FK_m97q39n6lg3aop5uhfbjlde6q` FOREIGN KEY (`file_id`) REFERENCES `t_file` (`id`),
  CONSTRAINT `FK_p8b0v527ibwt8x7jscba6cfkc` FOREIGN KEY (`civil_id`) REFERENCES `c_populationcivil` (`id`),
  CONSTRAINT `FK_qqpays4jwikede222pebi7v6t` FOREIGN KEY (`fplanning_id`) REFERENCES `c_familyplanning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_person` */

/*Table structure for table `c_populationcivil` */

DROP TABLE IF EXISTS `c_populationcivil`;

CREATE TABLE `c_populationcivil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activearmy` varchar(255) DEFAULT NULL,
  `checkamount` varchar(255) DEFAULT NULL,
  `deformitystate` varchar(255) DEFAULT NULL,
  `disabilitybenefits` varchar(255) DEFAULT NULL,
  `disabilitynumber` varchar(255) DEFAULT NULL,
  `disabled` varchar(255) DEFAULT NULL,
  `disabledmoney` varchar(255) DEFAULT NULL,
  `disablitygrade` varchar(255) DEFAULT NULL,
  `elderly` varchar(255) DEFAULT NULL,
  `emptynester` varchar(255) DEFAULT NULL,
  `modelworkers` varchar(255) DEFAULT NULL,
  `oldarmy` varchar(255) DEFAULT NULL,
  `orphanmoney` varchar(255) DEFAULT NULL,
  `orphanschildren` varchar(255) DEFAULT NULL,
  `recadres` varchar(255) DEFAULT NULL,
  `receivegrantsorphan` varchar(255) DEFAULT NULL,
  `subsidies` varchar(255) DEFAULT NULL,
  `subsidiesmoney` varchar(255) DEFAULT NULL,
  `veteran` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_populationcivil` */

/*Table structure for table `c_stability` */

DROP TABLE IF EXISTS `c_stability`;

CREATE TABLE `c_stability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `communitycorrection` varchar(255) DEFAULT NULL,
  `custody_place` varchar(255) DEFAULT NULL,
  `focus_petitioners` varchar(255) DEFAULT NULL,
  `four_situation` varchar(255) DEFAULT NULL,
  `illegalfund` varchar(255) DEFAULT NULL,
  `involved_in_cults` varchar(255) DEFAULT NULL,
  `nuclearpersonnel` varchar(255) DEFAULT NULL,
  `recidivist` varchar(255) DEFAULT NULL,
  `rectify_end_date` varchar(255) DEFAULT NULL,
  `rectifyid` varchar(255) DEFAULT NULL,
  `rectify_start_date` varchar(255) DEFAULT NULL,
  `rectify_type` varchar(255) DEFAULT NULL,
  `reeducationreform` varchar(255) DEFAULT NULL,
  `three_situation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_stability` */

/*Table structure for table `c_vehicleinformation` */

DROP TABLE IF EXISTS `c_vehicleinformation`;

CREATE TABLE `c_vehicleinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `licenseplatenumber` varchar(255) DEFAULT NULL,
  `owners` varchar(255) DEFAULT NULL,
  `person_contact` varchar(255) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `vehicletype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `c_vehicleinformation` */

/*Table structure for table `model_role` */

DROP TABLE IF EXISTS `model_role`;

CREATE TABLE `model_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_flag` varchar(20) DEFAULT NULL COMMENT '角色权限数据标记 访问2 添加 3 编辑5 删除7  查看11   配置41',
  `data_type` varchar(10) DEFAULT NULL COMMENT '群组资源g  用户资源u',
  `model_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `is_parent` varchar(20) DEFAULT 'false',
  `is_check_role` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_3oomuh5u72re43drn6b5nd0s8` (`model_id`),
  KEY `FK_tksiww941oghgoq7do84i0s67` (`role_id`),
  CONSTRAINT `FK_3oomuh5u72re43drn6b5nd0s8` FOREIGN KEY (`model_id`) REFERENCES `oa_model` (`model_id`),
  CONSTRAINT `FK_tksiww941oghgoq7do84i0s67` FOREIGN KEY (`role_id`) REFERENCES `oa_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `model_role` */

insert  into `model_role`(`id`,`data_flag`,`data_type`,`model_id`,`role_id`,`is_parent`,`is_check_role`) values (1,'2,3,5,7,11,41','u',2,2,'false',0),(2,NULL,NULL,1,2,'true',0),(3,'2,3,5,7,11,41','u',3,2,'false',1),(23,'2,3,5,7,11,41','u',75,2,'false',0),(24,NULL,NULL,74,2,'true',0),(35,NULL,NULL,10,3,'true',0),(36,'2,3,5,7,11,41',NULL,11,3,'false',0),(37,'2,3,5,7,11,41',NULL,12,3,'false',0),(38,'2,3,5,7,11,41',NULL,13,3,'false',0),(39,NULL,NULL,1,6,'true',0),(40,'2,3,5,7,11,41',NULL,2,6,'false',0),(41,'2,3,5,7,11,41',NULL,3,6,'false',0),(42,'2,3,5,7,11,41',NULL,4,6,'false',0),(43,'2,3,5,7,11,41',NULL,5,6,'false',0),(44,NULL,NULL,22,6,'true',0),(45,'2,3,5,7,11,41',NULL,23,6,'false',0),(46,NULL,NULL,47,6,'true',0),(47,NULL,NULL,46,6,'true',0),(48,NULL,NULL,48,6,'false',0),(49,NULL,NULL,49,6,'true',0),(50,NULL,NULL,50,6,'true',0),(51,NULL,NULL,55,6,'true',0),(52,'2,3,5,7,11,41',NULL,56,6,'false',0),(53,NULL,NULL,58,6,'true',0),(54,NULL,NULL,27,6,'false',0),(55,NULL,NULL,24,6,'true',0),(56,NULL,NULL,26,6,'false',0),(57,NULL,NULL,59,6,'false',0),(58,'2,3,5,7,11,41',NULL,63,6,'false',0),(59,'2,3,5,7,11,41',NULL,64,6,'false',0),(60,'2,3,5,7,11,41',NULL,66,6,'false',0),(61,'2,3,5,7,11,41',NULL,68,6,'false',0),(62,'2,3,5,7,11,41',NULL,70,6,'false',0),(63,'2,3,5,7,11,41',NULL,71,6,'false',0),(64,NULL,NULL,14,7,'true',0),(65,'2,3,5,7,11,41',NULL,15,7,'false',0),(66,'2,3,5,7,11,41',NULL,16,7,'false',0),(67,'2,3,5,7,11,41',NULL,17,7,'false',0),(68,'2,3,5,7,11,41',NULL,18,7,'false',0),(69,'2,3,5,7,11,41',NULL,19,7,'false',0),(70,'2,3,5,7,11,41',NULL,20,7,'false',0),(71,'2,3,5,7,11,41',NULL,21,7,'false',0);

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

/*Table structure for table `oa_org` */

DROP TABLE IF EXISTS `oa_org`;

CREATE TABLE `oa_org` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `node_id` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `is_parent` varchar(255) DEFAULT NULL,
  `org_num` varchar(255) DEFAULT NULL,
  `org_address` varchar(255) DEFAULT NULL,
  `org_remark` varchar(2000) DEFAULT NULL,
  `org_p_c_d` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `oa_org` */

insert  into `oa_org`(`org_id`,`node_id`,`org_name`,`parent_id`,`is_parent`,`org_num`,`org_address`,`org_remark`,`org_p_c_d`) values (1,'1','银河电信总部','0','true','000101','高新区银河科技园A座1507','','650029,660712,673610'),(2,'2','宝鸡分公司','1','false','000102','','','650029,660714,673628');

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

/*Table structure for table `org_sysuser` */

DROP TABLE IF EXISTS `org_sysuser`;

CREATE TABLE `org_sysuser` (
  `sys_id` int(11) NOT NULL,
  `org_id` int(11) NOT NULL,
  KEY `FK_8fx0y8f3nf7ix9lvjc9pbltjc` (`org_id`),
  KEY `FK_q1ou9tqifwe4exohm0uth467h` (`sys_id`),
  CONSTRAINT `FK_q1ou9tqifwe4exohm0uth467h` FOREIGN KEY (`sys_id`) REFERENCES `oa_sysuser` (`id`),
  CONSTRAINT `FK_8fx0y8f3nf7ix9lvjc9pbltjc` FOREIGN KEY (`org_id`) REFERENCES `oa_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org_sysuser` */

insert  into `org_sysuser`(`sys_id`,`org_id`) values (2,1);

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

/*Table structure for table `role_sysuser` */

DROP TABLE IF EXISTS `role_sysuser`;

CREATE TABLE `role_sysuser` (
  `sys_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK_67482rxfypb39pcdnjsg00wdr` (`role_id`),
  KEY `FK_s6h1f71h7egu9juottghtjato` (`sys_id`),
  CONSTRAINT `FK_s6h1f71h7egu9juottghtjato` FOREIGN KEY (`sys_id`) REFERENCES `oa_sysuser` (`id`),
  CONSTRAINT `FK_67482rxfypb39pcdnjsg00wdr` FOREIGN KEY (`role_id`) REFERENCES `oa_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_sysuser` */

insert  into `role_sysuser`(`sys_id`,`role_id`) values (2,2),(2,3),(2,6),(2,7);

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `jobid` bigint(20) NOT NULL AUTO_INCREMENT,
  `beanclass` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `cronexpression` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `isconcurrent` varchar(255) DEFAULT NULL,
  `jobgroup` varchar(255) DEFAULT NULL,
  `jobname` varchar(255) DEFAULT NULL,
  `jobstatus` varchar(255) DEFAULT NULL,
  `message_kind` int(11) DEFAULT NULL,
  `methodname` varchar(255) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `receiverid` bigint(20) DEFAULT NULL,
  `springid` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `updatetime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `schedule_job` */

/*Table structure for table `t_city` */

DROP TABLE IF EXISTS `t_city`;

CREATE TABLE `t_city` (
  `cityid` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(50) DEFAULT NULL,
  `city_sort` int(11) DEFAULT NULL,
  `proid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cityid`)
) ENGINE=InnoDB AUTO_INCREMENT=660775 DEFAULT CHARSET=utf8;

/*Data for the table `t_city` */

insert  into `t_city`(`cityid`,`city_name`,`city_sort`,`proid`) values (660420,'市辖区',NULL,650003),(660421,'市辖县',NULL,650003),(660422,'市辖区',NULL,650004),(660423,'市辖县',NULL,650004),(660424,'石家庄市',NULL,650005),(660425,'唐山市',NULL,650005),(660426,'秦皇岛市',NULL,650005),(660427,'邯郸市',NULL,650005),(660428,'邢台市',NULL,650005),(660429,'保定市',NULL,650005),(660430,'张家口市',NULL,650005),(660431,'承德市',NULL,650005),(660432,'沧州市',NULL,650005),(660433,'廊坊市',NULL,650005),(660434,'衡水市',NULL,650005),(660435,'太原市',NULL,650006),(660436,'大同市',NULL,650006),(660437,'阳泉市',NULL,650006),(660438,'长治市',NULL,650006),(660439,'晋城市',NULL,650006),(660440,'朔州市',NULL,650006),(660441,'晋中市',NULL,650006),(660442,'运城市',NULL,650006),(660443,'忻州市',NULL,650006),(660444,'临汾市',NULL,650006),(660445,'吕梁市',NULL,650006),(660446,'呼和浩特市',NULL,650007),(660447,'包头市',NULL,650007),(660448,'乌海市',NULL,650007),(660449,'赤峰市',NULL,650007),(660450,'通辽市',NULL,650007),(660451,'鄂尔多斯市',NULL,650007),(660452,'呼伦贝尔市',NULL,650007),(660453,'巴彦淖尔市',NULL,650007),(660454,'乌兰察布市',NULL,650007),(660455,'兴安盟',NULL,650007),(660456,'锡林郭勒盟',NULL,650007),(660457,'阿拉善盟',NULL,650007),(660458,'沈阳市',NULL,650008),(660459,'大连市',NULL,650008),(660460,'鞍山市',NULL,650008),(660461,'抚顺市',NULL,650008),(660462,'本溪市',NULL,650008),(660463,'丹东市',NULL,650008),(660464,'锦州市',NULL,650008),(660465,'营口市',NULL,650008),(660466,'阜新市',NULL,650008),(660467,'辽阳市',NULL,650008),(660468,'盘锦市',NULL,650008),(660469,'铁岭市',NULL,650008),(660470,'朝阳市',NULL,650008),(660471,'葫芦岛市',NULL,650008),(660472,'长春市',NULL,650009),(660473,'吉林市',NULL,650009),(660474,'四平市',NULL,650009),(660475,'辽源市',NULL,650009),(660476,'通化市',NULL,650009),(660477,'白山市',NULL,650009),(660478,'松原市',NULL,650009),(660479,'白城市',NULL,650009),(660480,'延边朝鲜族自治州',NULL,650009),(660481,'哈尔滨市',NULL,650010),(660482,'齐齐哈尔市',NULL,650010),(660483,'鸡西市',NULL,650010),(660484,'鹤岗市',NULL,650010),(660485,'双鸭山市',NULL,650010),(660486,'大庆市',NULL,650010),(660487,'伊春市',NULL,650010),(660488,'佳木斯市',NULL,650010),(660489,'七台河市',NULL,650010),(660490,'牡丹江市',NULL,650010),(660491,'黑河市',NULL,650010),(660492,'绥化市',NULL,650010),(660493,'大兴安岭地区',NULL,650010),(660494,'市辖区',NULL,650011),(660495,'市辖县',NULL,650011),(660496,'南京市',NULL,650012),(660497,'无锡市',NULL,650012),(660498,'徐州市',NULL,650012),(660499,'常州市',NULL,650012),(660500,'苏州市',NULL,650012),(660501,'南通市',NULL,650012),(660502,'连云港市',NULL,650012),(660503,'淮安市',NULL,650012),(660504,'盐城市',NULL,650012),(660505,'扬州市',NULL,650012),(660506,'镇江市',NULL,650012),(660507,'泰州市',NULL,650012),(660508,'宿迁市',NULL,650012),(660509,'杭州市',NULL,650013),(660510,'宁波市',NULL,650013),(660511,'温州市',NULL,650013),(660512,'嘉兴市',NULL,650013),(660513,'湖州市',NULL,650013),(660514,'绍兴市',NULL,650013),(660515,'金华市',NULL,650013),(660516,'衢州市',NULL,650013),(660517,'舟山市',NULL,650013),(660518,'台州市',NULL,650013),(660519,'丽水市',NULL,650013),(660520,'合肥市',NULL,650014),(660521,'芜湖市',NULL,650014),(660522,'蚌埠市',NULL,650014),(660523,'淮南市',NULL,650014),(660524,'马鞍山市',NULL,650014),(660525,'淮北市',NULL,650014),(660526,'铜陵市',NULL,650014),(660527,'安庆市',NULL,650014),(660528,'黄山市',NULL,650014),(660529,'滁州市',NULL,650014),(660530,'阜阳市',NULL,650014),(660531,'宿州市',NULL,650014),(660532,'六安市',NULL,650014),(660533,'亳州市',NULL,650014),(660534,'池州市',NULL,650014),(660535,'宣城市',NULL,650014),(660536,'福州市',NULL,650015),(660537,'厦门市',NULL,650015),(660538,'莆田市',NULL,650015),(660539,'三明市',NULL,650015),(660540,'泉州市',NULL,650015),(660541,'漳州市',NULL,650015),(660542,'南平市',NULL,650015),(660543,'龙岩市',NULL,650015),(660544,'宁德市',NULL,650015),(660545,'南昌市',NULL,650016),(660546,'景德镇市',NULL,650016),(660547,'萍乡市',NULL,650016),(660548,'九江市',NULL,650016),(660549,'新余市',NULL,650016),(660550,'鹰潭市',NULL,650016),(660551,'赣州市',NULL,650016),(660552,'吉安市',NULL,650016),(660553,'宜春市',NULL,650016),(660554,'抚州市',NULL,650016),(660555,'上饶市',NULL,650016),(660556,'济南市',NULL,650017),(660557,'青岛市',NULL,650017),(660558,'淄博市',NULL,650017),(660559,'枣庄市',NULL,650017),(660560,'东营市',NULL,650017),(660561,'烟台市',NULL,650017),(660562,'潍坊市',NULL,650017),(660563,'济宁市',NULL,650017),(660564,'泰安市',NULL,650017),(660565,'威海市',NULL,650017),(660566,'日照市',NULL,650017),(660567,'莱芜市',NULL,650017),(660568,'临沂市',NULL,650017),(660569,'德州市',NULL,650017),(660570,'聊城市',NULL,650017),(660571,'滨州市',NULL,650017),(660572,'菏泽市',NULL,650017),(660573,'郑州市',NULL,650018),(660574,'开封市',NULL,650018),(660575,'洛阳市',NULL,650018),(660576,'平顶山市',NULL,650018),(660577,'安阳市',NULL,650018),(660578,'鹤壁市',NULL,650018),(660579,'新乡市',NULL,650018),(660580,'焦作市',NULL,650018),(660581,'濮阳市',NULL,650018),(660582,'许昌市',NULL,650018),(660583,'漯河市',NULL,650018),(660584,'三门峡市',NULL,650018),(660585,'南阳市',NULL,650018),(660586,'商丘市',NULL,650018),(660587,'信阳市',NULL,650018),(660588,'周口市',NULL,650018),(660589,'驻马店市',NULL,650018),(660590,'省直辖县级行政区划',NULL,650018),(660591,'武汉市',NULL,650019),(660592,'黄石市',NULL,650019),(660593,'十堰市',NULL,650019),(660594,'宜昌市',NULL,650019),(660595,'襄阳市',NULL,650019),(660596,'鄂州市',NULL,650019),(660597,'荆门市',NULL,650019),(660598,'孝感市',NULL,650019),(660599,'荆州市',NULL,650019),(660600,'黄冈市',NULL,650019),(660601,'咸宁市',NULL,650019),(660602,'随州市',NULL,650019),(660603,'恩施土家族苗族自治州',NULL,650019),(660604,'省直辖县级行政区划',NULL,650019),(660605,'长沙市',NULL,650020),(660606,'株洲市',NULL,650020),(660607,'湘潭市',NULL,650020),(660608,'衡阳市',NULL,650020),(660609,'邵阳市',NULL,650020),(660610,'岳阳市',NULL,650020),(660611,'常德市',NULL,650020),(660612,'张家界市',NULL,650020),(660613,'益阳市',NULL,650020),(660614,'郴州市',NULL,650020),(660615,'永州市',NULL,650020),(660616,'怀化市',NULL,650020),(660617,'娄底市',NULL,650020),(660618,'湘西土家族苗族自治州',NULL,650020),(660619,'广州市',NULL,650021),(660620,'韶关市',NULL,650021),(660621,'深圳市',NULL,650021),(660622,'珠海市',NULL,650021),(660623,'汕头市',NULL,650021),(660624,'佛山市',NULL,650021),(660625,'江门市',NULL,650021),(660626,'湛江市',NULL,650021),(660627,'茂名市',NULL,650021),(660628,'肇庆市',NULL,650021),(660629,'惠州市',NULL,650021),(660630,'梅州市',NULL,650021),(660631,'汕尾市',NULL,650021),(660632,'河源市',NULL,650021),(660633,'阳江市',NULL,650021),(660634,'清远市',NULL,650021),(660635,'东莞市',NULL,650021),(660636,'中山市',NULL,650021),(660637,'潮州市',NULL,650021),(660638,'揭阳市',NULL,650021),(660639,'云浮市',NULL,650021),(660640,'南宁市',NULL,650022),(660641,'柳州市',NULL,650022),(660642,'桂林市',NULL,650022),(660643,'梧州市',NULL,650022),(660644,'北海市',NULL,650022),(660645,'防城港市',NULL,650022),(660646,'钦州市',NULL,650022),(660647,'贵港市',NULL,650022),(660648,'玉林市',NULL,650022),(660649,'百色市',NULL,650022),(660650,'贺州市',NULL,650022),(660651,'河池市',NULL,650022),(660652,'来宾市',NULL,650022),(660653,'崇左市',NULL,650022),(660654,'海口市',NULL,650023),(660655,'三亚市',NULL,650023),(660656,'省直辖县级行政区划',NULL,650023),(660657,'市辖区',NULL,650024),(660658,'市辖县',NULL,650024),(660659,'成都市',NULL,650025),(660660,'自贡市',NULL,650025),(660661,'攀枝花市',NULL,650025),(660662,'泸州市',NULL,650025),(660663,'德阳市',NULL,650025),(660664,'绵阳市',NULL,650025),(660665,'广元市',NULL,650025),(660666,'遂宁市',NULL,650025),(660667,'内江市',NULL,650025),(660668,'乐山市',NULL,650025),(660669,'南充市',NULL,650025),(660670,'眉山市',NULL,650025),(660671,'宜宾市',NULL,650025),(660672,'广安市',NULL,650025),(660673,'达州市',NULL,650025),(660674,'雅安市',NULL,650025),(660675,'巴中市',NULL,650025),(660676,'资阳市',NULL,650025),(660677,'阿坝藏族羌族自治州',NULL,650025),(660678,'甘孜藏族自治州',NULL,650025),(660679,'凉山彝族自治州',NULL,650025),(660680,'贵阳市',NULL,650026),(660681,'六盘水市',NULL,650026),(660682,'遵义市',NULL,650026),(660683,'安顺市',NULL,650026),(660684,'毕节市',NULL,650026),(660685,'铜仁市',NULL,650026),(660686,'黔西南布依族苗族自治州',NULL,650026),(660687,'黔东南苗族侗族自治州',NULL,650026),(660688,'黔南布依族苗族自治州',NULL,650026),(660689,'昆明市',NULL,650027),(660690,'曲靖市',NULL,650027),(660691,'玉溪市',NULL,650027),(660692,'保山市',NULL,650027),(660693,'昭通市',NULL,650027),(660694,'丽江市',NULL,650027),(660695,'普洱市',NULL,650027),(660696,'临沧市',NULL,650027),(660697,'楚雄彝族自治州',NULL,650027),(660698,'红河哈尼族彝族自治州',NULL,650027),(660699,'文山壮族苗族自治州',NULL,650027),(660700,'西双版纳傣族自治州',NULL,650027),(660701,'大理白族自治州',NULL,650027),(660702,'德宏傣族景颇族自治州',NULL,650027),(660703,'怒江傈僳族自治州',NULL,650027),(660704,'迪庆藏族自治州',NULL,650027),(660705,'拉萨市',NULL,650028),(660706,'昌都地区',NULL,650028),(660707,'山南地区',NULL,650028),(660708,'日喀则地区',NULL,650028),(660709,'那曲地区',NULL,650028),(660710,'阿里地区',NULL,650028),(660711,'林芝地区',NULL,650028),(660712,'西安市',NULL,650029),(660713,'铜川市',NULL,650029),(660714,'宝鸡市',NULL,650029),(660715,'咸阳市',NULL,650029),(660716,'渭南市',NULL,650029),(660717,'延安市',NULL,650029),(660718,'汉中市',NULL,650029),(660719,'榆林市',NULL,650029),(660720,'安康市',NULL,650029),(660721,'商洛市',NULL,650029),(660722,'兰州市',NULL,650030),(660723,'嘉峪关市',NULL,650030),(660724,'金昌市',NULL,650030),(660725,'白银市',NULL,650030),(660726,'天水市',NULL,650030),(660727,'武威市',NULL,650030),(660728,'张掖市',NULL,650030),(660729,'平凉市',NULL,650030),(660730,'酒泉市',NULL,650030),(660731,'庆阳市',NULL,650030),(660732,'定西市',NULL,650030),(660733,'陇南市',NULL,650030),(660734,'临夏回族自治州',NULL,650030),(660735,'甘南藏族自治州',NULL,650030),(660736,'西宁市',NULL,650031),(660737,'海东地区',NULL,650031),(660738,'海北藏族自治州',NULL,650031),(660739,'黄南藏族自治州',NULL,650031),(660740,'海南藏族自治州',NULL,650031),(660741,'果洛藏族自治州',NULL,650031),(660742,'玉树藏族自治州',NULL,650031),(660743,'海西蒙古族藏族自治州',NULL,650031),(660744,'银川市',NULL,650032),(660745,'石嘴山市',NULL,650032),(660746,'吴忠市',NULL,650032),(660747,'固原市',NULL,650032),(660748,'中卫市',NULL,650032),(660749,'乌鲁木齐市',NULL,650033),(660750,'克拉玛依市',NULL,650033),(660751,'吐鲁番地区',NULL,650033),(660752,'哈密地区',NULL,650033),(660753,'昌吉回族自治州',NULL,650033),(660754,'博尔塔拉蒙古自治州',NULL,650033),(660755,'巴音郭楞蒙古自治州',NULL,650033),(660756,'阿克苏地区',NULL,650033),(660757,'克孜勒苏柯尔克孜自治州',NULL,650033),(660758,'喀什地区',NULL,650033),(660759,'和田地区',NULL,650033),(660760,'伊犁哈萨克自治州',NULL,650033),(660761,'塔城地区',NULL,650033),(660762,'阿勒泰地区',NULL,650033),(660763,'自治区直辖县级行政区划',NULL,650033),(660764,'香港',NULL,650034),(660765,'澳门',NULL,650035),(660766,'台北市',NULL,650036),(660767,'高雄市',NULL,650036),(660768,'基隆市',NULL,650036),(660769,'台中市',NULL,650036),(660770,'台南市',NULL,650036),(660771,'新竹市',NULL,650036),(660772,'嘉义市',NULL,650036),(660773,'县',NULL,650036),(660774,'巢湖市',NULL,650014);

/*Table structure for table `t_district` */

DROP TABLE IF EXISTS `t_district`;

CREATE TABLE `t_district` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityid` int(11) DEFAULT NULL,
  `dis_name` varchar(50) DEFAULT NULL,
  `dis_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=674033 DEFAULT CHARSET=utf8;

/*Data for the table `t_district` */

insert  into `t_district`(`id`,`cityid`,`dis_name`,`dis_sort`) values (671111,660420,'东城区',NULL),(671112,660420,'西城区',NULL),(671113,660420,'朝阳区',NULL),(671114,660420,'丰台区',NULL),(671115,660420,'石景山区',NULL),(671116,660420,'海淀区',NULL),(671117,660420,'门头沟区',NULL),(671118,660420,'房山区',NULL),(671119,660420,'通州区',NULL),(671120,660420,'顺义区',NULL),(671121,660420,'昌平区',NULL),(671122,660420,'大兴区',NULL),(671123,660420,'怀柔区',NULL),(671124,660420,'平谷区',NULL),(671125,660421,'密云县',NULL),(671126,660421,'延庆县',NULL),(671127,660422,'和平区',NULL),(671128,660422,'河东区',NULL),(671129,660422,'河西区',NULL),(671130,660422,'南开区',NULL),(671131,660422,'河北区',NULL),(671132,660422,'红桥区',NULL),(671133,660422,'东丽区',NULL),(671134,660422,'西青区',NULL),(671135,660422,'津南区',NULL),(671136,660422,'北辰区',NULL),(671137,660422,'武清区',NULL),(671138,660422,'宝坻区',NULL),(671139,660422,'滨海新区',NULL),(671140,660423,'宁河县',NULL),(671141,660423,'静海县',NULL),(671142,660423,'蓟县',NULL),(671143,660424,'长安区',NULL),(671144,660424,'桥东区',NULL),(671145,660424,'桥西区',NULL),(671146,660424,'新华区',NULL),(671147,660424,'井陉矿区',NULL),(671148,660424,'裕华区',NULL),(671149,660424,'井陉县',NULL),(671150,660424,'正定县',NULL),(671151,660424,'栾城县',NULL),(671152,660424,'行唐县',NULL),(671153,660424,'灵寿县',NULL),(671154,660424,'高邑县',NULL),(671155,660424,'深泽县',NULL),(671156,660424,'赞皇县',NULL),(671157,660424,'无极县',NULL),(671158,660424,'平山县',NULL),(671159,660424,'元氏县',NULL),(671160,660424,'赵县',NULL),(671161,660424,'辛集市',NULL),(671162,660424,'藁城市',NULL),(671163,660424,'晋州市',NULL),(671164,660424,'新乐市',NULL),(671165,660424,'鹿泉市',NULL),(671166,660425,'路南区',NULL),(671167,660425,'路北区',NULL),(671168,660425,'古冶区',NULL),(671169,660425,'开平区',NULL),(671170,660425,'丰南区',NULL),(671171,660425,'丰润区',NULL),(671172,660425,'滦县',NULL),(671173,660425,'滦南县',NULL),(671174,660425,'乐亭县',NULL),(671175,660425,'迁西县',NULL),(671176,660425,'玉田县',NULL),(671177,660425,'唐海县',NULL),(671178,660425,'遵化市',NULL),(671179,660425,'迁安市',NULL),(671180,660426,'海港区',NULL),(671181,660426,'山海关区',NULL),(671182,660426,'北戴河区',NULL),(671183,660426,'青龙满族自治县',NULL),(671184,660426,'昌黎县',NULL),(671185,660426,'抚宁县',NULL),(671186,660426,'卢龙县',NULL),(671187,660427,'邯山区',NULL),(671188,660427,'丛台区',NULL),(671189,660427,'复兴区',NULL),(671190,660427,'峰峰矿区',NULL),(671191,660427,'邯郸县',NULL),(671192,660427,'临漳县',NULL),(671193,660427,'成安县',NULL),(671194,660427,'大名县',NULL),(671195,660427,'涉县',NULL),(671196,660427,'磁县',NULL),(671197,660427,'肥乡县',NULL),(671198,660427,'永年县',NULL),(671199,660427,'邱县',NULL),(671200,660427,'鸡泽县',NULL),(671201,660427,'广平县',NULL),(671202,660427,'馆陶县',NULL),(671203,660427,'魏县',NULL),(671204,660427,'曲周县',NULL),(671205,660427,'武安市',NULL),(671206,660428,'桥东区',NULL),(671207,660428,'桥西区',NULL),(671208,660428,'邢台县',NULL),(671209,660428,'临城县',NULL),(671210,660428,'内丘县',NULL),(671211,660428,'柏乡县',NULL),(671212,660428,'隆尧县',NULL),(671213,660428,'任县',NULL),(671214,660428,'南和县',NULL),(671215,660428,'宁晋县',NULL),(671216,660428,'巨鹿县',NULL),(671217,660428,'新河县',NULL),(671218,660428,'广宗县',NULL),(671219,660428,'平乡县',NULL),(671220,660428,'威县',NULL),(671221,660428,'清河县',NULL),(671222,660428,'临西县',NULL),(671223,660428,'南宫市',NULL),(671224,660428,'沙河市',NULL),(671225,660429,'新市区',NULL),(671226,660429,'北市区',NULL),(671227,660429,'南市区',NULL),(671228,660429,'满城县',NULL),(671229,660429,'清苑县',NULL),(671230,660429,'涞水县',NULL),(671231,660429,'阜平县',NULL),(671232,660429,'徐水县',NULL),(671233,660429,'定兴县',NULL),(671234,660429,'唐县',NULL),(671235,660429,'高阳县',NULL),(671236,660429,'容城县',NULL),(671237,660429,'涞源县',NULL),(671238,660429,'望都县',NULL),(671239,660429,'安新县',NULL),(671240,660429,'易县',NULL),(671241,660429,'曲阳县',NULL),(671242,660429,'蠡县',NULL),(671243,660429,'顺平县',NULL),(671244,660429,'博野县',NULL),(671245,660429,'雄县',NULL),(671246,660429,'涿州市',NULL),(671247,660429,'定州市',NULL),(671248,660429,'安国市',NULL),(671249,660429,'高碑店市',NULL),(671250,660430,'桥东区',NULL),(671251,660430,'桥西区',NULL),(671252,660430,'宣化区',NULL),(671253,660430,'下花园区',NULL),(671254,660430,'宣化县',NULL),(671255,660430,'张北县',NULL),(671256,660430,'康保县',NULL),(671257,660430,'沽源县',NULL),(671258,660430,'尚义县',NULL),(671259,660430,'蔚县',NULL),(671260,660430,'阳原县',NULL),(671261,660430,'怀安县',NULL),(671262,660430,'万全县',NULL),(671263,660430,'怀来县',NULL),(671264,660430,'涿鹿县',NULL),(671265,660430,'赤城县',NULL),(671266,660430,'崇礼县',NULL),(671267,660431,'双桥区',NULL),(671268,660431,'双滦区',NULL),(671269,660431,'鹰手营子矿区',NULL),(671270,660431,'承德县',NULL),(671271,660431,'兴隆县',NULL),(671272,660431,'平泉县',NULL),(671273,660431,'滦平县',NULL),(671274,660431,'隆化县',NULL),(671275,660431,'丰宁满族自治县',NULL),(671276,660431,'宽城满族自治县',NULL),(671277,660431,'围场满族蒙古族自治县',NULL),(671278,660432,'新华区',NULL),(671279,660432,'运河区',NULL),(671280,660432,'沧县',NULL),(671281,660432,'青县',NULL),(671282,660432,'东光县',NULL),(671283,660432,'海兴县',NULL),(671284,660432,'盐山县',NULL),(671285,660432,'肃宁县',NULL),(671286,660432,'南皮县',NULL),(671287,660432,'吴桥县',NULL),(671288,660432,'献县',NULL),(671289,660432,'孟村回族自治县',NULL),(671290,660432,'泊头市',NULL),(671291,660432,'任丘市',NULL),(671292,660432,'黄骅市',NULL),(671293,660432,'河间市',NULL),(671294,660433,'安次区',NULL),(671295,660433,'广阳区',NULL),(671296,660433,'固安县',NULL),(671297,660433,'永清县',NULL),(671298,660433,'香河县',NULL),(671299,660433,'大城县',NULL),(671300,660433,'文安县',NULL),(671301,660433,'大厂回族自治县',NULL),(671302,660433,'霸州市',NULL),(671303,660433,'三河市',NULL),(671304,660434,'桃城区',NULL),(671305,660434,'枣强县',NULL),(671306,660434,'武邑县',NULL),(671307,660434,'武强县',NULL),(671308,660434,'饶阳县',NULL),(671309,660434,'安平县',NULL),(671310,660434,'故城县',NULL),(671311,660434,'景县',NULL),(671312,660434,'阜城县',NULL),(671313,660434,'冀州市',NULL),(671314,660434,'深州市',NULL),(671315,660435,'小店区',NULL),(671316,660435,'迎泽区',NULL),(671317,660435,'杏花岭区',NULL),(671318,660435,'尖草坪区',NULL),(671319,660435,'万柏林区',NULL),(671320,660435,'晋源区',NULL),(671321,660435,'清徐县',NULL),(671322,660435,'阳曲县',NULL),(671323,660435,'娄烦县',NULL),(671324,660435,'古交市',NULL),(671325,660436,'城区',NULL),(671326,660436,'矿区',NULL),(671327,660436,'南郊区',NULL),(671328,660436,'新荣区',NULL),(671329,660436,'阳高县',NULL),(671330,660436,'天镇县',NULL),(671331,660436,'广灵县',NULL),(671332,660436,'灵丘县',NULL),(671333,660436,'浑源县',NULL),(671334,660436,'左云县',NULL),(671335,660436,'大同县',NULL),(671336,660437,'城区',NULL),(671337,660437,'矿区',NULL),(671338,660437,'郊区',NULL),(671339,660437,'平定县',NULL),(671340,660437,'盂县',NULL),(671341,660438,'城区',NULL),(671342,660438,'郊区',NULL),(671343,660438,'长治县',NULL),(671344,660438,'襄垣县',NULL),(671345,660438,'屯留县',NULL),(671346,660438,'平顺县',NULL),(671347,660438,'黎城县',NULL),(671348,660438,'壶关县',NULL),(671349,660438,'长子县',NULL),(671350,660438,'武乡县',NULL),(671351,660438,'沁县',NULL),(671352,660438,'沁源县',NULL),(671353,660438,'潞城市',NULL),(671354,660439,'城区',NULL),(671355,660439,'沁水县',NULL),(671356,660439,'阳城县',NULL),(671357,660439,'陵川县',NULL),(671358,660439,'泽州县',NULL),(671359,660439,'高平市',NULL),(671360,660440,'朔城区',NULL),(671361,660440,'平鲁区',NULL),(671362,660440,'山阴县',NULL),(671363,660440,'应县',NULL),(671364,660440,'右玉县',NULL),(671365,660440,'怀仁县',NULL),(671366,660441,'榆次区',NULL),(671367,660441,'榆社县',NULL),(671368,660441,'左权县',NULL),(671369,660441,'和顺县',NULL),(671370,660441,'昔阳县',NULL),(671371,660441,'寿阳县',NULL),(671372,660441,'太谷县',NULL),(671373,660441,'祁县',NULL),(671374,660441,'平遥县',NULL),(671375,660441,'灵石县',NULL),(671376,660441,'介休市',NULL),(671377,660442,'盐湖区',NULL),(671378,660442,'临猗县',NULL),(671379,660442,'万荣县',NULL),(671380,660442,'闻喜县',NULL),(671381,660442,'稷山县',NULL),(671382,660442,'新绛县',NULL),(671383,660442,'绛县',NULL),(671384,660442,'垣曲县',NULL),(671385,660442,'夏县',NULL),(671386,660442,'平陆县',NULL),(671387,660442,'芮城县',NULL),(671388,660442,'永济市',NULL),(671389,660442,'河津市',NULL),(671390,660443,'忻府区',NULL),(671391,660443,'定襄县',NULL),(671392,660443,'五台县',NULL),(671393,660443,'代县',NULL),(671394,660443,'繁峙县',NULL),(671395,660443,'宁武县',NULL),(671396,660443,'静乐县',NULL),(671397,660443,'神池县',NULL),(671398,660443,'五寨县',NULL),(671399,660443,'岢岚县',NULL),(671400,660443,'河曲县',NULL),(671401,660443,'保德县',NULL),(671402,660443,'偏关县',NULL),(671403,660443,'原平市',NULL),(671404,660444,'尧都区',NULL),(671405,660444,'曲沃县',NULL),(671406,660444,'翼城县',NULL),(671407,660444,'襄汾县',NULL),(671408,660444,'洪洞县',NULL),(671409,660444,'古县',NULL),(671410,660444,'安泽县',NULL),(671411,660444,'浮山县',NULL),(671412,660444,'吉县',NULL),(671413,660444,'乡宁县',NULL),(671414,660444,'大宁县',NULL),(671415,660444,'隰县',NULL),(671416,660444,'永和县',NULL),(671417,660444,'蒲县',NULL),(671418,660444,'汾西县',NULL),(671419,660444,'侯马市',NULL),(671420,660444,'霍州市',NULL),(671421,660445,'离石区',NULL),(671422,660445,'文水县',NULL),(671423,660445,'交城县',NULL),(671424,660445,'兴县',NULL),(671425,660445,'临县',NULL),(671426,660445,'柳林县',NULL),(671427,660445,'石楼县',NULL),(671428,660445,'岚县',NULL),(671429,660445,'方山县',NULL),(671430,660445,'中阳县',NULL),(671431,660445,'交口县',NULL),(671432,660445,'孝义市',NULL),(671433,660445,'汾阳市',NULL),(671434,660446,'新城区',NULL),(671435,660446,'回民区',NULL),(671436,660446,'玉泉区',NULL),(671437,660446,'赛罕区',NULL),(671438,660446,'土默特左旗',NULL),(671439,660446,'托克托县',NULL),(671440,660446,'和林格尔县',NULL),(671441,660446,'清水河县',NULL),(671442,660446,'武川县',NULL),(671443,660447,'东河区',NULL),(671444,660447,'昆都仑区',NULL),(671445,660447,'青山区',NULL),(671446,660447,'石拐区',NULL),(671447,660447,'白云鄂博矿区',NULL),(671448,660447,'九原区',NULL),(671449,660447,'土默特右旗',NULL),(671450,660447,'固阳县',NULL),(671451,660447,'达尔罕茂明安联合旗',NULL),(671452,660448,'海勃湾区',NULL),(671453,660448,'海南区',NULL),(671454,660448,'乌达区',NULL),(671455,660449,'红山区',NULL),(671456,660449,'元宝山区',NULL),(671457,660449,'松山区',NULL),(671458,660449,'阿鲁科尔沁旗',NULL),(671459,660449,'巴林左旗',NULL),(671460,660449,'巴林右旗',NULL),(671461,660449,'林西县',NULL),(671462,660449,'克什克腾旗',NULL),(671463,660449,'翁牛特旗',NULL),(671464,660449,'喀喇沁旗',NULL),(671465,660449,'宁城县',NULL),(671466,660449,'敖汉旗',NULL),(671467,660450,'科尔沁区',NULL),(671468,660450,'科尔沁左翼中旗',NULL),(671469,660450,'科尔沁左翼后旗',NULL),(671470,660450,'开鲁县',NULL),(671471,660450,'库伦旗',NULL),(671472,660450,'奈曼旗',NULL),(671473,660450,'扎鲁特旗',NULL),(671474,660450,'霍林郭勒市',NULL),(671475,660451,'东胜区',NULL),(671476,660451,'达拉特旗',NULL),(671477,660451,'准格尔旗',NULL),(671478,660451,'鄂托克前旗',NULL),(671479,660451,'鄂托克旗',NULL),(671480,660451,'杭锦旗',NULL),(671481,660451,'乌审旗',NULL),(671482,660451,'伊金霍洛旗',NULL),(671483,660452,'海拉尔区',NULL),(671484,660452,'阿荣旗',NULL),(671485,660452,'莫力达瓦达斡尔族自治旗',NULL),(671486,660452,'鄂伦春自治旗',NULL),(671487,660452,'鄂温克族自治旗',NULL),(671488,660452,'陈巴尔虎旗',NULL),(671489,660452,'新巴尔虎左旗',NULL),(671490,660452,'新巴尔虎右旗',NULL),(671491,660452,'满洲里市',NULL),(671492,660452,'牙克石市',NULL),(671493,660452,'扎兰屯市',NULL),(671494,660452,'额尔古纳市',NULL),(671495,660452,'根河市',NULL),(671496,660453,'临河区',NULL),(671497,660453,'五原县',NULL),(671498,660453,'磴口县',NULL),(671499,660453,'乌拉特前旗',NULL),(671500,660453,'乌拉特中旗',NULL),(671501,660453,'乌拉特后旗',NULL),(671502,660453,'杭锦后旗',NULL),(671503,660454,'集宁区',NULL),(671504,660454,'卓资县',NULL),(671505,660454,'化德县',NULL),(671506,660454,'商都县',NULL),(671507,660454,'兴和县',NULL),(671508,660454,'凉城县',NULL),(671509,660454,'察哈尔右翼前旗',NULL),(671510,660454,'察哈尔右翼中旗',NULL),(671511,660454,'察哈尔右翼后旗',NULL),(671512,660454,'四子王旗',NULL),(671513,660454,'丰镇市',NULL),(671514,660455,'乌兰浩特市',NULL),(671515,660455,'阿尔山市',NULL),(671516,660455,'科尔沁右翼前旗',NULL),(671517,660455,'科尔沁右翼中旗',NULL),(671518,660455,'扎赉特旗',NULL),(671519,660455,'突泉县',NULL),(671520,660456,'二连浩特市',NULL),(671521,660456,'锡林浩特市',NULL),(671522,660456,'阿巴嘎旗',NULL),(671523,660456,'苏尼特左旗',NULL),(671524,660456,'苏尼特右旗',NULL),(671525,660456,'东乌珠穆沁旗',NULL),(671526,660456,'西乌珠穆沁旗',NULL),(671527,660456,'太仆寺旗',NULL),(671528,660456,'镶黄旗',NULL),(671529,660456,'正镶白旗',NULL),(671530,660456,'正蓝旗',NULL),(671531,660456,'多伦县',NULL),(671532,660457,'阿拉善左旗',NULL),(671533,660457,'阿拉善右旗',NULL),(671534,660457,'额济纳旗',NULL),(671535,660458,'和平区',NULL),(671536,660458,'沈河区',NULL),(671537,660458,'大东区',NULL),(671538,660458,'皇姑区',NULL),(671539,660458,'铁西区',NULL),(671540,660458,'苏家屯区',NULL),(671541,660458,'东陵区',NULL),(671542,660458,'沈北新区',NULL),(671543,660458,'于洪区',NULL),(671544,660458,'辽中县',NULL),(671545,660458,'康平县',NULL),(671546,660458,'法库县',NULL),(671547,660458,'新民市',NULL),(671548,660459,'中山区',NULL),(671549,660459,'西岗区',NULL),(671550,660459,'沙河口区',NULL),(671551,660459,'甘井子区',NULL),(671552,660459,'旅顺口区',NULL),(671553,660459,'金州区',NULL),(671554,660459,'长海县',NULL),(671555,660459,'瓦房店市',NULL),(671556,660459,'普兰店市',NULL),(671557,660459,'庄河市',NULL),(671558,660460,'铁东区',NULL),(671559,660460,'铁西区',NULL),(671560,660460,'立山区',NULL),(671561,660460,'千山区',NULL),(671562,660460,'台安县',NULL),(671563,660460,'岫岩满族自治县',NULL),(671564,660460,'海城市',NULL),(671565,660461,'新抚区',NULL),(671566,660461,'东洲区',NULL),(671567,660461,'望花区',NULL),(671568,660461,'顺城区',NULL),(671569,660461,'抚顺县',NULL),(671570,660461,'新宾满族自治县',NULL),(671571,660461,'清原满族自治县',NULL),(671572,660462,'平山区',NULL),(671573,660462,'溪湖区',NULL),(671574,660462,'明山区',NULL),(671575,660462,'南芬区',NULL),(671576,660462,'本溪满族自治县',NULL),(671577,660462,'桓仁满族自治县',NULL),(671578,660463,'元宝区',NULL),(671579,660463,'振兴区',NULL),(671580,660463,'振安区',NULL),(671581,660463,'宽甸满族自治县',NULL),(671582,660463,'东港市',NULL),(671583,660463,'凤城市',NULL),(671584,660464,'古塔区',NULL),(671585,660464,'凌河区',NULL),(671586,660464,'太和区',NULL),(671587,660464,'黑山县',NULL),(671588,660464,'义县',NULL),(671589,660464,'凌海市',NULL),(671590,660464,'北镇市',NULL),(671591,660465,'站前区',NULL),(671592,660465,'西市区',NULL),(671593,660465,'鲅鱼圈区',NULL),(671594,660465,'老边区',NULL),(671595,660465,'盖州市',NULL),(671596,660465,'大石桥市',NULL),(671597,660466,'海州区',NULL),(671598,660466,'新邱区',NULL),(671599,660466,'太平区',NULL),(671600,660466,'清河门区',NULL),(671601,660466,'细河区',NULL),(671602,660466,'阜新蒙古族自治县',NULL),(671603,660466,'彰武县',NULL),(671604,660467,'白塔区',NULL),(671605,660467,'文圣区',NULL),(671606,660467,'宏伟区',NULL),(671607,660467,'弓长岭区',NULL),(671608,660467,'太子河区',NULL),(671609,660467,'辽阳县',NULL),(671610,660467,'灯塔市',NULL),(671611,660468,'双台子区',NULL),(671612,660468,'兴隆台区',NULL),(671613,660468,'大洼县',NULL),(671614,660468,'盘山县',NULL),(671615,660469,'银州区',NULL),(671616,660469,'清河区',NULL),(671617,660469,'铁岭县',NULL),(671618,660469,'西丰县',NULL),(671619,660469,'昌图县',NULL),(671620,660469,'调兵山市',NULL),(671621,660469,'开原市',NULL),(671622,660470,'双塔区',NULL),(671623,660470,'龙城区',NULL),(671624,660470,'朝阳县',NULL),(671625,660470,'建平县',NULL),(671626,660470,'喀喇沁左翼蒙古族自治县',NULL),(671627,660470,'北票市',NULL),(671628,660470,'凌源市',NULL),(671629,660471,'连山区',NULL),(671630,660471,'龙港区',NULL),(671631,660471,'南票区',NULL),(671632,660471,'绥中县',NULL),(671633,660471,'建昌县',NULL),(671634,660471,'兴城市',NULL),(671635,660472,'南关区',NULL),(671636,660472,'宽城区',NULL),(671637,660472,'朝阳区',NULL),(671638,660472,'二道区',NULL),(671639,660472,'绿园区',NULL),(671640,660472,'双阳区',NULL),(671641,660472,'农安县',NULL),(671642,660472,'九台市',NULL),(671643,660472,'榆树市',NULL),(671644,660472,'德惠市',NULL),(671645,660473,'昌邑区',NULL),(671646,660473,'龙潭区',NULL),(671647,660473,'船营区',NULL),(671648,660473,'丰满区',NULL),(671649,660473,'永吉县',NULL),(671650,660473,'蛟河市',NULL),(671651,660473,'桦甸市',NULL),(671652,660473,'舒兰市',NULL),(671653,660473,'磐石市',NULL),(671654,660474,'铁西区',NULL),(671655,660474,'铁东区',NULL),(671656,660474,'梨树县',NULL),(671657,660474,'伊通满族自治县',NULL),(671658,660474,'公主岭市',NULL),(671659,660474,'双辽市',NULL),(671660,660475,'龙山区',NULL),(671661,660475,'西安区',NULL),(671662,660475,'东丰县',NULL),(671663,660475,'东辽县',NULL),(671664,660476,'东昌区',NULL),(671665,660476,'二道江区',NULL),(671666,660476,'通化县',NULL),(671667,660476,'辉南县',NULL),(671668,660476,'柳河县',NULL),(671669,660476,'梅河口市',NULL),(671670,660476,'集安市',NULL),(671671,660477,'八道江区',NULL),(671672,660477,'江源区',NULL),(671673,660477,'抚松县',NULL),(671674,660477,'靖宇县',NULL),(671675,660477,'长白朝鲜族自治县',NULL),(671676,660477,'临江市',NULL),(671677,660478,'宁江区',NULL),(671678,660478,'前郭尔罗斯蒙古族自治县',NULL),(671679,660478,'长岭县',NULL),(671680,660478,'乾安县',NULL),(671681,660478,'扶余县',NULL),(671682,660479,'洮北区',NULL),(671683,660479,'镇赉县',NULL),(671684,660479,'通榆县',NULL),(671685,660479,'洮南市',NULL),(671686,660479,'大安市',NULL),(671687,660480,'延吉市',NULL),(671688,660480,'图们市',NULL),(671689,660480,'敦化市',NULL),(671690,660480,'珲春市',NULL),(671691,660480,'龙井市',NULL),(671692,660480,'和龙市',NULL),(671693,660480,'汪清县',NULL),(671694,660480,'安图县',NULL),(671695,660481,'道里区',NULL),(671696,660481,'南岗区',NULL),(671697,660481,'道外区',NULL),(671698,660481,'平房区',NULL),(671699,660481,'松北区',NULL),(671700,660481,'香坊区',NULL),(671701,660481,'呼兰区',NULL),(671702,660481,'阿城区',NULL),(671703,660481,'依兰县',NULL),(671704,660481,'方正县',NULL),(671705,660481,'宾县',NULL),(671706,660481,'巴彦县',NULL),(671707,660481,'木兰县',NULL),(671708,660481,'通河县',NULL),(671709,660481,'延寿县',NULL),(671710,660481,'双城市',NULL),(671711,660481,'尚志市',NULL),(671712,660481,'五常市',NULL),(671713,660482,'龙沙区',NULL),(671714,660482,'建华区',NULL),(671715,660482,'铁锋区',NULL),(671716,660482,'昂昂溪区',NULL),(671717,660482,'富拉尔基区',NULL),(671718,660482,'碾子山区',NULL),(671719,660482,'梅里斯达斡尔族区',NULL),(671720,660482,'龙江县',NULL),(671721,660482,'依安县',NULL),(671722,660482,'泰来县',NULL),(671723,660482,'甘南县',NULL),(671724,660482,'富裕县',NULL),(671725,660482,'克山县',NULL),(671726,660482,'克东县',NULL),(671727,660482,'拜泉县',NULL),(671728,660482,'讷河市',NULL),(671729,660483,'鸡冠区',NULL),(671730,660483,'恒山区',NULL),(671731,660483,'滴道区',NULL),(671732,660483,'梨树区',NULL),(671733,660483,'城子河区',NULL),(671734,660483,'麻山区',NULL),(671735,660483,'鸡东县',NULL),(671736,660483,'虎林市',NULL),(671737,660483,'密山市',NULL),(671738,660484,'向阳区',NULL),(671739,660484,'工农区',NULL),(671740,660484,'南山区',NULL),(671741,660484,'兴安区',NULL),(671742,660484,'东山区',NULL),(671743,660484,'兴山区',NULL),(671744,660484,'萝北县',NULL),(671745,660484,'绥滨县',NULL),(671746,660485,'尖山区',NULL),(671747,660485,'岭东区',NULL),(671748,660485,'四方台区',NULL),(671749,660485,'宝山区',NULL),(671750,660485,'集贤县',NULL),(671751,660485,'友谊县',NULL),(671752,660485,'宝清县',NULL),(671753,660485,'饶河县',NULL),(671754,660486,'萨尔图区',NULL),(671755,660486,'龙凤区',NULL),(671756,660486,'让胡路区',NULL),(671757,660486,'红岗区',NULL),(671758,660486,'大同区',NULL),(671759,660486,'肇州县',NULL),(671760,660486,'肇源县',NULL),(671761,660486,'林甸县',NULL),(671762,660486,'杜尔伯特蒙古族自治县',NULL),(671763,660487,'伊春区',NULL),(671764,660487,'南岔区',NULL),(671765,660487,'友好区',NULL),(671766,660487,'西林区',NULL),(671767,660487,'翠峦区',NULL),(671768,660487,'新青区',NULL),(671769,660487,'美溪区',NULL),(671770,660487,'金山屯区',NULL),(671771,660487,'五营区',NULL),(671772,660487,'乌马河区',NULL),(671773,660487,'汤旺河区',NULL),(671774,660487,'带岭区',NULL),(671775,660487,'乌伊岭区',NULL),(671776,660487,'红星区',NULL),(671777,660487,'上甘岭区',NULL),(671778,660487,'嘉荫县',NULL),(671779,660487,'铁力市',NULL),(671780,660488,'向阳区',NULL),(671781,660488,'前进区',NULL),(671782,660488,'东风区',NULL),(671783,660488,'郊区',NULL),(671784,660488,'桦南县',NULL),(671785,660488,'桦川县',NULL),(671786,660488,'汤原县',NULL),(671787,660488,'抚远县',NULL),(671788,660488,'同江市',NULL),(671789,660488,'富锦市',NULL),(671790,660489,'新兴区',NULL),(671791,660489,'桃山区',NULL),(671792,660489,'茄子河区',NULL),(671793,660489,'勃利县',NULL),(671794,660490,'东安区',NULL),(671795,660490,'阳明区',NULL),(671796,660490,'爱民区',NULL),(671797,660490,'西安区',NULL),(671798,660490,'东宁县',NULL),(671799,660490,'林口县',NULL),(671800,660490,'绥芬河市',NULL),(671801,660490,'海林市',NULL),(671802,660490,'宁安市',NULL),(671803,660490,'穆棱市',NULL),(671804,660491,'爱辉区',NULL),(671805,660491,'嫩江县',NULL),(671806,660491,'逊克县',NULL),(671807,660491,'孙吴县',NULL),(671808,660491,'北安市',NULL),(671809,660491,'五大连池市',NULL),(671810,660492,'北林区',NULL),(671811,660492,'望奎县',NULL),(671812,660492,'兰西县',NULL),(671813,660492,'青冈县',NULL),(671814,660492,'庆安县',NULL),(671815,660492,'明水县',NULL),(671816,660492,'绥棱县',NULL),(671817,660492,'安达市',NULL),(671818,660492,'肇东市',NULL),(671819,660492,'海伦市',NULL),(671820,660493,'呼玛县',NULL),(671821,660493,'塔河县',NULL),(671822,660493,'漠河县',NULL),(671823,660494,'黄浦区',NULL),(671824,660494,'徐汇区',NULL),(671825,660494,'长宁区',NULL),(671826,660494,'静安区',NULL),(671827,660494,'普陀区',NULL),(671828,660494,'闸北区',NULL),(671829,660494,'虹口区',NULL),(671830,660494,'杨浦区',NULL),(671831,660494,'闵行区',NULL),(671832,660494,'宝山区',NULL),(671833,660494,'嘉定区',NULL),(671834,660494,'浦东新区',NULL),(671835,660494,'金山区',NULL),(671836,660494,'松江区',NULL),(671837,660494,'青浦区',NULL),(671838,660494,'奉贤区',NULL),(671839,660495,'崇明县',NULL),(671840,660496,'玄武区',NULL),(671841,660496,'白下区',NULL),(671842,660496,'秦淮区',NULL),(671843,660496,'建邺区',NULL),(671844,660496,'鼓楼区',NULL),(671845,660496,'下关区',NULL),(671846,660496,'浦口区',NULL),(671847,660496,'栖霞区',NULL),(671848,660496,'雨花台区',NULL),(671849,660496,'江宁区',NULL),(671850,660496,'六合区',NULL),(671851,660496,'溧水县',NULL),(671852,660496,'高淳县',NULL),(671853,660497,'崇安区',NULL),(671854,660497,'南长区',NULL),(671855,660497,'北塘区',NULL),(671856,660497,'锡山区',NULL),(671857,660497,'惠山区',NULL),(671858,660497,'滨湖区',NULL),(671859,660497,'江阴市',NULL),(671860,660497,'宜兴市',NULL),(671861,660498,'鼓楼区',NULL),(671862,660498,'云龙区',NULL),(671863,660498,'贾汪区',NULL),(671864,660498,'泉山区',NULL),(671865,660498,'铜山区',NULL),(671866,660498,'丰县',NULL),(671867,660498,'沛县',NULL),(671868,660498,'睢宁县',NULL),(671869,660498,'新沂市',NULL),(671870,660498,'邳州市',NULL),(671871,660499,'天宁区',NULL),(671872,660499,'钟楼区',NULL),(671873,660499,'戚墅堰区',NULL),(671874,660499,'新北区',NULL),(671875,660499,'武进区',NULL),(671876,660499,'溧阳市',NULL),(671877,660499,'金坛市',NULL),(671878,660500,'沧浪区',NULL),(671879,660500,'平江区',NULL),(671880,660500,'金阊区',NULL),(671881,660500,'虎丘区',NULL),(671882,660500,'吴中区',NULL),(671883,660500,'相城区',NULL),(671884,660500,'常熟市',NULL),(671885,660500,'张家港市',NULL),(671886,660500,'昆山市',NULL),(671887,660500,'吴江市',NULL),(671888,660500,'太仓市',NULL),(671889,660501,'崇川区',NULL),(671890,660501,'港闸区',NULL),(671891,660501,'通州区',NULL),(671892,660501,'海安县',NULL),(671893,660501,'如东县',NULL),(671894,660501,'启东市',NULL),(671895,660501,'如皋市',NULL),(671896,660501,'海门市',NULL),(671897,660502,'连云区',NULL),(671898,660502,'新浦区',NULL),(671899,660502,'海州区',NULL),(671900,660502,'赣榆县',NULL),(671901,660502,'东海县',NULL),(671902,660502,'灌云县',NULL),(671903,660502,'灌南县',NULL),(671904,660503,'清河区',NULL),(671905,660503,'楚州区',NULL),(671906,660503,'淮阴区',NULL),(671907,660503,'清浦区',NULL),(671908,660503,'涟水县',NULL),(671909,660503,'洪泽县',NULL),(671910,660503,'盱眙县',NULL),(671911,660503,'金湖县',NULL),(671912,660504,'亭湖区',NULL),(671913,660504,'盐都区',NULL),(671914,660504,'响水县',NULL),(671915,660504,'滨海县',NULL),(671916,660504,'阜宁县',NULL),(671917,660504,'射阳县',NULL),(671918,660504,'建湖县',NULL),(671919,660504,'东台市',NULL),(671920,660504,'大丰市',NULL),(671921,660505,'广陵区',NULL),(671922,660505,'邗江区',NULL),(671923,660505,'江都区',NULL),(671924,660505,'宝应县',NULL),(671925,660505,'仪征市',NULL),(671926,660505,'高邮市',NULL),(671927,660506,'京口区',NULL),(671928,660506,'润州区',NULL),(671929,660506,'丹徒区',NULL),(671930,660506,'丹阳市',NULL),(671931,660506,'扬中市',NULL),(671932,660506,'句容市',NULL),(671933,660507,'海陵区',NULL),(671934,660507,'高港区',NULL),(671935,660507,'兴化市',NULL),(671936,660507,'靖江市',NULL),(671937,660507,'泰兴市',NULL),(671938,660507,'姜堰市',NULL),(671939,660508,'宿城区',NULL),(671940,660508,'宿豫区',NULL),(671941,660508,'沭阳县',NULL),(671942,660508,'泗阳县',NULL),(671943,660508,'泗洪县',NULL),(671944,660509,'上城区',NULL),(671945,660509,'下城区',NULL),(671946,660509,'江干区',NULL),(671947,660509,'拱墅区',NULL),(671948,660509,'西湖区',NULL),(671949,660509,'滨江区',NULL),(671950,660509,'萧山区',NULL),(671951,660509,'余杭区',NULL),(671952,660509,'桐庐县',NULL),(671953,660509,'淳安县',NULL),(671954,660509,'建德市',NULL),(671955,660509,'富阳市',NULL),(671956,660509,'临安市',NULL),(671957,660510,'海曙区',NULL),(671958,660510,'江东区',NULL),(671959,660510,'江北区',NULL),(671960,660510,'北仑区',NULL),(671961,660510,'镇海区',NULL),(671962,660510,'鄞州区',NULL),(671963,660510,'象山县',NULL),(671964,660510,'宁海县',NULL),(671965,660510,'余姚市',NULL),(671966,660510,'慈溪市',NULL),(671967,660510,'奉化市',NULL),(671968,660511,'鹿城区',NULL),(671969,660511,'龙湾区',NULL),(671970,660511,'瓯海区',NULL),(671971,660511,'洞头县',NULL),(671972,660511,'永嘉县',NULL),(671973,660511,'平阳县',NULL),(671974,660511,'苍南县',NULL),(671975,660511,'文成县',NULL),(671976,660511,'泰顺县',NULL),(671977,660511,'瑞安市',NULL),(671978,660511,'乐清市',NULL),(671979,660512,'南湖区',NULL),(671980,660512,'秀洲区',NULL),(671981,660512,'嘉善县',NULL),(671982,660512,'海盐县',NULL),(671983,660512,'海宁市',NULL),(671984,660512,'平湖市',NULL),(671985,660512,'桐乡市',NULL),(671986,660513,'吴兴区',NULL),(671987,660513,'南浔区',NULL),(671988,660513,'德清县',NULL),(671989,660513,'长兴县',NULL),(671990,660513,'安吉县',NULL),(671991,660514,'越城区',NULL),(671992,660514,'绍兴县',NULL),(671993,660514,'新昌县',NULL),(671994,660514,'诸暨市',NULL),(671995,660514,'上虞市',NULL),(671996,660514,'嵊州市',NULL),(671997,660515,'婺城区',NULL),(671998,660515,'金东区',NULL),(671999,660515,'武义县',NULL),(672000,660515,'浦江县',NULL),(672001,660515,'磐安县',NULL),(672002,660515,'兰溪市',NULL),(672003,660515,'义乌市',NULL),(672004,660515,'东阳市',NULL),(672005,660515,'永康市',NULL),(672006,660516,'柯城区',NULL),(672007,660516,'衢江区',NULL),(672008,660516,'常山县',NULL),(672009,660516,'开化县',NULL),(672010,660516,'龙游县',NULL),(672011,660516,'江山市',NULL),(672012,660517,'定海区',NULL),(672013,660517,'普陀区',NULL),(672014,660517,'岱山县',NULL),(672015,660517,'嵊泗县',NULL),(672016,660518,'椒江区',NULL),(672017,660518,'黄岩区',NULL),(672018,660518,'路桥区',NULL),(672019,660518,'玉环县',NULL),(672020,660518,'三门县',NULL),(672021,660518,'天台县',NULL),(672022,660518,'仙居县',NULL),(672023,660518,'温岭市',NULL),(672024,660518,'临海市',NULL),(672025,660519,'莲都区',NULL),(672026,660519,'青田县',NULL),(672027,660519,'缙云县',NULL),(672028,660519,'遂昌县',NULL),(672029,660519,'松阳县',NULL),(672030,660519,'云和县',NULL),(672031,660519,'庆元县',NULL),(672032,660519,'景宁畲族自治县',NULL),(672033,660519,'龙泉市',NULL),(672034,660520,'瑶海区',NULL),(672035,660520,'庐阳区',NULL),(672036,660520,'蜀山区',NULL),(672037,660520,'包河区',NULL),(672038,660520,'长丰县',NULL),(672039,660520,'肥东县',NULL),(672040,660520,'肥西县',NULL),(672041,660520,'庐江县',NULL),(672042,660520,'巢湖市',NULL),(672043,660521,'镜湖区',NULL),(672044,660521,'弋江区',NULL),(672045,660521,'鸠江区',NULL),(672046,660521,'三山区',NULL),(672047,660521,'芜湖县',NULL),(672048,660521,'繁昌县',NULL),(672049,660521,'南陵县',NULL),(672050,660521,'无为县',NULL),(672051,660522,'龙子湖区',NULL),(672052,660522,'蚌山区',NULL),(672053,660522,'禹会区',NULL),(672054,660522,'淮上区',NULL),(672055,660522,'怀远县',NULL),(672056,660522,'五河县',NULL),(672057,660522,'固镇县',NULL),(672058,660523,'大通区',NULL),(672059,660523,'田家庵区',NULL),(672060,660523,'谢家集区',NULL),(672061,660523,'八公山区',NULL),(672062,660523,'潘集区',NULL),(672063,660523,'凤台县',NULL),(672064,660524,'金家庄区',NULL),(672065,660524,'花山区',NULL),(672066,660524,'雨山区',NULL),(672067,660524,'当涂县',NULL),(672068,660524,'含山县',NULL),(672069,660524,'和县',NULL),(672070,660525,'杜集区',NULL),(672071,660525,'相山区',NULL),(672072,660525,'烈山区',NULL),(672073,660525,'濉溪县',NULL),(672074,660526,'铜官山区',NULL),(672075,660526,'狮子山区',NULL),(672076,660526,'郊区',NULL),(672077,660526,'铜陵县',NULL),(672078,660527,'迎江区',NULL),(672079,660527,'大观区',NULL),(672080,660527,'宜秀区',NULL),(672081,660527,'怀宁县',NULL),(672082,660527,'枞阳县',NULL),(672083,660527,'潜山县',NULL),(672084,660527,'太湖县',NULL),(672085,660527,'宿松县',NULL),(672086,660527,'望江县',NULL),(672087,660527,'岳西县',NULL),(672088,660527,'桐城市',NULL),(672089,660528,'屯溪区',NULL),(672090,660528,'黄山区',NULL),(672091,660528,'徽州区',NULL),(672092,660528,'歙县',NULL),(672093,660528,'休宁县',NULL),(672094,660528,'黟县',NULL),(672095,660528,'祁门县',NULL),(672096,660529,'琅琊区',NULL),(672097,660529,'南谯区',NULL),(672098,660529,'来安县',NULL),(672099,660529,'全椒县',NULL),(672100,660529,'定远县',NULL),(672101,660529,'凤阳县',NULL),(672102,660529,'天长市',NULL),(672103,660529,'明光市',NULL),(672104,660530,'颍州区',NULL),(672105,660530,'颍东区',NULL),(672106,660530,'颍泉区',NULL),(672107,660530,'临泉县',NULL),(672108,660530,'太和县',NULL),(672109,660530,'阜南县',NULL),(672110,660530,'颍上县',NULL),(672111,660530,'界首市',NULL),(672112,660531,'埇桥区',NULL),(672113,660531,'砀山县',NULL),(672114,660531,'萧县',NULL),(672115,660531,'灵璧县',NULL),(672116,660531,'泗县',NULL),(672117,660532,'金安区',NULL),(672118,660532,'裕安区',NULL),(672119,660532,'寿县',NULL),(672120,660532,'霍邱县',NULL),(672121,660532,'舒城县',NULL),(672122,660532,'金寨县',NULL),(672123,660532,'霍山县',NULL),(672124,660533,'谯城区',NULL),(672125,660533,'涡阳县',NULL),(672126,660533,'蒙城县',NULL),(672127,660533,'利辛县',NULL),(672128,660534,'贵池区',NULL),(672129,660534,'东至县',NULL),(672130,660534,'石台县',NULL),(672131,660534,'青阳县',NULL),(672132,660535,'宣州区',NULL),(672133,660535,'郎溪县',NULL),(672134,660535,'广德县',NULL),(672135,660535,'泾县',NULL),(672136,660535,'绩溪县',NULL),(672137,660535,'旌德县',NULL),(672138,660535,'宁国市',NULL),(672139,660536,'鼓楼区',NULL),(672140,660536,'台江区',NULL),(672141,660536,'仓山区',NULL),(672142,660536,'马尾区',NULL),(672143,660536,'晋安区',NULL),(672144,660536,'闽侯县',NULL),(672145,660536,'连江县',NULL),(672146,660536,'罗源县',NULL),(672147,660536,'闽清县',NULL),(672148,660536,'永泰县',NULL),(672149,660536,'平潭县',NULL),(672150,660536,'福清市',NULL),(672151,660536,'长乐市',NULL),(672152,660537,'思明区',NULL),(672153,660537,'海沧区',NULL),(672154,660537,'湖里区',NULL),(672155,660537,'集美区',NULL),(672156,660537,'同安区',NULL),(672157,660537,'翔安区',NULL),(672158,660538,'城厢区',NULL),(672159,660538,'涵江区',NULL),(672160,660538,'荔城区',NULL),(672161,660538,'秀屿区',NULL),(672162,660538,'仙游县',NULL),(672163,660539,'梅列区',NULL),(672164,660539,'三元区',NULL),(672165,660539,'明溪县',NULL),(672166,660539,'清流县',NULL),(672167,660539,'宁化县',NULL),(672168,660539,'大田县',NULL),(672169,660539,'尤溪县',NULL),(672170,660539,'沙县',NULL),(672171,660539,'将乐县',NULL),(672172,660539,'泰宁县',NULL),(672173,660539,'建宁县',NULL),(672174,660539,'永安市',NULL),(672175,660540,'鲤城区',NULL),(672176,660540,'丰泽区',NULL),(672177,660540,'洛江区',NULL),(672178,660540,'泉港区',NULL),(672179,660540,'惠安县',NULL),(672180,660540,'安溪县',NULL),(672181,660540,'永春县',NULL),(672182,660540,'德化县',NULL),(672183,660540,'金门县',NULL),(672184,660540,'石狮市',NULL),(672185,660540,'晋江市',NULL),(672186,660540,'南安市',NULL),(672187,660541,'芗城区',NULL),(672188,660541,'龙文区',NULL),(672189,660541,'云霄县',NULL),(672190,660541,'漳浦县',NULL),(672191,660541,'诏安县',NULL),(672192,660541,'长泰县',NULL),(672193,660541,'东山县',NULL),(672194,660541,'南靖县',NULL),(672195,660541,'平和县',NULL),(672196,660541,'华安县',NULL),(672197,660541,'龙海市',NULL),(672198,660542,'延平区',NULL),(672199,660542,'顺昌县',NULL),(672200,660542,'浦城县',NULL),(672201,660542,'光泽县',NULL),(672202,660542,'松溪县',NULL),(672203,660542,'政和县',NULL),(672204,660542,'邵武市',NULL),(672205,660542,'武夷山市',NULL),(672206,660542,'建瓯市',NULL),(672207,660542,'建阳市',NULL),(672208,660543,'新罗区',NULL),(672209,660543,'长汀县',NULL),(672210,660543,'永定县',NULL),(672211,660543,'上杭县',NULL),(672212,660543,'武平县',NULL),(672213,660543,'连城县',NULL),(672214,660543,'漳平市',NULL),(672215,660544,'蕉城区',NULL),(672216,660544,'霞浦县',NULL),(672217,660544,'古田县',NULL),(672218,660544,'屏南县',NULL),(672219,660544,'寿宁县',NULL),(672220,660544,'周宁县',NULL),(672221,660544,'柘荣县',NULL),(672222,660544,'福安市',NULL),(672223,660544,'福鼎市',NULL),(672224,660545,'东湖区',NULL),(672225,660545,'西湖区',NULL),(672226,660545,'青云谱区',NULL),(672227,660545,'湾里区',NULL),(672228,660545,'青山湖区',NULL),(672229,660545,'南昌县',NULL),(672230,660545,'新建县',NULL),(672231,660545,'安义县',NULL),(672232,660545,'进贤县',NULL),(672233,660546,'昌江区',NULL),(672234,660546,'珠山区',NULL),(672235,660546,'浮梁县',NULL),(672236,660546,'乐平市',NULL),(672237,660547,'安源区',NULL),(672238,660547,'湘东区',NULL),(672239,660547,'莲花县',NULL),(672240,660547,'上栗县',NULL),(672241,660547,'芦溪县',NULL),(672242,660548,'庐山区',NULL),(672243,660548,'浔阳区',NULL),(672244,660548,'九江县',NULL),(672245,660548,'武宁县',NULL),(672246,660548,'修水县',NULL),(672247,660548,'永修县',NULL),(672248,660548,'德安县',NULL),(672249,660548,'星子县',NULL),(672250,660548,'都昌县',NULL),(672251,660548,'湖口县',NULL),(672252,660548,'彭泽县',NULL),(672253,660548,'瑞昌市',NULL),(672254,660548,'共青城市',NULL),(672255,660549,'渝水区',NULL),(672256,660549,'分宜县',NULL),(672257,660550,'月湖区',NULL),(672258,660550,'余江县',NULL),(672259,660550,'贵溪市',NULL),(672260,660551,'章贡区',NULL),(672261,660551,'赣县',NULL),(672262,660551,'信丰县',NULL),(672263,660551,'大余县',NULL),(672264,660551,'上犹县',NULL),(672265,660551,'崇义县',NULL),(672266,660551,'安远县',NULL),(672267,660551,'龙南县',NULL),(672268,660551,'定南县',NULL),(672269,660551,'全南县',NULL),(672270,660551,'宁都县',NULL),(672271,660551,'于都县',NULL),(672272,660551,'兴国县',NULL),(672273,660551,'会昌县',NULL),(672274,660551,'寻乌县',NULL),(672275,660551,'石城县',NULL),(672276,660551,'瑞金市',NULL),(672277,660551,'南康市',NULL),(672278,660552,'吉州区',NULL),(672279,660552,'青原区',NULL),(672280,660552,'吉安县',NULL),(672281,660552,'吉水县',NULL),(672282,660552,'峡江县',NULL),(672283,660552,'新干县',NULL),(672284,660552,'永丰县',NULL),(672285,660552,'泰和县',NULL),(672286,660552,'遂川县',NULL),(672287,660552,'万安县',NULL),(672288,660552,'安福县',NULL),(672289,660552,'永新县',NULL),(672290,660552,'井冈山市',NULL),(672291,660553,'袁州区',NULL),(672292,660553,'奉新县',NULL),(672293,660553,'万载县',NULL),(672294,660553,'上高县',NULL),(672295,660553,'宜丰县',NULL),(672296,660553,'靖安县',NULL),(672297,660553,'铜鼓县',NULL),(672298,660553,'丰城市',NULL),(672299,660553,'樟树市',NULL),(672300,660553,'高安市',NULL),(672301,660554,'临川区',NULL),(672302,660554,'南城县',NULL),(672303,660554,'黎川县',NULL),(672304,660554,'南丰县',NULL),(672305,660554,'崇仁县',NULL),(672306,660554,'乐安县',NULL),(672307,660554,'宜黄县',NULL),(672308,660554,'金溪县',NULL),(672309,660554,'资溪县',NULL),(672310,660554,'东乡县',NULL),(672311,660554,'广昌县',NULL),(672312,660555,'信州区',NULL),(672313,660555,'上饶县',NULL),(672314,660555,'广丰县',NULL),(672315,660555,'玉山县',NULL),(672316,660555,'铅山县',NULL),(672317,660555,'横峰县',NULL),(672318,660555,'弋阳县',NULL),(672319,660555,'余干县',NULL),(672320,660555,'鄱阳县',NULL),(672321,660555,'万年县',NULL),(672322,660555,'婺源县',NULL),(672323,660555,'德兴市',NULL),(672324,660556,'历下区',NULL),(672325,660556,'市中区',NULL),(672326,660556,'槐荫区',NULL),(672327,660556,'天桥区',NULL),(672328,660556,'历城区',NULL),(672329,660556,'长清区',NULL),(672330,660556,'平阴县',NULL),(672331,660556,'济阳县',NULL),(672332,660556,'商河县',NULL),(672333,660556,'章丘市',NULL),(672334,660557,'市南区',NULL),(672335,660557,'市北区',NULL),(672336,660557,'四方区',NULL),(672337,660557,'黄岛区',NULL),(672338,660557,'崂山区',NULL),(672339,660557,'李沧区',NULL),(672340,660557,'城阳区',NULL),(672341,660557,'胶州市',NULL),(672342,660557,'即墨市',NULL),(672343,660557,'平度市',NULL),(672344,660557,'胶南市',NULL),(672345,660557,'莱西市',NULL),(672346,660558,'淄川区',NULL),(672347,660558,'张店区',NULL),(672348,660558,'博山区',NULL),(672349,660558,'临淄区',NULL),(672350,660558,'周村区',NULL),(672351,660558,'桓台县',NULL),(672352,660558,'高青县',NULL),(672353,660558,'沂源县',NULL),(672354,660559,'市中区',NULL),(672355,660559,'薛城区',NULL),(672356,660559,'峄城区',NULL),(672357,660559,'台儿庄区',NULL),(672358,660559,'山亭区',NULL),(672359,660559,'滕州市',NULL),(672360,660560,'东营区',NULL),(672361,660560,'河口区',NULL),(672362,660560,'垦利县',NULL),(672363,660560,'利津县',NULL),(672364,660560,'广饶县',NULL),(672365,660561,'芝罘区',NULL),(672366,660561,'福山区',NULL),(672367,660561,'牟平区',NULL),(672368,660561,'莱山区',NULL),(672369,660561,'长岛县',NULL),(672370,660561,'龙口市',NULL),(672371,660561,'莱阳市',NULL),(672372,660561,'莱州市',NULL),(672373,660561,'蓬莱市',NULL),(672374,660561,'招远市',NULL),(672375,660561,'栖霞市',NULL),(672376,660561,'海阳市',NULL),(672377,660562,'潍城区',NULL),(672378,660562,'寒亭区',NULL),(672379,660562,'坊子区',NULL),(672380,660562,'奎文区',NULL),(672381,660562,'临朐县',NULL),(672382,660562,'昌乐县',NULL),(672383,660562,'青州市',NULL),(672384,660562,'诸城市',NULL),(672385,660562,'寿光市',NULL),(672386,660562,'安丘市',NULL),(672387,660562,'高密市',NULL),(672388,660562,'昌邑市',NULL),(672389,660563,'市中区',NULL),(672390,660563,'任城区',NULL),(672391,660563,'微山县',NULL),(672392,660563,'鱼台县',NULL),(672393,660563,'金乡县',NULL),(672394,660563,'嘉祥县',NULL),(672395,660563,'汶上县',NULL),(672396,660563,'泗水县',NULL),(672397,660563,'梁山县',NULL),(672398,660563,'曲阜市',NULL),(672399,660563,'兖州市',NULL),(672400,660563,'邹城市',NULL),(672401,660564,'泰山区',NULL),(672402,660564,'岱岳区',NULL),(672403,660564,'宁阳县',NULL),(672404,660564,'东平县',NULL),(672405,660564,'新泰市',NULL),(672406,660564,'肥城市',NULL),(672407,660565,'环翠区',NULL),(672408,660565,'文登市',NULL),(672409,660565,'荣成市',NULL),(672410,660565,'乳山市',NULL),(672411,660566,'东港区',NULL),(672412,660566,'岚山区',NULL),(672413,660566,'五莲县',NULL),(672414,660566,'莒县',NULL),(672415,660567,'莱城区',NULL),(672416,660567,'钢城区',NULL),(672417,660568,'兰山区',NULL),(672418,660568,'罗庄区',NULL),(672419,660568,'河东区',NULL),(672420,660568,'沂南县',NULL),(672421,660568,'郯城县',NULL),(672422,660568,'沂水县',NULL),(672423,660568,'苍山县',NULL),(672424,660568,'费县',NULL),(672425,660568,'平邑县',NULL),(672426,660568,'莒南县',NULL),(672427,660568,'蒙阴县',NULL),(672428,660568,'临沭县',NULL),(672429,660569,'德城区',NULL),(672430,660569,'陵县',NULL),(672431,660569,'宁津县',NULL),(672432,660569,'庆云县',NULL),(672433,660569,'临邑县',NULL),(672434,660569,'齐河县',NULL),(672435,660569,'平原县',NULL),(672436,660569,'夏津县',NULL),(672437,660569,'武城县',NULL),(672438,660569,'乐陵市',NULL),(672439,660569,'禹城市',NULL),(672440,660570,'东昌府区',NULL),(672441,660570,'阳谷县',NULL),(672442,660570,'莘县',NULL),(672443,660570,'茌平县',NULL),(672444,660570,'东阿县',NULL),(672445,660570,'冠县',NULL),(672446,660570,'高唐县',NULL),(672447,660570,'临清市',NULL),(672448,660571,'滨城区',NULL),(672449,660571,'惠民县',NULL),(672450,660571,'阳信县',NULL),(672451,660571,'无棣县',NULL),(672452,660571,'沾化县',NULL),(672453,660571,'博兴县',NULL),(672454,660571,'邹平县',NULL),(672455,660572,'牡丹区',NULL),(672456,660572,'曹县',NULL),(672457,660572,'单县',NULL),(672458,660572,'成武县',NULL),(672459,660572,'巨野县',NULL),(672460,660572,'郓城县',NULL),(672461,660572,'鄄城县',NULL),(672462,660572,'定陶县',NULL),(672463,660572,'东明县',NULL),(672464,660573,'中原区',NULL),(672465,660573,'二七区',NULL),(672466,660573,'管城回族区',NULL),(672467,660573,'金水区',NULL),(672468,660573,'上街区',NULL),(672469,660573,'惠济区',NULL),(672470,660573,'中牟县',NULL),(672471,660573,'巩义市',NULL),(672472,660573,'荥阳市',NULL),(672473,660573,'新密市',NULL),(672474,660573,'新郑市',NULL),(672475,660573,'登封市',NULL),(672476,660574,'龙亭区',NULL),(672477,660574,'顺河回族区',NULL),(672478,660574,'鼓楼区',NULL),(672479,660574,'禹王台区',NULL),(672480,660574,'金明区',NULL),(672481,660574,'杞县',NULL),(672482,660574,'通许县',NULL),(672483,660574,'尉氏县',NULL),(672484,660574,'开封县',NULL),(672485,660574,'兰考县',NULL),(672486,660575,'老城区',NULL),(672487,660575,'西工区',NULL),(672488,660575,'瀍河回族区',NULL),(672489,660575,'涧西区',NULL),(672490,660575,'吉利区',NULL),(672491,660575,'洛龙区',NULL),(672492,660575,'孟津县',NULL),(672493,660575,'新安县',NULL),(672494,660575,'栾川县',NULL),(672495,660575,'嵩县',NULL),(672496,660575,'汝阳县',NULL),(672497,660575,'宜阳县',NULL),(672498,660575,'洛宁县',NULL),(672499,660575,'伊川县',NULL),(672500,660575,'偃师市',NULL),(672501,660576,'新华区',NULL),(672502,660576,'卫东区',NULL),(672503,660576,'石龙区',NULL),(672504,660576,'湛河区',NULL),(672505,660576,'宝丰县',NULL),(672506,660576,'叶县',NULL),(672507,660576,'鲁山县',NULL),(672508,660576,'郏县',NULL),(672509,660576,'舞钢市',NULL),(672510,660576,'汝州市',NULL),(672511,660577,'文峰区',NULL),(672512,660577,'北关区',NULL),(672513,660577,'殷都区',NULL),(672514,660577,'龙安区',NULL),(672515,660577,'安阳县',NULL),(672516,660577,'汤阴县',NULL),(672517,660577,'滑县',NULL),(672518,660577,'内黄县',NULL),(672519,660577,'林州市',NULL),(672520,660578,'鹤山区',NULL),(672521,660578,'山城区',NULL),(672522,660578,'淇滨区',NULL),(672523,660578,'浚县',NULL),(672524,660578,'淇县',NULL),(672525,660579,'红旗区',NULL),(672526,660579,'卫滨区',NULL),(672527,660579,'凤泉区',NULL),(672528,660579,'牧野区',NULL),(672529,660579,'新乡县',NULL),(672530,660579,'获嘉县',NULL),(672531,660579,'原阳县',NULL),(672532,660579,'延津县',NULL),(672533,660579,'封丘县',NULL),(672534,660579,'长垣县',NULL),(672535,660579,'卫辉市',NULL),(672536,660579,'辉县市',NULL),(672537,660580,'解放区',NULL),(672538,660580,'中站区',NULL),(672539,660580,'马村区',NULL),(672540,660580,'山阳区',NULL),(672541,660580,'修武县',NULL),(672542,660580,'博爱县',NULL),(672543,660580,'武陟县',NULL),(672544,660580,'温县',NULL),(672545,660580,'沁阳市',NULL),(672546,660580,'孟州市',NULL),(672547,660581,'华龙区',NULL),(672548,660581,'清丰县',NULL),(672549,660581,'南乐县',NULL),(672550,660581,'范县',NULL),(672551,660581,'台前县',NULL),(672552,660581,'濮阳县',NULL),(672553,660582,'魏都区',NULL),(672554,660582,'许昌县',NULL),(672555,660582,'鄢陵县',NULL),(672556,660582,'襄城县',NULL),(672557,660582,'禹州市',NULL),(672558,660582,'长葛市',NULL),(672559,660583,'源汇区',NULL),(672560,660583,'郾城区',NULL),(672561,660583,'召陵区',NULL),(672562,660583,'舞阳县',NULL),(672563,660583,'临颍县',NULL),(672564,660584,'湖滨区',NULL),(672565,660584,'渑池县',NULL),(672566,660584,'陕县',NULL),(672567,660584,'卢氏县',NULL),(672568,660584,'义马市',NULL),(672569,660584,'灵宝市',NULL),(672570,660585,'宛城区',NULL),(672571,660585,'卧龙区',NULL),(672572,660585,'南召县',NULL),(672573,660585,'方城县',NULL),(672574,660585,'西峡县',NULL),(672575,660585,'镇平县',NULL),(672576,660585,'内乡县',NULL),(672577,660585,'淅川县',NULL),(672578,660585,'社旗县',NULL),(672579,660585,'唐河县',NULL),(672580,660585,'新野县',NULL),(672581,660585,'桐柏县',NULL),(672582,660585,'邓州市',NULL),(672583,660586,'梁园区',NULL),(672584,660586,'睢阳区',NULL),(672585,660586,'民权县',NULL),(672586,660586,'睢县',NULL),(672587,660586,'宁陵县',NULL),(672588,660586,'柘城县',NULL),(672589,660586,'虞城县',NULL),(672590,660586,'夏邑县',NULL),(672591,660586,'永城市',NULL),(672592,660587,'浉河区',NULL),(672593,660587,'平桥区',NULL),(672594,660587,'罗山县',NULL),(672595,660587,'光山县',NULL),(672596,660587,'新县',NULL),(672597,660587,'商城县',NULL),(672598,660587,'固始县',NULL),(672599,660587,'潢川县',NULL),(672600,660587,'淮滨县',NULL),(672601,660587,'息县',NULL),(672602,660588,'川汇区',NULL),(672603,660588,'扶沟县',NULL),(672604,660588,'西华县',NULL),(672605,660588,'商水县',NULL),(672606,660588,'沈丘县',NULL),(672607,660588,'郸城县',NULL),(672608,660588,'淮阳县',NULL),(672609,660588,'太康县',NULL),(672610,660588,'鹿邑县',NULL),(672611,660588,'项城市',NULL),(672612,660589,'驿城区',NULL),(672613,660589,'西平县',NULL),(672614,660589,'上蔡县',NULL),(672615,660589,'平舆县',NULL),(672616,660589,'正阳县',NULL),(672617,660589,'确山县',NULL),(672618,660589,'泌阳县',NULL),(672619,660589,'汝南县',NULL),(672620,660589,'遂平县',NULL),(672621,660589,'新蔡县',NULL),(672622,660590,'济源市',NULL),(672623,660591,'江岸区',NULL),(672624,660591,'江汉区',NULL),(672625,660591,'硚口区',NULL),(672626,660591,'汉阳区',NULL),(672627,660591,'武昌区',NULL),(672628,660591,'青山区',NULL),(672629,660591,'洪山区',NULL),(672630,660591,'东西湖区',NULL),(672631,660591,'汉南区',NULL),(672632,660591,'蔡甸区',NULL),(672633,660591,'江夏区',NULL),(672634,660591,'黄陂区',NULL),(672635,660591,'新洲区',NULL),(672636,660592,'黄石港区',NULL),(672637,660592,'西塞山区',NULL),(672638,660592,'下陆区',NULL),(672639,660592,'铁山区',NULL),(672640,660592,'阳新县',NULL),(672641,660592,'大冶市',NULL),(672642,660593,'茅箭区',NULL),(672643,660593,'张湾区',NULL),(672644,660593,'郧县',NULL),(672645,660593,'郧西县',NULL),(672646,660593,'竹山县',NULL),(672647,660593,'竹溪县',NULL),(672648,660593,'房县',NULL),(672649,660593,'丹江口市',NULL),(672650,660594,'西陵区',NULL),(672651,660594,'伍家岗区',NULL),(672652,660594,'点军区',NULL),(672653,660594,'猇亭区',NULL),(672654,660594,'夷陵区',NULL),(672655,660594,'远安县',NULL),(672656,660594,'兴山县',NULL),(672657,660594,'秭归县',NULL),(672658,660594,'长阳土家族自治县',NULL),(672659,660594,'五峰土家族自治县',NULL),(672660,660594,'宜都市',NULL),(672661,660594,'当阳市',NULL),(672662,660594,'枝江市',NULL),(672663,660595,'襄城区',NULL),(672664,660595,'樊城区',NULL),(672665,660595,'襄州区',NULL),(672666,660595,'南漳县',NULL),(672667,660595,'谷城县',NULL),(672668,660595,'保康县',NULL),(672669,660595,'老河口市',NULL),(672670,660595,'枣阳市',NULL),(672671,660595,'宜城市',NULL),(672672,660596,'梁子湖区',NULL),(672673,660596,'华容区',NULL),(672674,660596,'鄂城区',NULL),(672675,660597,'东宝区',NULL),(672676,660597,'掇刀区',NULL),(672677,660597,'京山县',NULL),(672678,660597,'沙洋县',NULL),(672679,660597,'钟祥市',NULL),(672680,660598,'孝南区',NULL),(672681,660598,'孝昌县',NULL),(672682,660598,'大悟县',NULL),(672683,660598,'云梦县',NULL),(672684,660598,'应城市',NULL),(672685,660598,'安陆市',NULL),(672686,660598,'汉川市',NULL),(672687,660599,'沙市区',NULL),(672688,660599,'荆州区',NULL),(672689,660599,'公安县',NULL),(672690,660599,'监利县',NULL),(672691,660599,'江陵县',NULL),(672692,660599,'石首市',NULL),(672693,660599,'洪湖市',NULL),(672694,660599,'松滋市',NULL),(672695,660600,'黄州区',NULL),(672696,660600,'团风县',NULL),(672697,660600,'红安县',NULL),(672698,660600,'罗田县',NULL),(672699,660600,'英山县',NULL),(672700,660600,'浠水县',NULL),(672701,660600,'蕲春县',NULL),(672702,660600,'黄梅县',NULL),(672703,660600,'麻城市',NULL),(672704,660600,'武穴市',NULL),(672705,660601,'咸安区',NULL),(672706,660601,'嘉鱼县',NULL),(672707,660601,'通城县',NULL),(672708,660601,'崇阳县',NULL),(672709,660601,'通山县',NULL),(672710,660601,'赤壁市',NULL),(672711,660602,'曾都区',NULL),(672712,660602,'随县',NULL),(672713,660602,'广水市',NULL),(672714,660603,'恩施市',NULL),(672715,660603,'利川市',NULL),(672716,660603,'建始县',NULL),(672717,660603,'巴东县',NULL),(672718,660603,'宣恩县',NULL),(672719,660603,'咸丰县',NULL),(672720,660603,'来凤县',NULL),(672721,660603,'鹤峰县',NULL),(672722,660604,'仙桃市',NULL),(672723,660604,'潜江市',NULL),(672724,660604,'天门市',NULL),(672725,660604,'神农架林区',NULL),(672726,660605,'芙蓉区',NULL),(672727,660605,'天心区',NULL),(672728,660605,'岳麓区',NULL),(672729,660605,'开福区',NULL),(672730,660605,'雨花区',NULL),(672731,660605,'望城区',NULL),(672732,660605,'长沙县',NULL),(672733,660605,'宁乡县',NULL),(672734,660605,'浏阳市',NULL),(672735,660606,'荷塘区',NULL),(672736,660606,'芦淞区',NULL),(672737,660606,'石峰区',NULL),(672738,660606,'天元区',NULL),(672739,660606,'株洲县',NULL),(672740,660606,'攸县',NULL),(672741,660606,'茶陵县',NULL),(672742,660606,'炎陵县',NULL),(672743,660606,'醴陵市',NULL),(672744,660607,'雨湖区',NULL),(672745,660607,'岳塘区',NULL),(672746,660607,'湘潭县',NULL),(672747,660607,'湘乡市',NULL),(672748,660607,'韶山市',NULL),(672749,660608,'珠晖区',NULL),(672750,660608,'雁峰区',NULL),(672751,660608,'石鼓区',NULL),(672752,660608,'蒸湘区',NULL),(672753,660608,'南岳区',NULL),(672754,660608,'衡阳县',NULL),(672755,660608,'衡南县',NULL),(672756,660608,'衡山县',NULL),(672757,660608,'衡东县',NULL),(672758,660608,'祁东县',NULL),(672759,660608,'耒阳市',NULL),(672760,660608,'常宁市',NULL),(672761,660609,'双清区',NULL),(672762,660609,'大祥区',NULL),(672763,660609,'北塔区',NULL),(672764,660609,'邵东县',NULL),(672765,660609,'新邵县',NULL),(672766,660609,'邵阳县',NULL),(672767,660609,'隆回县',NULL),(672768,660609,'洞口县',NULL),(672769,660609,'绥宁县',NULL),(672770,660609,'新宁县',NULL),(672771,660609,'城步苗族自治县',NULL),(672772,660609,'武冈市',NULL),(672773,660610,'岳阳楼区',NULL),(672774,660610,'云溪区',NULL),(672775,660610,'君山区',NULL),(672776,660610,'岳阳县',NULL),(672777,660610,'华容县',NULL),(672778,660610,'湘阴县',NULL),(672779,660610,'平江县',NULL),(672780,660610,'汨罗市',NULL),(672781,660610,'临湘市',NULL),(672782,660611,'武陵区',NULL),(672783,660611,'鼎城区',NULL),(672784,660611,'安乡县',NULL),(672785,660611,'汉寿县',NULL),(672786,660611,'澧县',NULL),(672787,660611,'临澧县',NULL),(672788,660611,'桃源县',NULL),(672789,660611,'石门县',NULL),(672790,660611,'津市市',NULL),(672791,660612,'永定区',NULL),(672792,660612,'武陵源区',NULL),(672793,660612,'慈利县',NULL),(672794,660612,'桑植县',NULL),(672795,660613,'资阳区',NULL),(672796,660613,'赫山区',NULL),(672797,660613,'南县',NULL),(672798,660613,'桃江县',NULL),(672799,660613,'安化县',NULL),(672800,660613,'沅江市',NULL),(672801,660614,'北湖区',NULL),(672802,660614,'苏仙区',NULL),(672803,660614,'桂阳县',NULL),(672804,660614,'宜章县',NULL),(672805,660614,'永兴县',NULL),(672806,660614,'嘉禾县',NULL),(672807,660614,'临武县',NULL),(672808,660614,'汝城县',NULL),(672809,660614,'桂东县',NULL),(672810,660614,'安仁县',NULL),(672811,660614,'资兴市',NULL),(672812,660615,'零陵区',NULL),(672813,660615,'冷水滩区',NULL),(672814,660615,'祁阳县',NULL),(672815,660615,'东安县',NULL),(672816,660615,'双牌县',NULL),(672817,660615,'道县',NULL),(672818,660615,'江永县',NULL),(672819,660615,'宁远县',NULL),(672820,660615,'蓝山县',NULL),(672821,660615,'新田县',NULL),(672822,660615,'江华瑶族自治县',NULL),(672823,660616,'鹤城区',NULL),(672824,660616,'中方县',NULL),(672825,660616,'沅陵县',NULL),(672826,660616,'辰溪县',NULL),(672827,660616,'溆浦县',NULL),(672828,660616,'会同县',NULL),(672829,660616,'麻阳苗族自治县',NULL),(672830,660616,'新晃侗族自治县',NULL),(672831,660616,'芷江侗族自治县',NULL),(672832,660616,'靖州苗族侗族自治县',NULL),(672833,660616,'通道侗族自治县',NULL),(672834,660616,'洪江市',NULL),(672835,660617,'娄星区',NULL),(672836,660617,'双峰县',NULL),(672837,660617,'新化县',NULL),(672838,660617,'冷水江市',NULL),(672839,660617,'涟源市',NULL),(672840,660618,'吉首市',NULL),(672841,660618,'泸溪县',NULL),(672842,660618,'凤凰县',NULL),(672843,660618,'花垣县',NULL),(672844,660618,'保靖县',NULL),(672845,660618,'古丈县',NULL),(672846,660618,'永顺县',NULL),(672847,660618,'龙山县',NULL),(672848,660619,'荔湾区',NULL),(672849,660619,'越秀区',NULL),(672850,660619,'海珠区',NULL),(672851,660619,'天河区',NULL),(672852,660619,'白云区',NULL),(672853,660619,'黄埔区',NULL),(672854,660619,'番禺区',NULL),(672855,660619,'花都区',NULL),(672856,660619,'南沙区',NULL),(672857,660619,'萝岗区',NULL),(672858,660619,'增城市',NULL),(672859,660619,'从化市',NULL),(672860,660620,'武江区',NULL),(672861,660620,'浈江区',NULL),(672862,660620,'曲江区',NULL),(672863,660620,'始兴县',NULL),(672864,660620,'仁化县',NULL),(672865,660620,'翁源县',NULL),(672866,660620,'乳源瑶族自治县',NULL),(672867,660620,'新丰县',NULL),(672868,660620,'乐昌市',NULL),(672869,660620,'南雄市',NULL),(672870,660621,'罗湖区',NULL),(672871,660621,'福田区',NULL),(672872,660621,'南山区',NULL),(672873,660621,'宝安区',NULL),(672874,660621,'龙岗区',NULL),(672875,660621,'盐田区',NULL),(672876,660622,'香洲区',NULL),(672877,660622,'斗门区',NULL),(672878,660622,'金湾区',NULL),(672879,660623,'龙湖区',NULL),(672880,660623,'金平区',NULL),(672881,660623,'濠江区',NULL),(672882,660623,'潮阳区',NULL),(672883,660623,'潮南区',NULL),(672884,660623,'澄海区',NULL),(672885,660623,'南澳县',NULL),(672886,660624,'禅城区',NULL),(672887,660624,'南海区',NULL),(672888,660624,'顺德区',NULL),(672889,660624,'三水区',NULL),(672890,660624,'高明区',NULL),(672891,660625,'蓬江区',NULL),(672892,660625,'江海区',NULL),(672893,660625,'新会区',NULL),(672894,660625,'台山市',NULL),(672895,660625,'开平市',NULL),(672896,660625,'鹤山市',NULL),(672897,660625,'恩平市',NULL),(672898,660626,'赤坎区',NULL),(672899,660626,'霞山区',NULL),(672900,660626,'坡头区',NULL),(672901,660626,'麻章区',NULL),(672902,660626,'遂溪县',NULL),(672903,660626,'徐闻县',NULL),(672904,660626,'廉江市',NULL),(672905,660626,'雷州市',NULL),(672906,660626,'吴川市',NULL),(672907,660627,'茂南区',NULL),(672908,660627,'茂港区',NULL),(672909,660627,'电白县',NULL),(672910,660627,'高州市',NULL),(672911,660627,'化州市',NULL),(672912,660627,'信宜市',NULL),(672913,660628,'端州区',NULL),(672914,660628,'鼎湖区',NULL),(672915,660628,'广宁县',NULL),(672916,660628,'怀集县',NULL),(672917,660628,'封开县',NULL),(672918,660628,'德庆县',NULL),(672919,660628,'高要市',NULL),(672920,660628,'四会市',NULL),(672921,660629,'惠城区',NULL),(672922,660629,'惠阳区',NULL),(672923,660629,'博罗县',NULL),(672924,660629,'惠东县',NULL),(672925,660629,'龙门县',NULL),(672926,660630,'梅江区',NULL),(672927,660630,'梅县',NULL),(672928,660630,'大埔县',NULL),(672929,660630,'丰顺县',NULL),(672930,660630,'五华县',NULL),(672931,660630,'平远县',NULL),(672932,660630,'蕉岭县',NULL),(672933,660630,'兴宁市',NULL),(672934,660631,'城区',NULL),(672935,660631,'海丰县',NULL),(672936,660631,'陆河县',NULL),(672937,660631,'陆丰市',NULL),(672938,660632,'源城区',NULL),(672939,660632,'紫金县',NULL),(672940,660632,'龙川县',NULL),(672941,660632,'连平县',NULL),(672942,660632,'和平县',NULL),(672943,660632,'东源县',NULL),(672944,660633,'江城区',NULL),(672945,660633,'阳西县',NULL),(672946,660633,'阳东县',NULL),(672947,660633,'阳春市',NULL),(672948,660634,'清城区',NULL),(672949,660634,'佛冈县',NULL),(672950,660634,'阳山县',NULL),(672951,660634,'连山壮族瑶族自治县',NULL),(672952,660634,'连南瑶族自治县',NULL),(672953,660634,'清新县',NULL),(672954,660634,'英德市',NULL),(672955,660634,'连州市',NULL),(672956,660637,'湘桥区',NULL),(672957,660637,'潮安县',NULL),(672958,660637,'饶平县',NULL),(672959,660638,'榕城区',NULL),(672960,660638,'揭东县',NULL),(672961,660638,'揭西县',NULL),(672962,660638,'惠来县',NULL),(672963,660638,'普宁市',NULL),(672964,660639,'云城区',NULL),(672965,660639,'新兴县',NULL),(672966,660639,'郁南县',NULL),(672967,660639,'云安县',NULL),(672968,660639,'罗定市',NULL),(672969,660640,'兴宁区',NULL),(672970,660640,'青秀区',NULL),(672971,660640,'江南区',NULL),(672972,660640,'西乡塘区',NULL),(672973,660640,'良庆区',NULL),(672974,660640,'邕宁区',NULL),(672975,660640,'武鸣县',NULL),(672976,660640,'隆安县',NULL),(672977,660640,'马山县',NULL),(672978,660640,'上林县',NULL),(672979,660640,'宾阳县',NULL),(672980,660640,'横县',NULL),(672981,660641,'城中区',NULL),(672982,660641,'鱼峰区',NULL),(672983,660641,'柳南区',NULL),(672984,660641,'柳北区',NULL),(672985,660641,'柳江县',NULL),(672986,660641,'柳城县',NULL),(672987,660641,'鹿寨县',NULL),(672988,660641,'融安县',NULL),(672989,660641,'融水苗族自治县',NULL),(672990,660641,'三江侗族自治县',NULL),(672991,660642,'秀峰区',NULL),(672992,660642,'叠彩区',NULL),(672993,660642,'象山区',NULL),(672994,660642,'七星区',NULL),(672995,660642,'雁山区',NULL),(672996,660642,'阳朔县',NULL),(672997,660642,'临桂县',NULL),(672998,660642,'灵川县',NULL),(672999,660642,'全州县',NULL),(673000,660642,'兴安县',NULL),(673001,660642,'永福县',NULL),(673002,660642,'灌阳县',NULL),(673003,660642,'龙胜各族自治县',NULL),(673004,660642,'资源县',NULL),(673005,660642,'平乐县',NULL),(673006,660642,'荔蒲县',NULL),(673007,660642,'恭城瑶族自治县',NULL),(673008,660643,'万秀区',NULL),(673009,660643,'蝶山区',NULL),(673010,660643,'长洲区',NULL),(673011,660643,'苍梧县',NULL),(673012,660643,'藤县',NULL),(673013,660643,'蒙山县',NULL),(673014,660643,'岑溪市',NULL),(673015,660644,'海城区',NULL),(673016,660644,'银海区',NULL),(673017,660644,'铁山港区',NULL),(673018,660644,'合浦县',NULL),(673019,660645,'港口区',NULL),(673020,660645,'防城区',NULL),(673021,660645,'上思县',NULL),(673022,660645,'东兴市',NULL),(673023,660646,'钦南区',NULL),(673024,660646,'钦北区',NULL),(673025,660646,'灵山县',NULL),(673026,660646,'浦北县',NULL),(673027,660647,'港北区',NULL),(673028,660647,'港南区',NULL),(673029,660647,'覃塘区',NULL),(673030,660647,'平南县',NULL),(673031,660647,'桂平市',NULL),(673032,660648,'玉州区',NULL),(673033,660648,'容县',NULL),(673034,660648,'陆川县',NULL),(673035,660648,'博白县',NULL),(673036,660648,'兴业县',NULL),(673037,660648,'北流市',NULL),(673038,660649,'右江区',NULL),(673039,660649,'田阳县',NULL),(673040,660649,'田东县',NULL),(673041,660649,'平果县',NULL),(673042,660649,'德保县',NULL),(673043,660649,'靖西县',NULL),(673044,660649,'那坡县',NULL),(673045,660649,'凌云县',NULL),(673046,660649,'乐业县',NULL),(673047,660649,'田林县',NULL),(673048,660649,'西林县',NULL),(673049,660649,'隆林各族自治县',NULL),(673050,660650,'八步区',NULL),(673051,660650,'昭平县',NULL),(673052,660650,'钟山县',NULL),(673053,660650,'富川瑶族自治县',NULL),(673054,660651,'金城江区',NULL),(673055,660651,'南丹县',NULL),(673056,660651,'天峨县',NULL),(673057,660651,'凤山县',NULL),(673058,660651,'东兰县',NULL),(673059,660651,'罗城仫佬族自治县',NULL),(673060,660651,'环江毛南族自治县',NULL),(673061,660651,'巴马瑶族自治县',NULL),(673062,660651,'都安瑶族自治县',NULL),(673063,660651,'大化瑶族自治县',NULL),(673064,660651,'宜州市',NULL),(673065,660652,'兴宾区',NULL),(673066,660652,'忻城县',NULL),(673067,660652,'象州县',NULL),(673068,660652,'武宣县',NULL),(673069,660652,'金秀瑶族自治县',NULL),(673070,660652,'合山市',NULL),(673071,660653,'江洲区',NULL),(673072,660653,'扶绥县',NULL),(673073,660653,'宁明县',NULL),(673074,660653,'龙州县',NULL),(673075,660653,'大新县',NULL),(673076,660653,'天等县',NULL),(673077,660653,'凭祥市',NULL),(673078,660654,'秀英区',NULL),(673079,660654,'龙华区',NULL),(673080,660654,'琼山区',NULL),(673081,660654,'美兰区',NULL),(673082,660656,'五指山市',NULL),(673083,660656,'琼海市',NULL),(673084,660656,'儋州市',NULL),(673085,660656,'文昌市',NULL),(673086,660656,'万宁市',NULL),(673087,660656,'东方市',NULL),(673088,660656,'定安县',NULL),(673089,660656,'屯昌县',NULL),(673090,660656,'澄迈县',NULL),(673091,660656,'临高县',NULL),(673092,660656,'白沙黎族自治县',NULL),(673093,660656,'昌江黎族自治县',NULL),(673094,660656,'乐东黎族自治县',NULL),(673095,660656,'陵水黎族自治县',NULL),(673096,660656,'保亭黎族苗族自治县',NULL),(673097,660656,'琼中黎族苗族自治县',NULL),(673098,660656,'西沙群岛',NULL),(673099,660656,'南沙群岛',NULL),(673100,660656,'中沙群岛的岛礁及其海域',NULL),(673101,660657,'万州区',NULL),(673102,660657,'涪陵区',NULL),(673103,660657,'渝中区',NULL),(673104,660657,'大渡口区',NULL),(673105,660657,'江北区',NULL),(673106,660657,'沙坪坝区',NULL),(673107,660657,'九龙坡区',NULL),(673108,660657,'南岸区',NULL),(673109,660657,'北碚区',NULL),(673110,660657,'綦江区',NULL),(673111,660657,'大足区',NULL),(673112,660657,'渝北区',NULL),(673113,660657,'巴南区',NULL),(673114,660657,'黔江区',NULL),(673115,660657,'长寿区',NULL),(673116,660657,'江津区',NULL),(673117,660657,'合川区',NULL),(673118,660657,'永川区',NULL),(673119,660657,'南川区',NULL),(673120,660658,'潼南县',NULL),(673121,660658,'铜梁县',NULL),(673122,660658,'荣昌县',NULL),(673123,660658,'璧山县',NULL),(673124,660658,'梁平县',NULL),(673125,660658,'城口县',NULL),(673126,660658,'丰都县',NULL),(673127,660658,'垫江县',NULL),(673128,660658,'武隆县',NULL),(673129,660658,'忠县',NULL),(673130,660658,'开县',NULL),(673131,660658,'云阳县',NULL),(673132,660658,'奉节县',NULL),(673133,660658,'巫山县',NULL),(673134,660658,'巫溪县',NULL),(673135,660658,'石柱土家族自治县',NULL),(673136,660658,'秀山土家族苗族自治县',NULL),(673137,660658,'酉阳土家族苗族自治县',NULL),(673138,660658,'彭水苗族土家族自治县',NULL),(673139,660659,'锦江区',NULL),(673140,660659,'青羊区',NULL),(673141,660659,'金牛区',NULL),(673142,660659,'武侯区',NULL),(673143,660659,'成华区',NULL),(673144,660659,'龙泉驿区',NULL),(673145,660659,'青白江区',NULL),(673146,660659,'新都区',NULL),(673147,660659,'温江区',NULL),(673148,660659,'金堂县',NULL),(673149,660659,'双流县',NULL),(673150,660659,'郫县',NULL),(673151,660659,'大邑县',NULL),(673152,660659,'蒲江县',NULL),(673153,660659,'新津县',NULL),(673154,660659,'都江堰市',NULL),(673155,660659,'彭州市',NULL),(673156,660659,'邛崃市',NULL),(673157,660659,'崇州市',NULL),(673158,660660,'自流井区',NULL),(673159,660660,'贡井区',NULL),(673160,660660,'大安区',NULL),(673161,660660,'沿滩区',NULL),(673162,660660,'荣县',NULL),(673163,660660,'富顺县',NULL),(673164,660661,'东区',NULL),(673165,660661,'西区',NULL),(673166,660661,'仁和区',NULL),(673167,660661,'米易县',NULL),(673168,660661,'盐边县',NULL),(673169,660662,'江阳区',NULL),(673170,660662,'纳溪区',NULL),(673171,660662,'龙马潭区',NULL),(673172,660662,'泸县',NULL),(673173,660662,'合江县',NULL),(673174,660662,'叙永县',NULL),(673175,660662,'古蔺县',NULL),(673176,660663,'旌阳区',NULL),(673177,660663,'中江县',NULL),(673178,660663,'罗江县',NULL),(673179,660663,'广汉市',NULL),(673180,660663,'什邡市',NULL),(673181,660663,'绵竹市',NULL),(673182,660664,'涪城区',NULL),(673183,660664,'游仙区',NULL),(673184,660664,'三台县',NULL),(673185,660664,'盐亭县',NULL),(673186,660664,'安县',NULL),(673187,660664,'梓潼县',NULL),(673188,660664,'北川羌族自治县',NULL),(673189,660664,'平武县',NULL),(673190,660664,'江油市',NULL),(673191,660665,'利州区',NULL),(673192,660665,'元坝区',NULL),(673193,660665,'朝天区',NULL),(673194,660665,'旺苍县',NULL),(673195,660665,'青川县',NULL),(673196,660665,'剑阁县',NULL),(673197,660665,'苍溪县',NULL),(673198,660666,'船山区',NULL),(673199,660666,'安居区',NULL),(673200,660666,'蓬溪县',NULL),(673201,660666,'射洪县',NULL),(673202,660666,'大英县',NULL),(673203,660667,'市中区',NULL),(673204,660667,'东兴区',NULL),(673205,660667,'威远县',NULL),(673206,660667,'资中县',NULL),(673207,660667,'隆昌县',NULL),(673208,660668,'市中区',NULL),(673209,660668,'沙湾区',NULL),(673210,660668,'五通桥区',NULL),(673211,660668,'金口河区',NULL),(673212,660668,'犍为县',NULL),(673213,660668,'井研县',NULL),(673214,660668,'夹江县',NULL),(673215,660668,'沐川县',NULL),(673216,660668,'峨边彝族自治县',NULL),(673217,660668,'马边彝族自治县',NULL),(673218,660668,'峨眉山市',NULL),(673219,660669,'顺庆区',NULL),(673220,660669,'高坪区',NULL),(673221,660669,'嘉陵区',NULL),(673222,660669,'南部县',NULL),(673223,660669,'营山县',NULL),(673224,660669,'蓬安县',NULL),(673225,660669,'仪陇县',NULL),(673226,660669,'西充县',NULL),(673227,660669,'阆中市',NULL),(673228,660670,'东坡区',NULL),(673229,660670,'仁寿县',NULL),(673230,660670,'彭山县',NULL),(673231,660670,'洪雅县',NULL),(673232,660670,'丹棱县',NULL),(673233,660670,'青神县',NULL),(673234,660671,'翠屏区',NULL),(673235,660671,'南溪区',NULL),(673236,660671,'宜宾县',NULL),(673237,660671,'江安县',NULL),(673238,660671,'长宁县',NULL),(673239,660671,'高县',NULL),(673240,660671,'珙县',NULL),(673241,660671,'筠连县',NULL),(673242,660671,'兴文县',NULL),(673243,660671,'屏山县',NULL),(673244,660672,'广安区',NULL),(673245,660672,'岳池县',NULL),(673246,660672,'武胜县',NULL),(673247,660672,'邻水县',NULL),(673248,660672,'华蓥市',NULL),(673249,660673,'通川区',NULL),(673250,660673,'达县',NULL),(673251,660673,'宣汉县',NULL),(673252,660673,'开江县',NULL),(673253,660673,'大竹县',NULL),(673254,660673,'渠县',NULL),(673255,660673,'万源市',NULL),(673256,660674,'雨城区',NULL),(673257,660674,'名山县',NULL),(673258,660674,'荥经县',NULL),(673259,660674,'汉源县',NULL),(673260,660674,'石棉县',NULL),(673261,660674,'天全县',NULL),(673262,660674,'芦山县',NULL),(673263,660674,'宝兴县',NULL),(673264,660675,'巴州区',NULL),(673265,660675,'通江县',NULL),(673266,660675,'南江县',NULL),(673267,660675,'平昌县',NULL),(673268,660676,'雁江区',NULL),(673269,660676,'安岳县',NULL),(673270,660676,'乐至县',NULL),(673271,660676,'简阳市',NULL),(673272,660677,'汶川县',NULL),(673273,660677,'理县',NULL),(673274,660677,'茂县',NULL),(673275,660677,'松潘县',NULL),(673276,660677,'九寨沟县',NULL),(673277,660677,'金川县',NULL),(673278,660677,'小金县',NULL),(673279,660677,'黑水县',NULL),(673280,660677,'马尔康县',NULL),(673281,660677,'壤塘县',NULL),(673282,660677,'阿坝县',NULL),(673283,660677,'若尔盖县',NULL),(673284,660677,'红原县',NULL),(673285,660678,'康定县',NULL),(673286,660678,'泸定县',NULL),(673287,660678,'丹巴县',NULL),(673288,660678,'九龙县',NULL),(673289,660678,'雅江县',NULL),(673290,660678,'道孚县',NULL),(673291,660678,'炉霍县',NULL),(673292,660678,'甘孜县',NULL),(673293,660678,'新龙县',NULL),(673294,660678,'德格县',NULL),(673295,660678,'白玉县',NULL),(673296,660678,'石渠县',NULL),(673297,660678,'色达县',NULL),(673298,660678,'理塘县',NULL),(673299,660678,'巴塘县',NULL),(673300,660678,'乡城县',NULL),(673301,660678,'稻城县',NULL),(673302,660678,'得荣县',NULL),(673303,660679,'西昌市',NULL),(673304,660679,'木里藏族自治县',NULL),(673305,660679,'盐源县',NULL),(673306,660679,'德昌县',NULL),(673307,660679,'会理县',NULL),(673308,660679,'会东县',NULL),(673309,660679,'宁南县',NULL),(673310,660679,'普格县',NULL),(673311,660679,'布拖县',NULL),(673312,660679,'金阳县',NULL),(673313,660679,'昭觉县',NULL),(673314,660679,'喜德县',NULL),(673315,660679,'冕宁县',NULL),(673316,660679,'越西县',NULL),(673317,660679,'甘洛县',NULL),(673318,660679,'美姑县',NULL),(673319,660679,'雷波县',NULL),(673320,660680,'南明区',NULL),(673321,660680,'云岩区',NULL),(673322,660680,'花溪区',NULL),(673323,660680,'乌当区',NULL),(673324,660680,'白云区',NULL),(673325,660680,'小河区',NULL),(673326,660680,'开阳县',NULL),(673327,660680,'息烽县',NULL),(673328,660680,'修文县',NULL),(673329,660680,'清镇市',NULL),(673330,660681,'钟山区',NULL),(673331,660681,'六枝特区',NULL),(673332,660681,'水城县',NULL),(673333,660681,'盘县',NULL),(673334,660682,'红花岗区',NULL),(673335,660682,'汇川区',NULL),(673336,660682,'遵义县',NULL),(673337,660682,'桐梓县',NULL),(673338,660682,'绥阳县',NULL),(673339,660682,'正安县',NULL),(673340,660682,'道真仡佬族苗族自治县',NULL),(673341,660682,'务川仡佬族苗族自治县',NULL),(673342,660682,'凤冈县',NULL),(673343,660682,'湄潭县',NULL),(673344,660682,'余庆县',NULL),(673345,660682,'习水县',NULL),(673346,660682,'赤水市',NULL),(673347,660682,'仁怀市',NULL),(673348,660683,'西秀区',NULL),(673349,660683,'平坝县',NULL),(673350,660683,'普定县',NULL),(673351,660683,'镇宁布依族苗族自治县',NULL),(673352,660683,'关岭布依族苗族自治县',NULL),(673353,660683,'紫云苗族布依族自治县',NULL),(673354,660684,'七星关区',NULL),(673355,660684,'大方县',NULL),(673356,660684,'黔西县',NULL),(673357,660684,'金沙县',NULL),(673358,660684,'织金县',NULL),(673359,660684,'纳雍县',NULL),(673360,660684,'威宁彝族回族苗族自治县',NULL),(673361,660684,'赫章县',NULL),(673362,660685,'碧江区',NULL),(673363,660685,'万山区',NULL),(673364,660685,'江口县',NULL),(673365,660685,'玉屏侗族自治县',NULL),(673366,660685,'石阡县',NULL),(673367,660685,'思南县',NULL),(673368,660685,'印江土家族苗族自治县',NULL),(673369,660685,'德江县',NULL),(673370,660685,'沿河土家族自治县',NULL),(673371,660685,'松桃苗族自治县',NULL),(673372,660686,'兴义市',NULL),(673373,660686,'兴仁县',NULL),(673374,660686,'普安县',NULL),(673375,660686,'晴隆县',NULL),(673376,660686,'贞丰县',NULL),(673377,660686,'望谟县',NULL),(673378,660686,'册亨县',NULL),(673379,660686,'安龙县',NULL),(673380,660687,'凯里市',NULL),(673381,660687,'黄平县',NULL),(673382,660687,'施秉县',NULL),(673383,660687,'三穗县',NULL),(673384,660687,'镇远县',NULL),(673385,660687,'岑巩县',NULL),(673386,660687,'天柱县',NULL),(673387,660687,'锦屏县',NULL),(673388,660687,'剑河县',NULL),(673389,660687,'台江县',NULL),(673390,660687,'黎平县',NULL),(673391,660687,'榕江县',NULL),(673392,660687,'从江县',NULL),(673393,660687,'雷山县',NULL),(673394,660687,'麻江县',NULL),(673395,660687,'丹寨县',NULL),(673396,660688,'都匀市',NULL),(673397,660688,'福泉市',NULL),(673398,660688,'荔波县',NULL),(673399,660688,'贵定县',NULL),(673400,660688,'瓮安县',NULL),(673401,660688,'独山县',NULL),(673402,660688,'平塘县',NULL),(673403,660688,'罗甸县',NULL),(673404,660688,'长顺县',NULL),(673405,660688,'龙里县',NULL),(673406,660688,'惠水县',NULL),(673407,660688,'三都水族自治县',NULL),(673408,660689,'五华区',NULL),(673409,660689,'盘龙区',NULL),(673410,660689,'官渡区',NULL),(673411,660689,'西山区',NULL),(673412,660689,'东川区',NULL),(673413,660689,'呈贡区',NULL),(673414,660689,'晋宁县',NULL),(673415,660689,'富民县',NULL),(673416,660689,'宜良县',NULL),(673417,660689,'石林彝族自治县',NULL),(673418,660689,'嵩明县',NULL),(673419,660689,'禄劝彝族苗族自治县',NULL),(673420,660689,'寻甸回族彝族自治县',NULL),(673421,660689,'安宁市',NULL),(673422,660690,'麒麟区',NULL),(673423,660690,'马龙县',NULL),(673424,660690,'陆良县',NULL),(673425,660690,'师宗县',NULL),(673426,660690,'罗平县',NULL),(673427,660690,'富源县',NULL),(673428,660690,'会泽县',NULL),(673429,660690,'沾益县',NULL),(673430,660690,'宣威市',NULL),(673431,660691,'红塔区',NULL),(673432,660691,'江川县',NULL),(673433,660691,'澄江县',NULL),(673434,660691,'通海县',NULL),(673435,660691,'华宁县',NULL),(673436,660691,'易门县',NULL),(673437,660691,'峨山彝族自治县',NULL),(673438,660691,'新平彝族傣族自治县',NULL),(673439,660691,'元江哈尼族彝族傣族自治县',NULL),(673440,660692,'隆阳区',NULL),(673441,660692,'施甸县',NULL),(673442,660692,'腾冲县',NULL),(673443,660692,'龙陵县',NULL),(673444,660692,'昌宁县',NULL),(673445,660693,'昭阳区',NULL),(673446,660693,'鲁甸县',NULL),(673447,660693,'巧家县',NULL),(673448,660693,'盐津县',NULL),(673449,660693,'大关县',NULL),(673450,660693,'永善县',NULL),(673451,660693,'绥江县',NULL),(673452,660693,'镇雄县',NULL),(673453,660693,'彝良县',NULL),(673454,660693,'威信县',NULL),(673455,660693,'水富县',NULL),(673456,660694,'古城区',NULL),(673457,660694,'玉龙纳西族自治县',NULL),(673458,660694,'永胜县',NULL),(673459,660694,'华坪县',NULL),(673460,660694,'宁蒗彝族自治县',NULL),(673461,660695,'思茅区',NULL),(673462,660695,'宁洱哈尼族彝族自治县',NULL),(673463,660695,'墨江哈尼族自治县',NULL),(673464,660695,'景东彝族自治县',NULL),(673465,660695,'景谷傣族彝族自治县',NULL),(673466,660695,'镇沅彝族哈尼族拉祜族自治县',NULL),(673467,660695,'江城哈尼族彝族自治县',NULL),(673468,660695,'孟连傣族拉祜族佤族自治县',NULL),(673469,660695,'澜沧拉祜族自治县',NULL),(673470,660695,'西盟佤族自治县',NULL),(673471,660696,'临翔区',NULL),(673472,660696,'凤庆县',NULL),(673473,660696,'云县',NULL),(673474,660696,'永德县',NULL),(673475,660696,'镇康县',NULL),(673476,660696,'双江拉祜族佤族布朗族傣族自治县',NULL),(673477,660696,'耿马傣族佤族自治县',NULL),(673478,660696,'沧源佤族自治县',NULL),(673479,660697,'楚雄市',NULL),(673480,660697,'双柏县',NULL),(673481,660697,'牟定县',NULL),(673482,660697,'南华县',NULL),(673483,660697,'姚安县',NULL),(673484,660697,'大姚县',NULL),(673485,660697,'永仁县',NULL),(673486,660697,'元谋县',NULL),(673487,660697,'武定县',NULL),(673488,660697,'禄丰县',NULL),(673489,660698,'个旧市',NULL),(673490,660698,'开远市',NULL),(673491,660698,'蒙自市',NULL),(673492,660698,'屏边苗族自治县',NULL),(673493,660698,'建水县',NULL),(673494,660698,'石屏县',NULL),(673495,660698,'弥勒县',NULL),(673496,660698,'泸西县',NULL),(673497,660698,'元阳县',NULL),(673498,660698,'红河县',NULL),(673499,660698,'金平苗族瑶族傣族自治县',NULL),(673500,660698,'绿春县',NULL),(673501,660698,'河口瑶族自治县',NULL),(673502,660699,'文山市',NULL),(673503,660699,'砚山县',NULL),(673504,660699,'西畴县',NULL),(673505,660699,'麻栗坡县',NULL),(673506,660699,'马关县',NULL),(673507,660699,'丘北县',NULL),(673508,660699,'广南县',NULL),(673509,660699,'富宁县',NULL),(673510,660700,'景洪市',NULL),(673511,660700,'勐海县',NULL),(673512,660700,'勐腊县',NULL),(673513,660701,'大理市',NULL),(673514,660701,'漾濞彝族自治县',NULL),(673515,660701,'祥云县',NULL),(673516,660701,'宾川县',NULL),(673517,660701,'弥渡县',NULL),(673518,660701,'南涧彝族自治县',NULL),(673519,660701,'巍山彝族回族自治县',NULL),(673520,660701,'永平县',NULL),(673521,660701,'云龙县',NULL),(673522,660701,'洱源县',NULL),(673523,660701,'剑川县',NULL),(673524,660701,'鹤庆县',NULL),(673525,660702,'瑞丽市',NULL),(673526,660702,'芒市',NULL),(673527,660702,'梁河县',NULL),(673528,660702,'盈江县',NULL),(673529,660702,'陇川县',NULL),(673530,660703,'泸水县',NULL),(673531,660703,'福贡县',NULL),(673532,660703,'贡山独龙族怒族自治县',NULL),(673533,660703,'兰坪白族普米族自治县',NULL),(673534,660704,'香格里拉县',NULL),(673535,660704,'德钦县',NULL),(673536,660704,'维西傈僳族自治县',NULL),(673537,660705,'城关区',NULL),(673538,660705,'林周县',NULL),(673539,660705,'当雄县',NULL),(673540,660705,'尼木县',NULL),(673541,660705,'曲水县',NULL),(673542,660705,'堆龙德庆县',NULL),(673543,660705,'达孜县',NULL),(673544,660705,'墨竹工卡县',NULL),(673545,660706,'昌都县',NULL),(673546,660706,'江达县',NULL),(673547,660706,'贡觉县',NULL),(673548,660706,'类乌齐县',NULL),(673549,660706,'丁青县',NULL),(673550,660706,'察雅县',NULL),(673551,660706,'八宿县',NULL),(673552,660706,'左贡县',NULL),(673553,660706,'芒康县',NULL),(673554,660706,'洛隆县',NULL),(673555,660706,'边坝县',NULL),(673556,660707,'乃东县',NULL),(673557,660707,'扎囊县',NULL),(673558,660707,'贡嘎县',NULL),(673559,660707,'桑日县',NULL),(673560,660707,'琼结县',NULL),(673561,660707,'曲松县',NULL),(673562,660707,'措美县',NULL),(673563,660707,'洛扎县',NULL),(673564,660707,'加查县',NULL),(673565,660707,'隆子县',NULL),(673566,660707,'错那县',NULL),(673567,660707,'浪卡子县',NULL),(673568,660708,'日喀则市',NULL),(673569,660708,'南木林县',NULL),(673570,660708,'江孜县',NULL),(673571,660708,'定日县',NULL),(673572,660708,'萨迦县',NULL),(673573,660708,'拉孜县',NULL),(673574,660708,'昂仁县',NULL),(673575,660708,'谢通门县',NULL),(673576,660708,'白朗县',NULL),(673577,660708,'仁布县',NULL),(673578,660708,'康马县',NULL),(673579,660708,'定结县',NULL),(673580,660708,'仲巴县',NULL),(673581,660708,'亚东县',NULL),(673582,660708,'吉隆县',NULL),(673583,660708,'聂拉木县',NULL),(673584,660708,'萨嘎县',NULL),(673585,660708,'岗巴县',NULL),(673586,660709,'那曲县',NULL),(673587,660709,'嘉黎县',NULL),(673588,660709,'比如县',NULL),(673589,660709,'聂荣县',NULL),(673590,660709,'安多县',NULL),(673591,660709,'申扎县',NULL),(673592,660709,'索县',NULL),(673593,660709,'班戈县',NULL),(673594,660709,'巴青县',NULL),(673595,660709,'尼玛县',NULL),(673596,660710,'普兰县',NULL),(673597,660710,'札达县',NULL),(673598,660710,'噶尔县',NULL),(673599,660710,'日土县',NULL),(673600,660710,'革吉县',NULL),(673601,660710,'改则县',NULL),(673602,660710,'措勤县',NULL),(673603,660711,'林芝县',NULL),(673604,660711,'工布江达县',NULL),(673605,660711,'米林县',NULL),(673606,660711,'墨脱县',NULL),(673607,660711,'波密县',NULL),(673608,660711,'察隅县',NULL),(673609,660711,'朗县',NULL),(673610,660712,'新城区',NULL),(673611,660712,'碑林区',NULL),(673612,660712,'莲湖区',NULL),(673613,660712,'灞桥区',NULL),(673614,660712,'未央区',NULL),(673615,660712,'雁塔区',NULL),(673616,660712,'阎良区',NULL),(673617,660712,'临潼区',NULL),(673618,660712,'长安区',NULL),(673619,660712,'蓝田县',NULL),(673620,660712,'周至县',NULL),(673621,660712,'户县',NULL),(673622,660712,'高陵县',NULL),(673623,660713,'王益区',NULL),(673624,660713,'印台区',NULL),(673625,660713,'耀州区',NULL),(673626,660713,'宜君县',NULL),(673627,660714,'渭滨区',NULL),(673628,660714,'金台区',NULL),(673629,660714,'陈仓区',NULL),(673630,660714,'凤翔县',NULL),(673631,660714,'岐山县',NULL),(673632,660714,'扶风县',NULL),(673633,660714,'眉县',NULL),(673634,660714,'陇县',NULL),(673635,660714,'千阳县',NULL),(673636,660714,'麟游县',NULL),(673637,660714,'凤县',NULL),(673638,660714,'太白县',NULL),(673639,660715,'秦都区',NULL),(673640,660715,'杨陵区',NULL),(673641,660715,'渭城区',NULL),(673642,660715,'三原县',NULL),(673643,660715,'泾阳县',NULL),(673644,660715,'乾县',NULL),(673645,660715,'礼泉县',NULL),(673646,660715,'永寿县',NULL),(673647,660715,'彬县',NULL),(673648,660715,'长武县',NULL),(673649,660715,'旬邑县',NULL),(673650,660715,'淳化县',NULL),(673651,660715,'武功县',NULL),(673652,660715,'兴平市',NULL),(673653,660716,'临渭区',NULL),(673654,660716,'华县',NULL),(673655,660716,'潼关县',NULL),(673656,660716,'大荔县',NULL),(673657,660716,'合阳县',NULL),(673658,660716,'澄城县',NULL),(673659,660716,'蒲城县',NULL),(673660,660716,'白水县',NULL),(673661,660716,'富平县',NULL),(673662,660716,'韩城市',NULL),(673663,660716,'华阴市',NULL),(673664,660717,'宝塔区',NULL),(673665,660717,'延长县',NULL),(673666,660717,'延川县',NULL),(673667,660717,'子长县',NULL),(673668,660717,'安塞县',NULL),(673669,660717,'志丹县',NULL),(673670,660717,'吴起县',NULL),(673671,660717,'甘泉县',NULL),(673672,660717,'富县',NULL),(673673,660717,'洛川县',NULL),(673674,660717,'宜川县',NULL),(673675,660717,'黄龙县',NULL),(673676,660717,'黄陵县',NULL),(673677,660718,'汉台区',NULL),(673678,660718,'南郑县',NULL),(673679,660718,'城固县',NULL),(673680,660718,'洋县',NULL),(673681,660718,'西乡县',NULL),(673682,660718,'勉县',NULL),(673683,660718,'宁强县',NULL),(673684,660718,'略阳县',NULL),(673685,660718,'镇巴县',NULL),(673686,660718,'留坝县',NULL),(673687,660718,'佛坪县',NULL),(673688,660719,'榆阳区',NULL),(673689,660719,'神木县',NULL),(673690,660719,'府谷县',NULL),(673691,660719,'横山县',NULL),(673692,660719,'靖边县',NULL),(673693,660719,'定边县',NULL),(673694,660719,'绥德县',NULL),(673695,660719,'米脂县',NULL),(673696,660719,'佳县',NULL),(673697,660719,'吴堡县',NULL),(673698,660719,'清涧县',NULL),(673699,660719,'子洲县',NULL),(673700,660720,'汉滨区',NULL),(673701,660720,'汉阴县',NULL),(673702,660720,'石泉县',NULL),(673703,660720,'宁陕县',NULL),(673704,660720,'紫阳县',NULL),(673705,660720,'岚皋县',NULL),(673706,660720,'平利县',NULL),(673707,660720,'镇坪县',NULL),(673708,660720,'旬阳县',NULL),(673709,660720,'白河县',NULL),(673710,660721,'商州区',NULL),(673711,660721,'洛南县',NULL),(673712,660721,'丹凤县',NULL),(673713,660721,'商南县',NULL),(673714,660721,'山阳县',NULL),(673715,660721,'镇安县',NULL),(673716,660721,'柞水县',NULL),(673717,660722,'城关区',NULL),(673718,660722,'七里河区',NULL),(673719,660722,'西固区',NULL),(673720,660722,'安宁区',NULL),(673721,660722,'红古区',NULL),(673722,660722,'永登县',NULL),(673723,660722,'皋兰县',NULL),(673724,660722,'榆中县',NULL),(673725,660724,'金川区',NULL),(673726,660724,'永昌县',NULL),(673727,660725,'白银区',NULL),(673728,660725,'平川区',NULL),(673729,660725,'靖远县',NULL),(673730,660725,'会宁县',NULL),(673731,660725,'景泰县',NULL),(673732,660726,'秦州区',NULL),(673733,660726,'麦积区',NULL),(673734,660726,'清水县',NULL),(673735,660726,'秦安县',NULL),(673736,660726,'甘谷县',NULL),(673737,660726,'武山县',NULL),(673738,660726,'张家川回族自治县',NULL),(673739,660727,'凉州区',NULL),(673740,660727,'民勤县',NULL),(673741,660727,'古浪县',NULL),(673742,660727,'天祝藏族自治县',NULL),(673743,660728,'甘州区',NULL),(673744,660728,'肃南裕固族自治县',NULL),(673745,660728,'民乐县',NULL),(673746,660728,'临泽县',NULL),(673747,660728,'高台县',NULL),(673748,660728,'山丹县',NULL),(673749,660729,'崆峒区',NULL),(673750,660729,'泾川县',NULL),(673751,660729,'灵台县',NULL),(673752,660729,'崇信县',NULL),(673753,660729,'华亭县',NULL),(673754,660729,'庄浪县',NULL),(673755,660729,'静宁县',NULL),(673756,660730,'肃州区',NULL),(673757,660730,'金塔县',NULL),(673758,660730,'瓜州县',NULL),(673759,660730,'肃北蒙古族自治县',NULL),(673760,660730,'阿克塞哈萨克族自治县',NULL),(673761,660730,'玉门市',NULL),(673762,660730,'敦煌市',NULL),(673763,660731,'西峰区',NULL),(673764,660731,'庆城县',NULL),(673765,660731,'环县',NULL),(673766,660731,'华池县',NULL),(673767,660731,'合水县',NULL),(673768,660731,'正宁县',NULL),(673769,660731,'宁县',NULL),(673770,660731,'镇原县',NULL),(673771,660732,'安定区',NULL),(673772,660732,'通渭县',NULL),(673773,660732,'陇西县',NULL),(673774,660732,'渭源县',NULL),(673775,660732,'临洮县',NULL),(673776,660732,'漳县',NULL),(673777,660732,'岷县',NULL),(673778,660733,'武都区',NULL),(673779,660733,'成县',NULL),(673780,660733,'文县',NULL),(673781,660733,'宕昌县',NULL),(673782,660733,'康县',NULL),(673783,660733,'西和县',NULL),(673784,660733,'礼县',NULL),(673785,660733,'徽县',NULL),(673786,660733,'两当县',NULL),(673787,660734,'临夏市',NULL),(673788,660734,'临夏县',NULL),(673789,660734,'康乐县',NULL),(673790,660734,'永靖县',NULL),(673791,660734,'广河县',NULL),(673792,660734,'和政县',NULL),(673793,660734,'东乡族自治县',NULL),(673794,660734,'积石山保安族东乡族撒拉族自治县',NULL),(673795,660735,'合作市',NULL),(673796,660735,'临潭县',NULL),(673797,660735,'卓尼县',NULL),(673798,660735,'舟曲县',NULL),(673799,660735,'迭部县',NULL),(673800,660735,'玛曲县',NULL),(673801,660735,'碌曲县',NULL),(673802,660735,'夏河县',NULL),(673803,660736,'城东区',NULL),(673804,660736,'城中区',NULL),(673805,660736,'城西区',NULL),(673806,660736,'城北区',NULL),(673807,660736,'大通回族土族自治县',NULL),(673808,660736,'湟中县',NULL),(673809,660736,'湟源县',NULL),(673810,660737,'平安县',NULL),(673811,660737,'民和回族土族自治县',NULL),(673812,660737,'乐都县',NULL),(673813,660737,'互助土族自治县',NULL),(673814,660737,'化隆回族自治县',NULL),(673815,660737,'循化撒拉族自治县',NULL),(673816,660738,'门源回族自治县',NULL),(673817,660738,'祁连县',NULL),(673818,660738,'海晏县',NULL),(673819,660738,'刚察县',NULL),(673820,660739,'同仁县',NULL),(673821,660739,'尖扎县',NULL),(673822,660739,'泽库县',NULL),(673823,660739,'河南蒙古族自治县',NULL),(673824,660740,'共和县',NULL),(673825,660740,'同德县',NULL),(673826,660740,'贵德县',NULL),(673827,660740,'兴海县',NULL),(673828,660740,'贵南县',NULL),(673829,660741,'玛沁县',NULL),(673830,660741,'班玛县',NULL),(673831,660741,'甘德县',NULL),(673832,660741,'达日县',NULL),(673833,660741,'久治县',NULL),(673834,660741,'玛多县',NULL),(673835,660742,'玉树县',NULL),(673836,660742,'杂多县',NULL),(673837,660742,'称多县',NULL),(673838,660742,'治多县',NULL),(673839,660742,'囊谦县',NULL),(673840,660742,'曲麻莱县',NULL),(673841,660743,'格尔木市',NULL),(673842,660743,'德令哈市',NULL),(673843,660743,'乌兰县',NULL),(673844,660743,'都兰县',NULL),(673845,660743,'天峻县',NULL),(673846,660744,'兴庆区',NULL),(673847,660744,'西夏区',NULL),(673848,660744,'金凤区',NULL),(673849,660744,'永宁县',NULL),(673850,660744,'贺兰县',NULL),(673851,660744,'灵武市',NULL),(673852,660745,'大武口区',NULL),(673853,660745,'惠农区',NULL),(673854,660745,'平罗县',NULL),(673855,660746,'利通区',NULL),(673856,660746,'红寺堡区',NULL),(673857,660746,'盐池县',NULL),(673858,660746,'同心县',NULL),(673859,660746,'青铜峡市',NULL),(673860,660747,'原州区',NULL),(673861,660747,'西吉县',NULL),(673862,660747,'隆德县',NULL),(673863,660747,'泾源县',NULL),(673864,660747,'彭阳县',NULL),(673865,660748,'沙坡头区',NULL),(673866,660748,'中宁县',NULL),(673867,660748,'海原县',NULL),(673868,660749,'天山区',NULL),(673869,660749,'沙依巴克区',NULL),(673870,660749,'新市区',NULL),(673871,660749,'水磨沟区',NULL),(673872,660749,'头屯河区',NULL),(673873,660749,'达坂城区',NULL),(673874,660749,'米东区',NULL),(673875,660749,'乌鲁木齐县',NULL),(673876,660750,'独山子区',NULL),(673877,660750,'克拉玛依区',NULL),(673878,660750,'白碱滩区',NULL),(673879,660750,'乌尔禾区',NULL),(673880,660751,'吐鲁番市',NULL),(673881,660751,'鄯善县',NULL),(673882,660751,'托克逊县',NULL),(673883,660752,'哈密市',NULL),(673884,660752,'巴里坤哈萨克自治县',NULL),(673885,660752,'伊吾县',NULL),(673886,660753,'昌吉市',NULL),(673887,660753,'阜康市',NULL),(673888,660753,'呼图壁县',NULL),(673889,660753,'玛纳斯县',NULL),(673890,660753,'奇台县',NULL),(673891,660753,'吉木萨尔县',NULL),(673892,660753,'木垒哈萨克自治县',NULL),(673893,660754,'博乐市',NULL),(673894,660754,'精河县',NULL),(673895,660754,'温泉县',NULL),(673896,660755,'库尔勒市',NULL),(673897,660755,'轮台县',NULL),(673898,660755,'尉犁县',NULL),(673899,660755,'若羌县',NULL),(673900,660755,'且末县',NULL),(673901,660755,'焉耆回族自治县',NULL),(673902,660755,'和静县',NULL),(673903,660755,'和硕县',NULL),(673904,660755,'博湖县',NULL),(673905,660756,'阿克苏市',NULL),(673906,660756,'温宿县',NULL),(673907,660756,'库车县',NULL),(673908,660756,'沙雅县',NULL),(673909,660756,'新和县',NULL),(673910,660756,'拜城县',NULL),(673911,660756,'乌什县',NULL),(673912,660756,'阿瓦提县',NULL),(673913,660756,'柯坪县',NULL),(673914,660757,'阿图什市',NULL),(673915,660757,'阿克陶县',NULL),(673916,660757,'阿合奇县',NULL),(673917,660757,'乌恰县',NULL),(673918,660758,'喀什市',NULL),(673919,660758,'疏附县',NULL),(673920,660758,'疏勒县',NULL),(673921,660758,'英吉沙县',NULL),(673922,660758,'泽普县',NULL),(673923,660758,'莎车县',NULL),(673924,660758,'叶城县',NULL),(673925,660758,'麦盖提县',NULL),(673926,660758,'岳普湖县',NULL),(673927,660758,'伽师县',NULL),(673928,660758,'巴楚县',NULL),(673929,660758,'塔什库尔干塔吉克自治县',NULL),(673930,660759,'和田市',NULL),(673931,660759,'和田县',NULL),(673932,660759,'墨玉县',NULL),(673933,660759,'皮山县',NULL),(673934,660759,'洛浦县',NULL),(673935,660759,'策勒县',NULL),(673936,660759,'于田县',NULL),(673937,660759,'民丰县',NULL),(673938,660760,'伊宁市',NULL),(673939,660760,'奎屯市',NULL),(673940,660760,'伊宁县',NULL),(673941,660760,'察布查尔锡伯自治县',NULL),(673942,660760,'霍城县',NULL),(673943,660760,'巩留县',NULL),(673944,660760,'新源县',NULL),(673945,660760,'昭苏县',NULL),(673946,660760,'特克斯县',NULL),(673947,660760,'尼勒克县',NULL),(673948,660761,'塔城市',NULL),(673949,660761,'乌苏市',NULL),(673950,660761,'额敏县',NULL),(673951,660761,'沙湾县',NULL),(673952,660761,'托里县',NULL),(673953,660761,'裕民县',NULL),(673954,660761,'和布克赛尔蒙古自治县',NULL),(673955,660762,'阿勒泰市',NULL),(673956,660762,'布尔津县',NULL),(673957,660762,'富蕴县',NULL),(673958,660762,'福海县',NULL),(673959,660762,'哈巴河县',NULL),(673960,660762,'青河县',NULL),(673961,660762,'吉木乃县',NULL),(673962,660763,'石河子市',NULL),(673963,660763,'阿拉尔市',NULL),(673964,660763,'图木舒克市',NULL),(673965,660763,'五家渠市',NULL),(673966,660764,'香港特别行政区',NULL),(673967,660765,'澳门特别行政区',NULL),(673968,660766,'中正区',NULL),(673969,660766,'大同区',NULL),(673970,660766,'中山区',NULL),(673971,660766,'松山区',NULL),(673972,660766,'大安区',NULL),(673973,660766,'万华区',NULL),(673974,660766,'信义区',NULL),(673975,660766,'士林区',NULL),(673976,660766,'北投区',NULL),(673977,660766,'内湖区',NULL),(673978,660766,'南港区',NULL),(673979,660766,'文山区',NULL),(673980,660767,'新兴区',NULL),(673981,660767,'前金区',NULL),(673982,660767,'芩雅区',NULL),(673983,660767,'盐埕区',NULL),(673984,660767,'鼓山区',NULL),(673985,660767,'旗津区',NULL),(673986,660767,'前镇区',NULL),(673987,660767,'三民区',NULL),(673988,660767,'左营区',NULL),(673989,660767,'楠梓区',NULL),(673990,660767,'小港区',NULL),(673991,660768,'仁爱区',NULL),(673992,660768,'信义区',NULL),(673993,660768,'中正区',NULL),(673994,660768,'中山区',NULL),(673995,660768,'安乐区',NULL),(673996,660768,'暖暖区',NULL),(673997,660768,'七堵区',NULL),(673998,660769,'中区',NULL),(673999,660769,'东区',NULL),(674000,660769,'南区',NULL),(674001,660769,'西区',NULL),(674002,660769,'北区',NULL),(674003,660769,'北屯区',NULL),(674004,660769,'西屯区',NULL),(674005,660769,'南屯区',NULL),(674006,660770,'中西区',NULL),(674007,660770,'东区',NULL),(674008,660770,'南区',NULL),(674009,660770,'北区',NULL),(674010,660770,'安平区',NULL),(674011,660770,'安南区',NULL),(674012,660771,'东区',NULL),(674013,660771,'北区',NULL),(674014,660771,'香山区',NULL),(674015,660772,'东区',NULL),(674016,660772,'西区',NULL),(674017,660773,'台北县(板桥市)',NULL),(674018,660773,'宜兰县(宜兰市)',NULL),(674019,660773,'新竹县(竹北市)',NULL),(674020,660773,'桃园县(桃园市)',NULL),(674021,660773,'苗栗县(苗栗市)',NULL),(674022,660773,'台中县(丰原市)',NULL),(674023,660773,'彰化县(彰化市)',NULL),(674024,660773,'南投县(南投市)',NULL),(674025,660773,'嘉义县(太保市)',NULL),(674026,660773,'云林县(斗六市)',NULL),(674027,660773,'台南县(新营市)',NULL),(674028,660773,'高雄县(凤山市)',NULL),(674029,660773,'屏东县(屏东市)',NULL),(674030,660773,'台东县(台东市)',NULL),(674031,660773,'花莲县(花莲市)',NULL),(674032,660773,'澎湖县(马公市)',NULL);

/*Table structure for table `t_file` */

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_comment` varchar(255) DEFAULT NULL,
  `file_count` int(11) DEFAULT NULL,
  `file_label` varchar(255) DEFAULT NULL,
  `file_xml` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_file` */

/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actiondescribe` varchar(255) DEFAULT NULL,
  `actiontime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_log` */

insert  into `t_log`(`id`,`actiondescribe`,`actiontime`) values (1,'admin(魏歆)更新了id为\'75\'的模块','2017-02-17 14:59:43'),(2,'admin(魏歆)更新了id为\'74\'的模块','2017-02-17 14:59:58');

/*Table structure for table `t_province` */

DROP TABLE IF EXISTS `t_province`;

CREATE TABLE `t_province` (
  `proid` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(50) DEFAULT NULL,
  `pro_remark` varchar(50) DEFAULT NULL,
  `pro_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`proid`)
) ENGINE=InnoDB AUTO_INCREMENT=650037 DEFAULT CHARSET=utf8;

/*Data for the table `t_province` */

insert  into `t_province`(`proid`,`pro_name`,`pro_remark`,`pro_sort`) values (650003,'﻿北京市','直辖市',NULL),(650004,'天津市','直辖市',NULL),(650005,'河北省','省份',NULL),(650006,'山西省','省份',NULL),(650007,'内蒙古自治区','自治区',NULL),(650008,'辽宁省','省份',NULL),(650009,'吉林省','省份',NULL),(650010,'黑龙江省','省份',NULL),(650011,'上海市','直辖市',NULL),(650012,'江苏省','省份',NULL),(650013,'浙江省','省份',NULL),(650014,'安徽省','省份',NULL),(650015,'福建省','省份',NULL),(650016,'江西省','省份',NULL),(650017,'山东省','省份',NULL),(650018,'河南省','省份',NULL),(650019,'湖北省','省份',NULL),(650020,'湖南省','省份',NULL),(650021,'广东省','省份',NULL),(650022,'广西壮族自治区','自治区',NULL),(650023,'海南省','省份',NULL),(650024,'重庆市','直辖市',NULL),(650025,'四川省','省份',NULL),(650026,'贵州省','省份',NULL),(650027,'云南省','省份',NULL),(650028,'西藏自治区','自治区',NULL),(650029,'陕西省','省份',NULL),(650030,'甘肃省','省份',NULL),(650031,'青海省','省份',NULL),(650032,'宁夏回族自治区','自治区',NULL),(650033,'新疆维吾尔自治区','自治区',NULL),(650034,'香港特别行政区','特别行政区',NULL),(650035,'澳门特别行政区','特别行政区',NULL),(650036,'台湾省','省份',NULL);

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

/*Table structure for table `w_column` */

DROP TABLE IF EXISTS `w_column`;

CREATE TABLE `w_column` (
  `column_id` int(11) NOT NULL AUTO_INCREMENT,
  `column_div` varchar(255) DEFAULT NULL,
  `model_url` varchar(255) DEFAULT NULL,
  `module_ids` varchar(255) DEFAULT NULL,
  `module_names` varchar(255) DEFAULT NULL,
  `page_url` varchar(255) DEFAULT NULL,
  `plugin_id` int(11) DEFAULT NULL,
  `model_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`column_id`),
  KEY `FK_esbfqocq7ygosly811vig5h84` (`model_id`),
  CONSTRAINT `FK_esbfqocq7ygosly811vig5h84` FOREIGN KEY (`model_id`) REFERENCES `w_module` (`model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `w_column` */

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

/*Table structure for table `w_massmsg` */

DROP TABLE IF EXISTS `w_massmsg`;

CREATE TABLE `w_massmsg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_ids` varchar(255) DEFAULT NULL,
  `errcode` int(11) DEFAULT NULL,
  `error_count` varchar(255) DEFAULT NULL,
  `failure_link` varchar(255) DEFAULT NULL,
  `filter` varchar(255) DEFAULT NULL,
  `filter_count` varchar(255) DEFAULT NULL,
  `media_id` varchar(255) DEFAULT NULL,
  `msg_data_id` varchar(255) DEFAULT NULL,
  `msg_id` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `send_json` varchar(255) DEFAULT NULL,
  `send_time` varchar(255) DEFAULT NULL,
  `send_type` varchar(255) DEFAULT NULL,
  `sent_count` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_count` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `w_massmsg` */

/*Table structure for table `w_menu` */

DROP TABLE IF EXISTS `w_menu`;

CREATE TABLE `w_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(255) DEFAULT NULL,
  `menu_intro` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_tree` text,
  `menu_type` int(11) DEFAULT NULL,
  `website_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `w_menu` */

/*Table structure for table `w_module` */

DROP TABLE IF EXISTS `w_module`;

CREATE TABLE `w_module` (
  `model_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_parent` varchar(255) DEFAULT NULL,
  `layout_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `model_key` int(11) DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `model_nid` int(11) DEFAULT NULL,
  `model_page_url` varchar(255) DEFAULT NULL,
  `model_pid` int(11) DEFAULT NULL,
  `model_role` int(11) DEFAULT NULL,
  `model_url` varchar(255) DEFAULT NULL,
  `plugin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `w_module` */

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

/*Table structure for table `w_website` */

DROP TABLE IF EXISTS `w_website`;

CREATE TABLE `w_website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `org_pid` varchar(255) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `theme_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `w_domain` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `w_website` */

/*Table structure for table `wb_article` */

DROP TABLE IF EXISTS `wb_article`;

CREATE TABLE `wb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_url` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `errcode` int(11) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `object_id` varchar(255) DEFAULT NULL,
  `pub_id` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wb_article` */

/*Table structure for table `wb_media` */

DROP TABLE IF EXISTS `wb_media`;

CREATE TABLE `wb_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bmiddle_pic` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `errcode` int(11) DEFAULT NULL,
  `local_url` varchar(255) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `original_pic` varchar(255) DEFAULT NULL,
  `pub_id` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `thumbnail_pic` varchar(255) DEFAULT NULL,
  `wb_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wb_media` */

/*Table structure for table `wb_token` */

DROP TABLE IF EXISTS `wb_token`;

CREATE TABLE `wb_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `expire_in` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wb_token` */

/*Table structure for table `wb_weibo` */

DROP TABLE IF EXISTS `wb_weibo`;

CREATE TABLE `wb_weibo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_sercret` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `org_pid` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `redirect_url` varchar(255) DEFAULT NULL,
  `token_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_60051muedrcyewgjhybfsc9wo` (`token_id`),
  CONSTRAINT `FK_60051muedrcyewgjhybfsc9wo` FOREIGN KEY (`token_id`) REFERENCES `wb_token` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wb_weibo` */

/*Table structure for table `wx_article` */

DROP TABLE IF EXISTS `wx_article`;

CREATE TABLE `wx_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `content_source_url` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `digest` varchar(255) DEFAULT NULL,
  `errcode` int(11) DEFAULT NULL,
  `media_id` varchar(255) DEFAULT NULL,
  `pub_id` varchar(255) DEFAULT NULL,
  `thumb_media_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_article` */

/*Table structure for table `wx_media` */

DROP TABLE IF EXISTS `wx_media`;

CREATE TABLE `wx_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(255) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `errcode` int(11) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `local_url` varchar(255) DEFAULT NULL,
  `media_id` varchar(255) DEFAULT NULL,
  `pub_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_media` */

/*Table structure for table `wx_token` */

DROP TABLE IF EXISTS `wx_token`;

CREATE TABLE `wx_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `app_id` varchar(255) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `expires_in` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_token` */

/*Table structure for table `wx_wechat` */

DROP TABLE IF EXISTS `wx_wechat`;

CREATE TABLE `wx_wechat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(255) DEFAULT NULL,
  `app_secret` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `org_pid` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `website_url` varchar(255) DEFAULT NULL,
  `wechat_id` varchar(255) DEFAULT NULL,
  `token_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mhw1jrb4he463lsbd2l1r0dyq` (`token_id`),
  CONSTRAINT `FK_mhw1jrb4he463lsbd2l1r0dyq` FOREIGN KEY (`token_id`) REFERENCES `wx_token` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wx_wechat` */

/* Function  structure for function  `findModelChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findModelChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findModelChildren`(nodeId CHAR(100)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
SET sTemp = '$';
SET sTempChd = nodeId;
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_model` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findMoreModelChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findMoreModelChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findMoreModelChildren`(nodeId CHAR(100),num INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
DECLARE pnum INT;
SET sTemp = '$';
SET pnum = 1;
WHILE pnum <= num DO
SET sTempChd = func_splitString(nodeId,',',pnum);
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_model` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
SET pnum = pnum+1;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findMoreOrgChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findMoreOrgChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findMoreOrgChildren`(nodeId CHAR(100),num INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
DECLARE pnum INT;
SET sTemp = '$';
SET pnum = 1;
WHILE pnum <= num DO
SET sTempChd = func_splitString(nodeId,',',pnum);
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_org` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
SET pnum = pnum+1;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findMoreRoleChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findMoreRoleChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findMoreRoleChildren`(nodeId CHAR(100),num INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
DECLARE pnum INT;
SET sTemp = '$';
SET pnum = 1;
WHILE pnum <= num DO
SET sTempChd = func_splitString(nodeId,',',pnum);
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_role` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
SET pnum = pnum+1;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findMoreUserChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findMoreUserChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findMoreUserChildren`(nodeId CHAR(100),num INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
DECLARE pnum INT;
SET sTemp = '$';
SET pnum = 1;
WHILE pnum <= num DO
SET sTempChd = func_splitString(nodeId,',',pnum);
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_sysuser` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
SET pnum = pnum+1;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findOrgChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findOrgChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findOrgChildren`(nodeId CHAR(100)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
SET sTemp = '$';
SET sTempChd = nodeId;
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_org` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findRoleChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findRoleChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findRoleChildren`(nodeId CHAR(100)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
SET sTemp = '$';
SET sTempChd = nodeId;
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_role` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `findUserChildren` */

/*!50003 DROP FUNCTION IF EXISTS `findUserChildren` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `findUserChildren`(nodeId CHAR(100)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
SET sTemp = '$';
SET sTempChd = nodeId;
WHILE sTempChd IS NOT NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(node_id) INTO sTempChd FROM `oa_sysuser` WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `func_splitString` */

/*!50003 DROP FUNCTION IF EXISTS `func_splitString` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`oawfederation`@`localhost` FUNCTION `func_splitString`( f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN
declare result varchar(255) default '';
set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));
return result;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
