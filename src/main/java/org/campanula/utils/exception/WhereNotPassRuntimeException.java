package org.campanula.utils.exception;

public class WhereNotPassRuntimeException extends RuntimeException {

    public WhereNotPassRuntimeException() {
        super("not pass");
    }

    public WhereNotPassRuntimeException(String message) {
        super(message);
    }
}
