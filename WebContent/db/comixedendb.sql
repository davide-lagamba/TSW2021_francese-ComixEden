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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Manga'),(2,'Supereroi'),(3,'Italiani'),(6,'Combattimento'),(7,'Azione'),(8,'dsdadd'),(9,'4444'),(10,'5665'),(11,'32r'),(12,'456456'),(13,'gdfgdfgdfg'),(14,'qweqwe'),(15,'121221'),(16,'13123'),(17,'ADDWDQWD'),(18,'rrrrr'),(19,'122'),(20,'tr'),(21,'23'),(22,'234'),(23,'324'),(24,'213'),(25,'wqd'),(26,'55'),(27,'231'),(28,'ererepore'),(29,'2431'),(30,'42342342'),(31,'67'),(32,'32');
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettaglio_ordine`
--

LOCK TABLES `dettaglio_ordine` WRITE;
/*!40000 ALTER TABLE `dettaglio_ordine` DISABLE KEYS */;
INSERT INTO `dettaglio_ordine` VALUES (53,1,47,61,5,10,22,'DragonBall',0),(54,2,47,43.92,6,6,22,'Superman',0),(55,1,49,6.1,5,1,22,'DragonBall',0),(56,1,50,6.1,5,1,22,'DragonBall',0),(57,1,51,6.1,5,1,22,'DragonBall',0),(58,1,52,36.599999999999994,5,6,22,'DragonBall',0),(59,2,52,80.52000000000001,6,11,22,'Superman',0),(60,3,52,46.11600000000001,6,7,22,'Tex',10),(61,1,53,6.1,5,1,22,'DragonBall',0),(62,2,54,14.64,6,2,22,'Superman',0),(63,2,55,7.32,6,1,22,'Superman',0),(64,2,56,7.32,6,1,22,'Superman',0),(65,3,56,19.764000000000003,6,3,22,'Tex',10),(66,5,56,6.831999999999999,7,1,22,'Spider Man',20),(67,10,57,50.727599999999995,7,6,22,'fffff',1),(68,8,59,7.32,6,1,22,'Naruto',0),(69,11,60,6.1,5,1,22,'test',0),(70,2,61,58.56,6,8,22,'Superman',0),(71,5,61,13.663999999999998,7,2,22,'Spider Man',20),(72,1,62,7.32,6,1,22,'DragonBall',0);
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
  `id_immagine` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_immagine`),
  KEY `id_prodotto_immagine_idx` (`id_prodotto`),
  CONSTRAINT `id_prodotto_immagine` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES ('1-2.jpg',1,0,1),('1.jpg',1,1,2),('2.jpg',2,1,3),('4.jpg',4,1,4),('5.jpg',5,1,5),('6.jpg',6,1,6),('7.jpg',7,1,7),('tex1.jpg',3,1,10),('Batman_3_variant.jpg',16,1,13);
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (47,1,-1,-1,'',-1,5,104.92,16,'2021-05-13',0),(48,1,-1,-1,NULL,-1,5,0,0,'2021-05-13',0),(49,1,-1,-1,NULL,-1,5,6.1,1,'2021-05-13',0),(50,1,-1,-1,'',-1,5,6.1,1,'2021-05-13',0),(51,9,-1,-1,'',-1,5,6.1,1,'2021-05-14',0),(52,9,-1,-1,'rf',-1,5,163.23600000000002,24,'2021-05-14',0),(53,5,-1,-1,'',-1,5,6.1,1,'2021-05-14',0),(54,9,-1,-1,'',-1,5,14.64,2,'2021-05-14',0),(55,5,-1,-1,'',-1,5,7.32,1,'2021-05-14',0),(56,10,-1,-1,'veloce',-1,5,33.916000000000004,5,'2021-05-16',0),(57,5,-1,-1,'',-1,5,50.727599999999995,6,'2021-05-18',0),(58,5,-1,-1,'',-1,5,0,0,'2021-05-18',0),(59,5,-1,-1,'',-1,5,7.32,1,'2021-05-18',0),(60,5,-1,-1,'',-1,5,6.1,1,'2021-05-18',0),(61,12,-1,-1,'Consegna velocissima',-1,5,72.224,10,'2021-05-20',0),(62,5,-1,-1,'',-1,5,7.32,1,'2021-05-21',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,6,'Primo volume','Nuovo',50,150,'Akira Toriyama','Italiano','2020-12-10',29,0,'BW',22,NULL,1,1,'DragonBall'),(2,6,'Primo volume','Nuovo',60,200,'DC Comics','Italiano','2020-11-12',11,0,'BW',22,NULL,2,2,'Superman'),(3,6,'Primo volume','Nuovo',40,100,'Giovanni Luigi Bonelli','Italiano','2019-11-12',40,10,'BW',22,NULL,3,3,'Tex'),(4,6,'Secondo Volume','Usato',50,150,'Marvel','Italiano','2020-02-02',30,0,'BW',22,NULL,2,2,'Iron Man'),(5,7,'Secondo Volume','Nuovo',40,100,'Marvel','Italiano','2020-03-01',9,20,'BW',22,NULL,2,2,'Spider Man'),(6,7,'Primo Volume','Nuovo',30,80,'Marvel','Italiano','2019-03-12',12,20,'BW',22,NULL,2,2,'DareDevil'),(7,6,'Primo Volume','Nuovo',50,150,'Eichiro Oda','Italiano','2019-04-12',11,0,'BW',22,NULL,1,1,'One Piece'),(16,23,'Secondo volume','Nuovo',0,23,'popo','popo','2021-05-19',23,23,'BW',22,NULL,9,6,'Thor'),(17,33,'Secondo volume','Nuovo',0,32,'Akira Yammaoto','Italiano','2021-05-19',32,32,'BW',32,NULL,9,7,'Toro');
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
  `partita_iva` varchar(45) DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id_produttore`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produttore`
