-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 15, 2020 at 10:36 AM
-- Server version: 10.4.12-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `slamland`
--

-- --------------------------------------------------------

--
-- Table structure for table `acheter`
--

CREATE TABLE `acheter` (
  `idachat` int(11) NOT NULL,
  `idvisiteur` int(11) DEFAULT NULL,
  `idcommerce` int(11) DEFAULT NULL,
  `nomconsommateur` varchar(50) DEFAULT NULL,
  `prenomconsommateur` varchar(50) DEFAULT NULL,
  `dateachat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acheter`
--

INSERT INTO `acheter` (`idachat`, `idvisiteur`, `idcommerce`, `nomconsommateur`, `prenomconsommateur`, `dateachat`) VALUES
(38, 24, 732, 'david', 'dupont', '15/06/2019'),
(39, 25, 548, 'martin', 'chane', '15/10/2019');

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `prix` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `libelle`, `prix`) VALUES
(12, 'Figurine Star-Wars', 39.99),
(89, 'peluche mickey', 19.99),
(91, 'Ensemble LEGO 75192 Millenium Falcon Star Wars', 799.99);

-- --------------------------------------------------------

--
-- Table structure for table `attractions`
--

CREATE TABLE `attractions` (
  `id` int(11) NOT NULL,
  `idparc` int(11) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `capaciteMax` int(11) DEFAULT NULL,
  `duree` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attractions`
--

INSERT INTO `attractions` (`id`, `idparc`, `nom`, `capaciteMax`, `duree`) VALUES
(482, 1, 'Goudurix', 50, 80),
(784, 2, 'Tour de la terreur', 40, 50),
(903, 1, 'OzIris', 40, 70);

-- --------------------------------------------------------

--
-- Table structure for table `commerce`
--

CREATE TABLE `commerce` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commerce`
--

INSERT INTO `commerce` (`id`, `nom`) VALUES
(548, 'Boutique Rainforest Café\r\n'),
(732, 'café Hyperion');

-- --------------------------------------------------------

--
-- Table structure for table `magasin`
--

CREATE TABLE `magasin` (
  `idmagasin` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`
--

INSERT INTO `magasin` (`idmagasin`, `nom`) VALUES
(548, 'Boutique Rainforest Café');

-- --------------------------------------------------------

--
-- Table structure for table `parcattractions`
--

CREATE TABLE `parcattractions` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `prixbillet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parcattractions`
--

INSERT INTO `parcattractions` (`id`, `nom`, `ville`, `prixbillet`) VALUES
(1, 'Astérix', 'Plailly', 39),
(2, 'Disneyland Paris', 'Coupvray', 50);

-- --------------------------------------------------------

--
-- Table structure for table `participer`
--

CREATE TABLE `participer` (
  `idparticipation` int(11) NOT NULL,
  `idattraction` int(11) DEFAULT NULL,
  `idvisiteur` int(11) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `dateNaissance` varchar(50) DEFAULT NULL,
  `idparc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participer`
--

INSERT INTO `participer` (`idparticipation`, `idattraction`, `idvisiteur`, `prenom`, `nom`, `dateNaissance`, `idparc`) VALUES
(1, 482, 24, 'david', 'dupont', '15/02/1997', 1),
(2, 482, 25, 'martin', 'chane', '15/02/1999', 1),
(5, 784, 7, 'ty', 'ty', '07/02/1967', 2);

-- --------------------------------------------------------

--
-- Table structure for table `posseder`
--

CREATE TABLE `posseder` (
  `idarticle` int(11) NOT NULL,
  `idmagasin` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `prix` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posseder`
--

INSERT INTO `posseder` (`idarticle`, `idmagasin`, `libelle`, `prix`) VALUES
(12, 548, 'Figurine Star-Wars', 39.99),
(89, 548, 'peluche mickey', 19.99),
(91, 548, 'Ensemble LEGO 75192 Millenium Falcon Star Wars', 799.99);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id`, `nom`) VALUES
(732, 'café Hyperion');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `login` varchar(50) NOT NULL,
  `mdp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`login`, `mdp`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `visiteur`
--

CREATE TABLE `visiteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `dateNaissance` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `visiteur`
--

INSERT INTO `visiteur` (`id`, `nom`, `prenom`, `dateNaissance`) VALUES
(7, 'ty', 'ty', '07/02/1967'),
(24, 'david', 'dupont', '15/02/1985'),
(25, 'martin', 'chane', '15/02/1999');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acheter`
--
ALTER TABLE `acheter`
  ADD PRIMARY KEY (`idachat`),
  ADD KEY `idvisiteur` (`idvisiteur`),
  ADD KEY `idcommerce` (`idcommerce`);

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attractions`
--
ALTER TABLE `attractions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idparc` (`idparc`);

--
-- Indexes for table `commerce`
--
ALTER TABLE `commerce`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `magasin`
--
ALTER TABLE `magasin`
  ADD PRIMARY KEY (`idmagasin`);

--
-- Indexes for table `parcattractions`
--
ALTER TABLE `parcattractions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `participer`
--
ALTER TABLE `participer`
  ADD PRIMARY KEY (`idparticipation`),
  ADD KEY `idattraction` (`idattraction`),
  ADD KEY `idvisiteur` (`idvisiteur`);

--
-- Indexes for table `posseder`
--
ALTER TABLE `posseder`
  ADD PRIMARY KEY (`idarticle`,`idmagasin`),
  ADD KEY `idmagasin` (`idmagasin`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`login`);

--
-- Indexes for table `visiteur`
--
ALTER TABLE `visiteur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commerce`
--
ALTER TABLE `commerce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=733;

--
-- AUTO_INCREMENT for table `participer`
--
ALTER TABLE `participer`
  MODIFY `idparticipation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `acheter`
--
ALTER TABLE `acheter`
  ADD CONSTRAINT `acheter_ibfk_1` FOREIGN KEY (`idvisiteur`) REFERENCES `visiteur` (`id`),
  ADD CONSTRAINT `acheter_ibfk_2` FOREIGN KEY (`idcommerce`) REFERENCES `commerce` (`id`);

--
-- Constraints for table `attractions`
--
ALTER TABLE `attractions`
  ADD CONSTRAINT `attractions_ibfk_1` FOREIGN KEY (`idparc`) REFERENCES `parcattractions` (`id`);

--
-- Constraints for table `magasin`
--
ALTER TABLE `magasin`
  ADD CONSTRAINT `magasin_ibfk_1` FOREIGN KEY (`idmagasin`) REFERENCES `commerce` (`id`);

--
-- Constraints for table `participer`
--
ALTER TABLE `participer`
  ADD CONSTRAINT `participer_ibfk_1` FOREIGN KEY (`idattraction`) REFERENCES `attractions` (`id`),
  ADD CONSTRAINT `participer_ibfk_2` FOREIGN KEY (`idvisiteur`) REFERENCES `visiteur` (`id`);

--
-- Constraints for table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`idarticle`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `posseder_ibfk_2` FOREIGN KEY (`idmagasin`) REFERENCES `magasin` (`idmagasin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
