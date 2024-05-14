package com.squares.gymapi.exceptions;

public class PlanNotExistsException extends RuntimeException {
    public PlanNotExistsException() { super("The chosen plan does not exist."); }
    public PlanNotExistsException(String message) { super(message); }
}
