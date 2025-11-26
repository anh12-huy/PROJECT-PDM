package employeeAttendanceSystem.com.employeeSystem.model;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;

 

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

 
public class EmployeeResponseDTO implements Serializable {
 
    private int employeeId;  
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String position;
    private LocalDateTime hiredDate; 
 
    private int departmentId;  
    private String departmentName; 
 
    private int totalLeaveDaysUsed;  
    private int totalShiftHours; 
 
    public EmployeeResponseDTO() {}
 
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public LocalDateTime getHiredDate() { return hiredDate; }
    public void setHiredDate(LocalDateTime hiredDate) { this.hiredDate = hiredDate; }
 
    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
 
    public int getTotalLeaveDaysUsed() { return totalLeaveDaysUsed; }
    public void setTotalLeaveDaysUsed(int totalLeaveDaysUsed) { this.totalLeaveDaysUsed = totalLeaveDaysUsed; }
 
    public int getTotalShiftHours() { return totalShiftHours; }
    public void setTotalShiftHours(int totalShiftHours) { this.totalShiftHours = totalShiftHours; }
}

