package com.nisum.core.nisumtechnicalchallenge.domain;

import lombok.NonNull;

public record Phone(
    @NonNull String number, @NonNull String cityCode, @NonNull String countryCode) {}
