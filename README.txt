========================================================================
EMPLOYEE ATTENDANCE MANAGEMENT SYSTEM - FRONTEND
========================================================================

1. OVERVIEW
------------------------------------------------------------------------
This is the frontend client for the Employee Attendance Management System. 
It is a Single Page Application (SPA) style project built with Vanilla 
JavaScript (ES Modules), HTML5, and CSS. It communicates with a backend 
API (presumed Java/Spring Boot) via RESTful endpoints.

The application handles user authentication, employee management, 
attendance tracking, department organization, shift management, and 
leave requests.

2. PREREQUISITES
------------------------------------------------------------------------
1. Node.js,Online Python 3 Compiler (Required to run the frontend server script `server.js`).
2. Backend API Server: 
   - Must be running on http://localhost:8081
   - Must expose endpoints at /api/...
3. A modern web browser (Chrome, Edge, Firefox,...) supporting ES Modules.

3. DIRECTORY STRUCTURE
------------------------------------------------------------------------
Ensure your files are organized in the following folder structure for the 
links and scripts to work correctly:

[Root Directory]
 ├── server.js                  (Node.js static server script)
 ├── UserLogin.html             (Entry point)
 ├── Dashboard.html
 ├── Employees.html
 ├── Attendance.html
 ├── Department.html
 ├── Shifts.html
 ├── Holiday.html
 ├── LeaveRecords.html
 ├── API Test Helper.html       (Debug tool)
 │
 ├── css/                       (Create this folder)
 │    └── styles.css            (Required for styling)
 │
 └── js/                        (Create this folder)
      ├── api.js                (Handles fetch requests & auth headers)
      ├── App.js                (Common logic: checkLogin, checkRole, getUser)
      ├── Charts.js             (Chart rendering logic)
      └── SideBar.js            (Sidebar rendering logic)

4. HOW TO RUN
------------------------------------------------------------------------
1. Open a terminal/command prompt in the root directory.
2. Run the frontend server:
   
   node server.js

3. The console will display:
   "Frontend server running at http://localhost:8081"

4. Open your browser and navigate to:
   http://localhost:8081/UserLogin.html

5. TEST CREDENTIALS (Default Database Data)
------------------------------------------------------------------------
Use the following credentials to log in and test different roles:
5.1. UserLogin
-UserID: 1
-Password: 123456
-Role: Admin
-Access: 
+All pages
+Full CRUD operations across the system
+Manage Employees, Departments, Shifts, Holidays
+Approve/Deny Leave Requests
+Mark attendance for all employees
-Restricted: None
5.2. Dashboard
-UserID: 2 
-Password: 123456
-Role: Manager 
-Access: 
+Dashboard overview for department
+Attendance (Self) 
+View Department Employee
s +Leave Requests (Approve/Deny for own department)
+View my Profile
+Holidays
-Restricted: Cannot delete data Cannot manage other employees Cannot edit Departments/Shifts/Holidays
5.3. Employees
-UserID: 1
-Password: 123456
-Role: Admin
-Access:
+Add, Edit, Delete Employees
+Manage department assignment
-Restricted: None
5.4. Attendance
-UserID: 3
-Password: 123456
-Role: Employee (Self Attendance)
-Access:
+Check In / Check Out
+Select Shift (Day, Night, Morning, Evening)
+View personal attendance history
-Restricted:
+Cannot edit or delete attendance
+Cannot mark attendance for others
5.5. Department
-UserID: 1
-Password: 123456
-Role: Admin
-Access:
+Add/Edit/Delete Departments
Restricted: None
5.6. Shifts
-UserID: 1
-Password: 123456
-Role: Admin
-Access:
+Create Shift
+Configure Start Time / End Time
+Edit/Delete shifts
-Restricted: None
5.7. Holiday
-UserID: 2
-Password: 123456
-Role: Manager
-Access:
+View holiday requested list
-Restricted:
+Cannot add/edit/delete holidays
5.8. LeaveRecords
-UserID: 3
-Password: 123456
-Role: Employee
+Access:
+Submit Leave Requests
+View approval status
-Restricted:
+No approval permissions
+Cannot view requests from other employees

6. FILE DESCRIPTIONS
------------------------------------------------------------------------
- server.js: 
  A lightweight HTTP server using Node.js to serve the HTML files and 
  handle static assets. It runs on port 8081 to avoid conflict with the 
  backend (port 8080).

- UserLogin.html: 
  The landing page. Handles User Login (POST /api/auth/login) and 
  Registration. Stores the JWT token in localStorage.

- Dashboard.html: 
  The main landing page after login. Displays statistics (Total Employees, 
  Today's Attendance, etc.) and quick action buttons.

- Employees.html: 
  CRUD interface for managing employee records. 
  - Admin: Add/Edit/Delete employees.
  - Employee: View own profile.

- Attendance.html:
  - Admin: Mark attendance for any employee manually.
  - Employee: "Check In" and "Check Out" buttons for self-attendance.
  - Displays attendance history.

- Department.html:
  Manage company departments. (Admin/Manager only).

- Shifts.html:
  Define work shifts (Start Time / End Time).

- Holiday.html:
  Manage company holidays.

- LeaveRecords.html:
  - Employee: Submit leave requests (Annual, Medical, Personal).
  - Admin/Manager: Approve or Deny leave requests.

- API Test Helper.html:
  A utility page to troubleshoot connection issues between the Frontend 
  (8081) and the Backend (8080). It helps verify CORS settings.

7. TROUBLESHOOTING
------------------------------------------------------------------------
- "401 Unauthorized": 
  Your session token may have expired. Click "Logout" or clear 
  localStorage and login again.

- "Network Error" / "Failed to fetch":
  Ensure the Backend Java server is running on port 8080.
  Use `API Test Helper.html` to verify connectivity.

- CORS Errors:
  Ensure the backend is configured to allow requests from origin 
  `http://localhost:8081`.
