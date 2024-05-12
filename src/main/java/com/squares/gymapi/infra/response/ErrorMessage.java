package com.squares.gymapi.infra.response;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, String message) {
}
