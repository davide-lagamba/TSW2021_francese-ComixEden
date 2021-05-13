CREATE DATABASE  IF NOT EXISTS `comixedendb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `comixedendb`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: comixedendb
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Manga'),(2,'Supereroi'),(3,'Italiani');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dettaglio_ordine`
--

DROP TABLE IF EXISTS `dettaglio_ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dettaglio_ordine` (
  `id_dettaglio` int NOT NULL AUTO_INCREMENT,
  `id_prodotto` int NOT NULL,
  `id_ordine` int NOT NULL,
  `prezzo_tot` double NOT NULL,
  `prezzo_singolo` double NOT NULL,
  `quantita` int NOT NULL,
  `iva` double NOT NULL,
  `nome` varchar(45) NOT NULL,
  `sconto` double NOT NULL,
  PRIMARY KEY (`id_dettaglio`),
  KEY `id_ordine_idx` (`id_ordine`),
  CONSTRAINT `id_ordine` FOREIGN KEY (`id_ordine`) REFERENCES `ordine` (`id_ordine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettaglio_ordine`
--

LOCK TABLES `dettaglio_ordine` WRITE;
/*!40000 ALTER TABLE `dettaglio_ordine` DISABLE KEYS */;
/*!40000 ALTER TABLE `dettaglio_ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagine`
--

DROP TABLE IF EXISTS `immagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immagine` (
  `Nome` varchar(45) NOT NULL,
  `id_prodotto` int NOT NULL,
  `copertina` tinyint(1) NOT NULL,
  PRIMARY KEY (`Nome`),
  KEY `id_prodotto_immagine_idx` (`id_prodotto`),
  CONSTRAINT `id_prodotto_immagine` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES ('1-2.jpg',1,0),('1.jpg',1,1),('2.jpg',2,1),('3.jpg',3,1),('4.jpg',4,1),('5.jpg',5,1),('6.jpg',6,1),('7.jpg',7,1),('8.jpg',8,1);
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `id_ordine` int NOT NULL AUTO_INCREMENT,
  `id_utente` int NOT NULL,
  `id_spedizione` int DEFAULT NULL,
  `id_fatturazione` int DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `id_pagamento` int DEFAULT NULL,
  `costo_spedizione` double DEFAULT NULL,
  `prezzo_totale` double NOT NULL,
  `quantita` int NOT NULL,
  `data` date NOT NULL,
  `consegnato` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  KEY `id_utente_ordine_idx` (`id_utente`),
  CONSTRAINT `id_utente_ordine` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `id_prodotto` int NOT NULL AUTO_INCREMENT,
  `prezzo_base` double NOT NULL,
  `descrizione` varchar(45) NOT NULL,
  `stato` varchar(45) NOT NULL,
  `peso` decimal(10,0) NOT NULL,
  `pagine` int DEFAULT NULL,
  `autori` varchar(45) DEFAULT NULL,
  `lingua` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `disponibilita` int NOT NULL,
  `sconto` int NOT NULL,
  `colore_stampa` varchar(45) NOT NULL,
  `iva` double NOT NULL,
  `score_medio` double DEFAULT NULL,
  `id_produttore` int DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id_prodotto`),
  KEY `id_produttore_idx` (`id_produttore`),
  KEY `id_categoria_idx` (`id_categoria`),
  CONSTRAINT `id_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `id_produttore` FOREIGN KEY (`id_produttore`) REFERENCES `produttore` (`id_produttore`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,5,'Primo volume','Nuovo',50,150,'Akira Toriyama','Italiano','2020-12-10',40,0,'BW',22,NULL,1,1,'DragonBall'),(2,6,'Primo volume','Nuovo',60,200,'DC Comics','Italiano','2020-11-12',20,0,'BW',22,NULL,2,2,'Superman'),(3,6,'Primo volume','Nuovo',40,100,'Giovanni Luigi Bonelli','Italiano','2019-11-12',23,10,'BW',22,NULL,3,3,'Tex'),(4,6,'Secondo Volume','Usato',50,150,'Marvel','Italiano','2020-02-02',20,0,'BW',22,NULL,2,2,'Iron Man'),(5,7,'Secondo Volume','Nuovo',40,100,'Marvel','Italiano','2020-03-01',10,20,'BW',22,NULL,2,2,'Spider Man'),(6,7,'Primo Volume','Nuovo',30,80,'Marvel','Italiano','2019-03-12',15,20,'BW',22,NULL,2,2,'DareDevil'),(7,6,'Primo Volume','Nuovo',50,150,'Eichiro Oda','Italiano','2019-04-12',20,0,'BW',22,NULL,1,1,'One Piece'),(8,6,'Primo Volume','Nuovo',50,150,'Masashi Kishimoto','Italiano','2018-12-11',10,0,'BW',22,NULL,1,1,'Naruto');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produttore`
--

DROP TABLE IF EXISTS `produttore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produttore` (
  `id_produttore` int NOT NULL AUTO_INCREMENT,
  `partita_iva` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id_produttore`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produttore`
--

LOCK TABLES `produttore` WRITE;
/*!40000 ALTER TABLE `produttore` DISABLE KEYS */;
INSERT INTO `produttore` VALUES (1,'123456','Panini'),(2,'213123','StarComix'),(3,'123123','PlanetManga');
/*!40000 ALTER TABLE `produttore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id_utente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `data_registrazione` date NOT NULL,
  `admin` tinyint NOT NULL,
  PRIMARY KEY (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-10 13:54:34