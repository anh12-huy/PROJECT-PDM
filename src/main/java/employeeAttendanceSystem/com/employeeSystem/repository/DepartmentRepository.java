package employeeAttendanceSystem.com.employeeSystem.repository;

import java.util.List;
import java.util.Optional;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.DepartmentEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;

public interface DepartmentRepository {
	   boolean create(DepartmentEntity dept);
	   Optional<DepartmentEntity> findById(int id);
	   List<DepartmentEntity> findAll();
	   boolean update(int id,DepartmentEntity dept);
	   boolean delete(int id);
	   List<EmployeeEntity> findEmployeesByDepartment(int departmentId);
	   boolean assignManager(int departmentId, int employeeId);
}
