package employeeAttendanceSystem.com.employeeSystem.service.impl;

import employeeAttendanceSystem.com.employeeSystem.repository.UserLoginRepository;
import employeeAttendanceSystem.com.employeeSystem.repository.impl.UserLoginRepositoryimpl;
import employeeAttendanceSystem.com.employeeSystem.service.AuthService;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.UserLoginEntity;
import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
import employeeAttendanceSystem.com.employeeSystem.Request.LoginRequest;
import employeeAttendanceSystem.com.employeeSystem.Request.RegisterRequest;
import employeeAttendanceSystem.com.employeeSystem.Request.AuthResponse;
import employeeAttendanceSystem.com.employeeSystem.Security.JwtTokenProvider;
import employeeAttendanceSystem.com.employeeSystem.Security.TokenBlacklist;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final UserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklist tokenBlacklist;


    public AuthServiceImpl(UserLoginRepository userLoginRepository,
                        PasswordEncoder passwordEncoder,
                        JwtTokenProvider jwtTokenProvider,
                        TokenBlacklist tokenBlacklist) {

        this.userLoginRepository = userLoginRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenBlacklist = tokenBlacklist;
    }
    
    @Override
    public AuthResponse login(LoginRequest request) {

        int userId;
        try {
            userId = Integer.parseInt(request.getUsername()); 
        } catch (NumberFormatException e) {
            throw new RuntimeException("UserID must be an integer");
        }

        Optional<UserLoginEntity> userOptional = userLoginRepository.getUserById(userId);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("UserID does not exist");
        }
        
        UserLoginEntity user = userOptional.get();
        

        if (!request.getPassword().equals(user.getPasswordHash())) { 
            throw new RuntimeException("Wrong password");
        }


        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userLoginRepository.updateUser(user.getUserID(), user);

        String token = jwtTokenProvider.generateToken(user.getUserID());
        return new AuthResponse(user.getUserID(), token, user.getRole().toString());
    }
    
    @Override
    public AuthResponse register(RegisterRequest request) {
        Optional<UserLoginEntity> existingUser = userLoginRepository.getUserByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        UserLoginEntity newUser = new UserLoginEntity();
        newUser.setUsername(request.getUsername());
        newUser.setPasswordHash(request.getPassword()); // plaintext
        newUser.setRole(RoleType.EMPLOYEE);
        newUser.setLastLogin(new Timestamp(System.currentTimeMillis()));

        boolean success = userLoginRepository.createUser(newUser);
        if (!success) throw new RuntimeException("Failed to create user");

        Optional<UserLoginEntity> createdUser = userLoginRepository.getUserByUsername(request.getUsername());
        if (createdUser.isEmpty()) throw new RuntimeException("Failed to retrieve created user");

        String token = jwtTokenProvider.generateToken(createdUser.get().getUserID());
        return new AuthResponse(createdUser.get().getUserID(), token, createdUser.get().getRole().toString());
    }

    @Override
    public void logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        tokenBlacklist.add(token);
    }
}
