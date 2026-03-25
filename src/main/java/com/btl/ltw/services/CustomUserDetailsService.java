package com.btl.ltw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.btl.ltw.dao.UserDAO;
import com.btl.ltw.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserDAO userDAO;

        @Override
        public UserDetails loadUserByUsername(String username)
                        throws UsernameNotFoundException {

                User user = userDAO.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return org.springframework.security.core.userdetails.User
                                .withUsername(user.getUsername())
                                .password(user.getPassword())
                                .roles(user.getRole().name().replace("ROLE_", ""))
                                .build();
        }
}