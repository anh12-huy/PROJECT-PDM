 


package employeeAttendanceSystem.com.employeeSystem.service.impl;


import employeeAttendanceSystem.com.employeeSystem.repository.LeaveTypeRepository;  
import employeeAttendanceSystem.com.employeeSystem.repository.impl.LeaveTypeRepositoryimpl;  
import employeeAttendanceSystem.com.employeeSystem.service.LeaveTypeService;  
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveTypeEntity;
import employeeAttendanceSystem.com.employeeSystem.model.LeaveTypeResponseDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.LeaveTypeRequest; 
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;  

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;
    
    
    private LeaveTypeResponseDTO mapEntityToDTO(LeaveTypeEntity entity) {
        if (entity == null) return null;
        LeaveTypeResponseDTO dto = new LeaveTypeResponseDTO();
        
     
        dto.setLeaveTypeName(entity.getLeaveType_name());
        dto.setMaxDaysAllowed(entity.getMaxDaysAllowed());
        return dto;
    }

    
    private LeaveTypeEntity mapRequestToEntity(LeaveTypeRequest request) {
        if (request == null) return null;
        LeaveTypeEntity entity = new LeaveTypeEntity();
        
        entity.setLeaveType_name(request.getLeaveType_name());
        entity.setMaxDaysAllowed(request.getMaxDaysAllowed());
        return entity;
    }

    public LeaveTypeServiceImpl() {
        this.leaveTypeRepository = new LeaveTypeRepositoryimpl();  
    }
    
 
 
    @Override
    public List<LeaveTypeResponseDTO> getAllLeaveTypes() {
        List<LeaveTypeEntity> entities = leaveTypeRepository.getAllLeaveType();
        List<LeaveTypeResponseDTO> dtos = new ArrayList<>();
        for (LeaveTypeEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));  
        }
        return dtos;
    }
 
    @Override
    public Optional<LeaveTypeResponseDTO> getLeaveTypeById(int leaveTypeId) {
        Optional<LeaveTypeEntity> entityOptional = leaveTypeRepository.getLeaveTypeById(leaveTypeId);
        
        return entityOptional.map(this::mapEntityToDTO);
    }
 
    @Override
    public LeaveTypeResponseDTO createLeaveType(LeaveTypeRequest request) {
         
        LeaveTypeEntity leaveTypeEntity = mapRequestToEntity(request);
        
        if (leaveTypeEntity.getLeaveType_name() == null || leaveTypeEntity.getLeaveType_name().trim().isEmpty()) {
            throw new IllegalArgumentException("Leave Type name cannot be empty.");
        }
        
        boolean success = leaveTypeRepository.createLeaveType(leaveTypeEntity);
        
        if (success) {
            return mapEntityToDTO(leaveTypeEntity);  
        }
        return null;
    }
 
    @Override
    public LeaveTypeResponseDTO updateLeaveType(int leaveTypeId, LeaveTypeRequest request) {
      
        Optional<LeaveTypeEntity> existingTypeOptional = leaveTypeRepository.getLeaveTypeById(leaveTypeId);
        
        if (existingTypeOptional.isEmpty()) {
            return null; 
        }
        
        LeaveTypeEntity existingType = existingTypeOptional.get();
 
        existingType.setLeaveType_name(request.getLeaveType_name());
        existingType.setMaxDaysAllowed(request.getMaxDaysAllowed());
 
        boolean success = leaveTypeRepository.updateLeaveType(leaveTypeId, existingType);
        
        if (success) {
            return mapEntityToDTO(existingType);  
        }
        return null;  
    }

    @Override
    public boolean deleteLeaveType(int leaveTypeId) {
        return leaveTypeRepository.deleteLeaveType(leaveTypeId);
    }
}