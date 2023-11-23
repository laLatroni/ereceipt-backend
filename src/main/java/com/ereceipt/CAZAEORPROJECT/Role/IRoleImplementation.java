package com.ereceipt.CAZAEORPROJECT.Role;

import com.ereceipt.CAZAEORPROJECT.User.User;

import java.util.List;

public interface IRoleImplementation {
    List<Role> getAllRoles();
    Role createRole(Role theRole);
    void deleteRole(Integer roleId);


    Role findByName(String name);



    Role findById(Integer roelId);

    User removeUserFromRole(Integer userId, Integer roleId);

    User assignUerToRole(Integer userId, Integer roleId);

    Role removeAllUserFromRole(Integer roleId);
}
