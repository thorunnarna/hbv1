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

    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    private UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    public boolean autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails.getUsername().equals("NotFound")) {
            return false;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        System.out.println("slegið inn plain: "+password);
        System.out.println("slegið inn "+pwEncoder.encode(password));
        System.out.println("náð í "+ userDetails.getPassword());
        if (pwEncoder.matches(password, userDetails.getPassword())) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return true;
        }
        //if (usernamePasswordAuthenticationToken.isAuthenticated()) {
        //    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //}
        return false;
    }
}
