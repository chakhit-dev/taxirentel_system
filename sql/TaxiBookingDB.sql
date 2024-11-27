-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.4.3-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for taxiandbooking_db
CREATE DATABASE IF NOT EXISTS `taxiandbooking_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `taxiandbooking_db`;

-- Dumping structure for table taxiandbooking_db.booking
CREATE TABLE IF NOT EXISTS `booking` (
  `booking_id` varchar(50) NOT NULL,
  `vehicle_id` varchar(50) DEFAULT NULL,
  `driver_id` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `driver_id` (`driver_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `FK_booking_driver` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_booking_vehicle_model` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle_model` (`vehicle_id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table taxiandbooking_db.booking: ~6 rows (approximately)
DELETE FROM `booking`;
INSERT INTO `booking` (`booking_id`, `vehicle_id`, `driver_id`, `date`) VALUES
	('BK00001', 'UTL27950', 'DRV85804', '2024-11-27 01:20:12'),
	('BK0003', 'SED50141', 'DRV43336', '2024-11-27 01:20:28'),
	('BK14764', 'SED50141', 'DRV85804', '2024-11-27 06:48:22'),
	('BK43990', 'SED50141', 'DRV85804', '2024-11-27 06:44:16'),
	('BK51594', 'SED50141', 'DRV85804', '2024-11-27 06:43:44'),
	('BK51611', 'SED50141', 'DRV43336', '2024-11-27 05:58:56');

-- Dumping structure for table taxiandbooking_db.driver
CREATE TABLE IF NOT EXISTS `driver` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `address` varchar(255) NOT NULL DEFAULT '',
  `phone` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table taxiandbooking_db.driver: ~2 rows (approximately)
DELETE FROM `driver`;
INSERT INTO `driver` (`id`, `name`, `address`, `phone`) VALUES
	('DRV43336', 'Jonathan Dev', 'Bankok', 11111333),
	('DRV85804', 'McDorman Miller', 'Japan', 33111);

-- Dumping structure for table taxiandbooking_db.time_active
CREATE TABLE IF NOT EXISTS `time_active` (
  `id_time` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`id_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table taxiandbooking_db.time_active: ~3 rows (approximately)
DELETE FROM `time_active`;
INSERT INTO `time_active` (`id_time`, `start_time`, `end_time`) VALUES
	(1, '06:00:00', '14:00:00'),
	(2, '14:00:00', '22:00:00'),
	(3, '22:00:00', '06:00:00');

-- Dumping structure for table taxiandbooking_db.vehicle_model
CREATE TABLE IF NOT EXISTS `vehicle_model` (
  `vehicle_id` varchar(50) NOT NULL DEFAULT '000',
  `vehicle_type` varchar(50) NOT NULL,
  `model` varchar(255) NOT NULL DEFAULT '',
  `price` decimal(20,6) NOT NULL,
  PRIMARY KEY (`vehicle_id`),
  KEY `vehicle_type` (`vehicle_type`),
  CONSTRAINT `FK_vehicle_model_vehicle_type` FOREIGN KEY (`vehicle_type`) REFERENCES `vehicle_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table taxiandbooking_db.vehicle_model: ~5 rows (approximately)
DELETE FROM `vehicle_model`;
INSERT INTO `vehicle_model` (`vehicle_id`, `vehicle_type`, `model`, `price`) VALUES
	('SED50141', 'Sedan', 'Tesla Model1', 12000.000000),
	('SED69745', 'Sedan', 'Kawasaki ZX10r', 1800.000000),
	('SED77274', 'Sedan', 'Tesla Model3', 12000.000000),
	('UTL27950', 'Utility', 'Honda Wave 125', 900.000000),
	('UTL84936', 'Utility', 'Toyota AE86', 12000.000000);

-- Dumping structure for table taxiandbooking_db.vehicle_type
CREATE TABLE IF NOT EXISTS `vehicle_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table taxiandbooking_db.vehicle_type: ~2 rows (approximately)
DELETE FROM `vehicle_type`;
INSERT INTO `vehicle_type` (`id`, `type`) VALUES
	(2, 'Sedan'),
	(1, 'Utility');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
