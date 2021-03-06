USE [MockProject_T4]
GO
/****** Object:  Table [dbo].[bookingoffice]    Script Date: 11/08/2021 22:49:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bookingoffice](
	[officeID] [bigint] IDENTITY(1,1) NOT NULL,
	[endContractDeadline] [date] NOT NULL,
	[officeName] [varchar](50) NOT NULL,
	[officePhone] [varchar](50) NULL,
	[officePlace] [varchar](50) NOT NULL,
	[officePrice] [bigint] NOT NULL,
	[startContractDeadline] [date] NOT NULL,
	[tripID] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[officeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bookofficeplace]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bookofficeplace](
	[officePlace] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[officePlace] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[car]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[car](
	[licensePlate] [varchar](50) NOT NULL,
	[carColor] [varchar](11) NOT NULL,
	[carType] [varchar](50) NOT NULL,
	[company] [varchar](50) NOT NULL,
	[parkID] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[licensePlate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[carcompany]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[carcompany](
	[company] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[company] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[employee]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[employee](
	[employeeID] [bigint] IDENTITY(1,1) NOT NULL,
	[account] [varchar](50) NOT NULL,
	[department] [varchar](10) NOT NULL,
	[employeeAddress] [varchar](50) NOT NULL,
	[employeeBirthdate] [date] NOT NULL,
	[employeeEmail] [varchar](50) NOT NULL,
	[employeeName] [varchar](50) NOT NULL,
	[employeePhone] [varchar](50) NOT NULL,
	[password] [varchar](20) NOT NULL,
	[sex] [varchar](1) NOT NULL,
 CONSTRAINT [PK__employee__C134C9A1F18A1176] PRIMARY KEY CLUSTERED 
(
	[employeeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[parkinglot]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[parkinglot](
	[parkID] [bigint] IDENTITY(1,1) NOT NULL,
	[parkArea] [bigint] NOT NULL,
	[parkName] [varchar](50) NOT NULL,
	[parkPlace] [varchar](11) NOT NULL,
	[parkPrice] [bigint] NOT NULL,
	[parkStatus] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[parkID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[parkinglotplace]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[parkinglotplace](
	[parkPlace] [varchar](11) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[parkPlace] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[permission]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[permission](
	[department] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[department] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ticket]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ticket](
	[ticketID] [bigint] IDENTITY(1,1) NOT NULL,
	[bookingTime] [time](7) NOT NULL,
	[customerName] [varchar](11) NOT NULL,
	[licensePlate] [varchar](50) NOT NULL,
	[tripID] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[ticketID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[trip]    Script Date: 11/08/2021 22:49:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[trip](
	[tripID] [bigint] IDENTITY(1,1) NOT NULL,
	[bookedTicketNumber] [int] NOT NULL,
	[carType] [varchar](11) NOT NULL,
	[departureDate] [date] NOT NULL,
	[departureTime] [time](7) NOT NULL,
	[destination] [varchar](50) NOT NULL,
	[driver] [varchar](11) NOT NULL,
	[maximumOnlineTicketNumber] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tripID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[bookingoffice] ON 

INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (3, CAST(N'2021-08-01' AS Date), N'F1', N'0906166559', N'Quay So 1', 1000, CAST(N'2021-08-12' AS Date), 2)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (4, CAST(N'2021-08-21' AS Date), N'F2', N'0906166559', N'Quay So 2', 1500, CAST(N'2021-08-01' AS Date), 2)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (7, CAST(N'2021-08-04' AS Date), N'F3', N'0906166559', N'Quay So 2', 1500, CAST(N'2021-08-20' AS Date), 2)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (8, CAST(N'2021-08-05' AS Date), N'F1', N'+10906166559', N'Quay So 1', 1000, CAST(N'2021-08-28' AS Date), 5)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (9, CAST(N'2021-08-13' AS Date), N'F23', N'+10906166559', N'Quay So 3', 1500, CAST(N'2021-08-26' AS Date), 4)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (10, CAST(N'2021-08-21' AS Date), N'F22', N'+10906166559', N'Quay So 3', 1500, CAST(N'2021-08-05' AS Date), 4)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (11, CAST(N'2021-08-07' AS Date), N'F15', N'0906166559', N'Quay So 3', 1000, CAST(N'2021-08-19' AS Date), 10004)
INSERT [dbo].[bookingoffice] ([officeID], [endContractDeadline], [officeName], [officePhone], [officePlace], [officePrice], [startContractDeadline], [tripID]) VALUES (12, CAST(N'2021-08-28' AS Date), N'F16', N'+10906166559', N'Quay So 1', 1000, CAST(N'2021-07-31' AS Date), 10004)
SET IDENTITY_INSERT [dbo].[bookingoffice] OFF
GO
INSERT [dbo].[bookofficeplace] ([officePlace]) VALUES (N'Quay So 1')
INSERT [dbo].[bookofficeplace] ([officePlace]) VALUES (N'Quay So 2')
INSERT [dbo].[bookofficeplace] ([officePlace]) VALUES (N'Quay So 3')
INSERT [dbo].[bookofficeplace] ([officePlace]) VALUES (N'Quay So 4')
GO
INSERT [dbo].[car] ([licensePlate], [carColor], [carType], [company], [parkID]) VALUES (N'AB123', N'White', N'Totyota', N'Tien Phat', 5)
INSERT [dbo].[car] ([licensePlate], [carColor], [carType], [company], [parkID]) VALUES (N'AB12322', N'Green', N'Totyota', N'Tien Phat', 5)
INSERT [dbo].[car] ([licensePlate], [carColor], [carType], [company], [parkID]) VALUES (N'AB1234', N'Blue', N'Totyota', N'Tien Phat', 2)
INSERT [dbo].[car] ([licensePlate], [carColor], [carType], [company], [parkID]) VALUES (N'AB1235', N'Blue', N'BMW', N'Tien Phat', 6)
INSERT [dbo].[car] ([licensePlate], [carColor], [carType], [company], [parkID]) VALUES (N'AB1236', N'Blue', N'BMW', N'Tien Phat', 6)
INSERT [dbo].[car] ([licensePlate], [carColor], [carType], [company], [parkID]) VALUES (N'bc', N'Green', N'Kosseign', N'Tien Phat', 6)
GO
INSERT [dbo].[carcompany] ([company]) VALUES (N'Dai Phong')
INSERT [dbo].[carcompany] ([company]) VALUES (N'Hoang Ha')
INSERT [dbo].[carcompany] ([company]) VALUES (N'Tien Phat')
INSERT [dbo].[carcompany] ([company]) VALUES (N'Truong Long')
GO
SET IDENTITY_INSERT [dbo].[employee] ON 

INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (20, N'hung1234', N'Employee', N'hpcity', CAST(N'2000-12-02' AS Date), N'hung.nt852@gmail.com', N'hungviplit123', N'+10906166556', N'Hung123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (21, N'hung12345', N'Parking', N'hpcity', CAST(N'2000-12-20' AS Date), N'hung.nt2000@gmail.com', N'hungpro', N'+10906166559', N'Hung123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (22, N'remylia2k000', N'Employee', N'hp', CAST(N'2000-12-20' AS Date), N'hung.nt19000@gmail.com', N'lil', N'+10906166559', N'2323', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (23, N'hungvip', N'Parking', N'hpcity', CAST(N'2000-12-20' AS Date), N'hungvip@gmail.com', N'hungvip', N'+10906166559', N'Hung123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (25, N'hungvip3', N'Parking', N'hp', CAST(N'2000-12-20' AS Date), N'hungvip3@gmail.com', N'hungvip3', N'+10906166559', N'Hung123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (20023, N'hung', N'Employee', N'hp', CAST(N'2000-12-20' AS Date), N'hung@gmail.com', N'nguyen thai hung', N'+10906166559', N'Hung123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (20024, N'an', N'Parking', N'hn', CAST(N'2000-12-20' AS Date), N'an123@gmail.com', N'Trong An', N'+10906166559', N'Trongan123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (20025, N'quy', N'Service', N'hncaptial', CAST(N'2000-12-20' AS Date), N'quy@gmail.com', N'Quang Quy', N'+10906166559', N'Quy123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (20026, N'haiha', N'Parking', N'hn', CAST(N'2000-12-20' AS Date), N'haiha@gmail.com', N'Hai Ha', N'+10906166559', N'Haiha123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (30023, N'abcxyz', N'Employee', N'hanoi', CAST(N'2000-12-20' AS Date), N'abcxyz@gmail.com', N'abcabc', N'+10906166559', N'Hung123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (30024, N'thanh123', N'Employee', N'ha noi', CAST(N'2021-08-20' AS Date), N'thanh123@gmail.com', N'thanh', N'+10906166559', N'Thanh123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (30025, N'trung', N'Employee', N'hp', CAST(N'2021-08-06' AS Date), N'trung@gmail.com', N'trung', N'+10906166559', N'Trung123', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (30026, N'sad123', N'Parking', N'dasd', CAST(N'2021-08-13' AS Date), N'sad123@gmail.com', N'sd', N'asdasd', N'Sad123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (30027, N'quy123', N'Employee', N'pt', CAST(N'2021-08-07' AS Date), N'quy123@gmail.com', N'Quang Quy', N'+10906166559', N'Quy123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (30028, N'hung23123', N'Parking', N'hp', CAST(N'2021-08-04' AS Date), N'hung.nt20@gmail.com', N'hungasdasd', N'+10906166559', N'Hung123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (40028, N'an321', N'Parking', N'hd', CAST(N'2021-08-06' AS Date), N'an321@gmail.com', N'an123', N'+10906166559', N'An12345', N'M')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (50028, N'antrong123', N'Parking', N'ha noi', CAST(N'2021-05-06' AS Date), N'an321123@gmail.com', N'nguyen trong an', N'+10906166559', N'Antrong123', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (50029, N'anan123', N'Parking', N'so 7 thien quang', CAST(N'2021-08-17' AS Date), N'anvip@gmail.com', N'Nguyen An', N'0123456789', N'AnAn13', N'F')
INSERT [dbo].[employee] ([employeeID], [account], [department], [employeeAddress], [employeeBirthdate], [employeeEmail], [employeeName], [employeePhone], [password], [sex]) VALUES (50030, N'hungnguyen', N'Parking', N'hp', CAST(N'2021-08-08' AS Date), N'hungnguyen@gmail.com', N'Nguyen Hung', N'+10906166559', N'Hung123', N'F')
SET IDENTITY_INSERT [dbo].[employee] OFF
GO
SET IDENTITY_INSERT [dbo].[parkinglot] ON 

INSERT [dbo].[parkinglot] ([parkID], [parkArea], [parkName], [parkPlace], [parkPrice], [parkStatus]) VALUES (2, 500, N'FPT', N'Khu Bac', 1205, N'Blank')
INSERT [dbo].[parkinglot] ([parkID], [parkArea], [parkName], [parkPlace], [parkPrice], [parkStatus]) VALUES (5, 5000, N'VNPT', N'Khu Bac', 1000, N'Full')
INSERT [dbo].[parkinglot] ([parkID], [parkArea], [parkName], [parkPlace], [parkPrice], [parkStatus]) VALUES (6, 5000, N'Hoa Lac', N'Khu Bac', 200, N'Blank')
INSERT [dbo].[parkinglot] ([parkID], [parkArea], [parkName], [parkPlace], [parkPrice], [parkStatus]) VALUES (7, 5000, N'VNPT2', N'Khu Nam', 1000, N'Blank')
INSERT [dbo].[parkinglot] ([parkID], [parkArea], [parkName], [parkPlace], [parkPrice], [parkStatus]) VALUES (10, 5000, N'VNPT4', N'Khu Bac', 1500, N'Blank')
SET IDENTITY_INSERT [dbo].[parkinglot] OFF
GO
INSERT [dbo].[parkinglotplace] ([parkPlace]) VALUES (N'Khu Bac')
INSERT [dbo].[parkinglotplace] ([parkPlace]) VALUES (N'Khu Dong')
INSERT [dbo].[parkinglotplace] ([parkPlace]) VALUES (N'Khu Nam')
INSERT [dbo].[parkinglotplace] ([parkPlace]) VALUES (N'Khu Tay')
GO
INSERT [dbo].[permission] ([department]) VALUES (N'Employee')
INSERT [dbo].[permission] ([department]) VALUES (N'Parking')
INSERT [dbo].[permission] ([department]) VALUES (N'Service')
GO
SET IDENTITY_INSERT [dbo].[ticket] ON 

INSERT [dbo].[ticket] ([ticketID], [bookingTime], [customerName], [licensePlate], [tripID]) VALUES (4, CAST(N'00:49:00' AS Time), N'hung', N'AB1235', 10004)
INSERT [dbo].[ticket] ([ticketID], [bookingTime], [customerName], [licensePlate], [tripID]) VALUES (5, CAST(N'23:59:00' AS Time), N'quy', N'bc', 10006)
INSERT [dbo].[ticket] ([ticketID], [bookingTime], [customerName], [licensePlate], [tripID]) VALUES (10004, CAST(N'15:17:00' AS Time), N'an', N'AB1234', 5)
INSERT [dbo].[ticket] ([ticketID], [bookingTime], [customerName], [licensePlate], [tripID]) VALUES (10005, CAST(N'15:16:00' AS Time), N'an', N'bc', 4)
INSERT [dbo].[ticket] ([ticketID], [bookingTime], [customerName], [licensePlate], [tripID]) VALUES (10006, CAST(N'15:16:00' AS Time), N'quy', N'bc', 10006)
SET IDENTITY_INSERT [dbo].[ticket] OFF
GO
SET IDENTITY_INSERT [dbo].[trip] ON 

INSERT [dbo].[trip] ([tripID], [bookedTicketNumber], [carType], [departureDate], [departureTime], [destination], [driver], [maximumOnlineTicketNumber]) VALUES (2, 15, N'BMW', CAST(N'2021-07-16' AS Date), CAST(N'10:07:00' AS Time), N'Ha Noi', N'Ha Noi', 10)
INSERT [dbo].[trip] ([tripID], [bookedTicketNumber], [carType], [departureDate], [departureTime], [destination], [driver], [maximumOnlineTicketNumber]) VALUES (4, 0, N'BMW', CAST(N'2021-08-12' AS Date), CAST(N'10:35:00' AS Time), N'TPHCM', N'hung', 15)
INSERT [dbo].[trip] ([tripID], [bookedTicketNumber], [carType], [departureDate], [departureTime], [destination], [driver], [maximumOnlineTicketNumber]) VALUES (5, 0, N'BMW', CAST(N'2021-07-31' AS Date), CAST(N'22:41:00' AS Time), N'Ha Noi', N'quy', 15)
INSERT [dbo].[trip] ([tripID], [bookedTicketNumber], [carType], [departureDate], [departureTime], [destination], [driver], [maximumOnlineTicketNumber]) VALUES (10004, 0, N'BMW', CAST(N'2021-08-12' AS Date), CAST(N'23:27:00' AS Time), N'Hai Phong', N'quy', 15)
INSERT [dbo].[trip] ([tripID], [bookedTicketNumber], [carType], [departureDate], [departureTime], [destination], [driver], [maximumOnlineTicketNumber]) VALUES (10006, 0, N'Totyota', CAST(N'2021-08-04' AS Date), CAST(N'12:07:00' AS Time), N'Da Lat', N'quy', 15)
SET IDENTITY_INSERT [dbo].[trip] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__employee__EA162E11BEE7EF96]    Script Date: 11/08/2021 22:49:43 PM ******/
ALTER TABLE [dbo].[employee] ADD  CONSTRAINT [UQ__employee__EA162E11BEE7EF96] UNIQUE NONCLUSTERED 
(
	[account] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[bookingoffice]  WITH CHECK ADD  CONSTRAINT [fk_bo_place] FOREIGN KEY([officePlace])
REFERENCES [dbo].[bookofficeplace] ([officePlace])
GO
ALTER TABLE [dbo].[bookingoffice] CHECK CONSTRAINT [fk_bo_place]
GO
ALTER TABLE [dbo].[bookingoffice]  WITH CHECK ADD  CONSTRAINT [fk_bo_tripid] FOREIGN KEY([tripID])
REFERENCES [dbo].[trip] ([tripID])
GO
ALTER TABLE [dbo].[bookingoffice] CHECK CONSTRAINT [fk_bo_tripid]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [fk_car_company] FOREIGN KEY([company])
REFERENCES [dbo].[carcompany] ([company])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [fk_car_company]
GO
ALTER TABLE [dbo].[car]  WITH CHECK ADD  CONSTRAINT [fk_car_parkid] FOREIGN KEY([parkID])
REFERENCES [dbo].[parkinglot] ([parkID])
GO
ALTER TABLE [dbo].[car] CHECK CONSTRAINT [fk_car_parkid]
GO
ALTER TABLE [dbo].[employee]  WITH CHECK ADD  CONSTRAINT [fk_emp_department] FOREIGN KEY([department])
REFERENCES [dbo].[permission] ([department])
GO
ALTER TABLE [dbo].[employee] CHECK CONSTRAINT [fk_emp_department]
GO
ALTER TABLE [dbo].[parkinglot]  WITH CHECK ADD  CONSTRAINT [fk_pl_place] FOREIGN KEY([parkPlace])
REFERENCES [dbo].[parkinglotplace] ([parkPlace])
GO
ALTER TABLE [dbo].[parkinglot] CHECK CONSTRAINT [fk_pl_place]
GO
ALTER TABLE [dbo].[ticket]  WITH CHECK ADD  CONSTRAINT [fk_ticket_licenseplate] FOREIGN KEY([licensePlate])
REFERENCES [dbo].[car] ([licensePlate])
GO
ALTER TABLE [dbo].[ticket] CHECK CONSTRAINT [fk_ticket_licenseplate]
GO
ALTER TABLE [dbo].[ticket]  WITH CHECK ADD  CONSTRAINT [fk_ticket_tripid] FOREIGN KEY([tripID])
REFERENCES [dbo].[trip] ([tripID])
GO
ALTER TABLE [dbo].[ticket] CHECK CONSTRAINT [fk_ticket_tripid]
GO
