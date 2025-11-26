package employeeAttendanceSystem.com.employeeSystem.model;

import java.sql.Date;
import java.time.LocalDateTime;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;


 

import java.io.Serializable;
import java.time.LocalDateTime;
 
public class AttendanceRequestDTO   {
 
    private LocalDateTime timestamp; 
 
    private Double latitude; 
 
    private Double longitude;
 
    public AttendanceRequestDTO() {}
 
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}


