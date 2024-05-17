package com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model;

import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.configuration.PasswordPattern;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record UserRequest(
    @NotEmpty(message = "Name is required") String name,
    @Email(message = "Email should be valid, following next structure aaaaaaa@dominio.cl")
        @NotEmpty(message = "Email is required")
        String email,
    @PasswordPattern String password,
    @Valid @NotEmpty(message = "Phones are required") List<PhoneRequest> phones) {}
