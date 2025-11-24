package com.github.julioctavares.study_api.application.dtos.doctor;

import java.util.UUID;

import com.github.julioctavares.study_api.domain.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String specialty;
    private String crm;
    private String phone;
    private Address address;
}
