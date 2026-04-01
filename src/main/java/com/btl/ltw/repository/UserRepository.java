package com.btl.ltw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.ltw.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByEmail(String email);

}