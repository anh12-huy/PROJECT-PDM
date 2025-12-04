package employeeAttendanceSystem.com.employeeSystem.service.impl;

import java.time.LocalDate;
import java.sql.Date; 
import java.sql.Timestamp; 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;
import employeeAttendanceSystem.com.employeeSystem.repository.AttendanceRepository;
import employeeAttendanceSystem.com.employeeSystem.service.AttendanceService;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
 
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    
    

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
 
     
    private AttendanceResponseDTO convertToDTO(AttendanceEntity entity) {
        AttendanceResponseDTO dto = new AttendanceResponseDTO();
      
        dto.setAttendanceId(entity.getAttendanceID());
        dto.setEmployeeId(entity.getEmployeeID());
     
        if (entity.getCheckIn() != null) {
            dto.setCheckInTime(entity.getCheckIn().toLocalDateTime());
        }
        if (entity.getCheckOut() != null) {
            dto.setCheckOutTime(entity.getCheckOut().toLocalDateTime());
        }
        
       
        if (entity.getStatus() != null) {
             dto.setMethod(entity.getStatus().name()); 
        }
        
        return dto;
    }
 
    
    @Override
    public boolean checkIn(int employeeId, int shiftId) {

        // Kiểm tra xem hôm nay đã check-in chưa
        Optional<AttendanceEntity> existing = 
                attendanceRepository.findOpenCheckInByEmployeeId(employeeId);

        // Nếu đã có check-in rồi => không tạo mới
        if (existing.isPresent() && existing.get().getCheckIn() != null) {
            return false;
        }

        // Ghi check-in
        return attendanceRepository.checkIn(employeeId, shiftId);
    }

    
    @Override
    public boolean checkOut(int employeeId) {

        Optional<AttendanceEntity> at = attendanceRepository.findOpenCheckInByEmployeeId(employeeId);

        // Không có bản ghi => không checkout được
        if (at.isEmpty()) return false;

        // Đã check-out rồi => không cho check-out lại
        if (at.get().getCheckOut() != null) return false;

        return attendanceRepository.checkOut(employeeId);
    }

    
    @Override
    public boolean markManualAttendance(AttendanceRequestDTO dto, int employeeId, int markerId) {

        // Nếu timestamp null, dùng thời gian hiện tại
        LocalDateTime ts = dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now();

        Optional<AttendanceEntity> existing =
                attendanceRepository.findByEmployeeAndDate(employeeId, Date.valueOf(ts.toLocalDate()));

        // Nếu đã tồn tại bản ghi và đã check-in, không override
        if (existing.isPresent() && existing.get().getCheckIn() != null) {
            return false;
        }

        AttendanceEntity entity = new AttendanceEntity();
        entity.setEmployeeID(employeeId);
        entity.setWorkDate(Date.valueOf(ts.toLocalDate()));
        entity.setShiftID(dto.getShiftId());
        entity.setCheckIn(Timestamp.valueOf(ts));
        entity.setStatus(AttendanceStatus.PRESENT);

        return attendanceRepository.save(entity);
    }

    
    
    @Override
    public boolean autoMarkAbsentForDate(LocalDate date) {
        return attendanceRepository.autoMarkAbsent(Date.valueOf(date));
    }
 
    
    @Override
    public List<AttendanceResponseDTO> getMyAttendanceHistory(int employeeId) {
        return attendanceRepository.findAllByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<AttendanceResponseDTO> getAttendanceByDate(LocalDate date) {
        
        Date sqlDate = Date.valueOf(date); 
        
     
        return attendanceRepository.getByDate(sqlDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
   
    @Override
    public String getCurrentStatus(int employeeId) {
        return attendanceRepository
                .getAttendanceStatus(employeeId)
                .map(Enum::name)
                .orElse(AttendanceStatus.ABSENT.name());
    }
}
