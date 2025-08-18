package com.tenbit.beep.common.exception;

public class ValueMissingException extends AuthenticationException {

    public ValueMissingException() {
        super();
    }

    public ValueMissingException(String message) {
        super(message);
    }

    public ValueMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueMissingException(Throwable cause) {
        super(cause);
    }
}
