package com.ereceipt.CAZAEORPROJECT.User;


import com.ereceipt.CAZAEORPROJECT.Authentication.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eor")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping("login")
//    public  ResponseEntity<?>login(@RequestBody User user){
//
//        User user1 = userService.login(user.getEmail(), PasswordEncoder)
//
//        return ResponseEntity.ok(user.)
//        return "login";
//    }
    @GetMapping("/user-all")
    public List<UserRecords>getAll(){
        return this.userService.getAllUsers();

    }
    @PostMapping("/user-register")
    public ResponseEntity<AuthenticationResponse> add(@RequestBody User user){
        return ResponseEntity.ok(userService.add(user));
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable("email") String email){
        return  userService.getUser(email);
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable("email") String email){
        userService.delete(email);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user)  {
        return ResponseEntity.ok(userService.update(user));
    }

}

