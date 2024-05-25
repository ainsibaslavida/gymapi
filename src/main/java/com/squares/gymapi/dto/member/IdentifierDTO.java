package com.squares.gymapi.dto.member;

import org.hibernate.validator.constraints.br.CPF;

public record IdentifierDTO(@CPF String cpf) {
}
