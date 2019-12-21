-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MC
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MC
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MC` DEFAULT CHARACTER SET utf8 ;
USE `MC` ;

-- -----------------------------------------------------
-- Table `MC`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`Administrador` (
  `idAdministrador` INT(11) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idAdministrador`, `email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`Musica` (
  `idMusica` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `duracao` DOUBLE NOT NULL,
  `formato` VARCHAR(5) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `artista` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMusica`),
  UNIQUE INDEX `idMusica_UNIQUE` (`idMusica` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`PlaylistMusica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`PlaylistMusica` (
  `idPlaylist` INT NOT NULL,
  `nomePlaylist` VARCHAR(45) NOT NULL,
  `idUtilizador` INT NOT NULL,
  `idMusica` INT NOT NULL,
  UNIQUE INDEX `idMusica_UNIQUE` (`idMusica` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`UtilizadorRegistado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`UtilizadorRegistado` (
  `idUtilizador` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `idBibliotecaMusica` INT NOT NULL,
  `idBibliotecaVideo` INT NOT NULL,
  PRIMARY KEY (`idUtilizador`),
  UNIQUE INDEX `idUtilizador_UNIQUE` (`idUtilizador` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`Video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`Video` (
  `idVideo` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `duracao` DOUBLE NOT NULL,
  `formato` VARCHAR(5) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `realizador` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVideo`),
  UNIQUE INDEX `idVideo_UNIQUE` (`idVideo` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`ProprietariosVideo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`ProprietariosVideo` (
  `idVideo` INT NOT NULL,
  `idUtilizador` INT NOT NULL,
  PRIMARY KEY (`idVideo`, `idUtilizador`),
  INDEX `fk_ProprietariosVideo_Video1_idx` (`idVideo` ASC) VISIBLE,
  CONSTRAINT `fk_ProprietariosVideo_UtilizadorRegistado1`
    FOREIGN KEY (`idUtilizador`)
    REFERENCES `MC`.`UtilizadorRegistado` (`idUtilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProprietariosVideo_Video1`
    FOREIGN KEY (`idVideo`)
    REFERENCES `MC`.`Video` (`idVideo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`ProprietariosMusica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`ProprietariosMusica` (
  `idMusica` INT NOT NULL,
  `idUtilizador` INT NOT NULL,
  PRIMARY KEY (`idMusica`, `idUtilizador`),
  INDEX `fk_ProprietariosMusica_UtilizadorRegistado1_idx` (`idUtilizador` ASC) VISIBLE,
  CONSTRAINT `fk_ProprietariosMusica_Musica1`
    FOREIGN KEY (`idMusica`)
    REFERENCES `MC`.`Musica` (`idMusica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProprietariosMusica_UtilizadorRegistado1`
    FOREIGN KEY (`idUtilizador`)
    REFERENCES `MC`.`UtilizadorRegistado` (`idUtilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`PlaylistVideo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`PlaylistVideo` (
  `idPlaylist` INT NOT NULL,
  `nomePlaylist` VARCHAR(45) NOT NULL,
  `idUtilizador` INT NOT NULL,
  `idVideo` INT NOT NULL,
  UNIQUE INDEX `idVideo_UNIQUE` (`idVideo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `MC`.`CategoriaVideo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`CategoriaVideo` (
  `idVideo` INT NOT NULL,
  `idUtilizador` INT NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  INDEX `fk_CategoriaVideo_UtilizadorRegistado1_idx` (`idUtilizador` ASC) VISIBLE,
  CONSTRAINT `fk_CategoriaVideo_Video1`
    FOREIGN KEY (`idVideo`)
    REFERENCES `MC`.`Video` (`idVideo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CategoriaVideo_UtilizadorRegistado1`
    FOREIGN KEY (`idUtilizador`)
    REFERENCES `MC`.`UtilizadorRegistado` (`idUtilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MC`.`CategoriaMusica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MC`.`CategoriaMusica` (
  `idMusica` INT NOT NULL AUTO_INCREMENT,
  `idUtilizador` INT NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  INDEX `fk_CategoriaMusica_UtilizadorRegistado1_idx` (`idUtilizador` ASC) VISIBLE,
  CONSTRAINT `fk_CategoriaMusica_Musica1`
    FOREIGN KEY (`idMusica`)
    REFERENCES `MC`.`Musica` (`idMusica`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CategoriaMusica_UtilizadorRegistado1`
    FOREIGN KEY (`idUtilizador`)
    REFERENCES `MC`.`UtilizadorRegistado` (`idUtilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
