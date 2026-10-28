package com.library.libmanagementsystem.Controller;

import com.library.libmanagementsystem.Service.impl.AuthServiceImpl;
import com.library.libmanagementsystem.exception.UserException;
import com.library.libmanagementsystem.payload.dto.UserDTO;
import com.library.libmanagementsystem.payload.request.ForgotPasswordRequest;
import com.library.libmanagementsystem.payload.request.LoginRequest;
import com.library.libmanagementsystem.payload.request.ResetPasswordRequest;
import com.library.libmanagementsystem.payload.response.ApiResponse;
import com.library.libmanagementsystem.payload.response.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUpHandler
            (@RequestBody @Valid UserDTO req) throws UserException {
       AuthResponse res =authService.signUp(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> loginHandler
            (@RequestBody @Valid LoginRequest req) throws UserException {
       AuthResponse res =authService.login(req.getEmail(), req.getPassword());
       return ResponseEntity.ok(res);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse> forgetPassword(
            @RequestBody @Valid ForgotPasswordRequest req
    ) throws UserException {
        authService.createPasswordResetToken(req.getEmail());

        ApiResponse res=new ApiResponse("Please click on the link to reset your password",true);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPasswordHandler(@RequestBody @Valid ResetPasswordRequest req) throws Exception {
        authService.resetPassword(req.getToken(),req.getNewPassword());
        ApiResponse res=new ApiResponse("Your new password has been generated ",true);
        return ResponseEntity.ok(res);
    }

}
