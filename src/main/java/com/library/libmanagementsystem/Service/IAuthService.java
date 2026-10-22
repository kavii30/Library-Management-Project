package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.payload.dto.UserDTO;
import com.library.libmanagementsystem.payload.response.AuthResponse;

public interface IAuthService {

AuthResponse login(String username,String password);

AuthResponse signUp(UserDTO req);

void createPasswordResetToken(String email);
void resetPassword(String token, String newPassword);
}
