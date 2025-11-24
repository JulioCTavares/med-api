package com.github.julioctavares.study_api.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioctavares.study_api.application.dtos.doctor.DoctorRequestDTO;
import com.github.julioctavares.study_api.application.dtos.doctor.DoctorResponseDTO;
import com.github.julioctavares.study_api.application.services.doctor.CreateDoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    
    private final CreateDoctorService createDoctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create( @Valid @RequestBody DoctorRequestDTO dto) {
        var doctor = this.createDoctorService.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }
}
