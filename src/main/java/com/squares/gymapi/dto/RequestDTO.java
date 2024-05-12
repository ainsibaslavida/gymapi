package com.squares.gymapi.dto;

import com.squares.gymapi.domain.types.Plans;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestDTO(
        @NotBlank String cpf,
        @NotBlank String name,
        @NotNull Integer age,
        @NotBlank String address,
        @NotBlank String phone,
        @NotBlank Plans plan,
        @NotNull Boolean active) {
}
