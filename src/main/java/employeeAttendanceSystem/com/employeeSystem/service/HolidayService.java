package employeeAttendanceSystem.com.employeeSystem.service;
 

import employeeAttendanceSystem.com.employeeSystem. model.HolidayResponseDTO;  
import employeeAttendanceSystem.com.employeeSystem. model.HolidayRequestDTO;  
import employeeAttendanceSystem.com.employeeSystem. repository.entity.HolidayEntity;  
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface HolidayService {
 
    List<HolidayResponseDTO> getAllHolidays();
 
    Optional<HolidayResponseDTO> getHolidayById(int holidayId);
 
    HolidayResponseDTO createHoliday(HolidayRequestDTO request);
 
    HolidayResponseDTO updateHoliday(int holidayId, HolidayRequestDTO request);
 
    boolean deleteHoliday(int holidayId);
 
    boolean isHoliday(Date date);
 
    boolean syncAttendanceStatusForHoliday(int holidayId);
}


