package com.ereceipt.CAZAEORPROJECT.Role;

import com.ereceipt.CAZAEORPROJECT.User.User;
import com.ereceipt.CAZAEORPROJECT.User.UserRepository;
import com.ereceipt.CAZAEORPROJECT.exception.RoleAlreadyExistException;
import com.ereceipt.CAZAEORPROJECT.exception.UserAlreadyExistsException;
import com.ereceipt.CAZAEORPROJECT.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @notes
 */
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleImplementation{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role createRole(Role theRole) {
        Optional<Role> checkRole = roleRepository.findByName(theRole.getName());
        if (checkRole.isPresent()){
            throw new RoleAlreadyExistException(checkRole.get().getName()+ " role already exist");
        }
        return roleRepository.save(theRole);
    }

    @Override
    public void deleteRole(Integer roleId) {
        this.removeAllUserFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }
    @Override
    public Role findById(Integer roelId) {
        return roleRepository.findById(roelId).get();
    }

    @Override
    public User removeUserFromRole(Integer userId, Integer roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new UserNotFoundException("User not found!");
    }

    @Override
    public User assignUerToRole(Integer userId, Integer roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new UserAlreadyExistsException(
                    user.get().getFirstName()+ " is already assigned to the " + role.get().getName() +" role");
        }
        role.ifPresent(theRole -> theRole.assignUserToRole(user.get()));
        roleRepository.save(role.get());
        return user.get();
    }

    @Override
    public Role removeAllUserFromRole(Integer roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(Role::removeAllUsersFromRole);
        return roleRepository.save(role.get());
    }
}