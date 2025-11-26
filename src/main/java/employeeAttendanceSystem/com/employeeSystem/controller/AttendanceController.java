package employeeAttendanceSystem.com.employeeSystem.controller;

import employeeAttendanceSystem.com.employeeSystem.service.AttendanceService;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceResponseDTO;
import employeeAttendanceSystem.com.employeeSystem.model.AttendanceRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkin(HttpServletRequest request,
                                    @RequestParam int shiftId) {

        int userId = getUserIdFromToken(request);

        boolean success = attendanceService.checkIn(userId, shiftId);

        return ResponseEntity.ok(success ? "Check-in successful" : "Check-in failed");
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(HttpServletRequest request) {
        int userId = getUserIdFromToken(request);
        boolean success = attendanceService.checkOut(userId);
        return ResponseEntity.ok(success ? "Check-out successful" : "Check-out failed");
    }

    @PostMapping("/mark")
    public ResponseEntity<?> mark(@RequestBody AttendanceRequestDTO request,
                                   @RequestParam int employeeId,
                                   HttpServletRequest httpRequest) {
        int markerId = getUserIdFromToken(httpRequest);
        boolean success = attendanceService.markManualAttendance(request, employeeId, markerId);
        return ResponseEntity.ok(success ? "Attendance marked successfully" : "Failed to mark attendance");
    }

    @PostMapping("/auto-absent")
    public ResponseEntity<Void> autoMarkAbsent() {
        attendanceService.autoMarkAbsentForDate(LocalDate.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-history")
    public ResponseEntity<List<AttendanceResponseDTO>> myHistory(HttpServletRequest request) {
        int userId = getUserIdFromToken(request);
        List<AttendanceResponseDTO> response = attendanceService.getMyAttendanceHistory(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-date")
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
