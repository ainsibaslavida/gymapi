package com.squares.gymapi.exceptions.response;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, String message) {
}
