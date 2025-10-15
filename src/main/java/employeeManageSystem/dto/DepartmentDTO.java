package employeeManageSystem.dto;

public class DepartmentDTO {
	private int departmentId;
	private String departmentName;
	private String location;
	private EmployeeDTO manager;
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
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
