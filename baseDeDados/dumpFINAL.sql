-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: MC
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `Administrador`
--

DROP TABLE IF EXISTS `Administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Administrador` (
  `idAdministrador` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`idAdministrador`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
INSERT INTO `Administrador` VALUES (1110,'admin@gmail.com','Admin','password');
/*!40000 ALTER TABLE `Administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoriaMusica`
--

DROP TABLE IF EXISTS `CategoriaMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategoriaMusica` (
  `idMusica` int(11) NOT NULL AUTO_INCREMENT,
  `idUtilizador` int(11) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  KEY `fk_CategoriaMusica_UtilizadorRegistado1_idx` (`idUtilizador`),
  KEY `fk_CategoriaMusica_Musica1` (`idMusica`),
  CONSTRAINT `fk_CategoriaMusica_Musica1` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`),
  CONSTRAINT `fk_CategoriaMusica_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB AUTO_INCREMENT=2007269453 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoriaMusica`
--

LOCK TABLES `CategoriaMusica` WRITE;
/*!40000 ALTER TABLE `CategoriaMusica` DISABLE KEYS */;
INSERT INTO `CategoriaMusica` VALUES (2007269452,2220,'Jazz'),(747375718,2220,'Rock');
/*!40000 ALTER TABLE `CategoriaMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CategoriaVideo`
--

DROP TABLE IF EXISTS `CategoriaVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CategoriaVideo` (
  `idVideo` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  KEY `fk_CategoriaVideo_UtilizadorRegistado1_idx` (`idUtilizador`),
  KEY `fk_CategoriaVideo_Video1` (`idVideo`),
  CONSTRAINT `fk_CategoriaVideo_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`),
  CONSTRAINT `fk_CategoriaVideo_Video1` FOREIGN KEY (`idVideo`) REFERENCES `video` (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CategoriaVideo`
--

LOCK TABLES `CategoriaVideo` WRITE;
/*!40000 ALTER TABLE `CategoriaVideo` DISABLE KEYS */;
INSERT INTO `CategoriaVideo` VALUES (-386592128,2220,'Pop'),(305230393,2222,'Rock');
/*!40000 ALTER TABLE `CategoriaVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Musica`
--

DROP TABLE IF EXISTS `Musica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Musica` (
  `idMusica` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `duracao` double NOT NULL,
  `formato` varchar(5) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `artista` varchar(45) NOT NULL,
  PRIMARY KEY (`idMusica`),
  UNIQUE KEY `idMusica_UNIQUE` (`idMusica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Musica`
--

LOCK TABLES `Musica` WRITE;
/*!40000 ALTER TABLE `Musica` DISABLE KEYS */;
INSERT INTO `Musica` VALUES (-2093751464,'Malibu',203166.296875,'mp3','HipHop ','Profjam'),(-1241600026,'Teeth ',205073.421875,'mp3','Pop Rock','5 Seconds of Summer '),(442219300,'Take Me Home',198333.171875,'mp3','Country','John-Denver'),(747375718,'You Need To Calm Down',210408,'mp3','Pop','Taylor Swift'),(2007269452,'Acid Jazz',365219.65625,'mp3','Acid Jazz','Herr Krank');
/*!40000 ALTER TABLE `Musica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlaylistMusica`
--

DROP TABLE IF EXISTS `PlaylistMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PlaylistMusica` (
  `idPlaylist` int(11) NOT NULL,
  `nomePlaylist` varchar(45) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `idMusica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaylistMusica`
--

LOCK TABLES `PlaylistMusica` WRITE;
/*!40000 ALTER TABLE `PlaylistMusica` DISABLE KEYS */;
INSERT INTO `PlaylistMusica` VALUES (302,'Biblioteca Pessoal',2222,2007269452),(301,'Biblioteca Pessoal',2221,2007269452),(301,'Biblioteca Pessoal',2221,747375718),(301,'Biblioteca Pessoal',2221,442219300),(300,'Biblioteca Pessoal',2220,2007269452),(300,'Biblioteca Pessoal',2220,-2093751464),(300,'Biblioteca Pessoal',2220,-1241600026);
/*!40000 ALTER TABLE `PlaylistMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PlaylistVideo`
--

DROP TABLE IF EXISTS `PlaylistVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PlaylistVideo` (
  `idPlaylist` int(11) NOT NULL,
  `nomePlaylist` varchar(45) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  `idVideo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaylistVideo`
--

LOCK TABLES `PlaylistVideo` WRITE;
/*!40000 ALTER TABLE `PlaylistVideo` DISABLE KEYS */;
INSERT INTO `PlaylistVideo` VALUES (401,'Biblioteca Pessoal',2221,-386592128),(401,'Biblioteca Pessoal',2221,-500253273),(400,'Biblioteca Pessoal',2220,-386592128),(400,'Biblioteca Pessoal',2220,-1066834681),(400,'Biblioteca Pessoal',2220,916624268),(400,'Biblioteca Pessoal',2220,305230393),(402,'Biblioteca Pessoal',2222,-386592128),(402,'Biblioteca Pessoal',2222,-500253273),(402,'Biblioteca Pessoal',2222,305230393);
/*!40000 ALTER TABLE `PlaylistVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProprietariosMusica`
--

DROP TABLE IF EXISTS `ProprietariosMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProprietariosMusica` (
  `idMusica` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  PRIMARY KEY (`idMusica`,`idUtilizador`),
  KEY `fk_ProprietariosMusica_UtilizadorRegistado1_idx` (`idUtilizador`),
  CONSTRAINT `fk_ProprietariosMusica_Musica1` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`),
  CONSTRAINT `fk_ProprietariosMusica_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProprietariosMusica`
--

LOCK TABLES `ProprietariosMusica` WRITE;
/*!40000 ALTER TABLE `ProprietariosMusica` DISABLE KEYS */;
INSERT INTO `ProprietariosMusica` VALUES (-2093751464,2220),(-1241600026,2220),(2007269452,2220),(442219300,2221),(747375718,2221),(2007269452,2221),(2007269452,2222);
/*!40000 ALTER TABLE `ProprietariosMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProprietariosVideo`
--

DROP TABLE IF EXISTS `ProprietariosVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProprietariosVideo` (
  `idVideo` int(11) NOT NULL,
  `idUtilizador` int(11) NOT NULL,
  PRIMARY KEY (`idVideo`,`idUtilizador`),
  KEY `fk_ProprietariosVideo_Video1_idx` (`idVideo`),
  KEY `fk_ProprietariosVideo_UtilizadorRegistado1` (`idUtilizador`),
  CONSTRAINT `fk_ProprietariosVideo_UtilizadorRegistado1` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`),
  CONSTRAINT `fk_ProprietariosVideo_Video1` FOREIGN KEY (`idVideo`) REFERENCES `video` (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProprietariosVideo`
--

LOCK TABLES `ProprietariosVideo` WRITE;
/*!40000 ALTER TABLE `ProprietariosVideo` DISABLE KEYS */;
INSERT INTO `ProprietariosVideo` VALUES (-1066834681,2220),(-500253273,2221),(-500253273,2222),(-386592128,2220),(-386592128,2221),(-386592128,2222),(305230393,2220),(305230393,2222),(916624268,2220);
/*!40000 ALTER TABLE `ProprietariosVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UtilizadorRegistado`
--

DROP TABLE IF EXISTS `UtilizadorRegistado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UtilizadorRegistado` (
  `idUtilizador` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `password` varchar(10) NOT NULL,
  `idBibliotecaMusica` int(11) NOT NULL,
  `idBibliotecaVideo` int(11) NOT NULL,
  PRIMARY KEY (`idUtilizador`),
  UNIQUE KEY `idUtilizador_UNIQUE` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UtilizadorRegistado`
--

LOCK TABLES `UtilizadorRegistado` WRITE;
/*!40000 ALTER TABLE `UtilizadorRegistado` DISABLE KEYS */;
INSERT INTO `UtilizadorRegistado` VALUES (2220,'maria@gmail.com','Maria','password',300,400),(2221,'hugo@gmail.com','Hugo','password',301,401),(2222,'susana@gmail.com','Susana','password',302,402);
/*!40000 ALTER TABLE `UtilizadorRegistado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Video`
--

DROP TABLE IF EXISTS `Video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Video` (
  `idVideo` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `duracao` double NOT NULL,
  `formato` varchar(5) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `realizador` varchar(45) NOT NULL,
  PRIMARY KEY (`idVideo`),
  UNIQUE KEY `idVideo_UNIQUE` (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Video`
--

LOCK TABLES `Video` WRITE;
/*!40000 ALTER TABLE `Video` DISABLE KEYS */;
INSERT INTO `Video` VALUES (-1066834681,'Blue Jeans',260.9,'mp4','Alternative','Lana Del Rey'),(-500253273,'Radioactive',261.06,'mp4','Rock','Imagine Dragons'),(-386592128,'Don\'t Start Now',214.58,'mp4','Disco','Dua Lipa'),(305230393,'My Songs Know What You Did In The Dark',187.73,'mp4','Punk Rock','Fall Out Boy'),(916624268,'Graveyard',235.45,'mp4','Pop','Halsey');
/*!40000 ALTER TABLE `Video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-17  0:32:03
