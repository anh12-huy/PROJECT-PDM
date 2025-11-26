package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.Request.LoginRequest;
import employeeAttendanceSystem.com.employeeSystem.Request.RegisterRequest;
import employeeAttendanceSystem.com.employeeSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
         System.out.println("Received login request: " + request);
    System.out.println("Username: " + request.getUsername());
    System.out.println("Password: " + request.getPassword());
    try {
        return ResponseEntity.ok(authService.login(request));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        try {
            authService.logout(authHeader);
            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }
}
