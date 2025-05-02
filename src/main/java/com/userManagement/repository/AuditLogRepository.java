package com.userManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userManagement.entity.Auditlog;

import java.util.Optional;


@Repository
public interface AuditLogRepository extends JpaRepository<Auditlog, Long> {
    Optional<Auditlog> findByEmail(String email);
}

