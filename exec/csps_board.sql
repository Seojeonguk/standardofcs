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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfyf1fchnby6hndhlfaidier1r` (`user_id`),
  CONSTRAINT `FKfyf1fchnby6hndhlfaidier1r` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (78,'2021-11-12 16:59:06','알려주세요 ㅠ','운영체제 쓰레드는 도대체 뭔가요?',82),(84,'2021-11-16 16:05:17','# HTML\n\n> \"웹 컨텐츠의 의미와 구조를 정의할 때 사용하는 언어\"\n\n## HTML 기초\n\n**Hyper**\n\n- 텍스트 등의 정보가 동일 선상에 있는 것이 아니라 다중으로 연결되어 있는 상태\n\n**Hyper Text**\n\n- 참조(하이퍼링크)를 통해 사용자가 한 문서에서 다른 문서로 즉시 접근 할 수 잇는 텍스트\n- 하이퍼 텍스트가 쓰인 기술등 중 가장 중요한 2가지 (http, html)\n\n**Markup Language**\n\n- 특정 텍스트에 역할을 부여하는, 따라서 \"마크업을 한다\" 라고 하는 건 제목이 제목이라하고 본문이 본문이라고 마킹을 하는 것\n- ex) h1 tag는 단순히 글자가 커지는 것이 아니라 의미론적으로 그 페이지에서 가장 핵심 주제를 의미하는 것\n\n<br>\n\n## HTML 기본 구조\n\n**DOM**\n\n- DOM은 문서의 구조화된 표현(structured representation)을 제공하며 프로그래밍 언어가 DOM 구조에 접근할 수 있는 방법을 제공하여 그들이 문서 구조, 스타일, 내용 등을 변경할 수 있게 도움\n- DOM은 동일한 문서를 표현하고, 저장하고, 조작하는 방법을 제공\n- 웹 페이지의 객체 지향 표현\n\n**요소 (Element)**\n\n- HTML 요소는 시작 태그와 종료 태그 그리고 태그 사이에 위치한 내용으로 구성\n  - 태그(Element, 요소)는 컨텐츠(내용)를 감싸서 그 정보의 성격과 의미를 정의 한다.\n- 내용이 없는 태그들\n  - br, hr, img, input, link, meta\n- 요소는 중첩(nested)될 수 있다.\n  - 이러한 중첩들로 하나의 문서를 완성해 나간다.\n  - 그리고 항상 열고 닫는 태그 쌍이 잘 맞는지 잘 봐야한다.\n  - HTML은 오류를 뿜지 않고 그냥 레이아웃이 깨져버리기 때문에 어떤 면에서는 친절하게 오류 띄워주고 어디 틀렸는지 알려주는 프로그래밍 보다 디버깅이 힘들다.\n\n**속성 (Attribute)**\n\n- 속성(Attribute)은 태그의 부가적인 정보가 들어온다.\n- 요소는 속성을 가질 수 있으며 요소에 추가적 정보(이미지 파일의 경로, 크기 등)를 제공한다. \n- 요소의 시작 태그에 위치해야 하며 **이름**과 **값**의 쌍을 이룬다.\n- 태그와 상관없이 사용 가능한 속성들(html global attribute)도 있다.\n\n<br>\n\n## 시맨틱 태그\n\n> 브라우저, 검색엔진, 개발자 모두에게 콘텐츠의 의미를 명확히 설명하는 태그\n\n**장점**\n\n1. 읽기 쉬워진다. (개발자)\n   - 개발자가 의도한 요소의 의미가 명확히 드러나고 있다.이것은 코드의 가독성을 높이고 유지보수를 쉽게 한다.\n2. 접근성이 좋아진다. (검색엔진 및 보조기술 → 시력장애용 스크린리더 → 더 나은 경험 제공)\n   - HTML 문서는 html 언어 + 사람이 읽을 수 있는 content의 조합인데, 검색 엔진은 HTML 코드만 잘 읽는다.\n   - 그래서 이 검색 엔진이 HTML을 잘 이해하도록 시맨틱 태그 사용이 권장되고, 그러면 검색 엔진도 무슨 내용인지 이해할 수 있게 된다.\n\n**시맨틱 웹**\n\n- 웹에 존재하는 수많은 웹페이지들에 메타데이터를 부여하여, 기존의 단순한 데이터 집합이었던 웹페이지를 \'의미\'와 \'관련성\'을 가지는 거대한 데이터베이스로 구축하고자 하는 발상.\n\n---\n\n## 참고 문헌\n\nhttps://developer.mozilla.org/ko/docs/Learn/HTML/Introduction_to_HTML/Getting_started\n\nhttps://developer.mozilla.org/ko/docs/Web/HTML/Global_attributes\n\nhttps://developer.mozilla.org/ko/docs/Glossary/Semantics','HTML 기초 학습했습니다.',89);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
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
