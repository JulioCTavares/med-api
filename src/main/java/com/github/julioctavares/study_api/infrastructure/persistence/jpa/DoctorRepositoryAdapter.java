package com.github.julioctavares.study_api.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.julioctavares.study_api.domain.entities.Doctor;
import com.github.julioctavares.study_api.domain.repositories.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DoctorRepositoryAdapter implements DoctorRepository {

    private final SpringDoctorJpaRepository springDoctorJpaRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return springDoctorJpaRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return springDoctorJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> findByCrm(String crm) {
        return springDoctorJpaRepository.findByCrm(crm);
    }
}

