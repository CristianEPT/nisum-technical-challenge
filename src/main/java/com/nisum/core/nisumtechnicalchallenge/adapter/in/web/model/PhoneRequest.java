package com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model;

import jakarta.validation.constraints.NotEmpty;

public record PhoneRequest(
    @NotEmpty(message = "Number is required") String number,
    @NotEmpty(message = "City code is required") String cityCode,
    @NotEmpty(message = "Country code is required") String countryCode) {}
