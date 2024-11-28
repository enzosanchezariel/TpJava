-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: tp_java
-- ------------------------------------------------------
-- Server version	8.0.40

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
  CONSTRAINT `answers_users_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (8,7,1),(8,12,3),(11,7,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenges`
--

LOCK TABLES `challenges` WRITE;
/*!40000 ALTER TABLE `challenges` DISABLE KEYS */;
INSERT INTO `challenges` VALUES (9,'Cientifico Principiante',3,11),(10,'Explorador Cientifico',10,11),(11,'Investigador Avanzado',15,11),(13,'Aprendiz de Matematicas',3,12),(14,'Calculador Rapido',10,12),(15,'Maestro Matematico',15,12),(17,'Historiador novato',3,13),(18,'Estudioso de la Historia',10,13),(19,'Historiador Experto',15,13),(21,'Explorador Novato',3,14),(22,'Aventurero',10,14),(23,'Explorador del Mundo',15,14),(25,'Deportista Amateur',3,15),(26,'Atleta Conocedor',10,15),(27,'Experto en Deportes',15,15),(28,'Novato Tecnologico',3,17),(29,'Aficionado a la Tecnologia',5,17),(30,'Experto en Tecnologia',10,17);
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
INSERT INTO `options` VALUES (1,'3',13),(1,'0',14),(1,'2',15),(1,'4',16),(1,'3',17),(1,'Un tipo de telefono',18),(1,'Albert Einstein',19),(1,'Una tecnologia para transmitir datos inalambricamente',20),(1,'Mueve el cursor en la pantalla',21),(1,'Madrid',22),(1,'10',23),(1,'Futbol',24),(1,'10',25),(1,'Super Nintendo',26),(1,'Luigi',27),(1,'1995',28),(1,'George Washington',29),(1,'1492',30),(1,'Hernan Cortes',31),(1,'En la India',32),(2,'4',13),(2,'1',14),(2,'3',15),(2,'2',16),(2,'5',17),(2,'Un dispositivo que puede realizar calculos y procesar informacion',18),(2,'Nikola Tesla',19),(2,'Wireless Fidelity',20),(2,'Se usa para escribir en la computadora',21),(2,'Paris',22),(2,'11',23),(2,'Basketball',24),(2,'20',25),(2,'Nintendo 64',26),(2,'Link',27),(2,'1996',28),(2,'Abraham Lincoln',29),(2,'1776',30),(2,'Cristobal Colon',31),(2,'En el norte de Africa, cerca del rio Nilo',32),(3,'5',13),(3,'3',14),(3,'4',15),(3,'6',16),(3,'6',17),(3,'Una fruta',18),(3,'Thomas Edison',19),(3,'Todas son correctas',20),(3,'Conecta la computadora a internet',21),(3,'Berlin',22),(3,'12',23),(3,'Tenis',24),(3,'30',25),(3,'Nintendo Entertainment System (NES)',26),(3,'Mario',27),(3,'1997',28),(3,'Thomas Jefferson',29),(3,'1789',30),(3,'Marco Polo',31),(3,'En Mesopotamia',32),(4,'6',13),(4,'4',14),(4,'5',15),(4,'e',16),(4,'1',17),(4,'Un elemento de cocina',18),(4,'Alexander Graham Bell',19),(4,'Todas son incorrectas',20),(4,'Controla impresoras',21),(4,'Roma',22),(4,'9',23),(4,'Volleyball',24),(4,'No hay un limite fijo, depende del partido',25),(4,'Nintendo Switch',26),(4,'Samus',27),(4,'1998',28),(4,'John Adams',29),(4,'1812',30),(4,'Leonardo da Vinci',31),(4,'En las Islas Canarias',32);
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (13,'2 + 2',2,7),(14,'3 x 1',3,7),(15,'5 - 1',3,7),(16,'Cual es el siguiente numero en la secuencia: 1, 2, 3, ___?',1,7),(17,'3 / 3',4,7),(18,'Que es una computadora?',2,8),(19,'Quien invento el telefono?',4,8),(20,'Que significa \"Wi-Fi\"?',3,8),(21,'Que hace un \"mouse\" en una computadora?',1,8),(22,'Cual es la capital de Francia?',2,9),(23,'Cuantos jugadores hay en un equipo de futbol?',2,10),(24,'En que deporte se usa una raqueta?',3,10),(25,'Cuantos puntos se necesita para ganar un partido de baloncesto?',4,10),(26,'Que consola fue la primera de Nintendo?',3,11),(27,'Quien es el personaje principal en la mayoria de los juegos de Nintendo?',3,11),(28,'En que año se lanzo la consola Nintendo 64?',2,11),(29,'Quien fue el primer presidente de los Estados Unidos?',1,12),(30,'En que año ocurrio la Revolucion Francesa?',3,12),(31,'Quien descubrio America en 1492?',2,12),(32,'Donde nacio la civilizacion del antiguo Egipto?',2,12);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (7,'-10 IQ Math Quiz','00:05:00',6,12,0),(8,'Tecno-Novato','00:12:00',6,17,0),(9,'Geografia','00:05:00',6,14,0),(10,'Mi Quiz de Deportes','00:10:00',7,15,0),(11,'Nintendo Quiz','00:10:00',8,17,0),(12,'Formulario de Historia 1','00:10:00',9,13,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranks`
--

LOCK TABLES `ranks` WRITE;
/*!40000 ALTER TABLE `ranks` DISABLE KEYS */;
INSERT INTO `ranks` VALUES (4,'Platino',3),(5,'Oro',10),(6,'Rubi',15),(7,'Esmeralda',20),(8,'Diamante',25),(10,'Novato',0);
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
  `code` varchar(36) NOT NULL,
  `max_amount_participants` int NOT NULL,
  `init_date` date NOT NULL,
  `end_date` date NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `admin` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `rooms_users_FK` (`admin`),
  CONSTRAINT `rooms_users_FK` FOREIGN KEY (`admin`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (6,'Sabiduria Epica','123123',10,'2024-11-07','2024-11-27',0,8),(7,'Conocimiento Supremo','asdasd',10,'2024-11-07','2025-01-16',0,8),(8,'Nintendo Quizzes Room','yahoo123',25,'2024-11-07','2025-05-22',0,9),(9,'Sala Gutierrez','abcdef',20,'2024-11-07','2025-05-27',0,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (11,'Ciencia',0),(12,'Matematica',0),(13,'Historia',0),(14,'Geografia',0),(15,'Deportes',0),(16,'Literatura y Arte',1),(17,'Tecnologia',0),(18,'Ã¡',1);
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
  `role` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'Enzo','Sanchez','enzo.sanchez.ariel@gmail.com','123123',1),(9,'Mario','Mario','itsame.mario@yahoo.com','letsago',0),(10,'Maria','Gutierrez','maria.gutierrez@gmail.com','mariagutierrez',0),(11,'Mark','Zuckerberg','area51.boy@meta.com','dadada',0),(12,'Lautaro','Duarte','lautaroduarte@gmail.com','asdasd',1);
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
  CONSTRAINT `fk_users_rooms_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_rooms`
--

LOCK TABLES `users_rooms` WRITE;
/*!40000 ALTER TABLE `users_rooms` DISABLE KEYS */;
INSERT INTO `users_rooms` VALUES (8,6),(9,6),(10,6),(11,6),(8,7),(10,7),(9,8),(10,8),(11,8),(8,9),(10,9);
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

-- Dump completed on 2024-11-26 16:08:34
