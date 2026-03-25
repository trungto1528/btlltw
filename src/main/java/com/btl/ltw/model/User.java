package com.btl.ltw.model;

import com.btl.ltw.enums.Role;
import com.btl.ltw.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private Role role; // ADMIN, EMPLOYEE, CUSTOMER
    private UserStatus status; // ACTIVE, INACTIVE
    private LocalDateTime createdAt;
}
