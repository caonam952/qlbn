package com.caonam.qlbn.security.repository;

import com.caonam.qlbn.security.entities.ERole;
import com.caonam.qlbn.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
