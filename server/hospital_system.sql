-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2020 at 01:35 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `checkup`
--

CREATE TABLE `checkup` (
  `id` int(11) NOT NULL,
  `patientid` int(11) DEFAULT NULL,
  `doctorid` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `status` varchar(55) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `checkup`
--

INSERT INTO `checkup` (`id`, `patientid`, `doctorid`, `reason`, `diagnosis`, `status`, `date`) VALUES
(11, 2, 2, 'Infection.', 'ear drops', 'incomplete', '2021-10-29'),
(19, 5, 2, 'Pulmonary Problem', 'drink more water', 'incomplete', '2021-10-29');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `name` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(1, 'ENT'),
(2, 'GENERAL'),
(3, 'RECEPTIONIST');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `fname` varchar(55) DEFAULT NULL,
  `lname` varchar(55) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `address` varchar(55) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `fname`, `lname`, `phone`, `address`, `dob`, `gender`) VALUES
(1, 'john', 'doe', 5123123, 'Curepipe', '2020-12-24', 'male'),
(2, 'lara', 'croft', 5342123, 'Savanne', '2020-08-12', 'female'),
(5, 'Johnny', 'English', 56974412, 'Reduit ', '1978-03-23', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `specialtreatment`
--

CREATE TABLE `specialtreatment` (
  `id` int(11) NOT NULL,
  `checkupid` int(11) DEFAULT NULL,
  `specialistid` int(11) DEFAULT NULL,
  `giventreatment` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(55) DEFAULT NULL,
  `departmentid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialtreatment`
--

INSERT INTO `specialtreatment` (`id`, `checkupid`, `specialistid`, `giventreatment`, `date`, `status`, `departmentid`) VALUES
(29, 19, NULL, NULL, '2021-10-29', 'Incomplete', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fname` varchar(55) DEFAULT NULL,
  `lname` varchar(55) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `address` varchar(55) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(55) DEFAULT NULL,
  `departmentid` int(11) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fname`, `lname`, `phone`, `address`, `dob`, `gender`, `departmentid`, `email`, `password`) VALUES
(1, 'Adel', 'Rose', 5123123, 'Curepipe', '2020-12-24', 'male', 1, 'ent@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b'),
(2, 'Kaviraj', 'Gosaye', 5342123, 'Savanne', '2020-08-12', 'male', 2, 'general@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b'),
(3, 'Henri', 'Jason', 5342123, 'Savanne', '2020-08-12', 'male', 3, 'receptionist@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `checkup`
--
ALTER TABLE `checkup`
  ADD PRIMARY KEY (`id`),
  ADD KEY `patientid` (`patientid`),
  ADD KEY `doctorid` (`doctorid`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `specialtreatment`
--
ALTER TABLE `specialtreatment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `checkupid` (`checkupid`),
  ADD KEY `specialistid` (`specialistid`),
  ADD KEY `departmentid` (`departmentid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `departmentid` (`departmentid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `checkup`
--
ALTER TABLE `checkup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `specialtreatment`
--
ALTER TABLE `specialtreatment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `checkup`
--
ALTER TABLE `checkup`
  ADD CONSTRAINT `checkup_ibfk_1` FOREIGN KEY (`patientid`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `checkup_ibfk_2` FOREIGN KEY (`doctorid`) REFERENCES `users` (`id`);

--
-- Constraints for table `specialtreatment`
--
ALTER TABLE `specialtreatment`
  ADD CONSTRAINT `specialtreatment_ibfk_1` FOREIGN KEY (`checkupid`) REFERENCES `checkup` (`id`),
  ADD CONSTRAINT `specialtreatment_ibfk_2` FOREIGN KEY (`specialistid`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `specialtreatment_ibfk_3` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
