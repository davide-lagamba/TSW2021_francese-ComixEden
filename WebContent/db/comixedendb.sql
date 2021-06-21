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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Manga'),(2,'Supereroi'),(3,'Italiani'),(4,'43'),(5,'batmna'),(6,'ewfwef'),(7,'yyyyy'),(8,'234234'),(9,'eeee');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettaglio_ordine`
--

LOCK TABLES `dettaglio_ordine` WRITE;
/*!40000 ALTER TABLE `dettaglio_ordine` DISABLE KEYS */;
INSERT INTO `dettaglio_ordine` VALUES (1,5,24,6.831999999999999,7,1,22,'Spider Man',20),(2,5,24,6.831999999999999,7,1,22,'Spider Man',20),(3,2,26,7.32,6,1,22,'Superman',0),(4,5,27,6.831999999999999,7,1,22,'Spider Man',20),(5,7,28,36.6,6,5,22,'One Piece',0),(6,6,28,6.831999999999999,7,1,22,'DareDevil',20),(7,7,29,7.32,6,1,22,'One Piece',0),(8,4,31,36.6,6,5,22,'Iron Man',0),(9,4,32,14.64,6,2,22,'Iron Man',0),(10,2,32,14.64,6,2,22,'Superman',0),(11,7,32,7.32,6,1,22,'One Piece',0),(12,7,32,7.32,6,1,22,'One Piece',0),(13,3,32,6.588000000000001,6,1,22,'Tex',10),(14,2,32,7.32,6,1,22,'Superman',0),(15,1,32,6.1,5,1,22,'DragonBall',0),(16,5,32,6.831999999999999,7,1,22,'Spider Man',20),(17,2,32,7.32,6,1,22,'Superman',0),(18,4,32,7.32,6,1,22,'Iron Man',0),(19,5,33,6.831999999999999,7,1,22,'Spider Man',20),(20,1,34,6.1,5,1,22,'DragonBall',0),(21,1,35,30.5,5,5,22,'DragonBall',0),(22,7,36,7.32,6,1,22,'One Piece',0),(23,2,37,7.32,6,1,22,'Superman',0),(24,10,38,1464,40,30,22,'Batman',0),(25,4,39,14.64,6,2,22,'Iron Man',0),(26,1,39,18.299999999999997,5,3,22,'DragonBall',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES ('2.jpg',2,1,3),('tex1.jpg',3,1,4),('4.jpg',4,1,5),('5.jpg',5,1,6),('6.jpg',6,1,7),('7.jpg',7,1,8),('8.jpg',8,1,9),('topolino.jpg',13,1,12),('batman.jpg',14,1,13),('ratman.jpg',15,1,14),('monster.jpg',16,1,15),('eden.jpg',17,1,16),('planetes.jpg',18,1,17),('akira.jpg',19,1,18),('eva.jpg',20,1,19),('db1.jpg',1,1,22),('db2.jpg',1,0,23);
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo` (
  `id_indirizzo` int NOT NULL AUTO_INCREMENT,
  `stato` varchar(45) NOT NULL,
  `citta` varchar(45) NOT NULL,
  `cap` varchar(5) NOT NULL,
  `via` varchar(45) NOT NULL,
  PRIMARY KEY (`id_indirizzo`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (1,'italia','napoli','12121','cv dvddx'),(2,'italia','napoli','12121','Sddscfsd'),(3,'italia','Dcscf','12121','Dfvdsv'),(4,'italia','napoli','12121','Dfvdsv'),(5,'italia','po','999','jjjjjj'),(6,'italia','Mario','343','rede'),(7,'italia','Torino','801','looooo'),(8,'italia','Torino','80123','looooo'),(9,'italia','Torino','80134','looooo'),(10,'italia','Torino','80138','looooo'),(11,'italia','Torino','80133','looooo'),(12,'italia','Torino','23232','d2ed23d'),(13,'italia','Milano','23232','dedehdeu'),(14,'italia','1231','12214','1212'),(15,'italia','Milano','23124','131212');
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locazione`
--

DROP TABLE IF EXISTS `locazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locazione` (
  `id_indirizzo` int NOT NULL,
  `id_utente` int NOT NULL,
  PRIMARY KEY (`id_indirizzo`,`id_utente`),
  KEY `id_utente_locazione_idx` (`id_utente`),
  CONSTRAINT `id_indirizzo_locazione` FOREIGN KEY (`id_indirizzo`) REFERENCES `indirizzo` (`id_indirizzo`),
  CONSTRAINT `id_utente_locazione` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locazione`
--

LOCK TABLES `locazione` WRITE;
/*!40000 ALTER TABLE `locazione` DISABLE KEYS */;
INSERT INTO `locazione` VALUES (2,17),(3,17),(4,17),(5,17),(6,18),(7,18),(8,18),(9,18),(10,18),(11,18),(12,19),(13,19),(14,21),(15,21);
/*!40000 ALTER TABLE `locazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pagamento`
--

DROP TABLE IF EXISTS `metodo_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_pagamento` (
  `numero` varchar(16) NOT NULL,
  `scadenza` date NOT NULL,
  `circuito` varchar(45) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pagamento`
--

LOCK TABLES `metodo_pagamento` WRITE;
/*!40000 ALTER TABLE `metodo_pagamento` DISABLE KEYS */;
INSERT INTO `metodo_pagamento` VALUES ('1111111111111111','1999-12-31','visa'),('1234567834321234','2021-06-25','visa'),('1234567891234563','2021-07-07','visa'),('1234567891234567','2021-06-25','visa'),('2312312312313124','2021-06-29','visa'),('2313123211111111','2021-08-26','visa');
/*!40000 ALTER TABLE `metodo_pagamento` ENABLE KEYS */;
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
  `id_pagamento` varchar(16) DEFAULT NULL,
  `costo_spedizione` double DEFAULT NULL,
  `prezzo_totale` double NOT NULL,
  `quantita` int NOT NULL,
  `data` date NOT NULL,
  `consegnato` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_ordine`),
  KEY `id_utente_ordine_idx` (`id_utente`),
  KEY `id_fatturazione_ordine_idx` (`id_fatturazione`),
  KEY `id_pagamento_ordine_idx` (`id_pagamento`),
  KEY `id_spedizione_ordine_idx` (`id_spedizione`),
  CONSTRAINT `id_fatturazione_ordine` FOREIGN KEY (`id_fatturazione`) REFERENCES `indirizzo` (`id_indirizzo`),
  CONSTRAINT `id_pagamento_ordine` FOREIGN KEY (`id_pagamento`) REFERENCES `metodo_pagamento` (`numero`),
  CONSTRAINT `id_spedizione_ordine` FOREIGN KEY (`id_spedizione`) REFERENCES `indirizzo` (`id_indirizzo`),
  CONSTRAINT `id_utente_ordine` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (12,17,3,2,'','1111111111111111',5,6.1,1,'2021-06-17',0),(23,17,3,4,'','1111111111111111',5,7.32,1,'2021-06-19',0),(24,17,4,4,'','1111111111111111',5,6.831999999999999,1,'2021-06-19',0),(26,17,5,5,'','1111111111111111',5,7.32,1,'2021-06-19',0),(27,18,7,6,'','1234567891234567',5,6.831999999999999,1,'2021-06-19',0),(28,18,7,7,'','1234567891234567',5,43.432,6,'2021-06-19',0),(29,18,7,7,'','1234567891234567',5,7.32,1,'2021-06-19',0),(30,18,7,7,'','1234567891234567',5,0,0,'2021-06-19',0),(31,18,7,7,'','1234567891234567',5,36.6,5,'2021-06-19',0),(32,18,7,7,'yyu','1234567891234567',5,29.28,4,'2021-06-19',0),(33,18,9,8,'','1234567834321234',5,6.831999999999999,1,'2021-06-19',0),(34,18,9,10,'','1234567891234563',5,6.1,1,'2021-06-19',0),(35,18,11,10,'','1234567891234567',5,30.5,5,'2021-06-20',0),(36,18,11,11,'','1234567891234567',5,7.32,1,'2021-06-20',0),(37,19,13,12,'Veloce','2312312312313124',5,7.32,1,'2021-06-20',0),(38,19,13,13,'','2312312312313124',5,1464,30,'2021-06-20',0),(39,21,15,14,'','2313123211111111',5,32.94,5,'2021-06-20',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (1,5,'Primo volume','Nuovo',50,150,'Akira Toriyama','Italiano','2020-12-10',30,0,'BW',22,NULL,1,1,'DragonBall'),(2,6,'Primo volume','Nuovo',60,200,'DC Comics','Italiano','2020-11-12',14,0,'BW',22,NULL,2,2,'Superman'),(3,54,'Primo volume','Nuovo',65,34,'Giovanni Luigi Bonelli','Italiano','2019-11-12',45,65,'BW',3,NULL,3,3,'Tex'),(4,6,'Secondo Volume','Usato',50,150,'Marvel','Italiano','2020-02-02',10,0,'BW',22,NULL,2,2,'Iron Man'),(5,7,'Secondo Volume','Nuovo',40,100,'Marvel','Italiano','2020-03-01',20,20,'BW',22,NULL,2,2,'Spider Man'),(6,7,'Primo Volume','Nuovo',30,80,'Marvel','Italiano','2019-03-12',14,20,'BW',22,NULL,2,2,'DareDevil'),(7,6,'Primo Volume','Nuovo',50,150,'Eichiro Oda','Italiano','2019-04-12',11,0,'BW',22,NULL,1,1,'One Piece'),(8,6,'Primo Volume','Nuovo',50,150,'Masashi Kishimoto','Italiano','2018-12-11',10,0,'BW',22,NULL,1,1,'Naruto'),(13,5,'Primo Volume','Nuovo',40,200,'Disney','Italiano','2017-12-12',20,0,'BW',22,NULL,3,3,'Topolino'),(14,6,'Primo Volume','Nuovo',50,140,'DC Comics','Inglese','2015-12-12',30,10,'BW',22,NULL,2,2,'Batman'),(15,7,'Collection 66','Usato',30,200,'Leo Ortolani','Italiano','2016-12-22',23,10,'BW',22,NULL,3,3,'Ratman'),(16,15,'Primo Volume','Nuovo',100,400,'Naoki Urasawa','Italiano','2014-12-12',10,0,'BW',22,NULL,1,1,'Monster'),(17,15,'Primo Volume','Nuovo',90,350,'Hiroki Endo','Italiano','2016-09-23',30,0,'BW',22,NULL,1,1,'Eden'),(18,30,'Volume unico','Nuovo',200,800,'Makoto Yukimura','Italiano','2019-12-11',20,0,'BW',22,NULL,1,1,'Planetes'),(19,20,'Quarto Volume','Nuovo',100,400,'Katsuhiro Otomo','Italiano','2018-12-11',20,0,'BW',22,NULL,1,1,'Akira'),(20,10,'Primo Volume','Nuovo',50,100,'Yoshiyuki Sadamoto','Italiano','2017-12-11',20,0,'BW',22,NULL,1,1,'Evangelion');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produttore`
--

LOCK TABLES `produttore` WRITE;
/*!40000 ALTER TABLE `produttore` DISABLE KEYS */;
INSERT INTO `produttore` VALUES (1,'123456','Panini'),(2,'213123','StarComix'),(3,'123123','PlanetManga'),(4,NULL,'43'),(5,NULL,'batmna'),(6,NULL,'ewfwe'),(7,NULL,'yyyyy'),(8,NULL,'324'),(9,NULL,'eeee');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Hdhdh','Hldhdhd','mariano','123','Mdddl','2021-05-11',0),(2,'mario ','Merlo','pippo23@outlook.it','1234','1233443','2021-05-13',0),(3,'Mario','Nerone','nerone@hotnando.com','pietro','332423423','2021-05-13',0),(4,'Mario','Merlo','Kdmskl','1234','12231312','2021-05-13',0),(5,'Ifjni','Sdkfmsd','Dkfmvkd','dfmvdfklÃ²','LkfdmÃ²lv','2021-05-13',0),(6,'Mariolo','Mariti','tizio','caio','Dwefw','2021-05-13',0),(7,'Mario','Merlo','pippocar','123','32432342','2021-05-13',0),(8,'Jkjkjkkj','Jkjkkjjk','Kmkkjjk','jkkkjkj','Kkjkjkjk','2021-05-13',0),(9,'Ritrar','Tytryr','Rtytry','rtyry','Ytryryry','2021-05-13',0),(10,'Ffwe','Feef','Effe','fefef','Efffe','2021-05-13',0),(11,'Rare','Erer','Erre','ere','Errerete','2021-05-13',1),(12,'Lklkjnkl','Knlknkl','Lknlnlk','kljl','Llhjklhjk','2021-05-13',0),(13,'Fremer','Ferrea','Refer','erfer','Referferf','2021-05-13',0),(14,'Fdojdfoi','Fdkndfl','Vdlkvmkdlf','dfvdfkl','Dflkvmdkl','2021-05-13',0),(15,'Gfgr','Ergere','Gergo','grege','Reger','2021-05-13',0),(16,'Wedwd','End','Wedw','ewdedw','Wdwdwdw','2021-05-13',0),(17,'mario','iezza','mario','mario','3243242','2021-02-01',0),(18,'popo','popo','popo@gmail.com','popo','123456789','2021-06-19',0),(19,'Davide','LaGamba','davide@gmail.com','davide','111111111','2021-06-20',0),(20,'admin','admin','admin','admin','123456780','2021-06-20',1),(21,'nello','nello','nello@gil.com','1234','123456789','2021-06-20',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizzo`
--

DROP TABLE IF EXISTS `utilizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizzo` (
  `id_utente` int NOT NULL,
  `numero_pagamento` varchar(16) NOT NULL,
  PRIMARY KEY (`id_utente`,`numero_pagamento`),
  KEY `numero_pagamento_utente_idx` (`numero_pagamento`),
  CONSTRAINT `id_utente` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`),
  CONSTRAINT `numero_pagamento_utente` FOREIGN KEY (`numero_pagamento`) REFERENCES `metodo_pagamento` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizzo`
--

LOCK TABLES `utilizzo` WRITE;
/*!40000 ALTER TABLE `utilizzo` DISABLE KEYS */;
INSERT INTO `utilizzo` VALUES (17,'1111111111111111'),(18,'1234567834321234'),(18,'1234567891234563'),(18,'1234567891234567'),(19,'2312312312313124'),(21,'2313123211111111');
/*!40000 ALTER TABLE `utilizzo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-21 10:49:43
