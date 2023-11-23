package com.ereceipt.CAZAEORPROJECT.User;

;
import javax.transaction.Transactional;

//import com.ereceipt.CAZAEORPROJECT.Authentication.AuthenticationResponse;
import com.ereceipt.CAZAEORPROJECT.Authentication.AuthenticationResponse;
import com.ereceipt.CAZAEORPROJECT.Role.Role;
import com.ereceipt.CAZAEORPROJECT.Role.RoleRepository;
import com.ereceipt.CAZAEORPROJECT.Token.TokenRepo;
import com.ereceipt.CAZAEORPROJECT.Token.TokenType;
import com.ereceipt.CAZAEORPROJECT.Token.Tokens;
import com.ereceipt.CAZAEORPROJECT.exception.UserAlreadyExistsException;
import com.ereceipt.CAZAEORPROJECT.exception.UserNotFoundException;
import com.ereceipt.CAZAEORPROJECT.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;
    private final TokenRepo tokenRepo;


// add a user, if email is present throw exception
    @Override
    public AuthenticationResponse add(User user) {
        Optional<User> theUser = userRepository.findByEmail(user.getEmail());
        if (theUser.isPresent()) {
            throw new UserAlreadyExistsException("A user with " + user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            user.setRoles(Collections.singletonList(role));
        } else {
            throw new RuntimeException("ROLE_USER NOT FOUND");
        }
        var saveUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user.getEmail());

        saveUserToken(saveUser, jwtToken);
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

    // GET ALL DETAILS OF USERS
    @Override
    public List<UserRecords> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserRecords(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),

                        new HashSet<>(user.getRoles()))).collect(Collectors.toList());
    }

    //DELETE 1 BY 1 USING EMAIL

    @Override
    @Transactional
    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    // SELECT 1 USER USING EMAIL
    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(""));
    }

    // UPDATE A USER DETAILS

    @Override
    public User update(User user) {
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }
}

