package com.library.libmanagementsystem.Service.impl;

import com.library.libmanagementsystem.Repository.PasswordResetTokenRepository;
import com.library.libmanagementsystem.Repository.UserRepository;
import com.library.libmanagementsystem.Service.IAuthService;
import com.library.libmanagementsystem.Service.IEmailService;
import com.library.libmanagementsystem.configuration.JwtProvider;
import com.library.libmanagementsystem.domain.UserRole;
import com.library.libmanagementsystem.exception.UserException;
import com.library.libmanagementsystem.mapper.UserMapper;
import com.library.libmanagementsystem.model.PasswordResetToken;
import com.library.libmanagementsystem.model.User;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import com.library.libmanagementsystem.payload.response.AuthResponse;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final CustomUserServiceImpl customUserServiceImpl;
    private final IEmailService emailService;

    @Override
    public AuthResponse login(String email, String password) throws UserException {
        Authentication authentication=authenticate(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
//        String role =authorities.iterator().next().getAuthority();
        String token= jwtProvider.generateToken(authentication);

        User user =userRepository.findByEmail(email);

        //update last login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);


        AuthResponse response=new AuthResponse();
        response.setJwt(token);
        response.setTitle("Login Success");
        response.setMessage("Welcome Back ");
        response.setUser(UserMapper.toDTO(user));
        return response;
    }

    @Override
    public AuthResponse signUp(UserDTO req) throws UserException {
        User user=userRepository.findByEmail(req.getEmail());

        if(user!=null){
            throw new UserException("email by this id already registered");

        }
        User createdUser=new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setPassword(passwordEncoder.encode(req.getPassword()));
        createdUser.setPhone(req.getPhone());
        createdUser.setFullName(req.getFullName());
        createdUser.setLastLogin(LocalDateTime.now());
        createdUser.setRole(UserRole.ROLE_USER);

        User savedUser=userRepository.save(createdUser);


        Authentication auth=new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt=jwtProvider.generateToken(auth);

        AuthResponse response=new AuthResponse();
        response.setJwt(jwt);
        response.setTitle("Welcome"+createdUser.getFullName());
        response.setMessage("registered success");
        response.setUser(UserMapper.toDTO(savedUser));
        return response;

    }

    @Override
    @Transactional
    public void createPasswordResetToken(String email) throws UserException {

        String frontendUrl ="http://localhost:5173";

        User user=userRepository.findByEmail(email);
        if(user==null) {
            throw new UserException("user with the given email is not in the database ");
        }

        String token= UUID.randomUUID().toString();
        PasswordResetToken resetToken=PasswordResetToken.builder()
                .token(token)
                .expiryDate(LocalDateTime.now().plusMinutes(5))
                .build();
        passwordResetTokenRepository.save(resetToken);

        String resetLink=frontendUrl+token;
        String subject="Password reset request ";
        String body="you requested to reset your password. Use this link to reset your password(Link is valid for 5 minutes only) "+resetLink;

        // sent email
        //we will write it later cuz we do not have this now

        emailService.sendEmail(user.getEmail(), subject,body);

    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) throws Exception {

        PasswordResetToken resetToken=passwordResetTokenRepository.findByToken(token)
                .orElseThrow(()-> new Exception("token is invalid "));

        if(resetToken.isExpired()){
            passwordResetTokenRepository.delete(resetToken);
            throw new Exception("token is expired ");
        }
        User user=resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);

    }
// this is just a method which i have used in my service method so that is why i do not need to create the controller of this
    // method for authentication
    private Authentication authenticate(String username,String password) throws UserException {
        UserDetails userDetails=customUserServiceImpl.loadUserByUsername(username);

        if(userDetails==null){
            throw new UserException("User not found ");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new UserException("Password does not match ");
        }
        return new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());


    }
}
