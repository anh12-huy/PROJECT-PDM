package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.EmployeeRepository;  
import employeeAttendanceSystem.com.employeeSystem.repository.impl.EmployeeRepositoryimpl;  
import employeeAttendanceSystem.com.employeeSystem.service.EmployeeService;  
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity;
import employeeAttendanceSystem.com.employeeSystem.model.EmployeeResponseDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.EmployeeRequestDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordResponseDTO; 

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections; 
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;  
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 

@Service
public class EmployeeServiceImpl implements EmployeeService {
 
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder; 
    
    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryimpl(); 
        this.passwordEncoder = new BCryptPasswordEncoder(); 
    }
 
    private EmployeeResponseDTO mapEntityToResponseDTO(EmployeeEntity entity) {
        if (entity == null) return null;
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setEmployeeId(entity.getEmployeeID());
        dto.setFirstName(entity.getName());  
        dto.setEmail(entity.getEmail());
        
        if (entity.getBirthday() != null) {
            dto.setDateOfBirth(entity.getBirthday().toLocalDate());
        }
        dto.setDepartmentId(entity.getDepartmentID()); 
         if (entity.getCreated_at() != null) {
            dto.setHiredDate(entity.getCreated_at().toLocalDateTime());
        }
        return dto;
    }

    private EmployeeEntity mapRequestToEntity(EmployeeRequestDTO request) {
        if (request == null) return null;
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(request.getFirstName() + " " + request.getLastName());
        entity.setEmail(request.getEmail());
        if (request.getDepartmentId() != null) {
            entity.setDepartmentID(request.getDepartmentId().intValue()); 
        }
        
        if (request.getDateOfBirth() != null) {
            entity.setBirthday(java.sql.Date.valueOf(request.getDateOfBirth()));
        }
        
        return entity;
    }
    
    private LeaveRecordResponseDTO mapLeaveRecordEntityToDTO(LeaveRecordEntity entity) {
        if (entity == null) return null;
        LeaveRecordResponseDTO dto = new LeaveRecordResponseDTO();
        
        dto.setLeaveId(entity.getLeaveID());
        dto.setEmployeeId(entity.getEmployeeID());
        dto.setLeaveTypeId(entity.getLeaveTypeID());
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
        
        if (entity.getStart_date() != null) {
             dto.setStartDate(entity.getStart_date().toLocalDate());
        }
        if (entity.getEnd_date() != null) {
             dto.setEndDate(entity.getEnd_date().toLocalDate());
        }
        if (entity.getCreated_at() != null) {
             dto.setCreatedAt(entity.getCreated_at().toLocalDateTime());
        }
        
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;
            dto.setTotalDays((int) days);
        }
        
        return dto;
    }

    private AttendanceResponseDTO mapAttendanceEntityToDTO(AttendanceEntity entity) {
        if (entity == null) return null;
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
        dto.setAttendanceId(entity.getAttendanceID());
        dto.setEmployeeId(entity.getEmployeeID());
        
        if (entity.getCheckIn() != null) {
            dto.setCheckInTime(entity.getCheckIn().toLocalDateTime());
        }
        if (entity.getCheckOut() != null) {
            dto.setCheckOutTime(entity.getCheckOut().toLocalDateTime());
        }
         
        return dto;
    }
    

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<EmployeeEntity> entities = employeeRepository.getAllEmployee();
        List<EmployeeResponseDTO> dtos = new ArrayList<>();
        for (EmployeeEntity entity : entities) {
            dtos.add(mapEntityToResponseDTO(entity));
        }
        return dtos;
    }
 
    @Override
    public Optional<EmployeeResponseDTO> getEmployeeDetails(int employeeId) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.getEmployeeInformation(employeeId);
        return employeeOptional.map(this::mapEntityToResponseDTO);
    }
 
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
        EmployeeEntity newEmployee = mapRequestToEntity(request);
        boolean success = employeeRepository.createEmployeeRecord(newEmployee);
        if (success) {
            return mapEntityToResponseDTO(newEmployee);
        }
        return null;
    }
 
    @Override
    public EmployeeResponseDTO updateEmployee(int employeeId, EmployeeRequestDTO request) {
        Optional<EmployeeEntity> existingEmployeeOptional = employeeRepository.getEmployeeInformation(employeeId);
        if (existingEmployeeOptional.isEmpty()) {
            return null;  
        }
        
        EmployeeEntity existingEmployee = existingEmployeeOptional.get();
        existingEmployee.setName(request.getFirstName() + " " + request.getLastName());
        existingEmployee.setEmail(request.getEmail());
        if (request.getDepartmentId() != null) {
            existingEmployee.setDepartmentID(request.getDepartmentId().intValue());
        }
 
        boolean success = employeeRepository.updateEmployeeRecord(employeeId, existingEmployee);
        if (success) {
            return mapEntityToResponseDTO(existingEmployee);
        }
        return null;
    }
 
    @Override
    public boolean deleteEmployee(int employeeId) {
        return employeeRepository.deleteEmployeeRecord(employeeId);
    }
 
    @Override
    public List<AttendanceResponseDTO> getAttendanceHistory(int employeeId) {
        List<AttendanceEntity> entities = employeeRepository.getAllAttendanceRecord(employeeId);
        List<AttendanceResponseDTO> dtos = new ArrayList<>();
        for (AttendanceEntity entity : entities) { 
            dtos.add(mapAttendanceEntityToDTO(entity));
        }
        return dtos;
    }
 
    @Override
    public List<LeaveRecordResponseDTO> getLeaveHistory(int employeeId) {
        List<LeaveRecordEntity> entities = employeeRepository.getAllLeaveEntity(employeeId);
        List<LeaveRecordResponseDTO> dtos = new ArrayList<>();
        for (LeaveRecordEntity entity : entities) { 
            dtos.add(mapLeaveRecordEntityToDTO(entity));
        }
        return dtos;
    }
 
    @Override
    public boolean assignDepartment(int employeeId, int departmentId) {
        employeeRepository.assignDepartment(employeeId, departmentId);
        return true; 
    }
 
    @Override
    public boolean assignShift(int employeeId, int shiftId) {
        employeeRepository.assignShift(employeeId, shiftId);
        return true; 
    }
}