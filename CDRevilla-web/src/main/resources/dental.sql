-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-02-2016 a las 22:54:03
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.5.30

--
-- Base de datos: `Clinica dental`
--
DROP DATABASE IF EXISTS clinicadental;
CREATE DATABASE IF NOT EXISTS `clinicadental` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `clinicadental`;



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `idPaciente` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(200) NOT NULL,
  `Apelllidos` varchar(200) NOT NULL,
  `DNI` varchar(8) NOT NULL,
  `Direccion` varchar(200) NOT NULL,
  `LugarNacimiento` varchar(200) NOT NULL,
  `Celular` varchar(20) NOT NULL,
  `Sexo` varchar(10) NOT NULL,
  `Edad` varchar(3) NOT NULL,
  `Raza` varchar(200) NOT NULL,
  `GradoInstruccion` varchar(200) NOT NULL,
  `Ocupación` varchar(100) NOT NULL,
  `Religion` varchar(200) NOT NULL,
  `EstadoCivil` varchar(200) NOT NULL,
  `Foto` varchar(200) NOT NULL,
  `FechaApertura` date NOT NULL,
  PRIMARY KEY (IdPaciente)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historiaclinica`
--

CREATE TABLE `historiaclinica` (
  `IdHistoriaClinica` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `IdPaciente` int(11) unsigned NOT NULL,
  `FechaAtencion` datetime NOT NULL,
  `IdEnfermedad` int(11) NOT NULL,
  `AntecedentesFamiliares` varchar(250) NOT NULL,
  `AntecedentesPersonales` varchar(250) NOT NULL,
  `IdExploracionFisica` int(11) NOT NULL,
  `DiagnosticoPresuntivo` varchar(250) NOT NULL,
  `DiagnosticoDefinitivo` varchar(250) NOT NULL,
  `PlanTrabajo` varchar(250) NOT NULL,
  `Pronostico` varchar(250) NOT NULL,
  `TratamientoRecomendacion` varchar(300) NOT NULL,
  `AltaPaciente` varchar(300) NOT NULL,
  PRIMARY KEY (IdHistoriaClinica),
  FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermedad`
--

