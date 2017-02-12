-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 25, 2016 at 02:51 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pia`
--

-- --------------------------------------------------------

--
-- Table structure for table `discipline`
--

CREATE TABLE `discipline` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `id_sport` int(11) NOT NULL,
  `min` int(11) NOT NULL,
  `max` int(11) NOT NULL,
  `tim` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discipline`
--

INSERT INTO `discipline` (`id`, `name`, `id_sport`, `min`, `max`, `tim`) VALUES
(1, '100m running', 4, 1, 1, 0),
(2, '200m running', 4, 1, 1, 0),
(3, '400m running', 4, 1, 1, 0),
(4, '800m running', 4, 1, 1, 0),
(5, '5000m running', 4, 1, 1, 0),
(6, '10000m running', 4, 1, 1, 0),
(7, 'High Jump', 4, 1, 1, 0),
(8, 'Long Jump', 4, 1, 1, 0),
(9, 'Single', 8, 1, 1, 0),
(10, 'Double', 8, 2, 2, 1),
(11, 'Single', 6, 1, 1, 0),
(12, 'Double', 6, 2, 2, 1),
(13, 'Triple jump', 4, 1, 1, 0),
(14, 'Pole vault   ', 4, 1, 1, 0),
(15, 'Shot put', 4, 1, 1, 0),
(16, 'Discus throw', 4, 1, 1, 0),
(17, 'Hammer throwing', 4, 1, 1, 0),
(18, 'Javelin throw   ', 4, 1, 1, 0),
(19, 'Marathon', 4, 1, 1, 0),
(20, 'Racewalking 20km', 4, 1, 1, 0),
(21, 'Racewalking 50km', 4, 1, 1, 0),
(22, 'Road racing 225km', 5, 1, 1, 0),
(23, '100m  butterfly', 9, 1, 1, 0),
(24, '200m freestyle', 9, 1, 1, 0),
(25, '50m rifle 3 positions', 7, 1, 1, 0),
(26, '10m air rifle ', 7, 1, 1, 0),
(27, '25m small rifle ', 7, 1, 1, 0),
(28, '10m air pistol', 7, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `matchh`
--

CREATE TABLE `matchh` (
  `id` int(11) NOT NULL,
  `id_op1` int(11) DEFAULT NULL,
  `id_op2` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `id_tournament` int(11) NOT NULL,
  `winner` int(11) DEFAULT NULL,
  `result` varchar(20) DEFAULT NULL,
  `kolo` int(11) NOT NULL,
  `location` varchar(20) DEFAULT NULL,
  `groupa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='kolo (1 - 5 ) kvalifikacije';

-- --------------------------------------------------------

--
-- Table structure for table `matchh8`
--

CREATE TABLE `matchh8` (
  `id` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `location` varchar(20) NOT NULL,
  `qualification` int(11) NOT NULL,
  `id_tournament` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matchh8`
--

INSERT INTO `matchh8` (`id`, `time`, `location`, `qualification`, `id_tournament`) VALUES
(1, NULL, '', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `nation`
--

CREATE TABLE `nation` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `flag` varchar(40) NOT NULL,
  `athletes` int(11) NOT NULL,
  `gold` int(11) NOT NULL,
  `silver` int(11) NOT NULL,
  `bronze` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `rang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nation`
--

INSERT INTO `nation` (`id`, `name`, `flag`, `athletes`, `gold`, `silver`, `bronze`, `total`, `rang`) VALUES
(1, 'United States', 'unitedstates', 0, 0, 0, 0, 0, 0),
(2, 'Serbia', 'serbia', 0, 0, 0, 0, 0, 0),
(3, 'China', 'china', 0, 0, 0, 0, 0, 0),
(4, 'Great Britain', 'gb', 0, 0, 0, 0, 0, 0),
(7, 'Russian Federation', 'ru', 0, 0, 0, 0, 0, 0),
(8, 'Germany', 'de', 0, 0, 0, 0, 0, 0),
(9, 'France', 'fr', 0, 0, 0, 0, 0, 0),
(10, 'Japan', 'jp', 0, 0, 0, 0, 0, 0),
(11, 'Australia', 'au', 0, 0, 0, 0, 0, 0),
(12, 'Italy', 'it', 0, 0, 0, 0, 0, 0),
(13, 'Canada', 'ca', 0, 0, 0, 0, 0, 0),
(14, 'Republic of Korea', 'kr', 0, 0, 0, 0, 0, 0),
(15, 'Netherlands', 'nl', 0, 0, 0, 0, 0, 0),
(16, 'Brazil', 'br', 0, 0, 0, 0, 0, 0),
(17, 'Jamaica', 'jm', 0, 0, 0, 0, 0, 0),
(18, 'Kenya', 'ke', 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

CREATE TABLE `record` (
  `id` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `id_sportsman` int(11) NOT NULL,
  `record` varchar(20) NOT NULL,
  `id_discipline` int(11) NOT NULL,
  `location` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `record`
--

INSERT INTO `record` (`id`, `year`, `id_sportsman`, `record`, `id_discipline`, `location`) VALUES
(1, 2012, 1, '9.63', 1, 'London'),
(2, 2008, 1, '19.30', 2, 'Beijing'),
(3, 2008, 3, '2:06:32', 19, 'Beijing'),
(4, 1996, 17, '2.39 m ', 7, 'Atlanta'),
(5, 1968, 16, '8.90 m', 8, 'Mexico City'),
(6, 2016, 15, '22.52 m', 15, 'Rio de Janeiro');

-- --------------------------------------------------------

--
-- Table structure for table `sport`
--

CREATE TABLE `sport` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `min` int(11) NOT NULL,
  `max` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport`
--

INSERT INTO `sport` (`id`, `name`, `min`, `max`) VALUES
(1, 'Basketball', 5, 12),
(2, 'Wolleyball', 6, 12),
(3, 'Waterpolo', 6, 13),
(4, 'Athletics', 0, 0),
(5, 'Bicycling', 0, 0),
(6, 'Table tennis', 0, 0),
(7, 'Shooting', 0, 0),
(8, 'Tennis', 0, 0),
(9, 'Swimming', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `sportsman`
--

CREATE TABLE `sportsman` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `id_sport` int(11) NOT NULL,
  `id_nation` int(11) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `medal` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportsman`
--

INSERT INTO `sportsman` (`id`, `name`, `id_sport`, `id_nation`, `sex`, `medal`) VALUES
(1, 'Usain Bolt', 4, 17, 'man', 1),
(2, 'David Rudisha', 4, 18, 'man', 0),
(3, 'Samuel Wanjiru', 4, 18, 'man', 0),
(5, 'Novak Djokovic', 8, 2, 'man', 0),
(6, 'Ivana Spanovic', 4, 2, 'woman', 0),
(7, 'Milos Teodosic', 1, 2, 'man', 0),
(8, 'Nikola Jokic', 1, 2, 'man', 0),
(9, 'Miroslav Raduljica', 1, 2, 'man', 0),
(10, 'Stefan Markovic', 1, 2, 'man', 0),
(11, 'Vladimi Stimac ', 1, 2, 'man', 0),
(12, 'Nemanja Nedovic', 1, 2, 'man', 0),
(13, 'Bogdan Bogdanovic', 1, 2, 'man', 0),
(14, 'Stefan Jovic', 1, 2, 'man', 0),
(15, 'Ryan Crouser', 4, 1, 'man', 1),
(16, 'Bob Beamon', 4, 1, 'man', 1),
(17, 'Charles Austin', 4, 1, 'man', 0),
(18, 'Andy Murray', 8, 4, 'man', 1),
(19, 'Viktor Troicki', 8, 2, 'man', 0),
(20, 'Ana Ivanovic', 8, 2, 'woman', 0),
(22, 'Nenad Zimonjic', 8, 2, 'man', 0),
(23, 'Zorana Arunovic', 7, 2, 'woman', 0),
(24, 'Milos Mladenovic', 7, 2, 'man', 0),
(25, 'Dusko Pjetlovic', 3, 2, 'man', 1),
(26, 'Gojko Pjetlovic', 3, 2, 'man', 1),
(27, 'Zivko Gocic', 3, 2, 'man', 1),
(28, 'Sava Randjelovic', 3, 2, 'man', 1),
(29, 'Nikola Jaksic', 3, 2, 'man', 1),
(30, 'Filip Filipovic', 3, 2, 'man', 1),
(31, 'Andrija Prlainovic', 3, 2, 'man', 1),
(32, 'Kevin Durant', 1, 1, 'man', 0),
(33, 'Carmelo Anthony', 1, 1, 'man', 0),
(34, 'Harrison Barnes', 1, 1, 'man', 0),
(36, 'Jimmy Butler', 1, 1, 'man', 0),
(37, 'DeMarcus Cousins', 1, 1, 'man', 0),
(38, 'DeMar DeRozan', 1, 1, 'man', 0),
(39, 'Paul George', 1, 1, 'man', 0),
(40, 'Kyrie Irving', 1, 1, 'man', 0),
(41, 'Klay Thompson', 1, 1, 'man', 0),
(42, 'Trayvon Bromell', 4, 1, 'man', 0),
(43, 'Marvin Bracy', 4, 1, 'man', 0),
(44, 'Justin Gatlin', 4, 1, 'man', 0),
(45, 'Tianna Bartoletta', 4, 1, 'woman', 0),
(46, 'Janay DeLoach', 4, 1, 'woman', 0),
(47, 'Brittney Reese', 4, 1, 'woman', 0),
(49, 'Serena Williams', 8, 1, 'woman', 0),
(50, 'Venus Williams ', 8, 1, 'woman', 0),
(51, 'Brian Baker', 8, 1, 'man', 0),
(52, 'Steve Johnson', 8, 1, 'man', 0),
(53, 'Andrew Bogut', 1, 11, 'man', 0),
(54, 'Aron Baynes', 1, 11, 'man', 0),
(55, 'Joe Ingles', 1, 11, 'man', 0),
(56, 'Patty Mills', 1, 11, 'man', 0),
(57, 'Matthew Dellavedova', 1, 11, 'man', 0),
(58, 'Cameron Bairstow', 1, 11, 'man', 0),
(59, 'AUS 100m 1', 4, 11, 'man', 0),
(60, 'AUS 100m 2', 4, 11, 'man', 0),
(61, 'AUS 100m 3', 4, 11, 'man', 0),
(62, 'AUS 100m 4', 4, 11, 'man', 0),
(63, 'AUS long jump 1', 4, 11, 'woman', 0),
(64, 'AUS long jump 2', 4, 11, 'woman', 0),
(65, 'AUS long jump 3', 4, 11, 'woman', 0),
(66, 'AUS tenis 1', 8, 11, 'man', 0),
(67, 'AUS tenis 2', 8, 11, 'man', 0),
(68, 'AUS tenis 3', 8, 11, 'man', 0),
(69, 'AUS tenis 4', 8, 11, 'man', 0),
(70, 'GBR basket 1', 1, 4, 'man', 0),
(71, 'GBR basket 2', 1, 4, 'man', 0),
(72, 'GBR basket 3', 1, 4, 'man', 0),
(73, 'GBR basket 4', 1, 4, 'man', 0),
(74, 'GBR basket 5', 1, 4, 'man', 0),
(75, 'GBR long jump 1', 4, 4, 'woman', 0),
(76, 'GBR long jump 2', 4, 4, 'woman', 0),
(77, 'GBR long jump 3', 4, 4, 'woman', 0),
(78, 'GBR 100m 3', 4, 4, 'man', 0),
(79, 'GBR 100m 2', 4, 4, 'man', 0),
(80, 'GBR 100m 1', 4, 4, 'man', 0),
(81, 'GBR tenis 1', 8, 4, 'man', 0),
(82, 'GBR tenis 2', 8, 4, 'man', 0),
(86, 'Laza Laza', 6, 2, 'man', 0),
(87, 'CHI basket 1', 1, 3, 'man', 0),
(88, 'CHI basket 2', 1, 3, 'man', 0),
(89, 'CHI basket 3', 1, 3, 'man', 0),
(90, 'CHI basket 4', 1, 3, 'man', 0),
(91, 'CHI basket 5', 1, 3, 'man', 0),
(92, 'CAN basket 1', 1, 13, 'man', 0),
(93, 'CAN basket 2', 1, 13, 'man', 0),
(94, 'CAN basket 3', 1, 13, 'man', 0),
(95, 'CAN basket 4', 1, 13, 'man', 0),
(96, 'CAN basket 5', 1, 13, 'man', 0),
(97, 'BRA basket 5', 1, 16, 'man', 0),
(98, 'BRA basket 4  ', 1, 16, 'man', 0),
(99, 'BRA basket 3 ', 1, 16, 'man', 0),
(100, 'BRA basket 2', 1, 16, 'man', 0),
(101, 'BRA basket 1', 1, 16, 'man', 0),
(102, 'FRA basket 1', 1, 9, 'man', 0),
(103, 'FRA basket 2', 1, 9, 'man', 0),
(104, 'FRA basket 3', 1, 9, 'man', 0),
(105, 'FRA basket 4', 1, 9, 'man', 0),
(106, 'FRA basket 5', 1, 9, 'man', 0),
(107, 'RUS basket 5', 1, 7, 'man', 0),
(108, 'RUS basket 4', 1, 7, 'man', 0),
(109, 'RUS basket 3', 1, 7, 'man', 0),
(110, 'RUS basket 2', 1, 7, 'man', 0),
(111, 'RUS basket 1', 1, 7, 'man', 0),
(112, 'GER basket 1', 1, 8, 'man', 0),
(113, 'GER basket 2', 1, 8, 'man', 0),
(114, 'GER basket 3', 1, 8, 'man', 0),
(115, 'GER basket 4', 1, 8, 'man', 0),
(116, 'GER basket 5', 1, 8, 'man', 0),
(117, 'ITA basket 5', 1, 12, 'man', 0),
(118, 'ITA basket 4', 1, 12, 'man', 0),
(119, 'ITA basket 3', 1, 12, 'man', 0),
(120, 'ITA basket 2', 1, 12, 'man', 0),
(121, 'ITA basket 1', 1, 12, 'man', 0),
(122, 'JAM basket 1', 1, 17, 'man', 0),
(123, 'JAM basket 2', 1, 17, 'man', 0),
(124, 'JAM basket 3', 1, 17, 'man', 0),
(125, 'JAM basket 4', 1, 17, 'man', 0),
(126, 'JAM basket 5', 1, 17, 'man', 0),
(127, 'SRB tenis 1', 8, 2, 'man', 0),
(128, 'SRB tenis 2', 8, 2, 'man', 0),
(129, 'SRB tenis 3', 8, 2, 'man', 0),
(130, 'SRB tenis 4', 8, 2, 'man', 0),
(131, 'USA tenis 1', 8, 1, 'man', 0),
(132, 'USA tenis 2', 8, 1, 'man', 0),
(133, 'USA tenis 3', 8, 1, 'man', 0),
(134, 'USA tenis 4', 8, 1, 'man', 0),
(135, 'USA tenis 5', 8, 1, 'man', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sportsman_discipline`
--

CREATE TABLE `sportsman_discipline` (
  `id_sportsman` int(11) NOT NULL,
  `id_discipline` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportsman_discipline`
--

INSERT INTO `sportsman_discipline` (`id_sportsman`, `id_discipline`) VALUES
(1, 1),
(42, 1),
(43, 1),
(44, 1),
(59, 1),
(60, 1),
(61, 1),
(62, 1),
(78, 1),
(79, 1),
(80, 1),
(1, 2),
(2, 4),
(17, 7),
(6, 8),
(16, 8),
(45, 8),
(46, 8),
(47, 8),
(63, 8),
(64, 8),
(65, 8),
(75, 8),
(76, 8),
(77, 8),
(5, 9),
(19, 9),
(20, 9),
(49, 9),
(50, 9),
(51, 9),
(52, 9),
(66, 9),
(67, 9),
(68, 9),
(69, 9),
(81, 9),
(82, 9),
(86, 9),
(127, 9),
(128, 9),
(129, 9),
(130, 9),
(131, 9),
(132, 9),
(133, 9),
(134, 9),
(135, 9),
(5, 10),
(18, 10),
(22, 10),
(49, 10),
(50, 10),
(51, 10),
(52, 10),
(66, 10),
(67, 10),
(68, 10),
(69, 10),
(81, 10),
(82, 10),
(128, 10),
(129, 10),
(18, 11),
(15, 15),
(24, 26),
(23, 27);

-- --------------------------------------------------------

--
-- Table structure for table `sportsman_team`
--

CREATE TABLE `sportsman_team` (
  `id_sportsman` int(11) NOT NULL,
  `id_team` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportsman_team`
--

INSERT INTO `sportsman_team` (`id_sportsman`, `id_team`) VALUES
(5, 13),
(5, 14),
(5, 58),
(6, 11),
(6, 36),
(7, 9),
(7, 38),
(8, 9),
(8, 38),
(9, 9),
(9, 38),
(10, 9),
(10, 38),
(11, 9),
(11, 38),
(12, 9),
(12, 38),
(13, 9),
(13, 38),
(14, 9),
(14, 38),
(18, 35),
(19, 15),
(19, 52),
(20, 12),
(22, 13),
(25, 10),
(26, 10),
(27, 10),
(28, 10),
(29, 10),
(30, 10),
(31, 10),
(32, 1),
(32, 39),
(33, 1),
(33, 39),
(34, 1),
(34, 39),
(36, 1),
(37, 1),
(37, 39),
(38, 1),
(38, 39),
(39, 1),
(39, 39),
(40, 1),
(40, 39),
(41, 1),
(41, 39),
(42, 2),
(43, 3),
(44, 4),
(45, 5),
(46, 6),
(46, 7),
(47, 8),
(49, 16),
(49, 17),
(50, 16),
(50, 18),
(53, 24),
(53, 40),
(54, 24),
(54, 40),
(55, 24),
(55, 40),
(56, 24),
(56, 40),
(57, 24),
(57, 40),
(58, 24),
(58, 40),
(59, 25),
(60, 26),
(61, 27),
(62, 28),
(66, 19),
(66, 23),
(67, 20),
(67, 23),
(68, 21),
(69, 22),
(70, 29),
(70, 42),
(71, 29),
(71, 42),
(72, 29),
(72, 42),
(73, 29),
(73, 42),
(74, 29),
(74, 42),
(75, 30),
(76, 31),
(77, 32),
(81, 33),
(81, 35),
(82, 34),
(86, 37),
(87, 41),
(88, 41),
(89, 41),
(90, 41),
(91, 41),
(92, 43),
(93, 43),
(94, 43),
(95, 43),
(96, 43),
(97, 44),
(98, 44),
(99, 44),
(100, 44),
(101, 44),
(102, 45),
(103, 45),
(104, 45),
(105, 45),
(106, 45),
(107, 46),
(108, 46),
(109, 46),
(110, 46),
(111, 46),
(112, 47),
(113, 47),
(114, 47),
(115, 47),
(116, 47),
(117, 48),
(118, 48),
(119, 48),
(120, 48),
(121, 48),
(122, 49),
(123, 49),
(124, 49),
(125, 49),
(126, 49),
(127, 53),
(127, 54),
(127, 55),
(127, 56),
(128, 50),
(130, 51),
(130, 57),
(131, 59),
(132, 63),
(133, 60),
(134, 64),
(135, 61),
(135, 62);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `id_tournament` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_sport` int(11) NOT NULL,
  `id_discipline` int(11) DEFAULT NULL,
  `sex` varchar(10) NOT NULL,
  `applied` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `id_tournament`, `id_user`, `id_sport`, `id_discipline`, `sex`, `applied`) VALUES
(2, 2, 4, 4, 1, 'man', 1),
(3, 2, 4, 4, 1, 'man', 1),
(4, 2, 4, 4, 1, 'man', 1),
(5, NULL, 4, 4, 8, 'woman', 1),
(6, NULL, 4, 4, 8, 'woman', 1),
(7, NULL, 4, 4, 8, 'woman', 0),
(8, NULL, 4, 4, 8, 'woman', 1),
(10, NULL, 5, 3, NULL, 'man', 1),
(11, NULL, 5, 4, 8, 'woman', 1),
(12, NULL, 5, 8, 9, 'woman', 1),
(13, NULL, 5, 8, 10, 'man', 1),
(14, 4, 5, 8, 9, 'man', 1),
(15, 4, 5, 8, 9, 'man', 1),
(16, NULL, 4, 8, 10, 'woman', 1),
(17, NULL, 4, 8, 9, 'woman', 1),
(18, NULL, 4, 8, 9, 'woman', 1),
(19, 4, 8, 8, 9, 'man', 1),
(20, 4, 8, 8, 9, 'man', 1),
(21, 4, 8, 8, 9, 'man', 1),
(22, 4, 8, 8, 9, 'man', 1),
(23, NULL, 8, 8, 10, 'man', 1),
(25, 2, 8, 4, 1, 'man', 1),
(26, 2, 8, 4, 1, 'man', 1),
(27, 2, 8, 4, 1, 'man', 1),
(28, 2, 8, 4, 1, 'man', 1),
(30, NULL, 11, 4, 8, 'woman', 1),
(31, NULL, 11, 4, 8, 'woman', 1),
(32, NULL, 11, 4, 8, 'woman', 1),
(33, NULL, 11, 8, 9, 'man', 0),
(34, NULL, 11, 8, 9, 'man', 0),
(35, NULL, 11, 8, 10, 'man', 1),
(36, NULL, 5, 4, 8, 'woman', 0),
(37, NULL, 5, 6, 9, 'man', 1),
(38, 3, 5, 1, NULL, 'man', 1),
(39, 3, 4, 1, NULL, 'man', 1),
(40, 3, 8, 1, NULL, 'man', 1),
(41, 3, 6, 1, NULL, 'man', 1),
(42, 3, 11, 1, NULL, 'man', 1),
(43, 3, 12, 1, NULL, 'man', 1),
(44, 3, 13, 1, NULL, 'man', 1),
(45, 3, 14, 1, NULL, 'man', 1),
(46, 3, 15, 1, NULL, 'man', 1),
(47, 3, 16, 1, NULL, 'man', 1),
(48, 3, 17, 1, NULL, 'man', 1),
(49, 3, 18, 1, NULL, 'man', 1),
(50, 4, 5, 8, 9, 'man', 1),
(51, 4, 5, 8, 9, 'man', 1),
(52, 4, 5, 8, 9, 'man', 1),
(53, 4, 5, 8, 9, 'man', 1),
(54, NULL, 5, 8, 9, 'man', 0),
(55, NULL, 5, 8, 9, 'man', 0),
(56, NULL, 5, 8, 9, 'man', 0),
(57, NULL, 5, 8, 9, 'man', 0),
(58, 4, 5, 8, 9, 'man', 1),
(59, 4, 4, 8, 9, 'man', 1),
(60, 4, 4, 8, 9, 'man', 1),
(61, NULL, 4, 8, 9, 'man', 0),
(62, 4, 4, 8, 9, 'man', 1),
(63, 4, 4, 8, 9, 'man', 1),
(64, 4, 4, 8, 9, 'man', 1);

-- --------------------------------------------------------

--
-- Table structure for table `team_matchh8`
--

CREATE TABLE `team_matchh8` (
  `id_team` int(11) NOT NULL,
  `id_matchh8` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tournament`
--

CREATE TABLE `tournament` (
  `id` int(11) NOT NULL,
  `id_sport` int(11) NOT NULL,
  `id_discipline` int(11) DEFAULT NULL,
  `sex` varchar(10) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  `location` varchar(50) NOT NULL,
  `id_delegate` int(11) DEFAULT NULL,
  `valid` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tournament`
--

INSERT INTO `tournament` (`id`, `id_sport`, `id_discipline`, `sex`, `begin`, `end`, `location`, `id_delegate`, `valid`) VALUES
(2, 4, 1, 'man', '2016-09-22', '2016-09-22', 'Trke', 7, 1),
(3, 1, NULL, 'man', '2016-09-24', '2016-09-24', 'Basketball Arena', 7, 1),
(4, 8, 9, 'man', '2016-09-25', '2016-09-25', 'Tennis Arena', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `id_nation` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `valid` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `firstname`, `lastname`, `email`, `id_nation`, `type`, `valid`) VALUES
(1, 'lazav94', 'ratasa2131', 'Lazar', 'Vasic', 'lazav94@gmail.com', 2, 1, 1),
(2, 'admin', 'Admin.,.123', 'Admin', 'Admin', 'admin@admin.com', 1, 0, 1),
(4, 'USA', 'Usaa123..', 'USA', 'SAD', 'USA@USA.com', 1, 2, 1),
(5, 'SRB', 'Srbija12>>', 'Aleksandar', 'Vucic', 'vucko@gmail.com', 2, 2, 1),
(6, 'CHI', 'f7D.fgfsdc.3', 'China', 'Chinic', 'chin@china.ch', 3, 2, 1),
(7, 'delegat1', 'Delegt123..1', 'Delegat', 'Delegatic', 'delegat@gmai.com', 18, 1, 1),
(8, 'AUS', 'Aurali123..', 'Australia', 'Australic', 'aus@aus.au', 11, 2, 1),
(11, 'GBR', 'Gbrd132..', 'Britanij', 'Britanic', 'gb@gbr.com', 4, 2, 1),
(12, 'CAN', 'Canada123..', 'Ides za ', 'Kanadu', 'ca@gmail.com', 13, 2, 1),
(13, 'BRA', 'Braz123..', 'Brazil', 'Brazilinjo', 'brazil@gmail.com', 16, 2, 1),
(14, 'FRA', 'France123..', 'Francuska', 'Pariz', 'france@gmail.com', 9, 2, 1),
(15, 'RUS', 'Rusia123..', 'Mother ', 'Russia', 'vodka@gmail.com', 7, 2, 1),
(16, 'GER', 'German123..', 'Angela', 'Merkel', 'dojc@gmail.com', 8, 2, 1),
(17, 'ITA', 'Italy123..', 'Pizza', 'Vegetarijana', 'italy@italy.com', 12, 2, 1),
(18, 'JAM', 'jAmaj123..', 'Bob', 'Marley', 'bob@bob.bob', 17, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `userrequest`
--

CREATE TABLE `userrequest` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sport` (`id_sport`);

--
-- Indexes for table `matchh`
--
ALTER TABLE `matchh`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `matchh8`
--
ALTER TABLE `matchh8`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nation`
--
ALTER TABLE `nation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sportsman` (`id_sportsman`),
  ADD KEY `id_discipline` (`id_discipline`);

--
-- Indexes for table `sport`
--
ALTER TABLE `sport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `sportsman`
--
ALTER TABLE `sportsman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_sport` (`id_sport`,`id_nation`),
  ADD KEY `id_nation` (`id_nation`);

--
-- Indexes for table `sportsman_discipline`
--
ALTER TABLE `sportsman_discipline`
  ADD PRIMARY KEY (`id_sportsman`,`id_discipline`),
  ADD KEY `id_discipline` (`id_discipline`),
  ADD KEY `id_sportsman` (`id_sportsman`);

--
-- Indexes for table `sportsman_team`
--
ALTER TABLE `sportsman_team`
  ADD PRIMARY KEY (`id_sportsman`,`id_team`),
  ADD KEY `id_sportsman` (`id_sportsman`,`id_team`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `team_matchh8`
--
ALTER TABLE `team_matchh8`
  ADD PRIMARY KEY (`id_team`,`id_matchh8`);

--
-- Indexes for table `tournament`
--
ALTER TABLE `tournament`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`id_sport`,`id_discipline`),
  ADD KEY `id_sport` (`id_sport`),
  ADD KEY `id_discipline` (`id_discipline`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nation` (`id_nation`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `userrequest`
--
ALTER TABLE `userrequest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `discipline`
--
ALTER TABLE `discipline`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `matchh`
--
ALTER TABLE `matchh`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;
--
-- AUTO_INCREMENT for table `matchh8`
--
ALTER TABLE `matchh8`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `nation`
--
ALTER TABLE `nation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `record`
--
ALTER TABLE `record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `sport`
--
ALTER TABLE `sport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `sportsman`
--
ALTER TABLE `sportsman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;
--
-- AUTO_INCREMENT for table `tournament`
--
ALTER TABLE `tournament`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `userrequest`
--
ALTER TABLE `userrequest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `discipline`
--
ALTER TABLE `discipline`
  ADD CONSTRAINT `discipline_ibfk_1` FOREIGN KEY (`id_sport`) REFERENCES `sport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `record_ibfk_1` FOREIGN KEY (`id_sportsman`) REFERENCES `sportsman` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `record_ibfk_2` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sportsman`
--
ALTER TABLE `sportsman`
  ADD CONSTRAINT `sportsman_ibfk_1` FOREIGN KEY (`id_sport`) REFERENCES `sport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sportsman_ibfk_2` FOREIGN KEY (`id_nation`) REFERENCES `nation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sportsman_discipline`
--
ALTER TABLE `sportsman_discipline`
  ADD CONSTRAINT `sportsman_discipline_ibfk_1` FOREIGN KEY (`id_sportsman`) REFERENCES `sportsman` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sportsman_discipline_ibfk_2` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tournament`
--
ALTER TABLE `tournament`
  ADD CONSTRAINT `tournament_ibfk_1` FOREIGN KEY (`id_sport`) REFERENCES `sport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tournament_ibfk_2` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_nation`) REFERENCES `nation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `userrequest`
--
ALTER TABLE `userrequest`
  ADD CONSTRAINT `userrequest_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
