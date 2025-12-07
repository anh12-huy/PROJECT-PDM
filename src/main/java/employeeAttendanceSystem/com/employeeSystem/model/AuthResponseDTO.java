package employeeAttendanceSystem.com.employeeSystem.model;

 
import java.io.Serializable;


public class AuthResponseDTO implements Serializable {
 
    private String token; 
    
    private String tokenType = "Bearer"; 
   
    private int userId; 
    private String username;
 
    public AuthResponseDTO() {}
 
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
 
    public int getUserId() {
        return userId;
    }
 
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


