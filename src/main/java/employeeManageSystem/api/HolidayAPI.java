package employeeManageSystem.api;

import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.HolidayDTO;
import employeeManageSystem.dto.LeaveDTO;
import employeeManageSystem.dto.LeaveTypeDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HolidayAPI {
	@GetMapping(value="/api/holidays")
	public List<HolidayDTO> getAllHodiday(@PathVariable Date startDate,
										  @PathVariable Date endDate){
		List<HolidayDTO> result=new ArrayList<>();
		return result;
	}	
	
	@GetMapping(value="/api/holidays/{id}")
	public HolidayDTO getHoliday(@PathVariable int id) {
		return new HolidayDTO();
	}
	
	@GetMapping(value="/api/holidays/check")
	public boolean holidayCheck(@RequestParam("Date") Date date) {
		return false;
	}
	
	@PostMapping(value="/api/holidays")
	public void createHolidays(@RequestBody HolidayDTO dto) {
		
	}
	
	
	@PutMapping(value="/api/holidays/{id}")
	public void updateHoliday(@RequestBody HolidayDTO dto,
							  @PathVariable int holidayId) {
		
	}
	
	@DeleteMapping(value="/api/holidays/{id}")
	public void deleteHoliday(@PathVariable int holidayId) {
	
	
	}
}
