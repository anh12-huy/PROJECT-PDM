package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;
import java.time.LocalDate;
 
public class HolidayRequestDTO implements Serializable {
 
    private LocalDate holidayDate; 
   
    private String holidayName;
 
    public HolidayRequestDTO() {}
 
    public LocalDate getHolidayDate() { return holidayDate; }
    public void setHolidayDate(LocalDate holidayDate) { this.holidayDate = holidayDate; }

    public String getHolidayName() { return holidayName; }
    public void setHolidayName(String holidayName) { this.holidayName = holidayName; }
}