CREATE TABLE `enfermedad` (
  `IdEnfermedad` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `IdHistoriaClinica` int(11) unsigned NOT NULL,
  `DatosInformante` varchar(250) NOT NULL,
  `MotivoConsulta` varchar(250) NOT NULL,
  `TiempoEnfermedad` varchar(250) NOT NULL,
  `SignosSintomas` varchar(250) NOT NULL,
  `RelatoCronologico` varchar(250) NOT NULL,
  `FuncionesBiologiacas` varchar(250) NOT NULL,
  PRIMARY KEY (IdEnfermedad),
  FOREIGN KEY (IdHistoriaClinica) REFERENCES historiaclinica(IdHistoriaClinica)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exploracionfisica`
--

CREATE TABLE `exploracionfisica` (
  `IdExploracionFisica` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `IdHistoriaClinica` int(11) unsigned NOT NULL,
  `SignosVitales` int(11) NOT NULL,
  `Pulso` int(11) NOT NULL,
  `Temperatura` int(11) NOT NULL,
  `FC` varchar(250) NOT NULL,
  `FrecuenciaRespiratoria` varchar(250) NOT NULL,
  `ExamenClinicoGeneral` varchar(250) NOT NULL,
  `Odontoestomatologico` varchar(250) NOT NULL,
  PRIMARY KEY (IdExploracionFisica),
  FOREIGN KEY (IdHistoriaClinica) REFERENCES historiaclinica(IdHistoriaClinica)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;





-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `IdPersonal` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(200) NOT NULL,
  `Apellidos` varchar(200) NOT NULL,
  `Especialidad` varchar(200) NOT NULL,
  `usuario` varchar(200) NOT NULL,
  `clave` varchar(200) NOT NULL,
  PRIMARY KEY (IdPersonal)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `IdVenta` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `IdPersonal` int(11) unsigned NOT NULL,
  `idPaciente` int(11) unsigned NOT NULL,

  `total` decimal(8,2) NOT NULL,
  `fecha` datetime NOT NULL,
  `formapago` varchar(200) DEFAULT 'CONTADO' NOT NULL,
  `comprobante` varchar(200) DEFAULT 'BOLETA' NOT NULL,

  PRIMARY KEY (IdVenta),
  FOREIGN KEY (IdPersonal) REFERENCES personal(IdPersonal),
  FOREIGN KEY (idPaciente) REFERENCES paciente(idPaciente),
  CONSTRAINT chk_venta_formapago
	CHECK (formapago IN ('CONTADO', 'CUOTAS')),
  CONSTRAINT chk_venta_comprobante
	CHECK (comprobante IN ('BOLETA', 'FACTURA', 'RECIBO'))
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicio` (
  `idServicio` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `descripcion` varchar(200),

  PRIMARY KEY (idServicio)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `servicioventa` (
  
  `IdVenta` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idServicio` int(11) unsigned NOT NULL,

  `cantidad` int(2) NOT NULL,
  `importe` decimal(8,2) NOT NULL,

  PRIMARY KEY (IdVenta, idServicio),
  FOREIGN KEY (IdVenta) REFERENCES venta(IdVenta),
  FOREIGN KEY (idServicio) REFERENCES servicio(idServicio)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuota`
--

CREATE TABLE `cuota` (
  
  `idCuota` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `IdVenta` int(11) unsigned NOT NULL, 
  `totalcuotas` int(2) NOT NULL, -- Total de cuotas a pagar
  `cuotaspagado` int(2) NOT NULL, -- Cuotas que se ha pagado
  `fecha` date NOT NULL,
  `plazo` varchar(100) NOT NULL,
  `importe` decimal(8,2) NOT NULL,

  PRIMARY KEY (idCuota),
  CONSTRAINT chk_cuota_plazo
	CHECK (plazo IN ('QUINCENAL', 'MENSUAL')),
  FOREIGN KEY (IdVenta) REFERENCES venta(IdVenta)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;




--
-- Volcado de datos para la tabla `paciente`
--


INSERT INTO `paciente` (`idPaciente`, `Nombre`, `Apelllidos`, `DNI`, `Direccion`, `LugarNacimiento`, `Celular`, `Sexo`, `Edad`, `Raza`, `GradoInstruccion`, `Ocupación`, `Religion`, `EstadoCivil`, `Foto`, `FechaApertura`) VALUES
(1, 'raul', 'romani flores', '2332', '2334', 'dffd', '33', 'ssd', '12', 'asdd', 'asdas', 'sadsa', 'sdds', 'sdas', 'asdsa', '2016-02-10'),
(2, 'Carlos', 'asdas', 'sasdsa', 'sadas', 'asdsa', 'asdas', 'sdfds', '999', 'sdfsd', 'sdfsdf', 'sdfsdfs', 'sdfsdf', 'sdsdf', 'asds', '2016-02-17');



--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`IdPersonal`, `Nombres`, `Apellidos`, `Especialidad`, `usuario`, `clave`) VALUES
(1, 'Julia', 'asd', 'asdas', 'admin', 'admin'),
(2, 'Carlos', 'asd', 'asd', 'sad', 'sad');


--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`idServicio`, `nombre`, `precio`, `descripcion`) VALUES
(1, 'Profilaxis', '20.00', 'limpieza de dientes');


--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`IdVenta`, `IdPersonal`, `idPaciente`, `total`, `fecha`, `formapago`, `comprobante`) VALUES
(1, 1, 1, '2323.00', '2014-12-06 12:32:12', 'asdsa', 'asdas'),
(3, 2, 1, '3232.00', '2016-02-02 00:00:00', 'sadasad', 'asdsasad');