CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialNum` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `access` int(11) DEFAULT NULL,
  `menuName` varchar(255) DEFAULT NULL,
  `alias` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialNum` varchar(50) DEFAULT NULL,
  `resName` varchar(50) DEFAULT NULL,
  `resDesc` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialNum` varchar(50) DEFAULT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(255) DEFAULT NULL,
  `access` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uId` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_id_uindex` (`id`),
  UNIQUE KEY `token_uId_uindex` (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `uId` varchar(50) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `roleSerialNum` varchar(50) DEFAULT NULL,
  `userPassword` varchar(50) DEFAULT NULL,
  `realName` varchar(50) DEFAULT NULL,
  `phoneNum` varchar(50) DEFAULT NULL,
  `mailAddr` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`uId`),
  UNIQUE KEY `user_uId_uindex` (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
