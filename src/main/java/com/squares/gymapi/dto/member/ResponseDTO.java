package com.squares.gymapi.dto;

import java.time.LocalDateTime;

public record MemberDetailsDTO(
        String cpf,
        String name,
        Integer age,
        String address,
        String phone,
        String plan,
        LocalDateTime created_at,
        Boolean active) {
}
