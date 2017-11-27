-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- 主機: 127.0.0.1
-- 產生時間： 2017-11-27 11:27:03
-- 伺服器版本: 10.1.24-MariaDB
-- PHP 版本： 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `test`
--

-- --------------------------------------------------------

--
-- 資料表結構 `product`
--

CREATE TABLE `product` (
  `ORDERNO` char(30) NOT NULL,
  `ORDERSN` int(4) NOT NULL,
  `CASENUMBER` char(20) DEFAULT NULL,
  `CUSTOMER` char(30) DEFAULT NULL,
  `QTY` int(10) DEFAULT NULL,
  `UNIT` char(4) DEFAULT NULL,
  `PRODUCT` char(40) DEFAULT NULL,
  `PRODUCTTYPE` char(40) DEFAULT NULL,
  `PRODUCTSIZE` char(30) DEFAULT NULL,
  `TAGNO` char(30) DEFAULT NULL,
  `TESTPRESSURE` char(30) DEFAULT NULL,
  `DEPT` char(40) DEFAULT NULL,
  `WORKITEM` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `product`
--

INSERT INTO `product` (`ORDERNO`, `ORDERSN`, `CASENUMBER`, `CUSTOMER`, `QTY`, `UNIT`, `PRODUCT`, `PRODUCTTYPE`, `PRODUCTSIZE`, `TAGNO`, `TESTPRESSURE`, `DEPT`, `WORKITEM`) VALUES
('220-20101023005-0002', 1, '6-DL1RD9-00', '塑化麥寮', 1, 'PCS', 'P8700015DE340', '870閥瓣座', '6\"', '', '', '售服組', ''),
('222-20100101001-0001', 1, '09P0146A-M0001', '俊鼎機械廠', 2, 'PCS', 'G9400040131363PFAB', 'VF-940', '16\"', '', 'ANSI 300LB', '內銷營業部', ''),
('222-20100101001-0002', 1, '09P0146A-M0001', '俊鼎機械廠', 1, 'PCS', 'XA02C007RC280SR_CLOSE_60PSI', '', '', '', '依客戶需求', '內銷營業部', ''),
('222-20100106012-0001', 1, '08P1300M-F0170', '中鼎工程股份有限公司', 1, 'PCS', 'G9100010131334PFAL', 'VF-910', '4\"', '', '', '內銷營業部', ''),
('222-20100110003-0001', 1, 'N5955CX21A-XR 970船', '台船高雄', 3, 'PCS', 'G7332005D51334NBTL', 'VF-733', '2\"', '', 'JIS 5K', '內銷營業部', ''),
('222-20100110003-0002', 1, 'N5955CX21A-XR 970船', '台船高雄', 1, 'PCS', 'G7332010D51334NBUG', 'VF-733', '4\"', '', 'JIS 5K', '內銷營業部', ''),
('222-20101231004-0001', 1, '0991116002', '向熙科技', 1, 'PCS', 'P9100025STPE0', '910閥座', '10\"', '', '', '內銷營業部', ''),
('223-20100105004-0001', 1, 'GI1001/B-217', 'GIOXAS S.A.', 100, 'PCS', 'G7303008F01410NBAL', 'VF-730', '3\"', '', '', '外銷營業部', ''),
('224-20100101001-0003', 1, '09P0146A-M0001', '俊鼎機械廠', 3, 'PCS', 'XA24C031UFR/L-04NCDAL', '', '', '', '', '內銷營業部', ''),
('K220-20100621014-0002', 1, '1-CL6LB5-00', '台塑', 3, 'PCS', 'KA10M0137341PAN2PNMO-HZ09-P3AC110V', '', '', '', '', '佳和', ''),
('K220-20100622001-0001', 1, 'FH160168', '遠紡', 5, 'PCS', 'G7303025D51334NBAG', 'VF-730', '10\"', '', '', '佳和', ''),
('K221-20100106001-0001', 1, '201001060004', '捷流蘇州', 15, 'PCS', 'P7302050DCD50', '730閥瓣', '20\"', '', '', '石化組', ''),
('K221-20100711012-0001', 1, 'FH160234', '遠紡', 1, 'PCS', 'G9100015131334TFAG', 'VF-910', '6\"', '', 'JIS 10K', '佳和', ''),
('P16076', 1, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '管路拆裝前'),
('P16076', 2, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '回廠維修前'),
('P16076', 3, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '維修前內漏測試-儀器數值'),
('P16076', 4, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '維修前外漏測試-閥組'),
('P16076', 5, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '操作器噴砂後'),
('P16076', 6, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '閥體分解'),
('P16076', 7, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '閥座受損'),
('P16076', 8, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '閥座研磨-塗覆研磨膏(需放置研磨外盒)'),
('P16076', 9, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '閥座研磨後'),
('P16076', 10, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '閥體組裝'),
('P16076', 11, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '閥體,操作器組裝測試'),
('P16076', 12, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '定位器調整測試(8mA)'),
('P16076', 13, 'RATM02E', '台中川普', 1, 'PCS', '', '', '6\"', 'FV-41007', '300LB', '', '定位器調整測試(16mA)');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ORDERNO`,`ORDERSN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
