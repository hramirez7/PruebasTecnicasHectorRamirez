

CREATE SCHEMA `db_hectoramirez` ;

CREATE TABLE `clientes` (
  `cliente_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`),
  UNIQUE KEY `UKmvqpaay4xno9pnlalro0awadi` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `cuenta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente_id` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `numero_cuenta` varchar(255) DEFAULT NULL,
  `saldo_inicial` double DEFAULT NULL,
  `tipo_cuenta` enum('AHORROS','CORRIENTE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpj7ncg765kt4klndu25bwbwe4` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `movimiento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `saldo_inicial` double DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `cuenta_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ea11fe7p3xa1kwwmdgi9f2fi` (`cuenta_id`),
  CONSTRAINT `FK4ea11fe7p3xa1kwwmdgi9f2fi` FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;



