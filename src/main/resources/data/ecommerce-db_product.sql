-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce-db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'- Ultra-Thin/- Low-Latency Bluetooth/- Cherry MX Ultra Low Profile Keyswitches','/imgs/keyboardcorsair.jpg','Corsair K100 RGB Optical switch (OPX-RF) (CH-912A01A-NA) Keyboard',229),(2,1,'- Adjustable Actuation Switches/- World\'s Fastest Mechanical Keyboard/- OLED Smart Display','/imgs/keyboardsteelseries.jpg','Steelseries Apex Pro Mini US 64820 Keyboard',149),(3,1,'- Unifying Wireless Technology','/imgs/keyboardlogitech.jpg','Logitech G913 TKL Lightspeed Wireless RGB Blue Clicky switch Keyboard',119),(4,1,'- Hybrid Mechanical Key Switches/- Detachable Plush Wrist Rest/- Programmable Macro Functionality','/imgs/keyboardrazer.jpg','Razer Ornata V3-Low Profile (USB/RGB) (RZ03-04460100-R3M1) Gaming Keyboard',99),(5,1,'- Backlit Low Profile Keys/- Wireless Rechargeable Programmable Keyboard','/imgs/keyboardfuhlen.jpg','Fuhlen T87S Pro Wireless White Red Switch',79),(6,1,'- Hot-swappable, ROG NX Brown Tactile Switches/- Acoustic Dampening Foam/- Media Controls','/imgs/keyboardasus.jpg','ASUS ROG Strix Flare II Animate 100% RGB Gaming Keyboard',213),(7,1,'- 87Key/- High-end Mechanical/- Bluetooth Keyboard','/imgs/keyboardleopold.jpg','LEOPOLD FC750R BlueTooth PD Grey/Blue',129),(8,1,'- USB/- Bluetooth/- 3Mode/- Hotswap','/imgs/keyboardflesport.jpg','FL-Esport FL750 SAM Marshmallow RGB Gasket Mount Kailh BOX V2 Red sw',229),(9,1,'- Statellite shaft structure/- Working mode: wired USB-TypeC/Bluetooth 5.1/2.4G/- 4000mAh lithium battery','/imgs/keyboarddareu.jpg','Dareu A98 PRO Tri Mode RED WAVE',169),(10,1,'- Wireless 2.4G + Bluetooth (BT5.0)/- ABS Keycaps','/imgs/keyboardedra.jpg','Edra EK368L Beta Wireless Gaming Keyboard',25),(11,2,'- 25,600 DPI/- Adjustable Weights/- 11 Programmable Buttons','/imgs/mouselogitech.jpg','Logitech G502 HERO High Performance Wired Gaming Mouse',50),(12,2,'- 6400 DPI Optical Sensor/- 6400 DPI Optical Sensor/- Rubber Side Grips ','/imgs/mouserazer.jpg','Razer DeathAdder Essential Gaming Mouse',29),(13,2,'- Backlit RGB LED/- 18000 DPI/- Optical','/imgs/mousecorsair.jpg','Corsair Scimitar Wireless Gaming Mouse',69),(14,2,'- 400+ Hour Battery Life/- 60 Million Clicks/- 18,000 CPI TrueMove Air Optical Sensor','/imgs/mousesteelseries.jpg','SteelSeries Rival 3 Wireless Gaming Mouse',36),(15,2,'- Tri-mode Connectivity (2.4GHz RF, Bluetooth, Wired)/- 36000 DPI Sensor/- Detachable Joystick','/imgs/mouseasus.jpg','ASUS ROG Chakram X Gaming Mouse',148),(16,2,'- Ultra-Lightweight 59g/- Honeycomb Shell/- Up to 16000 DPI','/imgs/mousehyperx.jpg','HyperX Pulsefire Haste - Gaming Mouse',40),(17,2,'- 19,000 DPI/- 2.4GHz and Bluetooth/- RGB Lighting and MasterPlus+ Software','/imgs/mouserapoo.jpg','Rapoo VT200 Wireless Gaming Mouse',69),(18,2,'- 65g Lightweight Design/- 12,000 DPI Optical Sensor/- 6 Programmable Buttons','/imgs/mousedareu.jpg','DAREU Wired Gaming Mouse',15),(19,2,'- 12800DPI Optical Sensor/- 6 Programmable Buttons/- Customizable RGB Honeycomb Mouse','/imgs/mouselecoo.jpg','Lecoo MS108 Gaming Mouse',30),(20,2,'- 3360 Sensor/- Ideal for Right-Handed/- 3200 DPI','/imgs/mousezowie.jpg','BenQ EC2-C Mouse',107),(21,3,'- THX Spatial Audio/- Compact Design/- Bluetooth 5.2 for PC, Smartphones, Tablets & Nintendo Switch','/imgs/speakersrazer.jpg','Razer Leviathan V2: Multi-Driver PC Gaming Soundbar with Subwoofer',249),(22,3,'- Full Stereo Sound/- Strong Bass/- Strong Bass','/imgs/speakerslogitech.png','Logitech Z130 PC Speakers',19),(23,3,'- 3.5mm AUX & PC Input','/imgs/speakersankere.jpg','Anker Soundcore Pro - A3142',149),(24,3,'- 2.0 Multimedia Speaker System/- BasXPort Technology','/imgs/speakersdenon.jpg','Denon Home 250',99),(25,3,'- Premium Drivers and Build/- Advanced Wired Connectivity','/imgs/speakersmicrolab.jpg','Microlab TMN9-BT (TMN9BT)',79),(26,3,'- 3/4 Silk Dome Tweeter/- Class D Amplifier/- Built-in USB DAC','/imgs/speakershivi.jpg','HiVi Swan D1100',229),(27,3,'- High-Performance USB-powered (5V)/- Simple USB plug-and-play setup (no drivers needed)','/imgs/speakershomesound.jpg','Homesound TH-111 2.0',17),(28,3,'- 3.5mm Audio/- Magnetically shielded/- Headphone output jack','/imgs/speakersedifier.png','Edifier R1080BT',80),(29,3,'- Volume Control/- 3.5 mm Aux Audio Plug','/imgs/speakersgenius.png','Genius SP-HF160 - 2.0',13),(30,3,'- Powerful Bass/- Effortless Mounting Swivel Brackets/- All Weather Resistance','/imgs/speakersklipsch.jpg','Klipsch ProMedia Heritage - 2.1',49),(31,4,'- 32GB 2666MT/- DDR4 CL16 Desktop Memory Single Module/- DDR4 CL16 Desktop Memory Single Module','/imgs/ramkingston.jpg','Kingston FURY Beast RGB',74),(32,4,'- 32GB (2X16GB)/- DDR4 3200 (PC4-25600) C16 1.35V Desktop Memory','/imgs/ramcorsair.jpg','Corsair Vengeance LPX',64),(33,4,'- 32GB (2x16GB)/- DDR4 RAM 3600MHz CL18 Desktop Memory','/imgs/ramlexar.jpg','Lexar Hades RGB',74),(34,4,'- 8GB/- DDR3/- 1600Mhz','/imgs/ramkingfast.jpg','Kingfast (KF1600DDAD3-8GB)',160),(35,4,'- 2 x 8 GB/- 240-Pin SDRAM (PC3-12800) DDR3/- 1600 MHz','/imgs/ramgskill.jpeg','G.Skill Ripjaws X Series 16 GB',56),(36,4,'- DDR4/- 3200Mhz/- 32GB (2x16GB)','/imgs/ramklevv.jpg','Klevv CRAS X RGB (KD4AGU880-32A160X)',130),(37,4,'- DDR5/- 64GB (2 x 32GB)/- 5200MHz','/imgs/ramteamgroup.jpg','TEAMGROUP VULCAN (FLRD532G5200HC40CDC01)',154),(38,4,'- DDR5/- 32GB (2x16GB) UDIMM Memory Kit/- 5200Mhz','/imgs/ramadata.jpg','Adata (AX5U5200C3816G-DCLABK)',145),(39,4,'- 32GB (2x16GB)/- DDR5/- 5200Mhz','/imgs/ramgigabyte.jpg','Gigabyte AORUS (GP-ARS32G52D5)',195),(40,4,'- 16GB (1x16GB)/- DDR4/- 2666Mhz','/imgs/ramkingmax.jpg','Kingmax Zeus Dragon Red (KM-LD4-2666-16GH)',55),(41,5,'- 4.5 GHz Upto 5.4GHz/- 40MB/- 8 Cores, 16 Threads/- 105W/- Socket AM5','/imgs/amdcpu1.jpg','CPU AMD Ryzen 7 7700X',404),(42,5,'- 4.5 GHz Upto 5.7GHz/- 81MB/- 16 Cores, 32 Threads/- 170W/- Socket AM5','/imgs/amdcpu2.jpg','CPU AMD Ryzen 9 7950X',698),(43,5,'- 3.7 GHz turbo upto 4.2GHz/- 11MB/- 6 Cores, 12 Threads/- 65W/- Socket AM4','/imgs/amdcpu3.jpg','CPU AMD Ryzen 5 4600G',127),(44,5,'- 4.2Ghz up to 5.0Ghz/- 105MB/- 8 cores 16 threads/- 120W/- Socket AM5','/imgs/amdcpu4.jpg','CPU AMD Ryzen 7 7800X3D',506),(45,5,'- 4.7 GHz Upto 5.3GHz/- 38MB/- 6 Cores, 12 Threads/- 105W/- Socket AM5','/imgs/amdcpu5.jpg','CPU AMD Ryzen 5 7600X',289),(46,5,'- 2.8GHz turbo up to 4.8Ghz/- 6 cores 12 threads/- 12MB Cache/- 65W','/imgs/intelcpu1.jpg','CPU Intel Core i5-11600 - Socket Intel LGA 1200',229),(47,5,'- 3.0GHz turbo up to 5.8Ghz/- 24 cores 32 threads/- 32MB Cache/- 125W)','/imgs/intelcpu2.jpg','CPU Intel Core i9-13900K - Socket Intel LGA 1700/Raptor Lake',707),(48,5,'- 3.4GHz turbo up to 5.4Ghz/- 16 cores 24 threads/- 24MB Cache/- 125W','/imgs/intelcpu3.jpg','CPU Intel Core i7-13700K - Socket Intel LGA 1700/Raptor Lake',477),(49,5,'- 2.9GHz turbo up to 4.8GHz/- 8 cores 16 threads/- 16MB Cache/- 65W','/imgs/intelcpu4.jpg','CPU Intel Core i7-10700 - Socket Intel LGA 1200',281),(50,5,'- 4.1GHz/- 2 cores 4 threads/- 4MB Cache/- 58W','/imgs/intelcpu5.jpg','CPU Intel Pentium Gold G6405 - Socket Intel LGA 1200',68);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-07 23:46:29
