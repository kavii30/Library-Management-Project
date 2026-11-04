package com.library.libmanagementsystem.Controller;

import com.library.libmanagementsystem.Service.UserService;
import com.library.libmanagementsystem.model.User;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }
    @GetMapping("/profile")
    ResponseEntity<User> getUserProfile() throws Exception {
        return ResponseEntity.ok(
                userService.getCurrentUser()
        );
    }
}
