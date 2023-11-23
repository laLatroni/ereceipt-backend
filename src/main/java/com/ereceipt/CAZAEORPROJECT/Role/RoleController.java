package com.ereceipt.CAZAEORPROJECT.Role;
import com.ereceipt.CAZAEORPROJECT.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @notes
 */
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleImplementation roleService;

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return new ResponseEntity<>(roleService.createRole(role), CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void createRole(@PathVariable("id") Integer roleId){
        roleService.deleteRole(roleId);
    }
    @PostMapping("/remove-all-users-from-role/{id}")
    public Role removeAllUsersFromRole(@PathVariable("id") Integer roleId){
        return roleService.removeAllUserFromRole(roleId);
    }
    @PostMapping("/remove-user-from-role")
    public User removeUserFromRole(@RequestParam("userId")Integer userId,
                                   @RequestParam("roleId") Integer roleId){
        return roleService.removeUserFromRole(userId, roleId);
    }

    @PostMapping("/assign-user-to-role")
    public User assignUserToRole(@RequestParam("userId")Integer userId,
                                 @RequestParam("roleId") Integer roleId){
        return roleService.assignUerToRole(userId, roleId);
    }
}

