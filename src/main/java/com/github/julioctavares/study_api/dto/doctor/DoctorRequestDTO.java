package com.github.julioctavares.study_api.dto.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Size(min = 3, max = 100, message = "Email deve ter entre 3 e 100 caracteres")
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 100, message = "Senha deve ter entre 8 e 100 caracteres")
    private String password;
    @NotBlank(message = "Especialidade é obrigatória")
    @Size(min = 3, max = 100, message = "Especialidade deve ter entre 3 e 100 caracteres")
    private String specialty;
    @NotBlank(message = "CRM é obrigatório")
    @Size(min = 3, max = 100, message = "CRM deve ter entre 3 e 100 caracteres")
    private String crm;
    @NotBlank(message = "Telefone é obrigatório")
    @Size(min = 3, max = 100, message = "Telefone deve ter entre 3 e 100 caracteres")
    private String phone;
    @NotBlank(message = "Endereço é obrigatório")
    @Size(min = 3, max = 100, message = "Endereço deve ter entre 3 e 100 caracteres")
    private String address;
}
