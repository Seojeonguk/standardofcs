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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `word` varchar(255) DEFAULT NULL,
  `problem_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp1fh2gvowvmbb58uwrhqslxq1` (`problem_id`),
  CONSTRAINT `FKp1fh2gvowvmbb58uwrhqslxq1` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (10,'서블릿',4),(11,'핸들러매핑',4),(12,'컨트롤러',4),(17,'작업단위',7),(18,'프로세스',7),(19,'실행',7),(20,'프로세스',8),(21,'스레드',8),(22,'자원',8),(23,'공유',8),(24,'프로그램',9),(25,'두',9),(26,'응용',9),(27,'공유',9),(28,'운영체제',10),(29,'서비스',10),(30,'핵심',10),(31,'암호화',11),(32,'공개키',11),(33,'개인키',11),(34,'서버',12),(35,'클라이언트',12),(36,'데이터',12),(37,'stateless',12),(40,'데이터',14),(41,'헤더',14),(42,'보안',14),(43,'안전',14),(44,'신뢰성',15),(45,'비교적',15),(46,'비연결형',15),(47,'실시간성',15),(48,'통신규약',16),(49,'웹',16),(50,'자바스크립트',17),(51,'비동기적',17),(52,'데이터',17),(53,'교환',17),(54,'기억',18),(55,'전역변수',18),(56,'억제',18),(57,'은닉',18),(58,'상단',19),(59,'선언',19),(219,'데이터베이스',102),(220,'교환',102),(263,'데이터',133),(264,'교환',133),(265,'객체',134),(266,'의존성',134),(267,'자바',135),(268,'프레임워크',135),(269,'오픈소스',135),(270,'근거리',136),(271,'동일',136),(272,'네트워크',136),(273,'자바',137),(274,'바이트',137),(275,'운영체제',137),(276,'데이터베이스',138),(277,'접근',138),(278,'객체',138),(279,'자바',139),(280,'관계형',139),(281,'데이터베이스',139),(282,'비영속',140),(283,'영속',140),(284,'준영속',140),(285,'엔티티',141),(286,'저장',141),(287,'트랜젝션',142),(288,'캐시',142),(289,'쓰기지연',142),(290,'그랜트',143),(291,'이너조인',144),(292,'아웃터조인',144),(293,'자연조인',144),(294,'직관성',145),(295,'유효성',145),(296,'학습성',145),(297,'유연성',145),(298,'회복',146),(299,'안전',146),(300,'강도',146),(301,'성능',146),(302,'구조',146),(303,'회귀',146),(304,'병행',146),(305,'노답',147),(306,'인증',148),(307,'인가',148),(308,'필터',148),(309,'어렵다',149),(310,'문제 승인 목록 테스트',150),(311,'답이없음',151);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-18 11:51:02
