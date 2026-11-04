package com.library.libmanagementsystem.Service;

import com.library.libmanagementsystem.model.User;
import com.library.libmanagementsystem.payload.dto.UserDTO;

import java.util.List;

public interface UserService {

   public User getCurrentUser() throws Exception;

   public List<UserDTO> getAllUsers();

}
