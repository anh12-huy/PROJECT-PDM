	package employeeAttendanceSystem.com.employeeSystem.model;

import java.time.LocalDateTime;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;

 

import java.io.Serializable;
import java.time.LocalDateTime;

public class AttendanceResponseDTO  {
 
    private int attendanceId; 
 
    private int employeeId; 
 
    private LocalDateTime checkInTime;
 
    private LocalDateTime checkOutTime; 
 
    private String method; 
 
    public AttendanceResponseDTO() {}
 
    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }
 
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) { this.checkOutTime = checkOutTime; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
