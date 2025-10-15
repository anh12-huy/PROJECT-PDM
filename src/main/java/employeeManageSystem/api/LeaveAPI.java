package employeeManageSystem.api;

import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.LeaveDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LeaveAPI {
	
	@GetMapping(value="/api/leaves")
	public List<LeaveDTO> getAllLeave(){
		List<LeaveDTO> result= new ArrayList<>();
		return result;
	}
	
	@GetMapping(value="/api/leaves/{id}")
	public LeaveDTO getLeave(@PathVariable int leaveId) {
		return new LeaveDTO();
	}
	
	@GetMapping(value="/api/leaves/employee/{employeeId}")
	public LeaveDTO getLeaveOfEmployee(@PathVariable int employeeId) {
		return new LeaveDTO();
	}
	
	@GetMapping(value="/api/leaves/pending")
	public List<LeaveDTO> getPendingLeave() {
		List<LeaveDTO> result= new ArrayList<>();
		return result;
	}
	
	@PostMapping(value="/api/leaves")
	public void createLeave(@RequestBody LeaveDTO dto) {
		
	}
	
	@PostMapping(value="/api/leaves/{id}/approve")
	public void approveLeave(@PathVariable int leaveId) {
		
	}
	
	@PostMapping(value="/api/leaves/{id}/reject")
	public void rejectLeave(@PathVariable int leaveId) {
		
	}
	
	
	@PutMapping(value="/api/leaves/{id}")
	public void updateLeave(@RequestBody LeaveDTO dto) {
		
	}
	
	@DeleteMapping(value="/api/leaves/{id}")
	public void deleteLeave(@PathVariable int leaveId) {
		
	}
}


