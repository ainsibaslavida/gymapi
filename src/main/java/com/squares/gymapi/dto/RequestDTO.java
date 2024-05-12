package com.squares.gymapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestDTO(
        @NotBlank String cpf,
        @NotBlank String name,
        @NotNull Integer age,
        @NotBlank String address,
        @NotBlank String phone,
        @NotBlank String plan) {
}
