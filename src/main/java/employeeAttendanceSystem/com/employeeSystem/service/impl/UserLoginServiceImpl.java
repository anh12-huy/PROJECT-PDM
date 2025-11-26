package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.UserLoginRepository; 
import employeeAttendanceSystem.com.employeeSystem.repository.impl.UserLoginRepositoryimpl; 
import employeeAttendanceSystem.com.employeeSystem.service.UserService; 
import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import employeeAttendanceSystem.com.employeeSystem.model.UserLoginRequestDTO;
import employeeAttendanceSystem.com.employeeSystem.model.UserLoginResponseDTO; 
import java.util.Optional;
import org.springframework.stereotype.Service; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;  

@Service 
public class UserLoginServiceImpl implements UserService{
    
    private final UserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder; 
  
    
    private UserLoginResponseDTO mapEntityToDTO(UserLoginEntity entity) {
        if (entity == null) return null;
        UserLoginResponseDTO dto = new UserLoginResponseDTO();
        dto.setUserId(entity.getUserID());
        dto.setUsername(entity.getUsername());
        
  
        return dto;
    }

    public UserLoginServiceImpl() {
        this.userLoginRepository = new UserLoginRepositoryimpl(); 
        this.passwordEncoder = new BCryptPasswordEncoder(); 
    }
  
    @Override
    public Optional<UserLoginResponseDTO> getUserById(int userId) {
         
        Optional<UserLoginEntity> userOptional = userLoginRepository.getUserById(userId);
        
         
        return userOptional.map(this::mapEntityToDTO);
    }
 
    @Override
    public UserLoginResponseDTO updateUser(int userId, UserLoginRequestDTO userDetails) {
        
        Optional<UserLoginEntity> existingUserOptional = userLoginRepository.getUserById(userId);
        
        if (existingUserOptional.isEmpty()) {
            return null;  
        }
        
        UserLoginEntity existingUser = existingUserOptional.get();
        
       
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setRole(userDetails.getRole());  
 
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(userDetails.getPassword()));
        }
 
        boolean success = userLoginRepository.updateUser(userId, existingUser);
        
        if (success) {
            return mapEntityToDTO(existingUser);  
        }
        return null; 
    }
 
    @Override
    public boolean deleteUser(int userId) {
        
        return userLoginRepository.deleteUser(userId);
    }
   
     
    @Override
    public Optional<UserLoginResponseDTO> getUserByUsername(String username) {
        
        Optional<UserLoginEntity> userOptional = userLoginRepository.getUserByUsername(username);
        
         
        return userOptional.map(this::mapEntityToDTO);
    }
}