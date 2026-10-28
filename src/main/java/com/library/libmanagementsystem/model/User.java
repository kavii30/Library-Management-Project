package com.library.libmanagementsystem.model;

import com.library.libmanagementsystem.domain.AuthProvider;
import com.library.libmanagementsystem.domain.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true,nullable=false)
    private String email;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    // Save enum as its name, not its position number, so the data stays correct if the enum list changes
    @ToString.Exclude
    private String password;

    private String phone;

    private AuthProvider authProvider= com.library.libmanagementsystem.domain.AuthProvider.LOCAL;

    private String googleID;

    private String profileImage;

    private LocalDateTime lastLogin;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
