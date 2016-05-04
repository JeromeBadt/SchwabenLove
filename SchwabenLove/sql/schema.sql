-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: schwabenlove
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

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

CREATE DATABASE `schwabenlove` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_german1_ci */;
USE schwabenlove;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark` (
  `fk_profile` int(11) NOT NULL,
  `fk_bookmark_list` int(11) DEFAULT NULL,
  KEY `bookmark_profile_profile_id_fk` (`fk_profile`),
  KEY `bookmark_bookmark_list_fk` (`fk_bookmark_list`),
  CONSTRAINT `bookmark_bookmark_list_fk` FOREIGN KEY (`fk_bookmark_list`) REFERENCES `bookmark_list` (`bookmark_id`),
  CONSTRAINT `bookmark_profile_profile_id_fk` FOREIGN KEY (`fk_profile`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `block_id` int(11) NOT NULL,
  `fk_profile_blocker` int(11) NOT NULL,
  `fk_profile_blocked` int(11) NOT NULL,
  PRIMARY KEY (`block_id`),
  KEY `blocker_profile_fk` (`fk_profile_blocker`),
  KEY `blocked_profile_fk` (`fk_profile_blocked`),
  CONSTRAINT `blocked_profile_fk` FOREIGN KEY (`fk_profile_blocked`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `blocker_profile_fk` FOREIGN KEY (`fk_profile_blocker`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmark_list`
--

DROP TABLE IF EXISTS `bookmark_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark_list` (
  `bookmark_id` int(11) NOT NULL,
  `fk_profile_bookmark_list_possession` int(11) NOT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `bookmark_list_profile_possession_fk` (`fk_profile_bookmark_list_possession`),
  CONSTRAINT `bookmark_list_profile_possession_fk` FOREIGN KEY (`fk_profile_bookmark_list_possession`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark_list`
--

LOCK TABLES `bookmark_list` WRITE;
/*!40000 ALTER TABLE `bookmark_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmark_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `description`
--

DROP TABLE IF EXISTS `description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `description` (
  `property_id` int(11) NOT NULL,
  KEY `description_property_id` (`property_id`),
  CONSTRAINT `description_property_id` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `description`
--

LOCK TABLES `description` WRITE;
/*!40000 ALTER TABLE `description` DISABLE KEYS */;
/*!40000 ALTER TABLE `description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information` (
  `information_id` int(11) NOT NULL,
  `input_text` varchar(250) NULL,
  `fk_profile_id` int(11) NOT NULL,
  `fk_property_id` int(11) NOT NULL,
  `fk_search_profile_id` int(11) NULL,
  PRIMARY KEY (`information_id`),
  KEY `information_profile_profile_id_fk` (`fk_profile_id`),
  KEY `information_prop_fk` (`fk_property_id`),
  KEY `Information_search_p_id_fk` (`fk_search_profile_id`),
  CONSTRAINT `Information_search_p_id_fk` FOREIGN KEY (`fk_search_profile_id`) REFERENCES `search_profile` (`search_profile_id`),
  CONSTRAINT `information_profile_profile_id_fk` FOREIGN KEY (`fk_profile_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `information_prop_fk` FOREIGN KEY (`fk_property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `profile_id` int(11) NOT NULL,
  `email` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `first_name` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `last_name` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `gender` enum('w','m') COLLATE latin1_german1_ci NOT NULL,
  `birthdate` date NOT NULL,
  `location` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `height` int(11) NOT NULL,
  `physique` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `hair_color` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `smoker` enum('TRUE','FALSE') CHARACTER SET latin1 NOT NULL,
  `education` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `profession` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `religion` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `property_id` int(11) NOT NULL,
  `explanation` varchar(250) NOT NULL,
  PRIMARY KEY (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_profile`
--

DROP TABLE IF EXISTS `search_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_profile` (
  `search_profile_id` int(11) NOT NULL,
  `gender` enum('w','m') NULL,
  `min_age` int(11) NULL,
  `max_age` int(11) NULL,
  `hair_color` varchar(45) NULL,
  `physique` varchar(45) NULL,
  `min_height` int(11) NULL,
  `max_height` int(11) NULL,
  `smoker` enum('TRUE','FALSE') NULL,
  `education` varchar(45) NULL,
  `profession` varchar(45) NULL,
  `religion` varchar(45) NULL,
  PRIMARY KEY (`search_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_profile`
--

LOCK TABLES `search_profile` WRITE;
/*!40000 ALTER TABLE `search_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `search_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selection`
--

DROP TABLE IF EXISTS `selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selection` (
  `property_id` int(11) NOT NULL,
  KEY `selection_property_id` (`property_id`),
  CONSTRAINT `selection_property_id` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selection`
--

LOCK TABLES `selection` WRITE;
/*!40000 ALTER TABLE `selection` DISABLE KEYS */;
/*!40000 ALTER TABLE `selection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selection_item`
--

DROP TABLE IF EXISTS `selection_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selection_item` (
  `selection_item_id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `fk_selection` int(11) NOT NULL,
  PRIMARY KEY (`selection_item_id`),
  KEY `selection_item_selection_id_fk_idx` (`fk_selection`),
  CONSTRAINT `selection_item_selection_id_fk` FOREIGN KEY (`fk_selection`) REFERENCES `selection` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selection_item`
--

LOCK TABLES `selection_item` WRITE;
/*!40000 ALTER TABLE `selection_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `selection_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `similarity_degree`
--

DROP TABLE IF EXISTS `similarity_degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `similarity_degree` (
  `similarity_degree_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `fk_profile_reference` int(11) NOT NULL,
  `fk_profile_comparison` int(11) NOT NULL,
  PRIMARY KEY (`similarity_degree_id`),
  KEY `similarity_degree_reference_fk` (`fk_profile_reference`),
  KEY `similarity_degree_comparison_fk` (`fk_profile_comparison`),
  CONSTRAINT `similarity_degree_comparison_fk` FOREIGN KEY (`fk_profile_comparison`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `similarity_degree_reference_fk` FOREIGN KEY (`fk_profile_reference`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `similarity_degree`
--

LOCK TABLES `similarity_degree` WRITE;
/*!40000 ALTER TABLE `similarity_degree` DISABLE KEYS */;
/*!40000 ALTER TABLE `similarity_degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit` (
  `visit_id` int(11) NOT NULL,
  `fk_profile_visitor` int(11) NOT NULL,
  `fk_profile_visited` int(11) NOT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `visit_visitor_fk` (`fk_profile_visitor`),
  KEY `visit_visited_fk` (`fk_profile_visited`),
  CONSTRAINT `visit_visited_fk` FOREIGN KEY (`fk_profile_visited`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `visit_visitor_fk` FOREIGN KEY (`fk_profile_visitor`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-29 17:52:13
