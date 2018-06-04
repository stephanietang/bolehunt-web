package com.bolehunt.domain.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}

