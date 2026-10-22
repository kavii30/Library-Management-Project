package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.UserRepository;
import com.library.libmanagementsystem.Service.IAuthService;
import com.library.libmanagementsystem.exception.UserException;
import com.library.libmanagementsystem.model.User;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import com.library.libmanagementsystem.payload.response.AuthResponse;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse login(String username, String password) {
        return null;
    }

    @Override
    public AuthResponse signUp(UserDTO req) {
        User user=userRepository.findByEmail(req.getEmail());

        if(user==null){
            return UserException("email by this id already registered");
        }
    }

    @Override
    public void createPasswordResetToken(String email) {

    }

    @Override
    public void resetPassword(String token, String newPassword) {

    }
}
