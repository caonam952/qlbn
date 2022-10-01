package com.caonam.qlbn.security.service.impl;

import com.caonam.qlbn.security.entity.Role;
import com.caonam.qlbn.security.entity.User;
import com.caonam.qlbn.security.repository.RoleRepository;
import com.caonam.qlbn.security.repository.UserRepository;
import com.caonam.qlbn.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, RoleRepository theRoleRepository, PasswordEncoder thePasswordEncoder) {
        userRepository = theUserRepository;
        roleRepository = theRoleRepository;
        passwordEncoder = thePasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        log.info("Find user on id: {}", id);
        Optional<User> result = userRepository.findById(id);
        return result;
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        log.info("Find role on id: {}", id);
        Optional<Role> result = roleRepository.findById(id);
        return result;
    }

//    @Override
//    public void update(User user, Long id) {
//        log.info("Update user on id {}", id);
//    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all user");
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.info("delete user on id {}", id);
        userRepository.deleteById(id);
    }


}
