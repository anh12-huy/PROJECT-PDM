package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.ShiftRepository;  
import employeeAttendanceSystem.com.employeeSystem.repository.impl.ShiftRepositoryimpl;  
import employeeAttendanceSystem.com.employeeSystem.service.ShiftService;  
import employeeAttendanceSystem.com.employeeSystem.repository.entity.ShiftEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity; 
import employeeAttendanceSystem.com.employeeSystem.model.ShiftDTO;
import employeeAttendanceSystem.com.employeeSystem.model.ShiftRequestDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.EmployeeResponseDTO;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service; 
import java.sql.Time; 

@Service 
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;
   
    private ShiftDTO mapShiftEntityToDTO(ShiftEntity entity) {
        if (entity == null) return null;
        ShiftDTO dto = new ShiftDTO();
        dto.setShiftId(entity.getShiftID());
        dto.setShiftName(entity.getShiftName());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }
  
    private ShiftEntity mapRequestToEntity(ShiftRequestDTO request) {
        if (request == null) return null;
        ShiftEntity entity = new ShiftEntity();
        entity.setShiftName(request.getShiftName());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        return entity;
    }
 
    private EmployeeResponseDTO mapEmployeeEntityToDTO(EmployeeEntity entity) {
    if (entity == null) return null;

    EmployeeResponseDTO dto = new EmployeeResponseDTO();

        // Mapping các field cơ bản
        dto.setEmployeeId(entity.getEmployeeID());
        dto.setFirstName(entity.getName()); // Nếu muốn tách firstName/lastName thì cần parse từ Name
        dto.setEmail(entity.getEmail());
        dto.setDateOfBirth(entity.getBirthday() != null ? entity.getBirthday().toLocalDate() : null);
        dto.setDepartmentId(entity.getDepartmentID());
        
        // Những field không có trong entity thì để mặc định
        dto.setLastName(null);
        dto.setPhoneNumber(null);
        dto.setPosition(null);
        dto.setHiredDate(entity.getCreated_at() != null ? entity.getCreated_at().toLocalDateTime() : null);
        dto.setDepartmentName(null);
        dto.setTotalLeaveDaysUsed(0);
        dto.setTotalShiftHours(0);

        return dto;
    }

    public ShiftServiceImpl() {
        this.shiftRepository = new ShiftRepositoryimpl(); 
    }

    @Override
    public List<ShiftDTO> getAllShifts() {
        List<ShiftEntity> entities = shiftRepository.geAtAllShift();
        List<ShiftDTO> dtos = new ArrayList<>();
        for (ShiftEntity entity : entities) {
            dtos.add(mapShiftEntityToDTO(entity)); 
        }
        return dtos;
    }

    @Override
    public Optional<ShiftDTO> getShiftById(int shiftId) {
        Optional<ShiftEntity> shiftOptional = shiftRepository.getShiftById(shiftId);
        
        return shiftOptional.map(this::mapShiftEntityToDTO);
    }
 
    @Override
    public ShiftDTO createShift(ShiftRequestDTO request) {
        ShiftEntity shiftEntity = mapRequestToEntity(request);
        
        if (shiftEntity.getShiftName() == null || shiftEntity.getStartTime() == null || shiftEntity.getEndTime() == null) {
            throw new IllegalArgumentException("Shift details cannot be empty.");
        }
        if (shiftEntity.getStartTime().after(shiftEntity.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time for a standard shift.");
        }
        
        boolean success = shiftRepository.createShift(shiftEntity);
        
        if (success) {
            return mapShiftEntityToDTO(shiftEntity); 
        }
        return null;
    }
  
    @Override
    public ShiftDTO updateShift(int shiftId, ShiftRequestDTO request) {
        Optional<ShiftEntity> existingShiftOptional = shiftRepository.getShiftById(shiftId);
        
        if (existingShiftOptional.isEmpty()) {
            return null; 
        }

        ShiftEntity existingShift = existingShiftOptional.get();

        existingShift.setShiftName(request.getShiftName());
        existingShift.setStartTime(request.getStartTime());
        existingShift.setEndTime(request.getEndTime());

        boolean success = shiftRepository.updateShift(shiftId, existingShift);
        
        if (success) {
            return mapShiftEntityToDTO(existingShift); 
        }
        return null; 
    }

    @Override
    public boolean deleteShift(int shiftId) {
        return shiftRepository.deleteShift(shiftId);
    }

    @Override
    public boolean assignEmployeeToShift(int shiftId, int employeeId) {
        return shiftRepository.assignEmployeToShift(shiftId, employeeId);
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesInShift(int shiftId) {
        List<EmployeeEntity> entities = shiftRepository.getAllEmployeeInShift(shiftId);
        List<EmployeeResponseDTO> dtos = new ArrayList<>();
        for (EmployeeEntity entity : entities) {
            dtos.add(mapEmployeeEntityToDTO(entity));  
        }
        return dtos;
    }
 
    @Override
    public List<ShiftDTO> getEmployeeSchedule(int employeeId) {
        List<ShiftEntity> entities = shiftRepository.getAllShiftOfEmployee(employeeId);
        List<ShiftDTO> dtos = new ArrayList<>();
        for (ShiftEntity entity : entities) {
            dtos.add(mapShiftEntityToDTO(entity));  
        }
        return dtos;
    }
}