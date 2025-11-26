package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;
import java.sql.Time; 

 
public class ShiftDTO implements Serializable {

    private int shiftId;  
    private String shiftName; 
    private Time startTime; 
    private Time endTime; 
  
    public ShiftDTO() {}
   
    public int getShiftId() { return shiftId; }
    public void setShiftId(int shiftId) { this.shiftId = shiftId; }

    public String getShiftName() { return shiftName; }
    public void setShiftName(String shiftName) { this.shiftName = shiftName; }

    public Time getStartTime() { return startTime; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }

    public Time getEndTime() { return endTime; }
    public void setEndTime(Time endTime) { this.endTime = endTime; }
}


