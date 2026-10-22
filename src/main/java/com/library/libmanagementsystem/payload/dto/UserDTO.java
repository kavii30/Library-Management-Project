package com.library.libmanagementsystem.payload.dto;

import com.library.libmanagementsystem.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String phone;
    private String fullName;
    private UserRole role;
    private String userName;

    private LocalDateTime lastLogin;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

}
