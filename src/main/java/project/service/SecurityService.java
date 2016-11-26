package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Svava on 16.11.16.
 */
@Service
public class SecurityService {

    // Should be autowired, but never managed to make it work.
    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };

    // Used for checking password
    PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    private UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    // Find user logged in (never used)
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    // Log in user
    public boolean autologin(String username, String password) {
        // Check if username already exists
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails.getUsername().equals("NotFound")) {
            return false;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        // Authenticate user (does not work in this version)
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Check manually whether password is correct
        if (pwEncoder.matches(password, userDetails.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return true;
        }
        return false;
    }
}
