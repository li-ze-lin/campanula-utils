package io.github.campanula.utils.exception;

public class CampanulaRuntimeException extends RuntimeException {

    public CampanulaRuntimeException(String message) {
        super(message);
    }

    public CampanulaRuntimeException(Throwable cause) {
        super(cause);
    }
}
