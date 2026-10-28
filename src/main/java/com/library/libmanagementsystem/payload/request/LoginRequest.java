package com.library.libmanagementsystem.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull(message="User name can't be null ")
    private String email;

    @NotNull(message="Password can't be null ")
    private String password;
}
