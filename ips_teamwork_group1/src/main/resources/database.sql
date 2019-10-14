drop table Competicion;
drop table Atleta;
drop table Inscripcion;

CREATE TABLE Competicion (
  `idCompeticion` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `fechaInicioInscripcion` DATE NULL,
  `fechaFinalInscripcion` DATE NULL,
  `fechaCompeticion` DATE NULL,
  `cuota` DOUBLE NULL,
  `distancia` DOUBLE NULL,
  `numeroPlazas` INT NULL,
  PRIMARY KEY (`idCompeticion`));

CREATE TABLE Atleta (
  `idAtleta` INT NOT NULL,
  `dni` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `fechaNacimiento` DATE NULL,
  `sexo` VARCHAR(45) NULL,
  PRIMARY KEY (`idAtleta`));

CREATE TABLE Inscripcion (
  `idAtleta` INT NOT NULL,
  `idCompeticion` INT NOT NULL,
  `fechaInscripcion` DATE NULL,
  `categoria` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `cantidadPagada` DOUBLE NULL,
  `tiempo` TIME NULL,
  `posicion` INT NULL,
  PRIMARY KEY (`idAtleta`, `idCompeticion`),
  CONSTRAINT `idAtleta`
    FOREIGN KEY (`idAtleta`)
    REFERENCES Atleta (`idAtleta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idCompeticion`
    FOREIGN KEY (`idCompeticion`)
    REFERENCES Competicion (`idCompeticion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);