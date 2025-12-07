package employeeAttendanceSystem.com.employeeSystem.model;

 

import java.io.Serializable;

public class DepartmentResponseDTO implements Serializable {

    private Integer departmentId;
    private String departmentName;
    private String departmentCode;  
    private Integer managerEmployeeId; 
    private String managerName;  

    public DepartmentResponseDTO() {}

    

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Integer getManagerEmployeeId() {
        return managerEmployeeId;
    }

    public void setManagerEmployeeId(Integer managerEmployeeId) {
        this.managerEmployeeId = managerEmployeeId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}