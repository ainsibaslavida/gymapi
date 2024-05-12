package com.squares.gymapi.exceptions;

public class MemberAlreadyExistsException extends RuntimeException {
    public MemberAlreadyExistsException() { super("The member already exists."); }
    public MemberAlreadyExistsException(String message) { super(message); }
}
