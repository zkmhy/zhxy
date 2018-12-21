/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.7.18-log : Database - zhxy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zhxy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zhxy`;

/*Table structure for table `classroom` */

DROP TABLE IF EXISTS `classroom`;

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `inclass` tinyint(1) DEFAULT '0',
  `forstudy` tinyint(1) DEFAULT '1',
  `fortest` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `classroom` */

insert  into `classroom`(`id`,`name`,`floor`,`inclass`,`forstudy`,`fortest`) values 
(1,'201',2,1,1,0),
(2,'202',2,1,1,0),
(3,'203',2,1,1,0),
(4,'204',2,1,1,0),
(5,'205',2,1,1,0),
(6,'206',2,1,1,0),
(7,'207',2,1,1,0),
(8,'208',2,1,1,0),
(9,'209',2,1,1,0),
(10,'210',2,0,1,0),
(11,'211',2,1,1,1),
(12,'212',2,1,1,1),
(13,'213',2,1,1,1),
(14,'214',2,0,1,0),
(15,'215',2,0,1,0),
(16,'401',4,1,1,0),
(17,'402',4,1,1,0),
(18,'403',4,1,1,0),
(19,'404',4,1,1,0),
(20,'405',4,1,1,0),
(21,'406',4,1,1,0),
(22,'407',4,1,1,0),
(23,'408',4,0,1,0),
(24,'409',4,0,1,0),
(25,'410',4,0,1,0),
(26,'411',4,0,1,0),
(27,'412',4,0,1,0),
(28,'413',4,0,1,0),
(29,'414',4,0,1,0),
(30,'415',4,0,1,0),
(31,'216',2,0,1,0),
(32,'217',2,0,1,0),
(33,'218',2,0,1,0),
(34,'219',2,0,1,0),
(35,'220',2,0,1,0),
(36,'221',2,0,1,0),
(37,'222',2,0,1,0),
(38,'223',2,0,1,0),
(39,'启蒙星',2,1,1,1),
(40,'启蒙星',2,1,1,1),
(41,'R305',3,1,1,0);

/*Table structure for table `clazz` */

DROP TABLE IF EXISTS `clazz`;

CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT '4',
  `teacherId` int(11) DEFAULT NULL,
  `currId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `clazz` */

insert  into `clazz`(`id`,`name`,`gradeId`,`priority`,`teacherId`,`currId`) values 
(1,'AT1701',2,4,1,5),
(2,'AT1702',2,4,2,5),
(3,'AT1703',2,4,3,4),
(4,'AT1704',2,4,4,4),
(5,'AT1705',2,4,5,4),
(6,'AT1706',2,4,1,3),
(7,'AT1707',2,4,2,3),
(8,'AT1708',1,4,3,3),
(9,'AT1709',1,4,4,3),
(10,'AT1710',1,4,5,2),
(11,'AT1711',1,4,1,2),
(12,'AT1712',1,4,2,2),
(13,'AT1713',1,4,3,1),
(14,'AT1714',1,4,4,1);

/*Table structure for table `curriculum` */

DROP TABLE IF EXISTS `curriculum`;

CREATE TABLE `curriculum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ename` varchar(50) DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `curriculum` */

insert  into `curriculum`(`id`,`name`,`ename`,`gradeId`) values 
(1,'使用Java实现面向对象编程','JAVA OOP',1),
(2,'使用Java实现面向数据库编程','JAVA+MySQL',1),
(3,'使用JQuery制作网页交互','JQuery',2),
(4,'深入.NET平台和C#编程','.NET+C#',2),
(5,'使用JSP/Servlet开发系统','JSP/Servlet',2);

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

insert  into `grade`(`id`,`name`,`floor`) values 
(1,'S1',2),
(2,'S2',2),
(3,'Y2',4);

/*Table structure for table `holiday` */

DROP TABLE IF EXISTS `holiday`;

CREATE TABLE `holiday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `begin` varchar(50) DEFAULT NULL,
  `end` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `holiday` */

insert  into `holiday`(`id`,`name`,`begin`,`end`) values 
(1,'国庆节','10-1','10-7'),
(2,'中秋节',NULL,NULL),
(3,'端午节',NULL,NULL);

/*Table structure for table `people` */

DROP TABLE IF EXISTS `people`;

CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `people` */

insert  into `people`(`id`,`name`,`rid`) values 
(1,'张龙',1),
(2,'李天华',1),
(3,'赵客缦胡缨',1),
(4,'吴悦',1),
(5,'洛克',1);

/*Table structure for table `plan` */

DROP TABLE IF EXISTS `plan`;

CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `roomid` int(11) DEFAULT NULL,
  `ap` tinyint(1) DEFAULT '0',
  `study` tinyint(1) DEFAULT '0',
  `active` tinyint(1) DEFAULT '0',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `plan` */

insert  into `plan`(`id`,`cid`,`roomid`,`ap`,`study`,`active`,`date`) values 
(1,1,1,0,1,0,'2018-12-15'),
(2,1,2,1,0,0,'2018-12-15'),
(3,2,2,0,0,0,'2018-12-15'),
(4,2,1,1,1,0,'2018-12-15'),
(5,3,3,0,1,0,'2018-12-15'),
(6,3,4,1,0,0,'2018-12-15'),
(7,4,4,0,0,0,'2018-12-15'),
(8,4,3,1,1,0,'2018-12-15'),
(9,1,1,0,1,0,'2018-12-10'),
(10,1,2,1,0,0,'2018-12-10'),
(11,2,2,0,0,0,'2018-12-10'),
(12,2,1,1,1,0,'2018-12-10'),
(13,3,3,0,1,0,'2018-12-10'),
(14,3,4,1,0,0,'2018-12-10'),
(15,4,4,0,0,0,'2018-12-10'),
(16,4,3,1,1,0,'2018-12-10'),
(17,1,1,0,1,0,'2018-12-17'),
(18,1,2,1,0,0,'2018-12-17'),
(19,2,2,0,0,0,'2018-12-17'),
(20,2,1,1,1,0,'2018-12-17'),
(21,3,3,0,1,0,'2018-12-17'),
(22,3,4,1,0,0,'2018-12-17'),
(23,4,4,0,0,0,'2018-12-17'),
(24,4,3,1,1,0,'2018-12-17'),
(25,1,5,0,1,0,'2018-12-17'),
(26,1,6,0,1,0,'2018-12-17'),
(27,1,7,0,1,0,'2018-12-17'),
(28,1,8,0,1,0,'2018-12-17'),
(29,1,9,0,1,0,'2018-12-17'),
(30,1,16,0,1,0,'2018-12-17'),
(31,1,17,0,1,0,'2018-12-17'),
(32,1,18,0,1,0,'2018-12-17'),
(33,1,19,0,1,0,'2018-12-17'),
(34,1,20,0,1,0,'2018-12-17'),
(35,1,21,0,1,0,'2018-12-17'),
(36,1,22,0,1,0,'2018-12-17'),
(37,1,39,0,1,0,'2018-12-17'),
(38,1,41,0,1,0,'2018-12-17');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values 
(1,'教员');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
