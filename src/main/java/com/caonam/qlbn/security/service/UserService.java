package com.caonam.qlbn.security.service;

import com.caonam.qlbn.security.entity.Role;
import com.caonam.qlbn.security.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    Optional<User> findUserById(Long id);

    Optional<Role> findRoleById(Long id);

//    void update(User user, Long id);

    List<User> getUsers();

    void deleteById(Long id);
}
