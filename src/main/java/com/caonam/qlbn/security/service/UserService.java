package com.caonam.qlbn.security.service;

import com.caonam.qlbn.security.entity.Role;
import com.caonam.qlbn.security.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
