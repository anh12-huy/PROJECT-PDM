package employeeManageSystem.api;
import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.LeaveDTO;
import employeeManageSystem.dto.LeaveTypeDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LeaveTypeAPI {
	@GetMapping(value="/api/leave-types")
	public List<LeaveTypeDTO> getAllLeaveType(){
		List<LeaveTypeDTO> result=new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/leave-types/{id}")
	public LeaveTypeDTO getLeaveType(@PathVariable String leaveTypeId) {
		return new LeaveTypeDTO();
	}
	
	
	@GetMapping(value="/api/leave-types/{id}/remaining-days/{employeeId}")
	public int getDayOffLeft(@PathVariable String leaveTypeId,
							@PathVariable String employeeId) {
	return 0;
	}
	
	@PostMapping(value="/api/leave-types")
	public void createLeaveType() {
}
}
