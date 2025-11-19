package com.github.julioctavares.study_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.julioctavares.study_api.entities.Doctor;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findBySpecialty(String specialty);
    Optional<Doctor> findByCrm(String crm);
}
