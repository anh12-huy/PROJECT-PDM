package employeeAttendanceSystem.com.employeeSystem.model;



import java.io.Serializable;

import javax.management.relation.Role;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
 
public class UserLoginRequestDTO implements Serializable {
 

    private String email;
    private String username;
    private String password;
    private RoleType role;

    
    
    public UserLoginRequestDTO() {}
    public RoleType getRole(){
        return role;
    }
    public void setRole(RoleType role){
        this.role=role;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
 
}
