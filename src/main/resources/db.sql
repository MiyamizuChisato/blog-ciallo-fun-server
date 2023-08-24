-- MySQL dump 10.13  Distrib 8.1.0, for macos13.3 (x86_64)
--
-- Host: 127.0.0.1    Database: blog_ciallo_fun
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archive` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `category` int NOT NULL,
  `description` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `watch_count` int NOT NULL DEFAULT '0',
  `like_count` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
INSERT INTO `archive` VALUES (1,'124123124','https://www.dmoe.cc/random.php?id=1',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 09:11:26',0),(2,'124123124','https://www.dmoe.cc/random.php?id=2',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 11:28:10',0),(3,'124123124','https://www.dmoe.cc/random.php?id=3',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 09:11:26',0),(4,'124123124','https://www.dmoe.cc/random.php?id=4',4,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(5,'124123124','https://www.dmoe.cc/random.php?id=5',4,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(6,'124123124','https://www.dmoe.cc/random.php?id=6',4,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(7,'124123124','https://www.dmoe.cc/random.php?id=8',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 09:11:26',0),(8,'124123124','https://www.dmoe.cc/random.php?id=9',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 09:11:26',0),(9,'124123124','https://www.dmoe.cc/random.php?id=11',2,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(10,'124123124','https://www.dmoe.cc/random.php?id=112',2,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(11,'124123124','https://www.dmoe.cc/random.php?id=123',2,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:48',0),(12,'124123124','https://www.dmoe.cc/random.php?id=14',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 09:11:26',0),(13,'124123124','https://www.dmoe.cc/random.php?id=15',3,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(14,'124123124','https://www.dmoe.cc/random.php?id=16',3,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 10:50:49',0),(15,'124123124','https://www.dmoe.cc/random.php?id=17',1,'1231245123','https://fun-ciallo-blog-1302869254.cos.ap-shanghai.myqcloud.com/hello.md',0,0,'2023-08-19 17:42:18','2023-08-20 11:28:10',0);
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Java','2023-08-19 15:59:49','2023-08-19 15:59:49',0),(2,'Spring','2023-08-19 15:59:49','2023-08-19 15:59:49',0),(3,'Game','2023-08-19 15:59:49','2023-08-19 15:59:49',0),(4,'Anime','2023-08-19 15:59:49','2023-08-19 15:59:49',0),(5,'Japanese','2023-08-19 15:59:49','2023-08-19 15:59:49',0),(6,'Music','2023-08-19 15:59:49','2023-08-19 15:59:49',0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_detail`
--

