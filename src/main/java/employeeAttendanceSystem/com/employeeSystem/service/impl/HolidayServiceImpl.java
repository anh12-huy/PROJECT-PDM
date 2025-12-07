 

package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.HolidayRepository; 
import employeeAttendanceSystem.com.employeeSystem.repository.AttendanceRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.EmployeeRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.impl.EmployeeRepositoryimpl; 
import employeeAttendanceSystem.com.employeeSystem.repository.impl.HolidayRepositoryimpl; 
import employeeAttendanceSystem.com.employeeSystem.repository.impl.AttendanceRepositoryimpl; 
import employeeAttendanceSystem.com.employeeSystem.service.HolidayService;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.AttendanceStatus;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.EmployeeEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.HolidayEntity;
import employeeAttendanceSystem.com.employeeSystem.model.HolidayResponseDTO; 
import employeeAttendanceSystem.com.employeeSystem.model.HolidayRequestDTO; 
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;  

@Service
public class HolidayServiceImpl implements HolidayService {
 
    private final HolidayRepository holidayRepository;
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
  
    public HolidayServiceImpl() {
        this.holidayRepository = new HolidayRepositoryimpl();
        this.attendanceRepository= new AttendanceRepositoryimpl();
        this.employeeRepository=new EmployeeRepositoryimpl();
    }
    
 
    private HolidayResponseDTO mapEntityToDTO(HolidayEntity entity) {
        if (entity == null) return null;
        HolidayResponseDTO dto = new HolidayResponseDTO();
        dto.setHolidayId(entity.getHolidayID());
        dto.setHolidayName(entity.getHolidayName());
       
        if (entity.getHolidayDate() != null) {
            dto.setHolidayDate(entity.getHolidayDate().toLocalDate());
        }
        return dto;
    }
  
    private HolidayEntity mapRequestToEntity(HolidayRequestDTO request) {
        if (request == null) return null;
        HolidayEntity entity = new HolidayEntity();
        entity.setHolidayName(request.getHolidayName());
        
        if (request.getHolidayDate() != null) {
             entity.setHolidayDate(Date.valueOf(request.getHolidayDate()));
        }
        
        return entity;
    }
    
    @Override
    public List<HolidayResponseDTO> getAllHolidays() {
        List<HolidayEntity> entities = holidayRepository.getAllHoliday();
        List<HolidayResponseDTO> dtos = new ArrayList<>();
        for (HolidayEntity entity : entities) {
            dtos.add(mapEntityToDTO(entity));
        }
        return dtos;
    }
  
    @Override
    public Optional<HolidayResponseDTO> getHolidayById(int holidayId) {
    
        Optional<HolidayEntity> holidayOptional = holidayRepository.getHolidayById(holidayId);
      
        return holidayOptional.map(this::mapEntityToDTO);
    }
 
    @Override
    public HolidayResponseDTO createHoliday(HolidayRequestDTO request) {
   
        HolidayEntity holidayEntity = mapRequestToEntity(request);
        
        if (holidayEntity.getHolidayName() == null || holidayEntity.getHolidayDate() == null) {
            throw new IllegalArgumentException("Holiday name and date cannot be empty.");
        }
        
        if (holidayRepository.checkHoliday(holidayEntity.getHolidayDate())) {
            throw new IllegalArgumentException("A holiday already exists on this date.");
        }
        
        boolean success = holidayRepository.createHolidayRecord(holidayEntity);
        
        if (success) {
            return mapEntityToDTO(holidayEntity); 
        }
        return null;
    }

  
    @Override
    public HolidayResponseDTO updateHoliday(int holidayId, HolidayRequestDTO request) {
       
        Optional<HolidayEntity> existingHolidayOptional = holidayRepository.getHolidayById(holidayId);
        
        if (existingHolidayOptional.isEmpty()) {
            return null;  
        }
        
        HolidayEntity existingHoliday = existingHolidayOptional.get();
      
        existingHoliday.setHolidayName(request.getHolidayName());
        existingHoliday.setHolidayDate(Date.valueOf(request.getHolidayDate()));
       
        boolean success = holidayRepository.updateHolidayRecord(holidayId, existingHoliday);
        
        if (success) {
            return mapEntityToDTO(existingHoliday);
        }
        return null;
    }
 
    @Override
    public boolean deleteHoliday(int holidayId) {
        return holidayRepository.deleteHolidayRecord(holidayId);
    }
 
    @Override
    public boolean isHoliday(Date date) {
        return holidayRepository.checkHoliday(date);
    }
  
    @Override
    public boolean syncAttendanceStatusForHoliday(int holidayId) {
        Optional<HolidayEntity> holidayOptional = holidayRepository.getHolidayById(holidayId);

        if (holidayOptional.isEmpty()) {
            System.out.println("Holiday với ID " + holidayId + " không tồn tại!");
            return false;
        }

        HolidayEntity holiday = holidayOptional.get();
        Date holidayDate = holiday.getHolidayDate();
        System.out.println("Đồng bộ trạng thái chấm công thành 'Holiday' cho ngày " + holidayDate);

        // Lấy tất cả nhân viên
        List<EmployeeEntity> employees = employeeRepository.getAllEmployee();

        for (EmployeeEntity emp : employees) {
            // Kiểm tra attendance đã tồn tại cho ngày holiday chưa
           AttendanceEntity attendance;
            Optional<AttendanceEntity> attendanceOpt = attendanceRepository.findByEmployeeAndDate(emp.getEmployeeID(), holidayDate);

            if (attendanceOpt.isPresent()) {
                attendance = attendanceOpt.get();
            } else {
                attendance = new AttendanceEntity();
                attendance.setEmployeeID(emp.getEmployeeID());
                attendance.setWorkDate(holidayDate);
                attendance.setShiftID(0);           // tránh NPE
                attendance.setHolidayID(holiday.getHolidayID());
            }

            attendance.setStatus(AttendanceStatus.HOLIDAY);
            attendanceRepository.save(attendance);
        }

        return true;
    }
}