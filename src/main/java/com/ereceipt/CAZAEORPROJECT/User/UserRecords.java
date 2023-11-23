package com.ereceipt.CAZAEORPROJECT.User;

import com.ereceipt.CAZAEORPROJECT.Role.Role;

import java.util.Set;

public class UserRecords {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String roleofUser;
    private Set<Role> roles;

    public UserRecords(Integer id, String firstName, String lastName, String email, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
//        this.roleofUser =roleofUser;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

//    public String getRoleofUser() {
//        return roleofUser;
//    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
