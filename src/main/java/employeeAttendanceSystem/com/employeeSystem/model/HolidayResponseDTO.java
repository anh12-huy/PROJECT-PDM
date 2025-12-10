package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;
import java.time.LocalDate;


public class HolidayResponseDTO implements Serializable {
 
    private int holidayId; 
     
    private LocalDate holidayDate; 
    
    private String holidayName;
 
    public HolidayResponseDTO() {}
 
    public int getHolidayId() { return holidayId; }
    public void setHolidayId(int holidayId) { this.holidayId = holidayId; }

    public LocalDate getHolidayDate() { return holidayDate; }
    public void setHolidayDate(LocalDate holidayDate) { this.holidayDate = holidayDate; }

    public String getHolidayName() { return holidayName; }
    public void setHolidayName(String holidayName) { this.holidayName = holidayName; }
}
