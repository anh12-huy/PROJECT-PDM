package employeeAttendanceSystem.com.employeeSystem.service;

 
import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import employeeAttendanceSystem.com.employeeSystem.model.UserLoginRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.model.UserLoginResponseDTO; 
import java.util.Optional;
public interface UserService {
    Optional<UserLoginResponseDTO> getUserById(int userId); 
    UserLoginResponseDTO updateUser(int userId, UserLoginRequestDTO userDetails);
   
    boolean deleteUser(int userId);
    Optional<UserLoginResponseDTO> getUserByUsername(String username);
}

