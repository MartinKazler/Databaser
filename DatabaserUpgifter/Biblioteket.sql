-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: Bibliotek
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `anställda`
--

DROP TABLE IF EXISTS `anställda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anställda` (
  `AnställdID` int NOT NULL AUTO_INCREMENT,
  `Namn` varchar(255) DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `TeleNr` int DEFAULT NULL,
  `MobilNr` int DEFAULT NULL,
  `Månadslön` int DEFAULT NULL,
  `Semesterdagar` int DEFAULT NULL,
  PRIMARY KEY (`AnställdID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anställda`
--

LOCK TABLES `anställda` WRITE;
/*!40000 ALTER TABLE `anställda` DISABLE KEYS */;
INSERT INTO `anställda` VALUES (1,'Asta Kask','Vägen2, Nollberga',13647,67869,500,24),(2,'Ebba Grön','Vägen 4, Nollberga',365868,6789,88888,25),(3,'Farbror Blå','Vägen 8, Nollberga',68686,NULL,600,67);
/*!40000 ALTER TABLE `anställda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `böcker`
--

DROP TABLE IF EXISTS `böcker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `böcker` (
  `BokID` int NOT NULL AUTO_INCREMENT,
  `Titel` varchar(255) DEFAULT NULL,
  `Författare` varchar(255) DEFAULT NULL,
  `Sidor` int DEFAULT NULL,
  `Klassifikation` varchar(255) DEFAULT NULL,
  `Lånstatus` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`BokID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `böcker`
--

LOCK TABLES `böcker` WRITE;
/*!40000 ALTER TABLE `böcker` DISABLE KEYS */;
INSERT INTO `böcker` VALUES (1,'Den ensamma katten','Rudolf Ruskprick',123,'Hce',0),(2,'Lastbilens tankar','Oskar Rudenerg',452,'uHce',0),(3,'Min faster Ingeborg','Inga Skoghorn',763,'Hcf',1);
/*!40000 ALTER TABLE `böcker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lånadeböcker`
--

DROP TABLE IF EXISTS `lånadeböcker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lånadeböcker` (
  `UlånID` int NOT NULL AUTO_INCREMENT,
  `BokID` int DEFAULT NULL,
  `LånekortID` int DEFAULT NULL,
  PRIMARY KEY (`UlånID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lånadeböcker`
--

LOCK TABLES `lånadeböcker` WRITE;
/*!40000 ALTER TABLE `lånadeböcker` DISABLE KEYS */;
INSERT INTO `lånadeböcker` VALUES (5,2,2);
/*!40000 ALTER TABLE `lånadeböcker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `låntagare`
--

DROP TABLE IF EXISTS `låntagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `låntagare` (
  `LånekortID` int NOT NULL AUTO_INCREMENT,
  `Namn` varchar(255) DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `TeleNr` int DEFAULT NULL,
  `LånekortsNr` int DEFAULT NULL,
  PRIMARY KEY (`LånekortID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `låntagare`
--

LOCK TABLES `låntagare` WRITE;
/*!40000 ALTER TABLE `låntagare` DISABLE KEYS */;
INSERT INTO `låntagare` VALUES (1,'Viggo Filtner','Vägen 1, Nollberga',11111,1234),(2,'Bosse Baron','Vägen 5, Nollberga',3333,3347);
/*!40000 ALTER TABLE `låntagare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tidskrifter`
--

DROP TABLE IF EXISTS `tidskrifter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tidskrifter` (
  `TidningID` int NOT NULL AUTO_INCREMENT,
  `Titel` varchar(255) DEFAULT NULL,
  `Utgivningsdatum` date DEFAULT NULL,
  `Lagerplats` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TidningID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tidskrifter`
--

LOCK TABLES `tidskrifter` WRITE;
/*!40000 ALTER TABLE `tidskrifter` DISABLE KEYS */;
INSERT INTO `tidskrifter` VALUES (1,'Illustrerad Ångest','2020-12-12','Hylla A'),(2,'Gårdagens Tidning','1988-10-10','Hylla C'),(3,'Moderna trasor','2001-01-05','Hylla A');
/*!40000 ALTER TABLE `tidskrifter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-14 17:53:01
