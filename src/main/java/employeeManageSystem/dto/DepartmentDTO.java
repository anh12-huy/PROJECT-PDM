package employeeManageSystem.dto;

public class DepartmentDTO {
	private String departmentId;
	private String departmentName;
	private String location;
	private EmployeeDTO manager;
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public EmployeeDTO getManager() {
		return manager;
	}
	public void setManager(EmployeeDTO manager) {
		this.manager = manager;
	}
	
}
