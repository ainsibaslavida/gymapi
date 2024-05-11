package com.squares.gymapi.dto.member;

import java.time.LocalDateTime;

public record ResponseDTO(
        String cpf,
        String name,
        Integer age,
        String address,
        String phone,
        String plan,
        LocalDateTime created_at,
        LocalDateTime last_update,
        Boolean active) {
}
