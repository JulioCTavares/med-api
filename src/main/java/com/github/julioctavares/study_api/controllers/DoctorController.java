package com.github.julioctavares.study_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.julioctavares.study_api.config.exceptions.EntityAlreadyExistsException;
import com.github.julioctavares.study_api.dto.doctor.DoctorRequestDTO;
import com.github.julioctavares.study_api.dto.doctor.DoctorResponseDTO;
import com.github.julioctavares.study_api.services.doctor.CreateDoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    
    private final CreateDoctorService createDoctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create( @Valid @RequestBody DoctorRequestDTO dto) {
       try {
        var doctor = this.createDoctorService.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
       } catch (EntityAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }
}
