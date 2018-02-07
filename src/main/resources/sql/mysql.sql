-- ----------------------------
-- 用户 user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uId` varchar(50) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `userRole` varchar(50) DEFAULT NULL,
  `userPassword` varchar(50) DEFAULT NULL,
  `realName` varchar(50) DEFAULT NULL,
  `phoneNum` varchar(50) DEFAULT NULL,
  `mailAddr` varchar(50) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`uId`),
  UNIQUE KEY `user_uId_uindex` (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8