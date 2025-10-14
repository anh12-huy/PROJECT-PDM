package employeeManageSystem.model;

public class LeaveTypeDTO {
	 	private String leaveTypeId;
	    private String leaveTypeName;
	    private Integer maxDaysAllowed;
		public String getLeaveTypeId() {
			return leaveTypeId;
		}
		public void setLeaveTypeId(String leaveTypeId) {
			this.leaveTypeId = leaveTypeId;
		}
		public String getLeaveTypeName() {
			return leaveTypeName;
		}
		public void setLeaveTypeName(String leaveTypeName) {
			this.leaveTypeName = leaveTypeName;
		}
		public Integer getMaxDaysAllowed() {
			return maxDaysAllowed;
		}
		public void setMaxDaysAllowed(Integer maxDaysAllowed) {
			this.maxDaysAllowed = maxDaysAllowed;
		}
}
