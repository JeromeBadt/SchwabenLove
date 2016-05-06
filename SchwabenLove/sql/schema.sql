-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: schwabenlove
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.13-MariaDB

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
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `block_id` int(11) NOT NULL,
  `fk_blocker_profile_id` int(11) NOT NULL,
  `fk_blocked_profile_id` int(11) NOT NULL,
  PRIMARY KEY (`block_id`),
  KEY `block_blocked_profile_id_fk` (`fk_blocked_profile_id`),
  KEY `block_blocker_profile_id_fk` (`fk_blocker_profile_id`),
  CONSTRAINT `block_blocked_profile_id_fk` FOREIGN KEY (`fk_blocked_profile_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `block_blocker_profile_id_fk` FOREIGN KEY (`fk_blocker_profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark` (
  `bookmark_id` int(11) NOT NULL,
  `fk_profile_id` int(11) NOT NULL,
  `fk_bookmark_list_id` int(11) NOT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `bookmark_bookmark_list_id_fk_idx` (`fk_bookmark_list_id`),
  KEY `bookmark_profile_id_fk` (`fk_profile_id`),
  CONSTRAINT `bookmark_bookmark_list_id_fk` FOREIGN KEY (`fk_bookmark_list_id`) REFERENCES `bookmark_list` (`bookmark_list_id`),
  CONSTRAINT `bookmark_profile_id_fk` FOREIGN KEY (`fk_profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bookmark_list`
--

DROP TABLE IF EXISTS `bookmark_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmark_list` (
  `bookmark_list_id` int(11) NOT NULL,
  `fk_profile_id` int(11) NOT NULL,
  PRIMARY KEY (`bookmark_list_id`),
  KEY `bookmark_list_profile_id_fk` (`fk_profile_id`),
  CONSTRAINT `bookmark_list_profile_id_fk` FOREIGN KEY (`fk_profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `description`
--

DROP TABLE IF EXISTS `description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `description` (
  `fk_property_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_property_id`),
  KEY `description_property_id` (`fk_property_id`),
  CONSTRAINT `description_property_id_fk` FOREIGN KEY (`fk_property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information` (
  `information_id` int(11) NOT NULL,
  `input_text` varchar(250) DEFAULT NULL,
  `fk_profile_id` int(11) NOT NULL,
  `fk_property_id` int(11) NOT NULL,
  `fk_search_profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`information_id`),
  KEY `information_profile_id_fk` (`fk_profile_id`),
  KEY `information_property_id_fk` (`fk_property_id`),
  KEY `information_search_profile_id_fk` (`fk_search_profile_id`),
  CONSTRAINT `information_profile_id_fk` FOREIGN KEY (`fk_profile_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `information_property_id_fk` FOREIGN KEY (`fk_property_id`) REFERENCES `property` (`property_id`),
  CONSTRAINT `information_search_profile_id_fk` FOREIGN KEY (`fk_search_profile_id`) REFERENCES `search_profile` (`search_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `search_profile`
--

DROP TABLE IF EXISTS `search_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_profile` (
  `search_profile_id` int(11) NOT NULL,
  `gender` enum('w','m') DEFAULT NULL,
  `min_age` int(11) DEFAULT NULL,
  `max_age` int(11) DEFAULT NULL,
  `hair_color` varchar(45) DEFAULT NULL,
  `physique` varchar(45) DEFAULT NULL,
  `min_height` int(11) DEFAULT NULL,
  `max_height` int(11) DEFAULT NULL,
  `smoker` enum('TRUE','FALSE') DEFAULT NULL,
  `education` varchar(45) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  `religion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`search_profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `selection`
--

DROP TABLE IF EXISTS `selection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selection` (
  `fk_property_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_property_id`),
  KEY `selection_property_id` (`fk_property_id`),
  CONSTRAINT `selection_property_id_fk` FOREIGN KEY (`fk_property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `selection_item`
--

DROP TABLE IF EXISTS `selection_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `selection_item` (
  `selection_item_id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `fk_selection_id` int(11) NOT NULL,
  PRIMARY KEY (`selection_item_id`),
  KEY `selection_item_selection_id_fk_idx` (`fk_selection_id`),
  CONSTRAINT `selection_item_selection_id_fk` FOREIGN KEY (`fk_selection_id`) REFERENCES `selection` (`fk_property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `similarity_degree`
--

DROP TABLE IF EXISTS `similarity_degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `similarity_degree` (
  `similarity_degree_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `fk_reference_profile_id` int(11) NOT NULL,
  `fk_comparison_profile_id` int(11) NOT NULL,
  PRIMARY KEY (`similarity_degree_id`),
  KEY `similarity_degree_reference_profile_id_fk` (`fk_reference_profile_id`),
  KEY `similarity_degree_comparison_profile_id_fk` (`fk_comparison_profile_id`),
  CONSTRAINT `similarity_degree_comparison_profile_id_fk` FOREIGN KEY (`fk_comparison_profile_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `similarity_degree_reference_profile_id_fk` FOREIGN KEY (`fk_reference_profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit` (
  `visit_id` int(11) NOT NULL,
  `fk_visitor_profile_id` int(11) NOT NULL,
  `fk_visited_profile_id` int(11) NOT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `visit_visitor_profile_id_fk` (`fk_visitor_profile_id`),
  KEY `visit_visited_profile_id_fk` (`fk_visited_profile_id`),
  CONSTRAINT `visit_visited_profile_id_fk` FOREIGN KEY (`fk_visited_profile_id`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `visit_visitor_profile_id_fk` FOREIGN KEY (`fk_visitor_profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-06 12:58:19
