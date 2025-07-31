package com.tenbit.beep.auth.domain.exception;

public class IllegalArgumentsException extends AuthenticationException {

    public IllegalArgumentsException() {
        super();
    }

    public IllegalArgumentsException(String message) {
        super(message);
    }

    public IllegalArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentsException(Throwable cause) {
        super(cause);
    }
}
