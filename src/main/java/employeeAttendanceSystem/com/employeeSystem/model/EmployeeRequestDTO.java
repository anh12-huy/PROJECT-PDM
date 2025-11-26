package employeeAttendanceSystem.com.employeeSystem.model;

import java.sql.Date;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.SexType;
 

import java.io.Serializable;
import java.time.LocalDate;

 

 
import java.io.Serializable;
import java.time.LocalDate;
 
public class EmployeeRequestDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
     
    private String password; 
   
    private Long departmentId; 
 
    public EmployeeRequestDTO() {}
 
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
  
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
