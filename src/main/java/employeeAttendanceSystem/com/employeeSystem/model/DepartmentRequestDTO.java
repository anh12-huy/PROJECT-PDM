package employeeAttendanceSystem.com.employeeSystem.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DepartmentRequestDTO implements Serializable {

    private String departmentName;
    private String departmentCode;
    private String location;
    private Integer managerEmployeeId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public DepartmentRequestDTO() {}

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getManagerEmployeeId() {
        return managerEmployeeId;
    }

    public void setManagerEmployeeId(Integer managerEmployeeId) {
        this.managerEmployeeId = managerEmployeeId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
