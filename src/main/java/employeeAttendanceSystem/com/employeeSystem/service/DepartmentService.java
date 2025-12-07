package employeeAttendanceSystem.com.employeeSystem.service;

 

import employeeAttendanceSystem.com.employeeSystem. model.DepartmentResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.DepartmentRequestDTO;
import employeeAttendanceSystem.com.employeeSystem. model.DepartmentAssignmentDTO;
import employeeAttendanceSystem.com.employeeSystem. model.EmployeeResponseDTO;
import employeeAttendanceSystem.com.employeeSystem. repository.entity.DepartmentEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentResponseDTO> getAllDepartments();

    Optional<DepartmentResponseDTO> getDepartmentById(int departmentId);

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO request);

    DepartmentResponseDTO updateDepartment(int departmentId, DepartmentRequestDTO request);

    boolean deleteDepartment(int departmentId);

    boolean assignManager(int departmentId, DepartmentAssignmentDTO assignmentDetails);

    List<EmployeeResponseDTO> getEmployeesByDepartment(int departmentId);
}
