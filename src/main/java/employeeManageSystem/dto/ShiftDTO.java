package employeeManageSystem.dto;

public class ShiftDTO {
	 	private String shiftId;
	    private String shiftName;
	    private String startTime;
	    private String endTime;
		public String getShiftId() {
			return shiftId;
		}
		public void setShiftId(String shiftId) {
			this.shiftId = shiftId;
		}
		public String getShiftName() {
			return shiftName;
		}
		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
}
