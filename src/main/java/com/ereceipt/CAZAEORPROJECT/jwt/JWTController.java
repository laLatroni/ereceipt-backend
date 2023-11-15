package com.ereceipt.CAZAEORPROJECT.jwt;
import com.ereceipt.CAZAEORPROJECT.exception.UserNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/v1/eor")
public class JWTController {
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")

    public String getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest credentials){

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(credentials.getUserName(),credentials.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken( credentials.getUserName() );
            }
        }catch (Exception e){
            throw new UserNotFoundException("BAD CREDENTIALS!!!!");
        }
        return null;
    }


}

