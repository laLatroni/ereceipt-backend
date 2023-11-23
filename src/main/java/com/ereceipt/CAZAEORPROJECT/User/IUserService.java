package com.ereceipt.CAZAEORPROJECT.User;

import com.ereceipt.CAZAEORPROJECT.Authentication.AuthenticationResponse;

import java.util.List;

public interface IUserService {
    AuthenticationResponse add(User user);
    List<UserRecords> getAllUsers();
    void delete(String email);
    User getUser(String email);
    User update(User user);
}

