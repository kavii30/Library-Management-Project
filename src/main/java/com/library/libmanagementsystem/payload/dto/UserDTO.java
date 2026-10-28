package com.library.libmanagementsystem.payload.dto;

import com.library.libmanagementsystem.domain.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotNull(message="email can't be null ")
    private String email;

    @NotNull(message = "Password is required ")
    private String password;

    private String phone;
    @NotNull(message = "Full name is mandatory")
    private String fullName;

    private UserRole role;

    @NotNull(message = "username can't be null ")
    private String userName;

    private LocalDateTime lastLogin;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

}
