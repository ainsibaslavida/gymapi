package com.squares.gymapi.dto;

import com.squares.gymapi.domain.types.Plans;

import java.time.LocalDateTime;

public record ResponseDTO(
        String cpf,
        String name,
        Integer age,
        String address,
        String phone,
        Plans plan,
        LocalDateTime created_at,
        LocalDateTime last_update,
        Boolean active) {
}
