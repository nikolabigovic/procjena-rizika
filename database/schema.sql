
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `firma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firma` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv_firme` varchar(255) NOT NULL,
  `sjediste` varchar(255) DEFAULT NULL,
  `pib` varchar(20) NOT NULL,
  `sifra_djelatnosti` varchar(20) DEFAULT NULL,
  `datum_osnivanja_firme` date DEFAULT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `posljedice` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pib` (`pib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `grupa_faktora_rizika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupa_faktora_rizika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `opis` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `indikator_ocjena`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indikator_ocjena` (
  `id` int NOT NULL AUTO_INCREMENT,
  `indikator_rizika_id` int NOT NULL,
  `procjena_rizika_id` int NOT NULL,
  `ocjena` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `indikator_rizika_id` (`indikator_rizika_id`),
  KEY `procjena_rizika_id` (`procjena_rizika_id`),
  CONSTRAINT `indikator_ocjena_ibfk_1` FOREIGN KEY (`indikator_rizika_id`) REFERENCES `indikator_rizika` (`id`),
  CONSTRAINT `indikator_ocjena_ibfk_2` FOREIGN KEY (`procjena_rizika_id`) REFERENCES `procjena_rizika` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `indikator_rizika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indikator_rizika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grupa_faktora_rizika_id` int NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `opis` text,
  PRIMARY KEY (`id`),
  KEY `grupa_faktora_rizika_id` (`grupa_faktora_rizika_id`),
  CONSTRAINT `indikator_rizika_ibfk_1` FOREIGN KEY (`grupa_faktora_rizika_id`) REFERENCES `grupa_faktora_rizika` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `klijent_fizicko_lice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klijent_fizicko_lice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `maticni_br` varchar(20) DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `mjesto_prebivalista` varchar(255) DEFAULT NULL,
  `pravno_lice_id` int NOT NULL,
  `status_id` int DEFAULT NULL,
  `is_pel` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `pravno_lice_id` (`pravno_lice_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `klijent_fizicko_lice_ibfk_1` FOREIGN KEY (`pravno_lice_id`) REFERENCES `klijent_pravno_lice` (`id`),
  CONSTRAINT `klijent_fizicko_lice_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `status_fizickog_lica` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `klijent_pravno_lice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klijent_pravno_lice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `sjediste` varchar(255) DEFAULT NULL,
  `pib` varchar(20) NOT NULL,
  `sifra_djelatnosti` varchar(20) DEFAULT NULL,
  `datum_osnivanja_firme` date DEFAULT NULL,
  `datum_uspostavljanja_poslovnog_odnosa` date DEFAULT NULL,
  `resident` tinyint(1) DEFAULT NULL,
  `razlog_poslovnog_odnosa` varchar(300) DEFAULT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `firma_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pib` (`pib`),
  KEY `firma_id` (`firma_id`),
  CONSTRAINT `klijent_pravno_lice_ibfk_1` FOREIGN KEY (`firma_id`) REFERENCES `firma` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `firma_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `firma_id` (`firma_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`firma_id`) REFERENCES `firma` (`id`),
  CONSTRAINT `korisnik_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `nivo_rizika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivo_rizika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `procjena_rizika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procjena_rizika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `klijent_pravno_lice_id` int NOT NULL,
  `korisnik_kreator_id` int NOT NULL,
  `prijetnja` double DEFAULT NULL,
  `ranjivost` double DEFAULT NULL,
  `posljedica` double DEFAULT NULL,
  `ukupni_rizik` double DEFAULT NULL,
  `nivo_rizika_id` int DEFAULT NULL,
  `datum_procjene` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `datum_naredne_procjene` date DEFAULT NULL,
  `automatski_rizik` tinyint(1) DEFAULT '0',
  `obrazlozenje` text,
  PRIMARY KEY (`id`),
  KEY `klijent_pravno_lice_id` (`klijent_pravno_lice_id`),
  KEY `korisnik_kreator_id` (`korisnik_kreator_id`),
  KEY `nivo_rizika_id` (`nivo_rizika_id`),
  CONSTRAINT `procjena_rizika_ibfk_1` FOREIGN KEY (`klijent_pravno_lice_id`) REFERENCES `klijent_pravno_lice` (`id`),
  CONSTRAINT `procjena_rizika_ibfk_2` FOREIGN KEY (`korisnik_kreator_id`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `procjena_rizika_ibfk_3` FOREIGN KEY (`nivo_rizika_id`) REFERENCES `nivo_rizika` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `ranjivost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ranjivost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sifra_djelatnosti` varchar(20) NOT NULL,
  `ocjena` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sifra_djelatnosti` (`sifra_djelatnosti`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `sifre_djelatnosti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sifre_djelatnosti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sifra` varchar(10) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sifra_UNIQUE` (`sifra`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `status_fizickog_lica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_fizickog_lica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `tezina_grupe_rizika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tezina_grupe_rizika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sifra_djelatnosti` varchar(20) NOT NULL,
  `grupa_faktora_rizika_id` int NOT NULL,
  `tezina` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `grupa_faktora_rizika_id` (`grupa_faktora_rizika_id`),
  CONSTRAINT `tezina_grupe_rizika_ibfk_1` FOREIGN KEY (`grupa_faktora_rizika_id`) REFERENCES `grupa_faktora_rizika` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

