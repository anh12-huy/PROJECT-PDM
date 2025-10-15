package employeeManageSystem.dto;

public class LeaveTypeDTO {
	 	private int leaveTypeId;
	    private String leaveTypeName;
	    private int maxDaysAllowed;
		public int getLeaveTypeId() {
			return leaveTypeId;
		}
		public void setLeaveTypeId(int leaveTypeId) {
			this.leaveTypeId = leaveTypeId;
		}
		public String getLeaveTypeName() {
			return leaveTypeName;
		}
		public void setLeaveTypeName(String leaveTypeName) {
			this.leaveTypeName = leaveTypeName;
		}
		public int getMaxDaysAllowed() {
			return maxDaysAllowed;
		}
		public void setMaxDaysAllowed(int maxDaysAllowed) {
			this.maxDaysAllowed = maxDaysAllowed;
		}
}
