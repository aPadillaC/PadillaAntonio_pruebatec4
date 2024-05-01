-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: agencia
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (2,'antonio.padilla@example.com',_binary '\0','Padilla','Lucia','12345678J'),(52,'lucia.sanchez@example.com',_binary '\0','Sanchez','Lucia','98765432Y'),(53,'carmen.munoz@example.com',_binary '\0','Muñoz','Carmen','02345678J'),(152,'jose.doe@example',_binary '\0','Doe','Jose','55555555T'),(202,'marta.gil@example.com',_binary '\0','Gil','Marta','85245698P'),(203,'ale.vega@example.com',_binary '\0','Vega','Ale','45600000T');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_flight_booking`
--

DROP TABLE IF EXISTS `client_flight_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_flight_booking` (
  `flight_booking_id` int NOT NULL,
  `client_id` int NOT NULL,
  KEY `FK9wakxd9rvg6xk17bxqdp6ttgc` (`client_id`),
  KEY `FKmo4641pwv4qjq1or8umfo0no7` (`flight_booking_id`),
  CONSTRAINT `FK9wakxd9rvg6xk17bxqdp6ttgc` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FKmo4641pwv4qjq1or8umfo0no7` FOREIGN KEY (`flight_booking_id`) REFERENCES `flight_booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_flight_booking`
--

