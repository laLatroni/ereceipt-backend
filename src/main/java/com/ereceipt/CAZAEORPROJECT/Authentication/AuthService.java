package com.ereceipt.CAZAEORPROJECT.Authentication;

import com.ereceipt.CAZAEORPROJECT.ERECEIPT_SERVICE.EOR.EMAIL_SERVICE;
import com.ereceipt.CAZAEORPROJECT.Token.TokenRepo;
import com.ereceipt.CAZAEORPROJECT.Token.TokenType;
import com.ereceipt.CAZAEORPROJECT.Token.Tokens;
import com.ereceipt.CAZAEORPROJECT.User.User;
import com.ereceipt.CAZAEORPROJECT.User.UserRepository;
import com.ereceipt.CAZAEORPROJECT.User.UserService;
import com.ereceipt.CAZAEORPROJECT.exception.UserAlreadyExistsException;
import com.ereceipt.CAZAEORPROJECT.jwt.JWTAuthenticationRequest;
import com.ereceipt.CAZAEORPROJECT.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EMAIL_SERVICE emailService;
    private final TokenRepo tokenRepo;
    private final UserService userService;



    public AuthenticationResponse authenticationUser(JWTAuthenticationRequest jwtAuthenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthenticationRequest.getUserName(),
                        jwtAuthenticationRequest.getPassword()
                )
        );
        var users = userRepository.findByEmail(jwtAuthenticationRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("USER"));


        var jwtToken =jwtService.generateToken(users.getEmail());
        revokeAllUserTokens(users);
        saveUserToken(users,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    private void revokeAllUserTokens(User user){
        var validateUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
        if(validateUserTokens.isEmpty())
            return;
        validateUserTokens.forEach(tokens ->{
            tokens.setExpired(true);
            tokens.setRevoked(true);
        });
        tokenRepo.saveAll(validateUserTokens);
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Tokens.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.Bearer)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }

}
