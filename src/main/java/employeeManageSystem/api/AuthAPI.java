package employeeManageSystem.api;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import employeeManageSystem.dto.UserLoginRequestDTO;
import employeeManageSystem.dto.UserLoginResponseDTO;
import employeeManageSystem.dto.UserRegisterRequestDTO;
import employeeManageSystem.dto.UserResponseDTO;

@RestController
public class AuthAPI {
	@PostMapping(value="/api/auth/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO request) {
        return new UserLoginResponseDTO();
    }
	
	@PostMapping(value="/api/auth/register")
    public UserResponseDTO register(@RequestBody UserRegisterRequestDTO request) {
        return new UserResponseDTO();
    }
	
	@PostMapping(value="/api/auth/logout")
    public String logout(HttpServletRequest request) {
        return "Logged out successfully";
    }
	
	
}
