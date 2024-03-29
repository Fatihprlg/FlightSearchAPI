USE [master]
GO
/****** Object:  Database [flight_api_db]    Script Date: 1/26/2024 3:42:13 PM ******/
CREATE DATABASE [flight_api_db]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'flight_api_db', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\flight_api_db.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'flight_api_db_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\flight_api_db_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [flight_api_db] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [flight_api_db].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [flight_api_db] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [flight_api_db] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [flight_api_db] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [flight_api_db] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [flight_api_db] SET ARITHABORT OFF 
GO
ALTER DATABASE [flight_api_db] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [flight_api_db] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [flight_api_db] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [flight_api_db] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [flight_api_db] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [flight_api_db] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [flight_api_db] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [flight_api_db] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [flight_api_db] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [flight_api_db] SET  DISABLE_BROKER 
GO
ALTER DATABASE [flight_api_db] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [flight_api_db] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [flight_api_db] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [flight_api_db] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [flight_api_db] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [flight_api_db] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [flight_api_db] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [flight_api_db] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [flight_api_db] SET  MULTI_USER 
GO
ALTER DATABASE [flight_api_db] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [flight_api_db] SET DB_CHAINING OFF 
GO
ALTER DATABASE [flight_api_db] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [flight_api_db] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [flight_api_db] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [flight_api_db] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [flight_api_db] SET QUERY_STORE = ON
GO
ALTER DATABASE [flight_api_db] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [flight_api_db]
GO
/****** Object:  User [flightapi]    Script Date: 1/26/2024 3:42:13 PM ******/
CREATE USER [flightapi] FOR LOGIN [flightapi] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [flightapi]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [flightapi]
GO
USE [flight_api_db]
GO
/****** Object:  Sequence [dbo].[airport_seq]    Script Date: 1/26/2024 3:42:13 PM ******/
CREATE SEQUENCE [dbo].[airport_seq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 50
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
USE [flight_api_db]
GO
/****** Object:  Sequence [dbo].[flight_seq]    Script Date: 1/26/2024 3:42:13 PM ******/
CREATE SEQUENCE [dbo].[flight_seq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 50
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[Airport]    Script Date: 1/26/2024 3:42:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Airport](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[city] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Airport] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Flight]    Script Date: 1/26/2024 3:42:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Flight](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[departureAirportId] [int] NOT NULL,
	[landingAirportId] [int] NOT NULL,
	[departureTime] [datetime] NOT NULL,
	[returnTime] [datetime] NULL,
	[price] [float] NOT NULL,
 CONSTRAINT [PK_Flight] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Airport] ON 

INSERT [dbo].[Airport] ([id], [city]) VALUES (1, N'Izmir')
INSERT [dbo].[Airport] ([id], [city]) VALUES (2, N'Samsun')
INSERT [dbo].[Airport] ([id], [city]) VALUES (3, N'Istanbul')
INSERT [dbo].[Airport] ([id], [city]) VALUES (4, N'Ankara')
INSERT [dbo].[Airport] ([id], [city]) VALUES (5, N'Adana')
INSERT [dbo].[Airport] ([id], [city]) VALUES (6, N'Antalya')
INSERT [dbo].[Airport] ([id], [city]) VALUES (7, N'Rize')
INSERT [dbo].[Airport] ([id], [city]) VALUES (8, N'Trabzon')
INSERT [dbo].[Airport] ([id], [city]) VALUES (9, N'Diyarbakir')
INSERT [dbo].[Airport] ([id], [city]) VALUES (10, N'Van')
INSERT [dbo].[Airport] ([id], [city]) VALUES (11, N'Londra')
INSERT [dbo].[Airport] ([id], [city]) VALUES (12, N'Sharm El Sheikh')
INSERT [dbo].[Airport] ([id], [city]) VALUES (13, N'Berlin')
INSERT [dbo].[Airport] ([id], [city]) VALUES (14, N'Kibris')
INSERT [dbo].[Airport] ([id], [city]) VALUES (15, N'Dubai')
INSERT [dbo].[Airport] ([id], [city]) VALUES (16, N'Roma')
INSERT [dbo].[Airport] ([id], [city]) VALUES (17, N'San Francisco')
SET IDENTITY_INSERT [dbo].[Airport] OFF
GO
SET IDENTITY_INSERT [dbo].[Flight] ON 

INSERT [dbo].[Flight] ([id], [departureAirportId], [landingAirportId], [departureTime], [returnTime], [price]) VALUES (1, 11, 4, CAST(N'2024-06-21T05:08:21.980' AS DateTime), NULL, 950.56781005859375)
INSERT [dbo].[Flight] ([id], [departureAirportId], [landingAirportId], [departureTime], [returnTime], [price]) VALUES (2, 3, 6, CAST(N'2024-01-26T11:51:45.937' AS DateTime), CAST(N'2024-01-26T11:51:45.937' AS DateTime), 657)
INSERT [dbo].[Flight] ([id], [departureAirportId], [landingAirportId], [departureTime], [returnTime], [price]) VALUES (3, 2, 8, CAST(N'2024-01-26T11:00:00.207' AS DateTime), NULL, 435)
INSERT [dbo].[Flight] ([id], [departureAirportId], [landingAirportId], [departureTime], [returnTime], [price]) VALUES (4, 2, 7, CAST(N'2024-01-26T12:02:34.987' AS DateTime), CAST(N'2024-01-26T12:02:34.987' AS DateTime), 1450)
SET IDENTITY_INSERT [dbo].[Flight] OFF
GO
ALTER TABLE [dbo].[Flight] ADD  CONSTRAINT [DF_Flight_departureAirportId]  DEFAULT ((1)) FOR [departureAirportId]
GO
ALTER TABLE [dbo].[Flight] ADD  CONSTRAINT [DF_Flight_landingAirportId]  DEFAULT ((1)) FOR [landingAirportId]
GO
ALTER TABLE [dbo].[Flight]  WITH CHECK ADD  CONSTRAINT [FK_Flight_Airport_DepartureId] FOREIGN KEY([departureAirportId])
REFERENCES [dbo].[Airport] ([id])
GO
ALTER TABLE [dbo].[Flight] CHECK CONSTRAINT [FK_Flight_Airport_DepartureId]
GO
ALTER TABLE [dbo].[Flight]  WITH CHECK ADD  CONSTRAINT [FK_Flight_Airport_LandingId] FOREIGN KEY([landingAirportId])
REFERENCES [dbo].[Airport] ([id])
GO
ALTER TABLE [dbo].[Flight] CHECK CONSTRAINT [FK_Flight_Airport_LandingId]
GO
USE [master]
GO
ALTER DATABASE [flight_api_db] SET  READ_WRITE 
GO
