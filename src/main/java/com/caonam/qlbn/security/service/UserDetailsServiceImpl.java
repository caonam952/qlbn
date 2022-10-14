package com.caonam.qlbn.security.service;

import com.caonam.qlbn.security.entities.User;
import com.caonam.qlbn.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public Optional<User> findUserById(Long id){
        Optional<User> result = userRepository.findById(id);
        return result;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
