-- phpMyAdmin SQL Dump
-- version 3.3.2deb1ubuntu1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 02, 2012 at 09:43 PM
-- Server version: 5.1.62
-- PHP Version: 5.3.2-1ubuntu4.15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Universitate`
--

-- --------------------------------------------------------

--
-- Table structure for table `facultate`
--

CREATE TABLE IF NOT EXISTS `facultate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nume` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `facultate`
--

INSERT INTO `facultate` (`ID`, `Nume`) VALUES
(1, 'Facultatea de Desen'),
(2, 'Facultatea de Educatie Fizica si Sport'),
(3, 'Facultatea de Inginerie'),
(4, 'Facultatea de Inginerie Alimentara Turism si Protectia Mediului'),
(5, 'Facultatea de Stiinte ale Educatiei Psihologie si Asistenta Sociala'),
(6, 'Facultatea de Stiinte economice'),
(7, 'Facultatea de Stiinte Exacte'),
(8, 'Facultatea de Stiinte Umaniste si Sociale'),
(9, 'Facultatea de Teologie');

-- --------------------------------------------------------

--
-- Table structure for table `orar_sectie1_sem1`
--

CREATE TABLE IF NOT EXISTS `orar_sectie1_sem1` (
  `ora` varchar(7) NOT NULL DEFAULT ' - ',
  `luni` varchar(100) NOT NULL DEFAULT ' ',
  `marti` varchar(100) NOT NULL DEFAULT ' ',
  `miercuri` varchar(100) NOT NULL DEFAULT ' ',
  `joi` varchar(100) NOT NULL DEFAULT ' ',
  `vineri` varchar(100) NOT NULL DEFAULT ' ',
  `sambata` varchar(100) NOT NULL DEFAULT ' ',
  `duminica` varchar(100) NOT NULL DEFAULT ' '
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orar_sectie1_sem1`
--

INSERT INTO `orar_sectie1_sem1` (`ora`, `luni`, `marti`, `miercuri`, `joi`, `vineri`, `sambata`, `duminica`) VALUES
('7 - 8', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('8 - 9', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('9 - 10', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('10 - 11', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('11 - 12', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('12 - 13', ' ', ' ', ' ora de miercuri', ' ', ' ', ' ', ' '),
('13 - 14', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('14 - 15', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('15 - 16', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('16 - 17', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('17 - 18', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('18 - 19', ' ', ' ', ' ', ' ', ' ', ' ', ' '),
('19 - 20', ' ', ' ', ' ', ' ', ' ', ' ', ' ');

-- --------------------------------------------------------

--
-- Table structure for table `sectie`
--

CREATE TABLE IF NOT EXISTS `sectie` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Facultate` int(11) NOT NULL COMMENT 'Id-ul Facultății de care aparține secția',
  `Nume` varchar(100) CHARACTER SET utf8 COLLATE utf8_romanian_ci NOT NULL COMMENT 'Numele secției facultății',
  `Forma_de_invatamant` varchar(50) CHARACTER SET utf8 COLLATE utf8_romanian_ci NOT NULL,
  `Numar_ani` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=43 ;

--
-- Dumping data for table `sectie`
--

INSERT INTO `sectie` (`ID`, `Facultate`, `Nume`, `Forma_de_invatamant`, `Numar_ani`) VALUES
(1, 1, 'Design', 'zi', 3),
(2, 1, 'Moda, dezign vestimentar', 'zi', 3),
(3, 2, 'Educație fizică și sport', 'zi', 3),
(4, 3, 'Tehnologia construcțiilor de mașini', 'zi', 4),
(5, 3, 'Ingineria sudarii', 'zi', 4),
(6, 3, 'Tehnologia si designul produselor textile', 'zi', 4),
(7, 3, 'Tehnologia tricotajelor si confectiilor', 'zi', 4),
(8, 3, 'Vehicul pentru transport feroviar', 'zi', 4),
(9, 3, 'Autovehicule rutiere', 'zi', 4),
(10, 3, 'Automatica si informatica aplicata', 'zi', 4),
(11, 3, 'Inginerie economica industriala', 'zi', 4),
(12, 4, 'Ingineria produselor alimentare', 'zi', 4),
(13, 4, 'Controlul si expertiza produselor alimentare', 'zi', 4),
(14, 4, 'Tehnologie chimică textilă', 'zi', 4),
(15, 4, 'Biotehnologii pentru industria alimentară', 'zi', 4),
(16, 4, 'Inginerie și management în alimentație publică și agroturism', 'zi', 4),
(17, 4, 'Ingineria sistemelor biotehnice și ecologice', 'zi', 4),
(18, 5, 'Psihopedagogie specială', 'zi', 3),
(19, 5, 'Pedagogia învățământului primar și preșcolar', 'zi', 3),
(20, 5, 'Asistență socială', 'zi', 3),
(21, 5, 'Psihologie', 'zi', 3),
(22, 6, 'Finanțe și bănci', 'zi', 3),
(23, 6, 'Finanțe și bănci', 'id', 3),
(24, 6, 'Contabilitate și informatică de gestiune', 'zi', 3),
(25, 6, 'Contabilitate și informatică de gestiune', 'id', 3),
(26, 6, 'Economia comerțului, turismului și serviciilor', 'zi', 3),
(27, 6, 'Economia comerțului, turismului și serviciilor', 'id', 3),
(28, 6, 'Administrarea afacerilor', 'zi', 3),
(29, 6, 'Management', 'zi', 3),
(30, 6, 'Marketing', 'zi', 3),
(31, 6, 'Informatică economică', 'zi', 3),
(32, 7, 'Informatică', 'zi', 3),
(33, 7, 'Informatică', 'id', 3),
(34, 7, 'Matematică Informatică', 'zi', 3),
(35, 8, 'Limba și literartura română - Limba și literatura engleză', 'zi', 3),
(36, 8, 'Administrație publică', 'zi', 3),
(37, 8, 'Poliție locală', 'zi', 3),
(38, 8, 'Jurnalism', 'zi', 3),
(39, 8, 'Teologie penticostală didactică', 'zi', 3),
(40, 9, 'Teologie Ortodoxă Pastorală', 'zi', 4),
(41, 9, 'Teologie Ortodoxă Didactică', 'zi', 4),
(42, 9, 'Teologie Ortodoxă Asistență socială', 'zi', 4);