DROP TABLE IF EXISTS `file_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_detail` (
  `id` varchar(32) NOT NULL COMMENT '文件id',
  `url` varchar(512) NOT NULL COMMENT '文件访问地址',
  `size` bigint DEFAULT NULL COMMENT '文件大小，单位字节',
  `filename` varchar(256) DEFAULT NULL COMMENT '文件名称',
  `original_filename` varchar(256) DEFAULT NULL COMMENT '原始文件名',
  `base_path` varchar(256) DEFAULT NULL COMMENT '基础存储路径',
  `path` varchar(256) DEFAULT NULL COMMENT '存储路径',
  `ext` varchar(32) DEFAULT NULL COMMENT '文件扩展名',
  `content_type` varchar(128) DEFAULT NULL COMMENT 'MIME类型',
  `platform` varchar(32) DEFAULT NULL COMMENT '存储平台',
  `th_url` varchar(512) DEFAULT NULL COMMENT '缩略图访问路径',
  `th_filename` varchar(256) DEFAULT NULL COMMENT '缩略图名称',
  `th_size` bigint DEFAULT NULL COMMENT '缩略图大小，单位字节',
  `th_content_type` varchar(128) DEFAULT NULL COMMENT '缩略图MIME类型',
  `object_id` varchar(32) DEFAULT NULL COMMENT '文件所属对象id',
  `object_type` varchar(32) DEFAULT NULL COMMENT '文件所属对象类型，例如用户头像，评价图片',
  `attr` text COMMENT '附加属性',
  `file_acl` varchar(32) DEFAULT NULL COMMENT '文件ACL',
  `th_file_acl` varchar(32) DEFAULT NULL COMMENT '缩略图文件ACL',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='文件记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_detail`
--

LOCK TABLES `file_detail` WRITE;
/*!40000 ALTER TABLE `file_detail` DISABLE KEYS */;
INSERT INTO `file_detail` VALUES ('1694574046424236034','https://cos.blog.ciallo.fun/blog/avatar/64e6e2ade88e91d3f75473ed.jpg',1555344,'64e6e2ade88e91d3f75473ed.jpg','04.jpg','blog/','avatar/','jpg','image/jpeg','cos-blog-ciallo-fun',NULL,NULL,NULL,NULL,NULL,'avatar','{}',NULL,NULL,'2023-08-24 12:55:09');
/*!40000 ALTER TABLE `file_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity_permission`
--

DROP TABLE IF EXISTS `identity_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `identity_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `identity_id` int NOT NULL,
  `permission_id` int NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity_permission`
--

LOCK TABLES `identity_permission` WRITE;
/*!40000 ALTER TABLE `identity_permission` DISABLE KEYS */;
INSERT INTO `identity_permission` VALUES (1,1,1,'2023-08-16 21:48:34','2023-08-16 21:48:34',0),(2,1,5,'2023-08-19 10:43:05','2023-08-19 10:43:05',0),(3,1,6,'2023-08-19 10:43:05','2023-08-19 10:43:05',0),(4,1,9,'2023-08-19 10:43:05','2023-08-19 10:43:05',0),(5,1,10,'2023-08-19 10:43:05','2023-08-19 10:43:05',0),(6,1,11,'2023-08-19 10:43:05','2023-08-19 10:43:05',0),(8,1,13,'2023-08-19 15:24:18','2023-08-19 15:24:18',0),(9,1,14,'2023-08-19 15:24:18','2023-08-19 15:24:18',0);
/*!40000 ALTER TABLE `identity_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity_profile`
--

DROP TABLE IF EXISTS `identity_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `identity_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity_profile`
--

LOCK TABLES `identity_profile` WRITE;
/*!40000 ALTER TABLE `identity_profile` DISABLE KEYS */;
INSERT INTO `identity_profile` VALUES (1,'注册会员','2000-01-01 00:00:00','2000-01-01 00:00:00',0),(2,'赞助会员','2000-01-01 00:00:00','2000-01-01 00:00:00',0),(3,'管理会员','2000-01-01 00:00:00','2000-01-01 00:00:00',0),(4,'开发会员','2000-01-01 00:00:00','2000-01-01 00:00:00',0),(5,'神様','2000-01-01 00:00:00','2000-01-01 00:00:00',0);
/*!40000 ALTER TABLE `identity_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_profile`
--

DROP TABLE IF EXISTS `permission_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_profile`
--

LOCK TABLES `permission_profile` WRITE;
/*!40000 ALTER TABLE `permission_profile` DISABLE KEYS */;
INSERT INTO `permission_profile` VALUES (1,'archive:get',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(2,'archive:post',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(3,'archive:put',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(4,'archive:delete',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(5,'commit:get',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(6,'commit:post',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(7,'commit:put',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(8,'commit:delete',NULL,'2000-01-01 00:00:00','2023-08-19 15:23:19',0),(9,'user:profile:get',NULL,'2023-08-19 10:41:16','2023-08-19 10:41:16',0),(10,'user:profile:post',NULL,'2023-08-19 10:41:17','2023-08-19 10:41:17',0),(11,'user:profile:put',NULL,'2023-08-19 10:41:17','2023-08-19 10:41:17',0),(12,'user:profile:delete',NULL,'2023-08-19 10:41:17','2023-08-19 10:41:17',0),(13,'message:get',NULL,'2023-08-19 15:23:47','2023-08-19 15:23:47',0),(14,'message:post',NULL,'2023-08-19 15:23:47','2023-08-19 15:23:47',0),(15,'message:put',NULL,'2023-08-19 15:23:47','2023-08-19 15:23:47',0),(16,'message:delete',NULL,'2023-08-19 15:23:47','2023-08-19 15:23:47',0);
/*!40000 ALTER TABLE `permission_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_auth` (
  `user_profile_id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES (3,'123@qq.com','$2a$10$BMAEq6rZK8nlJNvtqGyPbOMKCWZy8q6wbMUBNr1vZ1z3.NttNtSk2');
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_identity`
--

DROP TABLE IF EXISTS `user_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_identity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `identity_id` int NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_identity`
--

LOCK TABLES `user_identity` WRITE;
/*!40000 ALTER TABLE `user_identity` DISABLE KEYS */;
INSERT INTO `user_identity` VALUES (1,1,1,'2023-08-16 21:50:22','2023-08-16 21:50:22',0),(2,2,1,'2023-08-16 21:57:56','2023-08-16 21:57:56',0),(3,3,1,'2023-08-19 08:52:15','2023-08-19 08:52:15',0);
/*!40000 ALTER TABLE `user_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_oauth`
--

DROP TABLE IF EXISTS `user_oauth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_oauth` (
  `user_profile_id` int NOT NULL,
  `oauth_type` varchar(255) NOT NULL,
  `oauth_id` varchar(255) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_oauth`
--

LOCK TABLES `user_oauth` WRITE;
/*!40000 ALTER TABLE `user_oauth` DISABLE KEYS */;
INSERT INTO `user_oauth` VALUES (1,'gitee','9611217','2c5b42885760d3e6e0b913ba21c3d02f'),(2,'github','60510927','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');
/*!40000 ALTER TABLE `user_oauth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `site` varchar(255) DEFAULT NULL,
  `motto` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) NOT NULL DEFAULT 'https://cos.blog.ciallo.fun/blog/avatar/avatar.jpg',
  `gender` tinyint DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,'ちさと',NULL,'僕は何もできないから!','https://cos.blog.ciallo.fun/blog/avatar/64e6e2ade88e91d3f75473ed.jpg',1,'四川省成都市','2023-08-19',1,'2023-08-16 21:50:22','2023-08-24 12:55:18',0),(2,'MiyamizuChisato',NULL,NULL,'https://avatars.githubusercontent.com/u/60510927?v=4',NULL,NULL,'2023-08-19',1,'2023-08-16 21:57:56','2023-08-19 10:28:40',0),(3,'name',NULL,NULL,'test',NULL,NULL,'2023-08-19',1,'2023-08-19 08:52:15','2023-08-19 10:28:40',0);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-24 12:59:35
