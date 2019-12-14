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
  PRIMARY KEY (`idMusica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Musica`
--

LOCK TABLES `Musica` WRITE;
/*!40000 ALTER TABLE `Musica` DISABLE KEYS */;
INSERT INTO `Musica` VALUES (-2062804315,'Hotline Bling',232295.671875,'mp3','R&B','Drake'),(-1323395469,'You Need to Calm Down',177407.046875,'mp3','Pop','Taylor Swift'),(-1021089120,'Centuries',228246.296875,'mp3','Rock','Fall Out Boy'),(-692283955,'Teeth ',205073.421875,'mp3','Punk','5 Seconds of Summer ');
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
  `idMusica` int(11) NOT NULL,
  PRIMARY KEY (`idPlaylist`),
  KEY `fk_Playlist_Utilizador_idx` (`idUtilizador`),
  KEY `fk_Playlist_Conteudo_idx` (`idMusica`),
  CONSTRAINT `fk_Playlist_Conteudo` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`),
  CONSTRAINT `fk_Playlist_Utilizador` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaylistMusica`
--

LOCK TABLES `PlaylistMusica` WRITE;
/*!40000 ALTER TABLE `PlaylistMusica` DISABLE KEYS */;
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
  `idVideo` int(11) NOT NULL,
  PRIMARY KEY (`idPlaylist`),
  KEY `fk_Playlist_Utilizador_idx` (`idUtilizador`),
  KEY `fk_Playlist_Conteudo0_idx` (`idVideo`),
  CONSTRAINT `fk_Playlist_Conteudo0` FOREIGN KEY (`idVideo`) REFERENCES `video` (`idVideo`),
  CONSTRAINT `fk_Playlist_Utilizador0` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PlaylistVideo`
--

LOCK TABLES `PlaylistVideo` WRITE;
/*!40000 ALTER TABLE `PlaylistVideo` DISABLE KEYS */;
/*!40000 ALTER TABLE `PlaylistVideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProprietariosMusica`
--

DROP TABLE IF EXISTS `ProprietariosMusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProprietariosMusica` (
  `idUtilizador` int(11) NOT NULL,
  `idMusica` int(11) NOT NULL,
  KEY `fk_Proprietarios_idUtilizador_idx` (`idUtilizador`),
  KEY `fk_Proprietarios_idConteudo_idx` (`idMusica`),
  CONSTRAINT `fk_Proprietarios_idConteudo0` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`),
  CONSTRAINT `fk_Proprietarios_idUtilizador0` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProprietariosMusica`
--

LOCK TABLES `ProprietariosMusica` WRITE;
/*!40000 ALTER TABLE `ProprietariosMusica` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProprietariosMusica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProprietariosVideo`
--

DROP TABLE IF EXISTS `ProprietariosVideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProprietariosVideo` (
  `idUtilizador` int(11) NOT NULL,
  `idVideo` int(11) NOT NULL,
  KEY `fk_Proprietarios_idUtilizador_idx` (`idUtilizador`),
  KEY `fk_Proprietarios_idConteudo_idx` (`idVideo`),
  CONSTRAINT `fk_Proprietarios_idConteudo` FOREIGN KEY (`idVideo`) REFERENCES `video` (`idVideo`),
  CONSTRAINT `fk_Proprietarios_idUtilizador` FOREIGN KEY (`idUtilizador`) REFERENCES `utilizadorregistado` (`idUtilizador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProprietariosVideo`
--

LOCK TABLES `ProprietariosVideo` WRITE;
/*!40000 ALTER TABLE `ProprietariosVideo` DISABLE KEYS */;
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
  PRIMARY KEY (`idUtilizador`,`email`)
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
  PRIMARY KEY (`idVideo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Video`
--

LOCK TABLES `Video` WRITE;
/*!40000 ALTER TABLE `Video` DISABLE KEYS */;
INSERT INTO `Video` VALUES (815800994,'Finally // Beautiful Stranger',238.42,'mp4','Pop','Halsey'),(937131542,'Graveyard',235.45,'mp4','Pop','Halsey'),(2143103325,'Radioactive',261.06,'mp4','Pop','Imagine Dragons');
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

-- Dump completed on 2019-12-10 17:46:04
