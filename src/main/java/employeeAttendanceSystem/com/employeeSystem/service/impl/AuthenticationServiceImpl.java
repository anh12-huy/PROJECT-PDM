package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.UserLoginRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import employeeAttendanceSystem.com.employeeSystem.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserLoginRepository userLoginRepository;

    public AuthenticationServiceImpl() {
        this.userLoginRepository = new employeeAttendanceSystem.com.employeeSystem.repository.impl.UserLoginRepositoryimpl();
    }

    @Override
    public RoleType getCurrentUserRole(int userId) {
        Optional<UserLoginEntity> userOptional = userLoginRepository.getUserById(userId);
        return userOptional.map(UserLoginEntity::getRole).orElse(null);
    }

    @Override
    public boolean hasRole(int userId, RoleType role) {
        RoleType userRole = getCurrentUserRole(userId);
        return userRole != null && userRole == role;
    }

    @Override
    public boolean isAdmin(int userId) {
        return hasRole(userId, RoleType.ADMIN);
    }

    @Override
    public boolean isManager(int userId) {
        RoleType userRole = getCurrentUserRole(userId);
        return userRole == RoleType.ADMIN || userRole == RoleType.MANAGER;
    }

    @Override
    public boolean isEmployee(int userId) {
        RoleType userRole = getCurrentUserRole(userId);
        return userRole == RoleType.ADMIN || userRole == RoleType.MANAGER || userRole == RoleType.EMPLOYEE;
    }
}
