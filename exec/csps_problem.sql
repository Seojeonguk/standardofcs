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
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5qlt94mi6xg5vaqg4hwso1fj6` (`category_id`),
  KEY `FKj6t2lpcskqxjdjr5j4txsxxdp` (`user_id`),
  CONSTRAINT `FK5qlt94mi6xg5vaqg4hwso1fj6` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKj6t2lpcskqxjdjr5j4txsxxdp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (4,'스프링 MVC구조 흐름에 대해 설명해주세요.','accept',3,3),(7,'스레드(Thread)는 무엇입니까?','accept',1,3),(8,'멀티 스레딩(Multi-threading) 의 장점과 단점은?','accept',1,3),(9,'소켓이란 무엇입니까?','accept',1,3),(10,'커널이란 무엇입니까?','accept',1,3),(11,'HTTP와 HTTPS의 차이점에 대해 설명하시오','accept',2,3),(12,'HTTP프로토콜은 무엇인가요?','accept',2,3),(14,'GET과 POST 차이에 대해 말해주세요.','accept',2,3),(15,'TCP와 UDP 차이에 대해 말해주세요.','accept',2,3),(16,'웹 프로토콜이란?','accept',4,3),(17,'AJAX란 무엇인가','accept',4,3),(18,'클로저(Closure)란 무엇이며, 왜 이러한 패턴을 사용하는가','accept',4,3),(19,'자바스크립트의 호이스팅(Hoisting)은 어떻게 이루어져 있는가','accept',4,3),(102,'DTO를 설명해주세요.','accept',3,3),(133,'DTO가 무엇인가요?','accept',3,82),(134,'DI란 무엇인가','accept',3,82),(135,'스프링이 무엇인가요?','accept',3,82),(136,'LAN이란 무엇인가요?','accept',2,82),(137,'JVM의 역할이 무엇인가요?','accept',3,82),(138,'DAO는 무엇인가요?','accept',3,82),(139,'JPA가 무엇인가요?','accept',5,82),(140,'엔티티의 상태(생명주기)에 대해서 설명해주세요','accept',5,82),(141,'영속성 컨텍스트가 무엇인가?','accept',5,82),(142,'jpa 동작과정을 설명해주세요','accept',5,82),(143,'사용자에게 권한을 주는 방법은?','revoke',12,84),(144,'조인의 종류는?','wait',12,84),(145,'사용자 인터페이스의 기본 원칙 4가지는?','wait',9,84),(146,'목적에 따른 테스트 방법의 종류는?','wait',9,84),(147,'악성유저가 만든 이상한 문제','wait',9,84),(148,'Spring Security란?','wait',10,84),(149,'Vue와 차이점은?','wait',8,84),(150,'문제 승인 목록 테스트','wait',5,84),(151,'22252','wait',5,84);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-18 11:51:01