LOCK TABLES `client_flight_booking` WRITE;
/*!40000 ALTER TABLE `client_flight_booking` DISABLE KEYS */;
INSERT INTO `client_flight_booking` VALUES (1,2),(1,2),(2,52),(2,53);
/*!40000 ALTER TABLE `client_flight_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_seq`
--

DROP TABLE IF EXISTS `client_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_seq`
--

LOCK TABLES `client_seq` WRITE;
/*!40000 ALTER TABLE `client_seq` DISABLE KEYS */;
INSERT INTO `client_seq` VALUES (301);
/*!40000 ALTER TABLE `client_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` int NOT NULL,
  `available_seats` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `flight_code` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `origin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,48,'2024-05-05','Madrid','BuMad1',_binary '\0','Buenos Aires'),(2,40,'2024-05-06','Paris','MaPar2',_binary '\0','Madrid'),(3,55,'2024-05-07','London','PaLon3',_binary '\0','Paris'),(4,60,'2024-05-08','New York','LoNew4',_binary '\0','London'),(5,45,'2024-05-09','Los Angeles','NeLos5',_binary '\0','New York'),(6,30,'2024-05-10','Tokyo','LoTok6',_binary '\0','Los Angeles'),(7,55,'2024-05-11','Sydney','ToSyd7',_binary '\0','Tokyo'),(8,45,'2024-05-12','Dubai','SyDub8',_binary '\0','Sydney'),(9,50,'2024-05-13','Istanbul','DuIst9',_binary '\0','Dubai'),(10,40,'2024-05-14','Moscow','IsMos10',_binary '\0','Istanbul'),(11,35,'2024-05-15','Beijing','MoBei11',_binary '\0','Moscow'),(12,45,'2024-05-16','Shanghai','BeSha12',_binary '\0','Beijing'),(13,50,'2024-05-17','Hong Kong','ShHon13',_binary '\0','Shanghai'),(14,40,'2024-05-18','Singapore','HoSin14',_binary '\0','Hong Kong'),(15,55,'2024-05-19','Bangkok','SiBan15',_binary '\0','Singapore'),(16,45,'2024-05-20','Buenos Aires','MaBue16',_binary '\0','Madrid'),(17,55,'2024-05-21','Madrid','PaMad17',_binary '\0','Paris'),(18,50,'2024-05-22','Paris','LoPar18',_binary '\0','London'),(19,60,'2024-05-23','London','NeLon19',_binary '\0','New York'),(20,50,'2024-05-24','New York','LoNew20',_binary '\0','Los Angeles'),(21,40,'2024-05-25','Los Angeles','ToLos21',_binary '\0','Tokyo'),(22,55,'2024-05-26','Tokyo','SyTok22',_binary '\0','Sydney'),(23,60,'2024-05-27','Sydney','DuSyd23',_binary '\0','Dubai'),(24,50,'2024-05-28','Dubai','IsDub24',_binary '\0','Istanbul'),(25,45,'2024-05-29','Istanbul','MoIst25',_binary '\0','Moscow'),(26,40,'2024-05-30','Moscow','BeMos26',_binary '\0','Beijing'),(27,35,'2024-05-31','Beijing','ShBei27',_binary '\0','Shanghai'),(28,45,'2024-06-01','Shanghai','HoSha28',_binary '\0','Hong Kong'),(29,50,'2024-06-02','Hong Kong','SiHon29',_binary '\0','Singapore'),(30,40,'2024-06-03','Singapore','BaSin30',_binary '\0','Bangkok');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_booking`
--

DROP TABLE IF EXISTS `flight_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_booking` (
  `id` int NOT NULL,
  `booking_code` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `seat_price` double DEFAULT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  `flight_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3uiklmjy1d7ba6rrjp6iq08kq` (`flight_id`),
  CONSTRAINT `FK3uiklmjy1d7ba6rrjp6iq08kq` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_booking`
--

LOCK TABLES `flight_booking` WRITE;
/*!40000 ALTER TABLE `flight_booking` DISABLE KEYS */;
INSERT INTO `flight_booking` VALUES (1,'BuMad1/BF1',_binary '\0',850,'Deluxe',1),(2,'BuMad1/BF2',_binary '',150,'Economy',1);
/*!40000 ALTER TABLE `flight_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_booking_seq`
--

DROP TABLE IF EXISTS `flight_booking_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_booking_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_booking_seq`
--

LOCK TABLES `flight_booking_seq` WRITE;
/*!40000 ALTER TABLE `flight_booking_seq` DISABLE KEYS */;
INSERT INTO `flight_booking_seq` VALUES (101);
/*!40000 ALTER TABLE `flight_booking_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_seq`
--

DROP TABLE IF EXISTS `flight_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_seq`
--

LOCK TABLES `flight_seq` WRITE;
/*!40000 ALTER TABLE `flight_seq` DISABLE KEYS */;
INSERT INTO `flight_seq` VALUES (101);
/*!40000 ALTER TABLE `flight_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `hotel_code` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'City1','HoCit1',_binary '\0','Hotel1'),(2,'City2','HoCit2',_binary '\0','Hotel2'),(3,'City3','HoCit3',_binary '\0','Hotel3'),(4,'City4','HoCit4',_binary '\0','Hotel4'),(5,'City5','HoCit5',_binary '\0','Hotel5'),(6,'City6','HoCit6',_binary '\0','Hotel6'),(7,'City7','HoCit7',_binary '\0','Hotel7'),(8,'City8','HoCit8',_binary '\0','Hotel8'),(9,'City9','HoCit9',_binary '\0','Hotel9'),(10,'Ciudad Autentication','HOCIU10',_binary '\0','Hotel Autentication'),(52,'Malaga','LAMAL1',_binary '\0','Larios'),(102,'Malaga','LAMAL2',_binary '\0','Larios');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_seq`
--

DROP TABLE IF EXISTS `hotel_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_seq`
--

LOCK TABLES `hotel_seq` WRITE;
/*!40000 ALTER TABLE `hotel_seq` DISABLE KEYS */;
INSERT INTO `hotel_seq` VALUES (201);
/*!40000 ALTER TABLE `hotel_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `is_booked` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `room_code` varchar(255) DEFAULT NULL,
  `room_price` double DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `hotel_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`),
  CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'2024-05-31','2024-06-25',_binary '',_binary '\0','HoCit1/R1',1500,'Economy',1),(2,'2024-05-31','2024-06-25',_binary '',_binary '\0','HoCit1/R1',750,'Economy',1),(3,'2024-06-01','2024-06-20',_binary '',_binary '\0','HoCit1/R2',2000,'Deluxe',1),(4,'2024-06-10','2024-06-30',_binary '',_binary '\0','HoCit1/R3',900,'Economy',1),(5,'2024-05-31','2024-06-25',_binary '\0',_binary '\0','HoCit2/R1',2200,'Deluxe',2),(6,'2024-06-01','2024-06-20',_binary '\0',_binary '\0','HoCit2/R2',1600,'Economy',2),(7,'2024-06-10','2024-06-30',_binary '\0',_binary '\0','HoCit2/R3',2500,'Deluxe',2),(8,'2024-05-31','2024-06-25',_binary '\0',_binary '\0','HoCit3/R1',1700,'Economy',3),(9,'2024-06-01','2024-06-20',_binary '\0',_binary '\0','HoCit3/R2',2300,'Deluxe',3),(10,'2024-06-10','2024-06-30',_binary '\0',_binary '\0','HoCit3/R3',1850,'Economy',3),(11,'2024-05-30','2024-06-20',_binary '\0',_binary '\0','HoCit4/R1',1100,'Economy',4),(12,'2024-06-05','2024-06-25',_binary '\0',_binary '\0','HoCit4/R2',1700,'Deluxe',4),(13,'2024-06-15','2024-07-05',_binary '\0',_binary '\0','HoCit4/R3',1300,'Economy',4),(14,'2024-05-25','2024-06-15',_binary '\0',_binary '\0','HoCit5/R1',950,'Economy',5),(15,'2024-06-10','2024-07-05',_binary '\0',_binary '\0','HoCit5/R2',2100,'Deluxe',5),(16,'2024-06-20','2024-07-15',_binary '\0',_binary '\0','HoCit5/R3',1100,'Economy',5),(17,'2024-05-25','2024-06-15',_binary '\0',_binary '\0','HoCit8/R1',950,'Economy',8),(18,'2024-06-10','2024-07-05',_binary '\0',_binary '\0','HoCit8/R2',2100,'Deluxe',8),(19,'2024-06-20','2024-07-15',_binary '\0',_binary '\0','HoCit8/R3',1100,'Economy',8),(20,'2024-05-30','2024-06-20',_binary '\0',_binary '\0','HoCit6/R1',1100,'Economy',6),(21,'2024-06-05','2024-06-25',_binary '\0',_binary '\0','HoCit6/R2',1700,'Deluxe',6),(22,'2024-06-15','2024-07-05',_binary '\0',_binary '\0','HoCit6/R3',1300,'Economy',6),(23,'2024-05-30','2024-06-20',_binary '\0',_binary '\0','HOCIU10/R1',1100,'Economy',10),(24,'2024-06-05','2024-06-25',_binary '\0',_binary '\0','HOCIU10/R2',1700,'Deluxe',10),(25,'2024-06-15','2024-07-05',_binary '\0',_binary '\0','HOCIU10/R3',1300,'Economy',10),(26,'2024-07-01','2024-07-20',_binary '\0',_binary '\0','HoCit7/R1',2600,'Deluxe',7),(27,'2024-06-15','2024-06-30',_binary '\0',_binary '\0','HoCit7/R2',900,'Economy',7),(28,'2024-08-05','2024-08-20',_binary '',_binary '\0','HoCit7/R3',2900,'Deluxe',7),(29,'2024-07-01','2024-07-20',_binary '\0',_binary '\0','HoCit9/R1',2600,'Deluxe',9),(30,'2024-06-15','2024-06-30',_binary '',_binary '\0','HoCit9/R2',900,'Economy',9),(31,'2024-08-05','2024-08-20',_binary '\0',_binary '\0','HoCit9/R3',2900,'Deluxe',9),(32,'2024-07-01','2024-07-20',_binary '\0',_binary '\0','HoCit1/R1',2600,'Deluxe',1),(33,'2024-06-15','2024-06-30',_binary '',_binary '\0','HoCit1/R2',900,'Economy',1),(34,'2024-08-05','2024-08-20',_binary '\0',_binary '\0','HoCit1/R3',2900,'Deluxe',1),(52,'2024-05-31','2024-06-25',_binary '\0',_binary '\0','LAMAL1/R1',2500,'Deluxe',52);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_booking`
--

DROP TABLE IF EXISTS `room_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_booking` (
  `id` int NOT NULL,
  `booking_code` varchar(255) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `is_completed` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `cliente_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqy9bgarrg69093v9m2m4maubr` (`cliente_id`),
  KEY `FKiwt0ws97ta91ukd4xonewjbxl` (`room_id`),
  CONSTRAINT `FKiwt0ws97ta91ukd4xonewjbxl` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `FKqy9bgarrg69093v9m2m4maubr` FOREIGN KEY (`cliente_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_booking`
--

LOCK TABLES `room_booking` WRITE;
/*!40000 ALTER TABLE `room_booking` DISABLE KEYS */;
INSERT INTO `room_booking` VALUES (1,'HoCit1/R1/BR1','2024-05-31','2024-06-25',_binary '\0',_binary '\0',152,1),(2,'HoCit1/R1/BR1','2024-05-31','2024-06-20',_binary '\0',_binary '\0',202,2),(3,'HoCit1/R2/BR1','2024-06-01','2024-06-20',_binary '\0',_binary '\0',202,3),(4,'HoCit1/R1/BR2','2024-05-31','2024-05-31',_binary '\0',_binary '',203,2),(52,'HoCit1/R1/BR3','2024-06-20','2024-06-25',_binary '\0',_binary '\0',203,2),(102,'HoCit1/R3/BR1','2024-06-11','2024-06-20',_binary '\0',_binary '\0',203,4),(152,'HoCit1/R3/BR2','2024-06-10','2024-06-11',_binary '\0',_binary '\0',203,4),(154,'HoCit1/R3/BR4','2024-06-20','2024-06-30',_binary '\0',_binary '\0',203,4),(202,'HoCit2/R1/BR1','2024-06-20','2024-06-25',_binary '\0',_binary '\0',203,5),(252,'HoCit9/R2/BR1','2024-06-15','2024-06-30',_binary '\0',_binary '\0',203,30),(302,'HoCit1/R2/BR1','2024-06-15','2024-06-30',_binary '\0',_binary '\0',203,33),(303,'HoCit9/R3/BR1','2024-08-05','2024-08-10',_binary '\0',_binary '\0',203,31),(552,'HoCit7/R3/BR2','2024-08-10','2024-08-20',_binary '\0',_binary '\0',203,28);
/*!40000 ALTER TABLE `room_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_booking_seq`
--

DROP TABLE IF EXISTS `room_booking_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_booking_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_booking_seq`
--

LOCK TABLES `room_booking_seq` WRITE;
/*!40000 ALTER TABLE `room_booking_seq` DISABLE KEYS */;
INSERT INTO `room_booking_seq` VALUES (651);
/*!40000 ALTER TABLE `room_booking_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_seq`
--

DROP TABLE IF EXISTS `room_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_seq`
--

LOCK TABLES `room_seq` WRITE;
/*!40000 ALTER TABLE `room_seq` DISABLE KEYS */;
INSERT INTO `room_seq` VALUES (151);
/*!40000 ALTER TABLE `room_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-02  0:35:35
