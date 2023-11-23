package com.ereceipt.CAZAEORPROJECT.jwt;
import com.ereceipt.CAZAEORPROJECT.Authentication.AuthService;
import com.ereceipt.CAZAEORPROJECT.Authentication.AuthenticationResponse;
import com.ereceipt.CAZAEORPROJECT.exception.UserNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 *notes
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class JWTController {
    @Autowired
    private  JWTService jwtService;
    @Autowired
    private AuthService authService;
@PostMapping("/login")
    public AuthenticationResponse getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest request){
        return authService.authenticationUser(request);
    }
}

