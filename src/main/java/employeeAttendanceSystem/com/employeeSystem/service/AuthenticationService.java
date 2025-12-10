package employeeAttendanceSystem.com.employeeSystem.service;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    RoleType getCurrentUserRole(int userId);
    boolean hasRole(int userId, RoleType role);
    boolean isAdmin(int userId);
    boolean isManager(int userId);
    boolean isEmployee(int userId);
}
