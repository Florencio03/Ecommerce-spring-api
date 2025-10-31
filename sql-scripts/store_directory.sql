CREATE DATABASE  IF NOT EXISTS `store_directory`;
USE `store_directory`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL, 
  `email` varchar(255) NOT NULL, 
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data example for table `session`
--

INSERT INTO `session` VALUES 
	('91f9d1f0-3c70-4ed3-88d7-4cc9fc108e29', now(), 'Mejor que ayer, aunque con dudas.','anxiety', 'spotted');
	
--
-- Table structure for table 'addresses
-- 

DROP TABLE IF EXISTS 'addresses';

CREATE TABLE 'adresses'(
	`id` bigint NOT NULL AUTO_INCREMENT,
	`street` varchar(255) NOT NULL, 
	`city` varchar(255) NOT NULL, 
	`zip` varchar(255) NOT NULL,
	`user_id` bigint NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_user_address`
		FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;