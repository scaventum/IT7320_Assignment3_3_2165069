-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2017 at 10:50 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_nanolib`
--

-- --------------------------------------------------------

--
-- Table structure for table `log_activity`
--

CREATE TABLE `log_activity` (
  `LogID` bigint(20) NOT NULL,
  `TransID` varchar(50) NOT NULL,
  `LogModule` varchar(50) NOT NULL,
  `LogAction` varchar(50) NOT NULL,
  `LogTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_activity`
--

INSERT INTO `log_activity` (`LogID`, `TransID`, `LogModule`, `LogAction`, `LogTime`) VALUES
(1, 'ADM', 'User', 'Insert', '2017-10-08 00:00:00'),
(2, 'M00001', 'Member', 'Insert', '2017-10-08 00:00:00'),
(3, 'M00002', 'Member', 'Insert', '2017-10-08 00:00:00'),
(4, '0-472-06521-1', 'Book', 'Insert', '2017-10-08 00:00:00'),
(5, '978-0-478-39455-9', 'Book', 'Insert', '2017-10-08 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `ms_book`
--

CREATE TABLE `ms_book` (
  `ISBN` varchar(50) NOT NULL,
  `BookTitle` varchar(200) NOT NULL,
  `BookEdition` int(200) NOT NULL,
  `BookDescription` text NOT NULL,
  `BookReleaseDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_book`
--

INSERT INTO `ms_book` (`ISBN`, `BookTitle`, `BookEdition`, `BookDescription`, `BookReleaseDate`) VALUES
('0-472-06521-1', 'Simulacra and Simulation', 1, 'Simulacra and Simulation is most known for its discussion of symbols, signs, and how they relate to contemporaneity (simultaneous existences). Baudrillard claims that our current society has replaced all reality and meaning with symbols and signs, and that human experience is a simulation of reality. Moreover, these simulacra are not merely mediations of reality, nor even deceptive mediations of reality; they are not based in a reality nor do they hide a reality, they simply hide that nothing like reality is relevant to our current understanding of our lives. The simulacra that Baudrillard refers to are the significations and symbolism of culture and media that construct perceived reality, the acquired understanding by which our lives and shared existence is and are rendered legible; Baudrillard believed that society has become so saturated with these simulacra and our lives so saturated with the constructs of society that all meaning was being rendered meaningless by being infinitely mutable. Baudrillard called this phenomenon the precession of simulacra.', '1994-01-01'),
('978-0-478-39455-9', 'The Official New Zealand Road Code', 13, 'Be prepared – set yourself up to pass first time. The official New Zealand road code is a user-friendly guide to New Zealand\'s traffic law and safe driving practices. It includes the questions that you could be asked when you sit the theory test for your learner license.', '2012-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `ms_member`
--

CREATE TABLE `ms_member` (
  `MemID` varchar(50) NOT NULL,
  `MemFName` varchar(50) NOT NULL,
  `MemLName` varchar(50) NOT NULL,
  `MemGender` varchar(50) NOT NULL,
  `MemDOB` date NOT NULL,
  `MemAddress` varchar(200) NOT NULL,
  `MemEmail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_member`
--

INSERT INTO `ms_member` (`MemID`, `MemFName`, `MemLName`, `MemGender`, `MemDOB`, `MemAddress`, `MemEmail`) VALUES
('M00001', 'Chester', 'Bennington', 'M', '1980-10-10', 'Aguora Hills, California, US', 'cb@lp.com'),
('M00002', 'Mike', 'Shinoda', 'M', '1980-10-11', 'Aguora Hills, California, US', 'ms@lp.com');

-- --------------------------------------------------------

--
-- Table structure for table `ms_serial`
--

CREATE TABLE `ms_serial` (
  `ISBN` varchar(50) NOT NULL,
  `Serial` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_serial`
--

INSERT INTO `ms_serial` (`ISBN`, `Serial`) VALUES
('0-472-06521-1', 1),
('0-472-06521-1', 2),
('0-472-06521-1', 3),
('0-472-06521-1', 4),
('0-472-06521-1', 5),
('978-0-478-39455-9', 1),
('978-0-478-39455-9', 2),
('978-0-478-39455-9', 3);

-- --------------------------------------------------------

--
-- Table structure for table `ms_user`
--

CREATE TABLE `ms_user` (
  `UserID` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `UserFName` varchar(50) NOT NULL,
  `UserLName` varchar(50) NOT NULL,
  `UserGender` varchar(50) NOT NULL,
  `UserDOB` date NOT NULL,
  `UserAddress` varchar(200) NOT NULL,
  `UserEmail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_user`
--

INSERT INTO `ms_user` (`UserID`, `Password`, `UserFName`, `UserLName`, `UserGender`, `UserDOB`, `UserAddress`, `UserEmail`) VALUES
('ADM', '12345', 'Adamantio', 'Scaventum', 'M', '1990-02-11', '276th Broken Dreams Blvd., Scavenport North, NZ', 'adm.nanolib@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tr_borrowing`
--

CREATE TABLE `tr_borrowing` (
  `BorID` varchar(50) NOT NULL,
  `MemID` varchar(50) NOT NULL,
  `ISBN` varchar(50) NOT NULL,
  `Serial` int(11) NOT NULL,
  `Duration` int(11) NOT NULL,
  `BorDate` date NOT NULL,
  `BorReturnDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `log_activity`
--
ALTER TABLE `log_activity`
  ADD PRIMARY KEY (`LogID`);

--
-- Indexes for table `ms_book`
--
ALTER TABLE `ms_book`
  ADD PRIMARY KEY (`ISBN`);

--
-- Indexes for table `ms_member`
--
ALTER TABLE `ms_member`
  ADD PRIMARY KEY (`MemID`);

--
-- Indexes for table `ms_serial`
--
ALTER TABLE `ms_serial`
  ADD PRIMARY KEY (`ISBN`,`Serial`);

--
-- Indexes for table `ms_user`
--
ALTER TABLE `ms_user`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `tr_borrowing`
--
ALTER TABLE `tr_borrowing`
  ADD PRIMARY KEY (`BorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `log_activity`
--
ALTER TABLE `log_activity`
  MODIFY `LogID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
