use training;
DROP TABLE IF EXISTS `shot`;
DROP TABLE IF EXISTS `shotrm`;
DROP TABLE IF EXISTS `shotrf`;
DROP TABLE IF EXISTS `shotflat`;


CREATE TABLE `shotrf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quality` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `shotrm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usage` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


CREATE TABLE `shot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `rmid` int(11) DEFAULT NULL,
  `rfid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `f1_idx` (`rfid`),
  KEY `f2_idx` (`rmid`),
  CONSTRAINT `f1` FOREIGN KEY (`rfid`) REFERENCES `shotrf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f2` FOREIGN KEY (`rmid`) REFERENCES `shotrm` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


CREATE TABLE `training`.`shotflat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `classmapper` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `usage` VARCHAR(45) NULL,
  `quality` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

