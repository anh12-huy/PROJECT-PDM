package employeeAttendanceSystem.com.employeeSystem.service;

import employeeAttendanceSystem.com.employeeSystem.Request.LoginRequest;
import employeeAttendanceSystem.com.employeeSystem.Request.RegisterRequest;
import employeeAttendanceSystem.com.employeeSystem.Request.AuthResponse;

public interface AuthService {
    
    AuthResponse login(LoginRequest request);
    
    AuthResponse register(RegisterRequest request);
    
    void logout(String token);
}

