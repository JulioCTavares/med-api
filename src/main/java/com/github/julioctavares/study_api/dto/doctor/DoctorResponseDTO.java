package com.github.julioctavares.study_api.dto.doctor;

import java.util.UUID;

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
    private String address;
}