--

LOCK TABLES `produttore` WRITE;
/*!40000 ALTER TABLE `produttore` DISABLE KEYS */;
INSERT INTO `produttore` VALUES (1,'123456','Panini'),(2,'213123','StarComix'),(3,'123123','PlanetManga'),(9,NULL,'Animeniacs'),(10,NULL,'wewewe'),(11,NULL,'444'),(12,NULL,'5656'),(13,NULL,'32r'),(14,NULL,'456464'),(15,NULL,'gdfgdfgdf'),(16,NULL,'weqwe'),(17,NULL,'12323'),(18,NULL,'3123123'),(19,NULL,'ASDASD'),(20,NULL,'rrrrrr'),(21,NULL,'1212'),(22,NULL,'rt'),(23,NULL,'we'),(24,NULL,'1234'),(25,NULL,'324'),(26,NULL,'123'),(27,NULL,'qwd'),(28,NULL,'55'),(29,NULL,'eproepr'),(30,NULL,'4213'),(31,NULL,'42342'),(32,NULL,'67'),(33,NULL,'23');
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
  PRIMARY KEY (`id_utente`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'aa','aa','dd','dw','12','2021-12-04',0),(2,'bb','bb','bb','bb','122','2021-11-04',0),(3,'DAVIDE','LA GAMBA','prova','123','23','2021-05-13',0),(5,'popo','popo','popo','popo','123','2021-05-14',0),(6,'qq','qq','qq','qq','123','2021-05-14',0),(7,'qs','qs','qs','qs','qs','2021-05-14',0),(8,'df','df','df','df','123','2021-05-14',0),(9,'ff','ff','ff','ff','ff','2021-05-14',0),(10,'sara','sara','sara','sara','123','2021-05-16',0),(11,'admin','admin','admin','admin','123','2021-12-12',1),(12,'Mirko','Pacelli','mirko','123','123','2021-05-20',0);
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

-- Dump completed on 2021-05-22 11:33:38
