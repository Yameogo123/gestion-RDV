-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 24 oct. 2021 à 19:54
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gest_rdv`
--

-- --------------------------------------------------------

--
-- Structure de la table `antecedant`
--

DROP TABLE IF EXISTS `antecedant`;
CREATE TABLE IF NOT EXISTS `antecedant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Déchargement des données de la table `antecedant`
--

INSERT INTO `antecedant` (`id`, `libelle`) VALUES
(1, 'diabète'),
(2, 'hypertension'),
(3, 'hépatite');

-- --------------------------------------------------------

--
-- Structure de la table `constante`
--

DROP TABLE IF EXISTS `constante`;
CREATE TABLE IF NOT EXISTS `constante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Structure de la table `diagnostic`
--

DROP TABLE IF EXISTS `diagnostic`;
CREATE TABLE IF NOT EXISTS `diagnostic` (
  `id_constante` int(11) NOT NULL,
  `id_consultation` int(11) NOT NULL,
  KEY `id_constante` (`id_constante`),
  KEY `id_consultation` (`id_consultation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `libelle` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `id_ordonnance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_ordonnance` (`id_ordonnance`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

DROP TABLE IF EXISTS `ordonnance`;
CREATE TABLE IF NOT EXISTS `ordonnance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `posologie` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `id_consultation` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_consultation` (`id_consultation`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `date` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_medecin` int(11) DEFAULT NULL,
  `id_ordonnance` int(11) DEFAULT NULL,
  `id_consultation` int(11) DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patient` (`id_patient`),
  KEY `id_medecin` (`id_medecin`),
  KEY `id_consultation` (`id_consultation`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`id`, `type`, `date`, `id_patient`, `id_medecin`, `id_ordonnance`, `id_consultation`, `libelle`) VALUES
(1, 'PRESTATION', '15/05/2021', 5, 4, NULL, NULL, 'Analyse'),
(6, 'CONSULTATION', '2021-10-28', 5, 4, NULL, NULL, NULL),
(7, 'PRESTATION', '2021-10-29', 3, NULL, NULL, NULL, 'Scan');

-- --------------------------------------------------------

--
-- Structure de la table `sante`
--

DROP TABLE IF EXISTS `sante`;
CREATE TABLE IF NOT EXISTS `sante` (
  `id_antecedant` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  KEY `id_antecedant` (`id_antecedant`),
  KEY `id_patient` (`id_patient`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Déchargement des données de la table `sante`
--

INSERT INTO `sante` (`id_antecedant`, `id_patient`) VALUES
(1, 5),
(2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomComplet` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `login` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_general_mysql500_ci NOT NULL,
  `code` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `statut` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  `disponibilite` varchar(255) COLLATE utf8_general_mysql500_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_mysql500_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nomComplet`, `login`, `password`, `role`, `code`, `statut`, `disponibilite`) VALUES
(1, 'exemple rp1', 'rp1@gmail.com', 'rp1', 'ROLE_RP', NULL, NULL, NULL),
(2, 'exemple secretaire1', 'secretaire1@gmail.com', 'secretaire1', 'ROLE_SECRETAIRE', NULL, NULL, NULL),
(3, 'exemple patient1', 'patient1@gmail.com', 'patient1', 'ROLE_PATIENT', 'P1', NULL, NULL),
(4, 'exemple medecin1', 'medecin1@gmail.com', 'medecin1', 'ROLE_MEDECIN', NULL, 'spécialiste', 'oui'),
(5, 'patient2', 'patient2@gmail.com', 'patient2', 'ROLE_PATIENT', 'GOR', NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
