package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.exception.UserException;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import com.library.libmanagementsystem.payload.response.AuthResponse;

public interface IAuthService {

AuthResponse login(String username,String password) throws UserException;

AuthResponse signUp(UserDTO req) throws UserException;

void createPasswordResetToken(String email) throws UserException;
void resetPassword(String token, String newPassword) throws Exception;
}
