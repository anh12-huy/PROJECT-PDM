package employeeManageSystem.dto;

import java.sql.Date;

public class ShiftDTO {
	 	private int shiftId;
	    private String shiftName;
	    private Date startTime;
	    private Date endTime;
		public int getShiftId() {
			return shiftId;
		}
		public void setShiftId(int shiftId) {
			this.shiftId = shiftId;
		}
		public String getShiftName() {
			return shiftName;
		}
		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
}
