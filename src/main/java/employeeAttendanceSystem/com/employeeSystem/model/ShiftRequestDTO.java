package employeeAttendanceSystem.com.employeeSystem.model;


 

import java.io.Serializable;
import java.sql.Time; 
 
public class ShiftRequestDTO implements Serializable {

    private String shiftName; 
    private Time startTime; 
    private Time endTime; 
 
    public ShiftRequestDTO() {}

    
    public String getShiftName() { return shiftName; }
    public void setShiftName(String shiftName) { this.shiftName = shiftName; }

    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }

    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }
}


