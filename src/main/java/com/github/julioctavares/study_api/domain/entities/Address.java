package com.github.julioctavares.study_api.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    private String neighborhood;
    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;
    private String complement;
}
