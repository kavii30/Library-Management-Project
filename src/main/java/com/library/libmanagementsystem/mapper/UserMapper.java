package com.library.libmanagementsystem.mapper;

import com.library.libmanagementsystem.model.User;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        // we have not set password because never give saved password to user again it is not
        // it is not the good practice to return back password;


        return userDTO;


    }

    public static List<UserDTO> toDTOList(List<User> users){
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Set<UserDTO> toDTOset(Set<User> user){
        return user.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toSet());
    }


    public static User toEntity(UserDTO userDTO){
        User createdUser=new User();
        createdUser.setEmail(userDTO.getEmail());
        createdUser.setPassword(userDTO.getPassword());
        createdUser.setCreatedAt(LocalDateTime.now());
        createdUser.setPhone(userDTO.getPhone());
        createdUser.setFullName(userDTO.getFullName());
        createdUser.setRole(userDTO.getRole());
        return createdUser;

    }
}
