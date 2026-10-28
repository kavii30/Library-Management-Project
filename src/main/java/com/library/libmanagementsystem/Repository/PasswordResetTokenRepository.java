package com.library.libmanagementsystem.Repository;

import com.library.libmanagementsystem.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

     Optional<PasswordResetToken> findByToken(String token);
}
