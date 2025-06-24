package com.userManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_user")  // Consistent with DB naming and previous logs
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "firstName must be provided")
    private String firstName;

    @NotBlank(message = "lastName must be provided")
    private String lastName;

    @NotBlank(message = "email must be provided")
    private String email;

    @NotBlank(message = "phoneNumber must be provided")
    private String phoneNumber;

    @NotBlank(message = "password must be provided")
    private String password;

    @NotBlank(message = "dateOfBirth must be provided")
    private String dateOfBirth;

    @NotBlank(message = "gender must be provided")
    private String gender;

    @NotBlank(message = "country must be provided")
    private String country;

    @NotBlank(message = "state must be provided")
    private String state;

    @NotBlank(message = "pinCode must be provided")
    private String pinCode;

    @Column(nullable = true) // Allows old records with null
    private String timeZone;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;

    // 🔐 OTP fields for forgot-password
    @Column(nullable = true)
    private String otp;

    @Column(nullable = true)
    private LocalDateTime otpGeneratedAt;
}
