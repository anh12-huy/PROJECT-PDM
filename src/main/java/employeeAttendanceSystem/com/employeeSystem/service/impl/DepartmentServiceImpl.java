 

package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.DepartmentRepository;  
import employeeAttendanceSystem.com.employeeSystem.service.DepartmentService;  
import employeeAttendanceSystem.com.employeeSystem.repository.entity.DepartmentEntity;  
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;  
import employeeAttendanceSystem.com.employeeSystem.repository.impl.DepartmentRepositoryimpl; 
import employeeAttendanceSystem.com.employeeSystem.model.DepartmentResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.DepartmentRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.model.DepartmentAssignmentDTO;
import employeeAttendanceSystem.com.employeeSystem.model.EmployeeResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;  

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
     
    private final EmployeeServiceImpl employeeService; 
 
    private DepartmentResponseDTO mapEntityToDTO(DepartmentEntity entity) {
        if (entity == null) return null;
        DepartmentResponseDTO dto = new DepartmentResponseDTO();
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setDepartmentName(entity.getDepartmentName());
         
        dto.setDepartmentCode(entity.getLocation()); 
        dto.setManagerEmployeeId(entity.getManagerEmployeeId());
  
        return dto;
    }
    
   
    private DepartmentEntity mapRequestToEntity(DepartmentRequestDTO request) {
        if (request == null) return null;
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDepartmentName(request.getDepartmentName());
        
        entity.setLocation(request.getDepartmentCode()); 
 
        return entity;
    }

    public DepartmentServiceImpl() {
        this.departmentRepository = new DepartmentRepositoryimpl();
   
        this.employeeService = new EmployeeServiceImpl(); 
    }

    
    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<DepartmentEntity> entities = departmentRepository.findAll();
        List<DepartmentResponseDTO> dtos = new ArrayList<>();
        for (DepartmentEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));
        }
        return dtos;
    }

 
    @Override
    public Optional<DepartmentResponseDTO> getDepartmentById(int departmentId) {
       
        Optional<DepartmentEntity> deptOptional = departmentRepository.findById(departmentId);
      
        return deptOptional.map(this::mapEntityToDTO);
    }

     
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request) {
        DepartmentEntity deptEntity = mapRequestToEntity(request);
        
        if (deptEntity.getDepartmentName() == null || deptEntity.getDepartmentName().trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
     
        boolean success = departmentRepository.create(deptEntity);
        
        if (success) {
        
            return mapEntityToDTO(deptEntity);
        }
        return null;
    }
 
    @Override
    public DepartmentResponseDTO updateDepartment(int departmentId, DepartmentRequestDTO request) {
        
        Optional<DepartmentEntity> existingDeptOptional = departmentRepository.findById(departmentId);
        
        if (existingDeptOptional.isEmpty()) {
            return null; 
        }
        
        DepartmentEntity existingDept = existingDeptOptional.get();
       
        existingDept.setDepartmentName(request.getDepartmentName());
        existingDept.setLocation(request.getDepartmentCode());
      
        
        boolean success = departmentRepository.update(departmentId, existingDept);
        
        if (success) {
            return mapEntityToDTO(existingDept);
        }
        return null;
    }

    
    @Override
    public boolean deleteDepartment(int departmentId) {
        return departmentRepository.delete(departmentId);
    }

       @Override
    public boolean assignManager(int departmentId, DepartmentAssignmentDTO assignmentDetails) {
     
        if (departmentRepository.findById(departmentId).isEmpty()) {
            throw new IllegalArgumentException("Department ID not found.");
        }
        
        int employeeId = assignmentDetails.getManagerId();
        
        return departmentRepository.assignManager(departmentId, employeeId);
    }

    
    @Override
    public List<EmployeeResponseDTO> getEmployeesByDepartment(int departmentId) {
        List<EmployeeEntity> entities = departmentRepository.findEmployeesByDepartment(departmentId);
        List<EmployeeResponseDTO> dtos = new ArrayList<>();
       
        for (EmployeeEntity entity : entities) {
           
            Optional<EmployeeResponseDTO> employeeDto = employeeService.getEmployeeDetails(entity.getEmployeeID());
            
            employeeDto.ifPresent(dtos::add);
        }
        return dtos;
    }
}
