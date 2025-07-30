package com.tenbit.beep.auth.domain.exception;

public class IllegalArgumentException extends AuthenticationException {

    public IllegalArgumentException() {
        super();
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
