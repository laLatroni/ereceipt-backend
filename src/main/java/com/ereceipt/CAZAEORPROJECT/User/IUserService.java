package com.ereceipt.CAZAEORPROJECT.User;

import java.util.List;

public interface IUserService {
    User add(User user);
    List<UserRecords> getAllUsers();
    void delete(String email);
    User getUser(String email);
    User update(User user);
}

