DROP DATABASE IF EXISTS employee_attendance;
CREATE DATABASE employee_attendance;
USE employee_attendance;

CREATE TABLE Department (
  DepartmentID INT AUTO_INCREMENT PRIMARY KEY,
  Department_name VARCHAR(100) NOT NULL UNIQUE,
  Location VARCHAR(100),
  ManagerEmployeeID INT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE UserLogin (
  UserID INT AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR(50) NOT NULL UNIQUE,
  PasswordHash VARCHAR(255) NOT NULL,
  Role ENUM('Admin','Manager','Employee') NOT NULL DEFAULT 'Employee',
  Last_login DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Employee (
  EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(100) NOT NULL,
  Ssn VARCHAR(50) UNIQUE,
  Address VARCHAR(255),
  Email VARCHAR(100) UNIQUE,
  Salary DOUBLE,
  Sex ENUM('Male','Female','Other'),
  Birthday DATE,
  DepartmentID INT,
  UserID INT UNIQUE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID) ON DELETE SET NULL,
  FOREIGN KEY (UserID) REFERENCES UserLogin(UserID) ON DELETE SET NULL
);

CREATE TABLE Shift (
  ShiftID INT AUTO_INCREMENT PRIMARY KEY,
  Shift_name VARCHAR(100) NOT NULL UNIQUE,
  Start_time TIME NOT NULL,
  End_time TIME NOT NULL
);

CREATE TABLE Holiday (
  HolidayID INT AUTO_INCREMENT PRIMARY KEY,
  HolidayName VARCHAR(100) NOT NULL UNIQUE,
  HolidayDate DATE NOT NULL,
  Description VARCHAR(255)
);

CREATE TABLE LeaveType (
  LeaveTypeID INT AUTO_INCREMENT PRIMARY KEY,
  LeaveType_name VARCHAR(100) NOT NULL UNIQUE,
  MaxDaysAllowed INT DEFAULT 0
);

CREATE TABLE LeaveRecord (
  LeaveID INT AUTO_INCREMENT PRIMARY KEY,
  EmployeeID INT NOT NULL,
  LeaveTypeID INT,
  Start_date DATE NOT NULL,
  End_date DATE NOT NULL,
  Reason VARCHAR(255),
  Status ENUM('Pending','Approved','Rejected') DEFAULT 'Pending',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE,
  FOREIGN KEY (LeaveTypeID) REFERENCES LeaveType(LeaveTypeID) ON DELETE SET NULL,
  CHECK (End_date >= Start_date)
);

CREATE TABLE Attendance (
  AttendanceID INT AUTO_INCREMENT PRIMARY KEY,
  HolidayID INT NULL,
  EmployeeID INT NOT NULL,
  WorkDate DATE NOT NULL,
  ShiftID INT,
  CheckIn DATETIME NULL,
  CheckOut DATETIME NULL,
  Status ENUM('Present','Absent','Leave','Holiday','Late') DEFAULT 'Present',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY ux_emp_date (EmployeeID, WorkDate),
  FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE,
  FOREIGN KEY (ShiftID) REFERENCES Shift(ShiftID) ON DELETE SET NULL,
  FOREIGN KEY (HolidayID) REFERENCES Holiday(HolidayID) ON DELETE SET NULL
);

ALTER TABLE Department
ADD CONSTRAINT fk_department_manager 
    FOREIGN KEY (ManagerEmployeeID) REFERENCES Employee(EmployeeID) ON DELETE SET NULL,
ADD CONSTRAINT uq_department_manager 
    UNIQUE (ManagerEmployeeID);

