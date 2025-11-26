package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.LeaveRecordRepository; 
import employeeAttendanceSystem.com.employeeSystem.repository.impl.LeaveRecordRepositoryimpl; 
import employeeAttendanceSystem.com.employeeSystem.service.LeaveRecordService; 
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveRecordEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.LeaveStatus; 
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordResponseDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.LeaveRecordRequestDTO; 

import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service; 
import java.sql.Date;

@Service 
public class LeaveRecordServiceImpl implements LeaveRecordService {
 
    private final LeaveRecordRepository leaveRecordRepository;

    public LeaveRecordServiceImpl() {
        this.leaveRecordRepository = new LeaveRecordRepositoryimpl(); 
    }
    
    private LeaveRecordResponseDTO mapEntityToDTO(LeaveRecordEntity entity) {
        if (entity == null) return null;
        LeaveRecordResponseDTO dto = new LeaveRecordResponseDTO();

        dto.setLeaveId(entity.getLeaveID());
        dto.setEmployeeId(entity.getEmployeeID());
        dto.setLeaveTypeId(entity.getLeaveTypeID());
  
        if (entity.getStart_date() != null) {
             dto.setStartDate(entity.getStart_date().toLocalDate());
        }
        if (entity.getEnd_date() != null) {
             dto.setEndDate(entity.getEnd_date().toLocalDate());
        }
     
        if (entity.getCreated_at() != null) {
             dto.setCreatedAt(entity.getCreated_at().toLocalDateTime());
        }
        
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
  
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;
            dto.setTotalDays((int) days);
        } else {
            dto.setTotalDays(0);
        }
    
        return dto;
    }


    @Override
    public List<LeaveRecordResponseDTO> getAllLeaveRecords() {
        List<LeaveRecordEntity> entities = leaveRecordRepository.getAllLeaveRecord();
        List<LeaveRecordResponseDTO> dtos = new ArrayList<>();
        for (LeaveRecordEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));
        }
        return dtos;
    }
 
    @Override
    public Optional<LeaveRecordResponseDTO> getLeaveRecordById(int leaveId) {
        Optional<LeaveRecordEntity> recordOptional = leaveRecordRepository.getLeaveRecordById(leaveId);
        return recordOptional.map(this::mapEntityToDTO);
    }

    @Override
    public LeaveRecordResponseDTO createLeaveRecord(LeaveRecordRequestDTO request) {
        LeaveRecordEntity leaveRecord = new LeaveRecordEntity();
        leaveRecord.setEmployeeID(request.getEmployeeId());
        leaveRecord.setLeaveTypeID(request.getLeaveTypeId());
        
        if (request.getStartDate() != null) {
            leaveRecord.setStart_date(Date.valueOf(request.getStartDate()));
        }
        if (request.getEndDate() != null) {
            leaveRecord.setEnd_date(Date.valueOf(request.getEndDate()));
        }
        leaveRecord.setReason(request.getReason());

        if (leaveRecord.getStart_date().after(leaveRecord.getEnd_date())) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        
        if (leaveRecord.getStatus() == null) {
            leaveRecord.setStatus(LeaveStatus.PENDING); 
        }
        
        boolean success = leaveRecordRepository.createLeaveRecord(leaveRecord);
        
        if (success) {
            return mapEntityToDTO(leaveRecord);  
        }
        return null;
    }
 
    @Override
    public LeaveRecordResponseDTO updateLeaveRecord(int leaveId, LeaveRecordRequestDTO request) {
    
        Optional<LeaveRecordEntity> existingRecordOptional = leaveRecordRepository.getLeaveRecordById(leaveId);
        
        if (existingRecordOptional.isEmpty()) {
            return null;  
        }
        
        LeaveRecordEntity existingRecord = existingRecordOptional.get();
        
        if (existingRecord.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalStateException("Cannot update a non-pending leave record.");
        }
        
        existingRecord.setLeaveTypeID(request.getLeaveTypeId());
        if (request.getStartDate() != null) {
            existingRecord.setStart_date(Date.valueOf(request.getStartDate()));
        }
        if (request.getEndDate() != null) {
            existingRecord.setEnd_date(Date.valueOf(request.getEndDate()));
        }
        existingRecord.setReason(request.getReason());
        
        boolean success = leaveRecordRepository.updateLeaveRecord(leaveId, existingRecord);
        
        if (success) {
            return mapEntityToDTO(existingRecord);  
        }
        return null; 
    }
 
    @Override
    public boolean deleteLeaveRecord(int leaveId) {
        Optional<LeaveRecordEntity> existingRecordOptional = leaveRecordRepository.getLeaveRecordById(leaveId);
        
        if (existingRecordOptional.isEmpty()) {
            return false;  
        }
        
        LeaveRecordEntity existingRecord = existingRecordOptional.get();

        if (existingRecord.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalStateException("Cannot delete a non-pending leave record.");
        }
        
        return leaveRecordRepository.deleteLeaveRecord(leaveId);
    }

    @Override
    public boolean approveLeaveRecord(int leaveId) {
        return leaveRecordRepository.approveLeaveRecord(leaveId);
    }

    @Override
    public boolean denyLeaveRecord(int leaveId) {
        return leaveRecordRepository.denyLeaveRecord(leaveId);
    }
    
    @Override
    public List<LeaveRecordResponseDTO> getLeaveRecordsByEmployee(int employeeId) {
        Optional<LeaveRecordEntity> singleRecordOptional = leaveRecordRepository.getLeaveRecordOfEmployee(employeeId);
        
        if (singleRecordOptional.isPresent()) {
            LeaveRecordEntity singleRecord = singleRecordOptional.get();
            return Collections.singletonList(mapEntityToDTO(singleRecord)); 
        }
        return Collections.emptyList();
    }
 
    @Override
    public List<LeaveRecordResponseDTO> getAllPendingLeaveRecords() {
        List<LeaveRecordEntity> entities = leaveRecordRepository.getAllPendingLeaveRecord();
        List<LeaveRecordResponseDTO> dtos = new ArrayList<>();
        for (LeaveRecordEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));  
        }
        return dtos;
    }
}