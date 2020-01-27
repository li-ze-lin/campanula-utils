package io.github.campanula.utils.exception;

/**
 * Author Campanula
 * Date 2019-12-14
 */
public class ObjectEmptyRuntimeException extends RuntimeException {

    public ObjectEmptyRuntimeException() {
        super("object is null");
    }

    public ObjectEmptyRuntimeException(String message) {
        super(message);
    }
}
