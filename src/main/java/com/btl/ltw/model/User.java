package com.btl.ltw.model;

import com.btl.ltw.enums.Role;
import com.btl.ltw.enums.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, EMPLOYEE, CUSTOMER

    @Enumerated(EnumType.STRING)
    private UserStatus status; // ACTIVE, INACTIVE

    private LocalDateTime createdAt;    
}
