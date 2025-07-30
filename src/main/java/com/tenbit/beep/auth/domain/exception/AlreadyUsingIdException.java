package com.tenbit.beep.auth.domain.exception;

public class AlreadyUsingIdException extends AuthenticationException {
    public AlreadyUsingIdException() {
        super();
    }

    public AlreadyUsingIdException(String message) {
        super(message);
    }

    public AlreadyUsingIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyUsingIdException(Throwable cause) {
        super(cause);
    }
}
