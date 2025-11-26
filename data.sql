
INSERT INTO Department (DepartmentID, Department_name, Location) VALUES 
(1, 'IT', 'Floor 1'), 
(2, 'HR', 'Floor 2'), 
(3, 'Sales', 'Floor 3'), 
(4, 'Finance', 'Floor 4'), 
(5, 'Marketing', 'Floor 5');


INSERT INTO UserLogin (UserID, Username, PasswordHash, Role, Last_login) VALUES
(1, 'nnahuy', '123456', 'Admin', NOW()),
(2, 'ndhai', '123456', 'Employee', NOW()), 
(3, 'ntan', '123456', 'Manager', NOW()), 
(4, 'ntmthu', '123456', 'Employee', NOW()),
(5, 'nnanh', '123456', 'Employee', NOW()),
(6, 'nmhieu', '123456', 'Employee', NOW());

INSERT INTO Employee (EmployeeID, Name, Address, Salary, Email, Sex, Birthday, DepartmentID, UserID) VALUES
(1, 'Nguyễn Nhật Anh Huy', 'Ho Chi Minh', 1500.09, 'ITITWE23031@gmail.com', 'Male', '2003-01-01', 1, 1),
(2, 'Nguyễn Đức Hải', 'Ha Noi', 1859.20, 'ITDSIU23006@gmail.com', 'Male', '2003-01-01', 1, 2),
(3, 'Nguyễn Tâm An', 'Can Tho', 2209.80, 'ITITDK23036@gmail.com', 'Male', '2003-01-01', 2, 3),
(4, 'Nguyễn Trần Minh Thư', 'Hai Phong', 2100.25, 'ITITWE24025@gmail.com', 'Female', '2004-01-01', 3, 4),
(5, 'Nguyễn Nhật Anh', 'Hue', 1472.88, 'ITITWE23045@gmail.com', 'Male', '2003-01-01', 4, 5),
(6, 'Nguyễn Minh Hiếu', 'Ca Mau', 1697.23, 'ITITDK23019@gmail.com', 'Male', '2003-01-01', 5, 6);


INSERT INTO Shift (ShiftID, Shift_name, Start_time, End_time) VALUES
(1, 'Day', '09:00:00', '17:30:00'),
(2, 'Night', '22:00:00', '06:00:00'),
(3, 'Morning', '07:00:00', '15:00:00'),
(4, 'Evening', '13:00:00', '21:00:00');


INSERT INTO Holiday (HolidayDate, HolidayName, Description) VALUES
('2025-01-01', 'New Year', 'Nam moi'),
('2025-04-30', 'Reunification Day', 'Giai phong mien Nam'),
('2025-05-01', 'International Labor Day', 'Quoc te lao dong'),
('2025-09-02', 'National Day', 'Quoc khanh');


INSERT INTO LeaveType (LeaveTypeID, LeaveType_name, MaxDaysAllowed) VALUES
(1, 'Sick', 12),
(2, 'Annual', 12),
(3, 'Unpaid', 0),
(4, 'Maternity', 180);


INSERT INTO Attendance (EmployeeID, WorkDate, ShiftID, Status, CheckIn, CheckOut) VALUES
(1, '2025-09-22', 1, 'Present', '2025-09-22 09:00:00', '2025-09-22 17:30:00'),
(2, '2025-09-22', 1, 'Present', '2025-09-22 09:00:00', '2025-09-22 17:30:00'),
(3, '2025-09-22', 1, 'Absent', NULL, NULL),
(4, '2025-09-22', 3, 'Present', '2025-09-22 07:00:00', '2025-09-22 15:00:00'),
(5, '2025-09-22', 1, 'Present', '2025-09-22 09:00:00', '2025-09-22 17:30:00');


INSERT INTO LeaveRecord (EmployeeID, LeaveTypeID, Start_date, End_date, Status, Reason) VALUES
(2, 1, '2025-09-25', '2025-09-25', 'Approved', 'Personal'),
(5, 2, '2025-09-24', '2025-09-26', 'Pending', 'Sick'),
(6, 3, '2025-09-23', '2025-09-23', 'Approved', 'Family');