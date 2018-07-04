-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 04. Jul 2018 um 18:13
-- Server-Version: 10.0.32-MariaDB
-- PHP-Version: 5.6.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET GLOBAL time_zone = '+00:00';


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `TimeTrackerAppDB`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `activity`
--

CREATE TABLE `activity` (
  `activityID` int(4) NOT NULL,
  `activityName` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `startTimeStamp` varchar(50) NOT NULL,
  `endTimeStamp` varchar(50) NOT NULL,
  `taskID` int(4) NOT NULL,
  `userID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `project`
--

CREATE TABLE `project` (
  `projectID` int(4) NOT NULL,
  `projectName` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  `projectManagerID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projectMember`
--

CREATE TABLE `projectMember` (
  `projectMemberID` int(4) NOT NULL,
  `userID` int(4) NOT NULL,
  `projectID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `task`
--

CREATE TABLE `task` (
  `taskID` int(4) NOT NULL COMMENT 'ID',
  `taskName` varchar(50) NOT NULL COMMENT 'Name',
  `description` varchar(500) NOT NULL COMMENT 'Plan (Minute)',
  `taskStateID` int(4) NOT NULL,
  `plannedTimeMinutes` bigint(64) NOT NULL COMMENT 'Plan (Hour)',
  `projectID` int(4) NOT NULL,
  `taskGroupID` int(4) NOT NULL COMMENT 'ID (Taskgroup)',
  `taskOwnerID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `taskGroup`
--

CREATE TABLE `taskGroup` (
  `taskGroupID` int(4) NOT NULL,
  `taskGroupName` varchar(50) NOT NULL,
  `ProjectID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `userID` int(4) NOT NULL COMMENT 'ID',
  `firstName` varchar(50) NOT NULL COMMENT 'Firstname',
  `lastName` varchar(50) NOT NULL COMMENT 'Lastname',
  `username` varchar(50) NOT NULL COMMENT 'Username',
  `password` varchar(50) NOT NULL COMMENT 'Password',
  `businessRoleID` int(4) NOT NULL COMMENT 'BusinessRole'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`activityID`);

--
-- Indizes für die Tabelle `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`projectID`);

--
-- Indizes für die Tabelle `projectMember`
--
ALTER TABLE `projectMember`
  ADD PRIMARY KEY (`projectMemberID`);

--
-- Indizes für die Tabelle `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`taskID`);

--
-- Indizes für die Tabelle `taskGroup`
--
ALTER TABLE `taskGroup`
  ADD PRIMARY KEY (`taskGroupID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `activity`
--
ALTER TABLE `activity`
  MODIFY `activityID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `project`
--
ALTER TABLE `project`
  MODIFY `projectID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `projectMember`
--
ALTER TABLE `projectMember`
  MODIFY `projectMemberID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `task`
--
ALTER TABLE `task`
  MODIFY `taskID` int(4) NOT NULL AUTO_INCREMENT COMMENT 'ID';
--
-- AUTO_INCREMENT für Tabelle `taskGroup`
--
ALTER TABLE `taskGroup`
  MODIFY `taskGroupID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(4) NOT NULL AUTO_INCREMENT COMMENT 'ID';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
