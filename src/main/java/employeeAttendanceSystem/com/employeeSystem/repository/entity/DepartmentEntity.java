package employeeAttendanceSystem.com.employeeSystem.repository.entity;
import java.sql.Timestamp;
import java.time.LocalDateTime;
public class DepartmentEntity {

	   private Integer departmentId;
	   private String departmentName;
	   private String location;
	   private Integer managerEmployeeId;
	   private Timestamp createdAt;
	   private Timestamp updatedAt;

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
