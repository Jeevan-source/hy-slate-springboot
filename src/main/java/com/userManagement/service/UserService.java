package com.userManagement.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userManagement.dto.UserDto;
import com.userManagement.entity.User;
import com.userManagement.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDto registerUser(UserDto userDto) {

	
    private final AuditLogService auditLogService;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,AuditLogService auditLogService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.auditLogService=auditLogService;
	}

	public UserDto registerUser(UserDto userDto, HttpServletRequest request) {

		// Check if email or phone number already exists
		if (userRepository.existsByEmail(userDto.getEmail())) {
			throw new IllegalArgumentException("Email is already registered!");
		}
		if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
			throw new IllegalArgumentException("Phone number is already registered!");
		}

		// Convert DTO to Entity
		User user = User.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName())
				.email(userDto.getEmail()).phoneNumber(userDto.getPhoneNumber())
				.password(passwordEncoder.encode(userDto.getPassword())) // Encrypt password
				.build();
		userRepository.save(user);



        auditLogService.logActivity(user.getEmail(), "ACCOUNT_CREATED", "User created their account", request);

		// Save user in the database
		return userDto;
	}


	public String deleteUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new EntityNotFoundException("user not Found");
		}
		userRepository.delete(user.get());
		return "user deleted successfully";
	}

	public String deactivateUser(long id) {

	public String deleteUser(long id, HttpServletRequest request) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new EntityNotFoundException("user not Found");
		}
		
		userRepository.delete(user.get());
		auditLogService.logActivity(user.get().getEmail(), "ACCOUNT_DELETED", "User deleted their account", request);
		return "user deleted successfully";
	}

	public String deactivateUser(long id, HttpServletRequest request) {

		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new EntityNotFoundException("user not found");
		User user1 = user.get();
		user1.setEnabled(false);
		userRepository.save(user1);


		auditLogService.logActivity(user1.getEmail(), "ACCOUNT_DEACTIVATED", "User deactivated their account", request);

		return "deactivated successfully";
	}
}
