package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.UserRepository;
import com.library.libmanagementsystem.Service.UserService;
import com.library.libmanagementsystem.mapper.UserMapper;
import com.library.libmanagementsystem.model.User;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() throws Exception {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found exception ");
        }
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<User> user=userRepository.findAll();
        return user.stream().map(
                UserMapper::toDTO
        ).collect(Collectors.toList());
    }
}
