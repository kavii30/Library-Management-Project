package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.UserRepository;
import com.library.libmanagementsystem.domain.UserRole;
import com.library.libmanagementsystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

// to run this method whenever we rerun our application
@Override
    public void run(String... args) throws Exception {
    initializeAdminUser();
    }

    private void initializeAdminUser(){
        String adminEmail="jisusbsdiwala@gmail.com";
        String adminPassword="NovaShelf";

        if(userRepository.findByEmail(adminEmail)==null){
            User user=User.builder()
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .fullName("Kavin ")
                    .role(UserRole.ROLE_ADMIN)
                    .build();
        }
    }

}
