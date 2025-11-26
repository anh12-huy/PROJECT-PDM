package employeeAttendanceSystem.com.employeeSystem.model;




 

import java.io.Serializable;

public class DepartmentRequestDTO implements Serializable {

private String departmentName;

private String departmentCode; 
 
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
}

