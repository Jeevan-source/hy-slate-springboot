package com.userManagement.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String dateOfBirth;
    private String gender;
    private String country;
    private String state;
    private String pinCode;
    private String timeZone;
    private String otp;
    private LocalDateTime otpGeneratedAt;
}
