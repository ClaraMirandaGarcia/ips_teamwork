DROP TABLE atleta;

CREATE TABLE atleta (
  `idAtleta` INT(11) NOT NULL,
  `dni` VARCHAR(45) NULL DEFAULT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `fechaNacimiento` DATE NULL DEFAULT NULL,
  `sexo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idAtleta`));
  
DROP TABLE competicion;

CREATE TABLE competicion (
  `idCompeticion` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `tipo` VARCHAR(45) NULL DEFAULT NULL,
  `fechaInicio` DATE NULL DEFAULT NULL,
  `fechaFinal` DATE NULL DEFAULT NULL,
  `cuota` DOUBLE NULL DEFAULT NULL,
  `distancia` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idCompeticion`));