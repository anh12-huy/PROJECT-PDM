package employeeAttendanceSystem.com.employeeSystem.service;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Integer) {
            return (Integer) authentication.getPrincipal();
        }
        throw new RuntimeException("No authenticated user found");
    }

    public RoleType getCurrentUserRole() {
        // Get role through service injection if needed
        // For now, return null - this will be enhanced
        return null;
    }
}
