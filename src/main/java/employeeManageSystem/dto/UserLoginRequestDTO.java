package employeeManageSystem.dto;

import java.sql.Date;

public class UserLoginRequestDTO {
	    private Long userId;
	    private String username;
	    private String role;
	    private Date lastLogin;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public Date getLastLogin() {
			return lastLogin;
		}
		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}
	    
}
