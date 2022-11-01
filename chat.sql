-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: chat
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
  `id` bigint NOT NULL,
  `friend_note` varchar(255) DEFAULT NULL,
  `group_id` int NOT NULL,
  `to_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,'son',1,6),(2,'dad',6,5),(3,'傻逼',3,7),(4,NULL,7,5);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `id` bigint NOT NULL,
  `group_name` varchar(25) NOT NULL,
  `max_num` int NOT NULL DEFAULT '200',
  `owner_id` int NOT NULL,
  `now_num` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foreign_owner_id` (`owner_id`),
  CONSTRAINT `foreign_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'普通好友',100,5,1),(2,'特殊好友',100,5,0),(3,'家人',20,5,1),(4,'普通好友',100,6,0),(5,'老师',20,6,0),(6,'家人',20,6,1),(7,'普通好友',100,7,0),(8,'家人',50,7,0);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `rolename` varchar(25) DEFAULT NULL,
  `rolememo` varchar(255) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('ROLE_USER','',1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(15) DEFAULT NULL,
  `college` varchar(15) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `head` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isEnabled` tinyint(1) DEFAULT '1',
  `isAccountNonLocked` tinyint(1) DEFAULT '1',
  `isCredentialsNonExpired` tinyint(1) DEFAULT '1',
  `isAccountNonExpired` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'cehan','$2a$10$jSiybT8S9A2SRsO9HQC/1er9ZZrS7yphUcbXY3FW7wtwjfh8nqssm',NULL,NULL,0,NULL,'cehan@gmail.com',1,1,1,1),(2,'jiahao','$2a$10$3ybkxqEC897GbRF8M0sGCeC6vzN7T4RJix0BXdGukz6h9E.ZkGkDy','何家豪','XUPT',0,NULL,'jihao@qq.com',1,1,1,1),(3,'huosang','$2a$10$3ybkxqEC897GbRF8M0sGCeC6vzN7T4RJix0BXdGukz6h9E.ZkGkDy',NULL,NULL,0,NULL,'huo@qq.com',1,1,1,1),(4,'admin','$2a$10$0nhwoYTRFs1z/GGFVHATmuFI4B0VD0s3YioD6ga.DtIPyWNQhNUsu','','',0,NULL,'admin@qq.com',1,1,1,1),(5,'1','$2a$10$3ybkxqEC897GbRF8M0sGCeC6vzN7T4RJix0BXdGukz6h9E.ZkGkDy',NULL,NULL,1,NULL,'san@qq.com',1,1,1,1),(6,'2','$2a$10$rf7prDR4SmqC/OA7NY4K4Oc44JxlZOqWzWZL0pYyvP6pgKttqsg3O',NULL,NULL,1,NULL,'si@qq.com',1,1,1,1),(7,'3','$2a$10$0nhwoYTRFs1z/GGFVHATmuFI4B0VD0s3YioD6ga.DtIPyWNQhNUsu',NULL,NULL,0,NULL,'wu@qq.com',1,1,1,1),(8,'dedede','$2a$10$lwaVbbMXJZI1RqXUZODCQ.CVzsvIZw4Y9UT.YOJSPmlolRGNSIb2q',NULL,NULL,0,NULL,'1376134353@qq.com',1,1,1,1),(9,'a','$2a$10$cXZZxCiM3MKjALYCR1YzM.mh/0sQZWZ7P86oxQwlNjGzH2P73OsMu','A',NULL,0,NULL,'a',1,1,1,1),(10,'sdsdsd','a',NULL,NULL,0,NULL,'cehan1001@outlook.com',1,1,1,1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (5,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-01 22:23:50
