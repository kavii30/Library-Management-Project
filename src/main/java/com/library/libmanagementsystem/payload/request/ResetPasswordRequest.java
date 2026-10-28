package com.library.libmanagementsystem.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

    @NotNull(message="without token you can't reset your password ")
    private String token;

    @NotNull(message="Enter new Password")
    private String newPassword;
}
