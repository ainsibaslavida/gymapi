package com.squares.gymapi.exceptions;

public class MemberNotExistsException extends RuntimeException {
    public MemberNotExistsException() { super("The member does not exists."); }
    public MemberNotExistsException(String message) { super(message); }
}
