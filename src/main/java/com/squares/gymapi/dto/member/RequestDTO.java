package com.squares.gymapi.dto.member;

import org.hibernate.validator.constraints.br.CPF;

import com.squares.gymapi.domain.types.Plans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestDTO(
        @CPF
        String cpf,
        @NotBlank
        String name,
        @NotNull
        Integer age,
        @NotBlank
        String address,
        @NotBlank
        String phone,
        @NotNull
        Plans plan,
        @NotNull
        Boolean active) {
}
