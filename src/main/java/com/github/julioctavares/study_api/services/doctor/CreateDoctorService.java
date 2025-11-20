package com.github.julioctavares.study_api.services.doctor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.github.julioctavares.study_api.config.exceptions.EntityAlreadyExistsException;
import com.github.julioctavares.study_api.dto.doctor.DoctorRequestDTO;
import com.github.julioctavares.study_api.dto.doctor.DoctorResponseDTO;
import com.github.julioctavares.study_api.entities.Address;
import com.github.julioctavares.study_api.entities.Doctor;
import com.github.julioctavares.study_api.repositories.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateDoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorResponseDTO execute(DoctorRequestDTO dto) {

        if (doctorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException("O email já está cadastrado");
        }

        if (doctorRepository.findByCrm(dto.getCrm()).isPresent()) {
            throw new EntityAlreadyExistsException("O CRM já está cadastrado");
        }

        // var hashedPassword = new BCryptPasswordEncoder().encode(dto.getPassword()); //TODO: Implementar hash da senha

        Doctor doctor = convertToEntity(dto);
        doctorRepository.save(doctor);
        return new DoctorResponseDTO(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getSpecialty(), doctor.getCrm(), doctor.getPhone(), doctor.getAddress());
    }

    private @NonNull Doctor convertToEntity( DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setPassword(dto.getPassword());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setCrm(dto.getCrm());
        doctor.setPhone(dto.getPhone());
        doctor.setAddress(new Address(
            dto.getAddress().getNeighborhood(), 
            dto.getAddress().getStreet(), 
            dto.getAddress().getNumber(), 
            dto.getAddress().getCity(), 
            dto.getAddress().getState(), 
            dto.getAddress().getZipCode(), 
            dto.getAddress().getComplement()));
        return doctor;
    }
}