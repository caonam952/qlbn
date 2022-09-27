package com.caonam.qlbn.security.Repository;

import com.caonam.qlbn.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
