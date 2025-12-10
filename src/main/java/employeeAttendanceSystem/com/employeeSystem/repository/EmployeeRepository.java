package employeeAttendanceSystem.com.employeeSystem.repository;

import java.util.*;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;

public interface EmployeeRepository {
	List<EmployeeEntity> getAllEmployee();
	Optional<EmployeeEntity> getEmployeeInformation(int emplId);
	boolean createEmployeeRecord(EmployeeEntity employee);
	boolean updateEmployeeRecord(int id ,EmployeeEntity employee);
	boolean deleteEmployeeRecord(int emplId);
	List<AttendanceEntity> getAllAttendanceRecord(int emplId);
	List<LeaveRecordEntity> getAllLeaveEntity(int emplId);
	void assignDepartment(int emplId,int deptId);
	void assignShift(int emplId,int shiftId);
}
