CREATE DATABASE  IF NOT EXISTS `tp_java` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tp_java`;
-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: tp_java
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `user_id` int NOT NULL,
  `quizz_id` int NOT NULL,
  `amountRight` int NOT NULL,
  PRIMARY KEY (`user_id`,`quizz_id`),
  KEY `answers_quizzes_FK` (`quizz_id`),
  CONSTRAINT `answers_quizzes_FK` FOREIGN KEY (`quizz_id`) REFERENCES `quizzes` (`id`),
  CONSTRAINT `answers_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenges`
--

DROP TABLE IF EXISTS `challenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenges` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `amount_questions` int NOT NULL,
  `topic` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `challenges_topics_FK` (`topic`),
  CONSTRAINT `challenges_topics_FK` FOREIGN KEY (`topic`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenges`
--

LOCK TABLES `challenges` WRITE;
/*!40000 ALTER TABLE `challenges` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `number` int NOT NULL,
  `option_text` text NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`number`,`question_id`),
  KEY `options_questions_FK` (`question_id`),
  CONSTRAINT `options_questions_FK` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'360',1),(1,'1 MB',2),(1,'Depende',3),(1,'Bananas',4),(1,'1 vez',5),(1,'Al menos 10',6),(1,'2023',7),(1,'10 de mayo de 2016',8),(1,'2003',9),(2,'120',1),(2,'2 MB',2),(2,'12.000.000.000 píxeles',3),(2,'Luz solar',4),(2,'0 veces',5),(2,'10',6),(2,'2019',7),(2,'9 de abril de 2012',8),(2,'2004',9),(3,'240',1),(3,'2,5 MB',2),(3,'12.000.000 píxeles',3),(3,'Piedras del infinito',4),(3,'Menos de 10',6),(3,'2020',7),(3,'11 de septiembre de 2001',8),(3,'2005',9),(4,'400',1),(4,'2021',7);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_text` text NOT NULL,
  `correct_answer` int NOT NULL,
  `quiz_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_questions_1_idx` (`quiz_id`),
  CONSTRAINT `fk_questions_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'Una publicación de Facebook tiene 120 \"me gusta\", y cada \"me gusta\" hace que en promedio 3 personas vean la publicación. ¿Cuántas personas han visto la publicación?',1,1),(2,'Un programador quiere optimizar la carga de imágenes en Instagram reduciendo su tamaño. Si una imagen de 4 MB se reduce al 50% de su tamaño original, ¿cuánto espacio ocupa la imagen después de la optimización?',2,1),(3,'Una cámara de un teléfono móvil utiliza un sensor que tiene una resolución de 12 megapíxeles. ¿Cuántos píxeles hay en total en la imagen?',3,2),(4,'¿Cuál es la principal fuente de energía utilizada en Meta?',1,2),(5,'¿Cuántas apariciones tiene Ricardo Darín en Breaking Bad?',2,4),(6,'¿En cuántas peliculas participa Ricardo Darín?',1,4),(7,'¿En qué año cambió de nombre Facebook Inc a Meta?',4,3),(8,'¿Cuándo compró Meta (anteriormente Facebook Inc) Instagram?',2,3),(9,'¿En qué año se fundó Meta (anteriormente Facebook inc)?',2,3);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `max_duration` time NOT NULL,
  `room_id` int NOT NULL,
  `topic_id` int NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `quizzes_rooms_FK` (`room_id`),
  KEY `quizzes_topics_FK` (`topic_id`),
  CONSTRAINT `quizzes_rooms_FK` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `quizzes_topics_FK` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (1,'Meta matemática','01:00:00',1,1,0),(2,'Meta ciencia','02:00:00',1,4,0),(3,'Meta historia','00:10:00',1,2,0),(4,'Peliculas Darín','00:10:00',2,3,0);
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranks`
--

DROP TABLE IF EXISTS `ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ranks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `amount_challenges` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranks`
--

LOCK TABLES `ranks` WRITE;
/*!40000 ALTER TABLE `ranks` DISABLE KEYS */;
/*!40000 ALTER TABLE `ranks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` int NOT NULL,
  `max_amount_participants` int NOT NULL,
  `init_date` date NOT NULL,
  `end_date` date NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `admin` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `rooms_users_FK` (`admin`),
  CONSTRAINT `rooms_users_FK` FOREIGN KEY (`admin`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Meta minds',123123,20,'2024-10-07','2024-11-07',0,2),(2,'Cine Argentino',232425,25,'2024-11-01','2024-12-01',0,1);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (1,'Matemática',0),(2,'Historia',0),(3,'Entretenimiento',0),(4,'Ciencia',0);
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ricardo','Darín','darin.carp@gmail.com','asdasd',0),(2,'Mark','Zuckerberg','area51.boy@meta.com','dadada',0),(3,'Gustavo','Cerati','gustavo.stereo@outlook.com','americanBlind',1),(4,'Diego','Maradona','d10.maradona@hotmail.com','footballHigh',1),(5,'Mario','Bros','itsame.mario@yahoo.com','solong',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_rooms`
--

DROP TABLE IF EXISTS `users_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_rooms` (
  `user_id` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`room_id`),
  KEY `fk_users_rooms_1_idx` (`room_id`),
  CONSTRAINT `fk_users_rooms_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `fk_users_rooms_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_rooms`
--

LOCK TABLES `users_rooms` WRITE;
/*!40000 ALTER TABLE `users_rooms` DISABLE KEYS */;
INSERT INTO `users_rooms` VALUES (1,1),(2,1),(1,2);
/*!40000 ALTER TABLE `users_rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-30 22:05:35
