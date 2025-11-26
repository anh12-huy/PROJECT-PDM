package employeeAttendanceSystem.com.employeeSystem.repository.entity;

import java.sql.Time;

public class ShiftEntity {
	   private int shiftID;
	 
	   private String shiftName;
	  
	   private Time startTime;
	  
	   private Time endTime;
	 
	   public int getShiftID() {
	       return shiftID;
	   }
	   public void setShiftID(int shiftID) {
	       this.shiftID = shiftID;
	   }
	   public String getShiftName() {
	       return shiftName;
	   }
	   public void setShiftName(String shiftName) {
	       this.shiftName = shiftName;
	   }
	   public Time getStartTime() {
	       return startTime;
	   }
	   public void setStartTime(Time startTime) {
	       this.startTime = startTime;
	   }
	   public Time getEndTime() {
	       return endTime;
	   }
	   public void setEndTime(Time endTime) {
	       this.endTime = endTime;
	   }
}
