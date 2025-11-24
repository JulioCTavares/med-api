package com.github.julioctavares.study_api.domain.repositories;

import java.util.Optional;

import com.github.julioctavares.study_api.domain.entities.Doctor;

public interface DoctorRepository {
    Doctor save(Doctor doctor);
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByCrm(String crm);
}
