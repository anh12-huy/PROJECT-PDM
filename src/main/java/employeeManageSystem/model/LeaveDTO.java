package employeeManageSystem.model;

public class LeaveDTO {
	 	private String leaveId;
	    private String startDate;
	    private String endDate;
	    private String reason;
	    private String status;

	    private String employeeId;
	    private String employeeName;

	    private String leaveTypeId;
	    private String leaveTypeName;
		public String getLeaveId() {
			return leaveId;
		}
		public void setLeaveId(String leaveId) {
			this.leaveId = leaveId;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
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
}
