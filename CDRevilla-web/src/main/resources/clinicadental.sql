-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-03-2016 a las 12:52:54
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinicadental`
--
CREATE DATABASE IF NOT EXISTS `clinicadental` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `clinicadental`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuota`
--

CREATE TABLE `cuota` (
  `idCuota` int(11) UNSIGNED NOT NULL,
  `IdVenta` int(11) UNSIGNED NOT NULL,
  `totalcuotas` int(2) NOT NULL,
  `cuotaspagado` int(2) NOT NULL,
  `fecha` date NOT NULL,
  `plazo` varchar(100) NOT NULL,
  `inicial` decimal(8,2) NOT NULL,
  `importe` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuota`
--

INSERT INTO `cuota` (`idCuota`, `IdVenta`, `totalcuotas`, `cuotaspagado`, `fecha`, `plazo`, `inicial`, `importe`) VALUES
(1, 7, 3, 0, '2016-04-04', 'MENSUAL', '20.00', '40.00'),
(2, 7, 5, 0, '2013-03-03', '3', '40.00', '20.00'),
(3, 13, 3, 0, '2016-03-23', 'MENSUAL', '40.00', '26.67');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermedad`
--

CREATE TABLE `enfermedad` (
  `IdEnfermedad` int(11) UNSIGNED NOT NULL,
  `IdHistoriaClinica` int(11) UNSIGNED NOT NULL,
  `DatosInformante` varchar(250) NOT NULL,
  `MotivoConsulta` varchar(250) NOT NULL,
  `TiempoEnfermedad` varchar(250) NOT NULL,
  `SignosSintomas` varchar(250) NOT NULL,
  `RelatoCronologico` varchar(250) NOT NULL,
  `FuncionesBiologiacas` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exploracionfisica`
--

CREATE TABLE `exploracionfisica` (
  `IdExploracionFisica` int(11) UNSIGNED NOT NULL,
  `IdHistoriaClinica` int(11) UNSIGNED NOT NULL,
  `SignosVitales` int(11) NOT NULL,
  `Pulso` int(11) NOT NULL,
  `Temperatura` int(11) NOT NULL,
  `FC` varchar(250) NOT NULL,
  `FrecuenciaRespiratoria` varchar(250) NOT NULL,
  `ExamenClinicoGeneral` varchar(250) NOT NULL,
  `Odontoestomatologico` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historiaclinica`
--

CREATE TABLE `historiaclinica` (
  `IdHistoriaClinica` int(11) UNSIGNED NOT NULL,
  `IdPaciente` int(11) UNSIGNED NOT NULL,
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
  `AltaPaciente` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `idPaciente` int(11) UNSIGNED NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apelllidos` varchar(100) NOT NULL,
  `DNI` varchar(8) NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `LugarNacimiento` varchar(100) NOT NULL,
  `Celular` varchar(20) NOT NULL,
  `Sexo` varchar(10) NOT NULL,
  `Edad` varchar(3) NOT NULL,
  `Raza` varchar(200) NOT NULL,
  `GradoInstruccion` varchar(200) NOT NULL,
  `Ocupación` varchar(50) NOT NULL,
  `Religion` varchar(50) NOT NULL,
  `EstadoCivil` varchar(50) NOT NULL,
  `Foto` varchar(100) NOT NULL,
  `FechaApertura` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`idPaciente`, `Nombre`, `Apelllidos`, `DNI`, `Direccion`, `LugarNacimiento`, `Celular`, `Sexo`, `Edad`, `Raza`, `GradoInstruccion`, `Ocupación`, `Religion`, `EstadoCivil`, `Foto`, `FechaApertura`) VALUES
(1, 'raul', 'romani flores', '2332', '2334', 'dffd', '33', 'ssd', '12', 'asdd', 'asdas', 'sadsa', 'sdds', 'sdas', 'asdsa', '2016-02-10'),
(2, 'Carlos', 'asdas', 'sasdsa', 'sadas', 'asdsa', 'asdas', 'sdfds', '999', 'sdfsd', 'sdfsdf', 'sdfsdfs', 'sdfsdf', 'sdsdf', 'asds', '2016-02-17');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `IdPersonal` int(11) UNSIGNED NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Especialidad` varchar(100) NOT NULL,
  `DNI` varchar(8) NOT NULL,
  `Foto` varchar(100) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `LugarNacimiento` varchar(200) DEFAULT NULL,
  `Celular` varchar(20) DEFAULT NULL,
  `Sexo` varchar(10) DEFAULT NULL,
  `Edad` varchar(3) DEFAULT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`IdPersonal`, `Nombres`, `Apellidos`, `Especialidad`, `DNI`, `Foto`, `Direccion`, `LugarNacimiento`, `Celular`, `Sexo`, `Edad`, `usuario`, `clave`, `rol`) VALUES
(1, 'Raúl', 'Romaní', 'odontologo', '43562312', NULL, NULL, NULL, NULL, NULL, NULL, 'admin', 'admin', 'administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `idServicio` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`idServicio`, `nombre`, `precio`, `descripcion`) VALUES
(1, 'Profilaxis', '20.00', 'limpieza de dientes'),
(2, 'Curación resina', '60.00', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicioventa`
--

CREATE TABLE `servicioventa` (
  `IdVenta` int(11) UNSIGNED NOT NULL,
  `idServicio` int(11) UNSIGNED NOT NULL,
  `cantidad` int(2) NOT NULL,
  `diente` char(32) NOT NULL,
  `importe` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `servicioventa`
--

INSERT INTO `servicioventa` (`IdVenta`, `idServicio`, `cantidad`, `diente`, `importe`) VALUES
(7, 1, 1, '', '20.00'),
(8, 2, 2, '6, 3', '120.00'),
(13, 2, 2, '4, 6', '120.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `IdVenta` int(11) UNSIGNED NOT NULL,
  `IdPersonal` int(11) UNSIGNED NOT NULL,
  `idPaciente` int(11) UNSIGNED NOT NULL,
  `total` decimal(8,2) NOT NULL,
  `fecha` datetime NOT NULL,
  `formapago` varchar(20) NOT NULL DEFAULT 'CONTADO',
  `comprobante` varchar(20) NOT NULL DEFAULT 'BOLETA'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`IdVenta`, `IdPersonal`, `idPaciente`, `total`, `fecha`, `formapago`, `comprobante`) VALUES
(1, 1, 1, '2323.00', '2014-12-06 12:32:12', 'asdsa', 'asdas'),
(3, 1, 1, '3232.00', '2016-02-02 00:00:00', 'sadasad', 'asdsasad'),
(7, 1, 1, '20.00', '2016-03-22 05:19:32', 'CONTADO', 'BOLETA'),
(8, 1, 1, '120.00', '2016-03-22 05:24:10', 'CONTADO', 'BOLETA'),
(13, 1, 1, '120.00', '2016-03-23 00:34:13', 'CONTADO', 'BOLETA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuota`
--
ALTER TABLE `cuota`
  ADD PRIMARY KEY (`idCuota`),
  ADD KEY `IdVenta` (`IdVenta`);

--
-- Indices de la tabla `enfermedad`
--
ALTER TABLE `enfermedad`
  ADD PRIMARY KEY (`IdEnfermedad`),
  ADD KEY `IdHistoriaClinica` (`IdHistoriaClinica`);

--
-- Indices de la tabla `exploracionfisica`
--
ALTER TABLE `exploracionfisica`
  ADD PRIMARY KEY (`IdExploracionFisica`),
  ADD KEY `IdHistoriaClinica` (`IdHistoriaClinica`);

--
-- Indices de la tabla `historiaclinica`
--
ALTER TABLE `historiaclinica`
  ADD PRIMARY KEY (`IdHistoriaClinica`),
  ADD KEY `IdPaciente` (`IdPaciente`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`idPaciente`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`IdPersonal`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`idServicio`);

--
-- Indices de la tabla `servicioventa`
--
ALTER TABLE `servicioventa`
  ADD PRIMARY KEY (`IdVenta`,`idServicio`),
  ADD KEY `idServicio` (`idServicio`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`IdVenta`),
  ADD KEY `IdPersonal` (`IdPersonal`),
  ADD KEY `idPaciente` (`idPaciente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuota`
--
ALTER TABLE `cuota`
  MODIFY `idCuota` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `enfermedad`
--
ALTER TABLE `enfermedad`
  MODIFY `IdEnfermedad` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `exploracionfisica`
--
ALTER TABLE `exploracionfisica`
  MODIFY `IdExploracionFisica` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `historiaclinica`
--
ALTER TABLE `historiaclinica`
  MODIFY `IdHistoriaClinica` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `idPaciente` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `IdPersonal` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `idServicio` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `servicioventa`
--
ALTER TABLE `servicioventa`
  MODIFY `IdVenta` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `IdVenta` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuota`
--
ALTER TABLE `cuota`
  ADD CONSTRAINT `cuota_ibfk_1` FOREIGN KEY (`IdVenta`) REFERENCES `venta` (`IdVenta`);

--
-- Filtros para la tabla `enfermedad`
--
ALTER TABLE `enfermedad`
  ADD CONSTRAINT `enfermedad_ibfk_1` FOREIGN KEY (`IdHistoriaClinica`) REFERENCES `historiaclinica` (`IdHistoriaClinica`);

--
-- Filtros para la tabla `exploracionfisica`
--
ALTER TABLE `exploracionfisica`
  ADD CONSTRAINT `exploracionfisica_ibfk_1` FOREIGN KEY (`IdHistoriaClinica`) REFERENCES `historiaclinica` (`IdHistoriaClinica`);

--
-- Filtros para la tabla `historiaclinica`
--
ALTER TABLE `historiaclinica`
  ADD CONSTRAINT `historiaclinica_ibfk_1` FOREIGN KEY (`IdPaciente`) REFERENCES `paciente` (`idPaciente`);

--
-- Filtros para la tabla `servicioventa`
--
ALTER TABLE `servicioventa`
  ADD CONSTRAINT `servicioventa_ibfk_1` FOREIGN KEY (`IdVenta`) REFERENCES `venta` (`IdVenta`),
  ADD CONSTRAINT `servicioventa_ibfk_2` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`IdPersonal`) REFERENCES `personal` (`IdPersonal`),
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
