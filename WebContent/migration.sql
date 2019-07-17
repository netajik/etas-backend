
create database etas;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;



CREATE TABLE `cab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(50) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1-available 2-unavailable 3-deleted',
  `comments` varchar(256) DEFAULT NULL,
  `vacancy` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `cab_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) DEFAULT NULL,
  `cab_id` int(11) DEFAULT NULL,
  `source_location` varchar(256) DEFAULT NULL,
  `booked_on` datetime DEFAULT NULL,
  `journey_on` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1-generated, 2-processded, 3-failed, 4-closed, 5-cancelled',
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  KEY `cab_id` (`cab_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`cab_id`) REFERENCES `cab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;