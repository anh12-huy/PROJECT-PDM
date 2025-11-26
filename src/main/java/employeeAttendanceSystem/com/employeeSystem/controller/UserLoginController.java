package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.UserService;
import employeeAttendanceSystem.com.employeeSystem.model.UserLoginRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserLoginController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody UserLoginRequestDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
