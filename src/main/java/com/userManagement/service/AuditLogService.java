package com.userManagement.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.userManagement.entity.Auditlog;
import com.userManagement.entity.User;
import com.userManagement.repository.AuditLogRepository;
import com.userManagement.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    public AuditLogService(AuditLogRepository auditLogRepository, UserRepository userRepository) {
        this.auditLogRepository = auditLogRepository;
        this.userRepository = userRepository;
    }

    public void logActivity(String email, String activity, String details, HttpServletRequest request) {
        Optional<User> user = userRepository.findByEmail(email);
        Long userId = user.map(User::getId).orElse(null);
        String ipAddress = request.getRemoteAddr();

        Auditlog log = new Auditlog(userId, email, activity, details, ipAddress);
        auditLogRepository.save(log);
    }

    public List<Auditlog>  getAllAuditLogs() {
        var logs=auditLogRepository.findAll();
        System.out.println(logs);
      return logs;
    }

   
}
