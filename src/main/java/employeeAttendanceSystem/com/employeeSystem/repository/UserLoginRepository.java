package employeeAttendanceSystem.com.employeeSystem.repository;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import java.util.Optional;
public interface UserLoginRepository {
	Optional<UserLoginEntity> getUserById(int id);
    boolean updateUser(int id, UserLoginEntity entity);
    boolean deleteUser(int id);
    boolean createUser(UserLoginEntity entity); 
    Optional<UserLoginEntity> getUserByUsername(String username); 
}
