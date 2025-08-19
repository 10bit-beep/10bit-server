package com.tenbit.beep.common.exception;

public class InvalidNfcTagException extends RuntimeException {
  public InvalidNfcTagException() {
    super();
  }

  public InvalidNfcTagException(String message) {
    super(message);
  }

  public InvalidNfcTagException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidNfcTagException(Throwable cause) {
    super(cause);
  }
}
