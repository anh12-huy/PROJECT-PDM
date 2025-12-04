package employeeAttendanceSystem.com.employeeSystem.Security;

import employeeAttendanceSystem.com.employeeSystem.repository.entity.RoleType;
import employeeAttendanceSystem.com.employeeSystem.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklist tokenBlacklist;
    private final AuthenticationService authenticationService;

    public JwtAuthenticationFilter(JwtTokenProvider provider,
                                   TokenBlacklist blacklist,
                                   AuthenticationService authenticationService) {
        this.jwtTokenProvider = provider;
        this.tokenBlacklist = blacklist;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (token != null && jwtTokenProvider.validate(token) && !tokenBlacklist.isBlacklisted(token)) {
            int userId = jwtTokenProvider.getUserIdFromJWT(token);

            // Get user role from database
            RoleType userRole = authenticationService.getCurrentUserRole(userId);

            // Create authorities list
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userRole.name());
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(authority);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            userId, null, authorities
                    );

            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
