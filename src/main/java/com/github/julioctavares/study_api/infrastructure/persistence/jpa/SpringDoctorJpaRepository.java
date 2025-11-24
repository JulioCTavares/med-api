package com.github.julioctavares.study_api.infrastructure.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.julioctavares.study_api.domain.entities.Doctor;

interface SpringDoctorJpaRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByCrm(String crm);
}

