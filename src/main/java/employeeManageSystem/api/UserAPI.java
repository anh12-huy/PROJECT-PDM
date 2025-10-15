package employeeManageSystem.api;

import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.UserResponseDTO;
import employeeManageSystem.dto.UserUpdateRequestDTO;

@RestController
public class UserAPI {
	@GetMapping(value="/api/users/{id}")
	public UserResponseDTO getUser(@PathVariable int userId) {
		return new UserResponseDTO();
	}
	
	@PutMapping(value="/api/users/{id}")
	public void updateUser(@PathVariable int id,
							@RequestBody UserUpdateRequestDTO dto) {
		
	}
	
	@DeleteMapping(value="/api/users/{id}")
	public void deleteUser(@PathVariable int userId) {
		
	}
	
}
