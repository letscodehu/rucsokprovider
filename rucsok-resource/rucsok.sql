-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: rucsok
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `rucsok`
--

DROP TABLE IF EXISTS `rucsok`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rucsok` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rucsok`
--

LOCK TABLES `rucsok` WRITE;
/*!40000 ALTER TABLE `rucsok` DISABLE KEYS */;
INSERT INTO `rucsok` VALUES (2,'https://avatars3.githubusercontent.com/u/16595253?v=3&s=400','https://github.com/letscodehu/rucsokprovider/commits/master','letscodehu/rucsokprovider'),(3,'http://i.imgur.com/QelEIx3h.jpg','http://imgur.com/gallery/QelEIx3','Life\'s hard'),(4,'http://i.imgur.com/2cw6CEm.jpg?fb','http://imgur.com/gallery/4M7cP','Hi usersub :)'),(5,'http://i.imgur.com/VSkv4SP.gif?noredirect','http://imgur.com/gallery/VSkv4SP','When your doctor suggests medical marijuana and you\'re trying to play it cool'),(6,'http://i.imgur.com/RdQlkOH.jpg','http://i.imgur.com/RdQlkOH.jpg',NULL),(7,'https://media.giphy.com/media/3oEduMifrXVBDOX0k0/giphy.gif','http://gph.is/1YkSLA0','America\'s Funniest Home Videos GIF - Find & Share on GIPHY'),(8,'https://media.giphy.com/media/xTiTnvyOryUz4apuH6/giphy.gif','http://giphy.com/gifs/foxtv-worlds-funniest-fails-xTiTnvyOryUz4apuH6','Fox TV GIF - Find & Share on GIPHY'),(9,'https://media.giphy.com/media/kMkTJV4a32ba8/giphy.gif','http://giphy.com/gifs/wheres-kMkTJV4a32ba8','Fail GIF - Find & Share on GIPHY'),(10,'http://i.imgur.com/WtYSqqoh.jpg','http://imgur.com/t/programming/WtYSqqo','MRW: Our developer delivers version free of any major bugs'),(11,'http://images-cdn.9gag.com/photo/a1Xd72R_700b.jpg','http://9gag.com/gag/a1Xd72R','Enough internet for today'),(12,'http://i.imgur.com/4UeyzpW.gif?noredirect','http://imgur.com/t/programming/4UeyzpW','When my boss tells me I have to draw 7 red lines, all perpendicular to each other'),(13,'http://images-cdn.9gag.com/photo/aL9MLL6_700b.jpg','http://9gag.com/gag/aL9MLL6','Cyka Blyat.'),(14,'http://images-cdn.9gag.com/photo/a25dMvw_700b.jpg','http://9gag.com/gag/a25dMvw','Work hard and party harder.'),(15,'http://images-cdn.9gag.com/photo/aPWWOOR_700b.jpg','http://9gag.com/gag/aPWWOOR','When you don\'t have the money for a BMX'),(16,'http://images-cdn.9gag.com/photo/agGG9b1_700b.jpg','http://9gag.com/gag/agGG9b1','His parents wanted him to wrestle. His dream was to dance.'),(17,'http://images-cdn.9gag.com/photo/an1NpyB_700b.jpg','http://9gag.com/gag/an1NpyB','I regret nothing');
/*!40000 ALTER TABLE `rucsok` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff`
--

DROP TABLE IF EXISTS `stuff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stuff` (
  `ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff`
--

LOCK TABLES `stuff` WRITE;
/*!40000 ALTER TABLE `stuff` DISABLE KEYS */;
/*!40000 ALTER TABLE `stuff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-08 15:46:14
