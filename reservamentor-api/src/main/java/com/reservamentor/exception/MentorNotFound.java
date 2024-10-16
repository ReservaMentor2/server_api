package com.reservamentor.exception;

public class MentorNotFound extends RuntimeException {
    public MentorNotFound(String message) {
        super(message);
    }
}
