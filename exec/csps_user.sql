-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: k5c101.p.ssafy.io    Database: csps
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ghghkdg52@naver.com',NULL,'이호황','llhh','$2a$10$04b6XfOHXVrNoJAt8StxYeEb/RortMqRgg7xm0gviReety5LDT5py','ROLE_USER'),(3,'sgs1159@naver.com','abd','이조순','조순몬','abcd1234','ROLE_USER'),(10,'sgs1159@nate.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/e3fd4ac2-dde1-4371-b258-d811b66bc14b.png','이조순',NULL,'$2a$10$lhZrJrxaJdW4WwlGM2nMweVDOMLE2PW/JkTpKFFRDy169p1i7PyTa','ROLE_USER'),(20,'sgs1158@naver.com',NULL,'이조순','이조순2','$2a$10$ZQxG7fW9XtI2lIZU0dIWXO9m5ekrHvrIqkrkkGwQtkxKVnMiQVuPy','ROLE_USER'),(47,'test@test.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','김테스트','테스트','$2a$10$DeYj8hi6Q.4b7qU9VwbFNey/BNYOft2she.Se3dRMAJ8OvqMgXdYa','ROLE_USER'),(48,'test@testtest.com',NULL,'tt','tt','$2a$10$t4YzCKQP/k/9e5OSqK8lce1Z3NP/jSzVwcIJEKykJU10.b9ix2yXy','ROLE_USER'),(76,'admin@naver.com',NULL,'관리자','관리자','$2a$10$AMRUOcdx7x7gESkutJNsl.E4xnFeNPFBo6P0DJRgwQAR9bEisLHP2','ROLE_ADMIN'),(77,'sgs1157@naver.com','default','순조이','블랙맘바','$2a$10$WYHzi84.WoaVl6rHEmCQlOFSotY8EhycJlbONg3cEIiVu2PeA7/H.','ROLE_USER'),(78,'sgs1154@naver.com',NULL,'박순대','노란코끼리','$2a$10$yoYncNQDJUp1EA.GTzL4WeOwBbL9oJq/oeQwMDf8rCU0pv7OyFo0K','ROLE_USER'),(79,'sgs1153@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/94e9b2a5-ee65-424b-a0f6-48fa69acd9a7.jpg','김순조','블랙맘바맘','$2a$10$KoGlz.xYp7pa5NBdq54lGO4vLK7.o6dMmqMXytKcEA46hxJHp0/AO','ROLE_USER'),(80,'sgs1150@nate.com',NULL,'박조순','김정태임22','$2a$10$VDKYw6Nk6lt9e3CNwK1wjOGo80x3J8QFUueBKjlLb89t/T3KZDu7e','ROLE_USER'),(81,'sgs1151@nate.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/0cc070fb-4e53-4476-853f-8dbb0b004cbe.JPG','성조순','순조성222','$2a$10$tBt81lUfwRz25Qaak6XuUO3eZeAeXcTo1Re.l4vRovf./uj7EeX5i','ROLE_USER'),(82,'sgs1149@nate.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/5ea952f5-c9d4-4c3d-817e-b092c4f873d8.JPG','김조순','순조김','$2a$10$aFo7c9vW2OvCVFHkheL0vOO.kP645tigwyCFN0rwxQ3bSxBjwAdYa','ROLE_ADMIN'),(83,'xrl@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/35d97c2a-1f9f-4146-b55c-05e391d1077c.png','박지수','응아쟁이','$2a$10$7bdUYzOgzh5mtIGyxuzG5OxeMB1tb.w7aTOOGWyXmxlU6V1TziwIq','ROLE_USER'),(84,'junguk7880@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','서정욱','ㅀㅇㅀ','$2a$10$WXMKCr7iAYQQpdvJdE9at.dFTqG4EZmRH66v40FevhmDT8C8VpBFu','ROLE_USER'),(85,'test2@test.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','테스트','테스트2','$2a$10$TmnjnIv4qb0JeAxb5XffLOKQ.a8WB2aEZ/4DrY1KNDSp6LwBz95a.','ROLE_USER'),(86,'test3@test.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','테스트','테스트2','$2a$10$NBpg9V4rYrMA7tOvSLBapOZJ0O4.p1n2tVM/US4uIDiQDk9hd0ZB2','ROLE_USER'),(87,'sgs1129@nate.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/5e0212f2-cfa1-47e6-97de-b9b91f7af5fb.jpg','이조순','순조아','$2a$10$SDxJvfIN7j9EUvjf2QI.jegr04pFp3f.vmVvRFISSUXl3dteJ9aoK','ROLE_USER'),(89,'rhkdgns0818@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/3690cd06-5e49-489e-8f55-cdc7d8d11624.png','임광훈','임광훈','$2a$10$xmWF/Gd6FrqgEQh4R/Szr.vEaoYwJMWX3WeyhjA8T/GX4Tlph5fqu','ROLE_USER'),(90,'arisu0906@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','박지수','CS에게 진남자','$2a$10$fTtjnVBa3Uiwil5CseJ1/.dSrwliUCrelUapYjJP8XAx0BA1Jx9Aq','ROLE_USER'),(91,'uk7880@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','123','순조김','$2a$10$K3h0fsITqlZ5uZeb43rw..kgs5wDmNOLdz3RF/1DnOEaC0FssX4Hu','ROLE_USER'),(92,'dbwk0404@naver.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','박민주','팍팍','$2a$10$3qNkuu5fXZQjmbwLUdQwtOP5HQPbkyjkbYFByTi.m50uz75UxP3Q.','ROLE_USER'),(93,'minjupar@gmail.com','https://freepjt.s3.ap-northeast-2.amazonaws.com/user/mainCharachter.png','정석조','정석조','$2a$10$VAGMR8vbXsRLimJ.NOhcReFOOn7MksYW3awTRf.iikMm4Vug7S.KW','ROLE_USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-18 11:51:03
