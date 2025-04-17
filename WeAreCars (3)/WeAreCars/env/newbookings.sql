/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.32-MariaDB : Database - wearecars
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wearecars` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `wearecars`;

/*Table structure for table `newbookings` */

DROP TABLE IF EXISTS `newbookings`;

CREATE TABLE `newbookings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `driving_license` tinyint(1) DEFAULT NULL,
  `car_type` varchar(255) DEFAULT NULL,
  `fuel_type` varchar(255) DEFAULT NULL,
  `extras` varchar(255) DEFAULT NULL,
  `rental_days` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `newbookings` */

insert  into `newbookings`(`id`,`firstname`,`lastname`,`address`,`age`,`driving_license`,`car_type`,`fuel_type`,`extras`,`rental_days`,`total_price`) values 
(2,'ddd','dd','ddd,ddd 03-ee3',22,1,'Family Car (+£50)','Petrol (No extra cost)','Breakdown Cover (+£2 per day)',2,108);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
