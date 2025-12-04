package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.AttendanceService;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/checkin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> checkin(HttpServletRequest request,
                                    @RequestParam int shiftId) {

        int userId = getUserIdFromToken(request);

        boolean success = attendanceService.checkIn(userId, shiftId);

        return ResponseEntity.ok(Map.of("success", success, "message", success ? "Check-in successful" : "Check-in failed"));
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> checkout(HttpServletRequest request) {
        int userId = getUserIdFromToken(request);
        boolean success = attendanceService.checkOut(userId);
        return ResponseEntity.ok(Map.of("success", success, "message", success ? "Check-out successful" : "Check-out failed"));
    }

    @PostMapping("/mark")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> mark(@RequestBody AttendanceRequestDTO request,
                                   HttpServletRequest httpRequest) {
        int markerId = getUserIdFromToken(httpRequest);
        boolean success = attendanceService.markManualAttendance(request, request.getEmployeeId(), markerId);
        return ResponseEntity.ok(Map.of("success", success, "message", success ? "Attendance marked successfully" : "Failed to mark attendance"));
    }

    @PostMapping("/auto-absent")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Void> autoMarkAbsent() {
        attendanceService.autoMarkAbsentForDate(LocalDate.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-history")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<List<AttendanceResponseDTO>> myHistory(HttpServletRequest request) {
        int userId = getUserIdFromToken(request);
        List<AttendanceResponseDTO> response = attendanceService.getMyAttendanceHistory(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-date")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<AttendanceResponseDTO>> getByDate(@RequestParam String date) {
        List<AttendanceResponseDTO> response = attendanceService.getAttendanceByDate(LocalDate.parse(date));
        return ResponseEntity.ok(response);
    }
 
    private int getUserIdFromToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            return jwtTokenProvider.getUserIdFromJWT(token);
        }
        throw new RuntimeException("Missing or invalid Authorization header");
    }
}
