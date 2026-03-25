package com.btl.ltw.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.btl.ltw.dao.UserDAO;
import com.btl.ltw.enums.Role;
import com.btl.ltw.enums.UserStatus;
import com.btl.ltw.model.User;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {

        if (userDAO.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userDAO.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);

        userDAO.save(user);
    }
}